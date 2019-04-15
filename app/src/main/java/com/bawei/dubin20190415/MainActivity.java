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

import com.bawei.dubin20190415.bean.LoginBean;
import com.bawei.dubin20190415.mvp.presenter.MainPresenterIml;
import com.bawei.dubin20190415.mvp.view.MainView;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener {

    private EditText edPhone,edPswd;
    private TextView tvRegist;
    private Button btnLogin;
    private MainPresenterIml mainPresenterIml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edPhone = (EditText)findViewById(R.id.edPhone);
        edPswd = (EditText)findViewById(R.id.edPswd);
        tvRegist = (TextView)findViewById(R.id.tvRegist);
        btnLogin = (Button)findViewById(R.id.btnLogin);

        SharedPreferences sp = getSharedPreferences("login", MODE_PRIVATE);
        String phone = sp.getString("phone", "");
        String pswd = sp.getString("pswd", "");

        edPhone.setText(phone);
        edPswd.setText(pswd);

        mainPresenterIml = new MainPresenterIml();
        mainPresenterIml.attch(this);

        tvRegist.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void success(String data) {
        LoginBean loginBean = new Gson().fromJson(data, LoginBean.class);
        String status = loginBean.getStatus();
        if (status.equals("0000")){
            Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
            LoginBean.Content result = loginBean.getResult();
            String headPic = result.getHeadPic();
            String nickName = result.getNickName();

            SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("headPic",headPic);
            edit.putString("nickName",nickName);
            edit.commit();

            startActivity(new Intent(MainActivity.this,ShowActivity.class));

        }else{
            Toast.makeText(this,"登录失败",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void fail() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvRegist:
                startActivity(new Intent(MainActivity.this,RegistActivity.class));
                break;
            case R.id.btnLogin:
                Login();
                break;
        }
    }

    private void Login() {
        String phone = edPhone.getText().toString().trim();
        String pswd = edPswd.getText().toString().trim();

        if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(pswd)){
            mainPresenterIml.showData(phone,pswd);
        }

    }
}
