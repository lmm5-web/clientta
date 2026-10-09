package com.example.clientta.ui.screens.tratamentos

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clientta.R
import com.example.clientta.data.entity.Tratamento
import com.example.clientta.ui.components.HeaderGlobal
import com.example.clientta.ui.theme.ClienttaBorderGray
import com.example.clientta.ui.theme.ClienttaPrimary
import com.example.clientta.ui.theme.ClienttaPrimaryLight
import com.example.clientta.ui.theme.ClienttaTextPrimary
import com.example.clientta.ui.theme.ClienttaTextSecondary
import com.example.clientta.ui.theme.ClienttaWhite
import com.example.clientta.viewmodel.TratamentoViewModel
import java.util.Locale

@Composable
fun TratamentosScreen(
    viewModel: TratamentoViewModel,
    onSelectTratamento: (Long) -> Unit,
    onNavigateBack: () -> Unit
) {
    val tratamentos by viewModel.tratamentosAtivos.collectAsState()
    var categoriaSelecionada by remember { mutableStateOf("Faciais") }
    val categorias = listOf("Faciais", "Epilação", "Corporais", "Massagens")

    Scaffold(
        topBar = {
            HeaderGlobal(onBackClick = onNavigateBack)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Tratamentos",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = ClienttaTextPrimary
            )

            Text(
                text = "Escolha o procedimento perfeito para você",
                fontSize = 14.sp,
                color = ClienttaTextSecondary,
                modifier = Modifier.padding(top = 2.dp, bottom = 16.dp)
            )

            // Chips de Categoria
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                categorias.forEach { cat ->
                    val isSelected = cat == categoriaSelecionada
                    Box(
                        modifier = Modifier
                            .height(38.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(if (isSelected) ClienttaPrimaryLight else ClienttaWhite)
                            .border(
                                width = if (isSelected) 1.5.dp else 1.dp,
                                color = if (isSelected) ClienttaPrimary else ClienttaBorderGray,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .clickable { categoriaSelecionada = cat }
                            .padding(horizontal = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = cat,
                            fontSize = 14.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSelected) ClienttaPrimary else ClienttaTextSecondary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (tratamentos.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.empty_tratamentos),
                        color = ClienttaTextSecondary
                    )
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    items(tratamentos) { tratamento ->
                        TratamentoItemCard(
                            tratamento = tratamento,
                            onClick = { onSelectTratamento(tratamento.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TratamentoItemCard(
    tratamento: Tratamento,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = ClienttaWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, ClienttaBorderGray)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagem do tratamento com bordas arredondadas
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(ClienttaPrimaryLight),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Face,
                    contentDescription = null,
                    tint = ClienttaPrimary,
                    modifier = Modifier.size(36.dp)
                )
            }

            Column(
                modifier = Modifier
                    .padding(start = 14.dp)
                    .weight(1f)
            ) {
                Text(
                    text = tratamento.nome,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = ClienttaTextPrimary
                )

                Text(
                    text = "${tratamento.descricao.take(35)}...",
                    fontSize = 12.sp,
                    color = ClienttaTextSecondary,
                    modifier = Modifier.padding(vertical = 2.dp)
                )

                Text(
                    text = "${String.format(Locale.getDefault(), "R$ %.2f", tratamento.preco)} • ${tratamento.duracao} min",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = ClienttaPrimary
                )
            }

            // Seta de navegação roxa (#673AB7) à direita
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Acessar",
                tint = ClienttaPrimary,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
