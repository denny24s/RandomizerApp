package com.example.randomizerapp.ui.setting

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.randomizerapp.R
import com.example.randomizerapp.ui.theme.MainColor
import com.example.randomizerapp.ui.theme.SplashBackground
import com.example.randomizerapp.viewmodel.SettingsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen(
    onBack: () -> Unit, vm: SettingsViewModel = koinViewModel()
) {
    val ctx = LocalContext.current

    Surface(color = MainColor, modifier = Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {


            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 24.dp, end = 8.dp)
            ) {
                Icon(painterResource(R.drawable.baseline_arrow_back_ios_new_24),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(12.dp)
                        .align(Alignment.CenterStart)
                        .clickable { onBack() })
                Text(
                    "Menu",
                    color = Color.White,
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(Modifier.height(120.dp))
            Image(
                painterResource(R.drawable.dice_5),
                contentDescription = null,
                modifier = Modifier.size(180.dp)
            )
            Spacer(Modifier.height(6.dp))
            Image(
                painterResource(R.drawable.logo_ramdomizer),
                contentDescription = null,
                modifier = Modifier.height(48.dp)
            )

            Spacer(Modifier.height(120.dp))

            MenuItem(R.drawable.baseline_language_24, "Language") {
                Toast.makeText(ctx, "Not implemented yet…", Toast.LENGTH_SHORT).show()
            }
            MenuItem(R.drawable.baseline_share_24, "Share") {
                val link = "https://github.com/denny24s/RandomizerApp"
                ctx.startActivity(
                    Intent.createChooser(
                        Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"; putExtra(Intent.EXTRA_TEXT, link)
                        }, "Share app"
                    )
                )
            }
            MenuItem(R.drawable.baseline_star_border_24, "Rate") {
                ctx.startActivity(
                    Intent(
                        Intent.ACTION_VIEW, Uri.parse("https://github.com/denny24s/RandomizerApp")
                    )
                )
            }
        }
    }
}

@Composable
private fun MenuItem(icon: Int, title: String, onClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 24.dp, vertical = 18.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Icon(painterResource(icon), null, tint = Color.White, modifier = Modifier.size(24.dp))
        Spacer(Modifier.width(24.dp))
        Text(title, color = Color.White, style = MaterialTheme.typography.bodyLarge)
    }
}






