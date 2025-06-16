package com.tests.composeplaygroundapp.composables.navigation

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.tests.composeplaygroundapp.testtags.DestinationPlaceholderTags

@Composable
fun RootNavigation() {

    val navHost = rememberNavController()

    NavHost(
        startDestination = NavDestinations.Root,
        navController = navHost
    ) {
        composable<NavDestinations.Root> {
            DestinationPlaceholder(
                destination = "Navigation landing page",
                onAction = {
                    navHost.navigate(NavDestinations.DestinationA)
                }
            )
        }

        composable<NavDestinations.DestinationA> {
            DestinationPlaceholder(
                destination = "Destination A",
                onAction = {
                    navHost.navigate(NavDestinations.DestinationB)
                }
            )
        }

        composable<NavDestinations.DestinationB> {
            DestinationPlaceholder(
                destination = "Destination B",
                onAction = {
                    navHost.navigate(NavDestinations.NestedDestination)
                }
            )
        }

        navigation<NavDestinations.NestedDestination>(
            startDestination = NavNested.NDestinationA
        ) {
            composable<NavNested.NDestinationA> {
                DestinationPlaceholder(
                    destination = "Nested navigation landing",
                    onAction = {
                        navHost.navigate(NavNested.NDestinationB)
                    }
                )
            }

            composable<NavNested.NDestinationB> {

                val context = LocalContext.current

                DestinationPlaceholder(
                    destination = "Another nested navigation destination",
                    onAction = {
                        Toast.makeText(context, "No more destinations", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }
}

@Composable
private fun DestinationPlaceholder(
    destination: String,
    onAction: () -> Unit,
) {
    Column(
        modifier = Modifier
    ) {
        Text(
            modifier = Modifier
                .testTag(DestinationPlaceholderTags.TEXT_TAG),
            text = destination,
            style = MaterialTheme.typography.bodyLarge
        )

        Button(
            modifier = Modifier
                .testTag(DestinationPlaceholderTags.BUTTON_TAG),
            onClick = onAction
        ) {
            Text("Navigate")
        }
    }
}