package com.fps.core.domain.events

import com.fps.core.domain.util.DataError
import com.fps.core.domain.util.Result

interface RemoteEventsDataSource {
    suspend fun getLiveEvents(): Result<List<SportCategory>, DataError.Network>
}