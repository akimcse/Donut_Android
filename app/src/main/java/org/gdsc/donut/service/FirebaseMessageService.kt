package org.gdsc.donut.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.activity.viewModels
import androidx.core.app.NotificationCompat
import androidx.lifecycle.viewModelScope
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.gdsc.donut.data.DonutSharedPreferences
import org.gdsc.donut.data.api.RetrofitBuilder
import org.gdsc.donut.data.remote.request.auth.RequestSendFCMToken
import org.gdsc.donut.ui.GiverMainActivity
import org.gdsc.donut.ui.viewModel.HomeViewModel
import org.gdsc.donut.ui.viewModel.SignViewModel

class FirebaseMessageService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        DonutSharedPreferences.setFCMToken(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

    }
}
