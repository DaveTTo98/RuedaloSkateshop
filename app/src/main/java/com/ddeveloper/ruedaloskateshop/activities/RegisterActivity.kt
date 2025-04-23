package com.ddeveloper.ruedaloskateshop.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.CheckBox
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ddeveloper.ruedaloskateshop.R
import org.intellij.lang.annotations.Pattern

class RegisterActivity: AppCompatActivity()  {

    //VARIABLES
    private lateinit var nameText: EditText
    private lateinit var lastnameText: EditText
    private lateinit var emailText: EditText
    private lateinit var telText: EditText
    private lateinit var passwordText: EditText
    private lateinit var repPasswordText: EditText
    private lateinit var termsCheck: CheckBox
    private lateinit var joinButton: Button
    private lateinit var textLogin: TextView

    //SAVE USER INFO

        private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        //INITIALIZE VARIABLES
        //USER DATA
        sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE)

        //USER FIELDS
        nameText = findViewById(R.id.name_field)
        lastnameText = findViewById(R.id.lastname_field)
        emailText = findViewById(R.id.email_field)
        telText = findViewById(R.id.email_field)
        passwordText = findViewById(R.id.password_field)
        repPasswordText = findViewById(R.id.rep_password_field)
        termsCheck = findViewById(R.id.terms_check)
        joinButton = findViewById(R.id.join_button)
        textLogin = findViewById(R.id.textLogin)

        //LISTENER LOGIN TEXT
        textLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        joinButton.setOnClickListener{
            if (validateFields()) {
                saveUserData()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun validateFields(): Boolean {
        val name_ver = nameText.text.toString().trim()
        val lastname_ver = lastnameText.text.toString().trim()
        val email_ver = emailText.text.toString().trim()
        val tel_ver = telText.text.toString().trim()
        val pass_ver = passwordText.text.toString().trim()
        val rep_pass_ver = repPasswordText.text.toString().trim()
        val terms_ver = termsCheck.isChecked

        //VALIDATION FIELDS

        if (name_ver.isEmpty()){
            //show errror message
            Toast.makeText(this, "El campo nombre es obligatorio", Toast.LENGTH_SHORT).show()
            return false
        }
        if(lastname_ver.isEmpty()){
            Toast.makeText(this, "El campo apellidos es obligatorio", Toast.LENGTH_SHORT).show()
            return false
        }
        if (email_ver.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email_ver).matches()){
            Toast.makeText(this, "El campo email es obligatorio", Toast.LENGTH_SHORT).show()
            return false
        }
        if (tel_ver.isEmpty()){
            Toast.makeText(this, "El campo telefono es obligatorio", Toast.LENGTH_SHORT).show()
            return false
        }
        if(pass_ver.isEmpty()){
            Toast.makeText(this, "El campo contraseña es obligatorio", Toast.LENGTH_SHORT).show()
            return false
        }
        if(rep_pass_ver.isEmpty()){
            Toast.makeText(this, "El campo repetir contraseña es obligatorio", Toast.LENGTH_SHORT).show()
            return false
        }
        if(pass_ver != rep_pass_ver){
            Toast.makeText(this, "las contraseñas no cooinciden", Toast.LENGTH_SHORT).show()
            return false
        }

        if(!terms_ver){
            Toast.makeText(this, "Debes aceptar los términos y condiciones", Toast.LENGTH_SHORT).show()
            return false
        }
        return true

    }

    //USER DATA VALIDATION
    private fun saveUserData(){
        //edit user data
        val editor = sharedPreferences.edit()
        editor.putString("nombre", nameText.text.toString().trim())
        editor.putString("apellidos", lastnameText.text.toString().trim())
        editor.putString("email", emailText.text.toString().trim())
        editor.putString("telefono", telText.text.toString().trim())
        editor.putString("contraseña", passwordText.text.toString().trim())
        editor.putString("rep_contraseña", repPasswordText.text.toString().trim())
        editor.apply()

        Log.d("Register Activity", "saveUserData: Datos del usuario guardados ")

        //MENSAJE DE REGISTRO EXITOSO
        Toast.makeText(this, "Te has registrado con éxito", Toast.LENGTH_SHORT).show()

    }














}