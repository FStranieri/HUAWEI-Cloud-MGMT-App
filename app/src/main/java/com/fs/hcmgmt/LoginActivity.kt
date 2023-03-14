package com.fs.hcmgmt

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.fs.hcmgmt.screen.LoginScreen
import com.fs.hcmgmt.ui.theme.ui.theme.HCMGMTTheme
import com.fs.hcmgmt.util.Result
import com.fs.hcmgmt.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val loginViewModel: LoginViewModel by viewModels()
            val loginState by loginViewModel.state.collectAsState()

            HCMGMTTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoginScreen(loginViewModel)
                }
            }

            if (loginState.result is Result.Success) {
                Toast.makeText(this, "SUCCESS", Toast.LENGTH_LONG).show()
            } else
            {
                Toast.makeText(this, "FAIL", Toast.LENGTH_LONG).show()
            }
        }
    }
}