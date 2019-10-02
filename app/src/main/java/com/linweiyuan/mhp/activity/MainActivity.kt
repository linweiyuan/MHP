package com.linweiyuan.mhp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.linweiyuan.mhp.R
import com.linweiyuan.mhp.adapter.PagerAdapter
import com.linweiyuan.mhp.common.Constant
import com.linweiyuan.mhp.common.file
import com.linweiyuan.mhp.common.readCodeFile
import com.linweiyuan.mhp.common.toLogin
import com.linweiyuan.mhp.fragment.AboutFragment
import com.linweiyuan.mhp.fragment.CodeFragment
import com.linweiyuan.mhp.fragment.misc.MiscFragment
import com.linweiyuan.mhp.fragment.quest.QuestFragment
import com.linweiyuan.misc.model.Data
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.qmuiteam.qmui.widget.QMUITabSegment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity(), QMUITabSegment.OnTabSelectedListener,
    ViewPager.OnPageChangeListener {
    private val codeFragment: CodeFragment by lazy { CodeFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        // 判断是否登录过
        val token = getSharedPreferences(Constant.SP_FILE_NAME, MODE_PRIVATE).getString(
            Constant.SP_TOKEN,
            null
        )
        if (token == null) {
            toLogin()
        } else {
            showView()
        }
    }

    private fun showView() {
        setContentView(R.layout.activity_main)
        QMUIStatusBarHelper.translucent(this)
        topBarMain.setTitle(getString(R.string.title_misc))

        val fragmentList = mutableListOf<Fragment>()
        fragmentList.add(MiscFragment())
        fragmentList.add(QuestFragment())
        fragmentList.add(codeFragment)
        fragmentList.add(AboutFragment())

        val viewPager = find<ViewPager>(R.id.viewPagerMain)
        // 不设置这个切换会重新加载
        viewPager.offscreenPageLimit = fragmentList.size
        val pagerAdapter = PagerAdapter(fragmentList, supportFragmentManager)
        viewPager.adapter = pagerAdapter
        viewPager.addOnPageChangeListener(this)

        val tabSegment = find<QMUITabSegment>(R.id.tabSegmentMain)
            .addTab(
                QMUITabSegment.Tab(
                    ContextCompat.getDrawable(this, R.drawable.ic_misc_normal),
                    ContextCompat.getDrawable(this, R.drawable.ic_misc_selected),
                    getText(R.string.title_misc),
                    false
                )
            )
            .addTab(
                QMUITabSegment.Tab(
                    ContextCompat.getDrawable(this, R.drawable.ic_quest_normal),
                    ContextCompat.getDrawable(this, R.drawable.ic_quest_selected),
                    getText(R.string.title_quest),
                    false
                )
            )
            .addTab(
                QMUITabSegment.Tab(
                    ContextCompat.getDrawable(this, R.drawable.ic_code_normal),
                    ContextCompat.getDrawable(this, R.drawable.ic_code_selected),
                    getText(R.string.title_code),
                    false
                )
            )
            .addTab(
                QMUITabSegment.Tab(
                    ContextCompat.getDrawable(this, R.drawable.ic_about_normal),
                    ContextCompat.getDrawable(this, R.drawable.ic_about_selected),
                    getText(R.string.title_about),
                    false
                )
            )
        tabSegment.selectTab(0)
        // 显示indicator
        tabSegment.setHasIndicator(true)
        // 显示在上面
        tabSegment.setIndicatorPosition(true)
        tabSegment.setDefaultTabIconPosition(QMUITabSegment.ICON_POSITION_TOP)
        tabSegment.addOnTabSelectedListener(this)
        tabSegment.notifyDataChanged()

        pagerAdapter.notifyDataSetChanged()
    }

    override fun onDoubleTap(index: Int) {}

    override fun onTabReselected(index: Int) {}

    override fun onTabUnselected(index: Int) {}

    override fun onTabSelected(index: Int) {
        viewPagerMain.currentItem = index
    }

    override fun onPageScrollStateChanged(index: Int) {}

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}

    override fun onPageSelected(index: Int) {
        when (index) {
            0 -> topBarMain.setTitle(getString(R.string.title_misc))
            1 -> topBarMain.setTitle(getString(R.string.title_quest))
            2 -> topBarMain.setTitle(getString(R.string.title_code))
            3 -> topBarMain.setTitle(getString(R.string.title_about))
        }
        // noAnimation设为true会闪两次
        tabSegmentMain.selectTab(index, true, true)
    }

    internal fun onSuccess(data: Data) {
        doAsync {
            val code = data.data as String
            file(Constant.CODE_FILE_FOLDER, Constant.CODE_FILE_NAME).appendText(code)

            uiThread {
                codeFragment.codeList.clear()
                codeFragment.codeList.addAll(readCodeFile())
                codeFragment.codeAdapter.notifyDataSetChanged()

                toast(data.msg)
            }
        }
    }
}
