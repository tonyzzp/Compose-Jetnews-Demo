package me.izzp.jetnewsdemo.interests

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.izzp.jetnewsdemo.JetNewsViewModel
import me.izzp.jetnewsdemo.mtTypography

private val topicsData = mapOf(
    "Android" to listOf(
        "Jetpack Compose",
        "Kotlin",
        "Jetpack"
    ),
    "Programming" to listOf(
        "Kotlin",
        "Java",
        "C++",
        "Go"
    ),
    "Technology" to listOf(
        "Pixel",
        "Google",
        "Baidu",
        "Facebook",
    )
)

@Composable
fun Topics(
    vm: JetNewsViewModel,
    scrollState: ScrollState = rememberScrollState()
) {
    Column(
        Modifier.verticalScroll(scrollState)
    ) {
        topicsData.forEach {
            Text(
                text = it.key,
                style = mtTypography.h6,
                modifier = Modifier.padding(8.dp)
            )
            Region(
                vm = vm,
                type = "Topics-${it.key}",
                data = it.value
            )
        }
    }
}
