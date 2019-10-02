package com.linweiyuan.mhp.service

import android.content.Context
import com.linweiyuan.mhp.annotation.Api
import com.linweiyuan.mhp.annotation.Param
import com.linweiyuan.mhp.model.Drink
import com.linweiyuan.mhp.model.Stone

interface CodeService {
    @Api("/code/stone")
    fun genStoneCode(@Param stone: Stone, callback: Callback, ctx: Context)

    @Api("/code/drink")
    fun genDrinkCode(@Param drink: Drink, callback: Callback, ctx: Context)
}
