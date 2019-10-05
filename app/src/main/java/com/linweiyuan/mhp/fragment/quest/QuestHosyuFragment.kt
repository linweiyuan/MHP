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
import com.linweiyuan.mhp.component.HosyuItemComponent
import com.linweiyuan.mhp.database.database
import com.linweiyuan.mhp.ui.quest.QuestHosyuFragmentUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class QuestHosyuFragment : Fragment() {
    internal lateinit var llHosyu: LinearLayout

    private lateinit var questItemAdapter: ArrayAdapter<String>

    private lateinit var ctx: Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        ctx = requireContext()
        val view = QuestHosyuFragmentUI().createView(AnkoContext.create(ctx, this))
        init()
        return view
    }

    private fun init() {
        ctx.database.use {
            questItemAdapter = arrayAdapter(Constant.TABLE_QUEST_ITEM, ctx)
        }
        val hosyuView = HosyuItemComponent().createView(AnkoContext.create(ctx, ctx))
        hosyuView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        hosyuView.find<Spinner>(R.id.spnQuestHosyu).adapter = questItemAdapter
        llHosyu.addView(hosyuView)
    }
}
