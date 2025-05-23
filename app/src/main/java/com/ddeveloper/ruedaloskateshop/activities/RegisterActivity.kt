package com.ddeveloper.ruedaloskateshop.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.ddeveloper.ruedaloskateshop.R

class RegisterActivity : AppCompatActivity() {

    // VARIABLES
    private lateinit var nameText: EditText
    private lateinit var lastnameText: EditText
    private lateinit var emailText: EditText
    private lateinit var telText: EditText
    private lateinit var passwordText: EditText
    private lateinit var repPasswordText: EditText
    private lateinit var termsCheck: CheckBox
    private lateinit var joinButton: Button
    private lateinit var textLogin: TextView
    private lateinit var seeTerms: TextView

    // SAVE USER INFO
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // INICIALIZAR VARIABLES
        sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE)

        nameText = findViewById(R.id.name_field)
        lastnameText = findViewById(R.id.lastname_field)
        emailText = findViewById(R.id.email_field)
        telText = findViewById(R.id.tel_field)
        passwordText = findViewById(R.id.password_field)
        repPasswordText = findViewById(R.id.rep_password_field)
        termsCheck = findViewById(R.id.terms_check)
        joinButton = findViewById(R.id.join_button)
        textLogin = findViewById(R.id.textLogin)
        seeTerms = findViewById(R.id.terms_conditions)

        // Abrir actividad de login
        textLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Mostrar términos y condiciones
        seeTerms.setOnClickListener {
            showTermsDialog()
        }

        // Registro
        joinButton.setOnClickListener {
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

        if (name_ver.isEmpty()) {
            Toast.makeText(this, "El campo nombre es obligatorio", Toast.LENGTH_SHORT).show()
            return false
        }
        if (lastname_ver.isEmpty()) {
            Toast.makeText(this, "El campo apellidos es obligatorio", Toast.LENGTH_SHORT).show()
            return false
        }
        if (email_ver.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email_ver).matches()) {
            Toast.makeText(this, "El campo email es obligatorio o inválido", Toast.LENGTH_SHORT).show()
            return false
        }
        if (tel_ver.isEmpty()) {
            Toast.makeText(this, "El campo teléfono es obligatorio", Toast.LENGTH_SHORT).show()
            return false
        }
        if (pass_ver.isEmpty()) {
            Toast.makeText(this, "El campo contraseña es obligatorio", Toast.LENGTH_SHORT).show()
            return false
        }
        if (rep_pass_ver.isEmpty()) {
            Toast.makeText(this, "El campo repetir contraseña es obligatorio", Toast.LENGTH_SHORT).show()
            return false
        }
        if (pass_ver != rep_pass_ver) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!terms_ver) {
            Toast.makeText(this, "Debes aceptar los términos y condiciones", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun saveUserData() {
        val editor = sharedPreferences.edit()
        editor.putString("nombre", nameText.text.toString().trim())
        editor.putString("apellidos", lastnameText.text.toString().trim())
        editor.putString("email", emailText.text.toString().trim())
        editor.putString("telefono", telText.text.toString().trim())
        editor.putString("contraseña", passwordText.text.toString().trim())
        editor.putString("rep_contraseña", repPasswordText.text.toString().trim())
        editor.apply()

        Toast.makeText(this, "Te has registrado con éxito", Toast.LENGTH_SHORT).show()
    }

    // MOSTRAR POPUP DE TÉRMINOS Y CONDICIONES
    private fun showTermsDialog() {
        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.dialog_terms, null)
        val textView = view.findViewById<TextView>(R.id.textTerms)

        val inputStream = resources.openRawResource(R.raw.terminos_y_condiciones_ruedalo)
        val text = inputStream.bufferedReader().use { it.readText() }

        textView.text = text

        builder.setView(view)
            .setTitle("Términos y Condiciones")
            .setPositiveButton("Aceptar") { dialog, _ -> dialog.dismiss() }
            .setCancelable(true)
            .create()
            .show()
    }
}
