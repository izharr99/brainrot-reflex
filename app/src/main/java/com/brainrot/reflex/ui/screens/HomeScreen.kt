package com.brainrot.reflex.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.brainrot.reflex.ui.theme.BrainrotBlack
import com.brainrot.reflex.ui.theme.NeonGreen
import com.brainrot.reflex.ui.theme.NeonPink

@Composable
fun HomeScreen(onStartClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BrainrotBlack)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "BRAINROT\nREFLEX",
            style = MaterialTheme.typography.displayLarge,
            color = NeonGreen,
            fontWeight = FontWeight.Black
        )
        Button(
            onClick = onStartClick,
            colors = ButtonDefaults.buttonColors(containerColor = NeonPink),
            modifier = Modifier.padding(top = 48.dp)
        ) {
            Text(
                text = "START",
                style = MaterialTheme.typography.labelLarge,
                color = BrainrotBlack
            )
        }
    }
}
