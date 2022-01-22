package com.hivian.lumapps_test.common

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> String.fromJson(): T = Gson().fromJson(this, object: TypeToken<T>() {}.type)

inline fun <reified T> T.toJson(): String = Gson().toJson(this)
