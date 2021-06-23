package com.example.raihanclubapps.core.domain

import com.example.raihanclubapps.core.domain.Club


class FootballInteractor(private val footballRepository: IFootballRepository): FootballUseCase {

    override fun getAllClub() = footballRepository.getAllClub()

    override fun getFavoriteClub() = footballRepository.getFavoriteClub()

    override fun setFavoriteClub(club: Club, state: Boolean) = footballRepository.setFavoriteClub(club, state)
}