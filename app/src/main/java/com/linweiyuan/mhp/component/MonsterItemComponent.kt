package com.linweiyuan.mhp.component

import android.content.Context
import android.text.InputType
import com.linweiyuan.mhp.R
import com.linweiyuan.mhp.common.searchableSpinner
import org.jetbrains.anko.*

class MonsterItemComponent : AnkoComponent<Context> {
    override fun createView(ui: AnkoContext<Context>) = with(ui) {
        linearLayout {
            searchableSpinner {
                id = R.id.spnQuestMonster
                setTitle(ctx.getString(R.string.monster))
                setPositiveButton(ctx.getString(R.string.back))
            }.lparams(width = 0) {
                margin = dimen(R.dimen.value_5)
                weight = 1F
            }
            searchableSpinner {
                id = R.id.spnQuestMonsterArea
                setTitle(ctx.getString(R.string.occur_area))
                setPositiveButton(ctx.getString(R.string.back))
            }.lparams(width = 0) {
                margin = dimen(R.dimen.value_5)
                weight = 1F
            }
            editText {
                id = R.id.edtQuestMonsterNum
                setText("0")
                inputType = InputType.TYPE_CLASS_NUMBER
            }.lparams(width = 100) {
                marginStart = dimen(R.dimen.value_15)
                marginEnd = dimen(R.dimen.value_15)
            }
        }
    }
}
