package org.gdsc.donut.data.api

import org.gdsc.donut.data.remote.request.report.RequestReportUsed
import org.gdsc.donut.data.remote.response.report.ResponseReportUsed
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ReportService {
    @POST("report/use")
    suspend fun reportUsed(
        @Header("Authorization") accessToken : String,
        @Body reportUsedInfo: RequestReportUsed
    ): ResponseReportUsed
}