package cn.cslg.weatherdemokotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import org.jetbrains.anko.defaultSharedPreferences
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val weatherId = defaultSharedPreferences.getString("weather_id","")
        if (!TextUtils.isEmpty(weatherId)){
            val intent = Intent(this, WeatherActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
