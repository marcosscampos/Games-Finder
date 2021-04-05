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

class NameFragment : Fragment() {
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_name, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let { act ->
            loginViewModel = ViewModelProviders.of(act).get(LoginViewModel::class.java)
        }

        setUpNome(view)
    }

    private fun setUpNome(view: View) {
        val btnName = view.findViewById<Button>(R.id.btnName)
        val txtName = view.findViewById<TextInputEditText>(R.id.txtName)

        btnName.setOnClickListener {
            loginViewModel.name.value = txtName.text.toString()

            if (loginViewModel.name.value != null) {
                findNavController().navigate(R.id.emailFragment, null)
            } else {
                Toast.makeText(activity, "The name field is mandatory.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}