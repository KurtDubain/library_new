package filter;

/**
 * 登陆权限过滤器
 */

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginFilter implements Filter{

    public void init(FilterConfig filterConfig) throws ServletException {
    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws ServletException, IOException {
        HttpServletRequest req=(HttpServletRequest) request;
        HttpServletResponse res= (HttpServletResponse)response;
        Object obj=req.getSession().getAttribute("user");
         if(obj!=null){
            try{
                chain.doFilter(request, response);
            } catch (ServletException | IOException e){
                e.printStackTrace();
            }
        }
        else{
            try{
                res.sendRedirect("/2_war_exploded/login.jsp");
          } catch (IOException e){
            e.printStackTrace();
        }
    }
}

    @Override
    public void destroy() {

    }

}
