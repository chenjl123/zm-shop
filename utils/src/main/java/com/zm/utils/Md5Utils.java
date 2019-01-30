package com.zm.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;

/**
 * MD5加密
 * @author Administrator
 *
 */
public class Md5Utils {

    /**
     * 编码，将字节数组转成可是别的字符串
     * @param b
     * @return
     */
    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }
    /**
     *将自己转成可识别字符串
     * @param b
     * @return
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
    /**
     *获取指定内容的MD5值
     * @param origin 被转化的内容
     * @param charsetname 字符集
     * @return
     */
    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname)) {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            }else{
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
            }
        }
        catch (Exception exception) {
        }
        return resultString;
    }
    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    public static String UrlEncode(String src)  throws UnsupportedEncodingException {
        return URLEncoder.encode(src, "UTF-8").replace("+", "%20");
    }
    //通过获取参数并且生成新的MD5校验码 然后和 获取到的MD5校验码 对比 以此验证传过来的数据是否被修改
    public static boolean validatePassword(String origin, String inputString, String charsetname){
        if(origin.equals(MD5Encode(inputString,charsetname).toUpperCase())){
            return true;
        } else{
            return false;
        }
    }

	public static String Md5(String plainText, Integer num) {
		StringBuffer buf = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if((num != null) && (num.intValue() == 16)){
			return buf.toString().substring(8, 24);
		}else{
			return buf.toString();
		}
		
	}
}