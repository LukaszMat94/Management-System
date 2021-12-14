package org.matusikl.initConfig;

import org.matusikl.config.CustomWebMvcConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {CustomWebMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/map/*"};
    }
}
