package com.example.clientta.ui.screens.agendamentos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clientta.ui.components.HeaderGlobal
import com.example.clientta.ui.theme.ClienttaBackground
import com.example.clientta.ui.theme.ClienttaBorderGray
import com.example.clientta.ui.theme.ClienttaComplementary2
import com.example.clientta.ui.theme.ClienttaPrimary
import com.example.clientta.ui.theme.ClienttaTextPrimary
import com.example.clientta.ui.theme.ClienttaWhite
import com.example.clientta.viewmodel.AgendamentoViewModel
import com.example.clientta.viewmodel.TratamentoViewModel

@Composable
fun AgendarScreen(
    tratamentoId: Long,
    agendamentoViewModel: AgendamentoViewModel,
    tratamentoViewModel: TratamentoViewModel,
    onAgendamentoConcluido: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val tratamentoFlow = tratamentoViewModel.getTratamentoById(tratamentoId)
    val tratamento by tratamentoFlow.collectAsState(initial = null)

    val data by agendamentoViewModel.dataSelecionada.collectAsState()
    val horario by agendamentoViewModel.horarioSelecionado.collectAsState()
    val errorMessage by agendamentoViewModel.errorMessage.collectAsState()
    val agendamentoSuccess by agendamentoViewModel.agendamentoSuccess.collectAsState()

    val horaiosDisponiveis = listOf("09:00", "10:00", "11:00", "14:00", "15:00", "16:00", "17:00")

    LaunchedEffect(agendamentoSuccess) {
        if (agendamentoSuccess) {
            agendamentoViewModel.resetSuccess()
            onAgendamentoConcluido()
        }
    }

    Scaffold(
        topBar = {
            HeaderGlobal(
                title = "Agendar Horário",
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
            if (tratamento != null) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = ClienttaWhite),
                    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Text(
                            text = tratamento!!.nome,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = ClienttaPrimary
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = data,
                            onValueChange = {
                                agendamentoViewModel.dataSelecionada.value = it
                                agendamentoViewModel.clearError()
                            },
                            label = { Text("Data (DD/MM/AAAA) *") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = ClienttaPrimary,
                                unfocusedBorderColor = ClienttaBorderGray,
                                focusedLabelColor = ClienttaPrimary
                            )
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = "Horários Disponíveis:",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = ClienttaTextPrimary
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(3),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.height(120.dp)
                        ) {
                            items(horaiosDisponiveis) { slot ->
                                FilterChip(
                                    selected = horario == slot,
                                    onClick = {
                                        agendamentoViewModel.horarioSelecionado.value = slot
                                        agendamentoViewModel.clearError()
                                    },
                                    label = { Text(slot) },
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = ClienttaPrimary,
                                        selectedLabelColor = ClienttaWhite,
                                        containerColor = ClienttaComplementary2
                                    )
                                )
                            }
                        }

                        if (errorMessage != null) {
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = errorMessage!!,
                                color = MaterialTheme.colorScheme.error,
                                fontSize = 13.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Button(
                            onClick = { agendamentoViewModel.agendar(tratamentoId) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = ClienttaPrimary),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Confirmar Agendamento", fontSize = 16.sp, color = ClienttaWhite)
                        }
                    }
                }
            }
        }
    }
}

