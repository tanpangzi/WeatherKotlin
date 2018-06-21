package com.coodays.repairrent.net

import android.provider.SyncStateContract
import cn.cslg.weatherdemokotlin.common.BASE_URL


class Urls {
    companion object {

        //接口首次获取cookie
        val initCookie = "/api/route.htm?method=user.cookie"
            get() = getDomain() + field
        //获取手机验证码
        val codemobile ="/api/route.htm?method=vcode.mobile"
           get() = getDomain() + field
        //验证手机验证码
        val validcode ="/api/route.htm?method=user.vcode.mobile.valid"
            get() = getDomain() + field
        //用户注册接口
        val register = "/api/route.htm?method=register"
            get() = getDomain()+ field
        //用户登录接口
        val login = "/api/route.htm?method=user.login"
            get() = getDomain()+ field
        //用户修改密码接口
        val updatePwd ="/api/route.htm?method = update.password"
            get() = getDomain()+ field
        //查询收货地址
        val addressList = "/api/route.htm?method=user.address.list"
            get() =  getDomain()+ field
        //新增收货地址
        val addressAdd = "/api/route.htm?method=user.address.add"
            get() = getDomain()+ field
        //修改收货地址
        val addressUpdate ="/api/route.htm?method=user.address.update"
            get() = getDomain()+field
        //删除收货地址
        val addressDelete ="/api/route.htm?method=user.address.remove"
           get() = getDomain()+field
        //修改默认收货地址
        val updateDefaultAddress = "/api/route.htm?method=user.address.uptdefault"
           get() = getDomain() + field
        //租赁图片上传
        val uploadImag ="/api/route.htm?method=base.img.common"
           get() = getDomain() + field
        //首页轮播图接口
        val homeBaner = "/api/route.htm?method=commodity.base.banner"
           get() = getDomain()+field
        //首页活动专区接口
        val homeActivity ="/api/route.htm?method=commodity.activity"
           get() = getDomain() +field

        val codeMobile = "/api/route.htm?method=vcode.mobile"
            get() = getDomain() + field

        //登录
        val logout = "/api/route.htm?method=exitLogon"
            get() = getDomain() + field
        //验证验证码
        val mobileValid = "/api/route.htm?method=user.vcode.mobile.valid"
            get() = getDomain() + field
        //修改密码
        val updatePassword = "/api/route.htm?method=update.password"
            get() = getDomain() + field
        //商品详情接口
        val goodsDetail ="/api/route.htm?method=commodity.getCommodityDetail"
            get() = getDomain()+ field
        //商品详情参数接口
        val goodPropetiList ="/api/route.htm?method=commodity.commodityPropertiesList"
            get() = getDomain() + field
        //商品分类（父级)接口
        val commidyCat = "/api/route.htm?method=commodity.cat.get"
            get() = getDomain()+ field
        //商品分类（子级）接口
        val typeChild ="/api/route.htm?method=commodity.cat.child"
            get() = getDomain()+ field
        //商品品牌接口
        val brandList ="/api/route.htm?method=commodity.commodityBrandList"
            get() = getDomain()+field
        //商品类别接口
        val commodityList ="/api/route.htm?method=commodity.commodityList"
            get() = getDomain()+field

        private fun getDomain(): String {
            return BASE_URL
        }
    }

}