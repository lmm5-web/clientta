package com.example.clientta.ui.screens.inicio

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clientta.data.session.UserSession
import com.example.clientta.ui.components.BottomNavBar
import com.example.clientta.ui.components.HeaderGlobal
import com.example.clientta.ui.screens.tratamentos.TratamentoItemCard
import com.example.clientta.ui.theme.ClienttaBorderGray
import com.example.clientta.ui.theme.ClienttaPrimary
import com.example.clientta.ui.theme.ClienttaPrimaryLight
import com.example.clientta.ui.theme.ClienttaTextPrimary
import com.example.clientta.ui.theme.ClienttaTextSecondary
import com.example.clientta.ui.theme.ClienttaWhite
import com.example.clientta.viewmodel.TratamentoViewModel

@Composable
fun InicioScreen(
    tratamentoViewModel: TratamentoViewModel,
    onNavigateToTratamentos: () -> Unit,
    onNavigateToAgendamentos: () -> Unit,
    onNavigateToPerfil: () -> Unit,
    onSelectTratamento: (Long) -> Unit
) {
    val cliente by UserSession.currentCliente.collectAsState()
    val tratamentos by tratamentoViewModel.tratamentosAtivos.collectAsState()
    var buscaText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            HeaderGlobal()
        },
        bottomBar = {
            BottomNavBar(
                currentRoute = "tratamentos",
                onItemSelected = { route ->
                    when (route) {
                        "tratamentos" -> onNavigateToTratamentos()
                        "agendamentos", "historico" -> onNavigateToAgendamentos()
                        "perfil" -> onNavigateToPerfil()
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Saudação
            Text(
                text = "Olá, ${cliente?.nome?.split(" ")?.firstOrNull() ?: "Usuário"}!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = ClienttaTextPrimary
            )

            Text(
                text = "Encontre o melhor cuidado para você",
                fontSize = 14.sp,
                color = ClienttaTextSecondary,
                modifier = Modifier.padding(top = 2.dp, bottom = 16.dp)
            )

            // Barra de Busca
            OutlinedTextField(
                value = buscaText,
                onValueChange = { buscaText = it },
                placeholder = { Text("Buscar tratamento...", color = ClienttaTextSecondary) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Buscar",
                        tint = ClienttaTextSecondary
                    )
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(24.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = ClienttaPrimary,
                    unfocusedBorderColor = ClienttaBorderGray,
                    focusedContainerColor = ClienttaWhite,
                    unfocusedContainerColor = ClienttaWhite
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Categorias em Destaque
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CategoryItem("Faciais", onClick = onNavigateToTratamentos)
                CategoryItem("Epilação", onClick = onNavigateToTratamentos)
                CategoryItem("Corporais", onClick = onNavigateToTratamentos)
                CategoryItem("Massagens", onClick = onNavigateToTratamentos)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Cabeçalho da Seção
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Tratamentos em destaque",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = ClienttaTextPrimary
                )

                TextButton(onClick = onNavigateToTratamentos) {
                    Text("Ver todos", color = ClienttaPrimary, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Lista de Tratamentos em Destaque
            val destaques = if (buscaText.isBlank()) {
                tratamentos.take(3)
            } else {
                tratamentos.filter { it.nome.contains(buscaText, ignoreCase = true) }
            }

            destaques.forEach { tratamento ->
                TratamentoItemCard(
                    tratamento = tratamento,
                    onClick = { onSelectTratamento(tratamento.id) }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Card "Cuide de você!"
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = ClienttaPrimaryLight)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Cuide de você! ✨",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = ClienttaPrimary
                    )

                    Text(
                        text = "Agende uma sessão de relaxamento e renove suas energias na clínica Clientta.",
                        fontSize = 13.sp,
                        color = ClienttaTextPrimary,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    Button(
                        onClick = onNavigateToTratamentos,
                        colors = ButtonDefaults.buttonColors(containerColor = ClienttaPrimary),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Agendar agora", fontSize = 14.sp, color = ClienttaWhite)
                    }
                }
            }
        }
    }
}

@Composable
private fun CategoryItem(
    title: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(CircleShape)
                .background(ClienttaPrimaryLight),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Face,
                contentDescription = title,
                tint = ClienttaPrimary,
                modifier = Modifier.size(28.dp)
            )
        }

        Text(
            text = title,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = ClienttaTextPrimary,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
