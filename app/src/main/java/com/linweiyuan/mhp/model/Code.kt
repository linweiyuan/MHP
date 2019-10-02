package com.linweiyuan.mhp.model

import com.linweiyuan.mhp.common.Constant

data class Code(
    var enable: Char = Constant.CODE_DISABLE,
    var name: String,
    var codeMap: Map<String, String> = mutableMapOf()
) {
    override fun toString(): String {
        return StringBuilder().apply {
            append("${Constant.CODE_CODE_NAME_PREFIX}$enable $name\n")
            for ((key, value) in codeMap) {
                append("${Constant.CODE_CODE_KEY_PREFIX} ${Constant.CODE_CODE_PREFIX}$key ${Constant.CODE_CODE_PREFIX}$value\n")
            }
        }.toString()
    }
}
