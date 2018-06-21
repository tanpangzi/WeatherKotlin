package cn.cslg.weatherdemokotlin.bean

/**
 * <br> Description
 * <br> Author: 谭俊
 * <br> PackageName cn.cslg.weatherdemokotlin.bean
 * <br> Date: 2018/6/21
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
class User {
    data class UserInfoLoginBean(val alias : String,
                                 val userName : String,
                                 val userId : String,
                                 val token : String)
}