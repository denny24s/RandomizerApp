package com.example.randomizerapp.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.randomizerapp.R
import com.example.randomizerapp.ui.theme.AccentRed
import com.example.randomizerapp.ui.theme.SplashBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val navController = rememberNavController()
    var currentTab by rememberSaveable { mutableStateOf(MainTab.Dice) }

    val backEntry by navController.currentBackStackEntryAsState()
    val isSettings = backEntry?.destination?.route == SETTINGS_ROUTE


    Scaffold(
        topBar = {
            if (!isSettings) {
                SmallTopAppBar(
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = SplashBackground,
                        titleContentColor = Color.White
                    ),
                    navigationIcon = {
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
                        Icon(
                            painter = painterResource(R.drawable.logo_ramdomizer),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.height(28.dp)
                        )
                    },
                    actions = {
                        IconButton(
                            onClick = { navController.navigate(SETTINGS_ROUTE) }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu",
                                tint = Color.White
                            )
                        }
                    }
                )
            }
        },

        containerColor = SplashBackground
    ) { innerPadding ->

        Column(Modifier.padding(innerPadding)) {

            if (!isSettings) {
                TabRow(
                    selectedTabIndex = currentTab.ordinal,
                    containerColor = SplashBackground,
                    contentColor = AccentRed,
                    indicator = { tp ->
                        SecondaryIndicator(
                            Modifier.tabIndicatorOffset(tp[currentTab.ordinal]),
                            height = 2.dp,
                            color = AccentRed
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
                                    MainTab.Dice -> R.drawable.dice_icon_main_screen
                                    MainTab.YesNo -> R.drawable.rule_24px
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
            }

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


