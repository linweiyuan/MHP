package com.linweiyuan.mhp.fragment.misc

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.linweiyuan.mhp.R
import com.linweiyuan.mhp.activity.MainActivity
import com.linweiyuan.mhp.common.Constant
import com.linweiyuan.mhp.common.arrayAdapter
import com.linweiyuan.mhp.database.database
import com.linweiyuan.mhp.model.BossNum
import com.linweiyuan.mhp.model.QuestNum
import com.linweiyuan.mhp.model.Time
import com.linweiyuan.mhp.model.WeaponNum
import com.linweiyuan.mhp.service.Callback
import com.linweiyuan.mhp.service.Service.codeService
import com.linweiyuan.mhp.ui.misc.ValueFragmentUI
import com.linweiyuan.misc.model.Data
import com.toptoche.searchablespinnerlibrary.SearchableSpinner
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.db.RowParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ValueFragment : Fragment() {
    internal lateinit var edtTimeHour: EditText
    internal lateinit var edtTimeMinute: EditText
    internal lateinit var btnTime: Button

    internal lateinit var spnWeaponType: SearchableSpinner
    internal lateinit var spnWeaponPlace: SearchableSpinner
    internal lateinit var edtWeaponValue: EditText
    internal lateinit var btnWeaponNum: Button

    internal lateinit var spnCardQuestType: SearchableSpinner
    internal lateinit var edtCardQuestValue: EditText
    internal lateinit var btnCardQuest: Button

    // 给变量命名真要命
    internal lateinit var spnBossRecordBoss: SearchableSpinner
    internal lateinit var edtBossRecordKillNum: EditText
    internal lateinit var edtBossRecordCatchNum: EditText
    internal lateinit var btnBossNum: Button

    private lateinit var ctx: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ctx = requireContext()
        val view = ValueFragmentUI().createView(AnkoContext.create(ctx, this))
        init()
        return view
    }

    private fun init() {
        doAsync {
            lateinit var weaponTypeAdapter: ArrayAdapter<String>
            lateinit var weaponPlaceAdapter: ArrayAdapter<String>
            lateinit var cardQuestTypeAdapter: ArrayAdapter<String>
            lateinit var bossRecordBossAdapter: ArrayAdapter<String>

            ctx.database.use {
                weaponTypeAdapter = arrayAdapter(Constant.TABLE_WEAPON_TYPE, ctx)
                weaponPlaceAdapter = arrayAdapter(Constant.TABLE_WEAPON_PLACE, ctx)
                cardQuestTypeAdapter = arrayAdapter(Constant.TABLE_CARD_QUEST_TYPE, ctx)
                bossRecordBossAdapter = arrayAdapter(Constant.TABLE_QUEST_BOSS, ctx)
                bossRecordBossAdapter.remove("无")
            }

            uiThread {
                spnWeaponType.setTitle(getString(R.string.weapon_type))
                spnWeaponPlace.setTitle(getString(R.string.weapon_place))
                spnCardQuestType.setTitle(getString(R.string.quest_type))
                spnBossRecordBoss.setTitle(getString(R.string.boss_record))

                spnWeaponType.adapter = weaponTypeAdapter
                spnWeaponPlace.adapter = weaponPlaceAdapter
                spnCardQuestType.adapter = cardQuestTypeAdapter
                spnBossRecordBoss.adapter = bossRecordBossAdapter
            }
        }
    }

    @SuppressLint("SetTextI18n")
    internal fun genTimeCode() {
        if (edtTimeHour.text.toString().trim().isEmpty()) {
            edtTimeHour.setText("0")
        }
        if (edtTimeMinute.text.toString().trim().isEmpty()) {
            edtTimeMinute.setText("0")
        }
        var hour = edtTimeHour.text.toString().trim().toInt()
        var minute = edtTimeMinute.text.toString().trim().toInt()
        if (hour > 1) {
            edtTimeHour.setText("1")
            hour = 1
        }
        if (minute > 59) {
            edtTimeHour.setText("60")
            minute = 59
        }
        val time = Time(
            hour = hour,
            minute = minute
        )
        codeService.genTimeCode(time, object : Callback {
            override fun onSuccess(data: Data) {
                (activity as MainActivity).onSuccess(data)
            }

            override fun onFailure(data: Data) {

            }
        }, ctx)
    }

    internal fun genWeaponNumCode() {
        if (edtWeaponValue.text.toString().trim().isEmpty()) {
            edtWeaponValue.setText("0")
        }
        val weaponNum = WeaponNum(
            type = spnWeaponType.selectedItemPosition,
            place = spnWeaponPlace.selectedItemPosition,
            value = edtWeaponValue.text.toString().trim().toInt()
        )
        codeService.genWeaponNumCode(weaponNum, object : Callback {
            override fun onSuccess(data: Data) {
                (activity as MainActivity).onSuccess(data)
            }

            override fun onFailure(data: Data) {

            }
        }, ctx)
    }

    internal fun genQuestNumCode() {
        if (edtCardQuestValue.text.toString().trim().isEmpty()) {
            edtCardQuestValue.setText("0")
        }
        val questNum = QuestNum(
            type = spnCardQuestType.selectedItemPosition,
            value = edtCardQuestValue.text.toString().trim().toInt()
        )
        codeService.genQuestNumCode(questNum, object : Callback {
            override fun onSuccess(data: Data) {
                (activity as MainActivity).onSuccess(data)
            }

            override fun onFailure(data: Data) {

            }
        }, ctx)
    }

    internal fun genBossNumCode() {
        if (edtBossRecordKillNum.text.toString().trim().isEmpty()) {
            edtBossRecordKillNum.setText("0")
        }
        if (edtBossRecordCatchNum.text.toString().trim().isEmpty()) {
            edtBossRecordCatchNum.setText("0")
        }
        lateinit var gameId: String
        doAsync {
            ctx.database.use {
                gameId = select(Constant.TABLE_QUEST_BOSS, "game_id").whereArgs(
                    "(id = {id} + 2)",
                    "id" to spnBossRecordBoss.selectedItemPosition
                ).parseSingle(object :
                    RowParser<String> {
                    override fun parseRow(columns: Array<Any?>): String {
                        return columns[0] as String
                    }
                })
            }
            val bossNum = BossNum(
                gameId = gameId,
                killNum = edtBossRecordKillNum.text.toString().trim().toInt(),
                catchNum = edtBossRecordCatchNum.text.toString().trim().toInt()
            )
            uiThread {
                codeService.genBossNumCode(bossNum, object : Callback {
                    override fun onSuccess(data: Data) {
                        (activity as MainActivity).onSuccess(data)
                    }

                    override fun onFailure(data: Data) {

                    }
                }, ctx)
            }
        }
    }
}
