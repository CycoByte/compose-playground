package com.tests.composeplaygroundapp.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.tests.composeplaygroundapp.testtags.SimpleColumnViewTags

@Composable
fun SimpleColumnView(
    modifier: Modifier,
    items: List<String>
) {

    Column(
        modifier = Modifier
            .testTag(SimpleColumnViewTags.ROOT)
            .then(modifier),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items.forEach { text ->
            Text(
                modifier = Modifier
                    .testTag(SimpleColumnViewTags.COLUMN_ITEM),
                text = text,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}