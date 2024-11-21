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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember

//@Composable
//fun ShukKiActivity(itemName: String) {
//    val context = LocalContext.current
//    val mapView = rememberMapViewWithLifecycle()
//    val locationPermissionLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.RequestPermission()
//    ) { isGranted ->
//        if (!isGranted) {
//            Toast.makeText(context, "Location permission denied!", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    LaunchedEffect(Unit) {
//        if (ContextCompat.checkSelfPermission(
//                context,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
//        }
//    }
//
//    AndroidView(factory = { mapView }) { mapView ->
//        mapView.getMapAsync { googleMap ->
//            // Example coordinates for the attraction (set dynamically)
//            val location = LatLng(43.6677, -79.3948) // Example: ROM
//            googleMap.addMarker(
//                MarkerOptions()
//                    .position(location)
//                    .title(itemName)
//            )
//            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
//        }
//    }
//}

@Composable
fun ShukKiActivity(itemName: String, latitude: Double, longitude: Double) {
    val mapView = rememberMapViewWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = itemName,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )
        AndroidView(
            factory = { mapView },
            modifier = Modifier.fillMaxSize()
        ) { mapView ->
            mapView.getMapAsync { googleMap ->
                val location = LatLng(latitude, longitude)
                googleMap.addMarker(MarkerOptions().position(location).title(itemName))
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
            }
        }
    }
}

@Composable
fun rememberMapViewWithLifecycle(): MapView {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }
    DisposableEffect(Unit) {
        mapView.onCreate(null)
        mapView.onResume()
        onDispose { mapView.onDestroy() }
    }
    return mapView
}