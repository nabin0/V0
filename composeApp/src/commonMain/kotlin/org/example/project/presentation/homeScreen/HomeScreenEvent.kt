package org.example.project.presentation.homeScreen

sealed class HomeScreenEvent {
    data class GetUserData(val userId: String): HomeScreenEvent()
}
