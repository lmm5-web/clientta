package com.example.clientta.ui.screens.preatendimento

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clientta.ui.theme.ClienttaPrimary
import com.example.clientta.viewmodel.PreAtendimentoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreAtendimentoScreen(
    agendamentoId: Long,
    viewModel: PreAtendimentoViewModel,
    onSalvarSuccess: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val preAtendimentoExistenteFlow = viewModel.getByAgendamentoId(agendamentoId)
    val preAtendimentoExistente by preAtendimentoExistenteFlow.collectAsState(initial = null)

    val alergia by viewModel.alergiaState.collectAsState()
    val doencaPele by viewModel.doencaPeleState.collectAsState()
    val medicamento by viewModel.medicamentoState.collectAsState()
    val observacoes by viewModel.observacoesState.collectAsState()
    val termoAceito by viewModel.termoAceitoState.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val salvarSuccess by viewModel.salvarSuccess.collectAsState()

    LaunchedEffect(salvarSuccess) {
        if (salvarSuccess) {
            viewModel.resetSuccess()
            onSalvarSuccess()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pré-Atendimento", color = MaterialTheme.colorScheme.onPrimary) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = ClienttaPrimary)
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    if (preAtendimentoExistente != null) {
                        Text(
                            text = "Pré-Atendimento Concluído",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = ClienttaPrimary
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "Data de Preenchimento: ${preAtendimentoExistente!!.dataPreenchimento}",
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = preAtendimentoExistente!!.respostas,
                            fontSize = 15.sp,
                            lineHeight = 22.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    } else {
                        Text(
                            text = "Questionário de Anamnese",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = ClienttaPrimary
                        )

                        Text(
                            text = "Por favor, responda às perguntas para o seu atendimento.",
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        OutlinedTextField(
                            value = alergia,
                            onValueChange = { viewModel.alergiaState.value = it; viewModel.clearError() },
                            label = { Text("Possui alguma alergia a cosméticos?") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ClienttaPrimary)
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            value = doencaPele,
                            onValueChange = { viewModel.doencaPeleState.value = it; viewModel.clearError() },
                            label = { Text("Possui problemas ou sensibilidade de pele?") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ClienttaPrimary)
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            value = medicamento,
                            onValueChange = { viewModel.medicamentoState.value = it; viewModel.clearError() },
                            label = { Text("Usa algum medicamento de uso contínuo?") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ClienttaPrimary)
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            value = observacoes,
                            onValueChange = { viewModel.observacoesState.value = it; viewModel.clearError() },
                            label = { Text("Observações adicionais para o profissional") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = ClienttaPrimary)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Checkbox(
                                checked = termoAceito,
                                onCheckedChange = { viewModel.termoAceitoState.value = it; viewModel.clearError() },
                                colors = CheckboxDefaults.colors(checkedColor = ClienttaPrimary)
                            )
                            Text(
                                text = "Declaro que li e concordo com os termos de consentimento do atendimento.",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }

                        if (errorMessage != null) {
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = errorMessage!!,
                                color = MaterialTheme.colorScheme.error,
                                fontSize = 13.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(
                            onClick = { viewModel.salvarPreAtendimento(agendamentoId) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = ClienttaPrimary),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Salvar Pré-Atendimento", fontSize = 16.sp, color = MaterialTheme.colorScheme.onPrimary)
                        }
                    }
                }
            }
        }
    }
}
