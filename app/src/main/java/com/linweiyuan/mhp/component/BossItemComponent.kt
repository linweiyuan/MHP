package com.linweiyuan.mhp.component

import android.annotation.SuppressLint
import android.content.Context
import android.text.InputType
import com.linweiyuan.mhp.R
import com.linweiyuan.mhp.common.searchableSpinner
import org.jetbrains.anko.*

class BossItemComponent : AnkoComponent<Context> {
    @SuppressLint("SetTextI18n")
    override fun createView(ui: AnkoContext<Context>) = with(ui) {
        verticalLayout {
            linearLayout {
                searchableSpinner {
                    id = R.id.spnQuestBoss
                    setTitle(ctx.getString(R.string.boss))
                    setPositiveButton(ctx.getString(R.string.back))
                }.lparams(width = 0) {
                    margin = dimen(R.dimen.value_5)
                    weight = 1F
                }
                searchableSpinner {
                    id = R.id.spnQuestBossArea
                    setTitle(ctx.getString(R.string.occur_area))
                    setPositiveButton(ctx.getString(R.string.back))
                }.lparams(width = 0) {
                    margin = dimen(R.dimen.value_5)
                    weight = 1F
                }
                editText {
                    id = R.id.edtQuestBossNum
                    hint = ctx.getString(R.string.quest_boss_num)
                    setText("0")
                    inputType = InputType.TYPE_CLASS_NUMBER
                }.lparams(width = 100) {
                    marginStart = dimen(R.dimen.value_15)
                    marginEnd = dimen(R.dimen.value_15)
                }

            }
            linearLayout {
                editText {
                    id = R.id.edtQuestBossSize
                    hint = ctx.getString(R.string.quest_boss_size)
                    setText("100")
                    inputType = InputType.TYPE_CLASS_NUMBER
                }.lparams(width = 100) {
                    marginStart = dimen(R.dimen.value_15)
                    marginEnd = dimen(R.dimen.value_15)
                }
                searchableSpinner {
                    id = R.id.spnQuestBossHP
                    setTitle(ctx.getString(R.string.quest_boss_hp))
                    setPositiveButton(ctx.getString(R.string.back))
                }.lparams(width = 0) {
                    margin = dimen(R.dimen.value_5)
                    weight = 1F
                }
                searchableSpinner {
                    id = R.id.spnQuestBossStrength
                    setTitle(ctx.getString(R.string.quest_boss_strength))
                    setPositiveButton(ctx.getString(R.string.back))
                }.lparams(width = 0) {
                    margin = dimen(R.dimen.value_5)
                    weight = 1F
                }
                searchableSpinner {
                    id = R.id.spnQuestBossEndurance
                    setTitle(ctx.getString(R.string.quest_boss_endurance))
                    setPositiveButton(ctx.getString(R.string.back))
                }.lparams(width = 0) {
                    margin = dimen(R.dimen.value_5)
                    weight = 1F
                }
                searchableSpinner {
                    id = R.id.spnQuestBossFatigue
                    setTitle(ctx.getString(R.string.quest_boss_fatigue))
                    setPositiveButton(ctx.getString(R.string.back))
                }.lparams(width = 0) {
                    margin = dimen(R.dimen.value_5)
                    weight = 1F
                }
            }
            linearLayout {
                searchableSpinner {
                    id = R.id.spnQuestBossStatus
                    setTitle(ctx.getString(R.string.quest_boss_status))
                    setPositiveButton(ctx.getString(R.string.back))
                }.lparams(width = 0) {
                    margin = dimen(R.dimen.value_5)
                    weight = 1F
                }
                searchableSpinner {
                    id = R.id.spnQuestBossRound
                    setTitle(ctx.getString(R.string.quest_boss_round))
                    setPositiveButton(ctx.getString(R.string.back))
                }.lparams(width = 0) {
                    margin = dimen(R.dimen.value_5)
                    weight = 1F
                }
            }
        }
    }
}
