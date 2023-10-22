package com.example.jwttestapp.config;



import com.example.jwttestapp.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
    //private final JwtTokenUtils jwtTokenUtils;
    @Value("${jwt.url_validate}")
    private String url;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        UserDto user_dto = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                HttpRequest httpRequest = HttpRequest.newBuilder()
                        .header("Content-Type", "text/plain")
                        .uri(URI.create(url))
                        .method("GET", HttpRequest.BodyPublishers.ofString(token))
                        .build();
                HttpClient client = HttpClient.newHttpClient();
                HttpResponse<String> jwt_response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

                System.out.println(jwt_response.statusCode());

                String body_str = jwt_response.body().toString();
                System.out.println(body_str);

                ObjectMapper objectMapper = new ObjectMapper();
                user_dto = objectMapper.readValue(body_str, UserDto.class);

            } catch (Exception ex) {
                log.debug("Auth error: " + ex.getMessage());
            }
        }
        if (user_dto != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    user_dto.getName(),
                    null,
                    user_dto.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
            );
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        filterChain.doFilter(request, response);
    }
}
