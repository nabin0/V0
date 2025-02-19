package org.example.project.di

import org.example.project.core.navigation.NavigationManager
import org.example.project.core.navigation.NavigationManagerImpl
import org.koin.dsl.module

val provideNavigationModule = module {
    single<NavigationManager>(createdAtStart = true) {
        NavigationManagerImpl()
    }
}