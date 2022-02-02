package uz.uniconsoft.messanger.presentation.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import dagger.hilt.android.AndroidEntryPoint
import uz.uniconsoft.messanger.business.domain.util.Device
import uz.uniconsoft.messanger.business.domain.util.getDeviceType
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
            if (LocalContext.current.getDeviceType() == Device.Type.Phone)
                ChatScreen(navController = navController, theme = theme)
            else ChatScreenTablet(theme = theme)
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


@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun ChatScreenTablet(theme: Theme) {
    Row {
        val index = remember {
            mutableStateOf(-1)
        }
        Column(modifier = Modifier.weight(3f)) {
            ChatScreen(theme = theme, index = index)
        }
        Column(modifier = Modifier.weight(5f)) {
            Box {
                Image(
                    painter = painterResource(id = theme.chatBackground),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillWidth
                )
                if (index.value >= 0)
                    ChatDetailScreen(index = index.value, theme = theme)
            }
        }
    }
}