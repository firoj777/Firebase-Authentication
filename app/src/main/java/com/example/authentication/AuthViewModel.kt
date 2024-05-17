package com.example.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel: ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    fun signup(email:String, password:String): LiveData<String> {
        val result = MutableLiveData<String>()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    result.value = "Sign up successful"
                }else{
                    result.value = "Sign up failed: ${it.exception?.message}"
                }
            }
        return result
    }
    fun signIn(email:String, password: String):LiveData<String>{
        val result = MutableLiveData<String>()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    result.value = "Sign in successful"
                }else{
                    result.value = "Sign in failed: ${it.exception?.message}"
                }
            }
        return result
    }

    fun signout(){
        auth.signOut()
    }
}
