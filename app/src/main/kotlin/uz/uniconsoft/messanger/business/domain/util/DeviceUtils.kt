package uz.uniconsoft.messanger.business.domain.util

import android.content.Context
import android.content.res.Resources
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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

fun Context.dpFromPx(px: Float): Float {
    return px / resources.displayMetrics.density
}

fun Context.pxFromDp(dp: Float): Float {
    return dp * resources.displayMetrics.density
}

fun Context.getStatusBarHeight(): Int {
    var result = 0
    val resourceId: Int = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = resources.getDimensionPixelSize(resourceId)
    }
    return result
}

fun Context.getDeviceType(): String =
    getString(R.string._device)


fun Modifier.setStatusBarPadding(context: Context): Modifier {
    val hPx = context.getStatusBarHeight().toFloat()
    val hDp = context.dpFromPx(hPx).toInt()
    padding(top = hDp.dp)

    return this
}

fun Context.getScreenOrientation() = getString(R.string._screenOrientation)