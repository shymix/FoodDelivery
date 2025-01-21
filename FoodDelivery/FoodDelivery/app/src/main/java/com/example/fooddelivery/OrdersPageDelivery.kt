package com.example.fooddelivery.ui.delivery

import androidx.compose.foundation.clickable
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
import com.example.fooddelivery.ui.delivery.viewmodel.OrdersViewModel

@Composable
fun OrdersPage(viewModel: OrdersViewModel = OrdersViewModel()) {
    val orders by viewModel.orders.collectAsState()
    val message by viewModel.message.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Доступні замовлення") })
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

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(orders) { order ->
                    OrderItem(order = order, onPickOrder = { viewModel.pickOrder(order.id) })
                }
            }
        }
    }
}

@Composable
fun OrderItem(order: Order, onPickOrder: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onPickOrder() }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Замовлення ID: ${order.id}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Товар: ${order.items.joinToString(", ")}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Адреса: ${order.address}", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrdersPagePreview() {
    OrdersPage()
}
