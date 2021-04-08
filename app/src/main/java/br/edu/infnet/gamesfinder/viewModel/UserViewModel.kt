package br.edu.infnet.gamesfinder.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    var user: MutableLiveData<String> = MutableLiveData<String>().apply { value }
}