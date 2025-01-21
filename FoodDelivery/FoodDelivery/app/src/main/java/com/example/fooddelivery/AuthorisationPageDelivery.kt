package com.example.fooddelivery.ui.delivery

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fooddelivery.data.repository.AuthRepository
import kotlinx.coroutines.launch

@Composable
fun AuthorisationPage(authRepository: AuthRepository = AuthRepository()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isSignUpMode by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
            Card(
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(16.dp),
                colors = CardDefaults.cardColors(contentColor = Color(0xFFEFEFEF))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Заголовок
                    Text(
                        text = "Вхід в акаунт",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF74C68F))
                            .padding(vertical = 16.dp),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Поле логіну
                    OutlinedTextField(
                        value = login,
                        onValueChange = { login = it },
                        label = { Text("Логін") },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF74C68F),
                            unfocusedBorderColor = Color(0xFF74C68F)
                        )
                    )

                    // Поле паролю
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Пароль") },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF74C68F),
                            unfocusedBorderColor = Color(0xFF74C68F)
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(text = "$errorMessage")

                    Button(
                        onClick =
                        {
                            scope.launch {
                                userViewModel.login(LoginRequest(login, password))
                            }
                            if (user != null) {
                                navController.navigate("orderspage")
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF74C68F)),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Увійти", color = Color.White)
                    }

                    Button(
                        onClick =
                        {
                            navController.navigate("signupdeliv")
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF74C68F)),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Не маєте акаунта? Зареєструйтеся", color = Color.White)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(text = message, color = MaterialTheme.colorScheme.error)
                }
            }
    }
}
