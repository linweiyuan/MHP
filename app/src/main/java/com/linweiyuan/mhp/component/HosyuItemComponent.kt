package com.linweiyuan.mhp.component

import android.content.Context
import android.text.InputType
import com.linweiyuan.mhp.R
import com.linweiyuan.mhp.common.searchableSpinner
import org.jetbrains.anko.*

class HosyuItemComponent : AnkoComponent<Context> {
    override fun createView(ui: AnkoContext<Context>) = with(ui) {
        linearLayout {
            searchableSpinner {
                id = R.id.spnQuestHosyu
                setTitle(ctx.getString(R.string.hosyu))
                setPositiveButton(ctx.getString(R.string.back))
            }.lparams(width = 0) {
                margin = dimen(R.dimen.value_5)
                weight = 1F
            }

            editText {
                id = R.id.edtQuestHosyuNum
                setText("0")
                inputType = InputType.TYPE_CLASS_NUMBER
            }.lparams {
                marginStart = dimen(R.dimen.value_5)
                marginEnd = dimen(R.dimen.value_5)
            }
        }
    }
}
