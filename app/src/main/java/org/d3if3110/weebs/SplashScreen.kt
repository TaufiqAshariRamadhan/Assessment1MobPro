package org.d3if3110.weebs

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import org.d3if3110.weebs.databinding.ActivityMainBinding
import org.d3if3110.weebs.databinding.HomeBinding

class SplashScreen {

    class SplashScreen : AppCompatActivity() {
        private val SPLASH_TIME_OUT: Long = 3000 // 3 seconds
        private lateinit var binding: HomeBinding
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = HomeBinding.inflate(layoutInflater)
            setContentView(binding.root)
            Handler().postDelayed({
                startActivity(Intent(this, Introd::class.java))
                finish()
            }, SPLASH_TIME_OUT)
        }

    }
}