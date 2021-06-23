package com.example.raihanclubapps.ui.clubs

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.raihanclubapps.R
import com.example.raihanclubapps.core.data.Resource
import com.example.raihanclubapps.core.ui.ClubAdapter
import com.example.raihanclubapps.databinding.FragmentClubsBinding
import com.example.raihanclubapps.ui.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class ClubsFragment : Fragment() {

    private val clubsViewModel: ClubsViewModel by viewModel()
    private var _binding: FragmentClubsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClubsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val clubAdapter = ClubAdapter()
            clubAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            clubsViewModel.club.observe(viewLifecycleOwner, { club ->
                if (club != null) {
                    when (club) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            clubAdapter.setData(club.data)
                            binding.progressBar.visibility = View.GONE
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.errornotif.root.visibility = View.VISIBLE
                            binding.errornotif.error.text = club.message ?: getString(R.string.error_notif)
                        }
                    }
                }
            })
            with(binding.rvClubs) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = clubAdapter
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}