package com.tests.composeplaygroundapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tests.composeplaygroundapp.composables.navigation.RootNavigation
import com.tests.composeplaygroundapp.ui.theme.ComposePlaygroundAppTheme

class ComposeNavigationSingleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposePlaygroundAppTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .padding(innerPadding)
                    ) {
                        RootNavigation()
                    }
                }
            }
        }
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, ComposeNavigationSingleActivity::class.java)
    }
}