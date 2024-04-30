package org.gdsc.donut.data.api

import org.gdsc.donut.data.remote.response.history.ResponseHistoryGiver
import org.gdsc.donut.data.remote.response.history.ResponseHistoryGiverDetail
import org.gdsc.donut.data.remote.response.history.ResponseHistoryReceiver
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import java.time.LocalDateTime

interface MyPageService {
    @GET("mypage/giver")
    suspend fun getGiverMyPageInfo(
        @Header("Authorization") accessToken : String
    ): ResponseGiverMyPage

    @GET("mypage/receiver")
    suspend fun getReceiverMyPageInfo(
        @Header("Authorization") accessToken : String
    ): ResponseReceiverMyPage
}