package puc.tcc.logistics.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import puc.tcc.logistics.client.auth.AuthClient;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@Slf4j
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if (bypass(res, chain, httpServletRequest)) return;

        HttpServletResponse response = (HttpServletResponse) res;
        var authHeader = ((HttpServletRequest) request).getHeader("Authorization");
        if(authHeader == null || authHeader.isBlank()){
            response.sendError(HttpStatus.UNAUTHORIZED.value());
            log.info("UNAUTHORIZED! request_url={}, method={}, header={}", httpServletRequest.getRequestURI(), httpServletRequest.getMethod(), authHeader);
            return;
        }
        var user = new AuthClient().validate(authHeader);
        log.info("user={}", user);

        if(user == null){
            log.info("UNAUTHORIZED! request_url={}, method={}, header={}", httpServletRequest.getRequestURI(), httpServletRequest.getMethod(), authHeader);
            response.sendError(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute("user", user.getId());
        chain.doFilter(request, res);
    }

    private boolean bypass(ServletResponse res, FilterChain chain, HttpServletRequest httpServletRequest) throws IOException, ServletException {
        if(httpServletRequest.getRequestURI().contains("swagger")
                || httpServletRequest.getRequestURI().contains("favico")
                || httpServletRequest.getRequestURI().contains("api-docs")
                || httpServletRequest.getMethod().contains("OPTIONS")){
            chain.doFilter(httpServletRequest, res);
            log.info("BYPASS! request_url={}, method={}, header={}", httpServletRequest.getRequestURI(), httpServletRequest.getMethod(), authHeader);

            return true;
        }
        return false;
    }
}
