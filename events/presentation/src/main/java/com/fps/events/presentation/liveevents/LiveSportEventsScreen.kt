package com.fps.events.presentation.liveevents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.fps.core.presentation.designsystem.StoiximanGray
import com.fps.core.presentation.designsystem.StoiximanTheme
import com.fps.core.presentation.designsystem.components.ConnectivityStatus
import com.fps.core.presentation.designsystem.components.ErrorScreen
import com.fps.core.presentation.designsystem.components.MainAppBackground
import com.fps.events.presentation.liveevents.components.CustomLazyColumn
import com.fps.events.presentation.liveevents.components.ExpandableSportEventCard
import org.koin.androidx.compose.koinViewModel

// We create this composable in order to use it in the app, but this composable calls another composable
// which does not include anything related to the navigation so this LiveEventsScreen can be tested
// in the future and not having to rely into anything else
@Composable
fun LiveEventsScreenRoot(
    viewModel: LiveEventsViewModel = koinViewModel()
) {
    LiveEventsScreen(
        state = viewModel.state,
        onAction = { action ->
            when (action) {
                // these lambdas, are empty but if we had to navigate to another screen we would do it here
                is SportEventsAction.OnFavouriteClick -> {}
                is SportEventsAction.OnShowOnlyFavourites -> {}
            }
            viewModel.onAction(action)
        })
}

@Composable
fun LiveEventsScreen(
    state: LiveSportEventsState, onAction: (SportEventsAction) -> Unit
) {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        MainAppBackground(
            modifier = Modifier
                .background(StoiximanGray)
        ) {
            ConnectivityStatus(isConnected = state.isConnectedToNetwork)

            if (state.isConnectedToNetwork) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(StoiximanGray)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(StoiximanGray)
                    ) {
                        if (state.error == null) {
                            CustomLazyColumn(
                                modifier = Modifier
                                    .background(StoiximanGray),
                                items = state.filteredFavouritesSportEvents,
                                content = {
                                    ExpandableSportEventCard(
                                        sportCategoryItemUi = it,
                                        onSetFavouriteItem = { favouriteAction ->
                                            onAction(favouriteAction)
                                        },
                                        onShowOnlyFavourites = { showOnlyFavouritesAction ->
                                            onAction(showOnlyFavouritesAction)
                                        }
                                    )
                                })
                        } else {
                            val context = LocalContext.current
                            ErrorScreen(state.error.asString(context))
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun LiveEventsScreenPreview() {
    StoiximanTheme {
        LiveEventsScreen(state = LiveSportEventsState(), onAction = {})
    }
}