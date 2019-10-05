package com.linweiyuan.mhp.ui.quest

import android.annotation.SuppressLint
import android.text.InputType
import android.widget.EditText
import com.linweiyuan.mhp.R
import com.linweiyuan.mhp.common.searchableSpinner
import com.linweiyuan.mhp.fragment.quest.QuestBasicFragment
import com.toptoche.searchablespinnerlibrary.SearchableSpinner
import org.jetbrains.anko.*

class QuestBasicFragmentUI : AnkoComponent<QuestBasicFragment> {
    @SuppressLint("SetTextI18n")
    override fun createView(ui: AnkoContext<QuestBasicFragment>) = with(ui) {
        scrollView {
            verticalLayout {
                owner.edtQuestName = editText {
                    hint = ctx.getString(R.string.quest_name)
                }.lparams(width = matchParent) {
                    marginStart = dimen(R.dimen.value_15)
                    marginEnd = dimen(R.dimen.value_15)
                }
                owner.edtQuestContent = editText {
                    hint = ctx.getString(R.string.quest_content)
                }.lparams(width = matchParent) {
                    marginStart = dimen(R.dimen.value_15)
                    marginEnd = dimen(R.dimen.value_15)
                }
                owner.edtQuestSuccess = editText { hint = ctx.getString(R.string.quest_success_condition) }.lparams(
                    width = matchParent
                ) {
                    marginStart = dimen(R.dimen.value_15)
                    marginEnd = dimen(R.dimen.value_15)
                }
                owner.edtQuestFailure = editText { hint = ctx.getString(R.string.quest_failure_condition) }.lparams(
                    width = matchParent
                ) {
                    marginStart = dimen(R.dimen.value_15)
                    marginEnd = dimen(R.dimen.value_15)
                }
                owner.edtQuestMonster = editText {
                    hint = ctx.getString(R.string.quest_main_monster)
                }.lparams(width = matchParent) {
                    marginStart = dimen(R.dimen.value_15)
                    marginEnd = dimen(R.dimen.value_15)
                }
                owner.edtQuestClient = editText {
                    hint = ctx.getString(R.string.quest_client)
                }.lparams(width = matchParent) {
                    marginStart = dimen(R.dimen.value_15)
                    marginEnd = dimen(R.dimen.value_15)
                }

                linearLayout {
                    owner.edtQuestSuccessPts = editText {
                        hint = ctx.getString(R.string.quest_success_point)
                        setText("100")
                        inputType = InputType.TYPE_CLASS_NUMBER
                    }.lparams(width = 0) {
                        marginStart = dimen(R.dimen.value_15)
                        marginEnd = dimen(R.dimen.value_15)
                        weight = 1F
                    }
                    owner.edtQuestFailPts = editText {
                        hint = ctx.getString(R.string.quest_failure_point)
                        setText("15")
                        inputType = InputType.TYPE_CLASS_NUMBER
                    }.lparams(width = 0) {
                        marginStart = dimen(R.dimen.value_15)
                        marginEnd = dimen(R.dimen.value_15)
                        weight = 1F
                    }
                }
                linearLayout {
                    owner.edtQuestContractZ = editText {
                        hint = ctx.getString(R.string.quest_contract_gold)
                        setText("100")
                        inputType = InputType.TYPE_CLASS_NUMBER
                    }.lparams(width = 0) {
                        marginStart = dimen(R.dimen.value_15)
                        marginEnd = dimen(R.dimen.value_15)
                        weight = 1F
                    }
                    owner.edtQuestRewardZ = editText {
                        hint = ctx.getString(R.string.quest_reward_gold)
                        setText("1200")
                        inputType = InputType.TYPE_CLASS_NUMBER
                    }.lparams(width = 0) {
                        marginStart = dimen(R.dimen.value_15)
                        marginEnd = dimen(R.dimen.value_15)
                        weight = 1F
                    }
                }
                linearLayout {
                    owner.edtQuestMinute = editText {
                        hint = ctx.getString(R.string.quest_time_minute)
                        setText("50")
                        inputType = InputType.TYPE_CLASS_NUMBER
                    }.lparams(width = 0) {
                        marginStart = dimen(R.dimen.value_15)
                        marginEnd = dimen(R.dimen.value_15)
                        weight = 1F
                    }
                    owner.edtQuestSecond = editText {
                        hint = ctx.getString(R.string.quest_time_second)
                        setText("00")
                        inputType = InputType.TYPE_CLASS_NUMBER
                    }.lparams(width = 0) {
                        marginStart = dimen(R.dimen.value_15)
                        marginEnd = dimen(R.dimen.value_15)
                        weight = 1F
                    }
                }

                owner.spnQuestStartArea = searchableSpinner {}.lparams(width = matchParent) {
                    margin = dimen(R.dimen.value_5)
                }
                owner.spnQuestBossSkill = searchableSpinner {}.lparams(width = matchParent) {
                    margin = dimen(R.dimen.value_5)
                }
                owner.spnQuestPickRank = searchableSpinner {}.lparams(width = matchParent) {
                    margin = dimen(R.dimen.value_5)
                }
                owner.spnQuestBGM = searchableSpinner {}.lparams(width = matchParent) {
                    margin = dimen(R.dimen.value_5)
                }
                owner.spnQuestReturnTime = searchableSpinner {}.lparams(width = matchParent) {
                    margin = dimen(R.dimen.value_5)
                }
                owner.spnQuestType = searchableSpinner {}.lparams(width = matchParent) {
                    margin = dimen(R.dimen.value_5)
                }
                owner.spnQuestRank = searchableSpinner {}.lparams(width = matchParent) {
                    margin = dimen(R.dimen.value_5)
                }
                owner.spnQuestMap = searchableSpinner {}.lparams(width = matchParent) {
                    margin = dimen(R.dimen.value_5)
                }

                linearLayout {
                    owner.spnQuestJoinCondition1 = searchableSpinner {}.lparams(width = 0) {
                        margin = dimen(R.dimen.value_5)
                        weight = 1F
                    }
                    owner.spnQuestJoinCondition2 = searchableSpinner {}.lparams(width = 0) {
                        margin = dimen(R.dimen.value_5)
                        weight = 1F
                    }
                }

                owner.spnQuestSuccessCondition = searchableSpinner {}.lparams(width = matchParent) {
                    margin = dimen(R.dimen.value_5)
                }
                linearLayout {
                    owner.spnQuestSuccessConditionType1 = searchableSpinner {}.lparams(width = 0) {
                        margin = dimen(R.dimen.value_5)
                        weight = 1F
                    }
                    owner.spnQuestSuccessConditionTypeItem1 = searchableSpinner {}.lparams { margin = dimen(R.dimen.value_5) }
                    owner.edtQuestSuccessConditionTypeNum1 = editText { setText("0") }.lparams { marginEnd = dimen(R.dimen.value_15) }
                }
                linearLayout {
                    owner.spnQuestSuccessConditionType2 = searchableSpinner {}.lparams(width = 0) {
                        margin = dimen(R.dimen.value_5)
                        weight = 1F
                    }
                    owner.spnQuestSuccessConditionTypeItem2 = searchableSpinner {}.lparams { margin = dimen(R.dimen.value_5) }
                    owner.edtQuestSuccessConditionTypeNum2 = editText { setText("0") }.lparams { marginEnd = dimen(R.dimen.value_15) }
                }

                owner.spnQuestBossIcon1 = searchableSpinner {}.lparams(width = matchParent) {
                    margin = dimen(R.dimen.value_5)
                }
                owner.spnQuestBossIcon2 = searchableSpinner {}.lparams(width = matchParent) {
                    margin = dimen(R.dimen.value_5)
                }
                owner.spnQuestBossIcon3 = searchableSpinner {}.lparams(width = matchParent) {
                    margin = dimen(R.dimen.value_5)
                }
                owner.spnQuestBossIcon4 = searchableSpinner {}.lparams(width = matchParent) {
                    margin = dimen(R.dimen.value_5)
                }
                owner.spnQuestBossIcon5 = searchableSpinner {}.lparams(width = matchParent) {
                    margin = dimen(R.dimen.value_5)
                }
            }
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
