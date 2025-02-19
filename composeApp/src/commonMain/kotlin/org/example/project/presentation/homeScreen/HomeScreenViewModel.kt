package org.example.project.presentation.homeScreen

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.example.project.core.BaseViewModel
import org.example.project.core.navigation.NavigationManager

class HomeScreenViewModel(
    homeScreenUiState: HomeScreenUiState,
    val navigationManager: NavigationManager,
) : BaseViewModel<HomeScreenUiState, HomeScreenUiState.PartialState, HomeScreenEvent, HomeScreenIntent>(
    homeScreenUiState
) {


    override fun mapIntents(intent: HomeScreenIntent): Flow<HomeScreenUiState.PartialState> =
        when (intent) {
            HomeScreenIntent.GetUserData -> getUserData()
        }

    private fun getUserData(): Flow<HomeScreenUiState.PartialState> = flow {
        emit(HomeScreenUiState.PartialState.Loading)
    }

    override fun reduceUiState(
        previousState: HomeScreenUiState,
        partialState: HomeScreenUiState.PartialState
    ): HomeScreenUiState = when (partialState) {
        is HomeScreenUiState.PartialState.Error -> previousState.copy(
            isLoading = false,
            isError = true
        )

        is HomeScreenUiState.PartialState.Fetched -> previousState.copy(
            isLoading = false,
            isError = false
        )

        HomeScreenUiState.PartialState.Loading -> previousState.copy(
            isLoading = false,
            isError = true
        )
    }



}