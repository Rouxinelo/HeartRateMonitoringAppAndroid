package components.others

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LineChart(
    measurements: List<Int>,
    maximumChartValues: Int = 5,
    maxMeasurement: Int = 150,
    horizontalMargin: Float = 40f
) {
    val chartValues = measurements.takeLast(maximumChartValues)

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(16.dp)
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color.White, Color.LightGray),
                    startY = 0f,
                    endY = 1000f
                )
            )
            .clip(RoundedCornerShape(10.dp))
            .border(2.dp, Color.Black, RoundedCornerShape(10.dp))
    ) {
        val width = constraints.maxWidth.toFloat()
        val height = constraints.maxHeight.toFloat()

        val points = if (chartValues.isNotEmpty()) {
            val effectiveWidth = width - (2 * horizontalMargin)
            val spacing = effectiveWidth / (maximumChartValues - 1).toFloat()

            chartValues.mapIndexed { index, value ->
                val x = horizontalMargin + (spacing * index)
                val y = height * (1 - value.toFloat() / maxMeasurement)
                x to y
            }
        } else emptyList()

        Canvas(modifier = Modifier.fillMaxSize()) {
            if (points.isNotEmpty()) {
                drawPath(
                    path = Path().apply {
                        points.forEachIndexed { index, point ->
                            if (index == 0) moveTo(point.first, point.second)
                            else lineTo(point.first, point.second)
                        }
                    },
                    color = Color.Red,
                    style = Stroke(width = 2.dp.toPx())
                )

                points.forEach { point ->
                    val (x, y) = point
                    drawCircle(
                        color = Color.Red,
                        radius = 5.dp.toPx(),
                        center = Offset(x, y)
                    )
                }
            }
        }

        points.forEachIndexed { index, (x, y) ->
            val xDp = with(LocalDensity.current) { x.toDp() }
            val yDp = with(LocalDensity.current) { y.toDp() }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(xDp - 10.dp, yDp - 25.dp)
            ) {
                Text(
                    text = chartValues[index].toString(),
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun preview() {
    LineChart(
        measurements = listOf(30, 120, 90, 120, 75),
        maximumChartValues = 5,
        maxMeasurement = 200
    )
}