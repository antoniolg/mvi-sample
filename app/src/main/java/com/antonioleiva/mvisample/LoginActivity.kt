package com.antonioleiva.mvisample

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.addRepeatingJob
import com.antonioleiva.mvisample.common.visible
import com.antonioleiva.mvisample.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val vm by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            login.setOnClickListener {
                vm.doLogin(
                    username.text.toString(),
                    password.text.toString()
                )
            }
        }

        addRepeatingJob(Lifecycle.State.STARTED) {
            vm.viewState.collect(::render)
        }
    }

    private fun render(authViewState: AuthViewState) = with(binding) {
        loading.visible = authViewState.loading
        login.isEnabled = !authViewState.loading
        message.isVisible = authViewState.error != null
        authViewState.error?.let(message::setText)
    }
}