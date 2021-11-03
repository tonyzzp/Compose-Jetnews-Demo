package me.izzp.jetnewsdemo.article

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import me.izzp.jetnewsdemo.JetNewsViewModel
import me.izzp.jetnewsdemo.NotAvailableDialog
import me.izzp.jetnewsdemo.data.Post

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ArticlePage(
    vm: JetNewsViewModel,
    postId: String,
    onBackClick: () -> Unit,
) {
    val post by remember(postId) {
        vm.getPost(postId)
    }
    if (post == null) {
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    } else {
        Body(
            vm = vm,
            post = post!!,
            onBackClick = onBackClick,
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun Body(
    vm: JetNewsViewModel,
    post: Post,
    onBackClick: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            Box(Modifier.fillMaxWidth()) {
                BackHandler(
                    enabled = sheetState.isVisible
                ) {
                    coroutineScope.launch {
                        sheetState.hide()
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ShareSheet(post, sheetState)
                }
            }
        },
    ) {
        Box(Modifier.fillMaxSize()) {
            Surface {
                Content(
                    vm = vm,
                    onBackClick = onBackClick,
                    post = post,
                    sheetState = sheetState,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun Content(
    vm: JetNewsViewModel,
    onBackClick: () -> Unit,
    post: Post,
    sheetState: ModalBottomSheetState,
) {
    val coroutineScope = rememberCoroutineScope()
    var showNotAvailableDialog by remember { mutableStateOf(false) }
    val favorites by vm.favorites.collectAsState()
    Column {
        ArticleTopBar(onBackClick)
        ArticleContent(post = post, modifier = Modifier.weight(1f))
        ArticleBottomBar(
            like = favorites.contains(post.id),
            onThumbUpClick = {
                showNotAvailableDialog = true
            },
            onBookmarkClick = {
                vm.toggleFavorite(post.id)
            },
            onShareClick = {
                coroutineScope.launch { sheetState.show() }
            },
        )
    }
    if (showNotAvailableDialog) {
        NotAvailableDialog {
            showNotAvailableDialog = false
        }
    }
}