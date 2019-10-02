package com.linweiyuan.mhp.adapter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter(
    private val fragmentList: List<androidx.fragment.app.Fragment>,
    fm: FragmentManager
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): androidx.fragment.app.Fragment = fragmentList[position]

    override fun getCount(): Int = fragmentList.size
}
