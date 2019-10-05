package com.linweiyuan.mhp.fragment.quest

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.linweiyuan.mhp.R
import com.linweiyuan.mhp.activity.MainActivity
import com.linweiyuan.mhp.adapter.PagerAdapter
import com.linweiyuan.mhp.common.Constant
import com.linweiyuan.mhp.common.arrayAdapter
import com.linweiyuan.mhp.database.database
import com.linweiyuan.mhp.model.*
import com.linweiyuan.mhp.service.Callback
import com.linweiyuan.mhp.service.Service.codeService
import com.linweiyuan.misc.model.Data
import com.qmuiteam.qmui.widget.QMUITabSegment
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton
import org.jetbrains.anko.db.RowParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class QuestFragment : Fragment(), ViewPager.OnPageChangeListener, QMUITabSegment.OnTabSelectedListener {
    private val questBasicFragment: QuestBasicFragment by lazy { QuestBasicFragment() }
    private val questShikyuFragment: QuestShikyuFragment by lazy { QuestShikyuFragment() }
    private val questHosyuFragment: QuestHosyuFragment by lazy { QuestHosyuFragment() }
    private val questMonsterFragment: QuestMonsterFragment by lazy { QuestMonsterFragment() }
    private val questBossFragment: QuestBossFragment by lazy { QuestBossFragment() }

    private lateinit var viewPagerQuest: ViewPager
    private lateinit var tabSegmentQuest: QMUITabSegment
    private lateinit var btnCreateQuestCode: QMUIRoundButton

    private lateinit var ctx: Context

    private lateinit var basic: Basic
    private lateinit var shikyus: List<Shikyu>
    private lateinit var hosyus: List<Hosyu>
    private lateinit var monsters: List<Monster>
    private lateinit var bosses: List<Boss>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        ctx = requireContext()
        val view = View.inflate(ctx, R.layout.fragment_quest, null)
        init(view)
        return view
    }

    private fun init(view: View) {
        val fragmentList = mutableListOf(questBasicFragment, questShikyuFragment, questHosyuFragment, questMonsterFragment, questBossFragment)

        viewPagerQuest = view.find(R.id.viewPagerQuest)
        viewPagerQuest.offscreenPageLimit = fragmentList.size
        val pagerAdapter = PagerAdapter(fragmentList, activity!!.supportFragmentManager)
        viewPagerQuest.adapter = pagerAdapter
        viewPagerQuest.addOnPageChangeListener(this)

        tabSegmentQuest = view.find<QMUITabSegment>(R.id.tabSegmentQuest)
            .addTab(QMUITabSegment.Tab(getString(R.string.quest_basic)))
            .addTab(QMUITabSegment.Tab(getString(R.string.quest_shikyu)))
            .addTab(QMUITabSegment.Tab(getString(R.string.quest_hosyu)))
            .addTab(QMUITabSegment.Tab(getString(R.string.quest_monster)))
            .addTab(QMUITabSegment.Tab(getString(R.string.quest_boss)))

        tabSegmentQuest.selectTab(0)
        tabSegmentQuest.setHasIndicator(true)
        tabSegmentQuest.mode = QMUITabSegment.MODE_SCROLLABLE
        tabSegmentQuest.setDefaultTabIconPosition(QMUITabSegment.ICON_POSITION_TOP)
        tabSegmentQuest.addOnTabSelectedListener(this)
        tabSegmentQuest.notifyDataChanged()

        pagerAdapter.notifyDataSetChanged()

        doAdapter()

        btnCreateQuestCode = view.find(R.id.btnCreateQuestCode)
        btnCreateQuestCode.onClick { createQuestCode() }
    }

    private fun doAdapter() {
        doAsync {
            lateinit var questItemAdapter: ArrayAdapter<String>
            lateinit var questBossAdapter: ArrayAdapter<String>
            lateinit var questMonBossMiscAdapter: ArrayAdapter<String>

            ctx.database.use {
                val bossList = select(Constant.TABLE_QUEST_BOSS)
                    .parseList(object : RowParser<String> {
                        override fun parseRow(columns: Array<Any?>): String {
                            return columns[1] as String
                        }
                    })
                val monsterList = select(Constant.TABLE_QUEST_MONSTER)
                    .parseList(object : RowParser<String> {
                        override fun parseRow(columns: Array<Any?>): String {
                            return columns[1] as String
                        }
                    })

                questBossAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_item, bossList)
                questItemAdapter = arrayAdapter(Constant.TABLE_QUEST_ITEM, ctx)
                questMonBossMiscAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_item, mutableListOf<String>().apply {
                    addAll(bossList)
                    addAll(monsterList.filter { it != "无" })
                })
            }

            uiThread {
                initBasicAdapter(questMonBossMiscAdapter, questItemAdapter, questBossAdapter)
            }
        }
    }

    private fun initBasicAdapter(questMonBossMiscAdapter: ArrayAdapter<String>, questItemAdapter: ArrayAdapter<String>, questBossAdapter: ArrayAdapter<String>) {
        questBasicFragment.spnQuestSuccessConditionType1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                questBasicFragment.spnQuestSuccessConditionTypeItem1.adapter = if (position == 0) questMonBossMiscAdapter else questItemAdapter
            }
        }
        questBasicFragment.spnQuestSuccessConditionType2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                questBasicFragment.spnQuestSuccessConditionTypeItem2.adapter = if (position == 0) questMonBossMiscAdapter else questItemAdapter
            }
        }
        questBasicFragment.spnQuestSuccessConditionTypeItem1.adapter = questMonBossMiscAdapter
        questBasicFragment.spnQuestSuccessConditionTypeItem2.adapter = questMonBossMiscAdapter
        questBasicFragment.spnQuestBossIcon1.adapter = questBossAdapter
        questBasicFragment.spnQuestBossIcon2.adapter = questBossAdapter
        questBasicFragment.spnQuestBossIcon3.adapter = questBossAdapter
        questBasicFragment.spnQuestBossIcon4.adapter = questBossAdapter
        questBasicFragment.spnQuestBossIcon5.adapter = questBossAdapter
    }

    override fun onPageScrollStateChanged(state: Int) {}

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    override fun onPageSelected(position: Int) {
        tabSegmentQuest.selectTab(position, true, true)
    }

    override fun onDoubleTap(index: Int) {}

    override fun onTabReselected(index: Int) {}

    override fun onTabUnselected(index: Int) {}

    override fun onTabSelected(index: Int) {
        viewPagerQuest.currentItem = index
    }

    private fun createQuestCode() {
        if (questBasicFragment.edtQuestName.text.toString().trim().isEmpty()) {
            requireActivity().toast(getString(R.string.quest_name_empty_hint))
            return
        }
        doMakeQuest()
    }

    private fun doMakeQuest() {
        doAsync {
            ctx.database.use {
                basic = makeBasic(this)
                shikyus = makeShikyuList(this)
                hosyus = makeHosyuList(this)
                monsters = makeMonsterList(this)
                bosses = makeBossList(this)

                val quest = Quest(
                    basic = basic,
                    shikyus = shikyus,
                    hosyus = hosyus,
                    monsters = monsters,
                    bosses = bosses
                )
                uiThread {
                    codeService.genQuestCode(quest, object : Callback {
                        override fun onSuccess(data: Data) {
                            (activity as MainActivity).onSuccess(data)
                        }

                        override fun onFailure(data: Data) {}
                    }, ctx)
                }
            }
        }
    }

    private fun makeBasic(db: SQLiteDatabase): Basic = with(questBasicFragment) {
        val type1 = spnQuestSuccessConditionType1.selectedItemPosition
        val item1 = spnQuestSuccessConditionTypeItem1.selectedItemPosition

        val type2 = spnQuestSuccessConditionType2.selectedItemPosition
        val item2 = spnQuestSuccessConditionTypeItem2.selectedItemPosition

        Basic(
            edtQuestName.text.toString().trim(),
            edtQuestSuccess.text.toString().trim(),
            edtQuestFailure.text.toString().trim(),
            edtQuestContent.text.toString().trim(),
            edtQuestMonster.text.toString().trim(),
            edtQuestClient.text.toString().trim(),
            edtQuestSuccessPts.text.toString().trim().toInt(),
            edtQuestFailPts.text.toString().trim().toInt(),
            spnQuestStartArea.selectedItemPosition,
            spnQuestBossSkill.selectedItemPosition,
            spnQuestPickRank.selectedItemPosition,
            spnQuestBGM.selectedItemPosition,
            spnQuestReturnTime.selectedItemPosition,
            spnQuestType.selectedItemPosition,
            edtQuestContractZ.text.toString().trim().toInt(),
            edtQuestRewardZ.text.toString().trim().toInt(),
            edtQuestMinute.text.toString().trim().toInt(),
            edtQuestSecond.text.toString().trim().toInt(),
            spnQuestRank.selectedItemPosition,
            spnQuestMap.selectedItemPosition,
            spnQuestJoinCondition1.selectedItemPosition,
            spnQuestJoinCondition2.selectedItemPosition,
            spnQuestSuccessCondition.selectedItemPosition,
            type1,
            db.select(if (type1 == 0) (if (item1 <= 40) Constant.TABLE_QUEST_BOSS else Constant.TABLE_QUEST_MONSTER) else Constant.TABLE_QUEST_ITEM, "game_id")
                .whereArgs("(id = {id} + 1)", "id" to if (type1 == 1 || item1 <= 40) item1 else (item1 - 40))
                .parseSingle(
                    object : RowParser<String> {
                        override fun parseRow(columns: Array<Any?>): String {
                            return columns[0] as String
                        }
                    }),
            edtQuestSuccessConditionTypeNum1.text.toString().trim().toInt(),
            type2,
            db.select(if (type2 == 0) (if (item2 <= 40) Constant.TABLE_QUEST_BOSS else Constant.TABLE_QUEST_MONSTER) else Constant.TABLE_QUEST_ITEM, "game_id")
                .whereArgs("(id = {id} + 1)", "id" to if (type2 == 1 || item2 <= 40) item2 else (item2 - 40))
                .parseSingle(
                    object : RowParser<String> {
                        override fun parseRow(columns: Array<Any?>): String {
                            return columns[0] as String
                        }
                    }),
            edtQuestSuccessConditionTypeNum2.text.toString().trim().toInt(),
            db.select(Constant.TABLE_QUEST_BOSS, "game_id")
                .whereArgs("(id = {id} + 1)", "id" to spnQuestBossIcon1.selectedItemPosition)
                .parseSingle(object : RowParser<String> {
                    override fun parseRow(columns: Array<Any?>): String {
                        return columns[0] as String
                    }
                }),
            db.select(Constant.TABLE_QUEST_BOSS, "game_id")
                .whereArgs("(id = {id} + 1)", "id" to spnQuestBossIcon2.selectedItemPosition)
                .parseSingle(object : RowParser<String> {
                    override fun parseRow(columns: Array<Any?>): String {
                        return columns[0] as String
                    }
                }),
            db.select(Constant.TABLE_QUEST_BOSS, "game_id")
                .whereArgs("(id = {id} + 1)", "id" to spnQuestBossIcon3.selectedItemPosition)
                .parseSingle(object : RowParser<String> {
                    override fun parseRow(columns: Array<Any?>): String {
                        return columns[0] as String
                    }
                }),
            db.select(Constant.TABLE_QUEST_BOSS, "game_id")
                .whereArgs("(id = {id} + 1)", "id" to spnQuestBossIcon4.selectedItemPosition)
                .parseSingle(object : RowParser<String> {
                    override fun parseRow(columns: Array<Any?>): String {
                        return columns[0] as String
                    }
                }),
            db.select(Constant.TABLE_QUEST_BOSS, "game_id")
                .whereArgs("(id = {id} + 1)", "id" to spnQuestBossIcon5.selectedItemPosition)
                .parseSingle(object : RowParser<String> {
                    override fun parseRow(columns: Array<Any?>): String {
                        return columns[0] as String
                    }
                })
        )
    }

    private fun makeShikyuList(db: SQLiteDatabase): MutableList<Shikyu> = mutableListOf<Shikyu>().apply {
        with(questShikyuFragment) {
            for (i in 0 until llShikyu.childCount) {
                val shikyuView = llShikyu.getChildAt(i)
                val spnQuestshikyu = shikyuView.find<Spinner>(R.id.spnQuestShikyu)
                val shikyuIndex = spnQuestshikyu.selectedItemPosition
                if (shikyuIndex != 0) {
                    val shikyuNumStr = shikyuView.find<EditText>(R.id.edtQuestShikyuNum).text.toString().trim()
                    if (shikyuNumStr.isNotEmpty()) {
                        val shikyuNum = shikyuNumStr.toInt()
                        if (shikyuNum != 0) {
                            add(
                                Shikyu(
                                    db.select(Constant.TABLE_QUEST_ITEM, "game_id")
                                        .whereArgs("(id = {id} + 1)", "id" to shikyuIndex)
                                        .parseSingle(object : RowParser<String> {
                                            override fun parseRow(columns: Array<Any?>): String {
                                                return columns[0] as String
                                            }
                                        }),
                                    shikyuNum
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    private fun makeHosyuList(db: SQLiteDatabase): MutableList<Hosyu> = mutableListOf<Hosyu>().apply {
        with(questHosyuFragment) {
            for (i in 0 until llHosyu.childCount) {
                val hosyuView = llHosyu.getChildAt(i)
                val hosyuIndex = hosyuView.find<Spinner>(R.id.spnQuestHosyu).selectedItemPosition
                if (hosyuIndex != 0) {
                    val hosyuNumStr = hosyuView.find<EditText>(R.id.edtQuestHosyuNum).text.toString().trim()
                    if (hosyuNumStr.isNotEmpty()) {
                        val hosyuNum = hosyuNumStr.toInt()
                        if (hosyuNum != 0) {
                            add(
                                Hosyu(
                                    db.select(Constant.TABLE_QUEST_ITEM, "game_id")
                                        .whereArgs("(id = {id} + 1)", "id" to hosyuIndex)
                                        .parseSingle(object : RowParser<String> {
                                            override fun parseRow(columns: Array<Any?>): String {
                                                return columns[0] as String
                                            }
                                        }),
                                    hosyuNum
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    private fun makeMonsterList(db: SQLiteDatabase): MutableList<Monster> = mutableListOf<Monster>().apply {
        with(questMonsterFragment) {
            for (i in 0 until llMonster.childCount) {
                val monsterView = llMonster.getChildAt(i)
                val monsterIndex = monsterView.find<Spinner>(R.id.spnQuestMonster).selectedItemPosition
                if (monsterIndex != 0) {
                    val monsterNumStr = monsterView.find<EditText>(R.id.edtQuestMonsterNum).text.toString().trim()
                    if (monsterNumStr.isNotEmpty()) {
                        val monsterNum = monsterNumStr.toInt()
                        if (monsterNum != 0) {
                            add(
                                Monster(
                                    db.select(Constant.TABLE_QUEST_MONSTER, "game_id")
                                        .whereArgs("(id = {id} + 1)", "id" to monsterIndex)
                                        .parseSingle(object : RowParser<String> {
                                            override fun parseRow(columns: Array<Any?>): String {
                                                return columns[0] as String
                                            }
                                        }),
                                    monsterView.find<Spinner>(R.id.spnQuestMonsterArea).selectedItemPosition,
                                    monsterNum
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    private fun makeBossList(db: SQLiteDatabase): MutableList<Boss> = mutableListOf<Boss>().apply {
        with(questBossFragment) {
            for (i in 0 until llBoss.childCount) {
                val bossView = llBoss.getChildAt(i)
                val bossIndex = bossView.find<Spinner>(R.id.spnQuestBoss).selectedItemPosition
                if (bossIndex != 0) {
                    val bossNumStr = bossView.find<EditText>(R.id.edtQuestBossNum).text.toString().trim()
                    if (bossNumStr.isNotEmpty()) {
                        val bossNum = bossNumStr.toInt()
                        if (bossNum != 0) {
                            add(
                                Boss(
                                    db.select(Constant.TABLE_QUEST_BOSS, "game_id")
                                        .whereArgs("(id = {id} + 1)", "id" to bossIndex)
                                        .parseSingle(object : RowParser<String> {
                                            override fun parseRow(columns: Array<Any?>): String {
                                                return columns[0] as String
                                            }
                                        }),
                                    bossView.find<Spinner>(R.id.spnQuestBossArea).selectedItemPosition,
                                    bossNum,
                                    bossView.find<EditText>(R.id.edtQuestBossSize).text.toString().trim().toInt(),
                                    bossView.find<Spinner>(R.id.spnQuestBossHP).selectedItemPosition,
                                    bossView.find<Spinner>(R.id.spnQuestBossStrength).selectedItemPosition,
                                    bossView.find<Spinner>(R.id.spnQuestBossEndurance).selectedItemPosition,
                                    bossView.find<Spinner>(R.id.spnQuestBossFatigue).selectedItemPosition,
                                    bossView.find<Spinner>(R.id.spnQuestBossStatus).selectedItemPosition,
                                    bossView.find<Spinner>(R.id.spnQuestBossRound).selectedItemPosition
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}
