package com.example.randomizerapp.ui.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.text.font.FontWeight
import com.example.randomizerapp.ui.theme.HistoryBg


@Composable
fun YesNoScreen(vm: YesNoViewModel = koinViewModel()) {
    val state by vm.state.collectAsState()
    var showHistory by remember { mutableStateOf(true) }

    Box(Modifier.fillMaxSize()) {

        /* ----- коло з PNG ----- */
        state.answer?.let { ans ->
            val alpha by animateFloatAsState(1f, label = "")
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 120.dp)      // відступ від TabRow
            ) {
                Image(
                    painter = painterResource(
                        if (ans.answer) R.drawable.yes else R.drawable.no
                    ),
                    contentDescription = if (ans.answer) "Yes" else "No",
                    modifier = Modifier
                        .size(220.dp)
                        .alpha(alpha)
                )
            }
        }

        /* --------  History -------- */
        AnimatedVisibility(
            visible = showHistory && state.history.isNotEmpty(),
            enter   = fadeIn(tween(300)),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 80.dp, start = 66.dp, end = 66.dp)
                .fillMaxWidth()
        ) {
            YesNoHistoryCard(state.history)
        }

        /* history toggle (зліва внизу) */
        SquareIconButton(
            onClick = { showHistory = !showHistory },
            iconRes = R.drawable.format_list_numbered_24px,
            contentDesc = "History",
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 6.dp, bottom = 24.dp)
        )

        /* Get random button */
        Button(
            onClick = vm::onGet,
            colors = ButtonDefaults.buttonColors(
                containerColor = AccentRed,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp, start = 66.dp, end = 66.dp)
                .fillMaxWidth()
        ) { Text("Get random") }
    }
}

/* картка історії */
@Composable
private fun YesNoHistoryCard(list: List<YesNoResult>) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = HistoryBg
    ) {
        LazyColumn(
            modifier = Modifier
                .heightIn(max = 200.dp)
                .fillMaxWidth()
        ) {
            items(list) { item ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (item.answer) "Yes" else "No",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize
                    )

                }
                Divider(
                    color = Color(0xFF6A6C80),
                    thickness = 1.dp
                )
            }
        }
    }
}
