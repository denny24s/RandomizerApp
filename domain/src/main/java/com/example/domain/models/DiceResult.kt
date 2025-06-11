package com.example.domain.models

/**
 * Повертаємо лише список граней, тому робимо простий data-class.
 */
data class DiceResult(val faces: List<Int>)
