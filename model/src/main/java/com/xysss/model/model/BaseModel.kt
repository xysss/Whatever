package com.xysss.model.model

/**
 * Author:bysd-2
 * Time:2021/3/1710:50
 */
data class BaseModel<T>(
    val `data`: T,
    val errorCode: Int,
    val errorMsg: String
)