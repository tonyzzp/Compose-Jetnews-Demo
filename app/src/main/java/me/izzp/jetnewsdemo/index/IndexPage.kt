package me.izzp.jetnewsdemo.index

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import me.izzp.jetnewsdemo.JetNewsViewModel
import me.izzp.jetnewsdemo.home.HomePage
import me.izzp.jetnewsdemo.home.IndexTopBar
import me.izzp.jetnewsdemo.interests.InterestsPage

enum class IndexPageType {
    home,
    interests,
}

@Composable
fun IndexPage(
    vm: JetNewsViewModel,
    type: IndexPageType,
    onNavigate: (route: String) -> Unit,
    homeScrollState: ScrollState = rememberScrollState(),
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            Drawer(
                type,
                onNavigate = {
                    onNavigate(it)
                    coroutineScope.launch {
                        scaffoldState.drawerState.close()
                    }
                },
            )
        },
        topBar = {
            IndexTopBar(
                onIconClick = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        content = {
            when (type) {
                IndexPageType.home -> {
                    HomePage(
                        vm = vm,
                        scaffoldState = scaffoldState,
                        onArticleClick = {
                            onNavigate("article/$it")
                        },
                        scrollState = homeScrollState,
                    )
                }
                IndexPageType.interests -> {
                    InterestsPage(vm)
                }
            }
        }
    )
}