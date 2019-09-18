package com.example.netlibrary;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 封装的retrofit工具类
 */

public class RetrofitUtils {
    private static String baseUrl = NetRequest.BASE_URL;

    /**
     * 单利
     */
    private RetrofitUtils() {
    }

    private static RetrofitUtils retrofitUtils = new RetrofitUtils();

    public static RetrofitUtils getInstance() {
        return retrofitUtils;
    }


    /**
     * -----------------------------有公共参数-----------------------------------
     */


    /**
     * 获取返回对象为string
     *
     * @return
     */
    public Retrofit getStringCommonRetrofit() {
        Retrofit stringRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                //返回结果为String对象
                .addConverterFactory(ScalarsConverterFactory.create())
                //使用Gson解析 返回结果为bean对象
                //.addConverterFactory(GsonConverterFactory.create())
                //使用fastJson解析 返回bean对象
                //.addConverterFactory(FastJsonConverterFactory.create())
                //注意使用rxJava需要设置下面代码(注意还到对应依赖)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                //公共参数
                .build();


        return stringRetrofit;

    }


    /**
     * -----------------------------非公共参数-----------------------------------
     */


    /**
     * 获取返回对象为string
     *
     * @return
     */
    public Retrofit getRetrofit() {
        Retrofit stringRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                //返回结果为String对象
                .addConverterFactory(ScalarsConverterFactory.create())
                //注意使用rxJava需要设置下面代码(注意还到对应依赖)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return stringRetrofit;

    }


//
//    /**
//     * 获取返回对象为string
//     *
//     * @return
//     */
//    public Retrofit getStringCommonRetrofit() {
//        Retrofit stringRetrofit = new Retrofit.Builder()
//                .baseUrl(baseUrl)
//                //返回结果为String对象
//                .addConverterFactory(ScalarsConverterFactory.create())
//                //注意使用rxJava需要设置下面代码(注意还到对应依赖)
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                //公共参数
////                .client(getOkHttpClient())
//                .build();
//
//
//        return stringRetrofit;
//
//    }
//
//    /**
//     * 获取返回对象为json
//     *
//     * @return
//     */
//    public Retrofit getGsonCommonRetrofit() {
//        Retrofit gsonRetrofit = new Retrofit.Builder()
//                .baseUrl(baseUrl)
//                //返回结果为bean对象
//                .addConverterFactory(GsonConverterFactory.create())
//                //注意使用rxJava需要设置下面代码(注意还到对应依赖)
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                //公共参数
//                .client(getOkHttpClient())
//                .build();
//        return gsonRetrofit;
//
//    }
//
//
//    /**
//     * 获取返回对象为string
//     *
//     * @return
//     */
//    public Retrofit getStringRetrofit() {
//
//        Retrofit stringRetrofit = new Retrofit.Builder()
//                .baseUrl(baseUrl)
//                //返回结果为String对象
//                .addConverterFactory(ScalarsConverterFactory.create())
//                //注意使用rxJava需要设置下面代码(注意还到对应依赖)
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                //公共参数
//                .client(getOkHttpClient())
//                .build();
//        return stringRetrofit;
//
//    }
//
//
//    /**
//     * 获取返回对象为json
//     *
//     * @return
//     */
//    public Retrofit getGsonRetrofit() {
//        Retrofit gsonRetrofit = new Retrofit.Builder()
//                .baseUrl(baseUrl)
//                //返回结果为bean对象
//                .addConverterFactory(GsonConverterFactory.create())
//                //注意使用rxJava需要设置下面代码(注意还到对应依赖)
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                //公共参数
//                .client(getOkHttpClient())
//                .build();
//        return gsonRetrofit;
//
//    }
//
//
//    /**
//     * okHttp拦截器
//     * 设置公共参数对象
//     *
//     * @return
//     */
//    @NonNull
//    private OkHttpClient getOkHttpClient() {
//        //拦截器
//        CommonInterceptor interceptor = new CommonInterceptor();
//        //OKHttp
//        return new OkHttpClient.Builder()
//                .addInterceptor(interceptor)
//                .build();
//    }

}
