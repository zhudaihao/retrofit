package com.example.administrator.retrofit;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.netlibrary.bean.HomeBean;

import okhttp3.ResponseBody;

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void send(View view) {
        //网络请求一行代码
        netClient.getHome();
    }

    @Override
    public void onSuccessful(String requestWhat, String data) {
        super.onSuccessful(requestWhat, data);
        HomeBean homeBean = JSON.parseObject(data, HomeBean.class);
        Toast.makeText(this, "" + homeBean.getImageList().get(0).getFileUrl(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(String requestWhat, ResponseBody data) {
        super.onFailure(requestWhat, data);
    }

    @Override
    public void onFailed(String error) {
        super.onFailed(error);

    }
}
