package com.linweiyuan.mhp.fragment.misc

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import com.linweiyuan.mhp.R
import com.linweiyuan.mhp.activity.MainActivity
import com.linweiyuan.mhp.common.Constant
import com.linweiyuan.mhp.common.arrayAdapter
import com.linweiyuan.mhp.database.database
import com.linweiyuan.mhp.model.Stone
import com.linweiyuan.mhp.service.Callback
import com.linweiyuan.mhp.service.Service.codeService
import com.linweiyuan.mhp.ui.misc.StoneFragmentUI
import com.linweiyuan.misc.model.Data
import com.toptoche.searchablespinnerlibrary.SearchableSpinner
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread

class StoneFragment : androidx.fragment.app.Fragment() {
    internal lateinit var spnStoneSkill1: SearchableSpinner
    internal lateinit var spnStonePoint1: SearchableSpinner
    internal lateinit var spnStoneSkill2: SearchableSpinner
    internal lateinit var spnStonePoint2: SearchableSpinner
    internal lateinit var spnStoneRarity: SearchableSpinner
    internal lateinit var rgStoneSlots: RadioGroup

    internal lateinit var btnStone: Button

    private lateinit var ctx: Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        ctx = requireContext()
        val view = StoneFragmentUI().createView(AnkoContext.create(ctx, this))
        init()
        return view
    }

    private fun init() {
        doAsync {
            lateinit var stoneSkillAdapter: ArrayAdapter<String>
            lateinit var stoneSkillPointAdapter: ArrayAdapter<String>
            lateinit var stoneRarityAdapter: ArrayAdapter<String>

            ctx.database.use {
                stoneSkillAdapter = arrayAdapter(Constant.TABLE_STONE_SKILL, ctx)
                stoneSkillPointAdapter = arrayAdapter(Constant.TABLE_STONE_SKILL_POINT, ctx)
                stoneRarityAdapter = arrayAdapter(Constant.TABLE_STONE_RARITY, ctx)
            }

            uiThread {
                spnStoneSkill1.setTitle(getString(R.string.stone_skill_name))
                spnStonePoint1.setTitle(getString(R.string.stone_skill_point))
                spnStoneSkill2.setTitle(getString(R.string.stone_skill_name))
                spnStonePoint2.setTitle(getString(R.string.stone_skill_point))
                spnStoneRarity.setTitle(getString(R.string.stone_skill_rarity))

                spnStoneSkill1.adapter = stoneSkillAdapter
                spnStoneSkill2.adapter = stoneSkillAdapter

                spnStonePoint1.adapter = stoneSkillPointAdapter
                spnStonePoint2.adapter = stoneSkillPointAdapter

                spnStoneRarity.adapter = stoneRarityAdapter
            }
        }
    }

    internal fun genStoneCode() {
        val stone = Stone(
            skill1 = spnStoneSkill1.selectedItemPosition,
            point1 = spnStonePoint1.selectedItemPosition + 1,
            skill2 = spnStoneSkill2.selectedItemPosition,
            point2 = spnStonePoint2.selectedItemPosition + 1,
            rarity = spnStoneRarity.selectedItemPosition + 1,
            slot = (requireActivity().find<RadioButton>(rgStoneSlots.checkedRadioButtonId)).text.toString().toInt()
        )
        codeService.genStoneCode(stone, object : Callback {
            override fun onSuccess(data: Data) {
                (activity as MainActivity).onSuccess(data)
            }

            override fun onFailure(data: Data) {}
        }, ctx)
    }
}
