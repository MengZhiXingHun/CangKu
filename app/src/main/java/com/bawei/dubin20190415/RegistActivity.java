package com.bawei.dubin20190415;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.dubin20190415.bean.RegistBean;
import com.bawei.dubin20190415.mvp.presenter.MainPresenterIml;
import com.bawei.dubin20190415.mvp.view.MainView;
import com.google.gson.Gson;

public class RegistActivity extends AppCompatActivity implements View.OnClickListener, MainView {

    private TextView tvBack,tvLogin;
    private EditText edPhone,edPswd,edPswdAgain;
    private Button btnRegist;
    private MainPresenterIml mainPresenterIml;
    private String phone;
    private String pswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        tvBack = (TextView)findViewById(R.id.tvBack);
        edPhone = (EditText)findViewById(R.id.edPhone);
        edPswd = (EditText)findViewById(R.id.edPswd);
        edPswdAgain = (EditText)findViewById(R.id.edPswdAgain);
        tvLogin = (TextView)findViewById(R.id.tvLogin);
        btnRegist = (Button)findViewById(R.id.btnRegist);

        mainPresenterIml = new MainPresenterIml();
        mainPresenterIml.attch(this);

        tvBack.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
        btnRegist.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvBack:
                finish();
                break;
            case R.id.tvLogin:
                finish();
                break;
            case R.id.btnRegist:
                Regist();
                break;
        }
    }

    private void Regist() {
        phone = edPhone.getText().toString().trim();
        pswd = edPswd.getText().toString().trim();
        String pswdAgain = edPswdAgain.getText().toString().trim();

        if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(pswd) && !TextUtils.isEmpty(pswdAgain)){

            if (pswdAgain.equals(pswd)){

                mainPresenterIml.RegistData(phone, pswd);

            }else{
                Toast.makeText(this,"与上一次密码不匹配",Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(this,"账号或密码不能为空",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void success(String data) {
        RegistBean registBean = new Gson().fromJson(data, RegistBean.class);
        String status = registBean.getStatus();
        if (status.equals("0000")){
            Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();

            SharedPreferences sp = getSharedPreferences("login", MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            SharedPreferences.Editor phone = edit.putString("phone", this.phone);
            SharedPreferences.Editor pswd = edit.putString("pswd", this.pswd);
            edit.commit();

            finish();
        }else{
            Toast.makeText(this,"注册失败",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void fail() {

    }
}
