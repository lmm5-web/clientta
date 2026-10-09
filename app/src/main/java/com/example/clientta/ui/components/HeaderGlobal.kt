package com.example.clientta.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.clientta.ui.theme.ClienttaPrimary
import com.example.clientta.ui.theme.ClienttaPrimaryLight
import com.example.clientta.ui.theme.ClienttaWhite

@Composable
fun HeaderGlobal(
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(ClienttaWhite)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (onBackClick != null) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Voltar",
                    tint = ClienttaPrimary,
                    modifier = Modifier.size(24.dp)
                )
            }
        } else {
            Box(modifier = Modifier.size(48.dp))
        }

        // Ícone do Logo: Círculo roxo claro (#E8E0F5) com flor/símbolo roxo (#673AB7)
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ClienttaPrimaryLight),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Face,
                contentDescription = "Clientta Logo",
                tint = ClienttaPrimary,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
