package rts.ch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import rts.ch.filter.CORSFilter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * The web-app initializer where mapping and config are setup.(as made in web.xml)
 */
@Configuration
public class RestWebAppInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext rootCtx = new AnnotationConfigWebApplicationContext();

        servletContext.addListener(new ContextLoaderListener(rootCtx));
        servletContext.addFilter("corsFilter", new CORSFilter()).addMappingForUrlPatterns(null, false, "/*");

        AnnotationConfigWebApplicationContext webCtx = new AnnotationConfigWebApplicationContext();
        webCtx.register(SpringMVCConfig.class);

        ServletRegistration.Dynamic reg = servletContext.addServlet("rest", new DispatcherServlet(webCtx));
        reg.setLoadOnStartup(1);
        reg.addMapping("/");
    }

}
