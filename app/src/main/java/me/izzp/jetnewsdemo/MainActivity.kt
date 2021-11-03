package me.izzp.jetnewsdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import me.izzp.jetnewsdemo.ui.theme.JetNewsDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        val vm by viewModels<JetNewsViewModel>()
        setContent {
            JetNewsDemoTheme {
                Gate(vm)
            }
        }
    }
}
