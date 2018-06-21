package cn.cslg.weatherdemokotlin.util

import android.content.Context
import cn.cslg.weatherdemokotlin.AppApplication
import com.coodays.repairrent.utli.ConstantsUtils
import java.util.*

/**
 * <br> Description sp文件
 * <br> Author: 谭俊
 * <br> PackageName cn.cslg.weatherdemokotlin.util
 * <br> Date: 2018/6/21
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
class PreferencesUtil {

    companion object {
        /**
         * 保存
         * Any相当于java里的object
         */
        fun put(key: String, value: Any) {
            val context = AppApplication.getInstance().applicationContext
            val sp = context.getSharedPreferences(ConstantsUtils.SP_FILE_NAME, Context.MODE_PRIVATE)
            val editor = sp.edit()

            when (value) {
                is String -> editor.putString(key, value)
                is Int -> editor.putInt(key, value)
                is Boolean -> editor.putBoolean(key, value)
                is Float -> editor.putFloat(key, value)
                is Long -> editor.putLong(key, value)
                else -> throw Throwable("Cannot save")
            }

            /**
             * 这种写法比when麻烦太多
             * is 相当于java的instanceOf
             */
            /*if (value is String){
                editor.putString(key, value)
            } else if (value is Int){
                editor.putInt(key, value)
            } else if (value is Boolean){
                editor.putBoolean(key, value)
            } else if (value is Float){
                editor.putFloat(key, value)
            } else if (value is Long){
                editor.putLong(key , value)
            }else{
                throw Throwable("cannot save")
            }*/

            if (!editor.commit()) {
                editor.apply()
            }
        }

        /**
         * 取出
         * Any相当于java里的object
         */
        fun get(key: String, default: Any): Any {
            val context = AppApplication.getInstance().applicationContext
            val sp = context.getSharedPreferences(ConstantsUtils.SP_FILE_NAME, Context.MODE_PRIVATE)

            return when (default) {
                is String -> sp.getString(key, default)
                is Int -> sp.getInt(key, default)
                is Boolean -> sp.getBoolean(key, default)
                is Float -> sp.getFloat(key, default)
                is Long -> sp.getLong(key, default)
                else -> throw UnknownFormatConversionException("SharedPreferences not found")
            }
        }

        /**
         * 去掉某个字段
         */
        fun remove(key: String) {
            val context = AppApplication.getInstance().applicationContext
            val sp = context.getSharedPreferences(ConstantsUtils.SP_FILE_NAME, Context.MODE_PRIVATE)

            val editor = sp.edit()
            editor.remove(key)

            if (!editor.commit()) {
                editor.apply()
            }
        }

        /**
         * 清除所有数据
         */
        fun clear() {
            val context = AppApplication.getInstance().applicationContext
            val sp = context.getSharedPreferences(ConstantsUtils.SP_FILE_NAME, Context.MODE_PRIVATE)
            val editor = sp.edit()
            editor.clear()

            if (!editor.commit()) {
                editor.apply()
            }
        }

        /**
         * 查询某个key是否已经存在
         */
        fun isContain(key: String): Boolean {
            val context = AppApplication.getInstance().applicationContext
            val sp = context.getSharedPreferences(ConstantsUtils.SP_FILE_NAME, Context.MODE_PRIVATE)
            return sp.contains(key)
        }
    }


}