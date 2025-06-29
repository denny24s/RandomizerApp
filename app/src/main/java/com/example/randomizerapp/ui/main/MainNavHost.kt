package com.example.randomizerapp.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.randomizerapp.ui.setting.SettingsScreen
import com.example.randomizerapp.ui.theme.SplashBackground

enum class MainTab(val route: String) {
    Dice("dice"), YesNo("yesno"), Number("number")
}

const val SETTINGS_ROUTE = "settings"

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = MainTab.Dice.route,
        modifier = modifier
            .fillMaxSize()
            .background(SplashBackground)
    ) {
        composable(MainTab.Dice.route) { DiceScreen() }
        composable(MainTab.YesNo.route) { YesNoScreen() }
        composable(MainTab.Number.route) { NumberScreen() }
        composable(SETTINGS_ROUTE) {
            SettingsScreen(onBack = { navController.popBackStack() })
        }
    }
}

