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

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun InterestsPage(
    vm: JetNewsViewModel,
) {
    Column(
        Modifier.fillMaxWidth(),
    ) {
        var index by remember { mutableStateOf(0) }
        TabRow(
            selectedTabIndex = index,
            backgroundColor = mtColors.surface,
            contentColor = mtColors.secondary,
        ) {
            Tab(
                selected = index == 0,
                text = { Text("Topics") },
                onClick = { index = 0 },
                selectedContentColor = mtColors.secondary,
                unselectedContentColor = mtColors.onSurface,
            )
            Tab(
                selected = index == 1,
                text = { Text("People") },
                onClick = { index = 1 },
                selectedContentColor = mtColors.secondary,
                unselectedContentColor = mtColors.onSurface,
            )
            Tab(
                selected = index == 2,
                text = { Text("Publications") },
                onClick = { index = 2 },
                selectedContentColor = mtColors.secondary,
                unselectedContentColor = mtColors.onSurface,
            )
        }
        Box(
            Modifier.fillMaxSize()
        ) {
            this@Column.AnimatedVisibility(
                visible = index == 0,
                enter = fadeIn(),
                exit = fadeOut(),
                content = {
                    Topics(
                        vm,
                        key("topics") {
                            rememberScrollState()
                        }
                    )
                },
            )
            this@Column.AnimatedVisibility(
                visible = index == 1,
                enter = fadeIn(),
                exit = fadeOut(),
                content = { People(vm, key("people") { rememberScrollState() }) },
            )
            this@Column.AnimatedVisibility(
                visible = index == 2,
                enter = fadeIn(),
                exit = fadeOut(),
                content = {
                    Publications(
                        vm,
                        key("publications") {
                            rememberScrollState()
                        }
                    )
                },
            )
        }
    }
}