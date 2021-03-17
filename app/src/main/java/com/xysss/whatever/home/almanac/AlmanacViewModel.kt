package com.xysss.whatever.home.almanac

import android.net.Uri
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xysss.core.almanac.ScreenShotsUtils
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

/**
 * Author:bysd-2
 * Time:2021/3/1711:27
 */

@ActivityScoped
class AlmanacViewModel @ViewModelInject constructor(
    private val almanacRepository: AlmanacRepository
) : ViewModel() {

    private val _state = MutableLiveData<ShareState>()

    val state: LiveData<ShareState>
        get() = _state

    private suspend fun addAlmanac(instance: Calendar, toString: String) {
        almanacRepository.addAlmanac(instance, toString)
    }

    fun shareAlmanac(activity: AlmanacActivity, view: View, calendar: Calendar) {
        _state.postValue(Sharing)
        viewModelScope.launch(Dispatchers.IO) {
            val almanacUri = almanacRepository.getAlmanacUri(calendar)
            if (almanacUri != null) {
                _state.postValue(ShareSuccess(almanacUri))
            } else {
                val tempUri = ScreenShotsUtils.takeScreenShot(activity, view)
                if (tempUri != null) {
                    addAlmanac(calendar, tempUri.toString())
                    _state.postValue(ShareSuccess(tempUri))
                } else {
                    _state.postValue(ShareError)
                }
            }
        }
    }

}

sealed class ShareState
object Sharing : ShareState()
data class ShareSuccess(val uri: Uri) : ShareState()
object ShareError : ShareState()