package Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author chandan
 */
public class LoginRedirect implements Filter {
    
    private FilterConfig filterConfig = null;
    
    public LoginRedirect() {
    }    
    
    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(true);
        String page = req.getRequestURL().toString();
        if(session.getAttribute("account") == null && 
                !page.equals("http://localhost:8080/NetbankApplication-war"
                + "/faces/login.xhtml")){
            resp.sendRedirect(req.getContextPath() + "/faces/login.xhtml");
        }
        try {
            chain.doFilter(request, response);
        } catch (Throwable t) {
        }
        
    }
    
    public void destroy() {
    }
    
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }
}
