package org.gdsc.donut.data.api

import org.gdsc.donut.data.remote.request.auth.RequestSignInGiver
import org.gdsc.donut.data.remote.request.message.RequestSendMsg
import org.gdsc.donut.data.remote.response.message.ResponseSendMsg
import org.gdsc.donut.data.remote.response.report.ResponseReportUsed
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface MessageService {
    @POST("message/receiver")
    suspend fun sendMessage(
        @Header("Authorization") accessToken: String,
        @Body receiverMsgInfo: RequestSendMsg
    ): ResponseSendMsg
}