package me.izzp.jetnewsdemo.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import me.izzp.jetnewsdemo.data.Post

@Composable
fun RecommendedBox(
    onArticleClick: (id: String) -> Unit,
    posts: List<Post>,
) {
    Column {
        Divider()
        posts.forEach { post ->
            PostListItem(
                post = post,
                onArticleClick = onArticleClick,
                tail = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(Icons.Default.Close, null)
                    }
                }
            )
            Divider()
        }
    }
}