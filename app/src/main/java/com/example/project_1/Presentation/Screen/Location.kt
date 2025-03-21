package com.example.project_1.Presentation.Screen

import android.Manifest
import android.content.Context
import android.location.Location
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng

import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Location() {
    val context = LocalContext.current

    // Permission state for fine and coarse location
    val locationPermission = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    // Create a camera position state to manage the map's camera
    val cameraPositionState = rememberCameraPositionState()

    // Define properties for the map (including enabling my location)
    val mapProperties = MapProperties(
        minZoomPreference = 3f,
        maxZoomPreference = 19f,
        isTrafficEnabled = false,
        isBuildingEnabled = true,
        isMyLocationEnabled = locationPermission.allPermissionsGranted,
        mapType = MapType.SATELLITE
    )

    // Define map UI settings (e.g., compass, zoom controls)
    val uiSettings = MapUiSettings(
        compassEnabled = true,
        rotationGesturesEnabled = true,
        scrollGesturesEnabled = true,
        tiltGesturesEnabled = true,
        zoomControlsEnabled = true,
        zoomGesturesEnabled = true,
        myLocationButtonEnabled = true
    )

    // Launch effect to request permissions when needed
    LaunchedEffect(key1 = locationPermission.permissions) {
        locationPermission.launchMultiplePermissionRequest()
    }

    data class Markerdata(
        val location : LatLng,
        val title : String,
        val description : String ?
    )


    val markerdata = listOf(
        Markerdata(location = LatLng(23.1298940,79.8743948), title = "GGITS Canteen",description = null),
        Markerdata(location = LatLng(23.1280742,79.8759072), title = "GGCT Parking",description = null),
        Markerdata(location = LatLng(23.1294854,79.8749191), title = "GGITS Parking",description = null),
        Markerdata(location = LatLng(23.1283215,79.8749902), title = "GGCT Canteen",description = null),
        Markerdata(location = LatLng(23.1274721,79.8762582), title = "GGCT COLLEGE",description = null),
        Markerdata(location = LatLng(23.127083,79.875940), title = "GGCE COLLEGE",description = null),
        Markerdata(location = LatLng(23.129189,79.874389), title = "Fees counter",description = null),
        Markerdata(location = LatLng(23.128580,79.874751), title = "Spandan",description = null),
        Markerdata(location = LatLng(23.1284004,79.8757624), title = "MBA Department",description = null)

    )




    // FusedLocationProviderClient to get current location
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    // State to store the current location of the user
    var currentLocation by remember { mutableStateOf<LatLng?>(null) }

    // Coroutine to fetch the current location
    LaunchedEffect(key1 = locationPermission.allPermissionsGranted) {
        if (locationPermission.allPermissionsGranted) {
            try {
                // Get the last known location from FusedLocationProviderClient
                fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                    location?.let {
                        // Update the current location state
                        currentLocation = LatLng(it.latitude, it.longitude)

                        // Update the camera position to center the map on the current location
                        cameraPositionState.position = CameraPosition.fromLatLngZoom(currentLocation!!, 15f)
                    }
                }
            } catch (e: SecurityException) {
                // Handle security exception if permissions are not granted
            }
        }
    }


    // Display the Google Map with current location and marker
    GoogleMap(
        cameraPositionState = cameraPositionState,
        uiSettings = uiSettings,
        properties = mapProperties,
    ) {
        // If current location is available, show the marker
        currentLocation?.let {
            Marker(
                state = MarkerState(position = it),
                title = "Current Location",
                snippet = "You are here",
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
            )
    for(data in markerdata){
        MarkerInfoWindow(
            state = MarkerState(position = data.location),
            title = data.title,
            snippet = data.description
        )
    } }
}
}

