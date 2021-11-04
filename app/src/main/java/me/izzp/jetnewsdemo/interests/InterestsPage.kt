package me.izzp.jetnewsdemo.interests

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import me.izzp.jetnewsdemo.JetNewsViewModel
import me.izzp.jetnewsdemo.mtColors

private class IntrestsItem(
    val title: String,
    val content: @Composable () -> Unit,
)

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun InterestsPage(
    vm: JetNewsViewModel,
) {
    var currentIndex by remember { mutableStateOf(0) }
    val scrollStates = listOf(
        rememberScrollState(),
        rememberScrollState(),
        rememberScrollState(),
    )
    val items = remember {
        listOf(
            IntrestsItem("Topics") { Topics(vm, scrollStates[0]) },
            IntrestsItem("People") { People(vm, scrollStates[1]) },
            IntrestsItem("Publications") { Publications(vm, scrollStates[2]) }
        )
    }
    Column(
        Modifier.fillMaxWidth(),
    ) {
        TabRow(
            selectedTabIndex = currentIndex,
            backgroundColor = mtColors.surface,
            contentColor = mtColors.secondary,
        ) {
            items.forEachIndexed { index, item ->
                Tab(
                    selected = currentIndex == index,
                    text = { Text(item.title) },
                    onClick = { currentIndex = index },
                    selectedContentColor = mtColors.secondary,
                    unselectedContentColor = mtColors.onSurface,
                )
            }
        }
        Box(
            Modifier.fillMaxSize()
        ) {
            items.forEachIndexed { index, item ->
                this@Column.AnimatedVisibility(
                    visible = currentIndex == index,
                    enter = fadeIn(),
                    exit = fadeOut(),
                    content = { item.content() }
                )
            }
        }
    }
}