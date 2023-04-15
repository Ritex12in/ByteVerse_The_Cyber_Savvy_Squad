package com.example.trashtracker.firebase

import com.example.trashtracker.contants.Constants
import com.example.trashtracker.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

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
}