package org.example.project.core.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import org.example.project.presentation.homeScreen.HomeScreen

sealed class NavigationDestination {
    data object HomeScreen : NavigationDestination()
    data object PopBackStack : NavigationDestination()
    data object PopBackStackUpTo : NavigationDestination()
    data object ReplaceAllWith : NavigationDestination()
}

fun getScreen(navigationDestination: NavigationDestination, data: Any?): Screen {
    return when (navigationDestination) {
        NavigationDestination.HomeScreen -> HomeScreen()
        NavigationDestination.PopBackStack -> PopToBackStackScreen()
        NavigationDestination.PopBackStackUpTo -> PopBackStackUpToScreen() // use data to Provide NavigationDestination until which screen should be removed
        NavigationDestination.ReplaceAllWith -> ReplaceAllWithScreen()

    }
}

class PopToBackStackScreen : Screen {
    @Composable
    override fun Content() {
    }
}

class PopBackStackUpToScreen : Screen {
    @Composable
    override fun Content() {
    }
}

class ReplaceAllWithScreen : Screen {
    @Composable
    override fun Content() {
    }
}
