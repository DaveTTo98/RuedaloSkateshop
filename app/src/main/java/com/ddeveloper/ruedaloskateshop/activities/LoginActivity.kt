package com.ddeveloper.ruedaloskateshop.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ddeveloper.ruedaloskateshop.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

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

        //CREAR CLIENTE DE GOOGLE SIGIN
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        googleButton = findViewById(R.id.BTgoogleLogin)

        googleButton.setOnClickListener{
            sigIn()
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

    private fun sigIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent,RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == RC_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignResult(task)
        }
    }

    private fun handleSignResult(completedTask: Task<GoogleSignInAccount>){
        try{
            val account = completedTask.getResult(ApiException::class.java)

            //login exitoso
            Log.d(TAG, "signInSucces: ${account.displayName}" )
            Toast.makeText(this, "Bienvenido ${account.displayName}", Toast.LENGTH_SHORT).show()

            //IR A MAIN ACTIVITY
            intent = Intent(this, MainActivity::class.java)
            intent.putExtra("USER_EMAIL", account.email)
            intent.putExtra("USER_NAME", account.displayName)
            startActivity(intent)
        }catch (e: ApiException){
            //ERROR AL INICIAR SESION
            Log.e(TAG, "signInResult:failed code= ${e.statusCode}")

            val mensaje = when(e.statusCode){
                7 -> "Error de conexión. Revisa tu conexión a internet"
                10 -> "Error de configuración. Valida la huella SHA-1"
                1500 -> "Error de Googe Play Service"
                12501 ->"Error de sesión, cancelado por el usuario"
                else -> "Error al iniciar sesión(codigo: ${e.statusCode})"
            }
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        }
    }

    //FIELDS VALIDATORS

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