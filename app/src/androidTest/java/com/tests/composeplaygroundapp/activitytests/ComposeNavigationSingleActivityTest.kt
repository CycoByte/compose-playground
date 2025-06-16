package com.tests.composeplaygroundapp.activitytests

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.espresso.Espresso
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tests.composeplaygroundapp.MainActivity
import com.tests.composeplaygroundapp.testtags.DestinationPlaceholderTags
import com.tests.composeplaygroundapp.testtags.MainActivityTags
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComposeNavigationSingleActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testNavigationFlow() {
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag(MainActivityTags.NEXT_ACTIVITY_3_BTN_TAG)
            .assertIsDisplayed()
            .assertIsEnabled()
            .performClick()

        composeTestRule.waitForIdle()

        assertNavigationPlaceholderDataCorrect("Navigation landing page", navigateNext = true)
        assertNavigationPlaceholderDataCorrect("Destination A", navigateNext = true)
        assertNavigationPlaceholderDataCorrect("Destination B", navigateNext = true)
        assertNavigationPlaceholderDataCorrect("Nested navigation landing", navigateNext = true)
        assertNavigationPlaceholderDataCorrect("Another nested navigation destination", navigateNext = false)

        Espresso.pressBack()
        assertNavigationPlaceholderDataCorrect("Nested navigation landing", navigateNext = false)
        Espresso.pressBack()
        assertNavigationPlaceholderDataCorrect("Destination B", navigateNext = false)
        Espresso.pressBack()
        assertNavigationPlaceholderDataCorrect("Destination A", navigateNext = false)
        Espresso.pressBack()
        assertNavigationPlaceholderDataCorrect("Navigation landing page", navigateNext = false)
        Espresso.pressBack()
        composeTestRule.waitForIdle() // back to main activity
        composeTestRule.onNodeWithTag(MainActivityTags.NEXT_ACTIVITY_3_BTN_TAG)
            .assertIsDisplayed()
    }

    private fun assertNavigationPlaceholderDataCorrect(
        text: String,
        navigateNext: Boolean
    ) {
        composeTestRule.onNodeWithTag(DestinationPlaceholderTags.TEXT_TAG)
            .assertIsDisplayed()
            .assertTextEquals(text)

        composeTestRule.onNodeWithTag(DestinationPlaceholderTags.BUTTON_TAG)
            .assertIsDisplayed()
            .assertTextEquals("Navigate")

        if (navigateNext) {
            composeTestRule.onNodeWithTag(DestinationPlaceholderTags.BUTTON_TAG)
                .assertHasClickAction()
                .performClick()
        }
        composeTestRule.waitForIdle()
    }
}