package com.example.starters.comm.config;

import com.example.starters.comm.oauth.OAuth2UserCustomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final OAuth2UserCustomService oAuth2UserCustomService;
    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http
    ) throws Exception {
        String loginPage = "/login";


        http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headerConfig -> headerConfig
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::deny)
                        .xssProtection(xssProtection -> xssProtection.headerValue(XXssProtectionHeaderWriter.HeaderValue.ENABLED_MODE_BLOCK))
                )
                .authorizeHttpRequests(request -> request
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/signup/**","/sign/**","/lib/**", "/oauth2/**", "/login/oauth2/**").permitAll()
//                        .requestMatchers("/h2-console").authenticated()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth -> oauth
                        .loginPage(loginPage)
//                        .redirectionEndpoint(redirectionEndpointConfig -> redirectionEndpointConfig
//                                .baseUri("/login/oauth2/callback/**")
//                        )
                        .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
                                .userService(oAuth2UserCustomService)
                        )
                )
                .formLogin(formLogin -> formLogin
                        .loginPage(loginPage).permitAll()
                        .loginProcessingUrl("/login/pro").permitAll()
                        .defaultSuccessUrl("/", true)
                )
                .logout(logoutConfig -> logoutConfig
                        .logoutUrl("/logout")
                        .logoutSuccessUrl(loginPage)
                );

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
