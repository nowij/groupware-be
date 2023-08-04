//package com.nowij.groupware.security;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.catalina.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Slf4j
//public class JWTAuthenticationFilter extends OncePerRequestFilter {
//    @Autowired
//    private JWTGenerator jwtGenerator;
//    @Autowired
//    private JWTUserDetailsService service;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String token = getHWTFromRequest(request);
//        log.debug("token >>> ", token);
//
//        // token 유효성 검사
//        if (StringUtils.hasText(token) && jwtGenerator.validateToken(token)) {
//            String id = jwtGenerator.getEmployeeIdFromJWT(token); // token에 들어있는 id 추출
//
//            UserDetails userDetails = service.loadUserByUsername(id);
//            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                    userDetails, null, userDetails.getAuthorities());
//            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        }
//        filterChain.doFilter(request, response);
//    }
//
//    private String getHWTFromRequest(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
//            // 헤더에 들어가있는 token 검증
//            return bearerToken.substring(7, bearerToken.length());
//        }
//        return null;
//    }
//}
