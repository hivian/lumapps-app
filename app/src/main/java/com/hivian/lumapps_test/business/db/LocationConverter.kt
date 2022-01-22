package com.hivian.lumapps_test.business.db

import androidx.room.TypeConverter
import com.hivian.lumapps_test.business.model.dto.Location
import com.hivian.lumapps_test.common.fromJson
import com.hivian.lumapps_test.common.toJson

class LocationConverter {

    @TypeConverter
    fun locationToJson(value: Location): String {
        return value.toJson()
    }

    @TypeConverter
    fun jsonToLocation(value: String): Location {
        return value.fromJson()
    }
}
