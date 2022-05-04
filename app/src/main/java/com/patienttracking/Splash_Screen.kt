package com.patienttracking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.messaging.FirebaseMessaging
import com.patienttracking.services.SharedPreference


class Splash_Screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        val sp = SharedPreference(this)

        //checkSharedPreferences(sp);

        Handler(Looper.getMainLooper()).postDelayed({
            var isNew = sp.getPreference("isNew")
            if(isNew == "false"){
                var isLoggedIn = sp.getPreference("isLoggedIn")
                if(isLoggedIn == "true"){
                    val intent = Intent(this, Home::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val intent = Intent(this, Login::class.java)
                    startActivity(intent)
                    finish()
                }
            } else {
                sp.setPreference("isNew", "false")
                val intent = Intent(this, Welcome::class.java)
                startActivity(intent)
                finish()
            }
        }, 3000) // 3000 is the delayed time in milliseconds.

        FirebaseMessaging.getInstance().token.addOnCompleteListener { task: Task<String> ->
            if (!task.isSuccessful) {
                return@addOnCompleteListener
            }

            var pushToken = task.result

            Toast.makeText(baseContext, pushToken, Toast.LENGTH_SHORT).show()
            Log.i("PUSH_TOKEN", "*****************************")
            Log.i("PUSH_TOKEN", "*****************************")
            Log.i("PUSH_TOKEN", "*****************************")
            Log.i("PUSH_TOKEN", "*****************************")
            Log.i("PUSH_TOKEN", "*****************************")
            Log.i("PUSH_TOKEN", "pushToken: $pushToken")
            Log.i("PUSH_TOKEN", "*****************************")
            Log.i("PUSH_TOKEN", "*****************************")
            Log.i("PUSH_TOKEN", "*****************************")
            Log.i("PUSH_TOKEN", "*****************************")
            Log.i("PUSH_TOKEN", "*****************************")
        }
    }
}