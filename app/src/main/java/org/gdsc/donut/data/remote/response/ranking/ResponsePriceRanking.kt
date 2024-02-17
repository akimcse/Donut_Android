package org.gdsc.donut.data.remote.response.ranking

data class ResponsePriceRanking(
    val code: Int,
    val message: String,
    val `data`: ResponsePriceRankingData?
)

data class ResponsePriceRankingData(
    val rank: Int,
    val name: String,
    val price: Long
)