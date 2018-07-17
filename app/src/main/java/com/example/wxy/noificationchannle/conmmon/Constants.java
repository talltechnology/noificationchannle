package com.example.wxy.noificationchannle.conmmon;

/**
 * Created by WXY on 2018/7/6.
 */

public class Constants {
    /**
     * 请求参数：请求参数
     公共参数：token、source=android、appVersion
     公共参数，无论什么地址，都需要传的参数
     返回数据：
     code：0为成功，1为失败，2为token过期
     msg：提示消息（可根据项目需求处理逻辑）
     **/
    public static final String URL="https://www.zhaoapi.cn/";




     /**1.登录接口
     接口地址：https://www.zhaoapi.cn/user/login
     https://www.zhaoapi.cn/user/login?mobile=12345678901&password=123456
     返回格式：json
     请求方式：get/post
     接口备注：用户登录接口
     请求参数说明：
     名称	类型	必填	说明
     mobile	string	是	手机号
     password	string	是	密码
     token	string	否	用户令牌
     */
     public static final String LOGIN_API="user/login";




    /**
     * 2.注册接口
     接口地址：https://www.zhaoapi.cn/user/reg
     返回格式：json
     请求方式：get/post
     接口备注：用户注册接口
     请求参数说明：
     名称	类型	必填	说明
     mobile	string	是	手机号
     password	string	是	密码
     token	String	否	用户令牌
     */
    public static final String REG_API="user/reg";
    /**
     * 3.上传头像
     接口地址：https://www.zhaoapi.cn/file/upload
     返回格式：json
     请求方式：get/post
     接口备注：上传头像接口（file路径修改）
     请求参数说明：
     名称	类型	必填	说明
     uid	string	是	用户id
     file	File	是	文件
     token	String	否	用户令牌
     */
    public static final String UPLOAD_API="file/upload";

}
