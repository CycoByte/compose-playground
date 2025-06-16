package com.tests.composeplaygroundapp.activitytests

import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasAnyChild
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.espresso.Espresso
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tests.composeplaygroundapp.MainActivity
import com.tests.composeplaygroundapp.testtags.MainActivityTags
import com.tests.composeplaygroundapp.testtags.SimpleColumnViewTags
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testLaunchedActivity() = runTest {
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag(MainActivityTags.NEXT_ACTIVITY_1_BTN_TAG)
            .assertIsDisplayed()
            .assertIsNotEnabled()

        composeTestRule.onNodeWithTag(MainActivityTags.FIRST_ARG_INP_TAG)
            .assertIsDisplayed()
            .performTextInput("this is a test")

        composeTestRule.onNodeWithTag(MainActivityTags.SECOND_ARG_INP_TAG)
            .assertIsDisplayed()
            .performTextInput("56789")

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag(MainActivityTags.NEXT_ACTIVITY_1_BTN_TAG)
            .assertIsDisplayed()
            .assertIsEnabled()
            .performClick()

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag(SimpleColumnViewTags.ROOT)
            .assertIsDisplayed()

        composeTestRule.onAllNodesWithTag(SimpleColumnViewTags.ROOT)
            .assertAny(hasAnyChild(hasText("this is a test")))

        composeTestRule.onAllNodesWithTag(SimpleColumnViewTags.ROOT)
            .assertAny(hasAnyChild(hasText("56789")))

        composeTestRule.waitForIdle()

        Espresso.pressBack()

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag(MainActivityTags.NEXT_ACTIVITY_1_BTN_TAG)
            .assertIsDisplayed()
    }
}