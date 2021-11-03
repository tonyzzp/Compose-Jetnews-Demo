package me.izzp.jetnewsdemo

import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import me.izzp.jetnewsdemo.article.ArticlePage
import me.izzp.jetnewsdemo.index.IndexPage
import me.izzp.jetnewsdemo.index.IndexPageType

@Composable
fun Gate(
    vm: JetNewsViewModel,
) {
    val navController = rememberNavController()
    val onNavigate: (route: String) -> Unit = remember {
        { route ->
            navController.navigate(route) {
                launchSingleTop = true
                restoreState = true
                popUpTo(navController.graph.startDestinationId) {
                    saveState = true
                }
            }
        }
    }
    val homeScrollState = rememberScrollState()
    NavHost(
        navController = navController,
        startDestination = "index/home",
    ) {
        composable("index/home") {
            IndexPage(
                vm = vm,
                type = IndexPageType.home,
                onNavigate = onNavigate,
                homeScrollState = homeScrollState,
            )
        }
        composable("index/interests") {
            IndexPage(
                vm = vm,
                type = IndexPageType.interests,
                onNavigate = onNavigate,
            )
        }
        composable(
            route = "article/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) {
            val id = it.arguments!!.getString("id")!!
            ArticlePage(
                vm = vm,
                postId = id,
                onBackClick = {
                    onNavigate("index/home")
                },
            )
        }
    }
}