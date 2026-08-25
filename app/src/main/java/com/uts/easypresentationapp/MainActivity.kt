package com.uts.easypresentationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Referencia a nuestra base de datos en la nube
        val database = Firebase.database.reference.child("presentation")

        setContent {
            var currentSlide by remember { mutableStateOf(1) }

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(
                    modifier = Modifier.fillMaxSize().padding(24.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Control Móvil - Diapositiva: $currentSlide", fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(32.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        Button(onClick = {
                            if (currentSlide > 1) {
                                currentSlide--
                                actualizarFirebase(database, currentSlide)
                            }
                        }) {
                            Text("Anterior")
                        }

                        Button(onClick = {
                            if (currentSlide < 5) {
                                currentSlide++
                                actualizarFirebase(database, currentSlide)
                            }
                        }) {
                            Text("Siguiente")
                        }
                    }
                }
            }
        }
    }

    private fun actualizarFirebase(database: com.google.firebase.database.DatabaseReference, slide: Int) {
        val contenido = when (slide) {
            1 -> "Introducción al Proyecto y Objetivos"
            2 -> "Arquitectura Modular en Jetpack Compose"
            3 -> "Intercambio de Datos en Tiempo Real"
            4 -> "Resultados y Demostración Práctica"
            else -> "Conclusiones y Preguntas"
        }

        val data = mapOf("currentSlide" to slide, "content" to contenido)
        database.setValue(data)
    }
}