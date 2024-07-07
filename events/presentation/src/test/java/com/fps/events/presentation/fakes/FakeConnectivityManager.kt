package com.fps.events.presentation.fakes

import com.fps.core.domain.connectivity.ConnectivityManager
import com.fps.core.domain.connectivity.NetworkConnectivityState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeConnectivityManager : ConnectivityManager {
    override fun networkState(): Flow<NetworkConnectivityState> {
        return flowOf(NetworkConnectivityState.Available)
    }
}