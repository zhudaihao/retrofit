package com.example.netlibrary;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.netlibrary.baseBean.BaseResponseEntity;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 回调封装
 */
public class CallBack<T> implements Callback<T> {
    private HttpListener httpListener;


    public CallBack(HttpListener httpListener) {
        this.httpListener = httpListener;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.body() == null) {
            return;
        }

        Log.e("zdh", "-------bod  " + response.body().toString());

        BaseResponseEntity baseBean = JSON.parseObject(response.body().toString(), BaseResponseEntity.class);


        //获取回调数据
        Object data = baseBean.getData();
        String requestWhat = baseBean.getCommon().getRequestWhat();
        ResponseBody responseBody = response.errorBody();

        /**
         * 0、2成功回调。其他失败回调
         */
        switch (baseBean.getMsg().getType()) {
            case 0:
            case 2:
                //实现回调方法
                httpListener.onSuccessful(requestWhat, data.toString());
                break;
            default:
                try {
                    Log.e("zdh", "-----------onFailure  " + responseBody.string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                httpListener.onFailure(requestWhat, responseBody);
                break;
        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        httpListener.onFailed(t.getMessage());
        Log.e("zdh", "-----------onFailure  " + t.getMessage());
    }


}
