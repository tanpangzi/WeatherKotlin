package com.coodays.repairrent.utli

import android.content.Context
import android.text.TextUtils
import android.widget.Toast

/**
 * created by tanjun
 * toast类
 */
class ToastUtil {

    companion object {

        /** LENGTH_LONG 时长  */
        private val LONG_DELAY = 3500 // 3.5 seconds
        /** LENGTH_SHORT 时长  */
        private val SHORT_DELAY = 2000 // 2 seconds

        private var mToast :Toast? = null

        /** 显示长时间的toast */
        fun showLongToast(context: Context,msg : String){
            show(context, msg, LONG_DELAY)
        }

        /** 显示短时间的Toast */
        fun showShortToast(context: Context, msg : String){
            show(context, msg, SHORT_DELAY)
        }

        /**
         * 显示toast
         */
        private fun show(context: Context,msg : String, duration : Int){
            if ( null == context || TextUtils.isEmpty(msg)){
                return
            }

            mToast = Toast.makeText(context, msg, duration)
            mToast!!.show()
        }

    }

}