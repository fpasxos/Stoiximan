package com.fps.events.data.datasources

import com.fps.core.data.networking.get
import com.fps.core.domain.events.SportCategory
import com.fps.core.domain.events.RemoteEventsDataSource
import com.fps.core.domain.util.DataError
import com.fps.core.domain.util.Result
import com.fps.core.domain.util.map
import com.fps.events.data.mappers.toSportCategory
import com.fps.events.data.models.SportCategoryDto
import io.ktor.client.HttpClient

class KtorRemoteEventsDataSource(
    private val httpClient: HttpClient
) : RemoteEventsDataSource {
    override suspend fun getLiveEvents(): Result<List<SportCategory>, DataError.Network> {
        return httpClient.get<List<SportCategoryDto>>(
            route = "/api/sports",
        ).map { sportCategories ->
            sportCategories.map { sportCategory ->
                sportCategory.toSportCategory()
            }
        }
    }
}