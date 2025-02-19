package org.example.project.core.navigation

import org.example.project.core.navigation.NavigationDestination

interface NavigationCommand {
    val destination: NavigationDestination
    val data: Any?
}
