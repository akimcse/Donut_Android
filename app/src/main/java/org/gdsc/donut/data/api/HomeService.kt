package org.gdsc.donut.data.api

import org.gdsc.donut.data.remote.response.home.ResponseHomeGiver
import org.gdsc.donut.data.remote.response.home.ResponseHomeReceiver
import org.gdsc.donut.data.remote.response.home.ResponseHomeReceiverBoxItem
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface HomeService {
    @GET("home/giver")
    suspend fun getGiverHomeInfo(
        @Header("Authorization") accessToken : String
    ): ResponseHomeGiver

    @GET("home/receiver")
    suspend fun getReceiverHomeInfo(
        @Header("Authorization") accessToken : String
    ): ResponseHomeReceiver

    @GET("home/receiver/box/{boxId}")
    suspend fun getReceiverHomeBoxInfo(
        @Header("Authorization") accessToken : String,
        @Path("boxId") boxId: Long
    ): ResponseHomeReceiverBoxItem
}