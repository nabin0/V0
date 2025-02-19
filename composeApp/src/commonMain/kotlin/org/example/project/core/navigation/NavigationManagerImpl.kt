package org.example.project.core.navigation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class NavigationManagerImpl(
    private val externalMainImmediateScope: CoroutineScope = EmptyCoroutineScope()
) : NavigationManager {
    private val navigationCommandChannel = Channel<NavigationCommand>(Channel.BUFFERED)
    override val navigationEvent = navigationCommandChannel.receiveAsFlow()

    override fun navigate(command: NavigationCommand) {
        externalMainImmediateScope.launch {
            navigationCommandChannel.send(command)
        }
    }
}


class EmptyCoroutineScope : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.Default
}