package br.com.k19.filters;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebFilter;

@WebFilter(servletNames={"Faces Servlet"})
public class JPAFilter implements Filter {
    
    private EntityManagerFactory factory;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException{
        this.factory = Persistence.createEntityManagerFactory("k19db-pu");
    }
    
    @Override
    public void destroy(){
        this.factory.close();
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)throws IOException, ServletException{
        
        //chegada
        EntityManager manager = this.factory.createEntityManager();
        request.setAttribute("EntityManager", manager);
        manager.getTransaction().begin();
        //Chegada
        
        //FAces servlet
        chain.doFilter(request, response);
        //faces servlet
        
        //Saida
        try{
            manager.getTransaction().commit();            
        }catch(Exception e){
            manager.getTransaction().rollback();
        }finally{
            manager.close();
        }
        //Saida
    }    
}
