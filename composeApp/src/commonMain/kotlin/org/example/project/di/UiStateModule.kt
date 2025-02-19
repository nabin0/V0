package org.example.project.di

import org.example.project.presentation.homeScreen.HomeScreenUiState
import org.koin.dsl.module

val provideUiStateModule = module {
    single {
        HomeScreenUiState()
    }

}