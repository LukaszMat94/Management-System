package org.matusikl.initConfig;

import org.matusikl.config.CustomDataJPAConfig;
import org.matusikl.config.CustomWebMvcConfig;
import org.matusikl.config.JSONConfig;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { CustomDataJPAConfig.class, JSONConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { CustomWebMvcConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/controller/*"};
    }

    @Override
    protected DispatcherServlet createDispatcherServlet(WebApplicationContext servletAppContext)
    {
        final DispatcherServlet servlet = (DispatcherServlet) super.createDispatcherServlet(servletAppContext);
        servlet.setThrowExceptionIfNoHandlerFound(true);
        return servlet;
    }
}
