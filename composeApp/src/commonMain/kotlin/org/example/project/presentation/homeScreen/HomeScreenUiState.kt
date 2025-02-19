package org.example.project.presentation.homeScreen

import org.example.project.core.UiState

data class HomeScreenUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
) : UiState() {
    sealed class PartialState {
        object Loading : PartialState()

        data class Fetched(val userEmail: String) : PartialState()

        data class Error(val throwable: Throwable) : PartialState()
    }
}
