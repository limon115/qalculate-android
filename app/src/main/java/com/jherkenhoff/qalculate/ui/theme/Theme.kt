package com.jherkenhoff.qalculate.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jherkenhoff.qalculate.R

val StixMathFont = FontFamily(
    Font(R.font.stix_regular, FontWeight.Normal),
    Font(R.font.stix_bold, FontWeight.Bold)
)

private val LimonRebuildScheme = darkColorScheme(
    primary = ClassWizAccent,
    secondary = LiquidGlass,
    tertiary = ClassWizDarkGray,
    background = TrueBlack,
    surface = TrueBlack,
    surfaceVariant = ClassWizDarkGray,
    onPrimary = TrueBlack,
    onSecondary = SolidWhite,
    onTertiary = SolidWhite,
    onBackground = SolidWhite,
    onSurface = SolidWhite,
    onSurfaceVariant = SolidWhite
)

// Clamping all display sizes down to professional dimensions
private val PremiumMathTypography = Typography(
    displayLarge = TextStyle(fontFamily = StixMathFont, fontWeight = FontWeight.Normal, fontSize = 32.sp),
    displayMedium = TextStyle(fontFamily = StixMathFont, fontWeight = FontWeight.Normal, fontSize = 26.sp),
    displaySmall = TextStyle(fontFamily = StixMathFont, fontWeight = FontWeight.Normal, fontSize = 22.sp),
    headlineLarge = TextStyle(fontFamily = StixMathFont, fontWeight = FontWeight.Bold, fontSize = 22.sp),
    bodyLarge = TextStyle(fontFamily = StixMathFont, fontWeight = FontWeight.Normal, fontSize = 18.sp, fontFeatureSettings = "frac"),
    bodyMedium = TextStyle(fontFamily = StixMathFont, fontWeight = FontWeight.Normal, fontSize = 16.sp, fontFeatureSettings = "frac"),
    labelLarge = TextStyle(fontFamily = StixMathFont, fontWeight = FontWeight.Medium, fontSize = 14.sp)
)

@Composable
fun QalculateTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LimonRebuildScheme,
        typography = PremiumMathTypography,
        content = content
    )
}
