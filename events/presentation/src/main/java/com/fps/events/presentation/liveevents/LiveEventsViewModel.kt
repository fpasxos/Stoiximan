package com.fps.events.presentation.liveevents

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fps.core.domain.connectivity.ConnectivityManager
import com.fps.core.domain.connectivity.NetworkConnectivityState
import com.fps.core.domain.events.EventsRepository
import com.fps.events.presentation.liveevents.mappers.toSportCategoryItemUi
import com.fps.events.presentation.liveevents.models.SportCategoryItemUi
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LiveEventsViewModel(
    private val liveEventsRepository: EventsRepository,
    connectivityManager: ConnectivityManager
) : ViewModel(), DefaultLifecycleObserver {

    var state by mutableStateOf(LiveSportEventsState())
        private set

    private val networkStatus: StateFlow<NetworkConnectivityState> =
        connectivityManager.networkState().stateIn(
            initialValue = NetworkConnectivityState.Available,
            scope = viewModelScope,
            started = WhileSubscribed(5000)
        )

    init {
        viewModelScope.launch {
            launch {
                collectNetworkStatus()
            }
        }
        getLocalLiveEvents()
        fetchRemoteLiveEvents()
    }

    private suspend fun collectNetworkStatus() {
        networkStatus.collect {
            state =
                state.copy(isConnectedToNetwork = it == NetworkConnectivityState.Available)
        }
    }
    private fun getLocalLiveEvents() {
        liveEventsRepository.getLocalLiveEvents().onEach { liveEvents ->
            val sportCategoryItems = liveEvents.map { it.toSportCategoryItemUi(viewModelScope) }
            state = state.copy(
                sportEvents = sportCategoryItems,
                filteredFavouritesSportEvents = filterSportCategories(sportCategoryItems),
                isLoading = false
            )
        }.launchIn(viewModelScope)
    }

    private fun fetchRemoteLiveEvents() {
        viewModelScope.launch {
            liveEventsRepository.getLiveEvents()
            state = state.copy(isLoading = false)
        }
    }

    fun onAction(action: SportEventsAction) {
        when (action) {
            is SportEventsAction.OnFavouriteClick -> {
                viewModelScope.launch {
                    liveEventsRepository.setSportEventAsFavourite(action.id, action.isFavourite)
                }
            }

            is SportEventsAction.OnShowOnlyFavourites -> {
                viewModelScope.launch {
                    liveEventsRepository.showFavouritesOnlyFromSportCategory(
                        action.id,
                        action.isFavouritesChecked
                    )
                }
            }
        }
    }
}

fun filterSportCategories(categories: List<SportCategoryItemUi>): List<SportCategoryItemUi> {
    return categories.map { category ->
        if (category.isShowOnlyFavourites) {
            val filteredEvents = category.activeSportEvents.filter { it.isFavourite }
            category.copy(activeSportEvents = filteredEvents)
        } else {
            category
        }
    }
}