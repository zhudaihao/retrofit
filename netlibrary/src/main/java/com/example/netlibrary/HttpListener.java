
package com.example.netlibrary;


import okhttp3.ResponseBody;

/**
 * 回调给使用者
 */
public interface HttpListener {

    void onSuccessful(String requestWhat, String data);

    void onFailure(String requestWhat, ResponseBody data);

    void onFailed(String error);

}
