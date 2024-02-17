package org.gdsc.donut.data.api

import org.gdsc.donut.data.remote.request.donation.RequestAssignReceiver
import org.gdsc.donut.data.remote.response.donation.ResponseAssignReceiver
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface DonationService {
    @POST("donation/receiver/assign")
    suspend fun assignReceiver(
        @Header("Authorization") accessToken : String,
        @Body receiverAssignInfo: RequestAssignReceiver
    ): ResponseAssignReceiver
}