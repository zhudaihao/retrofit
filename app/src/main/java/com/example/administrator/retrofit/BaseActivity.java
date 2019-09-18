package com.example.administrator.retrofit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.netlibrary.HttpListener;
import com.example.netlibrary.NetClient;
import com.example.netlibrary.utils.Util;

import okhttp3.ResponseBody;

public class BaseActivity extends AppCompatActivity implements HttpListener {

    protected NetClient netClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        netClient = new NetClient(Util.getAppVersion(getApplicationContext()), this);
    }


    /**
     * 网络请求回调
     *
     * @param requestWhat
     * @param data
     */
    @Override
    public void onSuccessful(String requestWhat, String data) {

    }

    @Override
    public void onFailure(String requestWhat, ResponseBody data) {

    }

    @Override
    public void onFailed(String error) {

    }
}
