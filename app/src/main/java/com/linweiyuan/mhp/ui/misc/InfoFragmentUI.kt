package com.linweiyuan.mhp.ui.misc

import android.widget.EditText
import com.linweiyuan.mhp.R
import com.linweiyuan.mhp.fragment.misc.InfoFragment
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class InfoFragmentUI : AnkoComponent<InfoFragment> {
    override fun createView(ui: AnkoContext<InfoFragment>) = with(ui) {
        verticalLayout {
            lparams(width = matchParent)

            textView(ctx.getString(R.string.name_not_too_long_hint)) {}.lparams {
                width = matchParent
                margin = dimen(R.dimen.value_15)
                padding = dimen(R.dimen.value_15)
            }

            owner.edtPlayerName = editText {
                hint = ctx.getString(R.string.player_name)
                singleLine = true
            }.lparams(width = matchParent) {
                marginStart = dimen(R.dimen.value_15)
                marginEnd = dimen(R.dimen.value_15)
            }

            owner.edtPlayerIntro = editText {
                hint = ctx.getString(R.string.player_intro)
                singleLine = true
            }.lparams(width = matchParent) {
                topMargin = dimen(R.dimen.value_15)
                marginStart = dimen(R.dimen.value_15)
                marginEnd = dimen(R.dimen.value_15)
            }

            owner.btnPlayer = include<QMUIRoundButton>(R.layout.round_button) {
                text = resources.getString(R.string.player_info)
                onClick { owner.genPlayerCode() }
            }

            scrollView { owner.llName = verticalLayout {} }.lparams(
                width = matchParent,
                height = 0
            ) { weight = 1F }

            owner.btnCat = include<QMUIRoundButton>(R.layout.round_button) {
                text = resources.getString(R.string.cat_info)
                onClick { owner.genCatCode() }
            }
        }.applyRecursively {
            with(it) {
                if (this is EditText) {
                    padding = dimen(R.dimen.value_15)
                }
                if (this !is QMUIRoundButton) padding = dimen(R.dimen.value_5)
            }
        }
    }
}
