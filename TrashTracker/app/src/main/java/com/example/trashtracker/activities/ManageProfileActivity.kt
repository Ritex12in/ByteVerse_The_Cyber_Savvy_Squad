package com.example.trashtracker.activities

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trashtracker.R
import com.example.trashtracker.databinding.ActivityManageProfileBinding
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
        val storageRef = Firebase.storage.reference
        val pathReference = storageRef.child("profilephoto/profilepic1.png")
        val ONE_MEGABYTE: Long = 1024 * 1024
        pathReference.getBytes(ONE_MEGABYTE).addOnSuccessListener {byteArray->
            val bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)

            binding?.circleImageView?.setImageBitmap(bmp)
        }
    }
}
