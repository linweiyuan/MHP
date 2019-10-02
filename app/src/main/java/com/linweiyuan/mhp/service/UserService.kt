package com.linweiyuan.mhp.service

import android.content.Context
import com.linweiyuan.mhp.annotation.Api
import com.linweiyuan.mhp.annotation.Param
import com.linweiyuan.mhp.model.User

interface UserService {
    @Api("/user/register")
    fun register(@Param user: User, callback: Callback, ctx: Context)

    @Api("/user/validate")
    fun validate(@Param user: User, callback: Callback, ctx: Context)
}
