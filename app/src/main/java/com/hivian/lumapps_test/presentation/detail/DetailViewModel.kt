package com.hivian.lumapps_test.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hivian.lumapps_test.business.model.domain.RandomUserDomain

class DetailViewModel(randomUser: RandomUserDomain): ViewModel() {

    val data = MutableLiveData(randomUser)

}
