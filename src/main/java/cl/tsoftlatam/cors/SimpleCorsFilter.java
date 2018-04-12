package cl.tsoftlatam.cors;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cl.tsoftlatam.cors.properties.CorsProperties;


/**
 * @author lpaolini
 * Interceptor de peticiones  HTTP. 
 * Creado para controlar todas aquellas peticiones OPTIONS Preflight - Para la comprobación de cabeceras, origenes y tipo de solicitud permitidas.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCorsFilter implements Filter {
	
	@Autowired
	private CorsProperties corsProperties;

    public SimpleCorsFilter() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        /**
         * Validar origen es permitido por cors. Si es permitido allowOrigin = true
         */
        
        Boolean allowOrigin = false;
        for(String origin : corsProperties.getAllowedDomains()) {
        	
        	if(origin.equals(request.getHeader("Origin"))) {
        		allowOrigin = true;
        		 break;
        	}
        }
        
        //Response. Agregar cabecera si el origen es permitido.
        if(allowOrigin) {
        	response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        }
        
        
        /**
         * Validar si el metodo utilizado en la petición es permitido por cors. Si es permitido allowMethod = true
         */
        Boolean allowMethod = false;
        String methods = null;
        for(String method : corsProperties.getAllowedMethods()) {
        	
        	if(request.getHeader("access-control-request-method") != null && request.getHeader("access-control-request-method").toUpperCase().equals(method.toUpperCase())) {
        		allowMethod = true;
        	}
        	
        	//Array obtenido desde propiedades cors. (.properties), Obtiene los metodos permitidos para las solicitudes.
        	if(methods != null) {
        		methods += "," + method;
        	}
        	else {
        		methods = method;
        	}
        }
        
        //Crear cabecera con los metodos permitidos por cors.
        if(methods != null)
        {
        	response.setHeader("Access-Control-Allow-Methods", methods);
        }
        
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization");

        /**
         * Si la petición es del tipo preflight (OPTIONS).
         */
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
        	
        	//Si el metodo de la petición no es permitido. Cambiar a estado metodo no permitido - HTTP CODE 405
        	if(!allowMethod) {
        		response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        	}
        	//Si el origen de la petición no es permitido. Cambiar a estado forbidden - HTTP CODE 403 - Debido a que falta el origen.
        	else if(!allowOrigin) {
        		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        	}
        	else {
        		response.setStatus(HttpServletResponse.SC_OK);
        	}
        } else {
    		chain.doFilter(req, res);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}