package com.linweiyuan.mhp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.linweiyuan.mhp.R
import com.linweiyuan.mhp.common.Constant
import com.linweiyuan.mhp.common.toLogin
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        // 判断是否登录过
        if (getSharedPreferences(Constant.SP_FILE_NAME, MODE_PRIVATE).getString(Constant.SP_TOKEN, null) == null) {
            toLogin()
            return
        }

        setContentView(R.layout.activity_main)
        QMUIStatusBarHelper.translucent(this)
        topBarMain.setTitle(getString(R.string.app_name))
    }
}
