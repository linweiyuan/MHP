package com.linweiyuan.mhp.service

import android.content.Context
import com.linweiyuan.mhp.annotation.Api
import com.linweiyuan.mhp.annotation.Param
import com.linweiyuan.mhp.model.*

interface CodeService {
    @Api("/code/stone")
    fun genStoneCode(@Param stone: Stone, callback: Callback, ctx: Context)

    @Api("/code/drink")
    fun genDrinkCode(@Param drink: Drink, callback: Callback, ctx: Context)

    @Api("/code/player")
    fun genPlayerCode(@Param player: Player, callback: Callback, ctx: Context)

    @Api("/code/cat")
    fun genCatCode(@Param cats: MutableList<Cat>, callback: Callback, ctx: Context)

    @Api("/code/time")
    fun genTimeCode(@Param time: Time, callback: Callback, ctx: Context)

    @Api("/code/weaponNum")
    fun genWeaponNumCode(@Param weaponNum: WeaponNum, callback: Callback, ctx: Context)

    @Api("/code/questNum")
    fun genQuestNumCode(@Param questNum: QuestNum, callback: Callback, ctx: Context)

    @Api("/code/bossNum")
    fun genBossNumCode(@Param bossNum: BossNum, callback: Callback, ctx: Context)

    @Api("/code/quest")
    fun genQuestCode(@Param quest: Quest, callback: Callback, ctx: Context)
}
