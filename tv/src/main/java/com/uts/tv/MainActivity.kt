package com.uts.tv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Surface
import androidx.tv.material3.Text
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import androidx.compose.runtime.*

class MainActivity : ComponentActivity() {

    @OptIn(androidx.tv.material3.ExperimentalTvMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = Firebase.database.reference.child("presentation")

        setContent {
            var slideText by remember { mutableStateOf("Esperando al controlador móvil...") }
            var slideNumber by remember { mutableStateOf(1) }

            // Escuchar cambios en tiempo real desde Firebase
            DisposableEffect(Unit) {
                val listener = object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val s = snapshot.child("currentSlide").getValue(Long::class.java)?.toInt() ?: 1
                        val c = snapshot.child("content").getValue(String::class.java) ?: "Conectando..."
                        slideNumber = s
                        slideText = c
                    }
                    override fun onCancelled(error: DatabaseError) {}
                }
                database.addValueEventListener(listener)
                onDispose { database.removeEventListener(listener) }
            }

            Surface(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier.fillMaxSize().padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "TV: Diapositiva $slideNumber", fontSize = 48.sp)
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(text = slideText, fontSize = 28.sp)
                    }
                }
            }
        }
    }
}