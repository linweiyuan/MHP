package com.linweiyuan.mhp.ui.quest

import android.widget.EditText
import com.linweiyuan.mhp.R
import com.linweiyuan.mhp.fragment.quest.QuestMonsterFragment
import com.toptoche.searchablespinnerlibrary.SearchableSpinner
import org.jetbrains.anko.*

class QuestMonsterFragmentUI : AnkoComponent<QuestMonsterFragment> {
    override fun createView(ui: AnkoContext<QuestMonsterFragment>) = with(ui) {
        scrollView {
            verticalLayout {
                owner.llMonster = verticalLayout {}
            }.applyRecursively {
                if (it is EditText) {
                    it.singleLine = true
                }
                if (it is SearchableSpinner) {
                    it.padding = dimen(R.dimen.value_15)
                    it.setPositiveButton(owner.getString(R.string.back))
                }
            }
        }
    }
}
