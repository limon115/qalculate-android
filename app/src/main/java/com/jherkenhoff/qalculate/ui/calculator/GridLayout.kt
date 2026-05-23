package com.jherkenhoff.qalculate.ui.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jherkenhoff.qalculate.model.KeyPositionSpec
import kotlin.math.roundToInt

// ... (Keep GridScope class as is)

@Composable
fun GridLayout(
    rows: Int,
    cols: Int,
    modifier: Modifier = Modifier,
    aspectRatio: Float = 1f,
    horizontalSpacing: Dp = 8.dp,
    verticalSpacing: Dp = 8.dp,
    content: GridScope.() -> Unit
) {
    val scope = GridScope().apply(content)
    val items = scope.items

    Layout(
        modifier = modifier.padding(16.dp),
        content = { items.forEach { it.content() } }
    ) { measurables, constraints ->
        val hSpace = horizontalSpacing.roundToPx()
        val vSpace = verticalSpacing.roundToPx()
        val totalWidth = constraints.maxWidth
        val cellWidth = (totalWidth - hSpace * (cols - 1)) / cols
        val cellHeight = (cellWidth * aspectRatio).roundToInt()

        val placeables = items.indices.map { i ->
            val width = cellWidth * items[i].positionSpec.colSpan + hSpace * (items[i].positionSpec.colSpan - 1)
            val height = cellHeight * items[i].positionSpec.rowSpan + vSpace * (items[i].positionSpec.rowSpan - 1)
            measurables[i].measure(Constraints.fixed(width, height))
        }

        val totalHeight = cellHeight * rows + vSpace * (rows - 1)
        layout(totalWidth, totalHeight) {
            items.indices.map { i ->
                val x = items[i].positionSpec.col * (cellWidth + hSpace)
                val y = items[i].positionSpec.row * (cellHeight + vSpace)
                placeables[i].placeRelative(x, y)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun Default() {
    val keyStyle = Modifier
        .padding(4.dp)
        .clip(CircleShape)
        .background(Color(0xFF333333)) // Premium ClassWiz Dark Gray

    GridLayout(3, 3, aspectRatio = 1f) {
        item(0, 0) { Box(keyStyle) }
        item(0, 1) { Box(keyStyle) }
        item(0, 2) { Box(keyStyle) }
    }
}
