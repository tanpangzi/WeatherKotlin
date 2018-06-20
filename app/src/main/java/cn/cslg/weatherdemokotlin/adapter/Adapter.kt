package cn.cslg.weatherdemokotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import cn.cslg.weatherdemokotlin.R
import cn.cslg.weatherdemokotlin.bean.DataCity
import kotlinx.android.synthetic.main.list_city_item.view.*
import org.jetbrains.anko.find

/**
 * <br> Description
 * <br> Author: 谭俊
 * <br> PackageName cn.cslg.weatherdemokotlin.adapter
 * <br> Date: 2018/6/20
 * <br> Copyright: Copyright © 2016 xTeam Technology. All rights reserved.
 */

/**
 * 省适配器
 * 参数数据 上下文 布局id
 * 继承ArrayAdapter
 */
class AdapterProvince(context: Context?, resId: Int, datas: ArrayList<DataCity.Province>)
    : ArrayAdapter<DataCity.Province>(context, resId, datas){

    /** 省略类型定义 */
    val resId = resId

    /**
     * 最后一个View是表示返回值类型
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        /** 省略类型定义 获取当前位置的item*/
        val item = getItem(position)
        /** 和java原来的写法一样 */
        val view= LayoutInflater.from(context).inflate(resId, parent, false)
        /** 查找控件 */
        val itemName = view.find<TextView>(R.id.item_name)
        /** 在控件是显示内容 */
        itemName.text = item.name
        return view
    }

}

/**
 * 市 适配器
 */
class AdapterCity(context: Context?, resId: Int, datas: ArrayList<DataCity.City>)
    : ArrayAdapter<DataCity.City>(context, resId, datas){
    val resId = resId
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        /** 获取当前位置item */
        val item = getItem(position)
        val view = LayoutInflater.from(context).inflate(resId, null)
        val itemName = view.find<TextView>(R.id.item_name)
        itemName.text = item.name
        return view
    }
}

/**
 * 区 适配器
 */
class AdapterCounty(context: Context?, resId: Int, datas: ArrayList<DataCity.County>)
    :ArrayAdapter<DataCity.County>(context, resId, datas){

    val resId = resId

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val item = getItem(position)
        val view = LayoutInflater.from(context).inflate(resId, null)
        val itemName = view.find<TextView>(R.id.item_name)
        itemName.text = item.name
        return view
    }
}