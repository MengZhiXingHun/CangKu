package com.bawei.dubin20190415.mvp.presenter;

import com.bawei.dubin20190415.mvp.model.MainModel;
import com.bawei.dubin20190415.mvp.model.MainModelIml;
import com.bawei.dubin20190415.mvp.view.MainView;

/**
 * @作者 杜彬
 * @创建日期 2019/3/20
 */
public class MainPresenterIml implements MainPresenter {

    private MainModelIml mainModelIml;
    private MainView mainView;

    @Override
    public void attch(MainView mainView) {
        mainModelIml = new MainModelIml();
        this.mainView = mainView;
    }

    @Override
    public void showData(String phone, String pswd) {
        mainModelIml.LoginGetData(phone, pswd, new MainModel.CallBackLinstener() {
            @Override
            public void success(String data) {
                mainView.success(data);
            }

            @Override
            public void fail() {
                mainView.fail();
            }
        });
    }

    @Override
    public void RegistData(String phone, String pswd) {
        mainModelIml.RegistGetData(phone, pswd, new MainModel.CallBackLinstener() {
            @Override
            public void success(String data) {
                mainView.success(data);
            }

            @Override
            public void fail() {
                mainView.fail();
            }
        });
    }

    @Override
    public void detch() {

        if (mainModelIml != null){
            mainModelIml = null;
        }
        if (mainView != null){
            mainView = null;
        }
        System.gc();

    }
}
