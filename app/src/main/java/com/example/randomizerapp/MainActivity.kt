package com.example.randomizerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Box
import com.example.randomizerapp.ui.splashscreen.SplashScreen
import com.example.randomizerapp.ui.main.MainScreen
import com.example.randomizerapp.ui.theme.RandomizerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RandomizerAppTheme {
                AppEntryPoint()
            }
        }
    }
}

@Composable
private fun AppEntryPoint() {
    var isSplashVisible by remember { mutableStateOf(true) }

    if (isSplashVisible) {
        SplashScreen(onTimeout = { isSplashVisible = false })
    } else {
        MainScreen(goToSettings = { /* navController.navigate("settings") */ })
    }
}


@Composable
private fun MainPlaceholderScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Main Screen",
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@Composable
@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
fun MainPlaceholderPreview() {
    RandomizerAppTheme {
        MainPlaceholderScreen()
    }
}
