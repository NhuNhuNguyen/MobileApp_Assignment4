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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nhu.nhu_shukki_comp304_sec002_lab04.ui.theme.Nhu_ShukKi_COMP304_Sec002_Lab04Theme

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

//@Composable
//fun MainActivity(navController: NavController) {
//    val categories = listOf("Historic", "Parks", "Museums", "Touristic Spots")
//    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
//        items(categories) { category ->
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 8.dp)
//                    .clickable { navController.navigate("second_screen/$category") }
//            ) {
//                Text(
//                    text = category,
//                    style = MaterialTheme.typography.titleMedium,
//                    modifier = Modifier.padding(16.dp)
//                )
//            }
//        }
//    }
//}

//@Composable
//fun AppNavigation() {
//    val navController = rememberNavController()
//    NavHost(navController = navController, startDestination = "main_screen") {
//        composable("main_screen") { MainActivity(navController) }
//        composable("second_screen/{category}") { backStackEntry ->
//            NhuActivity(navController, backStackEntry.arguments?.getString("category") ?: "")
//        }
//        composable("third_screen/{itemName}") { backStackEntry ->
//            ShukKiActivity(backStackEntry.arguments?.getString("itemName") ?: "")
//        }
//    }
//}

@Composable
fun MainActivity(navController: NavController) {
    val categories = listOf("Historic", "Parks", "Museums", "Touristic")
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(categories) { category ->
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .clickable { navController.navigate("landmarks/$category") },
                elevation = CardDefaults.elevatedCardElevation(8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.landmark), // Placeholder
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