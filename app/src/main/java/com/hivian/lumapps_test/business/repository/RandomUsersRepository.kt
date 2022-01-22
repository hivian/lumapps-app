package com.hivian.lumapps_test.business.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.hivian.lumapps_test.business.db.RandomUsersDatabase
import com.hivian.lumapps_test.business.model.dto.RandomUserDTO
import com.hivian.lumapps_test.business.remote.getRetrofitApiLayer
import com.hivian.lumapps_test.common.BaseRepository
import com.hivian.lumapps_test.common.Resource

class RandomUsersRepository(application: Application): BaseRepository() {

    private val httpRetrofit = getRetrofitApiLayer()

    private val randomsUsersDao = RandomUsersDatabase.getInstance(application).randomUsersDao()

    val randomsUsersLocal: LiveData<List<RandomUserDTO>> = randomsUsersDao.getAllRandomUsers()

    suspend fun fetchRandomUsers(): Resource<List<RandomUserDTO>> {
        val safeCall = safeApiCall {
            httpRetrofit.fetchRandomUsers()
        }
        if (safeCall is Resource.Success)
            saveRandomUsers(safeCall.data)
        return safeCall
    }

    private suspend fun saveRandomUsers(randomUserList: List<RandomUserDTO>) {
        randomsUsersDao.upsert(randomUserList)
    }

}
