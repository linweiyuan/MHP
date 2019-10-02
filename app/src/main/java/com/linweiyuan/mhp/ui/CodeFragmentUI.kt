package com.linweiyuan.mhp.ui

import androidx.recyclerview.widget.LinearLayoutManager
import com.linweiyuan.mhp.R
import com.linweiyuan.mhp.fragment.CodeFragment
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class CodeFragmentUI : AnkoComponent<CodeFragment> {
    override fun createView(ui: AnkoContext<CodeFragment>) = with(ui) {
        verticalLayout {
            recyclerView {
                id = R.id.recyclerCode
                layoutManager = LinearLayoutManager(ctx)
            }.lparams(width = matchParent, height = 0) { weight = 1F }
        }.applyRecursively {
            with(it) {
                if (this !is QMUIRoundButton) padding = dimen(R.dimen.value_5)
            }
        }
    }
}
