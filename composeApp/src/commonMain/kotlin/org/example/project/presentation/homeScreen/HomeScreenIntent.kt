package org.example.project.presentation.homeScreen

sealed class HomeScreenIntent {
    data object GetUserData: HomeScreenIntent()
}
