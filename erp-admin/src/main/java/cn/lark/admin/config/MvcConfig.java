package cn.lark.admin.config;

import cn.lark.admin.interceptors.NoLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 配置类，使拦截器生效
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public NoLoginInterceptor noLoginInterceptor(){
        return new NoLoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //设置哪些路径拦截，哪些不拦截
        registry.addInterceptor(noLoginInterceptor())
                .addPathPatterns("/**") //拦截
                .excludePathPatterns("/index","/user/login", //这些不拦截
                        "/css/**","/error/**","/images/**","/js/**","/lib/**");
    }
}
