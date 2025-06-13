package com.tests.composeplaygroundapp.composabletests

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnyChild
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tests.composeplaygroundapp.composables.SimpleColumnView
import com.tests.composeplaygroundapp.testtags.SimpleColumnViewTags
import com.tests.composeplaygroundapp.ui.theme.ComposePlaygroundAppTheme
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SimpleColumnViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testViewSetsValues() = runTest {

        composeTestRule.setContent {
            val theArgs = remember {
                listOf(
                    "whazzzuuup",
                    "123456"
                )
            }

            ComposePlaygroundAppTheme {
                Scaffold {
                    SimpleColumnView(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(it)
                            .padding(24.dp),
                        items = theArgs
                    )
                }
            }
        }

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag(SimpleColumnViewTags.ROOT)
            .assertIsDisplayed()

        composeTestRule.onAllNodesWithTag(SimpleColumnViewTags.COLUMN_ITEM)
            .assertCountEquals(2)

        composeTestRule.onAllNodesWithTag(SimpleColumnViewTags.ROOT)
            .assertAny(hasAnyChild(hasText("whazzzuuup")))

        composeTestRule.onAllNodesWithTag(SimpleColumnViewTags.ROOT)
            .assertAny(hasAnyChild(hasText("123456")))
    }
}