package com.example.clientta.ui.screens.tratamentos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clientta.ui.components.HeaderGlobal
import com.example.clientta.ui.theme.ClienttaBackground
import com.example.clientta.ui.theme.ClienttaPrimary
import com.example.clientta.ui.theme.ClienttaTextPrimary
import com.example.clientta.ui.theme.ClienttaTextSecondary
import com.example.clientta.ui.theme.ClienttaWhite
import com.example.clientta.viewmodel.TratamentoViewModel
import java.util.Locale

@Composable
fun DetalheTratamentoScreen(
    tratamentoId: Long,
    viewModel: TratamentoViewModel,
    onIniciarAgendamento: (Long) -> Unit,
    onNavigateBack: () -> Unit
) {
    val tratamentoFlow = viewModel.getTratamentoById(tratamentoId)
    val tratamento by tratamentoFlow.collectAsState(initial = null)

    Scaffold(
        topBar = {
            HeaderGlobal(
                title = "Detalhes do Tratamento",
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
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = ClienttaPrimary
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = tratamento!!.descricao,
                            fontSize = 15.sp,
                            color = ClienttaTextSecondary,
                            lineHeight = 22.sp
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = "Preço: ${String.format(Locale.getDefault(), "R$ %.2f", tratamento!!.preco)}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = ClienttaTextPrimary
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Duração estimada: ${tratamento!!.duracao} minutos",
                            fontSize = 15.sp,
                            color = ClienttaPrimary
                        )

                        Spacer(modifier = Modifier.height(28.dp))

                        Button(
                            onClick = { onIniciarAgendamento(tratamento!!.id) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = ClienttaPrimary),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Agendar Atendimento", fontSize = 16.sp, color = ClienttaWhite)
                        }
                    }
                }
            }
        }
    }
}

