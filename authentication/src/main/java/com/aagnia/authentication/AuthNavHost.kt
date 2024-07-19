package com.aagnia.authentication

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AuthNavHost(navController: NavHostController, facebookLoginHandler: FacebookLoginHandler) {
    NavHost(navController = navController, startDestination = NavRoutes.SignIn) {
        composable(NavRoutes.SignIn) {
            SignInView(navController, facebookLoginHandler)
        }
        composable(NavRoutes.SignUp) {
            SignUpView(navController)
        }
    }
}