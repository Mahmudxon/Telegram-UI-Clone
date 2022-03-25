package uz.uniconsoft.messanger.presentation

import android.app.Application
import androidx.compose.runtime.CompositionLocalProvider
import dagger.hilt.android.HiltAndroidApp
import uz.uniconsoft.messanger.business.domain.util.LocalFileManager
import uz.uniconsoft.messanger.presentation.ui.main.Router

@HiltAndroidApp
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start app()

    }
}