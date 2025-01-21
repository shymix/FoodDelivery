package com.example.fooddelivery.ui.delivery

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fooddelivery.data.models.Order
import com.example.fooddelivery.ui.delivery.viewmodel.PersonalViewModel

@Composable
fun PersonalPage(viewModel: PersonalViewModel = PersonalViewModel()) {
    val userInfo by viewModel.userInfo.collectAsState()
    val orderHistory by viewModel.orderHistory.collectAsState()
    val message by viewModel.message.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Сторінка кур'єра") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            if (message.isNotEmpty()) {
                Text(
                    text = message,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Personal Info Section
            userInfo?.let { info ->
                Text(
                    text = "Ім'я: ${info.firstName} ${info.lastName}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Пошта: ${info.email}",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Місто: ${info.city}",
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Order History Section
            Text(
                text = "Історія Замовлень",
                style = MaterialTheme.typography.titleMedium
            )
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(orderHistory) { order ->
                    OrderHistoryItem(order)
                }
            }
        }
    }
}

@Composable
fun OrderHistoryItem(order: Order) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Замовлення ID: ${order.id}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Товари: ${order.items.joinToString(", ")}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Адреса: ${order.address}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Статус: ${order.status}", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PersonalPagePreview() {
    PersonalPage()
}
