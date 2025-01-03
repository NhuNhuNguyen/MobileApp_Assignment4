package com.nhu.nhu_shukki_comp304_sec002_lab04

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.Image
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun MainActivity(navController: NavController) {
    val context = LocalContext.current
    val categories = listOf("Historic", "Parks", "Museums", "Touristic")
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item{
            Text(
                text = "Toronto Landmark Locator",
                style = MaterialTheme.typography.displayMedium.copy(
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color(0xFF3A3A3A),
                modifier = Modifier
                    .padding(vertical = 24.dp, horizontal = 8.dp)
//                    .clickable { showCard = true }
            )}
        items(categories) { category ->
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .clickable { navController.navigate("landmarks/$category") },
                elevation = CardDefaults.elevatedCardElevation(8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    val imageResId = context.resources.getIdentifier(
                        category.lowercase(),
                        "drawable",
                        context.packageName
                    )
                    Image(
                        painter = painterResource(id = imageResId),
                        contentDescription = null,
                        modifier = Modifier.size(100.dp)
                    )
                    Text(
                        text = category,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "categories") {
        composable("categories") { MainActivity(navController) }
        composable("landmarks/{category}") { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category")
            NhuActivity(navController, category)
        }
        composable("map/{name}/{lat}/{lng}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val lat = backStackEntry.arguments?.getString("lat")?.toDouble() ?: 0.0
            val lng = backStackEntry.arguments?.getString("lng")?.toDouble() ?: 0.0
            ShukKiActivity(navController, name, lat, lng)
        }
    }
}