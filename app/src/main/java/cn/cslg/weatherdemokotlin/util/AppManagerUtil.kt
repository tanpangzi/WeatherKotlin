package com.coodays.repairrent.utli

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import cn.cslg.weatherdemokotlin.BaseApplication
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by tanjun on 2017/6/19.
 * 系统功能模块工具类
 */
class AppManagerUtil {

    /** 构造 */
    constructor()

    /** 堆栈 */
    private var activityStack: Stack<Activity>? = null
    /** Activity管理对象 */
    var instance: AppManagerUtil? = null

    fun getAppManager(): AppManagerUtil {
        synchronized(AppManagerUtil::class.java) {
            if (null == instance){
                instance = AppManagerUtil()
            }
        }
        /** 两个!!表示不空为才能返回 */
        return instance!!
    }

    /**
     * 将activity添加到堆栈
     */
    fun addActivity(activity: Activity){
        if (activity == null) {
            activityStack = Stack()
        }
        activityStack!!.add(activity)
    }

    /**
     * 移除堆栈
     */
    fun removeActivity(activityRemove: Activity){
        if (null != activityRemove && activityStack!!.size > 0){
            if (activityStack!!.contains(activityRemove)){
                activityStack!!.remove(activityRemove)
            }
        }
    }

    /**
     * 结束指定类名的Activity
     */
    fun finishActivity(clazz : Any){
        if (null != activityStack && activityStack!!.size >= 1){
            for(activity in activityStack!!){
                if (activity.javaClass.name.equals(clazz)){
                    activityStack!!.remove(activity)
                    activity.finish()
                    break
                }
            }
        }
    }

    /**
     * 结束除指定类名外的所有activity
     */
    fun finishOtherActivity(){
        val curActivity = activityStack!!.lastElement()
        finishOtherActivity(curActivity)
    }

    /**
     * 结束除指定类名的所有activity
     */
    fun finishOtherActivity(activity: Activity){
        if (null != activityStack && activityStack!!.size >= 0){
            val activityStackClone = activityStack

            if (activityStackClone!!.contains(activity)){
                activityStackClone.remove(activity)
            }

            for (otherActivity in activityStackClone){
                if (otherActivity.javaClass.name.equals(activity.javaClass.name)){
                    activityStackClone.remove(otherActivity)
                    otherActivity.finish()
                }
            }
        }
    }


    /**
     * 结束指定类名外的所有activity
     */
    fun finishOtherActivity(clazz : Any){
        if (null != activityStack!! && activityStack!!.size >= 1){
            var activityStackClone = activityStack
            for(activity in activityStackClone!!){
                if (!activity.javaClass.equals(clazz)){
                    activityStack!!.remove(activity)
                    activity.finish()
                }
            }
        }
    }

    /**
     * 结束所有activity
     */
    fun finishAllActivity(){
        if (null != activityStack && activityStack!!.size >= 1) {
            var activityStackClone = activityStack
            for(activity in activityStackClone!!){
                activity.finish()
            }
            activityStack!!.clear()
        }
    }

    /**
     * 退出程序
     */
    fun exitApp(){
        val context = BaseApplication.getInstance().applicationContext
        /** 获取activityManager 定义时 这里加上ActivityManager 不然会导致下面的serviceList不好写*/
        val manager : ActivityManager= context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        /** 从窗口管理器中获取正在运行的service */
        val serviceList : ArrayList<ActivityManager.RunningServiceInfo> = manager.getRunningServices(100)
                as ArrayList<ActivityManager.RunningServiceInfo>
        /** 当前app所有服务的类名 */
        val service = getServicesName(serviceList)
        for (serviceName in service){
           val intent = Intent(context, Class.forName(serviceName))
        }
        finishAllActivity()
        android.os.Process.killProcess(android.os.Process.myPid())
        System.exit(0)
    }

    /**
     * 获取当前app所有的服务
     * 返回当前app所有的服务名称
     */
    fun getServicesName(list : ArrayList<ActivityManager.RunningServiceInfo>) : ArrayList<String>{
        val serviceList = ArrayList<String>()
        for (serviceInfo in list){
            val pid = serviceInfo.pid
            if(pid == android.os.Process.myPid()){
                serviceList.add(serviceInfo.service.className)
            }
        }
        return serviceList
    }




    /** 获取HostUrl */
    fun getDomain(): String {
        return ConstantsUtils.BASE_URL
    }

    fun isOnline(base: BaseApplication): Boolean {
        val cm: ConnectivityManager = base.getSystemService(
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