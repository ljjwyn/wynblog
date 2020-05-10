package com.lijiajie.wynbolg.wynblog.config;
import com.lijiajie.wynbolg.wynblog.intercepors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {


    /**
     * 登录session key
     */
    public final static String SESSION_KEY = "user";


    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/Path/**").addResourceLocations("file:/home/jiajie/test/webljj/laopoImage/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(loginInterceptor);

        // 排除配置
//        addInterceptor.excludePathPatterns("/index**");
//        addInterceptor.excludePathPatterns("/login**");
//        addInterceptor.excludePathPatterns("/register**");
        // session过期会导致他出问题
//		addInterceptor.excludePathPatterns("/datasetuser**");
//		addInterceptor.excludePathPatterns("/labeldataset**");
//        addInterceptor.excludePathPatterns("/css/**","/dist/**","/assets/**","/js/**","/ng/**","/layout/**","/img/**","/bootstrap/**","/static/**","/mapper/**");

        // 拦截配置
        addInterceptor.addPathPatterns("/pages-**");
    }


}

