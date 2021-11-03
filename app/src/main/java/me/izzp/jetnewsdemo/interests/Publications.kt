package me.izzp.jetnewsdemo.interests

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.izzp.jetnewsdemo.JetNewsViewModel


private val pubData = listOf(
    "Kotlin Vibe",
    "Compose Bark",
    "Android dev",
    "Jetpack Art",
    "Flying dot",
    "Public Area"
)

@Composable
fun Publications(
    vm: JetNewsViewModel,
    scrollState: ScrollState = rememberScrollState(),
) {
    Region(
        vm = vm,
        type = "Publications",
        data = pubData,
        modifier = Modifier.verticalScroll(scrollState)
    )
}