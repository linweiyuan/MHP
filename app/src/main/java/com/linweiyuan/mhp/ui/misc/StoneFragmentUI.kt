package com.linweiyuan.mhp.ui.misc

import android.view.Gravity
import android.widget.LinearLayout
import com.linweiyuan.mhp.R
import com.linweiyuan.mhp.common.searchableSpinner
import com.linweiyuan.mhp.fragment.misc.StoneFragment
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton
import com.toptoche.searchablespinnerlibrary.SearchableSpinner
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class StoneFragmentUI : AnkoComponent<StoneFragment> {
    override fun createView(ui: AnkoContext<StoneFragment>) = with(ui) {
        verticalLayout {
            linearLayout {
                owner.spnStoneSkill1 = searchableSpinner {}.lparams(width = 0) { weight = 1F }
                owner.spnStonePoint1 = searchableSpinner {}
            }.lparams(matchParent) {
                verticalMargin = dimen(R.dimen.value_10)
            }

            linearLayout {
                owner.spnStoneSkill2 = searchableSpinner { }.lparams(width = 0) { weight = 1F }
                owner.spnStonePoint2 = searchableSpinner { }
            }.lparams(matchParent) {
                verticalMargin = dimen(R.dimen.value_15)
            }

            owner.spnStoneRarity = searchableSpinner {}.lparams(matchParent) {
                margin = dimen(R.dimen.value_5)
                verticalMargin = dimen(R.dimen.value_15)
            }

            owner.rgStoneSlots = radioGroup {
                orientation = LinearLayout.HORIZONTAL

                radioButton {
                    id = R.id.rbStoneSlots0
                    text = "0"
                    isChecked = true
                }
                radioButton {
                    id = R.id.rbStoneSlots1
                    text = "1"
                }
                radioButton {
                    id = R.id.rbStoneSlots2
                    text = "2"
                }
                radioButton {
                    id = R.id.rbStoneSlots3
                    text = "3"
                }
            }.lparams {
                gravity = Gravity.CENTER
                verticalMargin = dimen(R.dimen.value_15)
            }

            owner.btnStone = include<QMUIRoundButton>(R.layout.round_button) {
                text = resources.getString(R.string.ok)
                onClick { owner.genStoneCode() }
            }
        }.applyRecursively {
            with(it) {
                if (this is SearchableSpinner) setPositiveButton(ctx.getString(R.string.back))
                if (this !is QMUIRoundButton) padding = dimen(R.dimen.value_5)
            }
        }
    }
}
