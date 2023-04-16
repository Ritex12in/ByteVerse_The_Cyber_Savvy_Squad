package com.example.trashtrackerDemo.utils

import android.content.Context
import android.location.Location
import android.location.LocationRequest
import android.os.Build
import android.os.Looper
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

class GetLocation(context: Context) {
    private var mLatitude:Double = 0.0
    private var mLongitude:Double = 0.0
    private var mFusedLocationClient: FusedLocationProviderClient

    fun getCurrentLatLng():LatLng
    {
        return LatLng(mLatitude, mLongitude)
    }

    private fun onLocationRequest(context: Context)
    {
        Dexter.withContext(context).withPermissions(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ).withListener(object : MultiplePermissionsListener {
            @RequiresApi(Build.VERSION_CODES.S)
            override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                report.let {
                    if (report!!.areAllPermissionsGranted())
                    {
                        getCurrentPosition()
                    }
                    else
                        Toast.makeText(context,"Permission not granted",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onPermissionRationaleShouldBeShown(
                permissions: MutableList<PermissionRequest>?,
                token: PermissionToken?
            ) {
                token?.continuePermissionRequest()
            }

        }).withErrorListener{
            Toast.makeText(context,"Some error occurred",Toast.LENGTH_SHORT).show()
        }.onSameThread().check()
    }

    @RequiresApi(Build.VERSION_CODES.S)
    @Suppress("MissingPermission")
    private fun getCurrentPosition(){
        val mLocationRequest = com.google.android.gms.location.LocationRequest()
        mLocationRequest.priority= LocationRequest.QUALITY_HIGH_ACCURACY
        mLocationRequest.interval = 1000
        mLocationRequest.numUpdates = 1
        mFusedLocationClient.requestLocationUpdates(mLocationRequest,mLocationCallBack, Looper.myLooper())
    }
    private val mLocationCallBack = object : LocationCallback()
    {
        override fun onLocationResult(locationResult: LocationResult) {
            val mLastLocation: Location? = locationResult.lastLocation
            if (mLastLocation!=null){
                mLatitude = mLastLocation.latitude
                mLongitude = mLastLocation.longitude
            }
        }
    }

    init {
        this.mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        onLocationRequest(context)
    }
}