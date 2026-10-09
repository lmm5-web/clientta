package com.example.clientta.ui.screens.profissional

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import com.example.clientta.data.session.UserSession
import com.example.clientta.ui.theme.ClienttaBackground
import com.example.clientta.ui.theme.ClienttaPrimary
import com.example.clientta.ui.theme.ClienttaTextPrimary
import com.example.clientta.ui.theme.ClienttaTextSecondary
import com.example.clientta.ui.theme.ClienttaWhite
import com.example.clientta.viewmodel.ProfissionalViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AreaProfissionalScreen(
    viewModel: ProfissionalViewModel,
    onLogout: () -> Unit
) {
    val profissional by UserSession.currentProfissional.collectAsState()
    val agendamentos by viewModel.todosAgendamentos.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Área Profissional", color = ClienttaWhite, fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = {
                        UserSession.logout()
                        onLogout()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = "Sair",
                            tint = ClienttaWhite
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = ClienttaPrimary)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(ClienttaBackground)
                .padding(16.dp)
        ) {
            Text(
                text = "Bem-vinda, ${profissional?.nome ?: "Profissional"}!",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = ClienttaPrimary
            )

            Text(
                text = "Especialidade: ${profissional?.especialidade ?: ""}",
                fontSize = 13.sp,
                color = ClienttaTextSecondary,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Agenda de Atendimentos",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = ClienttaTextPrimary,
                modifier = Modifier.padding(bottom = 12.dp)
            )

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
                        ProfissionalAgendamentoCard(
                            agendamento = agendamento,
                            viewModel = viewModel
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProfissionalAgendamentoCard(
    agendamento: Agendamento,
    viewModel: ProfissionalViewModel
) {
    val clienteFlow = viewModel.getClienteById(agendamento.clienteId)
    val cliente by clienteFlow.collectAsState(initial = null)

    val tratamentoFlow = viewModel.getTratamentoById(agendamento.tratamentoId)
    val tratamento by tratamentoFlow.collectAsState(initial = null)

    val preAtendimentoFlow = viewModel.getPreAtendimentoByAgendamentoId(agendamento.id)
    val preAtendimento by preAtendimentoFlow.collectAsState(initial = null)

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
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = cliente?.nome ?: "Cliente",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = ClienttaPrimary
                )

                Text(
                    text = agendamento.status,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = when (agendamento.status) {
                        Agendamento.STATUS_CONCLUIDO -> ClienttaPrimary
                        Agendamento.STATUS_CANCELADO -> MaterialTheme.colorScheme.error
                        else -> ClienttaPrimary
                    }
                )
            }

            Text(
                text = "Tratamento: ${tratamento?.nome ?: ""}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = ClienttaTextPrimary
            )

            Text(
                text = "Data/Horário: ${agendamento.data} às ${agendamento.horario}",
                fontSize = 13.sp,
                color = ClienttaTextSecondary
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (preAtendimento != null) {
                Text(
                    text = "Pré-Atendimento:",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = ClienttaPrimary
                )
                Text(
                    text = preAtendimento!!.respostas,
                    fontSize = 12.sp,
                    color = ClienttaTextPrimary
                )
            } else {
                Text(
                    text = "Pré-atendimento ainda não preenchido.",
                    fontSize = 12.sp,
                    color = ClienttaTextSecondary
                )
            }

            if (agendamento.status == Agendamento.STATUS_AGENDADO) {
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = { viewModel.concluirAgendamento(agendamento) },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = ClienttaPrimary)
                    ) {
                        Text("Concluir", fontSize = 12.sp, color = ClienttaWhite)
                    }

                    OutlinedButton(
                        onClick = { viewModel.cancelarAgendamento(agendamento) },
                        modifier = Modifier.weight(0.8f)
                    ) {
                        Text("Cancelar", fontSize = 12.sp, color = MaterialTheme.colorScheme.error)
                    }
                }
            }
        }
    }
}

