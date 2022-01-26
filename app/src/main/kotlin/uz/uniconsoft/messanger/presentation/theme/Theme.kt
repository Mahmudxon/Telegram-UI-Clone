package uz.uniconsoft.messanger.presentation.theme

import android.content.Context
import androidx.annotation.DrawableRes
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
            chatBackgroundColor = Color(0xFFCAD4B1),
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
        ),
        Theme(
            id = Theme.defDark,
            name = "Dark",
            contentBackgroundColor = Color(0xFF222B34),
            windowBackground = Color(0xFFDEE2E7),
            appbarBackgroundColor = Color(0xFF517DA2),
            chatBackgroundColor = Color.Black,
            chatBackground = R.drawable.chat_background_dark,
            ownChatBackgroundColor = Color(0xFFEFFEDD),
            partnerChatBackgroundColor = Color.White,
            primaryTextColor = Color(0xFFEFEFEF),
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
    )

    private var checkedIndex = 0

    var currentTheme = mutableStateOf(themes[checkedIndex])


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