package me.izzp.jetnewsdemo.interests

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import me.izzp.jetnewsdemo.JetNewsViewModel
import me.izzp.jetnewsdemo.R
import me.izzp.jetnewsdemo.mtColors
import me.izzp.jetnewsdemo.mtTypography


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RegionItem(
    key: String,
    text: String,
    isMarkup: Boolean,
    onClick: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(),
            onClick = onClick,
        ).padding(8.dp, 12.dp)
    ) {
        Image(painterResource(R.drawable.placeholder_1_1), null)
        Spacer(Modifier.width(8.dp))
        Text(text = text, style = mtTypography.subtitle1)
        Spacer(Modifier.weight(1f))
        AnimatedContent(
            targetState = isMarkup,
        ) {
            if (it) {
                Icon(
                    imageVector = Icons.Default.Done,
                    null,
                    tint = Color.White,
                    modifier = Modifier.background(mtColors.secondary, CircleShape).size(28.dp)
                )
            } else {
                Icon(
                    Icons.Default.Add,
                    null,
                    tint = mtColors.secondary,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
    Divider(startIndent = 56.dp)
}

@Composable
fun Region(
    vm: JetNewsViewModel,
    data: List<String>,
    type: String,
    modifier: Modifier = Modifier,
) {
    val favorites by vm.favorites.collectAsState()
    Column(modifier.fillMaxWidth()) {
        data.forEach {
            val key = "$type-$it"
            RegionItem(
                key = key,
                text = it,
                isMarkup = favorites.contains(key),
                onClick = {
                    vm.toggleFavorite(key)
                }
            )
        }
    }
}