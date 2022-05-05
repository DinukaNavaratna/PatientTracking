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
import com.patienttracking.services.DB_Helper
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
                val db = DB_Helper(this, null)
                db.createTable("CREATE TABLE reminders (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, body TEXT NOT NULL, time TEXT NOT NULL, next_time TEXT NOT NULL, status TEXT NOT NULL)")
                db.createTable("CREATE TABLE notes (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, body TEXT NOT NULL, time TEXT NOT NULL, status TEXT NOT NULL)")
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