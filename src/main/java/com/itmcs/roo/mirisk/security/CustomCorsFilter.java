package com.itmcs.roo.mirisk.security;

import java.util.Arrays;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Component
public class CustomCorsFilter {

    private CorsConfiguration config;
    private UrlBasedCorsConfigurationSource urlBasedConfigSource;
    private CorsFilter corsFilter;

    public CustomCorsFilter() {
    	System.out.println("\n\nInitializing CustomCorsFilter ...\n\n");
        config = new CorsConfiguration();
       // config.applyPermitDefaultValues();

        config.setAllowCredentials(true);
        config.setAllowedOrigins(Arrays.asList("*"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("*"));
        config.setExposedHeaders(Arrays.asList("content-length", "token"));
        config.setMaxAge(3600L);

        urlBasedConfigSource = new UrlBasedCorsConfigurationSource();
        urlBasedConfigSource.registerCorsConfiguration("/**", config);

        corsFilter = new CorsFilter(urlBasedConfigSource);
    }

	public CorsConfiguration getConfig() {
		return config;
	}

	public void setConfig(CorsConfiguration config) {
		this.config = config;
	}

	public UrlBasedCorsConfigurationSource getUrlBasedConfigSource() {
		return urlBasedConfigSource;
	}

	public void setUrlBasedConfigSource(UrlBasedCorsConfigurationSource urlBasedConfigSource) {
		this.urlBasedConfigSource = urlBasedConfigSource;
	}

	public CorsFilter getCorsFilter() {
		return corsFilter;
	}

	public void setCorsFilter(CorsFilter corsFilter) {
		this.corsFilter = corsFilter;
	}
    
    
}
