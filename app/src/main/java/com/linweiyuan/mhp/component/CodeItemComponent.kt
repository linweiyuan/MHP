package com.linweiyuan.mhp.component

import android.content.Context
import com.linweiyuan.mhp.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class CodeItemComponent : AnkoComponent<Context> {
    override fun createView(ui: AnkoContext<Context>) = with(ui) {
        cardView {
            lparams(width = matchParent) {
                margin = dimen(R.dimen.value_5)
            }
            id = R.id.cardCode

            radius = 15F

            linearLayout {
                padding = dimen(R.dimen.value_5)
                textView {
                    id = R.id.txtCodeName
                }.lparams(width = 0) {
                    weight = 1F
                }

                checkBox {
                    id = R.id.chkCodeEnable
                }
            }
        }
    }
}
