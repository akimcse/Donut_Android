package org.gdsc.donut.data.api

import org.gdsc.donut.data.remote.response.home.ResponseHistoryReceiver
import retrofit2.http.GET
import retrofit2.http.Header

interface HistoryService {
    @GET("history/receiver/info")
    suspend fun getReceiverHistoryInfo(
        @Header("Authorization") accessToken : String
    ): ResponseHistoryReceiver
}