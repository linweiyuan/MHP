package com.linweiyuan.mhp.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.linweiyuan.mhp.R
import com.linweiyuan.mhp.common.popup
import com.linweiyuan.mhp.model.User
import com.linweiyuan.mhp.service.Callback
import com.linweiyuan.mhp.service.Service.userService
import com.linweiyuan.misc.model.Data
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        setContentView(R.layout.activity_login)
        QMUIStatusBarHelper.translucent(this)
        topBarLogin.setTitle(getString(R.string.app_name))

        btnRegister.onClick {
            register(
                edtUsername.text.toString().trim(),
                edtPassword.text.toString().trim()
            )
        }
    }

    private fun register(username: String, password: String) {
        if (check(username, password)) {
            btnRegister.isEnabled = false

            userService.register(User(username, password), object : Callback {
                override fun onSuccess(data: Data) {
                    toast(data.msg)
                    edtUsername.visibility = View.GONE
                    edtPassword.visibility = View.GONE
                    btnRegister.visibility = View.GONE

                    edtRegCode.visibility = View.VISIBLE
                    toast(data.msg)
                }

                override fun onFailure(data: Data) {
                    toast(data.msg)
                    btnRegister.isEnabled = true
                }
            }, this)
        }
    }

    private fun check(username: String, password: String): Boolean {
        if (username.isEmpty()) {
            popup(getString(R.string.username_empty_hint)).show(edtUsername)
            return false
        }
        if (password.isEmpty()) {
            popup(getString(R.string.password_empty_hint)).show(edtPassword)
            return false
        }
        return true
    }
}
