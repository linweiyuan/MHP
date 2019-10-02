package com.linweiyuan.mhp.model

data class Quest(
    val basic: Basic,
    val shikyus: List<Shikyu>,
    val hosyus: List<Hosyu>,
    val monsters: List<Monster>,
    val bosses: List<Boss>
)

data class Basic(
    val name: String,
    val success: String,
    val failure: String,
    val content: String,
    val monster: String,
    val client: String,
    val successPts: Int,
    val failPts: Int,
    val startArea: Int,
    val bossSkill: Int,
    val pickRank: Int,
    val bgm: Int,
    val returnTime: Int,
    val questType1: Int,
    val contractZ: Int,
    val rewardZ: Int,
    val minute: Int,
    val second: Int,
    val rank: Int,
    val map: Int,
    val joinCondition1: Int,
    val joinCondition2: Int,
    val successCondition: Int,
    val successConditionType1: Int,
    val successConditionTypeItem1: String,
    val successConditionTypeNum1: Int,
    val successConditionType2: Int,
    val successConditionTypeItem2: String,
    val successConditionTypeNum2: Int,
    val bossIcon1: String,
    val bossIcon2: String,
    val bossIcon3: String,
    val bossIcon4: String,
    val bossIcon5: String
)

data class Shikyu(
    val gameId: String,
    val num: Int
)

data class Hosyu(
    val gameId: String,
    val num: Int
)

data class Monster(
    val gameId: String,
    val area: Int,
    val num: Int
)

data class Boss(
    val gameId: String,
    val area: Int,
    val num: Int,
    val size: Int,
    val hp: Int,
    val strength: Int,
    val endurance: Int,
    val fatigue: Int,
    val status: Int,
    val round: Int
)
