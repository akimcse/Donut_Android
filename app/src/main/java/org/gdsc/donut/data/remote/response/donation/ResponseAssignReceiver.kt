package org.gdsc.donut.data.remote.response.donation

data class ResponseAssignReceiver(
    val code: Int,
    val message: String,
    val data: ResponseAssignReceiverData?
)

data class ResponseAssignReceiverData(
    val giftboxId: Int
)
