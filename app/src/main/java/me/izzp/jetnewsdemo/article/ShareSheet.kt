package me.izzp.jetnewsdemo.article

import android.content.ComponentName
import android.content.Intent
import android.content.pm.ResolveInfo
import android.graphics.drawable.Drawable
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.izzp.jetnewsdemo.data.Post
import me.izzp.jetnewsdemo.mtShapes
import me.izzp.jetnewsdemo.mtTypography
import me.izzp.jetnewsdemo.toAnnotatedString

@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun ShareSheet(
    post: Post,
    sheetState: ModalBottomSheetState,
) {
    val coroutineScope = rememberCoroutineScope()
    val modPadding = Modifier.padding(8.dp)
    val context = LocalContext.current
    Box(
        Modifier
            .then(modPadding)
            .size(30.dp, 5.dp)
            .background(Color.LightGray, CircleShape)
    )
    Text(
        text = "Share",
        style = mtTypography.subtitle1,
        modifier = modPadding,
    )
    Text(
        text = post.url,
        style = mtTypography.body1,
        modifier = modPadding,
    )
    val clipboardManager = LocalClipboardManager.current
    OutlinedButton(
        onClick = {
            Toast.makeText(context, "已复制", Toast.LENGTH_SHORT).show()
            clipboardManager.setText(post.url.toAnnotatedString())
            coroutineScope.launch {
                sheetState.hide()
            }
        },
        shape = CircleShape,
        modifier = modPadding,
    ) {
        Icon(Icons.Default.ContentCopy, null)
        Spacer(Modifier.width(4.dp))
        Text("COPY")
    }
    Card(
        modifier = modPadding.fillMaxWidth(),
        elevation = 4.dp,
    ) {
        Box(
            modifier = Modifier.padding(8.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = post.title,
                style = mtTypography.subtitle1,
            )
        }
    }

    Divider(Modifier.padding(0.dp, 8.dp))
    var isLoading by remember { mutableStateOf(true) }
    LaunchedEffect(Unit) {
        isLoading = true
        delay(3000)
        isLoading = false
    }
    Box(
        modifier = Modifier.height(56.dp).fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        AnimatedVisibility(
            visible = isLoading,
            exit = slideOutVertically(),
            enter = fadeIn(),
        ) {
            Row(
                modifier = Modifier.height(56.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally)
            ) {
                repeat(5) {
                    LoadingItem()
                }
            }
        }
        AnimatedVisibility(
            visible = !isLoading,
            enter = slideInVertically(
                initialOffsetY = { height ->
                    height
                }
            ),
            exit = fadeOut(),
        ) {
            Text(
                text = "No recommended people to share with",
                style = mtTypography.caption,
                modifier = Modifier.fillMaxWidth().height(56.dp).wrapContentSize(Alignment.Center)
            )
        }
    }
    Divider(Modifier.padding(0.dp, 8.dp))
    ShareRow(post.url, onClick = {
        coroutineScope.launch {
            sheetState.hide()
        }
    })
    Spacer(Modifier.height(16.dp))
}

@Composable
private fun LoadingItem() {
    val transition = rememberInfiniteTransition()
    val alpha by transition.animateFloat(
        1f,
        0.4f,
        infiniteRepeatable(tween(700), RepeatMode.Reverse)
    )
    Box(
        Modifier
            .size(48.dp)
            .background(Color.LightGray.copy(alpha), mtShapes.medium)
    )
}

@Composable
private fun ShareItem(
    title: String,
    icon: Drawable,
    intent: Intent,
    info: ResolveInfo,
    onClick: () -> Unit,
) {
    val context = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Image(
            painter = rememberImagePainter(icon),
            null,
            Modifier
                .size(46.dp)
                .border(1.dp, Color.LightGray, CircleShape)
                .clip(CircleShape)
                .clickable {
                    onClick()
                    val intent = Intent(intent)
                    intent.component =
                        ComponentName.createRelative(
                            info.activityInfo.packageName,
                            info.activityInfo.name,
                        )
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    println(intent)
                    try {
                        context.startActivity(intent)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
        )
        Text(
            text = title,
            style = mtTypography.body2,
        )
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
private fun ShareRow(
    content: String,
    onClick: () -> Unit,
) {
    val context = LocalContext.current
    val pm = context.packageManager
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "text/plain"
    intent.putExtra(Intent.EXTRA_TEXT, content)
    val list = context.packageManager.queryIntentActivities(intent, 0)
    LazyVerticalGrid(
        cells = GridCells.Adaptive(100.dp),
    ) {
        repeat(4) {
            items(list) { info ->
                ShareItem(
                    title = info.loadLabel(pm).toString(),
                    icon = info.loadIcon(pm),
                    intent = intent,
                    info = info,
                    onClick = onClick,
                )
            }
        }
    }
}
