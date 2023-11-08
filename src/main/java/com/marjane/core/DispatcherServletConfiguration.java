package com.marjane.core;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * This class configures the Dispatcher Servlet for the application.
 *
 * @author Ouharri Outman
 * @version 1.0
 */
public class DispatcherServletConfiguration extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    /**
     * Specify application classes with WEB configuration.
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfiguration.class};
    }

    /**
     * Specify URL patterns for the Dispatcher Servlet.
     *
     * @return An array of URL patterns.
     */
    @Override
    protected String @NotNull [] getServletMappings() {
        return new String[]{"/"};
    }
}