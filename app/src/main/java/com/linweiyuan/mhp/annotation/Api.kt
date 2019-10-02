package com.linweiyuan.mhp.annotation

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Api(
    val url: String
)
