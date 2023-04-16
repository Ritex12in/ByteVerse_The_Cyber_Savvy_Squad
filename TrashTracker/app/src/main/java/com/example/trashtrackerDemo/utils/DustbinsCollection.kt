package com.example.trashtrackerDemo.utils

import com.example.trashtrackerDemo.contants.DustbinPosition

object DustbinsCollection {
    public fun getDustbinsList():ArrayList<DustbinPosition>
    {
        val listOfDustbins= ArrayList<DustbinPosition>()
        listOfDustbins.add(DustbinPosition(25.5940947,85.1375645))
        listOfDustbins.add(DustbinPosition(25.5970876,85.1275600))
        listOfDustbins.add(DustbinPosition(25.5940000,85.1375686))
        listOfDustbins.add(DustbinPosition(25.5930947,85.1275645))
        listOfDustbins.add(DustbinPosition(25.5140947,85.1275645))
        listOfDustbins.add(DustbinPosition(25.5910947,85.1375245))
        listOfDustbins.add(DustbinPosition(25.5240947,85.1379645))
        listOfDustbins.add(DustbinPosition(25.5040947,85.1334645))
        listOfDustbins.add(DustbinPosition(25.5610947,85.1975645))
        return listOfDustbins
    }
}