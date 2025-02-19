package org.example.project.core.navigation

object NavigationUtils {
    private var navigationManager: NavigationManager? = null
    fun getCustomNavigationManager(): NavigationManager? {
         navigationManager = navigationManager ?: NavigationManagerImpl()
        return navigationManager
    }
}