package com.example.clientta.ui.screens.perfil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clientta.ui.components.HeaderGlobal
import com.example.clientta.ui.theme.ClienttaBackground
import com.example.clientta.ui.theme.ClienttaBorderGray
import com.example.clientta.ui.theme.ClienttaPrimary
import com.example.clientta.ui.theme.ClienttaTextPrimary
import com.example.clientta.ui.theme.ClienttaTextSecondary
import com.example.clientta.ui.theme.ClienttaWhite
import com.example.clientta.viewmodel.PerfilViewModel

@Composable
fun PerfilScreen(
    viewModel: PerfilViewModel,
    onNavigateBack: () -> Unit
) {
    val cliente by viewModel.currentCliente.collectAsState()
    val telefone by viewModel.telefoneState.collectAsState()
    val email by viewModel.emailState.collectAsState()
    val endereco by viewModel.enderecoState.collectAsState()
    val successMessage by viewModel.successMessage.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Scaffold(
        topBar = {
            HeaderGlobal(
                title = "Meu Perfil",
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
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = ClienttaWhite),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = cliente?.nome ?: "Cliente",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = ClienttaPrimary
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "CPF: ${viewModel.maskCpf(cliente?.cpf ?: "")}",
                        fontSize = 14.sp,
                        color = ClienttaTextSecondary
                    )

                    Text(
                        text = "Data Nasc.: ${cliente?.dataNascimento ?: ""}",
                        fontSize = 14.sp,
                        color = ClienttaTextSecondary
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Atualizar Contato",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = ClienttaTextPrimary
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = telefone,
                        onValueChange = { viewModel.telefoneState.value = it; viewModel.clearMessages() },
                        label = { Text("Telefone / WhatsApp") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = ClienttaPrimary,
                            unfocusedBorderColor = ClienttaBorderGray
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { viewModel.emailState.value = it; viewModel.clearMessages() },
                        label = { Text("E-mail") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = ClienttaPrimary,
                            unfocusedBorderColor = ClienttaBorderGray
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = endereco,
                        onValueChange = { viewModel.enderecoState.value = it; viewModel.clearMessages() },
                        label = { Text("Endereço") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = ClienttaPrimary,
                            unfocusedBorderColor = ClienttaBorderGray
                        )
                    )

                    if (successMessage != null) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = successMessage!!,
                            color = ClienttaPrimary,
                            fontSize = 13.sp
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

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = { viewModel.salvarPerfil() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = ClienttaPrimary),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Salvar Alterações", fontSize = 16.sp, color = ClienttaWhite)
                    }
                }
            }
        }
    }
}

