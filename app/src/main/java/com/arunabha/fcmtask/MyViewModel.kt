package com.arunabha.fcmtask

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MyViewModel : ViewModel() {
    private val _token = MutableStateFlow("")
    val token: StateFlow<String> get() = _token

    init {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if(task.isSuccessful){
                _token.value = task.result ?: ""
                Log.d("fcmToken", _token.value)
            }
        }
    }
}