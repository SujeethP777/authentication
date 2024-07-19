package com.aagnia.authentication

import android.content.Intent
import android.util.Log
import androidx.activity.ComponentActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult

class FacebookLoginHandler(private val activity: ComponentActivity, private val onLoginSuccess: () -> Unit) {

    val callbackManager: CallbackManager = CallbackManager.Factory.create()

    init {
        LoginManager.getInstance().registerCallback(callbackManager, object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult) {
                Log.d("SignInView", "Facebook AccessToken: ${result.accessToken.token}")
                onLoginSuccess()
            }

            override fun onCancel() {
                Log.d("SignInView", "Facebook login canceled")
            }

            override fun onError(error: FacebookException) {
                Log.e("SignInView", "Facebook login error", error)
            }
        })
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
}