package com.antonioleiva.mvisample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _viewState = MutableStateFlow(AuthViewState())
    val viewState: StateFlow<AuthViewState>
        get() = _viewState

    fun doLogin(email: String, pass: String) {
        viewModelScope.launch {
            _viewState.value = AuthViewState(true, null)
            delay(2000)
            _viewState.value = AuthViewState(false, "Test Error")
        }
    }
}