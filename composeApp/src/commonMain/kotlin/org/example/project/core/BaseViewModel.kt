package org.example.project.core

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseViewModel<UI_STATE : UiState, PARTIAL_UI_STATE, EVENT, INTENT>(initialState: UI_STATE) :
    ViewModel() {

    private val intentFlow = MutableSharedFlow<INTENT>()

    val uiState: MutableStateFlow<UI_STATE> = MutableStateFlow(initialState)

    private val eventChannel = Channel<EVENT>(Channel.BUFFERED)
    val event = eventChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            intentFlow
                .flatMapMerge { mapIntents(it) }
                .scan(uiState.value, ::reduceUiState)
                .catch {
                    emit(uiState.value)
                }
                .collect {
                    uiState.value = it
                }
        }
    }

    fun acceptIntent(intent: INTENT) =
        viewModelScope.launch {
            intentFlow.emit(intent)
        }

    protected fun publishEvent(event: EVENT) {
        viewModelScope.launch {
            eventChannel.send(event)
        }
    }

    abstract fun mapIntents(intent: INTENT): Flow<PARTIAL_UI_STATE>

    abstract fun reduceUiState(
        previousState: UI_STATE,
        partialState: PARTIAL_UI_STATE
    ): UI_STATE

}

open class UiState