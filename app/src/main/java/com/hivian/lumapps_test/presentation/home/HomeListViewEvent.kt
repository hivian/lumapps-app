package com.hivian.lumapps_test.presentation.home

import com.hivian.lumapps_test.business.model.domain.RandomUserDomain

sealed class HomeListViewEvent {

    data class OpenDetailView(val randomUser: RandomUserDomain) : HomeListViewEvent()

}
