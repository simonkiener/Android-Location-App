package ch.bfh.cas.mad.location_app

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.location.Location
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : ComponentActivity() {

    private lateinit var textViewLocation: TextView
    private lateinit var buttonGetLocation: Button
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        textViewLocation = findViewById(R.id.textView_location)
        buttonGetLocation = findViewById(R.id.button_location)


        buttonGetLocation.setOnClickListener {
            getLocation()
        }
    }



    private fun getLocation() {
        if(checkSelfPermission(ACCESS_FINE_LOCATION) != PERMISSION_GRANTED) {
            requestLocationPermission()
        } else {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    if(location != null) {
                        textViewLocation.text = location.toString()
                    } else {
                        textViewLocation.text = "No location available"
                    }
                }
        }
    }

    private fun requestLocationPermission() {
        requestPermissions(arrayOf(ACCESS_FINE_LOCATION), 1)
    }
}

