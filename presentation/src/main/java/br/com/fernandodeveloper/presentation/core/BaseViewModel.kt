package br.com.fernandodeveloper.presentation.br.com.fernandodeveloper.presentation.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


open class BaseViewModel : ViewModel() {

    fun launchSuspend(block: suspend () -> Unit) {
        viewModelScope.launch {
            block()
        }
    }
}