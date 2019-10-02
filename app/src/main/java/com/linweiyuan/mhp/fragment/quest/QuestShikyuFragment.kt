package com.linweiyuan.mhp.fragment.quest

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.linweiyuan.mhp.R
import com.linweiyuan.mhp.common.Constant
import com.linweiyuan.mhp.common.arrayAdapter
import com.linweiyuan.mhp.component.ShikyuItemComponent
import com.linweiyuan.mhp.database.database
import com.linweiyuan.mhp.ui.quest.QuestShikyuFragmentUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast

class QuestShikyuFragment : Fragment() {
    internal lateinit var llShikyu: LinearLayout

    private lateinit var questItemAdapter: ArrayAdapter<String>

    private lateinit var ctx: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ctx = requireContext()
        val view = QuestShikyuFragmentUI().createView(AnkoContext.create(ctx, this))
        init()
        return view
    }

    private fun init() {
        ctx.database.use {
            questItemAdapter = arrayAdapter(Constant.TABLE_QUEST_ITEM, ctx)
        }
        addShikyuView(null)
    }

    private fun addShikyuView(view: View?) {
        if (llShikyu.childCount == 40) {
            requireActivity().toast(getString(R.string.shikyu_list_more_than_40_hint))
            return
        }
        val shikyuView = ShikyuItemComponent().createView(AnkoContext.create(ctx, ctx))
        if (view == null) {
            shikyuView.find<View>(R.id.btnShikyuOperate).onClick {
                addShikyuView(view)
            }
        }
        shikyuView.layoutParams =
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        shikyuView.find<Spinner>(R.id.spnQuestShikyu).adapter = questItemAdapter
        llShikyu.addView(shikyuView)
        reconfigureView()
    }

    private fun reconfigureView() {
        val childCount = llShikyu.childCount
        for (i in 0 until childCount) {
            val shikyuView = llShikyu.getChildAt(i)
            val btnRemove = shikyuView.find<Button>(R.id.btnShikyuOperate)
            btnRemove.text = "-删除"
            btnRemove.onClick {
                llShikyu.removeView(shikyuView)
                reconfigureView()
            }
            if (i == childCount - 1) {
                val btnAdd = shikyuView.find<Button>(R.id.btnShikyuOperate)
                btnAdd.text = "+增加"
                btnAdd.onClick {
                    addShikyuView(view)
                }
            }
        }
    }
}
