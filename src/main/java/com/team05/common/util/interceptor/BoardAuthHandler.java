package com.team05.common.util.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.team05.command.UserVO;

public class BoardAuthHandler extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session=request.getSession();
		UserVO userVO=(UserVO)session.getAttribute("userVO");
		String qid=request.getParameter("qid");
		System.out.println(qid);
		if(userVO !=null) {
			if(qid !=null) {
				if(userVO.getUserId().equals(qid)) {
					return true;
				}
			}
		}
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println("<script>");
		out.println("alert('권한이 없습니다');");
		out.println("history.go(-1)");
		out.println("</script>");
		return false;
	}
	
	
}
