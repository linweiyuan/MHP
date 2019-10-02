package com.linweiyuan.mhp.model

data class User(
    val username: String,
    val password: String? = null,
    val regCode: String? = null
)
