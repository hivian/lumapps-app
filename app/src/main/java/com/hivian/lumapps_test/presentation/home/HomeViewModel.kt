package com.hivian.lumapps_test.presentation.home

import android.app.Application
import androidx.lifecycle.*
import com.hivian.lumapps_test.business.Mapper
import com.hivian.lumapps_test.ui.NetworkState
import com.hivian.lumapps_test.business.model.domain.RandomUserDomain
import com.hivian.lumapps_test.business.repository.RandomUsersRepository
import com.hivian.lumapps_test.common.Resource
import com.hivian.lumapps_test.common.SingleLiveData
import kotlinx.coroutines.launch

class HomeViewModel(application: Application): AndroidViewModel(application) {

    private val randomUsersRepository = RandomUsersRepository(getApplication())

    var data : LiveData<List<RandomUserDomain>> =
        Transformations.map(randomUsersRepository.randomsUsersLocal) {
            Mapper.mapDTOToDomain(it)
        }

    val networkState = MutableLiveData<NetworkState>()

    val clickEvent = SingleLiveData<HomeListViewEvent>()

    init {
        fetchRandomUsers()
    }

    fun openRandomUserDetail(randomUser: RandomUserDomain) {
        clickEvent.value = HomeListViewEvent.OpenDetailView(randomUser)
    }

    private fun fetchRandomUsers() = viewModelScope.launch {
        networkState.value = NetworkState.Loading
        when (val resultList = randomUsersRepository.fetchRandomUsers()) {
            is Resource.Success -> networkState.value = NetworkState.Success
            is Resource.Error -> networkState.value = NetworkState.Error(resultList.message)
        }
    }

}
