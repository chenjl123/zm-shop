package com.zm.zuul;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * filterType：返回过滤器的类型：pre、route、post、error   
 *             PRE：请求被路由之前被调用，身份验证
 *             ROUTING：将请求路由到微服务。
 *             POST：路由到微服务之后执行
 *             ERROR：在其他阶段发生错误时执行。
 *	filterOrder:返回int值，来确定filter的执行顺序，不同的过滤器允许返回相同的数字
 *	shouldFilter:返回boolean确定filter是否执行
 *	run：过滤器执行内容
 * @author Administrator
 *
 */
public class PreRequestLogFilter extends ZuulFilter {

	@Override
	public Object run() throws ZuulException {
		// TODO Auto-generated method stub
		 RequestContext ctx = RequestContext.getCurrentContext();
	    HttpServletRequest request = ctx.getRequest();
	    // request.getMethod(), request.getRequestURL().toString()));
	    String token = request.getParameter("token");
	    if (token == null){
	        //认证失败
	        System.out.println("token验证失败");
	        HttpServletResponse response = ctx.getResponse();
	        response.setCharacterEncoding("utf-8");  //设置字符集
	        response.setContentType("text/html; charset=utf-8"); //设置相应格式
	        response.setStatus(401);
	        ctx.setSendZuulResponse(false); //不进行路由
	        try {
	            response.getWriter().write("token 验证失败"); //响应体
	        } catch (IOException e) {
	        	System.out.println("response io异常");
	            e.printStackTrace();
	        }
	        ctx.setResponse(response);
	        return null;
	    }
	    System.out.println("token验证成功");
	    return null;
	}

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

}
