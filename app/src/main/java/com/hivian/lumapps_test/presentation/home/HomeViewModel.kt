package com.hivian.lumapps_test.presentation.home

import android.app.Application
import androidx.lifecycle.*
import com.hivian.lumapps_test.business.Mapper
import com.hivian.lumapps_test.ui.NetworkState
import com.hivian.lumapps_test.business.model.domain.RandomUserDomain
import com.hivian.lumapps_test.business.repository.RandomUsersRepository
import com.hivian.lumapps_test.common.IScrollMoreDelegate
import com.hivian.lumapps_test.common.Resource
import com.hivian.lumapps_test.common.SingleLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application): AndroidViewModel(application), IScrollMoreDelegate {

    companion object {
        const val RESULT_COUNT = 20
    }

    private var pageCount = 1

    private val randomUsersRepository = RandomUsersRepository(getApplication())

    private var isLoadingMore: Boolean = false

    var data : LiveData<List<RandomUserDomain>> =
        Transformations.map(randomUsersRepository.randomsUsersLocal) {
            if (it.count() > 0) {
                pageCount = it.count() / RESULT_COUNT
            }

            Mapper.mapDTOToDomain(it)
        }

    val networkState = MutableLiveData<NetworkState>()

    val clickEvent = SingleLiveData<HomeListViewEvent>()

    var displayErrorMessage: LiveData<Boolean> =
        Transformations.map(data) {
            it.isEmpty() && networkState.value is NetworkState.Error
        }

    init {
        fetchRandomUsers()
    }

    fun openRandomUserDetail(randomUser: RandomUserDomain) {
        clickEvent.value = HomeListViewEvent.OpenDetailView(randomUser)
    }

    private fun fetchRandomUsers() = viewModelScope.launch {
        networkState.value = NetworkState.Loading

        when (val resultList = randomUsersRepository.fetchRandomUsers(pageCount, RESULT_COUNT)) {
            is Resource.Success -> networkState.value = NetworkState.Success
            is Resource.Error -> networkState.value = NetworkState.Error(resultList.message)
        }
    }

    override fun loadMore() {
        if (isLoadingMore) return

        isLoadingMore = true
        viewModelScope.launch(Dispatchers.Main) {
            val resultList = randomUsersRepository.fetchRandomUsers(++pageCount, RESULT_COUNT)
            if (resultList is Resource.Error) {
                pageCount--
            }

            isLoadingMore = false
        }
    }
}
