package me.izzp.jetnewsdemo.interests

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.izzp.jetnewsdemo.JetNewsViewModel

private val peopleData = listOf(
    "Toral", "Uvarek", "Kris", "Grala", "Vennonn", "Tava", "Kemika", "Dodola"
)

@Composable
fun People(
    vm: JetNewsViewModel,
    scrollState: ScrollState = rememberScrollState(),
) {
    Region(
        vm = vm,
        data = peopleData,
        type = "People",
        modifier = Modifier.verticalScroll(scrollState)
    )
}