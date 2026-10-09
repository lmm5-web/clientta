package com.example.clientta.ui.screens.agendamentos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clientta.R
import com.example.clientta.data.entity.Agendamento
import com.example.clientta.ui.components.HeaderGlobal
import com.example.clientta.ui.theme.ClienttaBackground
import com.example.clientta.ui.theme.ClienttaPrimary
import com.example.clientta.ui.theme.ClienttaTextPrimary
import com.example.clientta.ui.theme.ClienttaTextSecondary
import com.example.clientta.ui.theme.ClienttaWhite
import com.example.clientta.viewmodel.AgendamentoViewModel
import com.example.clientta.viewmodel.TratamentoViewModel

@Composable
fun AgendamentosListScreen(
    agendamentoViewModel: AgendamentoViewModel,
    tratamentoViewModel: TratamentoViewModel,
    onOpenPreAtendimento: (Long) -> Unit,
    onNavigateBack: () -> Unit
) {
    val agendamentos by agendamentoViewModel.meusAgendamentos.collectAsState()

    Scaffold(
        topBar = {
            HeaderGlobal(
                title = "Meus Agendamentos",
                onBackClick = onNavigateBack
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(ClienttaBackground)
                .padding(16.dp)
        ) {
            if (agendamentos.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.empty_agendamentos),
                        color = ClienttaTextSecondary
                    )
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(agendamentos) { agendamento ->
                        AgendamentoCard(
                            agendamento = agendamento,
                            tratamentoViewModel = tratamentoViewModel,
                            onPreAtendimento = { onOpenPreAtendimento(agendamento.id) },
                            onCancelar = { agendamentoViewModel.cancelar(agendamento) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AgendamentoCard(
    agendamento: Agendamento,
    tratamentoViewModel: TratamentoViewModel,
    onPreAtendimento: () -> Unit,
    onCancelar: () -> Unit
) {
    val tratamentoFlow = tratamentoViewModel.getTratamentoById(agendamento.tratamentoId)
    val tratamento by tratamentoFlow.collectAsState(initial = null)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = ClienttaWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = tratamento?.nome ?: "Tratamento",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = ClienttaPrimary
                )

                Text(
                    text = agendamento.status,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = when (agendamento.status) {
                        Agendamento.STATUS_CONCLUIDO -> ClienttaPrimary
                        Agendamento.STATUS_CANCELADO -> MaterialTheme.colorScheme.error
                        else -> ClienttaPrimary
                    }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Data: ${agendamento.data} às ${agendamento.horario}",
                fontSize = 14.sp,
                color = ClienttaTextSecondary
            )

            if (agendamento.status == Agendamento.STATUS_AGENDADO) {
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = onPreAtendimento,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = ClienttaPrimary)
                    ) {
                        Text("Pré-atendimento", fontSize = 12.sp, color = ClienttaWhite)
                    }

                    OutlinedButton(
                        onClick = onCancelar,
                        modifier = Modifier.weight(0.8f)
                    ) {
                        Text("Cancelar", fontSize = 12.sp, color = MaterialTheme.colorScheme.error)
                    }
                }
            }
        }
    }
}

