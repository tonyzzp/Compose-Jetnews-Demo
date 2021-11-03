package me.izzp.jetnewsdemo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.izzp.jetnewsdemo.mtTypography

private val LightColorPalette = lightColors(
    surface = Color.White,
    onSurface = Color.Black,
    background = Color.White,
    onBackground = Color.Black,
    primary = Color(30, 110, 230),
    primaryVariant = Color(30, 110, 230),
    onPrimary = Color.White,
    secondary = Color(220, 10, 60),
    secondaryVariant = Color(220, 10, 60),
    onSecondary = Color.White,
)

private val DarkColorPalette = darkColors(
    primary = Color(30, 110, 230),
    primaryVariant = Color(30, 110, 230),
    secondary = Color(220, 10, 60),
    secondaryVariant = Color(220, 10, 60),
    surface = Color.Black,
    onSurface = Color.White,
)

val Typography = Typography(
    h4 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 30.sp,
        letterSpacing = 0.25.sp
    ),
    h5 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 26.sp,
        letterSpacing = 0.25.sp
    ),
    subtitle1 = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    ),
    subtitle2 = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Light
    ),
)

val Typography.body: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 20.sp,
        letterSpacing = 0.3.sp,
        lineHeight = 25.sp,
    )


val Typography.title: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 35.sp,
        letterSpacing = 0.3.sp,
    )

val Typography.subtitle: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 18.sp,
        letterSpacing = 0.3.sp,
        fontWeight = FontWeight.Light,
    )

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

@Composable
fun JetNewsDemoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

@Composable
@Preview
private fun ThemePreview() {
    JetNewsDemoTheme {
        Column {
            Text(
                text = "title ".repeat(5),
                style = mtTypography.title,
            )
            Text(
                text = "subtitle ".repeat(5),
                style = mtTypography.subtitle,
            )
            Text(
                text = "subtitle1 ".repeat(5),
                style = mtTypography.subtitle1,
            )
            Text(
                text = "subtitle2 ".repeat(5),
                style = mtTypography.subtitle2,
            )
            Text(
                text = "body ".repeat(20),
                style = mtTypography.body,
            )
            Text(
                text = "head ".repeat(3),
                style = mtTypography.h4,
            )
        }
    }
}