package org.gdsc.donut.data.api

import org.gdsc.donut.data.remote.response.home.ResponseHomeGiver
import retrofit2.http.GET
import retrofit2.http.Header

interface HomeService {
    @GET("home/giver")
    suspend fun getGiverHomeInfo(
        @Header("Authorization") accessToken : String
    ): ResponseHomeGiver
}