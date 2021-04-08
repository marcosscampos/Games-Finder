package br.edu.infnet.gamesfinder.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import br.edu.infnet.gamesfinder.R
import br.edu.infnet.gamesfinder.service.util.CheckPassword

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email = intent.getStringExtra("email")

        if (email != null) {
            findViewById<EditText>(R.id.editEmail).setText(email.toString())
        }
    }

    fun register(view: View) {
        val register = Intent(this, RegisterActivity::class.java)
        startActivity(register)
    }

    fun login(view: View) {
        val loginPassword = findViewById<EditText>(R.id.editPassword).text.toString()
        val password = intent.getStringExtra("password").toString()
        val name = intent.getStringExtra("name").toString()

        val checkPassword = CheckPassword(loginPassword, password)
        if (checkPassword) {
            val login = Intent(this, MainActivity::class.java)
            login.putExtra("name", name)
            startActivity(login)
        } else {
            Toast.makeText(baseContext, getString(R.string.invalid_password), Toast.LENGTH_SHORT)
                .show()
        }
    }
}