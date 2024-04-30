package org.gdsc.donut.data.api

import org.gdsc.donut.data.remote.response.report.ResponseReportUnused
import org.gdsc.donut.data.remote.response.report.ResponseReportUsed
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ReportService {
    @POST("report/use")
    suspend fun reportUsed(
        @Header("Authorization") accessToken: String,
        @Query("giftId") giftId: Long,
    ): ResponseReportUsed

    @POST("report/{giftId}")
    suspend fun reportUnused(
        @Header("Authorization") accessToken: String,
        @Path("giftId") giftId: Long,
    ): ResponseReportUnused
}