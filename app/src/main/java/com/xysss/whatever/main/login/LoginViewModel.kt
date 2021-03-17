package com.xysss.whatever.main.login

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xysss.core.Play
import com.xysss.core.util.LiveDataBus
import com.xysss.core.util.showToast
import com.xysss.model.model.BaseModel
import com.xysss.model.model.Login
import com.xysss.whatever.R
import com.xysss.whatever.home.LOGIN_REFRESH
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Author:bysd-2
 * Time:2021/3/1711:31
 */

@ActivityScoped
class LoginViewModel @ViewModelInject constructor(
    application: Application,
    private val accountRepository: AccountRepository
) : AndroidViewModel(application) {

    private val _state = MutableLiveData<LoginState>()
    val state: LiveData<LoginState>
        get() = _state

    fun toLoginOrRegister(account: Account) {
        _state.postValue(Logging)
        viewModelScope.launch(Dispatchers.IO) {
            val loginModel: BaseModel<Login> = if (account.isLogin) {
                accountRepository.getLogin(account.username, account.password)
            } else {
                accountRepository.getRegister(
                    account.username,
                    account.password,
                    account.password
                )
            }

            if (loginModel.errorCode == 0) {
                val login = loginModel.data
                _state.postValue(LoginSuccess(login))
                Play.isLogin = true
                Play.setUserInfo(login.nickname, login.username)
                showToast(
                    if (account.isLogin) getApplication<Application>().getString(R.string.login_success) else getApplication<Application>().getString(
                        R.string.register_success
                    )
                )
                withContext(Dispatchers.Main) {
                    LiveDataBus.get().getChannel(LOGIN_REFRESH).setValue(true)
                }
            } else {
                showToast(loginModel.errorMsg)
                _state.postValue(LoginError)
            }
        }
    }

}

data class Account(val username: String, val password: String, val isLogin: Boolean)
sealed class LoginState
object Logging : LoginState()
data class LoginSuccess(val login: Login) : LoginState()
object LoginError : LoginState()