@file:OptIn(ExperimentalCoroutinesApi::class)

package com.fps.events.presentation

import com.fps.core.domain.connectivity.ConnectivityManager
import com.fps.core.domain.connectivity.NetworkConnectivityState
import com.fps.core.domain.events.SportCategory
import com.fps.events.presentation.fakes.FakeConnectivityManager
import com.fps.events.presentation.fakes.FakeEventsRepository
import com.fps.events.presentation.fakes.FakeLocalSportEventsDataSource
import com.fps.events.presentation.fakes.FakeRemoteEventsDataSource
import com.fps.events.presentation.helpers.FakeDataHelper
import com.fps.events.presentation.helpers.MainCoroutineExtension
import com.fps.events.presentation.liveevents.LiveEventsViewModel
import com.fps.events.presentation.liveevents.SportEventsAction
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.RegisterExtension

@ExtendWith(MainCoroutineExtension::class)
class LiveEventsViewModelTest {

    companion object {
        @JvmField
        @RegisterExtension
        val mainCoroutineExtension = MainCoroutineExtension()
    }

    private lateinit var testDispatcher: TestDispatcher
    private lateinit var testScope: CoroutineScope

    private lateinit var viewModel: LiveEventsViewModel
    private lateinit var fakeEventsRepository: FakeEventsRepository

    private lateinit var fakeLocalSportEventsDataSource: FakeLocalSportEventsDataSource
    private lateinit var fakeRemoteSportEventsDataSource: FakeRemoteEventsDataSource

    private lateinit var connectivityManager: ConnectivityManager

    private lateinit var fakeConnectivityManager: FakeConnectivityManager
    private lateinit var viewModelAction: SportEventsAction
    private lateinit var randomSportCategoryData: List<SportCategory>

    @BeforeEach
    fun setUp() {
        fakeLocalSportEventsDataSource = FakeLocalSportEventsDataSource()
        fakeRemoteSportEventsDataSource = FakeRemoteEventsDataSource()

        randomSportCategoryData = FakeDataHelper.getFakeSportCategories()
        fakeRemoteSportEventsDataSource.setRandomLiveEvents(randomSportCategoryData)

        testDispatcher = UnconfinedTestDispatcher()
        testScope = CoroutineScope(testDispatcher)

        fakeEventsRepository = FakeEventsRepository(
            localSportEventsDataSource = fakeLocalSportEventsDataSource,
            remoteLiveEventsDataSource = fakeRemoteSportEventsDataSource
        )

        fakeConnectivityManager = FakeConnectivityManager()

        connectivityManager = mockk()
        every { connectivityManager.networkState() } returns flowOf(NetworkConnectivityState.Available)


        viewModel = LiveEventsViewModel(
            liveEventsRepository = fakeEventsRepository,
            connectivityManager = connectivityManager
        )
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun `initial state is correct`() = runTest {
        val initialState = viewModel.state
        assertTrue(initialState.isConnectedToNetwork)
        assertFalse(initialState.isLoading)
        assertEquals(10, initialState.sportEvents.size)
    }

    @Test
    fun `handles network connectivity changes`() = runTest {
        every { connectivityManager.networkState() } returns flowOf(NetworkConnectivityState.Unavailable)

        viewModel = LiveEventsViewModel(fakeEventsRepository, connectivityManager)
        testScheduler.advanceUntilIdle()

        assertFalse(viewModel.state.isConnectedToNetwork)
    }

    @Test
    fun `fetch live events updates state`() = runTest {
        viewModel = LiveEventsViewModel(fakeEventsRepository, connectivityManager)
        advanceUntilIdle() // Ensure all coroutines complete
        assertFalse(viewModel.state.isLoading)
        assertTrue(viewModel.state.sportEvents.isNotEmpty())
    }

    @Test
    fun `loads live events successfully`() = runTest {
        viewModel = LiveEventsViewModel(fakeEventsRepository, connectivityManager)
        testScheduler.advanceUntilIdle()

        assertFalse(viewModel.state.isLoading)
        assertEquals(10, viewModel.state.sportEvents.size)
    }

    @Test
    fun `onFavouriteClick action sets non-favourite event as favourite`() = runTest {
        // Find a non-favourite event
        val nonFavouriteEvent = viewModel.state.sportEvents
            .flatMap { it.activeSportEvents }
            .first { !it.isFavourite }

        val eventId = nonFavouriteEvent.id

        // Perform the action to set the event as favourite
        viewModel.onAction(SportEventsAction.OnFavouriteClick(eventId, true))
        testScheduler.advanceUntilIdle()

        // Verify that the event is now marked as favourite
        val updatedEvent = viewModel.state.sportEvents
            .flatMap { it.activeSportEvents }
            .first { it.id == eventId }

        assertTrue(updatedEvent.isFavourite)
    }

    @Test
    fun `Check when the user filters a sport category, it show favourites only`() = runTest {
        // Find a non-favourite event within its category
        val nonFavouriteCategory = viewModel.state.sportEvents.first { category ->
            category.activeSportEvents.any { !it.isFavourite }
        }

        viewModel.onAction(
            SportEventsAction.OnShowOnlyFavourites(
                id = nonFavouriteCategory.sportId,
                isFavouritesChecked = true
            )
        )
        advanceUntilIdle()

        val state = viewModel.state
        val filteredCategory = state.sportEvents.find { it.sportId == nonFavouriteCategory.sportId }
        assert(filteredCategory != null) { "Filtered category should not be null" }
        assert(filteredCategory?.activeSportEvents?.all { it.isFavourite } == true) {
            "All active sport events in the filtered category should be favourites"
        }
    }

}