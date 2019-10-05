package com.linweiyuan.mhp.fragment.quest

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.linweiyuan.mhp.R
import com.linweiyuan.mhp.common.Constant
import com.linweiyuan.mhp.common.arrayAdapter
import com.linweiyuan.mhp.component.BossItemComponent
import com.linweiyuan.mhp.database.database
import com.linweiyuan.mhp.ui.quest.QuestBossFragmentUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread

class QuestBossFragment : Fragment() {
    internal lateinit var llBoss: LinearLayout

    private lateinit var ctx: Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        ctx = requireContext()
        val view = QuestBossFragmentUI().createView(AnkoContext.create(ctx, this))
        init()
        return view
    }

    private fun init() {
        doAsync {
            lateinit var questBossAdapter: ArrayAdapter<String>
            lateinit var questAreaAdapter: ArrayAdapter<String>
            lateinit var questBossPointAdapter: ArrayAdapter<String>
            lateinit var questBossStatusAdapter: ArrayAdapter<String>
            lateinit var questBossRoundAdapter: ArrayAdapter<String>

            ctx.database.use {
                questBossAdapter = arrayAdapter(Constant.TABLE_QUEST_BOSS, ctx)
                questAreaAdapter = arrayAdapter(Constant.TABLE_QUEST_AREA, ctx)
                questBossPointAdapter = arrayAdapter(Constant.TABLE_QUEST_BOSS_POINT, ctx)
                questBossStatusAdapter = arrayAdapter(Constant.TABLE_QUEST_BOSS_STATUS, ctx)
                questBossRoundAdapter = arrayAdapter(Constant.TABLE_QUEST_BOSS_ROUND, ctx)
            }

            uiThread {
                for (i in 0 until 4) {
                    val bossView = BossItemComponent().createView(AnkoContext.create(ctx, ctx))
                    bossView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                    bossView.find<Spinner>(R.id.spnQuestBoss).adapter = questBossAdapter
                    bossView.find<Spinner>(R.id.spnQuestBossArea).adapter = questAreaAdapter
                    bossView.find<Spinner>(R.id.spnQuestBossHP).adapter = questBossPointAdapter
                    bossView.find<Spinner>(R.id.spnQuestBossStrength).adapter = questBossPointAdapter
                    bossView.find<Spinner>(R.id.spnQuestBossEndurance).adapter = questBossPointAdapter
                    bossView.find<Spinner>(R.id.spnQuestBossFatigue).adapter = questBossPointAdapter
                    bossView.find<Spinner>(R.id.spnQuestBossStatus).adapter = questBossStatusAdapter
                    bossView.find<Spinner>(R.id.spnQuestBossRound).adapter = questBossRoundAdapter
                    llBoss.addView(bossView)
                }
            }
        }
    }
}
