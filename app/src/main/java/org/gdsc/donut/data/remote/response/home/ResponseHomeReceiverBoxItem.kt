package org.gdsc.donut.data.remote.response.home

data class ResponseHomeReceiverBoxItem(
    val code: Int,
    val message: String,
    val `data`: ResponseHomeReceiverData?
)

data class ResponseHomeReceiverBoxItemData(
    val store: String,
    val amount: Int,
    val dueDate: String,
    val giftList: List<ResponseHomeReceiverGift>?
)

data class ResponseHomeReceiverGift(
    val giftId: Int,
    val product: String,
    val price: Int,
    val dueDate: String,
    val isUsed: String
)