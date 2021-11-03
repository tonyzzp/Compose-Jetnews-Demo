package me.izzp.jetnewsdemo.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import me.izzp.jetnewsdemo.ProvideContentAlpha
import me.izzp.jetnewsdemo.data.Post
import me.izzp.jetnewsdemo.mtShapes
import me.izzp.jetnewsdemo.mtTypography

@Composable
fun PostListItem(
    post: Post,
    onArticleClick: (id: String) -> Unit,
    modifier: Modifier = Modifier,
    tail: @Composable RowScope.() -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                onClick = { onArticleClick(post.id) }
            )
            .padding(12.dp)
    ) {
        Image(
            painterResource(post.imageThumbId),
            null,
            modifier = Modifier.size(56.dp).clip(mtShapes.medium)
        )
        Spacer(Modifier.width(12.dp))
        Column(
            modifier = Modifier.weight(1f),
        ) {
            Text(
                text = post.title,
                style = mtTypography.h6,
            )
            ProvideContentAlpha(ContentAlpha.medium) {
                Text(
                    text = post.metadata.author.name,
                    style = mtTypography.subtitle1,
                )
            }
        }
        tail()
    }
}