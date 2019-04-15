package com.bawei.dubin20190415.mvp.model;

import com.bawei.dubin20190415.net.HttpUtil;

/**
 * @作者 杜彬
 * @创建日期 2019/3/20
 */
public class MainModelIml implements MainModel {
    //登录数据
    @Override
    public void LoginGetData(String phone, String pswd, final CallBackLinstener linstener) {
        String url = "http://172.17.8.100/small/user/v1/login";
        HttpUtil.getInstance().postAsyncTask(url, phone, pswd, new HttpUtil.CallBack() {
            @Override
            public void getBack(String data) {
                linstener.success(data);
            }
        });

    }

    @Override
    public void RegistGetData(String phone, String pswd, final CallBackLinstener linstener) {
        String url = "http://172.17.8.100/small/user/v1/register";
        HttpUtil.getInstance().postAsyncTask(url, phone, pswd, new HttpUtil.CallBack() {
            @Override
            public void getBack(String data) {
                linstener.success(data);
            }
        });
    }

}
