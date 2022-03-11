package uz.uniconsoft.messanger.presentation.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.uniconsoft.messanger.business.domain.util.Device
import uz.uniconsoft.messanger.business.domain.util.getDeviceType
import uz.uniconsoft.messanger.business.domain.util.getScreenOrientation
import uz.uniconsoft.messanger.presentation.component.CircularReveal
import uz.uniconsoft.messanger.presentation.main.screens.*
import uz.uniconsoft.messanger.presentation.theme.TelegramCloneTheme
import uz.uniconsoft.messanger.presentation.theme.Theme
import uz.uniconsoft.messanger.presentation.theme.ThemeManger
import javax.inject.Inject

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var themeManger: ThemeManger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemTheme = themeManger.currentTheme.value
            val uiController = rememberSystemUiController()
            val index = remember {
                mutableStateOf(-1)
            }
            val scaffoldState = rememberScaffoldState()
            val navController = rememberNavController()
            ProvideWindowInsets {
                CircularReveal(
                    targetState = systemTheme,
                    animationSpec = tween(durationMillis = 700)
                ) { theme ->
                    Box(
                        modifier = Modifier
                            .background(theme.appbarBackgroundColor)
                            .statusBarsPadding()
                    ) {
                        TelegramCloneTheme(theme.isDark) {
                            uiController.setNavigationBarColor(theme.windowBackground)
                            Column(
                                modifier = Modifier
                                    .background(theme.windowBackground)
                                    .navigationBarsWithImePadding(),
                            ) {
                                CompositionLocalProvider(Router provides navController) {
                                    MainScreen(
                                        theme = theme,
                                        isTabletLandCape = getDeviceType() == Device.Type.Tablet && getScreenOrientation() == Device.Screen.Orientation.Landscape,
                                        scaffoldState = scaffoldState,
                                        index = index
                                    )
                                }
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
fun MainScreen(
    theme: Theme,
    isTabletLandCape: Boolean,
    scaffoldState: ScaffoldState,
    index: MutableState<Int>
) {
    val navController = Router.current

    NavHost(navController = navController, startDestination = Routes.Chat.route) {

        composable(Routes.Chat.route) {
            if (isTabletLandCape)
                ChatScreenToTablet(theme = theme, scaffoldState = scaffoldState, index = index)
                {
                    if (index.value >= 0)
                        ChatDetailScreen(index = index.value, theme = theme)
                }
            else {
                ChatScreen(
                    navController = navController,
                    theme = theme,
                    scaffoldState = scaffoldState
                )
            }
        }

        if (isTabletLandCape)
            dialog(Routes.Setting.route) {
                Card {
                    SettingScreen(
                        theme = theme, modifier = Modifier
                            .width(600.dp)
                            .height(600.dp)
                            .background(theme.windowBackground)
                    )
                }
                CloseDrawer(scaffoldState = scaffoldState)
            }
        else
            composable(Routes.Setting.route) {
                SettingScreen(theme = theme)
                CloseDrawer(scaffoldState = scaffoldState)
            }

        composable(Routes.ChatDetail.route + "/{id}") {
            val id = it.arguments?.getInt("id")
            ChatDetailScreen(index = id ?: 0, theme = theme)
        }

        if (isTabletLandCape)
            dialog(Routes.Contact.route) {
                Card {
                    ContactScreen(
                        theme = theme, modifier = Modifier
                            .width(600.dp)
                            .height(600.dp)
                            .background(theme.windowBackground)
                    )
                }
                CloseDrawer(scaffoldState = scaffoldState)
            }
        else
            composable(Routes.Contact.route) {
                ContactScreen(theme = theme)
                CloseDrawer(scaffoldState = scaffoldState)
            }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CloseDrawer(scaffoldState: ScaffoldState) {
    val scope = rememberCoroutineScope()
    scope.launch {
        scaffoldState.drawerState.close()
    }
}