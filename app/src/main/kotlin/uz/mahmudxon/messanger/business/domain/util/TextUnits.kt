package uz.mahmudxon.messanger.business.domain.util

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Build
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.style.MetricAffectingSpan
import android.view.Gravity
import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.viewinterop.AndroidView
import java.util.*


class TypefaceSpan(
    private var ctx: Context,
    private var typeface: Typeface?,
    private var textSize: Int = 0,
    private var color: Int = 0
) : MetricAffectingSpan() {

    fun setColor(value: Int) {
        color = value
    }

    val isMono: Boolean
        get() = typeface === Typeface.MONOSPACE
    val isBold: Boolean
        get() = typeface === getTypeFace(ctx, "fonts/rmedium.ttf")
    val isItalic: Boolean
        get() = typeface === getTypeFace(ctx, "fonts/ritalic.ttf")

    override fun updateMeasureState(p: TextPaint) {
        if (typeface != null) {
            p.typeface = typeface
        }
        if (textSize != 0) {
            p.textSize = textSize.toFloat()
        }
        p.flags = p.flags or Paint.SUBPIXEL_TEXT_FLAG
    }

    override fun updateDrawState(tp: TextPaint) {
        if (typeface != null) {
            tp.typeface = typeface
        }
        if (textSize != 0) {
            tp.textSize = textSize.toFloat()
        }
        if (color != 0) {
            tp.color = color
        }
        tp.flags = tp.flags or Paint.SUBPIXEL_TEXT_FLAG
    }
}


const val FLAG_TAG_BR = 1
const val FLAG_TAG_BOLD = 2
const val FLAG_TAG_COLOR = 4
const val FLAG_TAG_URL = 8
const val FLAG_TAG_ALL = FLAG_TAG_BR or FLAG_TAG_BOLD or FLAG_TAG_URL

fun replaceTags(ctx: Context, str: String): SpannableStringBuilder {
    return replaceTags(ctx, str, FLAG_TAG_ALL)
}

fun replaceTags(ctx: Context, str: String, flag: Int): SpannableStringBuilder {
    try {
        var start: Int
        var end: Int
        val stringBuilder = StringBuilder(str)
        if (flag and FLAG_TAG_BR != 0) {
            while (stringBuilder.indexOf("<br>").also { start = it } != -1) {
                stringBuilder.replace(start, start + 4, "\n")
            }
            while (stringBuilder.indexOf("<br/>").also { start = it } != -1) {
                stringBuilder.replace(start, start + 5, "\n")
            }
        }
        val bolds = ArrayList<Int>()
        if (flag and FLAG_TAG_BOLD != 0) {
            while (stringBuilder.indexOf("<b>").also { start = it } != -1) {
                stringBuilder.replace(start, start + 3, "")
                end = stringBuilder.indexOf("</b>")
                if (end == -1) {
                    end = stringBuilder.indexOf("<b>")
                }
                stringBuilder.replace(end, end + 4, "")
                bolds.add(start)
                bolds.add(end)
            }
            while (stringBuilder.indexOf("**").also { start = it } != -1) {
                stringBuilder.replace(start, start + 2, "")
                end = stringBuilder.indexOf("**")
                if (end >= 0) {
                    stringBuilder.replace(end, end + 2, "")
                    bolds.add(start)
                    bolds.add(end)
                }
            }
        }
        if (flag and FLAG_TAG_URL != 0) {
            while (stringBuilder.indexOf("**").also { start = it } != -1) {
                stringBuilder.replace(start, start + 2, "")
                end = stringBuilder.indexOf("**")
                if (end >= 0) {
                    stringBuilder.replace(end, end + 2, "")
                    bolds.add(start)
                    bolds.add(end)
                }
            }
        }
        val spannableStringBuilder = SpannableStringBuilder(stringBuilder)

        for (a in 0 until bolds.size / 2) {
            spannableStringBuilder.setSpan(
                TypefaceSpan(ctx, getTypeFace(ctx, "fonts/rmedium.ttf")),
                bolds[a * 2], bolds[a * 2 + 1], Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        return spannableStringBuilder
    } catch (e: Exception) {

    }
    return SpannableStringBuilder(str)
}

fun getTypeFace(ctx: Context, assetPath: String): Typeface {
    return if (Build.VERSION.SDK_INT >= 26) {
        val builder = Typeface.Builder(ctx.assets, assetPath)
        if (assetPath.contains("medium")) {
            builder.setWeight(700)
        }
        if (assetPath.contains("italic")) {
            builder.setItalic(true)
        }
        builder.build()
    } else {
        Typeface.createFromAsset(ctx.assets, assetPath)
    }
}

@Composable
fun StyledText(
    text: CharSequence,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null,
    textColor: Color? = null
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            val textView = TextView(context)
            when (textAlign) {
                TextAlign.Center -> {
                    textView.gravity = Gravity.CENTER
                }
                else -> {
                    textView.gravity = Gravity.START
                }
            }

            textColor?.let {
                textView.setTextColor(it.toArgb())
            }
            textView
        },
        update = {
            it.text = text
        }
    )
}
