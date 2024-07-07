package com.fps.events.presentation

import assertk.assertThat
import com.fps.events.presentation.fakes.FakeConnectivityManager
import com.fps.events.presentation.fakes.FakeEventsRepository
import com.fps.events.presentation.fakes.FakeLocalSportEventsDataSource
import com.fps.events.presentation.fakes.FakeRemoteEventsDataSource
import com.fps.events.presentation.helpers.FakeDataHelper
import com.fps.events.presentation.helpers.MainCoroutineExtension
import com.fps.events.presentation.liveevents.LiveEventsViewModel
import com.fps.events.presentation.liveevents.SportEventsAction
import com.fps.events.presentation.liveevents.mappers.toSportCategoryItemUi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
//import kotlinx.coroutines.Dispatchers
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

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
    private lateinit var fakeConnectivityManager: FakeConnectivityManager
    private lateinit var viewModelAction: SportEventsAction

    @BeforeEach
    fun setUp() {
        fakeEventsRepository = FakeEventsRepository(
            localSportEventsDataSource = FakeLocalSportEventsDataSource(),
            remoteLiveEventsDataSource = FakeRemoteEventsDataSource()
        )

        fakeConnectivityManager = FakeConnectivityManager()

        viewModel = LiveEventsViewModel(
            liveEventsRepository = fakeEventsRepository,
            connectivityManager = fakeConnectivityManager
        )
    }

    @AfterEach
    fun tearDown() {
//        Dispatchers.resetMain()
    }

    @Test
    fun getState() {
        viewModel.state
    }

    @Test
    fun getNetworkStatus() {
    }

    @Test
    fun `Check when the user filters a sport category, it show favourites only`() = runTest {
        val fakeSportCategories = FakeDataHelper.getFakeSportCategories()
        val firstCategoryId = fakeSportCategories.first().sportId

        viewModel.onAction(
            SportEventsAction.OnShowOnlyFavourites(
                id = firstCategoryId,
                isFavouritesChecked = true
            )
        )

        fakeEventsRepository.getLocalLiveEvents().onEach { sportCategory ->
            val sportCategoryItem = sportCategory
                .filter { it.sportId == firstCategoryId }
                .map { it.toSportCategoryItemUi() }
                .firstOrNull()
            assertThat(sportCategoryItem?.activeSportEvents?.none { !it.isFavourite })
        }
    }

}