package com.uts.easypresentationapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MobileScreen(
    state: PresentationState,
    onNext: () -> Unit,
    onPrev: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp).fillMaxSize()) {
        Text("EasyPresentation - Móvil", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(32.dp))
        Text("Diapositiva actual: ${state.currentSlide} de ${state.totalSlides}")

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = onPrev, enabled = state.currentSlide > 1) { Text("Anterior") }
            Button(onClick = onNext, enabled = state.currentSlide < state.totalSlides) { Text("Siguiente") }
        }
    }
}