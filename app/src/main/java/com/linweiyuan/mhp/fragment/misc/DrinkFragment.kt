package com.linweiyuan.mhp.fragment.misc

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.fragment.app.Fragment
import com.linweiyuan.mhp.R
import com.linweiyuan.mhp.activity.MainActivity
import com.linweiyuan.mhp.common.Constant
import com.linweiyuan.mhp.common.arrayAdapter
import com.linweiyuan.mhp.database.database
import com.linweiyuan.mhp.model.Drink
import com.linweiyuan.mhp.service.Callback
import com.linweiyuan.mhp.service.Service.codeService
import com.linweiyuan.mhp.ui.misc.DrinkFragmentUI
import com.linweiyuan.misc.model.Data
import com.toptoche.searchablespinnerlibrary.SearchableSpinner
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DrinkFragment : Fragment() {
    internal lateinit var spnDrinkSkill1: SearchableSpinner
    internal lateinit var spnDrinkSkill2: SearchableSpinner
    internal lateinit var spnDrinkSkill3: SearchableSpinner
    internal lateinit var spnDrinkSkill4: SearchableSpinner
    internal lateinit var spnDrinkSkill5: SearchableSpinner
    internal lateinit var btnDrink: Button

    private lateinit var ctx: Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        ctx = requireContext()
        val view = DrinkFragmentUI().createView(AnkoContext.create(ctx, this))
        init()
        return view
    }

    private fun init() {
        doAsync {
            lateinit var drinkSkillAdapter: ArrayAdapter<String>

            ctx.database.use {
                drinkSkillAdapter = arrayAdapter(Constant.TABLE_DRINK_SKILL, ctx)
            }

            uiThread {
                spnDrinkSkill1.setTitle(getString(R.string.title_drink))
                spnDrinkSkill2.setTitle(getString(R.string.title_drink))
                spnDrinkSkill3.setTitle(getString(R.string.title_drink))
                spnDrinkSkill4.setTitle(getString(R.string.title_drink))
                spnDrinkSkill5.setTitle(getString(R.string.title_drink))

                spnDrinkSkill1.adapter = drinkSkillAdapter
                spnDrinkSkill2.adapter = drinkSkillAdapter
                spnDrinkSkill3.adapter = drinkSkillAdapter
                spnDrinkSkill4.adapter = drinkSkillAdapter
                spnDrinkSkill5.adapter = drinkSkillAdapter
            }
        }
    }

    internal fun genDrinkCode() {
        val drink = Drink(
            skill1 = spnDrinkSkill1.selectedItemPosition,
            skill2 = spnDrinkSkill2.selectedItemPosition,
            skill3 = spnDrinkSkill3.selectedItemPosition,
            skill4 = spnDrinkSkill4.selectedItemPosition,
            skill5 = spnDrinkSkill5.selectedItemPosition
        )
        codeService.genDrinkCode(drink, object : Callback {
            override fun onSuccess(data: Data) {
                (activity as MainActivity).onSuccess(data)
            }

            override fun onFailure(data: Data) {}
        }, ctx)
    }
}
