package com.tests.composeplaygroundapp.activitytests

import android.view.InputDevice
import android.view.MotionEvent
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.CoordinatesProvider
import androidx.test.espresso.action.GeneralClickAction
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.Tap
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isClickable
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tests.composeplaygroundapp.MainActivity
import com.tests.composeplaygroundapp.R
import com.tests.composeplaygroundapp.testtags.FragmentWithComposeTags
import com.tests.composeplaygroundapp.testtags.MainActivityTags
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HybridActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun navigateThroughTest() = runTest {
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag(MainActivityTags.NEXT_ACTIVITY_2_BTN_TAG)
            .assertIsDisplayed()
            .assertIsEnabled()
            .performClick()

        composeTestRule.waitForIdle()

        onView(withId(R.id.main)).check(matches(isDisplayed()))
        onView(withId(R.id.txtTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.setFragmentBtn)).check(matches(isDisplayed()))
        onView(withId(R.id.fragmentContainer)).check(matches(not(isDisplayed())))

        """
            Reached dead end here - Espresso matchers correctly identify all views in XML layout
            but cannot perform click action on XML button to place fragment in container.
            Need to figure a way to click the button.
            
            Discovered that by adding a GeneralClick in the center of the screen i am able to perform
            the click action further down. Both are required to work, its as if the activity is not 
            focused to capture click.
        """

        onView(isRoot()).perform(
            GeneralClickAction(
                Tap.SINGLE,
                CoordinatesProvider { view ->
                    val screenPos = IntArray(2)
                    view.getLocationOnScreen(screenPos)
                    val screenWidth = view.width
                    val screenHeight = view.height
                    val x = screenPos[0] + screenWidth / 2f
                    val y = screenPos[1] + screenHeight / 2f
                    floatArrayOf(x, y)
                },
                Press.FINGER,
                InputDevice.SOURCE_UNKNOWN,
                MotionEvent.BUTTON_STYLUS_PRIMARY
            )
        )
        onView(withId(R.id.setFragmentBtn))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
            .check(matches(isEnabled()))
            .perform(click())

        // commented fail test
//        composeTestRule.onNodeWithText("set fragment")
//            .assertIsDisplayed()
//            .performClick()

        composeTestRule.waitForIdle()
        onView(withId(R.id.fragmentContainer)).check(matches(isDisplayed()))

        composeTestRule.onNodeWithTag(FragmentWithComposeTags.TEXT_TAG)
            .assertIsDisplayed()
            .assertTextEquals("This is the fragment view")

        composeTestRule.onNodeWithTag(FragmentWithComposeTags.BUTTON_TAG)
            .assertIsDisplayed()
            .assertHasClickAction()
            .performClick()

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag(FragmentWithComposeTags.TEXT_TAG)
            .assertIsDisplayed()
            .assertTextEquals("clickityclick")

        Espresso.pressBack()

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag(MainActivityTags.NEXT_ACTIVITY_2_BTN_TAG)
            .assertIsDisplayed()
    }
}