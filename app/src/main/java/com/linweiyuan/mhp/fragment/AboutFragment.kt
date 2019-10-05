package com.linweiyuan.mhp.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.linweiyuan.mhp.ui.AboutFragmentUI
import org.jetbrains.anko.AnkoContext

class AboutFragment : Fragment() {
    internal lateinit var txtAbout: TextView

    private lateinit var ctx: Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        ctx = requireContext()
        return AboutFragmentUI().createView(AnkoContext.create(ctx, this))
    }
}
