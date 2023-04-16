package com.example.trashtrackerDemo.activities

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.trashtrackerDemo.contants.Constants
import com.example.trashtrackerDemo.databinding.ActivityManageProfileBinding
import com.example.trashtrackerDemo.firebase.FireStoreClass
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class ManageProfileActivity : AppCompatActivity() {
    private var binding: ActivityManageProfileBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManageProfileBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.ivBackBtnManageProfile?.setOnClickListener {
            finish()
        }
        setProfilePic()
    }
    private fun setProfilePic()
    {
        val mFireStore = FirebaseFirestore.getInstance()
        mFireStore.collection(Constants.USERS).document(FireStoreClass().getCurrentUserId()).get().addOnSuccessListener { document->
            val image = document.get("image").toString()
            Log.e("getImage",image)
            val storageRef = Firebase.storage.reference
            val pathReference = storageRef.child(image)
            val ONE_MEGABYTE: Long = 1024 * 1024
            pathReference.getBytes(ONE_MEGABYTE).addOnSuccessListener {byteArray->
                val bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)

                binding?.circleImageView?.setImageBitmap(bmp)
            }
        }
    }
}
