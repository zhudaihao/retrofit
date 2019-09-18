package com.example.netlibrary.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by AsionReachel on 2016/2/24.
 * E-mail:AsionReachel@qq.com
 */
public class Util {

    //签名
    public static String getSignature(String basestring, String secret)
            throws IOException {
        // 先将参数以其参数名的字典序升序进行排序
        StringBuffer sb = new StringBuffer();

        sb.append(basestring);
        sb.append(secret);

        // 使用MD5对待签名串求签
        byte[] bytes = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            bytes = md5.digest(sb.toString().getBytes("UTF-8"));
        } catch (GeneralSecurityException ex) {
            throw new IOException(ex);
        }

        // 将MD5输出的二进制结果转换为小写的十六进制
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex);
        }
        return sign.toString();
    }


    public static String mapToString(HashMap<String, Object> map) {
        StringBuffer baseString = new StringBuffer();
        List<Map.Entry<String, Object>> infoIds = new ArrayList<>(map.entrySet());
        //Collections.sort(infoIds, (o1, o2) -> (o1.getKey()).toString().compareTo(o2.getKey().toString()));
        Comparator<? super Map.Entry<String, Object>> text = new Comparator<Map.Entry<String, Object>>() {
            @Override
            public int compare(Map.Entry<String, Object> stringObjectEntry, Map.Entry<String, Object> t1) {
                int date = (stringObjectEntry.getKey()).toString().compareTo(t1.getKey().toString());
                return date;
            }
        };
        Collections.sort(infoIds, text);
        for (int i = 0; i < infoIds.size(); i++) {
            baseString.append(infoIds.get(i).toString());
        }

        return baseString.toString();
    }



    /**
     * 版本号
     *
     * @param context
     * @return
     */
    public static int getAppVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            int versionCode = info.versionCode;
            return versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }


    /**
     * 手机串码
     *
     * @return
     */
    public static String getIMEI() {
        return android.os.Build.MODEL;
    }




    /**
     * Base64 加密
     */
    public static String setBase64(String password) {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        byte[] pass = new byte[0];
        try {
            pass = password.getBytes("UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return base64Encoder.encode(pass);
    }

    /**
     * Base64 解密
     */
    public static String setUnBase64(String password) {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        String code = null;
        try {
            byte[] pass = base64Decoder.decodeBuffer(password);
            code = new String(pass, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }


        return code;
    }
}
