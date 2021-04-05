package br.edu.infnet.gamesfinder.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.edu.infnet.gamesfinder.R

class UserFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val txtUser = view.findViewById<TextView>(R.id.txtLoggedUser)
        val name = arguments?.get("name")
        if (name == null) {
            txtUser.text = getString(R.string.error_on_show)
        } else {
            txtUser.text = name.toString()
        }
    }
}