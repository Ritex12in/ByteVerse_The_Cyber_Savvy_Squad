package com.example.trashtrackerDemo.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.trashtrackerDemo.R
import com.example.trashtrackerDemo.databinding.ActivitySignInBinding
import com.example.trashtrackerDemo.firebase.FireStoreClass
import com.example.trashtrackerDemo.models.User
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInActivity : BaseActivity() {
    private var binding:ActivitySignInBinding? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        auth = Firebase.auth
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail().build()
        googleSignInClient = GoogleSignIn.getClient(this,gso)

        binding?.tvRegister?.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
            finish()
        }

        binding?.tvForgotPassword?.setOnClickListener {
            startActivity(Intent(this,ForgetPasswordActivity::class.java))
        }

        binding?.btnSignIn?.setOnClickListener {
            userLogin()
        }

        binding?.btnSignInWithGoogle?.setOnClickListener {
            sinInWithGoogle()
        }
    }

    private fun userLogin()
    {
        val email = binding?.etSinInEmail?.text.toString()
        val password = binding?.etSinInPassword?.text.toString()
        if (validateForm(email, password))
        {
            showProgressBar()
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){task->
                    if (task.isSuccessful)
                    {
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    }
                    else
                    {
                        binding?.btnSignIn?.text = "Login"
                        Toast.makeText(this,"Oops! Something went wrong", Toast.LENGTH_SHORT).show()
                    }
                    hideProgressBar()
                }
        }
    }

    private fun sinInWithGoogle()
    {
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    {result->
        if (result.resultCode == Activity.RESULT_OK)
        {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResults(task)
        }
    }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful)
        {
            val account: GoogleSignInAccount? = task.result
            if (account!=null) {
                updateUI(account)
            }
        }
        else
        {
            Toast.makeText(this,"Sign In Failed, try again.",Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        showProgressBar()
        val credential = GoogleAuthProvider.getCredential(account.idToken,null)
        auth.signInWithCredential(credential).addOnCompleteListener{
            if (it.isSuccessful)
            {
                val id = FireStoreClass().getCurrentUserId()
                val name = account.displayName.toString()
                val email = account.email.toString()
                val userInfo = User(id, name, email)
                FireStoreClass().registerUser(userInfo)
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }
            else
            {
                Toast.makeText(this,"Sign In Failed, try again.",Toast.LENGTH_SHORT).show()
            }
            hideProgressBar()
        }
    }

    private fun validateForm(email:String,password:String):Boolean
    {
        return when {
            TextUtils.isEmpty(email) && !Patterns.EMAIL_ADDRESS.matcher(email).matches()->{
                binding?.tilEmail?.error = "Enter valid email address"
                false
            }
            TextUtils.isEmpty(password)->{
                binding?.tilPassword?.error = "Enter password"
                binding?.tilEmail?.error = null
                false
            }
            else -> {
                binding?.tilEmail?.error = null
                binding?.tilPassword?.error = null
                true }
        }
    }
}