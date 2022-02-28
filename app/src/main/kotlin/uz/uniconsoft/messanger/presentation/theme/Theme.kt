package uz.uniconsoft.messanger.presentation.theme

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import dagger.hilt.android.qualifiers.ApplicationContext
import uz.uniconsoft.messanger.R
import javax.inject.Inject
import javax.inject.Singleton

data class Theme(
    val id: String,
    val name: String,
    // Background
    val contentBackgroundColor: Color,
    val windowBackground: Color,
    val appbarBackgroundColor: Color,
    val chatBackgroundColor: Color,
    @DrawableRes
    val chatBackground: Int,
    val ownChatBackgroundColor: Color,
    val partnerChatBackgroundColor: Color,
    val menuBackColor: Color,

    // Text & Icon
    val primaryTextColor: Color,
    val menuTextColor: Color,
    val menuIconColor: Color,
    val captionColor: Color,
    val titleTextColor: Color = Blue500,
    val appbarTextColor: Color,
    val appbarCaptionColor: Color,
    val chatTextColor: Color,
    val chatCaption: Color,
    val chatOwnCaption: Color,
    val isDark: Boolean,
    val linkColor: Color,
    val manager: ThemeManger
) {
    companion object {
        const val defLight = "light"
        const val defDark = "dark"
    }
}


@Singleton
class ThemeManger @Inject constructor(
    @ApplicationContext ctx: Context,
) {
    private val themes = arrayListOf(
        Theme(
            id = Theme.defLight,
            name = "Light",
            contentBackgroundColor = Color.White,
            windowBackground = Color(0xFFDEE2E7),
            appbarBackgroundColor = Color(0xFF517DA2),
            chatBackgroundColor = Color(0xffC7D9E9),
            chatBackground = R.drawable.chat_background_light,
            ownChatBackgroundColor = Color(0xFFEFFEDD),
            partnerChatBackgroundColor = Color.White,
            menuBackColor = Color.White,
            primaryTextColor = Color(0xFF222222),
            menuTextColor = Color(0xFF444444),
            menuIconColor = Color(0xFF889198),
            captionColor = Color(0xFF8D8E90),
            titleTextColor = Blue500,
            appbarTextColor = Color.White,
            appbarCaptionColor = Color(0xFFD2E9FB),
            chatTextColor = Color.Black,
            chatCaption = Color(0xFFA1AAB3),
            chatOwnCaption = Color(0xFF62AC55),
            isDark = false,
            linkColor = Blue700,
            manager = this
        ),
        Theme(
            id = Theme.defDark,
            name = "Dark",
            contentBackgroundColor = Color(0xFF222B34),
            windowBackground = Color(0xFF1d232f),
            appbarBackgroundColor = Color(0xFF222d3b),
            chatBackgroundColor = Color(0XFF151a28),
            chatBackground = R.drawable.chat_background_dark,
            ownChatBackgroundColor = Color(0xFF232e3a),
            partnerChatBackgroundColor = Color(0xFF232e3a),
            menuBackColor = Color(0xFF1d232f),
            primaryTextColor = Color(0xFFd8dce5),
            menuTextColor = Color(0xFFced3d8),
            menuIconColor = Color(0xFF6e7481),
            captionColor = Color(0xFFced4e0),
            titleTextColor = Color(0xFFf0f5fa),
            appbarTextColor = Color(0xFFf0f5fa),
            appbarCaptionColor = Color(0xFFced4e0),
            chatTextColor = Color(0xffd6dade),
            chatCaption = Color(0xFF535e6a),
            chatOwnCaption = Color(0xFF535e6a),
            isDark = true,
            linkColor = Blue200,
            manager = this
        )
    )

    private var checkedIndex = 0

    var currentTheme = mutableStateOf(themes[checkedIndex])


    var lastLightTheme = themes[0]
    var lastDarkTheme = themes[1]


    fun toggle() {
        if (currentTheme.value.isDark) {
            currentTheme.value = lastLightTheme
        } else {
            currentTheme.value = lastDarkTheme
        }
    }

    fun changeTheme(id: String) {
        for (i in 0 until themes.size) {
            if (themes[i].id == id) {
                checkedIndex = i
                currentTheme.value = themes[checkedIndex]
                return
            }
        }
    }

}


private val DarkColorPalette = darkColors(
    primary = Blue200,
    primaryVariant = Blue700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Blue500,
    primaryVariant = Blue700,
    secondary = Blue200
)

@Composable
fun TelegramCloneTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}