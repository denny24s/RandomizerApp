package com.example.randomizerapp.ui.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.domain.models.YesNoResult
import com.example.randomizerapp.R
import com.example.randomizerapp.ui.theme.AccentRed
import com.example.randomizerapp.viewmodel.YesNoViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.randomizerapp.ui.theme.ControlButtonBg
import com.example.randomizerapp.ui.theme.HistoryBg
import com.example.randomizerapp.viewmodel.NumberViewModel

@Composable
fun NumberScreen(vm: NumberViewModel = koinViewModel()) {
    val state by vm.state.collectAsState()
    var showHistory by remember { mutableStateOf(true) }

    Box(Modifier.fillMaxSize()) {

        /* ---------- number (fade-in) ---------- */
        state.number?.let { num ->
            val alpha by animateFloatAsState(1f, label = "")
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 120.dp)
                    .alpha(alpha)
            ) {
                Text(
                    text = num.toString(),
                    modifier = Modifier.padding(horizontal = 48.dp, vertical = 32.dp),
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.Black
                )
            }
        }

        /* ---------- inputs ---------- */
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = 80.dp),
            horizontalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            NumberInput(
                label = "from:",
                value = state.from,
                onValueChange = vm::updateFrom,
                modifier = Modifier.weight(1f)
                    .padding(start = 6.dp)
            )
            NumberInput(
                label = "to:",
                value = state.to,
                onValueChange = vm::updateTo,
                modifier = Modifier.weight(1f)
                    .padding(end = 6.dp)
            )
        }


        /* ---------- history ---------- */
        AnimatedVisibility(
            visible = showHistory && state.history.isNotEmpty(),
            enter = fadeIn(tween(300)),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 80.dp, start = 66.dp, end = 66.dp)
                .fillMaxWidth()
        ) { NumberHistoryCard(state.history) }

        /* history toggle */
        SquareIconButton(
            onClick = { showHistory = !showHistory },
            iconRes = R.drawable.format_list_numbered_24px,
            contentDesc = "History",
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 6.dp, bottom = 24.dp)
        )

        /* generate button */
        val fromOk = state.from.toIntOrNull() != null
        val toOk   = state.to.toIntOrNull()   != null
        val enabled = fromOk && toOk && state.from.toInt() <= state.to.toInt()

        Button(
            onClick = vm::onGenerate,
            enabled = enabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = AccentRed,
                contentColor = Color.White,
                disabledContainerColor = AccentRed.copy(alpha = .3f)
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp, start = 66.dp, end = 66.dp)
                .fillMaxWidth()
        ) { Text("Get random") }
    }
}

/* ---------- reusable ---------- */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NumberInput(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier          // ← новий параметр
) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color  = ControlButtonBg,
        modifier = modifier                 // ← застосовуємо
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(label, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Light)
            TextField(
                value = value,
                onValueChange = { onValueChange(it.take(8)) },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.White,
                    focusedIndicatorColor   = Color.White
                ),
                textStyle = LocalTextStyle.current.copy(color = Color.White)
            )
        }
    }
}


@Composable
private fun NumberHistoryCard(history: List<Int>) {
    // ⚡️ останні результати першими
    val recentFirst = remember(history) { history.asReversed() }

    Surface(
        shape = RoundedCornerShape(8.dp),
        color  = Color(0xFF4A4C5F)
    ) {
        LazyColumn(
            modifier = Modifier
                .heightIn(max = 200.dp)
                .fillMaxWidth()
        ) {
            items(recentFirst) { n ->
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    Alignment.Center
                ) {
                    Text("%,d".format(n), color = Color.White)
                }
                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color(0xFF6A6C80)
                )
            }
        }
    }
}

