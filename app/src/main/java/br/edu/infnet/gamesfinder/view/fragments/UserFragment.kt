package br.edu.infnet.gamesfinder.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import br.edu.infnet.gamesfinder.R
import br.edu.infnet.gamesfinder.viewModel.LoginViewModel
import br.edu.infnet.gamesfinder.viewModel.UserViewModel

class UserFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let { act ->
            userViewModel = ViewModelProviders.of(act).get(UserViewModel::class.java)
        }

        showUser(view)
    }

    private fun showUser(view: View) {
        val txtUser = view.findViewById<TextView>(R.id.txtLoggedUser)

        userViewModel.user.observe(viewLifecycleOwner, {
            if (it != null) {
                txtUser.text = it.toString()
            } else {
                txtUser.text = ""
            }
        })
    }
}