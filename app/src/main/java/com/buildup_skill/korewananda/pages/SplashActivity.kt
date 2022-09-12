package com.buildup_skill.korewananda.pages

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.annotation.RequiresApi
import com.buildup_skill.korewananda.R
import com.buildup_skill.korewananda.services.AlarmBroadcastReceiver

class SplashActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        AlarmBroadcastReceiver.startAlarmBroadcastReceiver(this)

        val splashTime: Long = 2000

        Handler().postDelayed({
            var intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
            finish()
        }, splashTime)
    }
}