package org.gdsc.donut.data.api

import org.gdsc.donut.data.remote.request.auth.RequestSignInReceiver
import org.gdsc.donut.data.remote.request.auth.RequestSignUpReceiver
import org.gdsc.donut.data.remote.response.auth.ResponseSignInReceiver
import org.gdsc.donut.data.remote.response.auth.ResponseSignUpReceiver
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/receiver/signup")
    suspend fun signUpReceiver(
        @Body receiverInfo: RequestSignUpReceiver
    ): ResponseSignUpReceiver

    @POST("auth/receiver/signin")
    suspend fun signInReceiver(
        @Body receiverInfo: RequestSignInReceiver
    ): ResponseSignInReceiver
}