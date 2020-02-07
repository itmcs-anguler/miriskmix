package com.itmcs.roo.mirisk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * = MiriskApplication
 *
 * TODO Auto-generated class documentation
 *
 */
@SpringBootApplication
public class MiriskApplication extends SpringBootServletInitializer{

	  private static Class<MiriskApplication> applicationClass = MiriskApplication.class;
	  
    /**
     * TODO Auto-generated method documentation
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(MiriskApplication.class, args);
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }
}