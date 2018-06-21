package com.coodays.repairrent.net

import android.util.ArrayMap
import com.coodays.repairrent.bean.*
import io.reactivex.Flowable
import retrofit2.http.*

/**
 * Created by payne on 2017/6/22.
 */
interface IHttpService {
    /**                                      --
     * 接口首次获取cookie
     */

    @GET()
    fun initCookie(@Url url: String): Flowable<BaseResultData>

    /**
     * 获取短信验证码
     */
    @FormUrlEncoded
    @POST()
    fun sendMsgCode(@Url url: String, @Field("mobile") mobile: String): Flowable<BaseResultData>

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST()
    fun register(@Url url: String, @FieldMap map: ArrayMap<String, String>): Flowable<BaseResultData>

    /**
     * 修改密码
     */
    @FormUrlEncoded
    @POST()
    fun updatePassword(@Url url: String, @Field("content") content: String): Flowable<BaseResultData>

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST()
    fun login(@Url url: String, @FieldMap map: ArrayMap<String, String>): Flowable<BaseResultData>

    /**
     * 退出登录
     */
    @POST()
    fun logout(@Url url: String): Flowable<BaseResultData>


    /**
     * 用户修改密码
     */
    @POST
    @FormUrlEncoded
    fun updatePwd(@Url url: String,@Field("content")content: String):Flowable<BaseResultData>

    /**
     * 查询收货地址接口
     */
    @POST
    @FormUrlEncoded
    fun getAddressList(@Url url: String,@Field("content")content: String):Flowable<BaseResultData>

    /**
     * 新增收货地址
     */
    @POST
    @FormUrlEncoded
    fun addAddress(@Url url: String,@Field("content")content: String) :Flowable<BaseResultData>

    /**
     * 修改收货地址
     */
    @POST
    @FormUrlEncoded
    fun updateAddress(@Url url: String,@Field("content")content: String):Flowable<BaseResultData>

    /**
     * 删除收货地址接口
     */
    @POST
    @FormUrlEncoded
    fun deleAddress(@Url url: String,@Field("content")content: String):Flowable<BaseResultData>

    /**
     * 修改默认收货地址
     */
    @POST
    @FormUrlEncoded
    fun updateDefaultAddress(@Url url: String,@Field("content")content: String):Flowable<BaseResultData>


    /**
     * 商品轮播图
     */
    @POST
    @FormUrlEncoded
    fun bannerImag(@Url url: String,@FieldMap map: ArrayMap<String, Any>):Flowable<HomeBanerData>

    /**
     *首页活动专区接口
     */
    @POST
    @FormUrlEncoded
    fun homeActivities(@Url url: String, @FieldMap map: ArrayMap<String, Any>):Flowable<ActivityData>

    /**
     * 商品详情接口
     */
    @POST
    @FormUrlEncoded
    fun getDetail(@Url url: String, @FieldMap map: ArrayMap<String, String>):Flowable<GoodDetail>

    /**
     *商品分类父级接口
     */
    @POST
    @FormUrlEncoded
    fun typeParent(@Url url: String,@FieldMap map: ArrayMap<String, Any>):Flowable<Typeparent>

    /**
     * 商品分类子级接口
     */
    @POST
    @FormUrlEncoded
    fun typeChild(@Url url: String,@FieldMap map: ArrayMap<String, Any>):Flowable<TypeChild>

    /**
     * 商品详情参数接口
     */
    @POST
    @FormUrlEncoded
    fun goodpropertieList(@Url url: String,@FieldMap map: ArrayMap<String, Any>):Flowable<GoodPropertiBean>

    /**
     * 商品品牌接口
     */
    @POST
    @FormUrlEncoded
    fun brandList(@Url url: String,@FieldMap map: ArrayMap<String, Any>) :Flowable<BaseResultData>


}