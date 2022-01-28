package com.hivian.lumapps_test.business.remote

import com.hivian.lumapps_test.business.model.dto.RandomUserDTO


class HttpRetrofit(private val service: ApiService) {

    suspend fun fetchRandomUsers(page: Int, results: Int): List<RandomUserDTO> =
        service.fetchRandomUsers(page = page, results = results).results

}
