package cn.cslg.weatherdemokotlin;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.multidex.MultiDex;

import com.coodays.repairrent.utli.ConstantsUtils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import cn.cslg.weatherdemokotlin.bean.User;
import cn.cslg.weatherdemokotlin.util.PreferencesUtil;


/**
 * 全局公用Application类
 * <p>
 * <br> Author: 叶青
 * <br> Version: 1.0.0
 * <br> Date: 2016年12月24日
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
public class BaseApplication extends Application {

    /** 全局TApplication  获取全局上下文，可用于文本、图片、sp数据的资源加载，不可用于视图级别的创建和展示 */
    protected static BaseApplication application;
    /** 当前app处于start状态的activity数量 >0 则说明当前app在栈顶 */
    private int startCount;
    /** 用户信息bean */
    private User.UserInfoLoginBean userInfoBean;

    private RefWatcher refWatcher;

    public User.UserInfoLoginBean getUserInfoBean() {
        if (userInfoBean == null) {
            //userInfoBean = (User.UserInfoLoginBean) PreferencesUtil.get(ConstantsUtils.SP_KEY_FILE_USER_INFO, null);
            userInfoBean = (User.UserInfoLoginBean)
                    PreferencesUtil.Companion.get(ConstantsUtils.SP_KEY_FILE_USER_INFO, null );
        }
        return userInfoBean;
    }

    public void setUserInfoBean(User.UserInfoLoginBean userInfoBean) {
        this.userInfoBean = userInfoBean;
        saveUserInfoBean();
    }

    public String getToken() {
        if (getUserInfoBean() != null) {
            return userInfoBean.getToken();
        }
        return "";
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /**
     * 保存用户信息
     */
    public static void saveUserInfoBean() {
        PreferencesUtil.Companion.put(ConstantsUtils.SP_KEY_FILE_USER_INFO, BaseApplication.getInstance().userInfoBean);
    }

    /**
     * 获取全局TApplication
     */
    public static BaseApplication getInstance() {
        if (application == null) {
            synchronized (BaseApplication.class) {
                if (application == null) {
                    application = new BaseApplication();
                }
            }
        }
        return application;
    }

    private void initLeakCanary(){
        if (LeakCanary.isInAnalyzerProcess(this)){
            refWatcher = RefWatcher.DISABLED;
            return;
        }
        refWatcher = LeakCanary.install(this);
    }


    @Override
    public void onCreate() {
        application = this;
        //applicationId要和包路径相同，才能正常重启APP WelcomeActivity
        initData("SplashActivity");
        initLeakCanary();
        super.onCreate();
    }

    /**
     * 初始化数据
     * @param activityName
     */
    private void initData(String activityName) {

        // 错误日志捕捉工具WelcomeActivity
        // 本地文件构建
        //FileUtil.createAllFile();
        //// 实例化GalleryFinal
        //initGalleryFinal(this);
        //// 实例化极光推送
        //JPushUtil.getInstance(this).setAlias("123456789amos21");
        //// 实例化LeakCanary
        //LeakCanary.install(this);

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                //// 添加Activity到堆栈
                //AppManagerUtil.getAppManager().addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                startCount = startCount + 1;
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
                startCount = startCount - 1;
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                //// 移除堆栈Activity
                //AppManagerUtil.getAppManager().removeActivity(activity);
            }
        });
        registerReceiver();
    }

    public int getStartCount() {
        return startCount;
    }

    /**
     * 广播过滤
     */
    private void registerReceiver() {
        IntentFilter filter = new IntentFilter();
        // filter.addAction(Intent.ACTION_SCREEN_ON);        // 监听开屏
        filter.addAction(Intent.ACTION_SCREEN_OFF);          // 监听锁屏
        filter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);// 监听Home键
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF) || intent.getAction().equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                    //                    if (getStartCount() > 0) {//APP在栈顶
                    //                    }
                }
            }
        }, filter);
    }

}