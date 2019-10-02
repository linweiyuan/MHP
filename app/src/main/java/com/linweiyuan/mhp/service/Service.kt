package com.linweiyuan.mhp.service

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.fastjson.JSON
import com.linweiyuan.mhp.annotation.Api
import com.linweiyuan.mhp.common.Constant
import com.linweiyuan.mhp.common.loadingDialog
import com.linweiyuan.mhp.common.toLogin
import com.linweiyuan.misc.model.Code
import com.linweiyuan.misc.model.Data
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import org.jsoup.HttpStatusException
import org.jsoup.Jsoup
import java.io.File
import java.lang.reflect.Proxy
import java.net.ConnectException

object Service {
    val userService by lazy { create(UserService::class.java) }
    val codeService by lazy { create(CodeService::class.java) }

    @Suppress("UNCHECKED_CAST")
    private fun <T> create(clazz: Class<T>) =
        Proxy.newProxyInstance(clazz.classLoader, arrayOf<Class<*>>(clazz)) { _, method, args ->
            // @Api注解
            val api = method.annotations[0] as Api
            // 入参
            val param = JSON.toJSONString(args[0])
            val callback = args[1] as Callback
            val ctx = args[2] as Context

            val dialog = ctx.loadingDialog()
            dialog.show()

            ctx.doAsync {
                try {
                    val token = ctx.getSharedPreferences(
                        Constant.SP_FILE_NAME,
                        AppCompatActivity.MODE_PRIVATE
                    ).getString(Constant.SP_TOKEN, null)

                    val json = Jsoup.connect("${Constant.URL_API_BASE}${api.url}")
                        .header("Authorization", token) // JWT token
                        .header("Content-Type", "application/json")
                        .requestBody(param)
                        .ignoreContentType(true)
                        .timeout(5000)
                        .post()
                        .text()
                    val data = JSON.parseObject(json, Data::class.java)

                    uiThread {
                        dialog.dismiss()
                        ctx.toast(data.msg)

                        when (data.code) {
                            Code.OK -> callback.onSuccess(data)
                            Code.ERR -> callback.onFailure(data)
                        }
                    }
                } catch (e: Exception) {
                    val data = Data(msg = "系统异常，请联系管理员")
                    data.code = Code.ERR

                    uiThread {
                        dialog.dismiss()
                        when (e) {
                            is HttpStatusException -> {
                                if (e.statusCode == 401) {
                                    ctx.toast("token过期，请重新登录")

                                    // 清除缓存数据
                                    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
                                    File(ctx.filesDir.parent).deleteRecursively()
                                    ctx.getSharedPreferences(
                                        Constant.SP_FILE_NAME,
                                        AppCompatActivity.MODE_PRIVATE
                                    ).edit().clear().apply()

                                    ctx.toLogin()
                                }
                            }
                            is ConnectException -> {
                                data.msg = "系统维护，请稍后重试"
                                callback.onFailure(data)
                            }
                            else -> callback.onFailure(data)
                        }
                    }
                }
            }
            null // 方法无返回值
        } as T
}
