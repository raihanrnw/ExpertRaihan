package com.example.raihanclubapps.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.raihanclubapps.R
import com.example.raihanclubapps.core.domain.Club
import com.example.raihanclubapps.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailClub = intent.getParcelableExtra<Club>(EXTRA_DATA)
        showDetailTourism(detailClub)
    }

    private fun showDetailTourism(detailClub: Club?) {
        detailClub?.let {
            binding.apply {
                supportActionBar?.title = detailClub.name
               clubName .text = detailClub.name
                detailLocation.text = detailClub.stadium
                detailDescription.text = detailClub.description
                Glide.with(this@DetailActivity)
                    .load(detailClub.logo)
                    .into(binding.detailLogo)

                var statusFavorite = detailClub.isFavorite
                setStatusFavorite(statusFavorite)
                btnFavorite.setOnClickListener {
                    statusFavorite = !statusFavorite
                    detailViewModel.setFavoriteClub(detailClub, statusFavorite)
                    setStatusFavorite(statusFavorite)
                }
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.btnFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.favorite_penuh))
        } else {
            binding.btnFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.favorite_kosong))
        }
    }
}