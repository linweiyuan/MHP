package com.linweiyuan.misc.model

data class Data(
    var code: Code = Code.OK,
    var msg: String,
    var data: Any? = null,
    var count: Int? = null,
    var ext: Map<String, Any>? = null
)
