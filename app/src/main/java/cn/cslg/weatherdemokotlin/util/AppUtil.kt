package com.coodays.repairrent.utli

import android.content.Context
import android.net.ConnectivityManager
import cn.cslg.weatherdemokotlin.AppApplication

/**
 * Created by payne on 2017/6/19.
 * 系统功能模块工具类
 */
class AppUtil {




    /**
     * http接口域名
     * 获得域名
     */
//    fun getDomain(mContext: Context): String {
//        val httpDomain: String= BuildConfig.DOMAIN
//        Log.v("appMoudle", "域名：" + httpDomain)
//        if (IfProxyExist(mContext)) {
//            //是否有代理
//            return  httpDomain
//        } else {
//            //添加阿里HttpDNS
//            val httpDns = HttpDns.getService(mContext, "158839")
//            httpDns.setLogEnabled(false)
//
//            val ip = httpDns.getIpByHostAsync(httpDomain)
//
//            if (ip != null) {
//                return https + ip + route
//            } else {
//                return https + httpDomain + route
//            }
//        }
//    }
     fun getDomain(): String {
        return ConstantsUtils.BASE_URL
    }
    fun isOnline(app: AppApplication): Boolean {
        val cm: ConnectivityManager = app.getSystemService(
                Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }


    /**
     * 检测系统是否已经设置代理，
     */
    fun IfProxyExist(ctx: Context): Boolean {
        val proxyHost: String? = System.getProperty("http.proxyHost")
        val proxyPort: Int
        val port = System.getProperty("http.proxyPort")
        proxyPort = Integer.parseInt(port ?: "-1")
        return proxyHost != null && proxyPort != -1
    }


}