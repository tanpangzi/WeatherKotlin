package cn.cslg.weatherdemokotlin.bean

/**
 * <br> Description
 * <br> Author: 谭俊
 * <br> PackageName cn.cslg.weatherdemokotlin.bean
 * <br> Date: 2018/6/20
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */
class DataCity {
    /** 省 */
    data class Province(val id: Int, val name: String)
    /** 市 */
    data class City(val id: Int, val name: String)
    /** 区 */
    data class County(val id:Int, val name: String, val weather_id:String)

}