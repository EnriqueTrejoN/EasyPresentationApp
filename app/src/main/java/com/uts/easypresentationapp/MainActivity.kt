package com.uts.easypresentationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uts.easypresentationapp.ui.theme.EasyPresentationAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EasyPresentationAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Este es el estado que comparten ambas pantallas simulando la red
                    var state by remember { mutableStateOf(PresentationState(content = "Contenido de la diapositiva 1")) }

                    Column(modifier = Modifier.fillMaxSize()) {
                        // MITAD SUPERIOR: El Visor de la TV
                        Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
                            TvScreen(state = state)
                        }

                        HorizontalDivider(thickness = 4.dp)

                        // MITAD INFERIOR: El Controlador Móvil
                        Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
                            MobileScreen(
                                state = state,
                                onNext = {
                                    if (state.currentSlide < state.totalSlides) {
                                        val nextSlide = state.currentSlide + 1
                                        state = state.copy(currentSlide = nextSlide, content = "Contenido de la diapositiva $nextSlide")
                                    }
                                },
                                onPrev = {
                                    if (state.currentSlide > 1) {
                                        val prevSlide = state.currentSlide - 1
                                        state = state.copy(currentSlide = prevSlide, content = "Contenido de la diapositiva $prevSlide")
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}