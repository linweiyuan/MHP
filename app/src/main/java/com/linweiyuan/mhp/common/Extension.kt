package com.linweiyuan.mhp.common

import android.app.Activity
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Environment
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.linweiyuan.mhp.R
import com.linweiyuan.mhp.activity.LoginActivity
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog
import com.qmuiteam.qmui.widget.popup.QMUIPopup
import org.jetbrains.anko.db.RowParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import java.io.File

fun Context.popup(msg: String?) = QMUIPopup(this, QMUIPopup.DIRECTION_NONE).apply {
    val textView = TextView(this@popup)
    textView.layoutParams =
        generateLayoutParam(QMUIDisplayHelper.dp2px(this@popup, 250), WRAP_CONTENT)
    textView.setLineSpacing(QMUIDisplayHelper.dp2px(this@popup, 4).toFloat(), 1.0F)
    val padding = QMUIDisplayHelper.dp2px(this@popup, 20)
    textView.setPadding(padding, padding, padding, padding)
    textView.setTextColor(ContextCompat.getColor(this@popup, R.color.app_color_description))
    textView.text = msg
    setContentView(textView)
    setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER)
    setPreferredDirection(QMUIPopup.DIRECTION_TOP)
}

fun Context.loadingDialog(): QMUITipDialog =
    QMUITipDialog.Builder(this).setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING).create()

fun Context.toLogin() {
    startActivity<LoginActivity>()
    (this as Activity).finish()
}

fun file(dirName: String = "", fileName: String, sdcard: Boolean = true): File {
    @Suppress("DEPRECATION")
    val dir = if (sdcard) {
        File(Environment.getExternalStorageDirectory().absoluteFile, dirName)
    } else {
        File(dirName)
    }
    if (!dir.exists()) dir.mkdirs()
    val file = File(dir, fileName)
    if (!file.exists()) file.createNewFile()
    return file
}

fun SQLiteDatabase.arrayAdapter(tableName: String, ctx: Context) = ArrayAdapter(
    ctx,
    android.R.layout.simple_spinner_item,
    (this.select(tableName).parseList(object : RowParser<String> {
        override fun parseRow(columns: Array<Any?>): String {
            return columns[1] as String
        }
    }))
)
