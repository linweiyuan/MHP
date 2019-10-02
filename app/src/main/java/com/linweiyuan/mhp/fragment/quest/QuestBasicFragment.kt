package com.linweiyuan.mhp.fragment.quest

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.linweiyuan.mhp.R
import com.linweiyuan.mhp.common.Constant
import com.linweiyuan.mhp.common.arrayAdapter
import com.linweiyuan.mhp.database.database
import com.linweiyuan.mhp.ui.quest.QuestBasicFragmentUI
import com.toptoche.searchablespinnerlibrary.SearchableSpinner
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class QuestBasicFragment : Fragment() {
    internal lateinit var edtQuestName: EditText
    internal lateinit var edtQuestSuccess: EditText
    internal lateinit var edtQuestFailure: EditText
    internal lateinit var edtQuestContent: EditText
    internal lateinit var edtQuestMonster: EditText
    internal lateinit var edtQuestClient: EditText

    internal lateinit var edtQuestSuccessPts: EditText
    internal lateinit var edtQuestFailPts: EditText
    internal lateinit var edtQuestContractZ: EditText
    internal lateinit var edtQuestRewardZ: EditText
    internal lateinit var edtQuestMinute: EditText
    internal lateinit var edtQuestSecond: EditText

    internal lateinit var spnQuestStartArea: SearchableSpinner
    internal lateinit var spnQuestBossSkill: SearchableSpinner
    internal lateinit var spnQuestPickRank: SearchableSpinner
    internal lateinit var spnQuestBGM: SearchableSpinner
    internal lateinit var spnQuestReturnTime: SearchableSpinner
    internal lateinit var spnQuestType: SearchableSpinner
    internal lateinit var spnQuestRank: SearchableSpinner
    internal lateinit var spnQuestMap: SearchableSpinner
    internal lateinit var spnQuestJoinCondition1: SearchableSpinner
    internal lateinit var spnQuestJoinCondition2: SearchableSpinner
    internal lateinit var spnQuestSuccessCondition: SearchableSpinner
    internal lateinit var spnQuestSuccessConditionType1: SearchableSpinner
    internal lateinit var spnQuestSuccessConditionTypeItem1: SearchableSpinner
    internal lateinit var edtQuestSuccessConditionTypeNum1: EditText
    internal lateinit var spnQuestSuccessConditionType2: SearchableSpinner
    internal lateinit var spnQuestSuccessConditionTypeItem2: SearchableSpinner
    internal lateinit var edtQuestSuccessConditionTypeNum2: EditText
    internal lateinit var spnQuestBossIcon1: SearchableSpinner
    internal lateinit var spnQuestBossIcon2: SearchableSpinner
    internal lateinit var spnQuestBossIcon3: SearchableSpinner
    internal lateinit var spnQuestBossIcon4: SearchableSpinner
    internal lateinit var spnQuestBossIcon5: SearchableSpinner

    private lateinit var ctx: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ctx = requireContext()
        val view = QuestBasicFragmentUI().createView(AnkoContext.create(ctx, this))
        init()
        return view
    }

    private fun init() {
        doAsync {
            lateinit var questStartAreaAdapter: ArrayAdapter<String>
            lateinit var questBossSkillAdapter: ArrayAdapter<String>
            lateinit var questPickRankAdapter: ArrayAdapter<String>
            lateinit var questBGMAdapter: ArrayAdapter<String>
            lateinit var questReturnTimeAdapter: ArrayAdapter<String>
            lateinit var questTypeAdapter: ArrayAdapter<String>
            lateinit var questRankAdapter: ArrayAdapter<String>
            lateinit var questMapAdapter: ArrayAdapter<String>
            lateinit var questJoinConditionAdapter: ArrayAdapter<String>
            lateinit var questSuccessConditionAdapter: ArrayAdapter<String>
            lateinit var questSuccessConditionTypeAdapter: ArrayAdapter<String>

            ctx.database.use {
                questStartAreaAdapter = arrayAdapter(Constant.TABLE_QUEST_START_AREA, ctx)
                questBossSkillAdapter = arrayAdapter(Constant.TABLE_QUEST_BOSS_SKILL, ctx)
                questPickRankAdapter = arrayAdapter(Constant.TABLE_QUEST_PICK_RANK, ctx)
                questBGMAdapter = arrayAdapter(Constant.TABLE_QUEST_BGM, ctx)
                questReturnTimeAdapter = arrayAdapter(Constant.TABLE_QUEST_RETURN_TIME, ctx)
                questTypeAdapter = arrayAdapter(Constant.TABLE_QUEST_TYPE, ctx)
                questRankAdapter = arrayAdapter(Constant.TABLE_QUEST_RANK, ctx)
                questMapAdapter = arrayAdapter(Constant.TABLE_QUEST_MAP, ctx)
                questJoinConditionAdapter =
                    arrayAdapter(Constant.TABLE_QUEST_JOIN_CONDITION, ctx)
                questSuccessConditionAdapter =
                    arrayAdapter(Constant.TABLE_QUEST_SUCCESS_CONDITION, ctx)
                questSuccessConditionTypeAdapter =
                    arrayAdapter(Constant.TABLE_QUEST_SUCCESS_CONDITION_TYPE, ctx)
            }

            uiThread {
                spnQuestStartArea.setTitle(getString(R.string.quest_start_area))
                spnQuestBossSkill.setTitle(getString(R.string.quest_boss_skill))
                spnQuestPickRank.setTitle(getString(R.string.quest_pick_off_rank))
                spnQuestBGM.setTitle(getString(R.string.quest_bgm))
                spnQuestReturnTime.setTitle(getString(R.string.quest_return_time))
                spnQuestType.setTitle(getString(R.string.quest_type))
                spnQuestRank.setTitle(getString(R.string.quest_rank))
                spnQuestMap.setTitle(getString(R.string.quest_map))
                spnQuestJoinCondition1.setTitle(getString(R.string.quest_join_condition_1))
                spnQuestJoinCondition2.setTitle(getString(R.string.quest_join_condition_2))
                spnQuestSuccessCondition.setTitle(getString(R.string.quest_success_condition_0))
                spnQuestSuccessConditionType1.setTitle(getString(R.string.quest_success_condition_1))
                spnQuestSuccessConditionType2.setTitle(getString(R.string.quest_success_condition_2))
                spnQuestSuccessConditionTypeItem1.setTitle(getString(R.string.quest_success_condition_1_item))
                spnQuestSuccessConditionTypeItem2.setTitle(getString(R.string.quest_success_condition_2_item))
                spnQuestBossIcon1.setTitle(getString(R.string.quest_boss_icon_1))
                spnQuestBossIcon2.setTitle(getString(R.string.quest_boss_icon_2))
                spnQuestBossIcon3.setTitle(getString(R.string.quest_boss_icon_3))
                spnQuestBossIcon4.setTitle(getString(R.string.quest_boss_icon_4))
                spnQuestBossIcon5.setTitle(getString(R.string.quest_boss_icon_5))

                spnQuestStartArea.adapter = questStartAreaAdapter
                spnQuestBossSkill.adapter = questBossSkillAdapter
                spnQuestPickRank.adapter = questPickRankAdapter
                spnQuestBGM.adapter = questBGMAdapter
                spnQuestReturnTime.adapter = questReturnTimeAdapter
                spnQuestType.adapter = questTypeAdapter
                spnQuestRank.adapter = questRankAdapter
                spnQuestMap.adapter = questMapAdapter
                spnQuestJoinCondition1.adapter = questJoinConditionAdapter
                spnQuestJoinCondition2.adapter = questJoinConditionAdapter
                spnQuestSuccessCondition.adapter = questSuccessConditionAdapter
                spnQuestSuccessConditionType1.adapter = questSuccessConditionTypeAdapter
                spnQuestSuccessConditionType2.adapter = questSuccessConditionTypeAdapter
            }
        }
    }
}
