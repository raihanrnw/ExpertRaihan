package com.example.raihanclubapps.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.raihanclubapps.core.domain.FootballUseCase

class FavoriteViewModel(footballUseCase: FootballUseCase) : ViewModel() {
    val club = footballUseCase.getFavoriteClub().asLiveData()
}