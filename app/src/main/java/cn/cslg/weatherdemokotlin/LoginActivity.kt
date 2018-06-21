package cn.cslg.weatherdemokotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_layout.*
import org.jetbrains.anko.longToast

/**
 * <br> Description
 * <br> Author: 谭俊
 * <br> PackageName cn.cslg.weatherdemokotlin
 * <br> Date: 2018/6/21
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
class LoginActivity :AppCompatActivity() {

    /*var etName : EditText? = null
    var etPwd : EditText? = null

    var btnLog : Button? = null*/

    var strName : String ?= null
    var strPwd : String ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)

        btn_login.setOnClickListener {
            strName = et_name.text.toString().trim()
            strPwd = et_pwd.text.toString().trim()

            if (strName.equals("tanjun") && strPwd.equals("123456")){
                showToast("登录成功")
            }

        }

    }

    fun showToast(msg : String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}