package com.hivian.lumapps_test.business.db

import androidx.room.TypeConverter
import com.hivian.lumapps_test.business.model.dto.Name
import com.hivian.lumapps_test.common.fromJson
import com.hivian.lumapps_test.common.toJson

class NameConverter {

    @TypeConverter
    fun nameToJson(value: Name): String {
        return value.toJson()
    }

    @TypeConverter
    fun jsonToName(value: String): Name {
        return value.fromJson()
    }
}
