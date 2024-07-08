package com.fps.events.presentation.helpers

import com.fps.core.domain.events.SportCategory
import com.fps.core.domain.events.SportEvent
import java.time.Instant
import kotlin.random.Random

object FakeDataHelper {

    fun getFakeSportCategories(): List<SportCategory> {
        return generateRandomSportCategoryData()
    }

    private fun generateRandomSportCategoryData(): MutableList<SportCategory> {
        val categoriesList = mutableListOf<SportCategory>()
        repeat(10) {
            categoriesList.add(getRandomSportCategory())
        }
        return categoriesList
    }

    private fun getRandomSportCategory(): SportCategory {
        val sportId = generateRandomSportId()
        return SportCategory(
            sportId = sportId,
            sportName = generateRandomSportName(),
            activeSportEvents = generateRandomSportEventData(sportId),
            isShowOnlyFavourites = Random.nextBoolean()
        )
    }

    private fun generateRandomSportEventData(sportId: String): MutableList<SportEvent> {
        val sportEventList = mutableListOf<SportEvent>()
        repeat(10) {
            sportEventList.add(getRandomSportEvent(sportId))
        }

        return sportEventList
    }


    private fun getRandomSportEvent(sportId: String): SportEvent {
        return SportEvent(
            eventId = (0..100000).random().toString(),
            sportId = sportId,
            fullEventName = getRandomTeamName(),
            eventStartTime = getRandomFutureUnixTime(),
            isFavourite = Random.nextBoolean()
        )
    }

    // here the name which is quite irrelevant will be different from sport id, due to random values
    private fun generateRandomSportName(): String {
        val symbols = listOf(
            "SOCCER",
            "BASKETBALL",
            "TENNIS",
            "TABLE TENNIS",
            "VOLLEYBALL",
            "ESPORTS",
            "BEACH VOLLEYBALL",
            "BADMINTON",
        )
        return symbols.random()
    }

    private fun generateRandomSportId(): String {
        val symbols = listOf("FOOT", "BASK", "TENN", "TABL", "VOLL", "ESPS", "BCHV", "BADM")
        return symbols.random()
    }

    private fun getRandomTeamName(): String {
        val firstNameLength = Random.nextInt(5, 16)
        val lastNameLength = Random.nextInt(5, 16)

        val firstName = getRandomString(firstNameLength)
        val lastName = getRandomString(lastNameLength)

        return "$firstName - $lastName"
    }

    private fun getRandomString(length: Int): String {
        val allowedChars = ('A'..'Z') + ('a'..'z')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    private fun getRandomFutureUnixTime(): Long {
        val now = Instant.now().epochSecond
        // Define a reasonable future range, for example, up to 10 years from now
        val tenYearsInSeconds = 10 * 365 * 24 * 60 * 60L
        return Random.nextLong(now, now + tenYearsInSeconds)
    }

}