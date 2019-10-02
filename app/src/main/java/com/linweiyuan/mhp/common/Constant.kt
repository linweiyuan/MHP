package com.linweiyuan.mhp.common

object Constant {
    const val URL_API_BASE = "https://api.linweiyuan.com/mhp"

    const val SP_FILE_NAME = "mhp"
    const val SP_TOKEN = "token"

    const val REG_CODE_LENGTH = 8

    const val SP_PERMISSION_GRANTED = "permission_granted"
    const val PERMISSION_WRITE_STORAGE = 0x00000001

    const val DB_NAME = "mhp.db"
    const val TABLE_STONE_SKILL = "stone_skill"
    const val TABLE_STONE_SKILL_POINT = "stone_skill_point"
    const val TABLE_STONE_RARITY = "stone_rarity"
    const val TABLE_DRINK_SKILL = "drink_skill"
    const val TABLE_WEAPON_TYPE = "weapon_type"
    const val TABLE_WEAPON_PLACE = "weapon_place"
    const val TABLE_CARD_QUEST_TYPE = "card_quest_type" // 工会卡片任务执行次数（与自制任务区分）
    const val TABLE_QUEST_BOSS = "quest_boss"
    const val TABLE_QUEST_START_AREA = "quest_start_area"
    const val TABLE_QUEST_BOSS_SKILL = "quest_boss_skill"
    const val TABLE_QUEST_PICK_RANK = "quest_pick_rank"
    const val TABLE_QUEST_BGM = "quest_bgm"
    const val TABLE_QUEST_RETURN_TIME = "quest_return_time"
    const val TABLE_QUEST_TYPE = "quest_type"
    const val TABLE_QUEST_RANK = "quest_rank"
    const val TABLE_QUEST_MAP = "quest_map"
    const val TABLE_QUEST_JOIN_CONDITION = "quest_join_condition"
    const val TABLE_QUEST_SUCCESS_CONDITION = "quest_success_condition"
    const val TABLE_QUEST_SUCCESS_CONDITION_TYPE = "quest_success_condition_type"
    const val TABLE_QUEST_AREA = "quest_area"
    const val TABLE_QUEST_ITEM = "quest_item"
    const val TABLE_QUEST_MONSTER = "quest_monster"
    const val TABLE_QUEST_BOSS_STATUS = "quest_boss_status"
    const val TABLE_QUEST_BOSS_ROUND = "quest_boss_round"
    const val TABLE_QUEST_BOSS_POINT = "quest_boss_point"

    const val CODE_FILE_NAME = "NPJB40001.ini"
    const val CODE_FILE_FOLDER = "PSP/Cheats"
    const val CODE_DISABLE = '0'
    const val CODE_ENABLE = '1'
    const val CODE_CODE_NAME_PREFIX = "_C"
    const val CODE_CODE_KEY_PREFIX = "_L"
    const val CODE_CODE_PREFIX = "0x"
}
