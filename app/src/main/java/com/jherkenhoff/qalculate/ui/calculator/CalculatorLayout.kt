package com.jherkenhoff.qalculate.ui.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CalculatorLayout(
    keypad: @Composable () -> Unit,
    inputSection: @Composable () -> Unit,
    history: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize().background(Color.Black)) {
        // Input Section - Liquid Glass Surface
        Box(modifier = Modifier.weight(1f).padding(8.dp)
            .background(Color.White.copy(alpha = 0.15f), RoundedCornerShape(16.dp))) {
            inputSection()
        }
        // Keypad Section - True Black Base
        Box(modifier = Modifier.weight(2f)) {
            keypad()
        }
    }
}
