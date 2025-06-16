package com.tests.composeplaygroundapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.tests.composeplaygroundapp.testtags.FragmentWithComposeTags
import com.tests.composeplaygroundapp.ui.theme.ComposePlaygroundAppTheme

class FragmentWithCompose : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ComposePlaygroundAppTheme {

                    var displayTest by remember {
                        mutableStateOf("This is the fragment view")
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .padding()
                                .testTag(FragmentWithComposeTags.TEXT_TAG),
                            text = displayTest
                        )

                        Button(
                            modifier = Modifier.testTag(FragmentWithComposeTags.BUTTON_TAG),
                            onClick = {
                                displayTest = "clickityclick"
                            }
                        ) {
                            Text("clicker")
                        }
                    }
                }
            }
        }
    }
}