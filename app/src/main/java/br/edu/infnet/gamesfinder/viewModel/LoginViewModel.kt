package br.edu.infnet.gamesfinder.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    var name:  MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    var email: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    var password: String = ""
}