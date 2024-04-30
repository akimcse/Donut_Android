package org.gdsc.donut.data.remote.response.mypage

data class ResponseReceiverMyPage(
    val code: Int,
    val message: String,
    val `data`: ResponseReceiverMyPageData?
)

data class ResponseReceiverMyPageData(
    val total: Double
)