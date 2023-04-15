package com.example.trashtracker.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import com.example.trashtracker.R
import com.example.trashtracker.databinding.ActivityMapBinding
import com.example.trashtracker.utils.GetLocation
import com.google.android.gms.common.api.Status
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener

class MapActivity : BaseActivity(), OnMapReadyCallback {

    private var binding:ActivityMapBinding? = null
    private var mGoogleMap: GoogleMap? = null
    private lateinit var currentLocation: GetLocation
    private lateinit var autocompleteFragment: AutocompleteSupportFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val w: Window = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        Places.initialize(applicationContext, getString(R.string.map_api_key))
        autocompleteFragment =
            supportFragmentManager.findFragmentById(R.id.autocomplete_fragment)
                    as AutocompleteSupportFragment
        autocompleteFragment.setPlaceFields(listOf(Place.Field.ID,Place.Field.ADDRESS,Place.Field.LAT_LNG))
        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place?) {
                val xLatitude = place?.latLng?.latitude!!
                val xLongitude = place?.latLng?.longitude!!
                zoomOnMap(LatLng(xLatitude,xLongitude))
            }

            override fun onError(status: Status) {
                showToast(this@MapActivity,"Some error occurred")
                Log.i("PlaceError", "An error occurred: $status")
            }
        })

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        currentLocation = GetLocation(this)
        binding?.myLocation?.setOnClickListener {
            zoomOnMap(currentLocation.getCurrentLatLng())
        }

        binding?.btnSettings?.setOnClickListener {
            startActivity(Intent(this,SettingsActivity::class.java))
            finish()
        }
        binding?.btnDashboard?.setOnClickListener {
            finish()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mGoogleMap = googleMap
        val currentLat = intent.getDoubleExtra("latitude",0.0)
        val currentLong = intent.getDoubleExtra("longitude",0.0)
        zoomOnMap(LatLng(currentLat,currentLong))
    }

    private fun zoomOnMap(mLatLng:LatLng)
    {
        val newLatLngZoom = CameraUpdateFactory.newLatLngZoom(mLatLng,12f)
        mGoogleMap?.animateCamera(newLatLngZoom)
    }
}

