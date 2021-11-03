package me.izzp.jetnewsdemo.index

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ViewList
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import me.izzp.jetnewsdemo.R
import me.izzp.jetnewsdemo.mtColors
import me.izzp.jetnewsdemo.mtShapes


@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun Drawer(
    type: IndexPageType,
    onNavigate: (route: String) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(12.dp)
    ) {
        Icon(
            painterResource(R.drawable.ic_jetnews_logo),
            null,
            tint = mtColors.secondary,
        )
        Spacer(Modifier.width(6.dp))
        Icon(
            painterResource(R.drawable.ic_jetnews_wordmark),
            null,
            tint = mtColors.onSurface
        )
    }
    Spacer(Modifier.height(6.dp))
    Divider()
    Spacer(Modifier.height(6.dp))
    MenuItem(
        icon = Icons.Default.Home,
        text = "Home",
        selected = type == IndexPageType.home,
        onClick = {
            onNavigate("index/home")
        }
    )
    MenuItem(
        icon = Icons.Default.ViewList,
        text = "Interests",
        selected = type == IndexPageType.interests,
        onClick = {
            onNavigate("index/interests")
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MenuItem(
    icon: ImageVector,
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val bgColor =
        if (selected) mtColors.secondary.copy(0.3f) else mtColors.surface
    val tint = if (selected) mtColors.secondary else mtColors.onSurface
    ListItem(
        icon = {
            Icon(icon, null, tint = tint)
        },
        text = {
            Text(text = text, color = tint)
        },
        modifier = Modifier
            .padding(8.dp)
            .background(bgColor, mtShapes.medium)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                onClick = onClick,
            )
    )
}