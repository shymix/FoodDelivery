package com.example.fooddelivery.ui.delivery

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fooddelivery.data.models.Order
import com.example.fooddelivery.ui.delivery.viewmodel.OrderDetailsViewModel

@Composable
fun OrderDetailsPage(
    orderId: String,
    viewModel: OrderDetailsViewModel = OrderDetailsViewModel(orderId)
) {
    val orderDetails by viewModel.orderDetails.collectAsState()
    val message by viewModel.message.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Order Details") })
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

            orderDetails?.let { order ->
                Text(text = "Order ID: ${order.id}", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Items: ${order.items.joinToString(", ")}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Address: ${order.address}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Status: ${order.status}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { viewModel.confirmOrder() },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Confirm Order")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderDetailsPagePreview() {
    OrderDetailsPage(orderId = "123")
}
