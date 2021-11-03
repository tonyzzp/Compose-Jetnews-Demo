package me.izzp.jetnewsdemo.article

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import me.izzp.jetnewsdemo.ProvideContentAlpha
import me.izzp.jetnewsdemo.data.Metadata
import me.izzp.jetnewsdemo.data.Post
import me.izzp.jetnewsdemo.mtColors
import me.izzp.jetnewsdemo.mtShapes
import me.izzp.jetnewsdemo.mtTypography
import me.izzp.jetnewsdemo.ui.theme.subtitle
import me.izzp.jetnewsdemo.ui.theme.title


@Composable
fun ArticleContent(
    post: Post,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(20.dp, 0.dp),
    ) {
        item(post.imageId) {
            Spacer(Modifier.height(20.dp))
            Image(
                painter = painterResource(post.imageId),
                null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.clip(mtShapes.medium).fillMaxWidth()
            )
        }
        item(post.title) {
            Spacer(Modifier.height(20.dp))
            Text(
                text = post.title,
                style = mtTypography.title,
            )
        }
        post.subtitle?.also { subtitle ->
            item(subtitle) {
                ProvideContentAlpha(ContentAlpha.medium) {
                    Text(
                        text = subtitle,
                        style = mtTypography.subtitle
                    )
                }
            }
        }
        item(post.metadata.hashCode()) {
            MetadataRow(post.metadata)
        }
        items(post.paragraphs) { p ->
            ParagraphBody(p)
        }
        item {
            Spacer(Modifier.height(30.dp))
        }
    }
}

@Composable
private fun MetadataRow(metadata: Metadata) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 20.dp)
    ) {
        Icon(
            Icons.Default.Person,
            null,
            modifier = Modifier
                .background(mtColors.onSurface, CircleShape)
                .size(48.dp)
                .clip(CircleShape)
                .padding(4.dp),
            tint = mtColors.surface,
        )
        Spacer(Modifier.width(8.dp))
        Column {
            Text(
                text = metadata.author.name,
                style = mtTypography.subtitle1
            )
            ProvideContentAlpha(ContentAlpha.medium) {
                Text(
                    text = metadata.date,
                    style = mtTypography.subtitle2
                )
            }
        }
    }
}
