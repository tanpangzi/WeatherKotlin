package cn.cslg.weatherdemokotlin

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import cn.cslg.weatherdemokotlin.bean.HeWeather
import cn.cslg.weatherdemokotlin.bean.Weather
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.android.synthetic.main.aqi.*
import kotlinx.android.synthetic.main.forecast.*
import kotlinx.android.synthetic.main.forecast_item.*
import kotlinx.android.synthetic.main.now.*
import kotlinx.android.synthetic.main.suggestion.*
import kotlinx.android.synthetic.main.title.*
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.defaultSharedPreferences
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread
import java.net.URL

/**
 * <br> Description
 * <br> Author: 谭俊
 * <br> PackageName cn.cslg.weatherdemokotlin
 * <br> Date: 2018/6/20
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
class WeatherActivity : AppCompatActivity() {

    /** 控件定义 */
    /** ?表示该变量是Nullable，不加表示该变量不可为null */
    var scrollView : ScrollView? = null
    var titleCity : TextView? = null
    var titleUpdateTime : TextView?= null
    var imgBing:ImageView? = null

    /** 两个公共属性 在chooseAreaFragment里调用 */
    var drawLayout : DrawerLayout? = null //侧滑
    var swipRefresh : SwipeRefreshLayout? = null //下拉刷新

    private val KEY = "739194e2b85c413c9487e17ba5bd8ee0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //状态栏透明化
        if (Build.VERSION.SDK_INT >= 21) {
            val v = window.decorView
            v.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.statusBarColor = Color.TRANSPARENT
        }
        setContentView(R.layout.activity_weather)


        drawLayout = find(R.id.drawer_layout)
        swipRefresh = find(R.id.swipe_refresh)
        scrollView = find(R.id.weather_layout)
        titleCity = find(R.id.title_city)
        titleUpdateTime = find(R.id.title_update_time)
        //layoutForecast = find(R.id.forecast_layout)

        imgBing = find(R.id.bing_pic_img)
        /** 加载图片 */
        loadBingImg()


        /** !!表示不为空时才能用 */
        /** ?表示是否为null */
        swipRefresh!!.setColorSchemeResources(R.color.colorPrimary)
        /** 控件id直接调用 */
        nav_button.setOnClickListener {
            drawLayout!!.openDrawer(GravityCompat.START)
        }

        /** weatherId */
        val weatherId = defaultSharedPreferences.getString("weather_id", "")
        weather_layout!!.visibility = View.INVISIBLE

        /** 请求数据 */
        getWeatherInfo(weatherId)

        /** 下载刷新 */
        swipRefresh!!.setOnRefreshListener {
            getWeatherInfo(weatherId)
        }

    }

    /**
     * 请求天气
     */
    fun getWeatherInfo(weatherId: String){
        val url = "http://guolin.tech/api/weather?cityid=" + weatherId + "&key=" + KEY
        async {
            val s = URL(url).readText()

            uiThread {
                val weather = Gson().fromJson(s, Weather::class.java)
                //关闭下拉刷新
                swipRefresh!!.isRefreshing = false
                Log.d("url",url)
                Log.d("url",weather.toString())
                showWeatherInfo(weather.HeWeather[0])
            }
        }
    }

    fun showWeatherInfo(info: HeWeather){
        //有时候请求出来是空数据（API问题吧）需要判定，否则报nullPointer异常
        if (info.status.equals("unknown city")){
            return
        }

        /** 直接调用布局里的控件 */
        title_city.text = info.basic.city
        title_update_time.text = info.basic.update.loc
        degree_text.text = info.now.tmp + " ℃"
        weather_info_text.text = info.now.cond.txt + "   "
        forecast_layout.removeAllViews()

        for (d in info.daily_forecast){
            val view = LayoutInflater.from(this).inflate(R.layout.forecast_item, null)
            /** 这里不能直接调用控件id 要先find */
            val date_text = view.find<TextView>(R.id.date_text)
            val info_text = view.find<TextView>(R.id.info_text)
            val max_text = view.find<TextView>(R.id.max_text)
            val min_text = view.find<TextView>(R.id.min_text)

            date_text.text = d.date
            max_text.text = d.tmp.max
            min_text.text = d.tmp.min

            if (d.cond.code_d == d.cond.code_n){
                info_text.text = d.cond.txt_d
            } else{
                info_text.text = d.cond.txt_d + "->" + d.cond.txt_n
            }

            forecast_layout.addView(view)
        }

        if (info.aqi != null){
            /** 直接加载控件 */
            aqi_text.text = info.aqi.city.aqi
            pm25_text.text = info.aqi.city.pm25
        }
        /** 直接加载控件 */
        comfort_text.text = "舒适度" + info.suggestion.comf.txt
        car_wash_text.text = "洗车指数" + info.suggestion.cw.txt
        sport_text.text = "运动建议" + info.suggestion.sport.txt

        scrollView!!.visibility = View.VISIBLE
    }

    //获取每日一图的API
    private fun loadBingImg() {
        val url = "http://guolin.tech/api/bing_pic"
        async {
            val s = URL(url).readText()
            uiThread {
                Glide.with(this@WeatherActivity).load(s).into(imgBing)
            }
        }
    }

}