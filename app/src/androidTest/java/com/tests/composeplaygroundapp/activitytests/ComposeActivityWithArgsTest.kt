package com.tests.composeplaygroundapp.activitytests

import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnyChild
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tests.composeplaygroundapp.ComposeUtils
import com.tests.composeplaygroundapp.activities.ComposeActivityWithArgs
import com.tests.composeplaygroundapp.testtags.SimpleColumnViewTags
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComposeActivityWithArgsTest {

    @get:Rule
    val composeTestRule = ComposeUtils.createAndroidIntentComposeRule<ComposeActivityWithArgs> {
        ComposeActivityWithArgs.Companion.getIntent(
            context = it,
            firstArg = "Why so serious punk?",
            secondArg = 123456
        )
    }

    @Test
    fun testUI() = runTest {

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag(SimpleColumnViewTags.ROOT)
            .assertIsDisplayed()

        composeTestRule.onAllNodesWithTag(SimpleColumnViewTags.COLUMN_ITEM)
            .assertCountEquals(2)

        composeTestRule.onAllNodesWithTag(SimpleColumnViewTags.ROOT)
            .assertAny(hasAnyChild(hasText("Why so serious punk?")))

        composeTestRule.onAllNodesWithTag(SimpleColumnViewTags.ROOT)
            .assertAny(hasAnyChild(hasText("123456")))
    }
}