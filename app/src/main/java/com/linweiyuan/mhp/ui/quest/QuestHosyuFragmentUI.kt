package com.linweiyuan.mhp.ui.quest

import com.linweiyuan.mhp.R
import com.linweiyuan.mhp.fragment.quest.QuestHosyuFragment
import org.jetbrains.anko.*

class QuestHosyuFragmentUI : AnkoComponent<QuestHosyuFragment> {
    override fun createView(ui: AnkoContext<QuestHosyuFragment>) = with(ui) {
        verticalLayout {
            owner.llHosyu = verticalLayout {}
            themedTextView(R.style.QDCommonDescription) {
                text = ctx.getString(R.string.hosyu_limit_hint)
            }.lparams(matchParent) {
                margin = dimen(R.dimen.value_5)
                padding = dimen(R.dimen.value_5)
            }
        }
    }
}
