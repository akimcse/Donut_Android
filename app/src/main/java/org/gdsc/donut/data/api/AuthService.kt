package org.gdsc.donut.data.api

import org.gdsc.donut.data.remote.request.auth.RequestSignInReceiver
import org.gdsc.donut.data.remote.request.auth.RequestSignUpReceiver
import org.gdsc.donut.data.remote.response.auth.ResponseSignInGiver
import org.gdsc.donut.data.remote.response.auth.ResponseSignInReceiver
import org.gdsc.donut.data.remote.response.auth.ResponseSignUpReceiver
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @POST("auth/receiver/signup")
    suspend fun signUpReceiver(
        @Body receiverSignUpInfo: RequestSignUpReceiver
    ): ResponseSignUpReceiver

    @POST("auth/receiver/signin")
    suspend fun signInReceiver(
        @Body receiverSignInInfo: RequestSignInReceiver
    ): ResponseSignInReceiver

    @POST("auth/giver/signin")
    suspend fun signInGiver(
        @Header("access_token") accessToken : String
    ): ResponseSignInGiver
}