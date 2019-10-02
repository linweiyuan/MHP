package com.linweiyuan.mhp.ui.quest

import com.linweiyuan.mhp.fragment.quest.QuestShikyuFragment
import org.jetbrains.anko.*

class QuestShikyuFragmentUI : AnkoComponent<QuestShikyuFragment> {
    override fun createView(ui: AnkoContext<QuestShikyuFragment>) = with(ui) {
        linearLayout {
            scrollView {
                owner.llShikyu = verticalLayout {}
            }.lparams(width = matchParent)
        }
    }
}
