package com.jinglebroda.presentation.utils.extensionFunFragment

import android.content.Context
import android.graphics.Point
import android.view.Display
import android.view.WindowManager
import androidx.fragment.app.Fragment


fun Fragment.sizeDisplayInPx():Point{
    val wm = requireContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val display = wm.defaultDisplay
    val size = Point()
    display.getSize(size)
    return size
}