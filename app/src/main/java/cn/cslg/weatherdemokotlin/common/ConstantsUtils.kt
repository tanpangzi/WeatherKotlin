package com.coodays.repairrent.utli

import cn.cslg.weatherdemokotlin.AppApplication

/**
 * Created by payne on 2017/6/20.
 * 常量
 */
class ConstantsUtils {

    companion object {
        val IMAGE_PATH :String  ="repairent"

        val TAG_LOGOUT = "TAG_LOGOUT"
        val TAG_REGISTER = "TAG_REGISTER"
        val TAG_UPDATE_PASSWORD = "TAG_UPDATE_PASSWORD"

        val ORDER_STATE_ALL ="ALL"
        val ORDER_STATE_DOING ="DOING"
        val ORDER_STATE_COMPLETE ="COMPLETE"
        val ORDER_STATE_CANCLE ="CANCLE"

        const val BASE_URL = "http://172.16.20.156:50400/" //俊焕给的地址
        val METHOD_UPLOADIMG = "/app/auth/task/uploadImg" //图片上传

        const val LOG_TAG : String = "tanjun"

        val USERNAME_KEY = "username"
        val PASSWORD_KEY = "password"

        /** 普通图片压缩裁剪的后缀 */
        const val IMAGE_LOGO_COMPRESS = "_compress+new."

        val CONSTANT_KEY_BASE = AppApplication.getInstance().getApplicationContext().getPackageName() + "."

        /** sp文件名 -- 用户 */
        const val SP_KEY_FILE_USER_INFO = "zmx"

        /** sp保存在data/data目录下的文件名 */
        val SP_FILE_NAME = CONSTANT_KEY_BASE + ".spData"

        /** posttimeKey请求发送时间  */
        val SERVER_POST_TIME_KEY = "posttime"

        /** signKey :40位的SHA签名  */
        val SERVER_SIGN_KEY = "sign"

        val TOKEN_MAP_KEY = "token"

        private val USER_ID_KEY = "userID"

        private val ALIAS = "Alias"

        private val BASEDATA_VERSION = "BaseDataVersion"

        private val PRODUCTINFOLIST_VERSION = "ProductInfoListVersion"

        private val AREALIST_VERSION = "AreaListVersion"

        private val JPUSH_MESSAGE = "jpush_message"



        // ******************************图片****************************//
        /** 图片相关  */
        val positionImages = 1// gps安装 装车位置

        /** groupId  */
        val GROUPID_POSITIONIMAGES = "positionImages" // gps安装  装车位置
    }


}