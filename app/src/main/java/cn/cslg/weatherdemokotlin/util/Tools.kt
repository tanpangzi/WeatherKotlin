package com.coodays.repairrent.utli

import java.util.regex.Pattern


/**
 * 作者：payne on 2017/7/24 14:18
 */
class Tools {

    companion object {
//        var userBean: UserData.UserBean? = null
//        fun initData() {
//            userBean = UsrDao.queryUser()
//        }
//
//        var IMG_HOST = ""
//        fun getImgUrl(imgUrl: String): String {
//            return IMG_HOST.plus(imgUrl)
//        }
//
//        fun loadImg(context: Context, url: String, imageView: ImageView) {
//            val options = RequestOptions()
//                    .placeholder(R.mipmap.ic_net_err)
//                    .error(R.mipmap.ic_net_err)
//                    .priority(Priority.HIGH)
//            Glide.with(context)
//                    .load(getImgUrl(url)).apply(options)
//                    .into(imageView)
//        }
//
//        fun loadImgWithHost(context: Context, url: String, imageView: ImageView) {
//            val options = RequestOptions()
//                    .placeholder(R.mipmap.ic_net_err)
//                    .error(R.mipmap.ic_net_err)
//                    .priority(Priority.HIGH)
//            Glide.with(context)
//                    .load(url).apply(options)
//                    .into(imageView)
//        }
//
//        fun loadImgWithHost(context: Context, srcId: Int, imageView: ImageView) {
//            val options = RequestOptions()
//                    .placeholder(R.mipmap.ic_net_err)
//                    .error(R.mipmap.ic_net_err)
//                    .priority(Priority.HIGH)
//            Glide.with(context)
//                    .load(srcId).apply(options)
//                    .into(imageView)
//        }
//
        /**
         * 判断是手机号是否正确

         * @param s String 类型
         */
        fun isRightPhoneNumber(s: String): Boolean {
            val p = Pattern.compile("^(13[0-9]|14[579]|15[012356789]|17[0135678]|18[0-9])[0-9]{8}$")
            val m = p.matcher(s)
            val b = m.matches()
            return b
        }
//
        /**
         * 判断密码是否符合规格
         */
        fun isRightPassword(s: String): Boolean {

            return s.length in 6..16
        }
//
//        /**
//         * 转换成百分比
//         */
//        fun castToPercent(num: Float): String {
//            val format = DecimalFormat("#%")
//            return format.format(num)
//        }
//
//        fun castToTwoDecimalPlaces(num: Float): String {
//            val format = DecimalFormat(".00")
//            return format.format(num)
//        }
//
//        fun formatTime(time: Long): String {
//            val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)
//            return formatter.format(time)
//        }
//
//
//        /**
//         *直接拨打电话
//         */
//        fun callPhone(context: Context, phone: String) {
//            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone))
//            val rxPermissions = RxPermissions(context as Activity)
//            rxPermissions
//                    .request(Manifest.permission.CALL_PHONE)
//                    .subscribe { granted ->
//                        if (granted) {
//                            context.startActivity(intent)
//                        } else {
//
//                        }
//                    }
//
//        }
//
//        fun getVersionName(context: Context): String {
//            val packageManager = context.packageManager
//            //getPackageName()是你当前类的包名，0代表是获取版本信息
//            val packInfo = packageManager.getPackageInfo(context.packageName, 0)
//            return packInfo.versionName
//        }
//
//        fun hasLogin(): Boolean {
//            return userBean != null
//        }
//
//
//        fun isVip(): Boolean {
//            if (userBean!!.mallStore.isEmpty() || userBean!!.mallStore[0] == null) {
//                return false
//            } else if (userBean!!.mallStore[0].isApproved()) {
//                return true
//            }
//            return false
//        }
//
//        fun exitLogin() {
//            userBean = null
//            PersistentCookieStore.newInstance().removeAll()
//            UsrDao.deleteUser()
//        }
//
//        fun createIWXAPI(context: Context): IWXAPI {
//            val api = WXAPIFactory.createWXAPI(context, "wx45a875bdcc36165b", false)
//            api.registerApp("wx45a875bdcc36165b")
//            return api
//        }
//
//        fun isWeChatPaySupport(context: Context): Boolean {
//            val api = createIWXAPI(context);
//            return api.wxAppSupportAPI >= Build.PAY_SUPPORTED_SDK_INT
//        }
//
//        fun round(price: Double): Double {
//            val format = DecimalFormat()
//            format.maximumFractionDigits = 2
//            return format.format(price).toDouble()
//        }
//
//        var list = arrayListOf<Activity>()
//        fun addActivity(activity: Activity) {
//            list.add(activity)
//        }
//
//        fun clearActivity() {
//            for (item in list) {
//                item.finish()
//            }
//            list.clear()
//        }
        //验证身份证号码
        val REGEX_ID:String = "^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$";
        fun isIDCard(card :String):Boolean{
            return Pattern.matches(REGEX_ID,card)
        }

    }

}