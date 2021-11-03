package me.izzp.jetnewsdemo

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable

@Composable
fun NotAvailableDialog(
    onClose: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onClose,
        confirmButton = {
            TextButton(
                onClick = onClose,
            ) {
                Text("CLOSE")
            }
        },
        text = {
            Text("Function not available")
        },
    )
}