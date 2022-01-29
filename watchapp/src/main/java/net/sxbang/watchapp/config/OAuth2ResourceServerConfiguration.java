package net.sxbang.watchapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("watch").stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // /open/**
        // /api/**
        http.formLogin()        // /login
                .successForwardUrl("/api/user/success")
                .and()
                .authorizeRequests()
                .antMatchers("/open/**", "/", "/*", "/oauth/*").permitAll() // permitAll 不登录权限
                .antMatchers("/api/**").authenticated()
//                    .antMatchers("/admin/**").hasRole("ADMIN")
            .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler)
//////            .and()
//////                .csrf().disable();
        ;
        // ** -> /open/user/list/shop/
        // * ->  /user  /user/38
        ;
    }
}
