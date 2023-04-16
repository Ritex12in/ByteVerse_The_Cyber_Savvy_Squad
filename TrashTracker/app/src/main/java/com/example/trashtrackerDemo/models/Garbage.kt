package com.example.trashtrackerDemo.models

import android.os.Parcel
import android.os.Parcelable

data class Garbage(
    val userId:String? = "",
    val type:String? = "",
    val date:String? = "",
    val amount:Double = 0.0,
    val latitude:Double = 0.0,
    val longitude:Double = 0.0
):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userId)
        parcel.writeString(type)
        parcel.writeString(date)
        parcel.writeDouble(amount)
        parcel.writeDouble(latitude)
        parcel.writeDouble(longitude)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Garbage> {
        override fun createFromParcel(parcel: Parcel): Garbage {
            return Garbage(parcel)
        }

        override fun newArray(size: Int): Array<Garbage?> {
            return arrayOfNulls(size)
        }
    }

}