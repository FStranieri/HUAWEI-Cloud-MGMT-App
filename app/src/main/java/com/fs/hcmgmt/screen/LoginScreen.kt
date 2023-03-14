package com.fs.hcmgmt.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fs.hcmgmt.R
import com.fs.hcmgmt.viewmodel.LoginViewModel

@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val loginViewModel: LoginViewModel = hiltViewModel()
        val loginState by loginViewModel.state.collectAsState()

        val username = remember { mutableStateOf(TextFieldValue()) }
        val usernameIAM = remember { mutableStateOf(TextFieldValue()) }
        val password = remember { mutableStateOf(TextFieldValue()) }

        Text(text = "HUAWEI CLOUD", style = TextStyle(fontSize = 40.sp))

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = stringResource(R.string.login_username)) },
            value = username.value,
            onValueChange = { username.value = it },
            keyboardOptions = KeyboardOptions(autoCorrect = true)
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (loginState.loginAsIAM) {
            TextField(
                label = { Text(text = stringResource(R.string.login_username_iam)) },
                value = usernameIAM.value,
                onValueChange = { usernameIAM.value = it },
                keyboardOptions = KeyboardOptions(autoCorrect = true)
            )

            Spacer(modifier = Modifier.height(20.dp))
        }

        TextField(
            label = { Text(text = stringResource(R.string.login_password)) },
            value = password.value,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { password.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {
                    if (loginState.loginAsIAM)
                        loginViewModel.loginIAM(
                            username.value.text,
                            usernameIAM.value.text,
                            password.value.text
                        )
                    else
                        loginViewModel.login(
                            username.value.text,
                            password.value.text
                        )
                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = stringResource(R.string.login_label_text))
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        ClickableText(
            text = AnnotatedString(
                if (loginState.loginAsIAM) stringResource(R.string.login_std_user_text) else stringResource(
                    R.string.login_std_user_iam
                )
            ),
            onClick = { loginViewModel.switchUI(!loginState.loginAsIAM) },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                color = Color.White
            )
        )
    }
}