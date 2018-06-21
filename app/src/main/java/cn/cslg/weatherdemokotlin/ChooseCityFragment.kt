package cn.cslg.weatherdemokotlin

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import cn.cslg.weatherdemokotlin.adapter.AdapterCity
import cn.cslg.weatherdemokotlin.adapter.AdapterCounty
import cn.cslg.weatherdemokotlin.adapter.AdapterProvince
import cn.cslg.weatherdemokotlin.bean.DataCity
import cn.cslg.weatherdemokotlin.common.LOG_TAG
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.choose_area.*
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.defaultSharedPreferences
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.uiThread
import java.lang.annotation.RetentionPolicy
import java.net.URL
import java.util.ArrayList

/**
 * <br> Description 城市列表 侧滑fragment
 * <br> Author: 谭俊
 * <br> PackageName cn.cslg.weatherdemokotlin
 * <br> Date: 2018/6/20
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
class ChooseCityFragment : Fragment() {

    private val LEVEL_WHOLE = 3

    /** 省 */
    private val LEVEL_PROVINCE = 0
    /** 市 */
    private val LEVEL_CITY = 1
    /** 区 */
    private val LEVEL_COUNTY = 2

    private var current_level = 0

    /** 省json */
    private val PROVINCE_URL = "http://guolin.tech/api/china/"

    /** 省列表 */
    private var provinceList = ArrayList<DataCity.Province>()
    /** 市列表 */
    private var cityList = ArrayList<DataCity.City>()
    /** 区列表 */
    private var countyList = ArrayList<DataCity.County>()

    /** 当前省 */
    private var curProvince: DataCity.Province? = null
    /** 当前市 */
    private var curCity: DataCity.City? = null
    /** 当前区 */
    private var curCounty: DataCity.County? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.choose_area, container, false)
        if (activity is WeatherActivity){
            val res = (activity as WeatherActivity).resources
            val resId = res.getIdentifier("status_bar_height", "dimen", "android")
            val height = res.getDimensionPixelSize(resId)
            val paddingContent = view.find<LinearLayout>(R.id.padding_content)
            paddingContent.setPadding(0, height, 0, 0)
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        current_level = 0
        list_view.setOnItemClickListener {
            /** 没有用到的参数用 "_"表示 */
            _, _, position, _ ->
            when(current_level){

                LEVEL_PROVINCE -> {
                    Log.e(LOG_TAG, "当前level --" + current_level)
                    curProvince = provinceList[position]
                    queryCity()
                }
                LEVEL_CITY -> {
                    Log.e(LOG_TAG, "当前level --" + current_level)
                    curCity = cityList[position]
                    queryCounty()
                }
                LEVEL_COUNTY -> {
                    Log.e(LOG_TAG, "当前level --" + current_level)
                    curCounty = countyList[position]
                    defaultSharedPreferences.edit().putString("weather_id", curCounty!!.weather_id).apply()
                    if (activity is MainActivity) {
                        startActivity<WeatherActivity>()
                        (activity as MainActivity).finish()   //将MainActivity销毁掉
                    } else if (activity is WeatherActivity) {
                        val act = activity as WeatherActivity
                        act.drawLayout!!.closeDrawers()
                        act.swipRefresh!!.isRefreshing = true      //显示下拉刷新
                        act.getWeatherInfo(curCounty!!.weather_id)

                        current_level = 0
                    }
                }
            }

        }

        back_button.setOnClickListener {
            if (current_level == LEVEL_CITY)
                queryProvince()
            else if (current_level == LEVEL_COUNTY)
                queryCity()
        }
        queryProvince()

    }

    /**
     * 查询省
     */
    fun queryProvince(){
        title_text.text = "中国"
        /**
         * 这里的隐藏和显示的写法和java不同
         */
        back_button.visibility = View.INVISIBLE //隐藏返回键
        showProgress()

        async {
            val province = URL(PROVINCE_URL).readText()
            uiThread {
                closeProgress()
                val t = object : TypeToken<List<DataCity.Province>>() {}.type
                provinceList = Gson().fromJson<List<DataCity.Province>>(province, t) as ArrayList<DataCity.Province>
                val adapter = AdapterProvince(context, R.layout.list_city_item, provinceList)
                list_view.adapter = adapter
                list_view.setSelection(0)
                current_level = LEVEL_PROVINCE
            }
        }
    }


    /**
     * 查询市
     */
    private fun queryCity(){
        title_text!!.text = curProvince!!.name //当前省
        back_button!!.visibility = View.VISIBLE
        showProgress()

        async {
            val source = URL(PROVINCE_URL + "/" + curProvince!!.id).readText()

            uiThread {
                closeProgress()
                val t = object : TypeToken<List<DataCity.City>>(){}.type
                cityList = Gson().fromJson<List<DataCity.City>>(source, t) as ArrayList<DataCity.City>
                val adapter = AdapterCity(context, R.layout.list_city_item, cityList)
                list_view!!.adapter = adapter
                list_view!!.setSelection(0)
                current_level = LEVEL_CITY
            }

        }
    }

    /**
     * 查询区
     */
    fun queryCounty(){
        title_text.text = curCity!!.name
        back_button.visibility = View.VISIBLE//显示返回
        showProgress()
        async {
            val source = URL(PROVINCE_URL + "/" + curProvince!!.id + "/" + curCity!!.id).readText()
            uiThread {
                closeProgress()
                val t = object : TypeToken<ArrayList<DataCity.County>>(){}.type
                countyList = Gson().fromJson<ArrayList<DataCity.County>>(source, t) as ArrayList<DataCity.County>
                val adapter = AdapterCounty(context, R.layout.list_city_item, countyList)
                list_view.adapter = adapter
                list_view.setSelection(0)
                current_level = LEVEL_COUNTY
            }
        }
    }

    //进度条
    var progress: ProgressDialog? = null

    /**
     * 显示进度条
     */
    fun showProgress(msg : String = "加载中"){
        if (progress == null) {
            progress = ProgressDialog(activity)
            progress!!.setMessage(msg)
            progress!!.setCancelable(false)
        }
        progress!!.show()
    }

    /**
     * 关闭进度条
     */
    fun closeProgress(){
        if (progress != null) {
            progress!!.dismiss()
        }
    }

}