package org.gdsc.donut.data.api

import org.gdsc.donut.data.remote.request.auth.RequestGoogleLogin
import org.gdsc.donut.data.remote.request.auth.RequestSignInReceiver
import org.gdsc.donut.data.remote.request.auth.RequestSignUpReceiver
import org.gdsc.donut.data.remote.response.auth.ResponseGoogleLogin
import org.gdsc.donut.data.remote.response.auth.ResponseSignInGiver
import org.gdsc.donut.data.remote.response.auth.ResponseSignInReceiver
import org.gdsc.donut.data.remote.response.auth.ResponseSignUpReceiver
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface GoogleService {
    @POST("oauth2/v4/token")
    suspend fun signInWithGoogle(
        @Body googleLoginInfo: RequestGoogleLogin
    ): ResponseGoogleLogin
}