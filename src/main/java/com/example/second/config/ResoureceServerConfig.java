package com.example.second.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResoureceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "order";

//    public void configure(ResourceServerSecurityConfigurer resources) {
//        // 如果关闭 stateless，则 accessToken 使用时的 session id 会被记录，后续请求不携带 accessToken 也可以正常响应
//        resources.resourceId(RESOURCE_ID).stateless(true);
//    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/oauth/confirm_access").permitAll()
                .antMatchers("/**/*.js").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .and()
                .requestMatchers().antMatchers("/order/**").and().authorizeRequests().antMatchers("/order/**").authenticated();
    }
}

