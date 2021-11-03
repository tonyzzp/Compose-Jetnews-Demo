package me.izzp.jetnewsdemo.article

import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import me.izzp.jetnewsdemo.R
import me.izzp.jetnewsdemo.mtColors

@Composable
fun ArticleBottomBar(
    like: Boolean,
    onThumbUpClick: () -> Unit,
    onBookmarkClick: () -> Unit,
    onShareClick: () -> Unit,
) {
    BottomAppBar(
        backgroundColor = mtColors.surface,
        elevation = 1.dp,
    ) {
        IconButton(
            onClick = onThumbUpClick,
        ) {
            Icon(Icons.Outlined.ThumbUp, null)
        }
        IconButton(
            onClick = onBookmarkClick,
        ) {
            if (like) {
                Icon(Icons.Default.Bookmark, null)
            } else {
                Icon(Icons.Default.BookmarkBorder, null)
            }
        }
        IconButton(
            onClick = onShareClick,
        ) {
            Icon(Icons.Outlined.Share, null)
        }
        Spacer(Modifier.weight(1f))
        IconButton(
            onClick = {}
        ) {
            Icon(painterResource(R.drawable.ic_text_settings), null)
        }
    }
}
