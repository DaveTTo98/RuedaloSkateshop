package com.ddeveloper.ruedaloskateshop.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ddeveloper.ruedaloskateshop.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable

class LoginActivity: AppCompatActivity() {

    //VARIABLES
    private lateinit var googleButton: Button
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 123
    private val TAG = "GoogleSignIn"
    private lateinit var ridername: EditText
    private lateinit var riderpassword: EditText
    private lateinit var RegisterButton: TextView
    private lateinit var recoverButton: TextView
    private lateinit var loginButton: Button

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //CONFIGURAR GOOGLE SIGN IN
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestProfile()
            .build()

        //CREAR CLIENTE DE GOOGLE
        var mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        googleButton = findViewById(R.id.BTgoogleLogin)
        googleButton.setOnClickListener {
            signIn()
        }



        //INICIALIZAR VARIABLES
        ridername = findViewById(R.id.rider_field)
        riderpassword = findViewById(R.id.lg_password_field)
        riderpassword = findViewById(R.id.lg_password_field)
        RegisterButton = findViewById(R.id.create_here)
        recoverButton = findViewById(R.id.forgot_pasword)
        loginButton = findViewById(R.id.join_button)

        sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE)


        //CONFIGURACION DE LISTENER
        //REGISTRARSE
        RegisterButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
        //RECUPERAR CONTRASEÑA
        recoverButton.setOnClickListener{
            val intent = Intent(this, RecoverActivity::class.java)
            startActivity(intent)
            finish()
        }
        //INICIAR SESIÓN
        loginButton.setOnClickListener{
            if(nameVal()){
                checkPassword()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            }
        }

    }



    private fun nameVal(): Boolean{
        val nameCheker = sharedPreferences.getString("nombre", "")
        val name = ridername.text.toString().trim()



        if(name != nameCheker){
            Toast.makeText(this, "El usuario no existe", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun checkPassword(): Boolean {
        val password = riderpassword.text.toString().trim()
        val password_check = sharedPreferences.getString("contraseña", "")

        if(password != password_check){
            Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
            return false
        }
        return true

    }




}