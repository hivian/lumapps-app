package com.hivian.lumapps_test.business.db

import androidx.room.TypeConverter
import com.hivian.lumapps_test.common.fromJson
import com.hivian.lumapps_test.common.toJson

class StringListConverter {

    @TypeConverter
    fun listToJson(value: List<String>): String {
        return value.toJson()
    }

    @TypeConverter
    fun jsonToList(value: String): List<String> {
        return value.fromJson()
    }
}
