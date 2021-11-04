package me.izzp.jetnewsdemo.interests

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import me.izzp.jetnewsdemo.JetNewsViewModel
import me.izzp.jetnewsdemo.mtColors

private class IntrestsItem(
    val title: String,
    val content: @Composable () -> Unit,
)

@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
fun InterestsPage(
    vm: JetNewsViewModel,
) {
    val coroutineScope = rememberCoroutineScope()
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
    val pagerState = rememberPagerState()
    Column(
        Modifier.fillMaxWidth(),
    ) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = mtColors.surface,
            contentColor = mtColors.secondary,
            indicator = {
                TabRowDefaults.Indicator(Modifier.pagerTabIndicatorOffset(pagerState, it))
            }
        ) {
            items.forEachIndexed { index, item ->
                Tab(
                    selected = pagerState.currentPage == index,
                    text = { Text(item.title) },
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    selectedContentColor = mtColors.secondary,
                    unselectedContentColor = mtColors.onSurface,
                )
            }
        }
        HorizontalPager(
            count = 3,
            state = pagerState,
            verticalAlignment = Alignment.Top,
            key = { it }
        ) { page ->
            items[page].content()
        }
    }
}