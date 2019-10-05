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
            codeMap.mapValues { (k, v) -> append("${Constant.CODE_CODE_KEY_PREFIX} ${Constant.CODE_CODE_PREFIX}$k ${Constant.CODE_CODE_PREFIX}$v\n") }
        }.toString()
    }
}
