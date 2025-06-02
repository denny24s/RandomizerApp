// MainScreen.kt – hosts TopBar, TabRow and MainNavHost
package com.example.randomizerapp.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.randomizerapp.R
import com.example.randomizerapp.ui.theme.AccentRed
import com.example.randomizerapp.ui.theme.SplashBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(goToSettings: () -> Unit = {}) {
    var currentTab by remember { mutableStateOf(MainTab.Dice) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    // logo (dice img + "Ramdomizer+" png)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(R.drawable.dice_5),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                        Spacer(Modifier.width(8.dp))
                        Icon(
                            painter = painterResource(R.drawable.logo_ramdomizer),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = SplashBackground,
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = goToSettings) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        containerColor = SplashBackground,
        bottomBar = {
            TabRow(
                selectedTabIndex = currentTab.ordinal,
                containerColor = SplashBackground,
                contentColor = AccentRed,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier.tabIndicatorOffset(tabPositions[currentTab.ordinal]),
                        color = AccentRed
                    )
                }
            ) {
                MainTab.values().forEach { tab ->
                    Tab(
                        selected = tab == currentTab,
                        onClick = { currentTab = tab },
                        text = { Text(tab.name.replaceFirstChar { it.uppercase() }) },
                        icon = {
                            val iconRes = when (tab) {
                                MainTab.Dice   -> R.drawable.dice_5414035
                                MainTab.YesNo  -> R.drawable.rule_24px
                                MainTab.Number -> R.drawable.numbers_24px
                            }
                            Icon(
                                painter = painterResource(iconRes),
                                contentDescription = tab.name,
                                tint = if (tab == currentTab) AccentRed else Color.White
                            )
                        },
                        selectedContentColor = AccentRed,
                        unselectedContentColor = Color.White
                    )
                }
            }
        }
    ) { innerPadding ->
        MainNavHost(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Preview
@Composable
private fun MainScreenPreview() = MainScreen()
