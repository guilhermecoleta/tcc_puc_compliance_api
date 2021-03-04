package puc.tcc.logistics.filter;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import puc.tcc.logistics.client.auth.AuthClient;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if(httpServletRequest.getRequestURI().contains("swagger")
                || httpServletRequest.getRequestURI().contains("favico")
                || httpServletRequest.getRequestURI().contains("api-docs")){
            chain.doFilter(httpServletRequest, res);
            return;
        }

        HttpServletResponse response = (HttpServletResponse) res;
        var authHeader = ((HttpServletRequest) request).getHeader("Authorization");
        if(authHeader == null || authHeader.isBlank()){
            response.sendError(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        var user = new AuthClient().validate(authHeader);
        if(user == null){
            response.sendError(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute("user", user.getId());
        chain.doFilter(request, res);
    }
}
