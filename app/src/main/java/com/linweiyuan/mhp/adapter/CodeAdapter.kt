package com.linweiyuan.mhp.adapter

import android.content.ClipboardManager
import android.content.Context
import android.text.InputType
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.linweiyuan.mhp.R
import com.linweiyuan.mhp.common.Constant
import com.linweiyuan.mhp.common.contentDialog
import com.linweiyuan.mhp.common.writeCodeFile
import com.linweiyuan.mhp.component.CodeItemComponent
import com.linweiyuan.mhp.model.Code
import com.qmuiteam.qmui.widget.dialog.QMUIDialog
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.sdk27.coroutines.onLongClick

class CodeAdapter(private val ctx: Context, private val codeList: MutableList<Code>) :
    RecyclerView.Adapter<CodeAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val code = codeList[position]
        holder.txtCodeName.text = code.name
        holder.chkCodeEnable.isChecked = code.enable == Constant.CODE_ENABLE
        with(ctx) {
            holder.cardCode.onClick {
                val builder = QMUIDialog.MessageDialogBuilder(this@with)
                builder.setTitle(getString(R.string.code_operation))
                    .setMessage(getString(R.string.code_operation_hint))
                    .addAction(getString(R.string.code_show)) { dialog, _ ->
                        contentDialog(getString(R.string.code_show_content), code.toString()).show()
                        dialog.dismiss()
                    }
                    .addAction(getString(R.string.code_share)) { dialog, _ ->
                        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        @Suppress("DEPRECATION")
                        clipboard.text = code.toString()
                        toast(getString(R.string.code_copy_finish))
                        share(code.toString())
                        dialog.dismiss()
                    }
                    .addAction(getString(R.string.code_delete)) { dialog, _ ->
                        doAsync {
                            codeList.removeAt(position)
                            writeCodeFile(codeList)
                            uiThread {
                                notifyDataSetChanged()
                                dialog.dismiss()
                            }
                        }
                    }
                    .show()
            }
            holder.cardCode.onLongClick {
                val builder = QMUIDialog.EditTextDialogBuilder(this@with)
                builder.setTitle(getString(R.string.code_rename))
                    .setPlaceholder(code.name)
                    .setInputType(InputType.TYPE_CLASS_TEXT)
                    .addAction(getString(R.string.ok), object : QMUIDialogAction.ActionListener {
                        override fun onClick(dialog: QMUIDialog, index: Int) {
                            @Suppress("DEPRECATION")
                            val name = builder.editText.text.toString().trim()
                            if (name.isNotBlank()) {
                                code.name = name
                                doAsync {
                                    writeCodeFile(codeList)
                                    uiThread {
                                        notifyDataSetChanged()
                                        dialog.dismiss()
                                    }
                                }
                            } else {
                                dialog.dismiss()
                            }
                        }
                    }).show()
            }
            holder.chkCodeEnable.onClick {
                code.enable = if (holder.chkCodeEnable.isChecked) Constant.CODE_ENABLE else Constant.CODE_DISABLE
                // 实时修改金手指代码
                doAsync { writeCodeFile(codeList) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(CodeItemComponent().createView(AnkoContext.create(ctx)))

    override fun getItemCount() = codeList.size

    override fun getItemViewType(position: Int) = position

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardCode = view.find<CardView>(R.id.cardCode)
        val txtCodeName = view.find<TextView>(R.id.txtCodeName)
        val chkCodeEnable = view.find<CheckBox>(R.id.chkCodeEnable)
    }
}
