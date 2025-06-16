package com.tests.composeplaygroundapp.composables.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface NavDestinations {
    @Serializable data object Root: NavDestinations
    @Serializable data object DestinationA: NavDestinations
    @Serializable data object DestinationB: NavDestinations
    @Serializable data object NestedDestination: NavDestinations
}

@Serializable
sealed interface NavNested {
    @Serializable data object NDestinationA: NavNested
    @Serializable data object NDestinationB: NavNested
}