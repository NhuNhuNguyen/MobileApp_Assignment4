package com.nhu.nhu_shukki_comp304_sec002_lab04

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.navigation.NavController
import com.google.android.gms.maps.model.LatLng
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.ui.unit.dp


//@Composable
//fun NhuActivity(navController: NavController, category: String) {
//    val attractions = when (category) {
//        "Historic" -> listOf("Royal Ontario Museum", "Art Gallery of Ontario", "Casa Loma")
//        "Parks" -> listOf("High Park", "Toronto Island", "Trinity Bellwoods")
//        "Museums" -> listOf("Science Centre", "Aga Khan Museum", "Bata Shoe Museum")
//        else -> listOf("CN Tower", "Ripley’s Aquarium", "Distillery District")
//    }
//
//    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
//        items(attractions) { attraction ->
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 8.dp)
//                    .clickable { navController.navigate("third_screen/$attraction") }
//            ) {
//                Text(
//                    text = attraction,
//                    style = MaterialTheme.typography.titleMedium,
//                    modifier = Modifier.padding(16.dp)
//                )
//            }
//        }
//    }
//}

data class Landmark(val name: String, val address: String, val latLng: LatLng)

@Composable
fun NhuActivity(navController: NavController, category: String?) {
    val landmarks = when (category) {
        "Historic" -> listOf(
            Landmark("Royal Ontario Museum", "100 Queens Park", LatLng(43.6677, -79.3948)),
            Landmark("Art Gallery of Ontario", "317 Dundas St W", LatLng(43.6536, -79.3925))
        )
        "Parks" -> listOf(
            Landmark("High Park", "1873 Bloor St W", LatLng(43.6465, -79.4637)),
            Landmark("Trinity Bellwoods Park", "790 Queen St W", LatLng(43.6469, -79.4175))
        )
        else -> emptyList()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(landmarks) { landmark ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        navController.navigate("map/${landmark.name}/${landmark.latLng.latitude}/${landmark.latLng.longitude}")
                    },
                elevation = CardDefaults.elevatedCardElevation(8.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = landmark.name, style = MaterialTheme.typography.titleLarge)
                    Text(text = landmark.address, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}