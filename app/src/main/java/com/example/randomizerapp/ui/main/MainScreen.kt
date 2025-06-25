// MainScreen.kt – hosts TopBar, TabRow and MainNavHost
package com.example.randomizerapp.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.randomizerapp.R
import com.example.randomizerapp.ui.theme.AccentRed
import com.example.randomizerapp.ui.theme.SplashBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val navController = rememberNavController()
    var currentTab by rememberSaveable { mutableStateOf(MainTab.Dice) }


    /** ➡️  переставляємо TabRow ДО Scaffold-body,
     *      тому в Scaffold залишаємо тільки TopBar */
    Scaffold(
        containerColor = SplashBackground,
        topBar = {
            SmallTopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = SplashBackground,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    // 🔹 маленька кісточка 24.dp
                    Icon(
                        painter = painterResource(R.drawable.dice_5),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(48.dp)
                            .padding(start = 16.dp)
                    )
                },
                title = {
                    // 🔹 логотип-PNG “Ramdomizer+”
                    Icon(
                        painter = painterResource(R.drawable.logo_ramdomizer),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.height(28.dp)         // зменшуємо
                    )
                },
                actions = {
                    IconButton(
                        onClick = { navController.navigate(SETTINGS_ROUTE) }
                    ) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.White)
                    }
                }
            )
        }
    ) { innerPadding ->

        Column(Modifier.padding(innerPadding)) {

            /* ---------- TabRow ---------- */
            TabRow(
                selectedTabIndex = currentTab.ordinal,
                containerColor = SplashBackground,
                contentColor = AccentRed,
                indicator = { tabs ->
                    TabRowDefaults.Indicator(
                        Modifier.tabIndicatorOffset(tabs[currentTab.ordinal]),
                        color = AccentRed, height = 2.dp
                    )
                }
            ) {
                MainTab.entries.forEach { tab ->
                    LeadingIconTab(
                        selected = tab == currentTab,
                        onClick = {
                            currentTab = tab
                            navController.navigate(tab.route) {
                                popUpTo(MainTab.Dice.route) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        text = { Text(tab.name) },
                        icon = {
                            val iconRes = when (tab) {
                                MainTab.Dice   -> R.drawable.dice_5414035
                                MainTab.YesNo  -> R.drawable.rule_24px
                                MainTab.Number -> R.drawable.numbers_24px
                            }
                            Icon(
                                painter = painterResource(iconRes),
                                contentDescription = tab.name,
                                tint = if (tab == currentTab) AccentRed else Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        selectedContentColor = AccentRed,
                        unselectedContentColor = Color.White
                    )
                }
            }

            /* ---------- Навігаційний хост ---------- */
            MainNavHost(
                navController = navController,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}


@Preview
@Composable
private fun MainScreenPreview() = MainScreen()