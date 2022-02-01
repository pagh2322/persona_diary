package com.threesharp.personadiary.utils

import android.content.res.Resources

object Utils {
    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }
}