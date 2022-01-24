package uz.uniconsoft.messanger.presentation.theme

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import dagger.hilt.android.qualifiers.ApplicationContext
import uz.uniconsoft.messanger.R
import uz.uniconsoft.messanger.business.datasource.datastore.AppDataStore
import javax.inject.Inject

data class Theme(
    val id: String,
    val name: String,
    // Background
    val contentBackgroundColor: Color,
    val windowBackground: Color,
    val appbarBackgroundColor: Color,
    val chatBackgroundColor: Color,
    @DrawableRes
    val chatBackground: Int? = null,
    val ownChatBackgroundColor: Color,
    val partnerChatBackgroundColor: Color,

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
    val isDark: Boolean
) {
    companion object {
        const val defLight = "light"
        const val defDark = "dark"
    }
}


class ThemeManger @Inject constructor(
    @ApplicationContext ctx: Context,
    private val store: AppDataStore
) {
    private val themes = ArrayList<Theme>()

    init {
        val defaultLightTheme = Theme(
            id = Theme.defLight,
            name = "Light",
            contentBackgroundColor = Color.White,
            windowBackground = Color(0xFFDEE2E7),
            appbarBackgroundColor = Color(0xFF517DA2),
            chatBackgroundColor = Color.White,
            chatBackground = R.drawable.chat_background_light,
            ownChatBackgroundColor = Color(0xFFEFFEDD),
            partnerChatBackgroundColor = Color.White,
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
            isDark = false
        )
        val defaultDarkTheme = Theme(
            id = Theme.defDark,
            name = "Dark",
            contentBackgroundColor = Color.White,
            windowBackground = Color(0xFFDEE2E7),
            appbarBackgroundColor = Color(0xFF517DA2),
            chatBackgroundColor = Color.White,
            chatBackground = R.drawable.chat_background_light,
            ownChatBackgroundColor = Color(0xFFEFFEDD),
            partnerChatBackgroundColor = Color.White,
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
            isDark = true
        )
        themes.add(defaultLightTheme)
        themes.add(defaultDarkTheme)
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
fun AppTheme(
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