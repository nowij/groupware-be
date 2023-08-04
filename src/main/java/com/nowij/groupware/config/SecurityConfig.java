//package com.nowij.groupware.config;
//
//import com.nowij.groupware.security.JWTAuthEntryPoint;
//import com.nowij.groupware.security.JWTAuthenticationFilter;
//import com.nowij.groupware.security.JWTUserDetailsService;
//import com.nowij.groupware.security.UserAccessDeniedHandler;
//import jakarta.servlet.DispatcherType;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
//import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
//import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class SecurityConfig {
//    private JWTUserDetailsService service;
//    private JWTAuthEntryPoint authEntryPoint;
//    public SecurityConfig(JWTUserDetailsService service, JWTAuthEntryPoint authEntryPoint) {
//        this.service = service;
//        this.authEntryPoint = authEntryPoint;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//        return configuration.getAuthenticationManager();
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .cors(Customizer.withDefaults())
//                .csrf(CsrfConfigurer::disable)
//                .exceptionHandling(authenticationManager -> authenticationManager
//                        .authenticationEntryPoint(authEntryPoint)
//                        .accessDeniedHandler(accessDeniedHandler()))
//                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeRequests()
//                .requestMatchers("/auth/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic(HttpBasicConfigurer::disable);
//        return http.build();
//    }
//
//    @Bean
//    public JWTAuthenticationFilter jwtAuthenticationFilter() {
//        return new JWTAuthenticationFilter();
//    }
//
//    @Bean
//    public AccessDeniedHandler accessDeniedHandler() {
//        return new UserAccessDeniedHandler();
//    }
//}
