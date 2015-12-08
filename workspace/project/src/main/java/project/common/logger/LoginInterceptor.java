package project.common.logger;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    protected Log log = LogFactory.getLog(LoggerInterceptor.class);
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	
    	if (log.isDebugEnabled()) {
             log.debug("Login interceptor!");
    	}
    	
    	HttpSession session = request.getSession();
    	if(this.checkSession(session)){
    		// if user has session, do nothing..
    	} else {
    		// if user has not session!
    		response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script language='javascript'>");
			out.println("alert('로그인이 필요합니다');");
			out.println("location.href = '..';");
			out.println("</script>"); 
			out.close();
			return false;
        }
    return true;
   }
   
   private boolean checkSession(HttpSession session){
	   if(null == session.getAttribute("sId")){
		   return false;
	   } else {
		   return true;
	   }
   }
}
