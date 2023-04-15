package com.example.trashtracker.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import com.example.trashtracker.R
import com.example.trashtracker.databinding.ActivitySignUpBinding
import com.example.trashtracker.firebase.FireStoreClass
import com.example.trashtracker.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : BaseActivity() {

    private var binding:ActivitySignUpBinding? = null
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        auth = Firebase.auth

        binding?.tvLoginPage?.setOnClickListener {
            startActivity(Intent(this,SignInActivity::class.java))
            finish()
        }

        binding?.btnSignUp?.setOnClickListener { registerUser() }
    }

    override fun onBackPressed() {
        startActivity(Intent(this,SignInActivity::class.java))
        finish()
    }

    private fun registerUser()
    {
        val name = binding?.etSinUpName?.text.toString()
        val email = binding?.etSinUpEmail?.text.toString()
        val password = binding?.etSinUpPassword?.text.toString()
        if (validateForm(name, email, password))
        {
            showProgressBar()
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){task ->
                    if (task.isSuccessful)
                    {
                        val rndInt = (1..12).random()
                        val image = "profilephoto/profilepic$rndInt"
                        val firebaseUser: FirebaseUser? = task.result.user
                        val userInfo = User(firebaseUser?.uid,name,firebaseUser?.email,image)
                        FireStoreClass().registerUser(userInfo)
                        Toast.makeText(this,"Registration Successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    }
                    else
                    {
                        Toast.makeText(this,"Oops! Something went wrong", Toast.LENGTH_SHORT).show()
                    }
                    hideProgressBar()
                }
        }
    }
    private fun validateForm(name:String, email:String,password:String):Boolean
    {
        return when {
            TextUtils.isEmpty(name)->{
                binding?.tilName?.error = "Enter name"
                false
            }
            TextUtils.isEmpty(email) && !Patterns.EMAIL_ADDRESS.matcher(email).matches()->{
                binding?.tilEmail?.error = "Enter valid email address"
                false
            }
            TextUtils.isEmpty(password)->{
                binding?.tilPassword?.error = "Enter password"
                false
            }
            else -> { true }
        }
    }
}