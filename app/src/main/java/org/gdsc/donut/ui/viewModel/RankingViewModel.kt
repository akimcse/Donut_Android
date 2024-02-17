package org.gdsc.donut.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.gdsc.donut.data.api.RetrofitBuilder
import org.gdsc.donut.data.remote.response.history.ResponseHistoryReceiver
import org.gdsc.donut.data.remote.response.ranking.ResponsePriceRanking

class RankingViewModel(application: Application) : AndroidViewModel(application) {
    private val _priceRankingInfo = MutableLiveData<ResponsePriceRanking>()
    val priceRankingInfo: LiveData<ResponsePriceRanking>
        get() = _priceRankingInfo

    fun requestPriceRankingInfo(accessToken: String) = viewModelScope.launch(Dispatchers.IO) {
        _priceRankingInfo.postValue(
            RetrofitBuilder.rankingService.getPriceRankingInfo("Bearer $accessToken")
        )
    }
}