package me.izzp.jetnewsdemo.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import me.izzp.jetnewsdemo.ProvideContentAlpha
import me.izzp.jetnewsdemo.data.Post
import me.izzp.jetnewsdemo.mtTypography

@Composable
fun PopularBox(
    onArticleClick: (id: String) -> Unit,
    posts: List<Post>,
) {
    Column(
        modifier = Modifier.padding(0.dp, 6.dp)
    ) {
        Text(
            text = "Popular on Jetnews",
            style = mtTypography.h6,
            modifier = Modifier.padding(12.dp, 0.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(0.dp, 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Spacer(Modifier.width(12.dp))
            posts.forEach { post ->
                PostItem(post, onArticleClick)
            }
            Spacer(Modifier.width(12.dp))
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun PostItem(
    post: Post,
    onArticleClick: (id: String) -> Unit,
) {
    Card(
        onClick = { onArticleClick(post.id) },
        modifier = Modifier.width(250.dp).height(200.dp),
    ) {
        Column {
            Image(
                painterResource(post.imageId),
                null,
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = post.title,
                style = mtTypography.h6,
                modifier = Modifier.padding(12.dp, 0.dp)
            )
            ProvideContentAlpha(ContentAlpha.medium) {
                Text(
                    text = post.metadata.author.name,
                    style = mtTypography.subtitle1,
                    modifier = Modifier.padding(12.dp, 0.dp)
                )
                Text(
                    text = post.metadata.date,
                    style = mtTypography.subtitle2,
                    modifier = Modifier.padding(12.dp, 0.dp)
                )
            }
        }
    }
}