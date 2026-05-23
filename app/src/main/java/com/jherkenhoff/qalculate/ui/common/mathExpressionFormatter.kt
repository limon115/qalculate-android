package com.jherkenhoff.qalculate.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.unit.em

// 1. The Limon Rebuild Diagnostic Display
@Composable
fun MathDisplay(text: String, modifier: Modifier = Modifier) {
    // A slightly more forgiving regex just in case there are hidden spaces
    val fracRegex = Regex("<frac>\\s*<num>(.*?)</num>\\s*<den>(.*?)</den>\\s*</frac>")
    
    Column(
        modifier = modifier.wrapContentSize(),
        horizontalAlignment = Alignment.End
    ) {
        // DIAGNOSTIC TAPE: This will print the raw backend string in red
        Text(
            text = "RAW: $text",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.labelLarge
        )
        
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.wrapContentSize()
        ) {
            var lastIndex = 0
            val matches = fracRegex.findAll(text)
            
            for (match in matches) {
                val preText = text.substring(lastIndex, match.range.first)
                if (preText.isNotEmpty()) {
                    Text(text = cleanMathTags(preText), style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onBackground)
                }
                
                val num = cleanMathTags(match.groupValues[1])
                val den = cleanMathTags(match.groupValues[2])
                TrueFraction(numerator = num, denominator = den)
                
                lastIndex = match.range.last + 1
            }
            
            if (lastIndex < text.length) {
                val postText = text.substring(lastIndex)
                Text(text = cleanMathTags(postText), style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onBackground)
            }
        }
    }
}

fun cleanMathTags(input: String): String {
    return input.replace(Regex("<.*?>"), "")
        .replace("&nbsp;", "")
        .replace("&lt;", "<")
        .replace("&gt;", ">")
        .replace("&amp;", "&")
}

// 2. Original fallback formatter
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
                "<frac>" -> append("(") 
                "<num>" -> pushStyle(SpanStyle(baselineShift = BaselineShift.Superscript, fontSize = 0.8.em))
                "</num>" -> pop()
                "<den>" -> pushStyle(SpanStyle(baselineShift = BaselineShift.Subscript, fontSize = 0.8.em))
                "</den>" -> pop()
                "</frac>" -> append(")")
                "&nbsp;" -> append("")
                "&lt;" -> append("<")
                "&gt;" -> append(">")
                "&amp;" -> append("&")
                else -> append(token.value)
            }
        }
    }
}
