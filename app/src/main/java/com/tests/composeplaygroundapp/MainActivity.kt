package com.tests.composeplaygroundapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.tests.composeplaygroundapp.activities.ComposeActivityWithArgs
import com.tests.composeplaygroundapp.activities.ComposeNavigationSingleActivity
import com.tests.composeplaygroundapp.activities.HybridActivity
import com.tests.composeplaygroundapp.testtags.MainActivityTags
import com.tests.composeplaygroundapp.ui.theme.ComposePlaygroundAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposePlaygroundAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .padding(innerPadding)
                            .padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {

                        Text(
                            text = "Pick a test",
                            style = MaterialTheme.typography.displaySmall
                        )

                        Text(
                            text = "Test compose activity with parameters",
                            style = MaterialTheme.typography.titleMedium
                        )

                        var first by remember {
                            mutableStateOf("")
                        }
                        var second by remember {
                            mutableStateOf("")
                        }

                        TextField(
                            modifier = Modifier.testTag(MainActivityTags.FIRST_ARG_INP_TAG),
                            value = first,
                            onValueChange = {
                                first = it
                            },
                            placeholder = {
                                Text("type anything")
                            }
                        )

                        TextField(
                            modifier = Modifier.testTag(MainActivityTags.SECOND_ARG_INP_TAG),
                            value = second,
                            onValueChange = {
                                if (it.isDigitsOnly()) {
                                    second = it
                                }
                            },
                            placeholder = {
                                Text("type a number")
                            }
                        )

                        Button(
                            modifier = Modifier
                                .testTag(MainActivityTags.NEXT_ACTIVITY_1_BTN_TAG),
                            onClick = {
                                val intent = ComposeActivityWithArgs.getIntent(this@MainActivity, first, second.toInt())
                                this@MainActivity.startActivity(intent)
                            },
                            enabled = first.isNotEmpty() && second.isNotEmpty()
                        ) {
                            Text("Go")
                        }

                        HorizontalDivider()

                        Text(
                            text = "Test hybrid activity with nested compose fragment",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Button(
                            modifier = Modifier
                                .testTag(MainActivityTags.NEXT_ACTIVITY_2_BTN_TAG),
                            onClick = {
                                val intent = HybridActivity.getIntent(this@MainActivity)
                                this@MainActivity.startActivity(intent)
                            }
                        ) {
                            Text("Go")
                        }

                        HorizontalDivider()

                        Text(
                            text = "Test single activity with compose navigation",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Button(
                            modifier = Modifier
                                .testTag(MainActivityTags.NEXT_ACTIVITY_3_BTN_TAG),
                            onClick = {
                                val intent = ComposeNavigationSingleActivity.getIntent(this@MainActivity)
                                this@MainActivity.startActivity(intent)
                            }
                        ) {
                            Text("Go")
                        }
                    }
                }
            }
        }
    }
}