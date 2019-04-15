package com.bawei.dubin20190415.mvp.model;

/**
 * @作者 杜彬
 * @创建日期 2019/3/20
 */
public interface MainModel {

    interface CallBackLinstener{
        void success(String data);
        void fail();
    }

    //登录接口
    void LoginGetData(String phone,String pswd,CallBackLinstener linstener);

    //注册接口
    void RegistGetData(String phone,String pswd,CallBackLinstener linstener);

}
