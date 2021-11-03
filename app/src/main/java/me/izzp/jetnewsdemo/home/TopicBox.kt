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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import me.izzp.jetnewsdemo.ProvideContentAlpha
import me.izzp.jetnewsdemo.data.Post
import me.izzp.jetnewsdemo.mtShapes
import me.izzp.jetnewsdemo.mtTypography

@Composable
fun TopicBox(
    post: Post,
    onArticleClick: (id: String) -> Unit,
) {
    Column(
        modifier = Modifier.padding(0.dp, 6.dp),
    ) {
        Text(
            text = "Top stories for you",
            style = mtTypography.h6,
            modifier = Modifier.padding(start = 12.dp)
        )
        Spacer(Modifier.height(12.dp))
        Column(
            modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                onClick = { onArticleClick(post.id) }
            ).padding(12.dp, 6.dp)
        ) {
            Image(
                painterResource(post.imageId),
                null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth().clip(mtShapes.medium)
            )
            Text(
                text = post.title,
                style = mtTypography.h6,
            )
            Column {
                Text(
                    text = post.metadata.author.name,
                    style = mtTypography.subtitle1
                )
                ProvideContentAlpha(ContentAlpha.medium) {
                    Text(
                        text = post.metadata.date,
                        style = mtTypography.subtitle2,
                    )
                }
            }
        }
    }
}