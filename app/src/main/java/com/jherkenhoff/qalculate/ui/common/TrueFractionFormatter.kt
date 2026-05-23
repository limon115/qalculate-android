package com.jherkenhoff.qalculate.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember

// The Limon Rebuild 2D Fraction Engine
@Composable
fun TrueFraction(
    numerator: String,
    denominator: String,
    color: Color = MaterialTheme.colorScheme.onBackground
) {
    val density = LocalDensity.current
    val numWidth = remember { mutableIntStateOf(0) }
    val denWidth = remember { mutableIntStateOf(0) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 4.dp)
    ) {
        // Numerator
        Text(
            text = numerator,
            color = color,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.onSizeChanged { numWidth.intValue = it.width }
        )
        
        // Fraction Bar (Dynamically sizes to the widest element)
        Box(
            modifier = Modifier
                .height(2.dp)
                .background(color)
                .width(with(density) { maxOf(numWidth.intValue, denWidth.intValue).toDp() } + 4.dp)
                .padding(vertical = 2.dp)
        )
        
        // Denominator
        Text(
            text = denominator,
            color = color,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.onSizeChanged { denWidth.intValue = it.width }
        )
    }
}
