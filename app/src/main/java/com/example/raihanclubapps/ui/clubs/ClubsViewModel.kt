package com.example.raihanclubapps.ui.clubs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.raihanclubapps.core.domain.FootballUseCase

class ClubsViewModel(footballUseCase: FootballUseCase) : ViewModel() {
    val club = footballUseCase.getAllClub().asLiveData()
}