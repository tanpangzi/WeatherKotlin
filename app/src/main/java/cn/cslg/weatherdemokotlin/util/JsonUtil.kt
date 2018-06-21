package com.coodays.repairrent.utli

import android.util.ArrayMap
import com.google.gson.Gson

/**
 * 作者：payne on 2017/7/3 19:27
 */
class JsonUtil {
    fun toJson(map: ArrayMap<String, String>?): String {
        return if (map == null) "{}" else Gson().toJson(map)
    }

    fun toAnyJson(map: ArrayMap<String, Any>?): String {
        return if (map == null) "{}" else Gson().toJson(map)
    }



}