package com.linweiyuan.mhp.fragment.misc

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.linweiyuan.mhp.R
import com.linweiyuan.mhp.adapter.PagerAdapter
import com.qmuiteam.qmui.widget.QMUITabSegment
import org.jetbrains.anko.find

class MiscFragment : Fragment(), ViewPager.OnPageChangeListener,
    QMUITabSegment.OnTabSelectedListener {
    private val stoneFragment = StoneFragment()

    private lateinit var viewPagerMisc: ViewPager
    private lateinit var tabSegmentMisc: QMUITabSegment

    private lateinit var ctx: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ctx = requireContext()
        val view = View.inflate(ctx, R.layout.fragment_misc, null)
        init(view)
        return view
    }

    private fun init(view: View) {
        val fragmentList = ArrayList<Fragment>()
        fragmentList.add(stoneFragment)

        viewPagerMisc = view.find(R.id.viewPagerMisc)
        viewPagerMisc.offscreenPageLimit = fragmentList.size
        val pagerAdapter = PagerAdapter(fragmentList, activity!!.supportFragmentManager)
        viewPagerMisc.adapter = pagerAdapter
        viewPagerMisc.addOnPageChangeListener(this)

        tabSegmentMisc = view.find<QMUITabSegment>(R.id.tabSegmentMisc)
            .addTab(QMUITabSegment.Tab(getString(R.string.title_stone)))

        tabSegmentMisc.selectTab(0)
        tabSegmentMisc.setHasIndicator(true)
        tabSegmentMisc.setDefaultTabIconPosition(QMUITabSegment.ICON_POSITION_LEFT)
        tabSegmentMisc.addOnTabSelectedListener(this)
        tabSegmentMisc.notifyDataChanged()

        pagerAdapter.notifyDataSetChanged()
    }

    override fun onPageScrollStateChanged(p0: Int) {}

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}

    override fun onPageSelected(index: Int) {
        tabSegmentMisc.selectTab(index, true, true)
    }

    override fun onDoubleTap(index: Int) {}

    override fun onTabReselected(index: Int) {}

    override fun onTabUnselected(index: Int) {}

    override fun onTabSelected(index: Int) {
        viewPagerMisc.currentItem = index
    }
}
