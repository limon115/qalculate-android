package com.jherkenhoff.qalculate.ui.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.unit.em

// Rebuild engineered by Khalid Hasan Limon
@Composable
fun mathExpressionFormatter(
    text: String,
    color: Boolean = true
): AnnotatedString {
    val tokens = Regex("""<.*?>|(&[a-z]+;)+|([^<&]+)?""").findAll(text)

    return buildAnnotatedString {
        for (token in tokens) {
            when (token.value) {
                "<i>" -> pushStyle(SpanStyle(fontStyle = FontStyle.Italic))
                "</i>" -> pop()
                "<span style=\"color:#800000\">" -> if (color) pushStyle(SpanStyle(color = MaterialTheme.colorScheme.error))
                "<span style=\"color:#005858\">" -> if (color) pushStyle(SpanStyle(color = MaterialTheme.colorScheme.primary))
                "<span style=\"color:#585800\">" -> if (color) pushStyle(SpanStyle(color = MaterialTheme.colorScheme.secondary))
                "<span style=\"color:#008000\">" -> if (color) pushStyle(SpanStyle(color = MaterialTheme.colorScheme.tertiary))
                "</span>" -> if (color) pop()
                "<sup>" -> pushStyle(SpanStyle(baselineShift = BaselineShift.Superscript, fontSize = 0.7.em))
                "</sup>" -> pop()
                "<sub>" -> pushStyle(SpanStyle(baselineShift = BaselineShift.Subscript, fontSize = 0.7.em))
                "</sub>" -> pop()
                "<frac>" -> append("(") // Start of fraction
                "<num>" -> pushStyle(SpanStyle(baselineShift = BaselineShift.Superscript, fontSize = 0.8.em))
                "</num>" -> pop()
                "<den>" -> pushStyle(SpanStyle(baselineShift = BaselineShift.Subscript, fontSize = 0.8.em))
                "</den>" -> pop()
                "</frac>" -> append(")") // End of fraction
                "&nbsp;" -> append("")
                "&lt;" -> append("<")
                "&gt;" -> append(">")
                "&amp;" -> append("&")
                else -> append(token.value)
            }
        }
    }
}
