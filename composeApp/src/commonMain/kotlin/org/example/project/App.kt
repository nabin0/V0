package org.example.project

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import kotlinx.coroutines.launch
import org.example.project.core.navigation.NavigationDestination
import org.example.project.core.navigation.NavigationManager
import org.example.project.core.navigation.getScreen
import org.example.project.presentation.homeScreen.HomeScreen
import org.koin.compose.getKoin

@Composable
fun App() {
    MaterialTheme {
        val navigationManager: NavigationManager = getKoin().get()
        val coroutineScope = rememberCoroutineScope()
        var baseNavigator: Navigator? = null
        coroutineScope.launch {
            navigationManager.navigationEvent.collect {
                when (it.destination) {
                    NavigationDestination.PopBackStack -> baseNavigator?.pop()
                    NavigationDestination.ReplaceAllWith -> baseNavigator?.replaceAll(
                        getScreen(it.data as NavigationDestination, null)
                    )

                    NavigationDestination.PopBackStackUpTo -> {
                        baseNavigator?.popUntil { screen ->
                            val destination = it.data as NavigationDestination
                            screen == getScreen(destination, null)
                        }
                    }

                    else -> baseNavigator?.push(getScreen(it.destination, it.data))
                }
            }
        }

        Navigator(screen = HomeScreen(),
            content = { navigator ->
                baseNavigator = navigator
                FadeTransition(navigator)
            }
        )
    }
}
