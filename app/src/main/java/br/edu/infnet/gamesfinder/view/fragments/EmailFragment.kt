package br.edu.infnet.gamesfinder.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import br.edu.infnet.gamesfinder.R
import br.edu.infnet.gamesfinder.viewModel.LoginViewModel
import com.google.android.material.textfield.TextInputEditText

class EmailFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_email, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let { act ->
            loginViewModel = ViewModelProviders.of(act).get(LoginViewModel::class.java)
        }

        setUpEmail(view)
    }

    private fun setUpEmail(view: View) {
        val btnEmail = view.findViewById<Button>(R.id.btnEmail)
        var txtEmail = view.findViewById<TextInputEditText>(R.id.txtEmail)

        btnEmail.setOnClickListener {
            loginViewModel.email.value = txtEmail.text.toString()

            if (loginViewModel.email.value != null) {
                findNavController().navigate(R.id.passwordFragment, null)
            } else {
                Toast.makeText(activity, "The email field is mandatory.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}