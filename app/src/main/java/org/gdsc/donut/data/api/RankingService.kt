package org.gdsc.donut.data.api

import org.gdsc.donut.data.remote.response.ranking.ResponsePriceRanking
import retrofit2.http.GET
import retrofit2.http.Header

interface RankingService {
    @GET("ranking/giver/info/price")
    suspend fun getPriceRankingInfo(
        @Header("Authorization") accessToken : String
    ): ResponsePriceRanking
}