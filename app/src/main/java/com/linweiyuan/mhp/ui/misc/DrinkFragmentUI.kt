package com.linweiyuan.mhp.ui.misc

import com.linweiyuan.mhp.R
import com.linweiyuan.mhp.common.searchableSpinner
import com.linweiyuan.mhp.fragment.misc.DrinkFragment
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton
import com.toptoche.searchablespinnerlibrary.SearchableSpinner
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class DrinkFragmentUI : AnkoComponent<DrinkFragment> {
    override fun createView(ui: AnkoContext<DrinkFragment>) = with(ui) {
        verticalLayout {
            owner.spnDrinkSkill1 = searchableSpinner {}.lparams(matchParent) {
                verticalMargin = dimen(R.dimen.value_15)
            }
            owner.spnDrinkSkill2 = searchableSpinner {}.lparams(matchParent) {
                verticalMargin = dimen(R.dimen.value_15)
            }
            owner.spnDrinkSkill3 = searchableSpinner {}.lparams(matchParent) {
                verticalMargin = dimen(R.dimen.value_15)
            }
            owner.spnDrinkSkill4 = searchableSpinner {}.lparams(matchParent) {
                verticalMargin = dimen(R.dimen.value_15)
            }
            owner.spnDrinkSkill5 = searchableSpinner {}.lparams(matchParent) {
                verticalMargin = dimen(R.dimen.value_15)
            }

            space {}.lparams(width = matchParent, height = 0) { weight = 1F }

            owner.btnDrink = include<QMUIRoundButton>(R.layout.round_button) {
                text = resources.getString(R.string.ok)
                onClick { owner.genDrinkCode() }
            }
        }.applyRecursively {
            with(it) {
                if (this is SearchableSpinner) {
                    setPositiveButton(ctx.getString(R.string.back))
                    layoutParams.width = matchParent
                }
                if (this !is QMUIRoundButton) padding = dimen(R.dimen.value_5)
            }
        }
    }
}
