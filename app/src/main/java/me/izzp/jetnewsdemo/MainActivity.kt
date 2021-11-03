package me.izzp.jetnewsdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Surface
import me.izzp.jetnewsdemo.ui.theme.JetNewsDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vm by viewModels<JetNewsViewModel>()
        setContent {
            JetNewsDemoTheme {
                Surface {
                    Gate(vm)
                }
            }
        }
    }
}
