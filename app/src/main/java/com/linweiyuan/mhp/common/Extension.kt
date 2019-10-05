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
import com.linweiyuan.mhp.model.Code
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.qmuiteam.qmui.widget.dialog.QMUIDialog
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog
import com.qmuiteam.qmui.widget.popup.QMUIPopup
import org.jetbrains.anko.db.RowParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import java.io.File

fun Context.popup(msg: String?) = QMUIPopup(this, QMUIPopup.DIRECTION_NONE).also {
    val textView = TextView(this)
    textView.layoutParams = it.generateLayoutParam(QMUIDisplayHelper.dp2px(this, 250), WRAP_CONTENT)
    textView.setLineSpacing(QMUIDisplayHelper.dp2px(this, 4).toFloat(), 1.0F)
    val padding = QMUIDisplayHelper.dp2px(this, 20)
    textView.setPadding(padding, padding, padding, padding)
    textView.setTextColor(ContextCompat.getColor(this, R.color.app_color_description))
    textView.text = msg
    it.setContentView(textView)
    it.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER)
    it.setPreferredDirection(QMUIPopup.DIRECTION_TOP)
}

fun Context.loadingDialog(): QMUITipDialog = QMUITipDialog.Builder(this).setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING).create()

fun Context.toLogin() {
    startActivity<LoginActivity>()
    (this as Activity).finish()
}

fun file(dirName: String = "", fileName: String, sdcard: Boolean = true): File {
    @Suppress("DEPRECATION")
    val dir = File(Environment.getExternalStorageDirectory().absoluteFile, dirName).takeIf { sdcard } ?: File(dirName)
    if (!dir.exists()) dir.mkdirs()
    val file = File(dir, fileName)
    if (!file.exists()) file.createNewFile()
    return file
}

fun SQLiteDatabase.arrayAdapter(tableName: String, ctx: Context) = ArrayAdapter(
    ctx, android.R.layout.simple_spinner_item, (this.select(tableName).parseList(object : RowParser<String> {
        override fun parseRow(columns: Array<Any?>): String {
            return columns[1] as String
        }
    }))
)

fun writeCodeFile(codeList: MutableList<Code>) {
    val sb = StringBuilder()
    codeList.forEach {
        sb.apply {
            append("${Constant.CODE_CODE_NAME_PREFIX}${it.enable} ${it.name}\n")
            it.codeMap.mapValues { (k, v) -> append("${Constant.CODE_CODE_KEY_PREFIX} ${Constant.CODE_CODE_PREFIX}$k ${Constant.CODE_CODE_PREFIX}$v\n") }
        }
    }
    file(Constant.CODE_FILE_FOLDER, Constant.CODE_FILE_NAME).writeText(sb.toString())
}

fun readCodeFile(): MutableList<Code> {
    val codeList = mutableListOf<Code>()
    lateinit var codeMap: HashMap<String, String>
    val codeFile = file(Constant.CODE_FILE_FOLDER, Constant.CODE_FILE_NAME)
    lateinit var code: Code
    codeFile.forEachLine {
        // 解析说明行
        if (it.startsWith(Constant.CODE_CODE_NAME_PREFIX)) {
            codeMap = HashMap()
            code = Code(enable = it[2], name = it.substring(4).trim(), codeMap = codeMap)
            codeList.add(code)
        }
        // 解析代码行
        if (it.startsWith(Constant.CODE_CODE_KEY_PREFIX)) {
            val str = it.substring(3).split(" ")
            codeMap[str[0].substring(2).trim()] = str[1].substring(2).trim()
        }
    }
    return codeList
}

fun Context.contentDialog(
    title: String,
    msg: String,
    text: String? = null,
    listener: QMUIDialogAction.ActionListener? = null
): QMUIDialog = QMUIDialog.MessageDialogBuilder(this).setTitle(title).setMessage(msg).apply {
    if (text != null) {
        addAction(text, listener)
    }
}.create()
