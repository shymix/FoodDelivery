package com.example.fooddelivery

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch


data class MenuItem(val name: String, val price: String, val imageRes: Int)

@Composable
fun Menu(navController: NavHostController, userViewModel: UserViewModel, restaurantId: Int, viewModel: ItemsViewModel = ItemsViewModel()) {
    val items by viewModel.items.observeAsState(initial = emptyList())
    val scope = rememberCoroutineScope()
    scope.launch {
        viewModel.fetchItems(restaurantId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF74C68F)),
                modifier = Modifier.height(40.dp).clip(RoundedCornerShape(12.dp))
            ) {
                Text(text = "Доступні заклади", style = MaterialTheme.typography.bodySmall)
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.user),
                contentDescription = "Profile Icon",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(40.dp)
                    .clickable { navController.navigate("Profile") }
            )
        }

        // Title
        Text(
            text = "Меню McDonald's",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
//            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 60.dp, top = 30.dp)
        )

        // Menu list
        LazyColumn {
            items(items) { item ->
                ItemCard("menu", item,
                    onClick = {navController.navigate("item/${item.id}") },
                    onButtonClick = {userViewModel.addToCart(item.id)})
            }
        }
    }
}
