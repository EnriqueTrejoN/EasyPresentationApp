package com.uts.tv
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.media3.ui.compose.state.PresentationState
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Surface

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Nota: Aquí no usamos el tema por defecto del móvil para evitar errores rápidos
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                // La TV solo recibe el estado.
                // En un proyecto completo, esto se lee de Firebase. Aquí lo ponemos fijo por la prisa.
                TvScreen(state = PresentationState(currentSlide = 1, content = "Esperando al controlador móvil..."))
            }
        }
    }
}