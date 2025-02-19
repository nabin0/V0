package org.example.project.di

import org.example.project.presentation.homeScreen.HomeScreenViewModel
import org.koin.dsl.module

val provideViewModelModule = module {
    single {
        HomeScreenViewModel(get(), get())
    }
}