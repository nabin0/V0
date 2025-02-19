package org.example.project.core.navigation

import kotlinx.coroutines.flow.Flow

interface NavigationManager {
    val navigationEvent: Flow<NavigationCommand>
    fun navigate(command: NavigationCommand)
}
