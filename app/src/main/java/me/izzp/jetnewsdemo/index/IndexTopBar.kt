package me.izzp.jetnewsdemo.home


import androidx.compose.foundation.Image
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import me.izzp.jetnewsdemo.R
import me.izzp.jetnewsdemo.mtColors

@Composable
fun IndexTopBar(
    onIconClick: () -> Unit,
) {
    TopAppBar(
        backgroundColor = mtColors.surface,
    ) {
        IconButton(
            onClick = { onIconClick() }
        ) {
            Icon(
                painterResource(R.drawable.ic_jetnews_logo),
                null,
                tint = mtColors.secondary,
            )
        }
        Image(
            painterResource(R.drawable.ic_jetnews_wordmark),
            null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.weight(1f),
            colorFilter = ColorFilter.tint(mtColors.onSurface)
        )
        IconButton(
            onClick = {}
        ) {
            Icon(Icons.Default.Search, null)
        }
    }
}
