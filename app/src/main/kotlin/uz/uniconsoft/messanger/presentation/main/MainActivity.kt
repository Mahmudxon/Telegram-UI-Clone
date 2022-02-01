package uz.uniconsoft.messanger.presentation.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
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
                ProvideWindowInsets {
                    val theme = themeManger.currentTheme.value
                    Box(
                        modifier = Modifier
                            .background(theme.appbarBackgroundColor)
                            .statusBarsPadding()
                    ) {
                        Column(
                            modifier = Modifier
                                .background(theme.windowBackground)
                                .navigationBarsWithImePadding(),
                        ) {
                                val navController = rememberNavController()
                                CompositionLocalProvider(Router provides navController) {
                                    MainScreen(theme = theme)
                                }
                        }
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

        composable(Routes.Chat.route) {
            ChatScreen(navController = navController, theme = theme)
        }

        composable(Routes.Setting.route) {
            SettingScreen(theme = theme)
        }

        composable(Routes.ChatDetail.route + "/{id}") {
            val id = it.arguments?.getInt("id")
            ChatDetailScreen(index = id ?: 0, theme = theme)
        }
    }
}