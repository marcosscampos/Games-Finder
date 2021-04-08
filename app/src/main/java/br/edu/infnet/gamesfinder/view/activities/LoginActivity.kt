package br.edu.infnet.gamesfinder.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import br.edu.infnet.gamesfinder.R
import br.edu.infnet.gamesfinder.service.util.CheckPassword
import br.edu.infnet.gamesfinder.view.fragments.UserFragment
import br.edu.infnet.gamesfinder.viewModel.UserViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email = intent.getStringExtra("email")

        if(email != null) {
            findViewById<EditText>(R.id.editEmail).setText(email.toString())
        }
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        getLoggerUser(UserFragment())
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
        if(checkPassword) {
            val login = Intent(this, MainActivity::class.java)
            login.putExtra("name", name)
            startActivity(login)
        } else {
            Toast.makeText(baseContext, "Invalid Password.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getLoggerUser(user: UserFragment) {
        val bundle = Bundle()
        val name = intent.getStringExtra("name")
        userViewModel.user.value = name

        bundle.putString("name", userViewModel.user.value)
        user.setArguments(bundle)

        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, user)
            .commit()
    }
}