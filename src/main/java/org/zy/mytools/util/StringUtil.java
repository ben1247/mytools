package org.zy.mytools.util;

import java.io.UnsupportedEncodingException;

/**
 * Created by yuezhang on 18/12/8.
 */
public class StringUtil {

    public static String convertUtf8(String str){
        return convert(str,"UTF-8");
    }

    public static String convertGBK(String str){
        return convert(str,"GBK");
    }

    public static String convertISO_8859_1(String str){
        return convert(str,"ISO-8859-1");
    }

    private static String convert(String str , String encoding){
        try {
            return new String(str.getBytes(),encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;
        }
    }

    public static boolean isNotEmpty(String str){
        return str != null && !"".equals(str);
    }

}
