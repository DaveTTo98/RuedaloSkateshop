package com.ddeveloper.ruedaloskateshop.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.ddeveloper.ruedaloskateshop.R

class HomeActivity: AppCompatActivity() {
    //VARIABLES
    private lateinit var LoginButton: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        Log.d("HomeActivity", "onCreate: Iniciando Activity Home")

        //INICIALIZACION DE VARIABLES
        LoginButton = findViewById(R.id.user_button)

        //CONFIGURACION DE LISTENER
        //REDIRECCIONAMIENTO HACIA EL LOGIN
        LoginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }


    }

}