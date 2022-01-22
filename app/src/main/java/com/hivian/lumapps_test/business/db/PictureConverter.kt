package com.hivian.lumapps_test.business.db

import androidx.room.TypeConverter
import com.hivian.lumapps_test.business.model.dto.Picture
import com.hivian.lumapps_test.common.fromJson
import com.hivian.lumapps_test.common.toJson

class PictureConverter {

    @TypeConverter
    fun pictureToJson(value: Picture): String {
        return value.toJson()
    }

    @TypeConverter
    fun jsonToPicture(value: String): Picture {
        return value.fromJson()
    }
}
