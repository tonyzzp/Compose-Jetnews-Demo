package me.izzp.jetnewsdemo.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import me.izzp.jetnewsdemo.JetNewsViewModel
import me.izzp.jetnewsdemo.data.Post


@Composable
fun RecentBox(
    vm: JetNewsViewModel,
    onArticleClick: (id: String) -> Unit,
    posts: List<Post>,
) {
    val favorites by vm.favorites.collectAsState()
    Column {
        Divider()
        posts.forEach { post ->
            PostListItem(
                post = post,
                onArticleClick = onArticleClick,
                tail = {
                    IconButton(
                        onClick = {
                            vm.toggleFavorite(post.id)
                        }
                    ) {
                        Icon(
                            if (favorites.contains(post.id)) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            null,
                        )
                    }
                }
            )
            Divider()
        }
    }
}