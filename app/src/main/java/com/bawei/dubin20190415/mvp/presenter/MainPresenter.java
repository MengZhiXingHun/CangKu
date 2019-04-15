package com.bawei.dubin20190415.mvp.presenter;

import com.bawei.dubin20190415.mvp.view.MainView;

/**
 * @作者 杜彬
 * @创建日期 2019/3/20
 */
public interface MainPresenter {

    void attch(MainView mainView);
    void showData(String phone,String pswd);
    void RegistData(String phone,String pswd);
    void detch();

}
