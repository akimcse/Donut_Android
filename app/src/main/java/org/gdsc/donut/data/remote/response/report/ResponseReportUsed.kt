package org.gdsc.donut.data.remote.response.report

data class ResponseReportUsed(
    val code: Int,
    val message: String,
    val `data`: ResponseReportUsedData?
)

data class ResponseReportUsedData(
    val isLast: Boolean
)