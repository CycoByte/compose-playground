package com.tests.composeplaygroundapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tests.composeplaygroundapp.composables.SimpleColumnView
import com.tests.composeplaygroundapp.ui.theme.ComposePlaygroundAppTheme

class ComposeActivityWithArgs : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val firstArg = intent.getStringExtra(FIRST_ARG)
        val secondArg = intent.getIntExtra(SECOND_ARG, -1)

        setContent {

            val theArgs = remember {
                listOf(
                    firstArg.toString(),
                    "$secondArg"
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
    }

    companion object {

        private const val FIRST_ARG = "first-arg"
        private const val SECOND_ARG = "second-arg"

        fun getIntent(context: Context, firstArg: String, secondArg: Int): Intent {
            return Intent(context, ComposeActivityWithArgs::class.java).apply {
                putExtra(FIRST_ARG, firstArg)
                putExtra(SECOND_ARG, secondArg)
            }
        }
    }
}