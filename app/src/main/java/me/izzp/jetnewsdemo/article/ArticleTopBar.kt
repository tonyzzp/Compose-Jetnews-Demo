package me.izzp.jetnewsdemo.article

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.izzp.jetnewsdemo.R
import me.izzp.jetnewsdemo.mtColors
import me.izzp.jetnewsdemo.mtTypography


@Composable
fun ArticleTopBar(
    onBackClick: () -> Unit,
) {
    TopAppBar(
        backgroundColor = mtColors.surface,
        navigationIcon = {
            IconButton(
                onClick = { onBackClick() },
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    null,
                    tint = mtColors.secondary,
                )
            }
        },
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painterResource(R.drawable.icon_article_background),
                    null,
                    modifier = Modifier.clip(
                        CircleShape
                    ).size(46.dp),
                )
                Spacer(Modifier.width(8.dp))
                Column {
                    Text("Published in:", style = mtTypography.subtitle1)
                    Text("Android Developers", style = mtTypography.subtitle1)
                }
            }
        },
        elevation = 1.dp,
    )
}

@Composable
@Preview
private fun ArticleTopBarPreview() {
    ArticleTopBar { }
}