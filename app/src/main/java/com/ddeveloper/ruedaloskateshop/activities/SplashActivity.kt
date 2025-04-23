package com.ddeveloper.ruedaloskateshop.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.ddeveloper.ruedaloskateshop.R
import com.bumptech.glide.Glide


class SplashActivity: AppCompatActivity() {

    //ACTIVITY VARIABLES
    private lateinit var loader_gif: ImageView
    private val SPLASH_TIME_OUT: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        //INICIALIZACIÓN DE VARIABLES
        loader_gif = findViewById(R.id.Iv_loader)

//VERIFICACIÓN DE INICIALIZACIÓN DE ACTIVITY SPLASH
Log.d("SplashActiviy", "onCreate: Iniciando Activity Splash")

        Glide.with(this)
            .asGif()
            .load(R.drawable.loader)
            .into(loader_gif)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_TIME_OUT)
    }
}