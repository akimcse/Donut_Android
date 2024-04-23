package org.gdsc.donut.data.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.gdsc.donut.data.remote.request.donation.RequestAssignReceiver
import org.gdsc.donut.data.remote.response.donation.ResponseAssignReceiver
import org.gdsc.donut.data.remote.response.donation.ResponseDonateGiver
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface DonationService {
    @POST("donation/receiver/assign")
    suspend fun assignReceiver(
        @Header("Authorization") accessToken : String,
        @Body receiverAssignInfo: RequestAssignReceiver
    ): ResponseAssignReceiver

    @Multipart
    @POST("donation/giver/donate")
    suspend fun donateGiver(
        @Header("Authorization") accessToken : String,
        @Part giftImage: MultipartBody.Part?,
        @Part("product") product: RequestBody,
        @Part("price") price: Int,
        @Part("dueDate") dueDate: RequestBody,
        @Part("store") store: RequestBody,
        @Part("isRestored") isRestored: RequestBody
    ): ResponseDonateGiver
}