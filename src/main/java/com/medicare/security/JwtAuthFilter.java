package com.medicare.security;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
  private static final String SECURITY_HEADER = "Authorization";
  private static final String SECURITY_PREFIX = "Bearer ";
  private final JwtUtils jwtUtils;
  private final MedicareUserDetailsService medicareUserDetailsService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
    String bearerToken = request.getHeader(SECURITY_HEADER);
    if (!(bearerToken != null && bearerToken.startsWith(SECURITY_PREFIX))) {
      filterChain.doFilter(request, response);
      return;
    }
    String token = bearerToken.replace(SECURITY_PREFIX, "");
    try {
      String userName = jwtUtils.extractUsername(token);
      if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        MedicareUserDetails userDetails = medicareUserDetailsService.loadUserByUsername(userName);
        if (jwtUtils.validateToken(token, userDetails)) {
          UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
              null, userDetails.getAuthorities());
          authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authToken);
        }
      }
    } catch (ExpiredJwtException ex) {
      filterChain.doFilter(request, response);
      return;
    }
    filterChain.doFilter(request, response);
  }
}
