package uz.uniconsoft.messanger.presentation.theme

import uz.uniconsoft.messanger.R

abstract class Theme {
    abstract val id: Int
    abstract val name: Int
    abstract val style: Int
    abstract val isDark: Boolean

    /** System **/
    abstract val statusBarColor: Int
    abstract val statusBarColorV23: Int
    abstract val isLightStatusBar: Boolean
    abstract val navigationBarColor: Int
    abstract val navigationBarColorV28: Int
    abstract val isLightNavigationBar: Boolean
    /** End System **/

    /** Background **/
    abstract val backgroundColor: Int
    abstract val actionBackColor: Int
    abstract val chatBackColor: Int
    abstract val ownChatBack: Int
    abstract val othersChatBack: Int
    /** End Background **/

    /** Text & Icon **/
    abstract val defaultTextColor: Int
    abstract val actionBarTextColor: Int
    abstract val secondaryTextColor: Int
    abstract val specialTextColor: Int
    abstract val checkedTextColor: Int
    abstract val contentTextColor: Int

    /** End Text & Icon **/

    val transparent = R.color.transparent

    companion object {
        const val CLASSIC = 0
        const val NIGHT_MODE = -1
    }
}