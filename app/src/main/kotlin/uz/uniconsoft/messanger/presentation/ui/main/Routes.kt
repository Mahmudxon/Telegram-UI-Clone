package uz.uniconsoft.messanger.presentation.ui.main

import android.annotation.SuppressLint
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController

@SuppressLint("CompositionLocalNaming")
val Router = compositionLocalOf<NavHostController> { error("No active user found!") }

sealed class Routes(val route: String) {
    object Chat : Routes("chat")
    object ChatDetail : Routes("chat/detail")
    object Setting : Routes("setting")
    object Contact : Routes("contact")
}