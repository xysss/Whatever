package com.xysss.whatever.main.login

import android.util.Log
import androidx.lifecycle.liveData
import com.xysss.core.util.showToast
import com.xysss.model.model.BaseModel
import com.xysss.network.base.PlayAndroidNetwork
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

/**
 * Author:bysd-2
 * Time:2021/3/1711:31
 */

@ActivityRetainedScoped
class AccountRepository @Inject constructor() {

    suspend fun getLogin(username: String, password: String) =
        PlayAndroidNetwork.getLogin(username, password)

    suspend fun getRegister(username: String, password: String, repassword: String) =
        PlayAndroidNetwork.getRegister(username, password, repassword)

    fun getLogout() = fires { PlayAndroidNetwork.getLogout() }

}

private const val TAG = "AccountRepository"

fun <T> fires(block: suspend () -> BaseModel<T>) =
    liveData {
        val result = try {
            val baseModel = block()
            if (baseModel.errorCode == 0) {
                val model = baseModel.data
                Result.success(model)
            } else {
                Log.e(
                    TAG,
                    "fires: response status is ${baseModel.errorCode}  msg is ${baseModel.errorMsg}"
                )
                showToast(baseModel.errorMsg)
                Result.failure(RuntimeException(baseModel.errorMsg))
            }
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
            Result.failure(e)
        }
        emit(result)
    }

fun <T> fire(block: suspend () -> Result<T>) =
    liveData {
        val result = try {
            block()
        } catch (e: Exception) {
            Log.e(TAG, "fire $e")
            Result.failure(e)
        }
        emit(result)
    }