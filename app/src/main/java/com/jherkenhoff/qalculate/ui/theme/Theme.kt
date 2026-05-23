package com.jherkenhoff.qalculate.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Enforce True Black & Liquid Glass universally
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

// Inject Professional Serif Typography
private val SerifTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    )
)

@Composable
fun QalculateTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is completely disabled to protect the aesthetic
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LimonRebuildScheme,
        typography = SerifTypography,
        content = content
    )
}
