package org.gdsc.donut.data.api

import org.gdsc.donut.data.remote.response.history.ResponseHistoryGiver
import org.gdsc.donut.data.remote.response.history.ResponseHistoryReceiver
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import java.time.LocalDateTime

interface HistoryService {
    @GET("history/receiver/info")
    suspend fun getReceiverHistoryInfo(
        @Header("Authorization") accessToken : String
    ): ResponseHistoryReceiver

    @GET("history/giver/info/{donateDate}")
    suspend fun getGiverHistoryInfo(
        @Header("Authorization") accessToken : String,
        @Path("donateDate") donateDate: LocalDateTime
    ): ResponseHistoryGiver
}