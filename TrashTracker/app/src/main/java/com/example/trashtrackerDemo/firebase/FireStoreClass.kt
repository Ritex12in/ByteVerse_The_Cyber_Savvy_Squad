package com.example.trashtrackerDemo.firebase

import android.widget.Toast
import com.example.trashtrackerDemo.activities.AddGarbageActivity
import com.example.trashtrackerDemo.contants.Constants
import com.example.trashtrackerDemo.models.Garbage
import com.example.trashtrackerDemo.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import java.text.SimpleDateFormat
import java.util.*

class FireStoreClass {

    private val mFireStore = FirebaseFirestore.getInstance()

    fun registerUser(userInfo: User)
    {
        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserId()).set(userInfo, SetOptions.merge())
    }

    fun getCurrentUserId(): String
    {
        val currentUser= FirebaseAuth.getInstance().currentUser
        var currentUserId = ""
        if (currentUser!=null)
            currentUserId = currentUser.uid
        return currentUserId
    }

    private fun updateTotalContribution()
    {
        val userRefer = mFireStore.collection(Constants.USERS).document(getCurrentUserId())
        userRefer.get().addOnSuccessListener {document->
            val contribution = document.get("contribution").toString().toLong()
            userRefer.update("contribution",contribution+1)
        }
    }

    fun addGarbage(activity:AddGarbageActivity ,garbage: Garbage)
    {
        mFireStore.collection(Constants.GARBAGE)
            .document().set(garbage, SetOptions.merge())
            .addOnCompleteListener{task->
                if (task.isSuccessful)
                {
                    mFireStore.collection(Constants.USERS)
                        .document(getCurrentUserId()).collection(Constants.GARBAGE).document().set(garbage, SetOptions.merge())
                    Toast.makeText(activity,"Garbage added",Toast.LENGTH_SHORT).show()
                    updateTotalContribution()
                    updateWasteTypeData(garbage.type!!)
                }
                else
                {
                    Toast.makeText(activity,"Failed to add, check network connection",Toast.LENGTH_SHORT).show()
                }
            }
    }
    private fun updateWasteTypeData(wasteType:String)
    {
        val dataRefer = FirebaseFirestore.getInstance().collection(Constants.CONSTANTS).document(Constants.WASTETYPEDATA)
        dataRefer.get().addOnSuccessListener { document ->
            val generalWaste = document.get("generalWaste").toString().toFloat()
            val plasticWaste = document.get("plasticWaste").toString().toFloat()
            if (wasteType == "plasticWaste")
                dataRefer.update("plasticWaste",plasticWaste+1)
            else
                dataRefer.update("generalWaste",generalWaste+1)
        }
    }

    fun addMonthData(value:Double)
    {
        val sdf = SimpleDateFormat("MM-yy")
        val currentDate = sdf.format(Date()).toString()
        val monthDataRefer = mFireStore.collection(Constants.CONSTANTS).document(Constants.MONTHWISEDATA)
        monthDataRefer.get().addOnCompleteListener {task->
            run {
                if (task.isSuccessful) {
                    val document: DocumentSnapshot = task.result
                    if (document.exists()) {
                        val dataMap: HashMap<String,Double> = document.get("MonthData") as HashMap<String, Double>
                        if (dataMap.containsKey(currentDate))
                            dataMap[currentDate] = dataMap[currentDate]?.plus(value)!!
                        else
                            dataMap[currentDate] = value

                        monthDataRefer.update("MonthData",dataMap)
                    }
                }
            }
        }
    }

    fun addYearData(value:Double)
    {
        val sdf = SimpleDateFormat("yyyy")
        val currentDate = sdf.format(Date()).toString()
        val monthDataRefer = mFireStore.collection(Constants.CONSTANTS).document(Constants.YEARWISEDATA)
        monthDataRefer.get().addOnCompleteListener {task->
            run {
                if (task.isSuccessful) {
                    val document: DocumentSnapshot = task.result
                    if (document.exists()) {
                        val dataMap: HashMap<String,Double> = document.get("YearData") as HashMap<String, Double>
                        if (dataMap.containsKey(currentDate))
                            dataMap[currentDate] = dataMap[currentDate]?.plus(value)!!
                        else
                            dataMap[currentDate] = value

                        monthDataRefer.update("YearData",dataMap)
                    }
                }
            }
        }
    }
    fun addWeekData(value:Double)
    {
        val sdf = SimpleDateFormat("dd-MM")
        val currentDate = sdf.format(Date()).toString()
        val monthDataRefer = mFireStore.collection(Constants.CONSTANTS).document(Constants.WEEKWISEDATA)
        monthDataRefer.get().addOnCompleteListener {task->
            run {
                if (task.isSuccessful) {
                    val document: DocumentSnapshot = task.result
                    if (document.exists()) {
                        val dataMap: HashMap<String,Double> = document.get("WeekData") as HashMap<String, Double>
                        if (dataMap.containsKey(currentDate))
                            dataMap[currentDate] = dataMap[currentDate]?.plus(value)!!
                        else
                            dataMap[currentDate] = value
                        monthDataRefer.update("WeekData",dataMap)
                    }
                }
            }
        }
    }
}