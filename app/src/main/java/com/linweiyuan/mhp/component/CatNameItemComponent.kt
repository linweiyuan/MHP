package com.linweiyuan.mhp.component

import android.content.Context
import com.linweiyuan.mhp.R
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton
import org.jetbrains.anko.*

class CatNameItemComponent : AnkoComponent<Context> {
    override fun createView(ui: AnkoContext<Context>) = with(ui) {
        verticalLayout {
            linearLayout {
                textView {
                    id = R.id.txtCatIndex
                }.lparams {
                    marginStart = dimen(R.dimen.value_10)
                    marginEnd = dimen(R.dimen.value_10)
                }

                include<QMUIRoundButton>(R.layout.round_button) {
                    id = R.id.btnCatOperate
                }.lparams(width = 0) {
                    weight = 1F
                }
            }.lparams(width = matchParent)

            editText {
                id = R.id.edtCatName
                hint = ctx.getString(R.string.cat_name)
                singleLine = true
            }.lparams(width = matchParent) {
                marginStart = dimen(R.dimen.value_5)
                marginEnd = dimen(R.dimen.value_5)
            }

            editText {
                id = R.id.edtCatOwner
                hint = ctx.getString(R.string.cat_owner)
                singleLine = true
            }.lparams(width = matchParent) {
                marginStart = dimen(R.dimen.value_5)
                marginEnd = dimen(R.dimen.value_5)
            }

            editText {
                id = R.id.edtCatIntro
                hint = ctx.getString(R.string.cat_intro)
                singleLine = true
            }.lparams(width = matchParent) {
                marginStart = dimen(R.dimen.value_5)
                marginEnd = dimen(R.dimen.value_5)
            }
        }
    }
}
