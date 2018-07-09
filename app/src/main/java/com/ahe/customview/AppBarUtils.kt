package com.ahe.customview

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics

object AppBarUtils {

    fun getDisplayMetrics(context: Context): DisplayMetrics {
        val resources = context.resources
        return resources.displayMetrics
    }

    fun convertDpToPixel(dp: Float, context: Context): Float {
        return dp * (getDisplayMetrics(context).densityDpi / 160f)
    }
    fun convertPixelToDp(px: Float, context: Context): Float {
        return (px / getDisplayMetrics(context).densityDpi) * 160f
    }
    fun convertDpToPixelSize(dp: Float, context: Context): Int {
        val pixels = convertDpToPixel(dp, context)
        val res = (pixels + 0.5f).toInt()
        if (res != 0) {
            return res
        } else if (pixels == 0f) {
            return 0
        } else if (pixels > 0) {
            return 1
        }
        return -1
    }

}