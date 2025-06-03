// DiceScreen.kt  (ui/main/dice)
package com.example.randomizerapp.ui.main.dice

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.randomizerapp.R
import com.example.randomizerapp.ui.theme.AccentRed
import com.example.randomizerapp.viewmodel.DiceViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DiceScreen(vm: DiceViewModel = koinViewModel()) {
    val state by vm.state.collectAsState()

    var showHistory by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            // dice faces
            DiceFacesRow(state.faces)

            Spacer(Modifier.height(32.dp))

            // history container
            AnimatedVisibility(
                visible = showHistory && state.history.isNotEmpty(),
                enter = fadeIn(tween(300))
            ) {
                HistoryList(history = state.history)
            }
        }

        // history toggle button (bottom-start)
        IconButton(
            onClick = { showHistory = !showHistory },
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 24.dp, bottom = 24.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.format_list_numbered_24px),
                contentDescription = "History",
                tint = Color.White
            )
        }

        // + / – buttons (bottom-end)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 24.dp, bottom = 24.dp)
        ) {
            IconButton(onClick = vm::incDice) {
                Icon(
                    painter = painterResource(R.drawable.add_24px),
                    contentDescription = "Increase dice",
                    tint = Color.White
                )
            }
            Spacer(Modifier.height(8.dp))
            IconButton(onClick = vm::decDice) {
                Icon(
                    painter = painterResource(R.drawable.remove_24px),
                    contentDescription = "Decrease dice",
                    tint = Color.White
                )
            }
        }

        // Get random button (bottom-center)
        Button(
            onClick = vm::onRoll,
            colors = ButtonDefaults.buttonColors(
                containerColor = AccentRed,
                contentColor = Color.White
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp, start = 64.dp, end = 64.dp)
                .fillMaxWidth()
        ) {
            Text("Get random")
        }
    }
}

/* ---- reusable composables ---- */

@Composable
private fun DiceFacesRow(faces: List<Int>) {
    val alphaTarget = if (faces.isEmpty()) 0f else 1f
    val alpha by animateFloatAsState(targetValue = alphaTarget, label = "")

    Row(
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier.alpha(alpha)
    ) {
        faces.forEach { face ->
            Image(
                painter = painterResource(diceRes(face)),
                contentDescription = "Dice $face",
                modifier = Modifier.size(120.dp)
            )
        }
    }
}

private fun diceRes(face: Int) = when (face) {
    1 -> R.drawable.dice_1
    2 -> R.drawable.dice_2
    3 -> R.drawable.dice_3
    4 -> R.drawable.dice_4
    5 -> R.drawable.dice_5
    else -> R.drawable.dice_6
}

@Composable
private fun HistoryList(history: List<List<Int>>) {
    Surface(
        tonalElevation = 3.dp,
        shape = MaterialTheme.shapes.medium,
        color = Color(0xFF4A4C5F)
    ) {
        LazyColumn(
            modifier = Modifier
                .heightIn(max = 200.dp)
                .width(200.dp)
                .padding(8.dp)
        ) {
            items(history) { faces ->
                DiceFacesRow(faces = faces)
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}

@Composable fun YesNoScreen()    { Placeholder("Yes / No") }
@Composable fun NumberScreen()   { Placeholder("Number") }

@Composable
private fun Placeholder(label: String) = Box(Modifier.fillMaxSize()) {
    Text(label, modifier = Modifier.align(Alignment.Center), color = Color.White)
}
