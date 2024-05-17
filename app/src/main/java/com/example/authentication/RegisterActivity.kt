package com.example.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.authentication.databinding.ActivityMainBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var viewModel:AuthViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        binding.registerBtn.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()
            val confirmPass = binding.conPassEt.text.toString()
            if(email.isEmpty() || password.isEmpty() || confirmPass.isEmpty()){
                Toast.makeText(this,"Please fill up all the fields!!!", Toast.LENGTH_SHORT).show()
            }else if(!password.equals(confirmPass)){
                Toast.makeText(this,"Password doesn't matched!!!", Toast.LENGTH_SHORT).show()
            }
            else{
                viewModel.signup(email, confirmPass).observe(this, {result->
                    Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
                    if(it.equals("Sign in successful")){
                        startActivity(Intent(this, HomeActivity::class.java))
                    }
                })

            }
        }
        binding.loginTxt.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }



    }
}