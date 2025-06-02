package com.example.randomizerapp.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.example.randomizerapp.ui.theme.SplashBackground

enum class MainTab(val route: String) { Dice("dice"), YesNo("yesno"), Number("number") }

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        startDestination = MainTab.Dice.route,
        navController = navController,
        modifier = modifier
            .fillMaxSize()
            .background(SplashBackground)        // same dark bg as splash
    ) {
        composable(MainTab.Dice.route)   { DiceScreen() }
        composable(MainTab.YesNo.route)  { YesNoScreen() }     // stub
        composable(MainTab.Number.route) { NumberScreen() }    // stub
    }
}
