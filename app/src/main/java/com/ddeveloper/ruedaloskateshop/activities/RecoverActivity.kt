package com.ddeveloper.ruedaloskateshop.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ddeveloper.ruedaloskateshop.R

class RecoverActivity: AppCompatActivity()  {
    // VARIABLES
    private lateinit var email_recover : EditText
    private lateinit var send_button : Button
    //SHARED PREFERENCES
    private lateinit var sharedPreferences: SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recover)

        sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE)

        //BINDING
        email_recover = findViewById(R.id.email_recovery_field)
        send_button = findViewById(R.id.send_button)

        //BUTTON CLICK
        send_button.setOnClickListener {
            if(valEmail()){
                checkEmail()

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }

    private fun valEmail(): Boolean{
        val email = email_recover.text.toString().trim()

        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            email_recover.error = "Este campo es obligatorio"
            return false
        }

        return true
    }

    private fun checkEmail(){
        val email = email_recover.text.toString().trim()
        val email_registered = sharedPreferences.getString("email", "")

        if (email == email_registered) {
            Toast.makeText(this, "Se ha enviado un correo a $email", Toast.LENGTH_SHORT).show()

            send_button.postDelayed({
                finish()
            }, 1500)

        }else{
            Toast.makeText(this, "Ups, algo salió mal", Toast.LENGTH_SHORT).show()
        }


    }


















}