// DiceScreen.kt  (ui/main/dice)
package com.example.randomizerapp.ui.main.dice

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.randomizerapp.ui.theme.ControlButtonBg
import com.example.randomizerapp.viewmodel.DiceViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DiceScreen(vm: DiceViewModel = koinViewModel()) {
    val state by vm.state.collectAsState()
    var showHistory by remember { mutableStateOf(true) }

    Box(Modifier.fillMaxSize()) {

        /* ---------- Центр: самі кості ---------- */
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier           // ⬅️ ДОДАЙТЕ
                .align(Alignment.Center)
                .padding(
                    bottom = 164.dp
                )
                .fillMaxWidth()           // ← оце і є ключ
        ) {
            DiceFacesRow(state.faces)
            Spacer(Modifier.height(16.dp))
        }

        /* ---------- HISTORY: притискаємо до кнопки ---------- */
        AnimatedVisibility(
            visible = showHistory && state.history.isNotEmpty(),
            enter = fadeIn(tween(300)),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    bottom = 80.dp,
                    start = 66.dp,
                    end = 66.dp
                ) // 100 dp ≈ висота кнопки + проміжок
                .fillMaxWidth()
        ) {
            HistoryCard(history = state.history)

        }

        /* ---- history toggle (зліва внизу) ---- */
        SquareIconButton(
            onClick = { showHistory = !showHistory },
            iconRes = R.drawable.format_list_numbered_24px,
            contentDesc = "History",
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 6.dp, bottom = 24.dp)
        )

        /* ---- plus / minus (справа внизу) ---- */
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 6.dp, bottom = 24.dp)
        ) {
            SquareIconButton(
                onClick = vm::incDice,
                enabled = state.diceCount < 3,
                iconRes = R.drawable.add_24px,
                contentDesc = "Increase dice"
            )
            Spacer(Modifier.height(12.dp))
            SquareIconButton(
                onClick = vm::decDice,
                enabled = state.diceCount > 1,
                iconRes = R.drawable.remove_24px,
                contentDesc = "Decrease dice"
            )
        }


        // Get random button (bottom-center)
        Button(
            onClick = vm::onRoll,
            colors = ButtonDefaults.buttonColors(
                containerColor = AccentRed,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp, start = 66.dp, end = 66.dp)
                .fillMaxWidth()
        ) {
            Text("Get random")
        }
    }
}

/* ---- reusable composables ---- */

@Composable
private fun DiceFacesRow(faces: List<Int>) {
    // fade-in так, як було
    val alpha by animateFloatAsState(
        targetValue = if (faces.isEmpty()) 0f else 1f,
        label = ""
    )

    /* ── 1. Обираємо розмір і відступи залежно від кількості ── */
    val (diceSize, spacing) = when (faces.size) {
        3    -> 96.dp to 12.dp   // три кості – дрібніші й ближчі
        else -> 120.dp to 24.dp  // 1 або 2 – як раніше
    }

    /* ── 2. Рядок заповнює ширину й центрує контент ── */
    Row(
        horizontalArrangement = Arrangement.spacedBy(
            spacing,
            Alignment.CenterHorizontally          // ⚡️ центрування
        ),
        modifier = Modifier
            .fillMaxWidth()                       // займаємо всю ширину
            .alpha(alpha)
    ) {
        faces.forEach { face ->
            Image(
                painter = painterResource(diceRes(face)),
                contentDescription = "Dice $face",
                modifier = Modifier.size(diceSize)
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


@Composable fun YesNoScreen()    { Placeholder("Yes / No") }
@Composable fun NumberScreen()   { Placeholder("Number") }

@Composable
private fun Placeholder(label: String) = Box(Modifier.fillMaxSize()) {
    Text(label, modifier = Modifier.align(Alignment.Center), color = Color.White)
}

@Composable
private fun SquareIconButton(
    onClick: () -> Unit,
    enabled: Boolean = true,
    @DrawableRes iconRes: Int,
    modifier: Modifier = Modifier,
    contentDesc: String
) {
    Surface(
        shape  = RoundedCornerShape(12.dp),
        color  = ControlButtonBg,
        modifier = modifier.size(56.dp)                // квадрат 56×56
    ) {
        IconButton(
            onClick = onClick,
            enabled = enabled,
            modifier = Modifier.fillMaxSize()           // заповнюємо Surface
        ) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = contentDesc,
                tint = if (enabled) Color.White else Color.White.copy(alpha = .3f)
            )
        }
    }
}

@Composable
private fun HistoryCard(history: List<List<Int>>) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = Color(0xFF4A4C5F)          // той самий фон
    ) {
        LazyColumn(
            modifier = Modifier
                .heightIn(max = 200.dp)
                .fillMaxWidth()
        ) {
            items(history) { faces ->
                HistoryRow(faces)
                Divider(
                    color = Color(0xFF6A6C80),        // тонка лінія між рядками
                    thickness = 1.dp
                )
            }
        }
    }
}

@Composable
private fun HistoryRow(faces: List<Int>) {
    /* невеличкі кості – 36 dp, між ними 8 dp  */
    Row(
        horizontalArrangement = Arrangement.spacedBy(
            8.dp,
            alignment = Alignment.CenterHorizontally
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        faces.forEach { face ->
            Image(
                painter = painterResource(diceRes(face)),
                contentDescription = "h-$face",
                modifier = Modifier.size(36.dp)
            )
        }
    }
}
