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
import com.linweiyuan.mhp.component.MonsterItemComponent
import com.linweiyuan.mhp.database.database
import com.linweiyuan.mhp.ui.quest.QuestMonsterFragmentUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread

class QuestMonsterFragment : Fragment() {
    internal lateinit var llMonster: LinearLayout

    private lateinit var ctx: Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        ctx = requireContext()
        val view = QuestMonsterFragmentUI().createView(AnkoContext.create(ctx, this))
        init()
        return view
    }

    private fun init() {
        doAsync {
            lateinit var questMonsterAdapter: ArrayAdapter<String>
            lateinit var questMonsterAreaAdapter: ArrayAdapter<String>

            ctx.database.use {
                questMonsterAdapter = arrayAdapter(Constant.TABLE_QUEST_MONSTER, ctx)
                questMonsterAreaAdapter = arrayAdapter(Constant.TABLE_QUEST_AREA, ctx)
            }

            uiThread {
                for (i in 0 until 4) {
                    val monsterView = MonsterItemComponent().createView(AnkoContext.create(ctx, ctx))
                    monsterView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                    monsterView.find<Spinner>(R.id.spnQuestMonster).adapter = questMonsterAdapter
                    monsterView.find<Spinner>(R.id.spnQuestMonsterArea).adapter = questMonsterAreaAdapter
                    llMonster.addView(monsterView)
                }
            }
        }
    }
}
