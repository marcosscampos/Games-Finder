package br.edu.infnet.gamesfinder.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.edu.infnet.gamesfinder.R
import br.edu.infnet.gamesfinder.service.util.CheckPassword
import br.edu.infnet.gamesfinder.view.activities.LoginActivity
import br.edu.infnet.gamesfinder.viewModel.LoginViewModel
import com.google.android.material.textfield.TextInputEditText

class PasswordFragment : Fragment() {
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let { act ->
            loginViewModel = ViewModelProviders.of(act).get(LoginViewModel::class.java)
        }

        setPassword(view)
    }

    private fun setPassword(view: View) {
        val txtPassword = view.findViewById<TextInputEditText>(R.id.txtPassword1)
        val txtConfirmPassword = view.findViewById<TextInputEditText>(R.id.txtPassword2)
        val btnFinish = view.findViewById<Button>(R.id.btnPassword)

        val checkPassword =
            CheckPassword(txtPassword.text.toString(), txtConfirmPassword.text.toString())

        btnFinish.setOnClickListener {
            if (checkPassword) {
                loginViewModel.password = txtPassword.text.toString()

                activity?.let {
                    val intent = Intent(it, LoginActivity::class.java)
                    loginViewModel.email.observe(viewLifecycleOwner, Observer {
                        intent.putExtra("email", it)
                    })
                    loginViewModel.name.observe(viewLifecycleOwner, Observer {
                        intent.putExtra("name", it)
                    })
                    intent.putExtra("password", loginViewModel.password)
                    it.startActivity(intent)
                    activity?.finish()
                }
            } else {
                Toast.makeText(context, "Passwords must be identical.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}