package me.izzp.jetnewsdemo.article

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import me.izzp.jetnewsdemo.data.MarkupType
import me.izzp.jetnewsdemo.data.Paragraph
import me.izzp.jetnewsdemo.data.ParagraphType
import me.izzp.jetnewsdemo.mtColors
import me.izzp.jetnewsdemo.mtShapes
import me.izzp.jetnewsdemo.mtTypography
import me.izzp.jetnewsdemo.ui.theme.body


private val StyleCode = SpanStyle(
    background = Color.LightGray.copy(0.5f)
)

private val StyleBold = SpanStyle(
    fontWeight = FontWeight.Bold
)

private val StyleItalic = SpanStyle(
    fontStyle = FontStyle.Italic
)

private val StyleUnderline = SpanStyle(
    textDecoration = TextDecoration.Underline
)

@Composable
private fun ClickableText(
    text: AnnotatedString,
    style: TextStyle,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val style = if (style.color == Color.Unspecified) {
        style.copy(mtColors.onSurface)
    } else {
        style
    }
    ClickableText(
        text = text,
        modifier = modifier,
        style = style,
    ) {
        text.getStringAnnotations("href", it, it + 1).firstOrNull()?.also { range ->
            val url = range.item
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }
}

@Composable
fun ParagraphBody(p: Paragraph) {
    when (p.type) {
        ParagraphType.Text -> {
            PText(p)
        }
        ParagraphType.CodeBlock -> {
            PCode(p)
        }
        ParagraphType.Header -> {
            PHead(p)
        }
        ParagraphType.Quote -> {
            PText(p)
        }
        ParagraphType.Subhead -> {
            PSubhead(p)
        }
        ParagraphType.Bullet -> {
            PBullet(p)
        }
    }
}

private fun AnnotatedString.Builder.applyMarkups(p: Paragraph) {
    p.markups.forEach { markup ->
        when (markup.type) {
            MarkupType.Code -> {
                addStyle(StyleCode, markup.start, markup.end)
            }
            MarkupType.Bold -> {
                addStyle(StyleBold, markup.start, markup.end)
            }
            MarkupType.Italic -> {
                addStyle(StyleItalic, markup.start, markup.end)
            }
            MarkupType.Link -> {
                addStyle(StyleUnderline, markup.start, markup.end)
                addStringAnnotation("href", markup.href!!, markup.start, markup.end)
            }
        }
    }
}

@Composable
fun PText(p: Paragraph) {
    val txt = buildAnnotatedString {
        append(p.text)
        applyMarkups(p)
    }
    ClickableText(
        text = txt,
        style = mtTypography.body,
        modifier = Modifier.padding(top = 20.dp)
    )
}

@Composable
fun PCode(p: Paragraph) {
    val txt = buildAnnotatedString {
        append(p.text)
        applyMarkups(p)
    }
    ClickableText(
        text = txt,
        style = mtTypography.body,
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth()
            .background(Color.LightGray.copy(0.5f), mtShapes.medium)
            .padding(12.dp, 20.dp)
    )
}

@Composable
fun PHead(p: Paragraph) {
    Text(
        text = p.text,
        style = mtTypography.h4,
        modifier = Modifier.padding(top = 20.dp)
    )
}


@Composable
fun PSubhead(p: Paragraph) {
    Text(
        text = p.text,
        style = mtTypography.h5,
        modifier = Modifier.padding(top = 20.dp)
    )
}

@Composable
fun PBullet(p: Paragraph) {
    val txt = buildAnnotatedString {
        append("● ")
        append(buildAnnotatedString {
            append(p.text)
            applyMarkups(p)
        })
    }
    ClickableText(
        text = txt,
        style = mtTypography.body,
        modifier = Modifier.padding(top = 10.dp)
    )
}