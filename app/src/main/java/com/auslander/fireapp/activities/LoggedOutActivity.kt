package com.auslander.fireapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.auslander.fireapp.R

class LoggedOutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_out)
    }
}