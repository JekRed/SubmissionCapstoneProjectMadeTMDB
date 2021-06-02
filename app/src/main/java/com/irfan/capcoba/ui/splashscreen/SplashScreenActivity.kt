package com.irfan.capcoba.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.irfan.capcoba.databinding.ActivitySplashScreenBinding
import com.irfan.capcoba.ui.HomeActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var splashScreenBinding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashScreenBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(splashScreenBinding.root)

        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finishAffinity()
        }, 3000)
    }
}