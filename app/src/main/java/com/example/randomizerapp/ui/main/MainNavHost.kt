package com.example.randomizerapp.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.example.randomizerapp.ui.theme.SplashBackground

enum class MainTab(val route: String) { Dice("dice"), YesNo("yesno"), Number("number") }

@Composable
fun MainNavHost(
    navController: NavHostController,              // <-- обов’язковий
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = MainTab.Dice.route,
        modifier = modifier
            .fillMaxSize()
            .background(SplashBackground)
    ) {
        composable(MainTab.Dice.route)   { DiceScreen() }
        composable(MainTab.YesNo.route)  { YesNoScreen() }
        composable(MainTab.Number.route) { NumberScreen() }
    }
}
