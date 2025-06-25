package com.example.randomizerapp.ui.setting

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.randomizerapp.R
import com.example.randomizerapp.ui.theme.SplashBackground
import com.example.randomizerapp.viewmodel.SettingsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    vm: SettingsViewModel = koinViewModel()
) {
    val ctx = LocalContext.current
    val state = vm.state.collectAsState().value

    Surface(color = SplashBackground, modifier = Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            /* ── Header ───────────────────────── */
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 8.dp)
            ) {
                Icon(
                    painterResource(R.drawable.baseline_arrow_back_ios_new_24),
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { onBack() }
                )
                Spacer(Modifier.width(16.dp))
                Text("Menu", color = Color.White, style = MaterialTheme.typography.titleLarge)
            }

            Spacer(Modifier.height(32.dp))

            Image(
                painterResource(R.drawable.dice_5),
                contentDescription = null,
                modifier = Modifier.size(160.dp)
            )
            Image(
                painterResource(R.drawable.logo_ramdomizer),
                contentDescription = null,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(Modifier.height(48.dp))
            Divider(color = Color(0x33FFFFFF))

            /* ── Menu items ───────────────────── */
            MenuItem(
                icon = R.drawable.baseline_language_24,
                title = "Language"
            ) {
                Toast
                    .makeText(ctx, "Not implemented yet…", Toast.LENGTH_SHORT)
                    .show()
            }

            MenuItem(
                icon = R.drawable.baseline_share_24,
                title = "Share"
            ) {
                val link = "https://github.com/denny24s?tab=overview&from=2025-06-01&to=2025-06-24"
                val share = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, link)
                }
                ctx.startActivity(Intent.createChooser(share, "Share app"))
            }

            MenuItem(
                icon = R.drawable.baseline_star_border_24,
                title = "Rate"
            ) {
                val link = Uri.parse("https://github.com/denny24s?tab=overview&from=2025-06-01&to=2025-06-24")
                val rate = Intent(Intent.ACTION_VIEW, link)
                ctx.startActivity(rate)
            }
        }
    }
}

@Composable
private fun MenuItem(
    icon: Int,
    title: String,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 24.dp, vertical = 20.dp)
    ) {
        Icon(
            painterResource(icon),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
        Spacer(Modifier.width(24.dp))
        Text(title, color = Color.White, style = MaterialTheme.typography.bodyLarge)
    }
    Divider(color = Color(0x33FFFFFF), thickness = 1.dp)
}

