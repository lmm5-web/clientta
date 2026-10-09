package com.example.clientta.ui.screens.cadastro

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clientta.ui.components.HeaderGlobal
import com.example.clientta.ui.theme.ClienttaBorderGray
import com.example.clientta.ui.theme.ClienttaPrimary
import com.example.clientta.ui.theme.ClienttaTextPrimary
import com.example.clientta.ui.theme.ClienttaTextSecondary
import com.example.clientta.ui.theme.ClienttaWhite
import com.example.clientta.viewmodel.CadastroViewModel

@Composable
fun CadastroScreen(
    viewModel: CadastroViewModel,
    onCadastroSuccess: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val nome by viewModel.nomeState.collectAsState()
    val cpf by viewModel.cpfState.collectAsState()
    val dataNascimento by viewModel.dataNascimentoState.collectAsState()
    val telefone by viewModel.telefoneState.collectAsState()
    val email by viewModel.emailState.collectAsState()
    val senha by viewModel.senhaState.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val cadastroSuccess by viewModel.cadastroSuccess.collectAsState()

    LaunchedEffect(cadastroSuccess) {
        if (cadastroSuccess) {
            viewModel.resetSuccess()
            onCadastroSuccess()
        }
    }

    Scaffold(
        topBar = {
            HeaderGlobal(onBackClick = onNavigateBack)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(ClienttaWhite)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Text(
                text = "Complete seu perfil",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = ClienttaTextPrimary
            )

            Text(
                text = "Informe seus dados pessoais para finalizar o cadastro na clínica",
                fontSize = 14.sp,
                color = ClienttaTextSecondary,
                modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
            )

            // Input: Nome Completo
            OutlinedTextField(
                value = nome,
                onValueChange = { viewModel.nomeState.value = it; viewModel.clearError() },
                placeholder = { Text("Nome Completo *", color = ClienttaTextSecondary) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = ClienttaPrimary,
                    unfocusedBorderColor = ClienttaBorderGray,
                    focusedContainerColor = ClienttaWhite,
                    unfocusedContainerColor = ClienttaWhite
                )
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Input: CPF
            OutlinedTextField(
                value = cpf,
                onValueChange = { viewModel.cpfState.value = it; viewModel.clearError() },
                placeholder = { Text("CPF *", color = ClienttaTextSecondary) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = ClienttaPrimary,
                    unfocusedBorderColor = ClienttaBorderGray,
                    focusedContainerColor = ClienttaWhite,
                    unfocusedContainerColor = ClienttaWhite
                )
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Input: Data de Nascimento
            OutlinedTextField(
                value = dataNascimento,
                onValueChange = { viewModel.dataNascimentoState.value = it; viewModel.clearError() },
                placeholder = { Text("Data de Nascimento (DD/MM/AAAA) *", color = ClienttaTextSecondary) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = ClienttaPrimary,
                    unfocusedBorderColor = ClienttaBorderGray,
                    focusedContainerColor = ClienttaWhite,
                    unfocusedContainerColor = ClienttaWhite
                )
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Input: Telefone / WhatsApp
            OutlinedTextField(
                value = telefone,
                onValueChange = { viewModel.telefoneState.value = it; viewModel.clearError() },
                placeholder = { Text("Telefone / WhatsApp *", color = ClienttaTextSecondary) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = ClienttaPrimary,
                    unfocusedBorderColor = ClienttaBorderGray,
                    focusedContainerColor = ClienttaWhite,
                    unfocusedContainerColor = ClienttaWhite
                )
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Input: E-mail
            OutlinedTextField(
                value = email,
                onValueChange = { viewModel.emailState.value = it; viewModel.clearError() },
                placeholder = { Text("E-mail *", color = ClienttaTextSecondary) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = ClienttaPrimary,
                    unfocusedBorderColor = ClienttaBorderGray,
                    focusedContainerColor = ClienttaWhite,
                    unfocusedContainerColor = ClienttaWhite
                )
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Input: Senha
            OutlinedTextField(
                value = senha,
                onValueChange = { viewModel.senhaState.value = it; viewModel.clearError() },
                placeholder = { Text("Senha *", color = ClienttaTextSecondary) },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = ClienttaPrimary,
                    unfocusedBorderColor = ClienttaBorderGray,
                    focusedContainerColor = ClienttaWhite,
                    unfocusedContainerColor = ClienttaWhite
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = true,
                    onCheckedChange = {},
                    colors = CheckboxDefaults.colors(checkedColor = ClienttaPrimary)
                )
                Text(
                    text = "Li e concordo com os termos de uso e política de privacidade",
                    fontSize = 12.sp,
                    color = ClienttaTextPrimary
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

            Spacer(modifier = Modifier.height(28.dp))

            // Botão Sólido Finalizar (#673AB7)
            Button(
                onClick = { viewModel.cadastrar() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ClienttaPrimary),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Finalizar",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = ClienttaWhite
                )
            }
        }
    }
}
