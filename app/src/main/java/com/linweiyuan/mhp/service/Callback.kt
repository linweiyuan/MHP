package com.linweiyuan.mhp.service

import com.linweiyuan.misc.model.Data

interface Callback {
    fun onSuccess(data: Data)

    fun onFailure(data: Data)
}
