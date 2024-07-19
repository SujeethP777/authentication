package com.aagnia.authentication

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.facebook.login.LoginManager
import com.stevdzasan.onetap.OneTapSignInWithGoogle
import com.stevdzasan.onetap.rememberOneTapSignInState

@Composable
fun SignUpView(navController: NavHostController){

    val darkBlue = Color(0xFF00008B)
    val orange = Color(0xffffA500)
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val state = rememberOneTapSignInState()
    val authenticated = remember { mutableStateOf(false) }
    val context = LocalContext.current

    OneTapSignInWithGoogle(
        state = state,
        clientId = "932603562148-7cmvif3ogq8s1mf6ro08t6c9i9ifteti.apps.googleusercontent.com",
        onTokenIdReceived = { tokenId ->
            authenticated.value = true
            Log.d("SignUpView", "Token Id: $tokenId")
        },
        onDialogDismissed = {
            Log.d("SignInView", it)
        },
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_back), 
                    contentDescription = null,
                    modifier = Modifier
                      .clickable { navController.navigate(NavRoutes.SignIn) }
                )
                }
            Text(
                text = stringResource(id = R.string.create_account),
                fontSize = 25.sp,
                fontWeight = FontWeight.W600,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .align(Alignment.CenterHorizontally),
                color = darkBlue
            )
            Spacer(modifier = Modifier.height(30.dp))

            CustomTextField(
                value = username,
                onValueChange = { username = it },
                placeholder = stringResource(id = R.string.username),
                leadingIconId = R.drawable.username,
                visualTransformation = VisualTransformation.None,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(15.dp))

            CustomTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = stringResource(id = R.string.email),
                leadingIconId = R.drawable.email,
                visualTransformation = VisualTransformation.None,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(15.dp))

            CustomTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = stringResource(id = R.string.password),
                leadingIconId = R.drawable.password,
                visualTransformation = StarVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(15.dp))

            CustomTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                placeholder = stringResource(id = R.string.confirm_password),
                leadingIconId = R.drawable.password,
                visualTransformation = StarVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(Color.Blue),
                shape = RectangleShape
            ) {
                Text(stringResource(id = R.string.register),color = Color.White)
            }

            Spacer(modifier = Modifier.height(15.dp))

            Column {
                Row {
                    Text(
                        text = stringResource(id = R.string.registering),
                        color = Color.Gray,
                    )

                    Text(text = " ")

                    Text(
                        text = stringResource(id = R.string.terms),
                        color = orange
                    )
                    Text(text = " ")
                }
                Row {
                    Text(
                        text = stringResource(id = R.string.use),
                        color = orange
                    )
                    Text(text = " ")
                    Text(
                        text = stringResource(id = R.string.and),
                        color = Color.Gray
                    )
                    Text(text = " ")
                    Text(
                        text = stringResource(id = R.string.privacy_policy),
                        color = orange
                    )
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            Button(
                onClick = { LoginManager.getInstance().logInWithReadPermissions(context as Activity, listOf("email", "public_profile")) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(Color.Blue),
                shape = RectangleShape
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.facebook),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(modifier = Modifier.weight(1f)) {
                        Text(
                            text = stringResource(id = R.string.sign_in_with_facebook),
                            color = Color.White,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = { state.open() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDB4437)),
                shape = RectangleShape
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(modifier = Modifier.weight(1f)) {
                        androidx.compose.material3.Text(
                            text = stringResource(id = R.string.sign_in_with_google),
                            color = Color.White,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row() {
                Text(
                    text = stringResource(id = R.string.have_an_account),
                    style = TextStyle(color = Color.DarkGray),
                    modifier = Modifier
                        .clickable { }
                )
                Text(
                    text = stringResource(id = R.string.sign_in),
                    style = TextStyle(color = Color.DarkGray),
                    color = Color.Blue,
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .clickable { navController.navigate(NavRoutes.SignIn) }
                )
            }
        }
    }
}