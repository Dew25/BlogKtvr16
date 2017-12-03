/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 *
 * @author Melnikov
 */
@WebFilter(filterName = "CharsetEncoding", urlPatterns = {"/*"},
            initParams={
                @WebInitParam(name="encoding",value="UTF-8", description="Encoding Param")
            })
public class CharsetEncoding implements Filter {
    
    private String code;
  

      
    public CharsetEncoding() {
    }    
    
        /**
     * Init method for this filter
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {    
        code = filterConfig.getInitParameter("encoding");
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
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        String codeRequest = request.getCharacterEncoding();
        if(code != null && !code.equalsIgnoreCase(codeRequest)){
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
        }
        
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    
}
