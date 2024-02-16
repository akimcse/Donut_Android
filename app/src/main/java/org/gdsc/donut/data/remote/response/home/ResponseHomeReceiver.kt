package org.gdsc.donut.data.remote.response.home

data class ResponseHomeReceiver(
    val code: Int,
    val message: String,
    val `data`: ResponseHomeReceiverData?
)

data class ResponseHomeReceiverData(
    val availability: Boolean,
    val amount: Int,
    val cu: Int,
    val gs25: Int,
    val sevenEleven: Int,
    val boxList: ResponseHomeReceiverBox?
)

data class ResponseHomeReceiverBox(
    val boxId: Int,
    val store: String,
    val dueDate: String,
    val amount: Int
)