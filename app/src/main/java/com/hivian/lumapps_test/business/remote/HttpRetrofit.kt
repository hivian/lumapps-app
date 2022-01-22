package com.hivian.lumapps_test.business.remote

import com.hivian.lumapps_test.business.model.dto.RandomUserDTO


class HttpRetrofit(private val service: ApiService) {

    suspend fun fetchRandomUsers(results: Int = 20): List<RandomUserDTO> =
        service.fetchRandomUsers(results = results).results

}
