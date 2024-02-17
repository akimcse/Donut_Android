package org.gdsc.donut.data.api

import org.gdsc.donut.data.remote.response.report.ResponseReportUsed
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ReportService {
    @POST("report/use/{giftId}")
    suspend fun reportUsed(
        @Header("Authorization") accessToken : String,
        @Path("giftId") giftId:Long,
    ): ResponseReportUsed
}