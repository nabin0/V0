package org.example.project.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun appModule() = listOf(provideNavigationModule, provideViewModelModule, provideUiStateModule)

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(
        appModule()
    )
}
