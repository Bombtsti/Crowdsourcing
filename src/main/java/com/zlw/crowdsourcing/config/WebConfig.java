package com.zlw.crowdsourcing.config;

import com.zlw.crowdsourcing.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());

        registration.addPathPatterns("/**"); //所有路径都被拦截
        registration.excludePathPatterns( //添加不拦截路径
                "/index",
                "/toIndex",
                "/toLogin",
                "/user/login",
                "/user/register",
                "/worker/toIndex",
                "/**/*.html",
                "/**/*.js",
                "/**/*.css",
                "/**/*.png",
                "/**/*.jpg",
                "/**/*.woff"
        );
    }
}
