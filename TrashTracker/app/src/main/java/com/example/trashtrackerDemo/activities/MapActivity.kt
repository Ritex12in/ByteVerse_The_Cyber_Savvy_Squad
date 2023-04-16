package com.example.trashtrackerDemo.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.annotation.RawRes
import com.example.trashtrackerDemo.R
import com.example.trashtrackerDemo.contants.DustbinPosition
import com.example.trashtrackerDemo.contants.OfficePosition
import com.example.trashtrackerDemo.databinding.ActivityMapBinding
import com.example.trashtrackerDemo.utils.DustbinsCollection
import com.example.trashtrackerDemo.utils.GetLocation
import com.example.trashtrackerDemo.utils.OfficeCollection
import com.google.android.gms.common.api.Status
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.maps.android.heatmaps.HeatmapTileProvider
import com.google.maps.android.heatmaps.WeightedLatLng
import org.json.JSONArray
import org.json.JSONException
import java.util.*


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

        val heatMapProvider = HeatmapTileProvider.Builder()
            .weightedData(generateHeatMapData(R.raw.police_stations))
            .radius(20)
            .maxIntensity(1000.0)
            .build()
        mGoogleMap?.addTileOverlay(TileOverlayOptions().tileProvider(heatMapProvider))
        val currentLat = intent.getDoubleExtra("latitude",0.0)
        val currentLong = intent.getDoubleExtra("longitude",0.0)
        zoomOnMap(LatLng(currentLat,currentLong))


        val dustbins:ArrayList<DustbinPosition> = DustbinsCollection.getDustbinsList()
        val removeDustbin = ArrayList<Marker?>()
        var isDustbinMarked = true
        binding?.LocateDustbin?.setOnClickListener {
            if (isDustbinMarked)
            {
                removeDustbin.clear()
                for (i in dustbins)
                {
                    val location = LatLng(i.latitude,i.longitude)
                    val temp= mGoogleMap?.addMarker(
                        MarkerOptions()
                        .position(location)
                        .title("Dustbin")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.dustbin_24dp))
                    )
                    removeDustbin.add(temp)
                }
                isDustbinMarked = false
            }
            else
            {
                for (i in removeDustbin)
                {
                    i?.remove()
                }
                isDustbinMarked = true
            }
        }

        //Office Location
        val office:ArrayList<OfficePosition> = OfficeCollection.getOfficeList()
        val removeOffice = ArrayList<Marker?>()
        var isOfficeMarked = true
        binding?.LocateMunicipality?.setOnClickListener {
            if (isOfficeMarked)
            {
                removeOffice.clear()
                for (i in office)
                {
                    val location = LatLng(i.latitude,i.longitude)
                    val temp= mGoogleMap?.addMarker(
                        MarkerOptions()
                        .position(location)
                        .title("Municipality Office")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.office_24dp))
                    )
                    removeOffice.add(temp)
                }
                isOfficeMarked = false
            }
            else
            {
                for (i in removeOffice)
                {
                    i?.remove()
                }
                isOfficeMarked = true
            }
        }
    }

    private fun zoomOnMap(mLatLng:LatLng)
    {
        val newLatLngZoom = CameraUpdateFactory.newLatLngZoom(mLatLng,12f)
        mGoogleMap?.animateCamera(newLatLngZoom)
    }

    @Throws(JSONException::class)
    private fun generateHeatMapData(@RawRes resource: Int): List<WeightedLatLng?> {
        val result: MutableList<WeightedLatLng?> = ArrayList()
        val inputStream = this.resources.openRawResource(resource)
        val json = Scanner(inputStream).useDelimiter("\\A").next()
        val array = JSONArray(json)
        for (i in 0 until array.length()) {
            val `object` = array.getJSONObject(i)
            val lat = `object`.getDouble("lat")
            val lng = `object`.getDouble("lon")
            val intensity = `object`.getDouble("density")
            result.add(WeightedLatLng(LatLng(lat, lng), intensity))
        }
        return result
    }
}

