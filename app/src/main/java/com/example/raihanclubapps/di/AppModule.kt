package com.example.raihanclubapps.di

import com.example.raihanclubapps.core.domain.FootballInteractor
import com.example.raihanclubapps.core.domain.FootballUseCase
import com.example.raihanclubapps.ui.clubs.ClubsViewModel
import com.example.raihanclubapps.ui.detail.DetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<FootballUseCase> { FootballInteractor(get()) }
}

val viewModelModule = module {
    viewModel { ClubsViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}