package com.uts.shared

data class PresentationState(
    val currentSlide: Int = 1,
    val totalSlides: Int = 5,
    val content: String = "Contenido multimedia cargando..."
)