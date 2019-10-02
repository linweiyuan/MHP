package com.linweiyuan.mhp.ui.quest

import android.annotation.SuppressLint
import android.widget.EditText
import com.linweiyuan.mhp.R
import com.linweiyuan.mhp.fragment.quest.QuestBossFragment
import com.toptoche.searchablespinnerlibrary.SearchableSpinner
import org.jetbrains.anko.*

class QuestBossFragmentUI : AnkoComponent<QuestBossFragment> {
    @SuppressLint("SetTextI18n")
    override fun createView(ui: AnkoContext<QuestBossFragment>) = with(ui) {
        scrollView {
            verticalLayout {
                owner.llBoss = verticalLayout {}
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
