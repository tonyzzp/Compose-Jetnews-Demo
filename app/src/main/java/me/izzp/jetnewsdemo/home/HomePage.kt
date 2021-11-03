package me.izzp.jetnewsdemo.home

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import me.izzp.jetnewsdemo.JetNewsViewModel
import me.izzp.jetnewsdemo.mtTypography


@Composable
fun HomePage(
    vm: JetNewsViewModel,
    scaffoldState: ScaffoldState,
    onArticleClick: (id: String) -> Unit,
    scrollState: ScrollState = rememberScrollState(),
) {
    val state = rememberSwipeRefreshState(false)
    state.isRefreshing = vm.isLoadingFeed
    if (vm.err) {
        LaunchedEffect(vm.err) {
            val result = scaffoldState.snackbarHostState.showSnackbar(
                message = "load fail",
                actionLabel = "retry",
                duration = SnackbarDuration.Short,
            )
            if (result == SnackbarResult.ActionPerformed) {
                vm.refresh()
            }
        }
    }
    SwipeRefresh(
        state = state,
        onRefresh = {
            vm.refresh()
        },
        modifier = Modifier.fillMaxSize(),
    ) {
        val ps = vm.postsFeed
        if (ps == null && vm.err) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize(),
            ) {
                TextButton(
                    onClick = {
                        vm.refresh()
                    }
                ) {
                    Text(
                        text = "加载失败，点击重试",
                        style = mtTypography.button,
                    )
                }
            }
        } else if (ps != null) {
            Surface {
                Column(
                    Modifier.verticalScroll(scrollState)
                ) {
                    TopicBox(post = ps.highlightedPost, onArticleClick = onArticleClick)
                    RecommendedBox(onArticleClick = onArticleClick, posts = ps.recommendedPosts)
                    PopularBox(onArticleClick = onArticleClick, posts = ps.popularPosts)
                    RecentBox(vm = vm, onArticleClick = onArticleClick, posts = ps.recentPosts)
                }
            }
        }
    }
}