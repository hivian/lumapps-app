package com.hivian.lumapps_test.business.remote

import com.hivian.lumapps_test.business.model.dto.Results
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api")
    suspend fun fetchRandomUsers(
        @Query("seed") seed: String = "lumapps",
        @Query("results") results: Int = 20,
        @Query("page") page: Int = 1): Results

}
