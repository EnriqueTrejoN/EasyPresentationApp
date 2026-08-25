package com.uts.tv

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Text
import com.uts.shared.PresentationState

@Composable
fun TvScreen(state: PresentationState) {
    Box(modifier = Modifier.fillMaxSize().padding(32.dp), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "TV: Diapositiva ${state.currentSlide}", fontSize = 48.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = state.content, fontSize = 24.sp)
        }
    }
}