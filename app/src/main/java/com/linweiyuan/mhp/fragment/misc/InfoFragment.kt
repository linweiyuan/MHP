package com.linweiyuan.mhp.fragment.misc

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.linweiyuan.mhp.R
import com.linweiyuan.mhp.activity.MainActivity
import com.linweiyuan.mhp.component.CatNameItemComponent
import com.linweiyuan.mhp.model.Cat
import com.linweiyuan.mhp.model.Player
import com.linweiyuan.mhp.service.Callback
import com.linweiyuan.mhp.service.Service.codeService
import com.linweiyuan.mhp.ui.misc.InfoFragmentUI
import com.linweiyuan.misc.model.Data
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast

class InfoFragment : Fragment() {
    internal lateinit var edtPlayerName: EditText
    internal lateinit var edtPlayerIntro: EditText
    internal lateinit var llName: LinearLayout
    internal lateinit var btnPlayer: Button
    internal lateinit var btnCat: Button

    private lateinit var ctx: Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        ctx = requireContext()
        val view = InfoFragmentUI().createView(AnkoContext.create(ctx, this))
        addCatView(null)
        return view
    }

    private fun addCatView(view: View?) {
        if (llName.childCount == 25) {
            requireActivity().toast(getString(R.string.cat_list_more_than_25_hint))
            return
        }
        val catView = CatNameItemComponent().createView(AnkoContext.create(ctx, ctx))
        if (view == null) {
            catView.find<View>(R.id.btnCatOperate).onClick {
                addCatView(view)
            }
        }
        catView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        llName.addView(catView)
        reconfigureView()
    }

    @SuppressLint("SetTextI18n")
    private fun reconfigureView() {
        val childCount = llName.childCount
        for (i in 0 until childCount) {
            val catView = llName.getChildAt(i)
            val txtCatIndex = catView.find<TextView>(R.id.txtCatIndex)
            txtCatIndex.text = "第" + (i + 1) + "只猫"
            val btnRemove = catView.find<Button>(R.id.btnCatOperate)
            btnRemove.text = "-删除"
            btnRemove.onClick {
                llName.removeView(catView)
                reconfigureView()
            }
            if (i == childCount - 1) {
                val btnAdd = catView.find<Button>(R.id.btnCatOperate)
                btnAdd.text = "+增加"
                btnAdd.onClick {
                    addCatView(view)
                }
            }
        }
    }

    private fun makeCatList(): MutableList<Cat> {
        val catList = mutableListOf<Cat>()
        for (i in 0 until llName.childCount) {
            val catView = llName.getChildAt(i)
            val edtCatName = catView.find<EditText>(R.id.edtCatName)
            val edtCatOwner = catView.find<EditText>(R.id.edtCatOwner)
            val edtCatIntro = catView.find<EditText>(R.id.edtCatIntro)
            catList.add(
                Cat(
                    name = edtCatName.text.toString().trim(),
                    owner = edtCatOwner.text.toString().trim(),
                    intro = edtCatIntro.text.toString().trim()
                )
            )
        }
        return catList
    }

    internal fun genPlayerCode() {
        if (edtPlayerName.text.toString().trim().isEmpty()) {
            requireActivity().toast(getString(R.string.player_name_empty_hint))
            return
        }
        val player = Player(
            name = edtPlayerName.text.toString().trim(),
            intro = edtPlayerIntro.text.toString().trim()
        )
        codeService.genPlayerCode(player, object : Callback {
            override fun onSuccess(data: Data) {
                (activity as MainActivity).onSuccess(data)
            }

            override fun onFailure(data: Data) {}
        }, ctx)
    }

    internal fun genCatCode() {
        val cats = makeCatList()
        cats.forEach {
            if (it.name.isEmpty()) {
                requireActivity().toast(getString(R.string.cat_name_empty_hint))
                return
            }
        }
        codeService.genCatCode(cats, object : Callback {
            override fun onSuccess(data: Data) {
                (activity as MainActivity).onSuccess(data)
            }

            override fun onFailure(data: Data) {}
        }, ctx)
    }
}
