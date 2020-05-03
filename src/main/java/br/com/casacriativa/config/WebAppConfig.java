package br.com.casacriativa.config;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.google.common.cache.CacheBuilder;

import br.com.casacriativa.control.IdeaController;
import br.com.casacriativa.dao.IdeaDAO;

@EnableWebMvc
@EnableCaching
@ComponentScan(basePackageClasses = {IdeaController.class, IdeaDAO.class})
public class WebAppConfig implements WebMvcConfigurer {

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		
		return resolver;
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
    @Bean
    public CacheManager cacheManager() {
    	GuavaCacheManager manager = new GuavaCacheManager();
    	manager.setCacheBuilder(CacheBuilder
    				.newBuilder()
    				.maximumSize(100)
    				.expireAfterAccess(2, TimeUnit.HOURS));
    	return new ConcurrentMapCacheManager();
    }
    
}
