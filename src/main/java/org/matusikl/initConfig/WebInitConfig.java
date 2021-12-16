package org.matusikl.initConfig;

import org.matusikl.config.CustomDataJPAConfig;
import org.matusikl.config.CustomWebMvcConfig;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { CustomDataJPAConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { CustomWebMvcConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/map/*"};
    }

    @Override
    protected DispatcherServlet createDispatcherServlet(WebApplicationContext servletAppContext)
    {
        final DispatcherServlet servlet = (DispatcherServlet) super.createDispatcherServlet(servletAppContext);
        servlet.setThrowExceptionIfNoHandlerFound(true);
        return servlet;
    }

}
