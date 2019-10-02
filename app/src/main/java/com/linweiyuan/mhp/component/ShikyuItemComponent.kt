package com.linweiyuan.mhp.component

import android.content.Context
import android.text.InputType
import com.linweiyuan.mhp.R
import com.linweiyuan.mhp.common.searchableSpinner
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton
import org.jetbrains.anko.*

class ShikyuItemComponent : AnkoComponent<Context> {
    override fun createView(ui: AnkoContext<Context>) = with(ui) {
        linearLayout {
            searchableSpinner {
                id = R.id.spnQuestShikyu
                setTitle(ctx.getString(R.string.shikyu))
                setPositiveButton(ctx.getString(R.string.back))
            }.lparams(width = 0) {
                margin = dimen(R.dimen.value_5)
                weight = 1F
            }

            editText {
                id = R.id.edtQuestShikyuNum
                setText("0")
                inputType = InputType.TYPE_CLASS_NUMBER
            }.lparams {
                marginStart = dimen(R.dimen.value_15)
                marginEnd = dimen(R.dimen.value_15)
                padding = dimen(R.dimen.value_15)
            }

            include<QMUIRoundButton>(R.layout.round_button) {
                id = R.id.btnShikyuOperate
            }.lparams(width = 200) {
                padding = dimen(R.dimen.value_5)
            }
        }
    }
}
