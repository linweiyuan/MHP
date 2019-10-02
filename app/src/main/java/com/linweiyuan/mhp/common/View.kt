package com.linweiyuan.mhp.common

import android.view.ViewManager
import com.toptoche.searchablespinnerlibrary.SearchableSpinner
import org.jetbrains.anko.custom.ankoView

// 下拉框
inline fun ViewManager.searchableSpinner(theme: Int = 0, init: SearchableSpinner.() -> Unit) =
    ankoView({ SearchableSpinner(it) }, theme, init)
