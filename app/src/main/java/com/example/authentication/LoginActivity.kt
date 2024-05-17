package com.example.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.authentication.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: AuthViewModel
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        binding.button.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()
            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(this,"Please fill up all the fields!!!", Toast.LENGTH_SHORT).show()
            }else{
                viewModel.signIn(email,password).observe(this, {
                    Toast.makeText(this,it, Toast.LENGTH_SHORT).show()
                    if(it.equals("Sign in successful")){
                        startActivity(Intent(this, HomeActivity::class.java))
                    }
                })


            }
        }
        binding.registerTxt.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }

    override fun onStart() {
        super.onStart()
        if(FirebaseAuth.getInstance().currentUser?.uid!=null){
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}