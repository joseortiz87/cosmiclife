package mx.com.cosmiclife.config;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;


@EnableWebMvc
@Configuration
public class WebConfig extends WebMvcAutoConfigurationAdapter {
		
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/");
        registry.addResourceHandler("/font/**").addResourceLocations("classpath:/static/fonts/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
	
	@Override
	public void configureMessageConverters(java.util.List<HttpMessageConverter<?>> converters) {
		// TODO Auto-generated method stub
	    // http
	    HttpMessageConverter<?> converter = new StringHttpMessageConverter();
	    converters.add(converter);

	    // string
	    converter = new FormHttpMessageConverter();
	    converters.add(converter);

	    // json
	    converter = new MappingJackson2HttpMessageConverter();
	    converters.add(converter);
	    
		super.configureMessageConverters(converters);
	}
	
    @Override
    public void configurePathMatch(PathMatchConfigurer matcher) {
        // turn off all suffix pattern matching
        matcher.setUseSuffixPatternMatch(false);
        // OR
        // turn on suffix pattern matching ONLY for suffixes
        // you explicitly register using
        // configureContentNegotiation(...)
        matcher.setUseRegisteredSuffixPatternMatch(true);
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    	// TODO Auto-generated method stub
    	configurer.favorPathExtension(false);
    	configurer.defaultContentType(MediaType.APPLICATION_JSON_UTF8);
    	/*
    	configurer.favorParameter(true);
    	Map<String, MediaType> mediaTypes = new HashMap<String, MediaType>();
    	mediaTypes.put("json", MediaType.APPLICATION_JSON);
    	mediaTypes.put("xml", MediaType.APPLICATION_XML);
    	configurer.mediaTypes(mediaTypes);
    	*/
    	super.configureContentNegotiation(configurer);
    }
    
}