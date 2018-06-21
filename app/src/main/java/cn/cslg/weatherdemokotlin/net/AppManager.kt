package com.coodays.repairrent.utli

import android.app.Activity
import android.content.Context
import java.util.*


/**
 * 作者：payne on 2017/8/1 16:44
 */
class AppManager {
    var activityStack: Stack<Activity> = Stack<Activity>()


    /**
     * 单一实例
     */

    companion object {
        val instance: AppManager = AppManager()
    }

    /**
     * 添加Activity到堆栈
     */
    fun addActivity(activity: Activity) {
        activityStack.add(activity)
    }

    /**
     * 移除
     */
    fun removeActivity(activity: Activity) {
        activityStack.remove(activity)
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    fun currentActivity(): Activity {
        val activity = activityStack.lastElement()
        return activity
    }


    /**
     * 结束指定的Activity
     */
    fun finishActivity(activity: Activity) {
        activityStack.remove(activity)
        activity.finish()
    }


    /**
     * 结束所有Activity
     */
    fun finishAllActivity() {
        activityStack.forEach {
            it?.finish()
        }
        activityStack.clear()
    }

    fun finishAllActivityExcept(activity: Activity) {
        activityStack.forEach {
            if (it.localClassName != activity.localClassName)
                it.finish()
        }

        activityStack.clear()
    }


    fun finishAllActivityExcept(clazz: Class<*>) {

        activityStack.forEach {

            if (it.componentName.className != clazz.name) {
                it.finish()
            }
        }

        activityStack.clear()
    }

    /**
     * 退出应用程序
     */
    fun exitApp(context: Context) {
        try {
            finishAllActivity()
            System.exit(0)
            android.os.Process.killProcess(android.os.Process.myPid())
        } catch (e: Exception) {
        }
    }


}