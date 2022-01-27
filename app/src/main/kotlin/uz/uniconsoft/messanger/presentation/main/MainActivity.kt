package uz.uniconsoft.messanger.presentation.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.uniconsoft.messanger.presentation.main.screens.ChatDetailScreen
import uz.uniconsoft.messanger.presentation.main.screens.ChatScreen
import uz.uniconsoft.messanger.presentation.main.screens.SettingScreen
import uz.uniconsoft.messanger.presentation.theme.TelegramCloneTheme
import uz.uniconsoft.messanger.presentation.theme.Theme
import uz.uniconsoft.messanger.presentation.theme.ThemeManger
import javax.inject.Inject

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var themeManger: ThemeManger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TelegramCloneTheme {
                val theme = themeManger.currentTheme.value
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()

                    CompositionLocalProvider(Router provides navController) {
                        MainScreen(theme = theme)
                    }
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun MainScreen(theme: Theme) {
    val navController = Router.current
    NavHost(navController = navController, startDestination = Routes.Chat.route) {

        // First route : Chat
        composable(Routes.Chat.route) {

            // Lay down the Home Composable
            // and pass the navController
            ChatScreen(navController = navController, theme = theme)
        }

        composable(Routes.Setting.route) {

            // Lay down the Home Composable
            // and pass the navController
            SettingScreen()
        }

        // Another Route : Chat detail
        composable(Routes.ChatDetail.route + "/{id}") {
            val id = it.arguments?.getInt("id")
            ChatDetailScreen(index = id ?: 0)
        }
    }
}