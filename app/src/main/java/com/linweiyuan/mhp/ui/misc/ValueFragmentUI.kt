package com.linweiyuan.mhp.ui.misc

import android.text.InputType
import com.linweiyuan.mhp.R
import com.linweiyuan.mhp.common.searchableSpinner
import com.linweiyuan.mhp.fragment.misc.ValueFragment
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton
import com.toptoche.searchablespinnerlibrary.SearchableSpinner
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class ValueFragmentUI : AnkoComponent<ValueFragment> {
    override fun createView(ui: AnkoContext<ValueFragment>) = with(ui) {
        scrollView {
            verticalLayout {
                linearLayout {
                    owner.edtTimeHour = editText {
                        hint = ctx.getString(R.string.hour)
                        inputType = InputType.TYPE_CLASS_NUMBER
                    }.lparams(width = 0) {
                        weight = 1F
                        margin = dimen(R.dimen.value_10)
                        padding = dimen(R.dimen.value_10)
                    }
                    owner.edtTimeMinute = editText {
                        hint = ctx.getString(R.string.minute)
                        inputType = InputType.TYPE_CLASS_NUMBER
                    }.lparams(width = 0) {
                        weight = 1F
                        margin = dimen(R.dimen.value_10)
                        padding = dimen(R.dimen.value_10)
                    }
                }
                owner.btnTime = include<QMUIRoundButton>(R.layout.round_button) {
                    text = resources.getString(R.string.time)
                    onClick { owner.genTimeCode() }
                }

                linearLayout {
                    owner.spnWeaponType = searchableSpinner {}.lparams(matchParent) {
                        verticalMargin = dimen(R.dimen.value_15)
                    }.lparams(width = 0) {
                        weight = 1F
                    }
                    owner.spnWeaponPlace = searchableSpinner {}.lparams(matchParent) {
                        verticalMargin = dimen(R.dimen.value_15)
                    }.lparams(width = 0) {
                        weight = 1F
                    }
                    owner.edtWeaponValue = editText {
                        hint = ctx.getString(R.string.num_hint)
                        inputType = InputType.TYPE_CLASS_NUMBER
                    }.lparams {
                        marginEnd = dimen(R.dimen.value_10)
                    }
                }
                owner.btnWeaponNum = include<QMUIRoundButton>(R.layout.round_button) {
                    text = resources.getString(R.string.weapon_num)
                    onClick { owner.genWeaponNumCode() }
                }

                linearLayout {
                    owner.spnCardQuestType = searchableSpinner {}.lparams(matchParent) {
                        verticalMargin = dimen(R.dimen.value_15)
                    }.lparams(width = 0) {
                        weight = 1F
                    }
                    owner.edtCardQuestValue = editText {
                        hint = ctx.getString(R.string.num_hint)
                        inputType = InputType.TYPE_CLASS_NUMBER
                    }.lparams {
                        marginEnd = dimen(R.dimen.value_10)
                    }
                }
                owner.btnCardQuest = include<QMUIRoundButton>(R.layout.round_button) {
                    text = resources.getString(R.string.card_quest_execute_num)
                    onClick { owner.genQuestNumCode() }
                }

                linearLayout {
                    owner.spnBossRecordBoss = searchableSpinner {}.lparams(matchParent) {
                        verticalMargin = dimen(R.dimen.value_15)
                    }.lparams(width = 0) {
                        weight = 1F
                    }
                    owner.edtBossRecordKillNum = editText {
                        hint = ctx.getString(R.string.boss_record_kill_num)
                        inputType = InputType.TYPE_CLASS_NUMBER
                    }.lparams {
                        marginEnd = dimen(R.dimen.value_10)
                    }
                    owner.edtBossRecordCatchNum = editText {
                        hint = ctx.getString(R.string.boss_record_catch_num)
                        inputType = InputType.TYPE_CLASS_NUMBER
                    }.lparams {
                        marginEnd = dimen(R.dimen.value_10)
                    }
                }
                owner.btnBossNum = include<QMUIRoundButton>(R.layout.round_button) {
                    text = resources.getString(R.string.boss_record)
                    onClick { owner.genBossNumCode() }
                }
            }.applyRecursively {
                with(it) {
                    if (this is SearchableSpinner) {
                        setPositiveButton(ctx.getString(R.string.back))
                    }
                    if (this !is QMUIRoundButton) padding = dimen(R.dimen.value_5)
                }
            }
        }
    }
}
