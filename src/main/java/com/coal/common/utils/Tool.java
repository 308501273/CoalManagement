package com.coal.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import sun.misc.BASE64Encoder;

//令牌生产器
public class Tool {
    private Tool(){}
    private static Tool instance = new Tool();
    public static Tool getInstance(){
        return instance;
    }
    public static String getRedisKey(String phone){
        return getTokenCode(phone,"");
    }
    public static String getTokenCode(String key,String code){
        String value = key+code;
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年-MM月dd日-HH时mm分ss秒");
        Date date = new Date(currentTime);

        //获取数据指纹，指纹是唯一的
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] b = md.digest(value.getBytes());//产生数据的指纹
            //Base64编码
            BASE64Encoder be = new BASE64Encoder();
            be.encode(b);
            return be.encode(b);//制定一个编码
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
     getTokenCode("1","");
    }
}