package com.example.netlibrary;

import com.example.netlibrary.iRequest.RestService;
import com.example.netlibrary.utils.AESCrypt;
import com.example.netlibrary.utils.Util;

import java.io.IOException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Retrofit;

public class NetClient {
    private AESCrypt aesc;
    private static final int OS_TYPE = 2;
    private static int LANGUAGE = 1;
    private int version;
    private HashMap<String, Object> map;
    private Retrofit stringCommonRetrofit;
    private HttpListener httpListener;

    public NetClient(int version, HttpListener httpListener) {
        try {
            aesc = new AESCrypt();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.version = version;
        this.httpListener = httpListener;

        //初始化
        init();
    }

    private void init() {
        getMap();
        RetrofitUtils instance = RetrofitUtils.getInstance();
        stringCommonRetrofit = instance.getStringCommonRetrofit();
    }

    /**
     * 初始化化map
     */
    private HashMap<String, Object> getMap() {
        if (map == null) {
            map = new HashMap<>();
        }
        setPublicParameter();
        return map;
    }

    /***
     * 公告参数
     */
    private void setPublicParameter() {
        map.clear();
        //公告参数配置项
        map.put("osType", OS_TYPE);
        map.put("version", version);
        map.put("imei", Util.getIMEI());
        map.put("language", LANGUAGE);
    }


    /**
     * 添加签名参数
     */
    private void setSignature() {
        //添加签名
        try {
            map.put("signture", Util.getSignature(Util.mapToString(map), "4DFF2D1F474B3215FEA02799774DB76A"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * get请求封装
     *
     * @param url         借款地址
     * @param requestWhat 接口标记
     */
    private void get(String url, String requestWhat) {
        map.put("requestWhat", requestWhat);
        setSignature();

        //请求retrofit
        RestService iHomeNew2 = stringCommonRetrofit.create(RestService.class);
        Call<String> home = iHomeNew2.get(url, map);
        //请求异步回调
        home.enqueue(new CallBack<String>(httpListener));

    }


    private void pot(String url, String requestWhat) {
        map.put("requestWhat", requestWhat);
        setSignature();

        //请求retrofit
        RestService iHomeNew2 = stringCommonRetrofit.create(RestService.class);
        Call<String> home = iHomeNew2.post(url, map);
        //请求异步回调
        home.enqueue(new CallBack<String>(httpListener));

    }

    /**
     * ------------------------------------------------------网络接口请求-----------------------------------------------------------------------
     */

    public void getHome() {
        get(NetRequest.HOME, RequestWhat.HOME);
    }






}
