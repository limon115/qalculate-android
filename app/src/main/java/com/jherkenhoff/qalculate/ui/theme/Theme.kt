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

// Load the premium STIX Two font locally
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

// Inject Professional Math Typography
private val PremiumMathTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = StixMathFont,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp, // Bumped size for the main input readability
        fontFeatureSettings = "frac" // Forces OpenType mathematical fractions
    ),
    bodyMedium = TextStyle(
        fontFamily = StixMathFont,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        fontFeatureSettings = "frac"
    ),
    headlineLarge = TextStyle(
        fontFamily = StixMathFont,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp
    ),
    labelLarge = TextStyle(
        fontFamily = StixMathFont,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    )
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
