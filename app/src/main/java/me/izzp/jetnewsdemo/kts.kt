package me.izzp.jetnewsdemo

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.text.buildAnnotatedString

val mtColors: Colors
    @Composable
    get() = MaterialTheme.colors

val mtTypography: Typography
    @Composable
    get() = MaterialTheme.typography

val mtShapes: Shapes
    @Composable
    get() = MaterialTheme.shapes

@Composable
fun ProvideContentAlpha(alpha: Float, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalContentAlpha provides alpha, content = content)
}

fun <T> SnapshotStateList<T>.addOrRemove(obj: T) {
    if (!remove(obj)) {
        add(obj)
    }
}

fun String.toAnnotatedString() = buildAnnotatedString {
    append(this@toAnnotatedString)
}