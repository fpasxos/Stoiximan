package com.fps.stoiximan

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.fps.events.presentation.liveevents.LiveEventsScreenRoot

@Composable
fun NavigationRoot(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "liveevents"
    ) {
        liveEventsGraph(navController)
    }
}

// We create a graph for each feature, so in this case only for the liveevents
private fun NavGraphBuilder.liveEventsGraph(navController: NavHostController) {
    navigation(
        startDestination = "intro",
        route = "liveevents"
    ) {
        composable(route = "intro") {
            LiveEventsScreenRoot(
                /* here, we can put navigation through lambdas, so when a user makes an action
                 which requires navigation, it will be 'executed' here and the navigation will
                 decide for the related 'graph'. For now, we do not have other screen in the app
                */
            )
        }
    }
}