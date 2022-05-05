package com.patienttracking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.patienttracking.services.SharedPreference

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)
    }

    fun openLogin(view: View) {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }

    fun openHome(view: View) {
        val sp = SharedPreference(this)
        sp.setPreference("isLoggedIn", "true")
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
        finish()
    }
}