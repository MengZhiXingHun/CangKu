package com.bawei.dubin20190415.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @作者 杜彬
 * @创建日期 2019/3/20
 */
public class HttpUtil {

    private HttpUtil(){}

    private static HttpUtil util;

    public static HttpUtil getInstance(){
        if (util == null){
            synchronized (HttpUtil.class){
                if (util == null){
                    util = new HttpUtil();
                }
            }
        }
        return util;
    }


    public static boolean isNetWorkConnected(Context context){

        if (context != null){
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();

            if (info != null){
                return info.isAvailable();
            }

        }
        return false;
    }

    public interface CallBack{
        void getBack(String data);
    }

    public static void postAsyncTask(String url, String phone, String pswd, final CallBack back){
        new AsyncTask<String, Void, String>(){

            @Override
            protected String doInBackground(String... strings) {
                return HttpUtil.postData(strings[0], strings[1], strings[2]);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                back.getBack(s);
            }
        }.execute(url,phone,pswd);
    }

    public static String postData(String url, String phone, String pswd){

        try {
            URL url1 = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setRequestMethod("POST");

            String body = "phone=" + URLEncoder.encode(phone) + "&pwd=" +URLEncoder.encode(pswd);
            connection.getOutputStream().write(body.getBytes());

//            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);

                StringBuilder builder = new StringBuilder();
                String str = "";

                while ((str = reader.readLine()) != null){
                    builder.append(str);
                }

                connection.disconnect();
                Log.i("zzz", builder.toString());
                return builder.toString();
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
