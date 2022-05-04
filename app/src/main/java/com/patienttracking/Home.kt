package com.patienttracking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)
    }

    fun openMedication(view: View) {
        val intent = Intent(this, Medication::class.java)
        startActivity(intent)
        finish()
    }

    fun openReminders(view: View) {
        val intent = Intent(this, Reminders::class.java)
        startActivity(intent)
        finish()
    }

    fun openHistory(view: View) {
        val intent = Intent(this, History::class.java)
        startActivity(intent)
        finish()
    }
}