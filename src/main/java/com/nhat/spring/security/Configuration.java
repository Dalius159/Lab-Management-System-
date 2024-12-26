
package com.nhat.spring.security;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@org.springframework.context.annotation.Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class Configuration {
    
    private final UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().and()
                .authorizeHttpRequests((authorize) ->
                        authorize
                                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                                .requestMatchers("/" , "login" , "/save" , "/register/**","TEST").permitAll()
                                
                                
                                //Home
                                .requestMatchers("/Home").hasAnyAuthority("ADMIN" , "VISITOR", "STAFF")
                                
                                 //MemberS
                                .requestMatchers("/member").hasAnyAuthority("ADMIN","VISITOR","STAFF")
                                .requestMatchers("/adminM/**" ).hasAuthority("ADMIN")
                                
                                //ProjectS
                                .requestMatchers("/project/**").hasAnyAuthority("ADMIN", "STAFF")
                                .requestMatchers("/adminP/**").hasAuthority("ADMIN" ) 
                        
                                 //Publication
                                .requestMatchers("/publication/**").hasAnyAuthority("ADMIN","VISITOR","STAFF")
                                .requestMatchers("/adminPub/**").hasAuthority("ADMIN" ) 
                                .requestMatchers("/addPublication/**").hasAnyAuthority("ADMIN")
                                
                                 //Resource
                                .requestMatchers("/resource").hasAnyAuthority("ADMIN", "STAFF")
                                .requestMatchers("/adminRes/**").hasAuthority("ADMIN")
                                
                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/Home")
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }
}
