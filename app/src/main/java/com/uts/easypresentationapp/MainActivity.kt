package com.uts.easypresentationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.uts.easypresentationapp.ui.theme.EasyPresentationAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EasyPresentationAppTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

                    // Estado simulado temporal
                    var state by remember { mutableStateOf(PresentationState()) }

                    MobileScreen(
                        state = state,
                        onNext = {
                            if (state.currentSlide < state.totalSlides) {
                                state = state.copy(currentSlide = state.currentSlide + 1)
                            }
                        },
                        onPrev = {
                            if (state.currentSlide > 1) {
                                state = state.copy(currentSlide = state.currentSlide - 1)
                            }
                        }
                    )
                }
            }
        }
    }
}