package uz.uniconsoft.messanger.presentation.util

import android.content.Context
import android.content.res.Resources
import uz.uniconsoft.messanger.R

object Device {

    object Screen {
        object Orientation {
            const val Portrait = "portrait"
            const val Landscape = "landscape"
        }

        val width: Int
            get() = Resources.getSystem().displayMetrics.widthPixels

        val height: Int
            get() = Resources.getSystem().displayMetrics.heightPixels
    }

    object Type {
        const val Phone = "phone"
        const val Tablet = "tablet"
    }
}

fun Context.getDeviceType(): String =
    getString(R.string._device)


fun Context.getScreenOrientation() = getString(R.string._screenOrientation)