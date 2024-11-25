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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


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
            Landmark("Casa Loma", "1 Austin Terrace", LatLng(43.6781, -79.4094)),
            Landmark("Royal Ontario Museum", "100 Queens Park", LatLng(43.6677, -79.3948)),
            Landmark("Richmond Hill Heritage Centre", "19 Church St N", LatLng(43.9143, -79.4393)),
            Landmark("Toronto Old City Hall", "60 Queen St W", LatLng(43.6526, -79.3817))
        )
        "Parks" -> listOf(
            Landmark("Harbour Square Park", "25 Queens Quay W", LatLng(43.6463, -79.3782)),
            Landmark("High Park", "1873 Bloor St W", LatLng(43.6465, -79.4637)),
            Landmark("Tommy Thompson Park", "1 Leslie St", LatLng(43.6393, -79.3271)),
            Landmark("Trinity Bellwoods Park", "790 Queen St W", LatLng(43.6469, -79.4175))
        )

        "Museums" -> listOf(
            Landmark("Art Gallery of Ontario", "317 Dundas St W", LatLng(43.6536, -79.3925)),
            Landmark("iArtS Museum", "580 King St W 2nd Floor", LatLng(43.6450, -79.3993)),
            Landmark("Museum of Toronto", "401 Richmond St W", LatLng(43.6486, -79.3940)),
            Landmark("Nature Museum", "168 Hamilton St", LatLng(43.7046, -79.3640))
        )

        "Touristic" -> listOf(
            Landmark("Toronto Skyline View", "Ferry Dock", LatLng(43.6321, -79.3576)),
            Landmark("Trinity Bellwoods Park", "790 Queen St W", LatLng(43.6469, -79.4175))
        )
        else -> emptyList()
    }


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        item{
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start

            ){
                IconButton(
                    onClick = {
                        navController.navigateUp() // Navigate back to the previous screen
                    },
                    modifier = Modifier.size(48.dp),
                    content = {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
//                        tint = if (isFavorite) Color.Red else Color.Gray // Set the icon color to red
                        )
                    }
                )
                Text(
                    text = "${category}",
                    style = MaterialTheme.typography.displayMedium.copy(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color(0xFF3A3A3A),
                    modifier = Modifier
                        .padding(vertical = 15.dp, horizontal = 8.dp)
//                    .clickable { showCard = true }
                )}
            }

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