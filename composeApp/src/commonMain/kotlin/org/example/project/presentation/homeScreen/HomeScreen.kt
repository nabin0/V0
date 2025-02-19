package org.example.project.presentation.homeScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import org.koin.compose.getKoin

class HomeScreen() : Screen {

    @Composable
    override fun Content() {
        val viewModel: HomeScreenViewModel = getKoin().get()
//        val videoViewModel: VideoViewModel = getKoin().get()
        var showContent by remember { mutableStateOf(false) }

        Scaffold(topBar = {
            Text("V0 Codebase")
        }) {
            Box(modifier = Modifier) {

                LazyColumn(
                    modifier = Modifier.fillMaxSize(), state = rememberLazyListState()
                ) {
                    item {
                        Text("inside lazy column")
                    }

                }
            }
        }


    }
}