package br.com.cocus.automacaococus.filter;


import br.com.cocus.automacaococus.bean.LoginBean;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author jsoliveira
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = {"/interno/*", "/index"})
public class LoginFilter implements Filter {

    @Inject
    private LoginBean loginBean;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        try {

            if (loginBean.getUsuario() != null) {
                chain.doFilter(request, response);
            } else {

                String redir = ((HttpServletRequest) request).getContextPath() + "/";
                ((HttpServletResponse) response).sendRedirect(redir);
            }
        } catch (IOException ex) {
            Logger.getLogger(LoginFilter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(LoginFilter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            Logger.getLogger(LoginFilter.class.getName()).log(Level.SEVERE, null, e);
        }

    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

}
