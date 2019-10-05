package com.linweiyuan.mhp.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.linweiyuan.mhp.R
import com.linweiyuan.mhp.common.Constant
import com.linweiyuan.mhp.common.file
import com.linweiyuan.mhp.common.popup
import com.linweiyuan.mhp.model.User
import com.linweiyuan.mhp.service.Callback
import com.linweiyuan.mhp.service.Service.userService
import com.linweiyuan.misc.model.Data
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity(), TextWatcher {
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
        edtRegCode.addTextChangedListener(this)
        btnLogin.onClick {
            login(
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

    override fun afterTextChanged(s: Editable?) {
        if (s.toString().length == Constant.REG_CODE_LENGTH) {
            validate(
                edtUsername.text.toString().trim(),
                edtPassword.text.toString().trim(),
                edtRegCode.text.toString().trim()
            )
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    private fun validate(username: String, password: String, regCode: String) {
        userService.validate(User(username, password, regCode), object : Callback {
            override fun onSuccess(data: Data) {
                login(username, password)
            }

            override fun onFailure(data: Data) {}
        }, this)
    }

    private fun login(username: String, password: String) {
        if (check(username, password)) {
            btnLogin.isEnabled = false

            userService.login(User(username = username, password = password), object : Callback {
                override fun onSuccess(data: Data) {
                    saveSP(Constant.SP_TOKEN, data.data as String)

                    ActivityCompat.requestPermissions(this@LoginActivity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), Constant.PERMISSION_WRITE_STORAGE)
                }

                override fun onFailure(data: Data) {
                    btnLogin.isEnabled = true
                }
            }, this)
        }
    }

    private fun saveSP(key: String, value: String) {
        getSharedPreferences(Constant.SP_FILE_NAME, MODE_PRIVATE)
            .edit()
            .putString(key, value)
            .apply()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            Constant.PERMISSION_WRITE_STORAGE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    longToast(getString(R.string.need_permission))
                    finish()
                } else {
                    saveSP(Constant.SP_PERMISSION_GRANTED, "") // 空标识
                    initDB()
                    toMain()
                }
            }
        }
    }

    private fun initDB() {
        val fullPath = getDatabasePath(Constant.DB_NAME).canonicalPath
        val dirName = fullPath.substring(0, fullPath.lastIndexOf('/'))
        val fileName = fullPath.substring(fullPath.lastIndexOf('/') + 1)

        val bytes = resources.openRawResource(R.raw.mhp).readBytes()
        file(dirName, fileName, false).writeBytes(bytes)
    }

    private fun toMain() {
        startActivity<MainActivity>()
        finish()
    }
}
