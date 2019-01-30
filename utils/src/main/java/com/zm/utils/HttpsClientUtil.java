package com.zm.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;

/**
 * https请求
 * @author Administrator
 *
 */
public class HttpsClientUtil {  
	
	/**
	 * https  post请求
	 * @param url   
	 * @param json 
	 * @param header  
	 * @return
	 */
    public static String doPost(String url, String json, Map<String, String> header){  
        HttpClient httpClient = null;  
        HttpPost httpPost = null;  
        String result = "";  
        try{  
            httpClient = new SSLClient();  
            httpPost = new HttpPost(url);  
            httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
            if(header != null){
            	for (Map.Entry<String, String> entry : header.entrySet()) {
            		httpPost.addHeader(entry.getKey(), entry.getValue());
            	}
            }
            ContentType contentType = ContentType.create("application/json", CharsetUtils.get("UTF-8"));
            httpPost.setEntity(new StringEntity(json, contentType));
            HttpResponse response = httpClient.execute(httpPost);  
            if(response != null){  	
                HttpEntity resEntity =  response.getEntity();  
                if(resEntity != null){  
                    result = EntityUtils.toString(resEntity, CharsetUtils.get("UTF-8"));  
                }  
            }  
        }catch(Exception ex){  
        	result = "调用接口失败";
            ex.printStackTrace();  
        }  
        return result;  
    }
    
    
    /**
     * https  get请求
     * @param url
     * @return
     */
    public static String doGet(String url){
		String charset = "utf-8";
		HttpClient httpClient = null;
		HttpGet httpGet= null;
		String result = null;
		try {
			httpClient = new SSLClient();
			httpGet = new HttpGet(url);
			HttpResponse response = httpClient.execute(httpGet);
			if(response != null){
				HttpEntity resEntity = response.getEntity();
				if(resEntity != null){
					result = EntityUtils.toString(resEntity,charset);
				}
			}
		} catch (Exception e) {
			result = "调用接口失败";
			e.printStackTrace();
		}
		return result;
	}
    
    /**
     * 获取客户端IP
     * @param request
     * @return
     */
    public static String getCustomerIP(HttpServletRequest request) {
		String userip = request.getHeader("forwarded");
		if ((userip != null) && (userip.length() != 0)
				&& (!("unknown".equalsIgnoreCase(userip)))) {
			if (userip.indexOf(",") > 0) {
				String[] userips = userip.split(",");
				for (int i = 0; i < userips.length; ++i) {
					if ((userips[i].split("=")[0].equals("for"))
							&& (userips[i].split("=")[1].indexOf(".") > 0)) {
						userip = userips[i].split("=")[1];
					}

				}

			} else if ((userip.split("=")[0].equals("for"))
					&& (userip.split("=")[1].indexOf(".") > 0)) {
				userip = userip.split("=")[1];
			} else {
				userip = null;
			}

		}

		if ((userip == null) || (userip.length() == 0)
				|| ("unknown".equalsIgnoreCase(userip))) {
			String userip_x = request.getHeader("x-forwarded-for");
			if ((userip_x != null) && (userip_x.length() != 0)
					&& (!("unknown".equalsIgnoreCase(userip_x)))) {
				if (userip_x.indexOf(",") > 0) {
					if ((userip == null) || (userip.length() == 0)
							|| ("unknown".equalsIgnoreCase(userip)))
						try {
							userip = userip_x.split(",")[0];
						} catch (Exception localException) {
						}
				} else
					userip = userip_x;
			}
		}

		if ((userip == null) || (userip.length() == 0)
				|| ("unknown".equalsIgnoreCase(userip))) {
			userip = request.getHeader("Client-IP");
		}
		if ((userip == null) || (userip.length() == 0)
				|| ("unknown".equalsIgnoreCase(userip))) {
			userip = request.getHeader("Proxy-Client-IP");
		}
		if ((userip == null) || (userip.length() == 0)
				|| ("unknown".equalsIgnoreCase(userip))) {
			userip = request.getHeader("WL-Proxy-Client-IP");
		}
		if ((userip == null) || (userip.length() == 0)
				|| ("unknown".equalsIgnoreCase(userip))) {
			userip = request.getRemoteAddr();
		}

		if ((userip.substring(0, 2) == "0.")
				|| (userip.substring(0, 3) == "10.")
				|| (userip.substring(0, 4) == "127.")
				|| (userip.substring(0, 4) == "192.")
				|| (userip.substring(0, 4) == "172.")) {
			userip = request.getRemoteAddr();
		}
		return userip;
	}

}  
