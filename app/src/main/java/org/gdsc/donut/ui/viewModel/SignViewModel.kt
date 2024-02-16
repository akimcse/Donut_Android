package org.gdsc.donut.ui.viewModel

import android.app.Application
import android.view.PixelCopy.Request
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.gdsc.donut.data.DonutSharedPreferences
import org.gdsc.donut.data.api.RetrofitBuilder
import org.gdsc.donut.data.remote.request.auth.RequestGoogleLogin
import org.gdsc.donut.data.remote.request.auth.RequestSignInReceiver
import org.gdsc.donut.data.remote.request.auth.RequestSignUpReceiver
import org.gdsc.donut.data.remote.response.auth.ResponseGoogleLogin
import org.gdsc.donut.data.remote.response.auth.ResponseSignInGiver
import org.gdsc.donut.data.remote.response.auth.ResponseSignInReceiver
import org.gdsc.donut.data.remote.response.auth.ResponseSignUpReceiver

class SignViewModel(application: Application) : AndroidViewModel(application) {
    private val _receiverSignUpInfo = MutableLiveData<ResponseSignUpReceiver>()
    val receiverSignUpInfo: LiveData<ResponseSignUpReceiver>
        get() = _receiverSignUpInfo

    private val _receiverSignInInfo = MutableLiveData<ResponseSignInReceiver>()
    val receiverSignInInfo: LiveData<ResponseSignInReceiver>
        get() = _receiverSignInInfo

    private val _giverSignInInfo = MutableLiveData<ResponseSignInGiver>()
    val giverSignInInfo: LiveData<ResponseSignInGiver>
        get() = _giverSignInInfo

    private val _googleLoginInfo = MutableLiveData<ResponseGoogleLogin>()
    val googleLoginInfo: LiveData<ResponseGoogleLogin>
        get() = _googleLoginInfo

    fun saveUserId(id: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            DonutSharedPreferences.setUserId(id)
        }
    }

    fun saveAccessToken(token: String?){
        viewModelScope.launch(Dispatchers.IO) {
            DonutSharedPreferences.setAccessToken(token)
        }
    }

    fun requestReceiverSignUp(id: String, password: String) =
        viewModelScope.launch(Dispatchers.IO) {
            _receiverSignUpInfo.postValue(
                RetrofitBuilder.authService.signUpReceiver(
                    RequestSignUpReceiver(id, password)
                )
            )
        }

    fun requestReceiverSignIn(id: String, password: String) =
        viewModelScope.launch(Dispatchers.IO) {
            _receiverSignInInfo.postValue(
                RetrofitBuilder.authService.signInReceiver(
                    RequestSignInReceiver(id, password)
                )
            )
        }

    fun requestGiverSignIn(accessToken: String) = viewModelScope.launch(Dispatchers.IO) {
        _giverSignInInfo.postValue(
            RetrofitBuilder.authService.signInGiver(accessToken)
        )
    }

    fun requestGoogleLogin(clientId: String, clientSecret: String, code: String, grantType: String, redirectUri: String)= viewModelScope.launch(Dispatchers.IO) {
        _googleLoginInfo.postValue(
            RetrofitBuilder.googleService.signInWithGoogle(RequestGoogleLogin(clientId, clientSecret, code, grantType, redirectUri))
        )
    }
}