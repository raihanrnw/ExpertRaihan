package com.example.raihanclubapps.ui.detail

import androidx.lifecycle.ViewModel
import com.example.raihanclubapps.core.domain.Club
import com.example.raihanclubapps.core.domain.FootballUseCase

class DetailViewModel(private val footballUseCase: FootballUseCase) : ViewModel() {
    fun setFavoriteClub(club: Club, newStatus:Boolean) =
        footballUseCase.setFavoriteClub(club, newStatus)
}