package com.linweiyuan.mhp.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.linweiyuan.mhp.R
import com.linweiyuan.mhp.adapter.CodeAdapter
import com.linweiyuan.mhp.common.Constant
import com.linweiyuan.mhp.common.file
import com.linweiyuan.mhp.common.readCodeFile
import com.linweiyuan.mhp.model.Code
import com.linweiyuan.mhp.ui.CodeFragmentUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread

class CodeFragment : Fragment() {
    internal lateinit var codeAdapter: CodeAdapter
    internal lateinit var codeList: MutableList<Code>

    private lateinit var ctx: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ctx = requireContext()
        val view = CodeFragmentUI().createView(AnkoContext.create(ctx, this))
        init()
        return view
    }

    private fun init() {
        doAsync {
            file(Constant.CODE_FILE_FOLDER, Constant.CODE_FILE_NAME)
            codeList = readCodeFile()
            codeAdapter = CodeAdapter(ctx, codeList)
            uiThread {
                requireActivity().find<RecyclerView>(R.id.recyclerCode).adapter = codeAdapter
            }
        }
    }
}
