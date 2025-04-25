package com.projeto.finance_manager.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    // Filtro executado a cada requisição para validar e autenticar o token JWT
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Recupera o token do cabeçalho Authorization
        String token = request.getHeader("Authorization");

        // Verifica se o token está presente e com o prefixo "Bearer "
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Remove o "Bearer "
            String username = jwtUtil.extractUsername(token); // Extrai o usuário do token

            // Verifica se o usuário está autenticado no contexto atual
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // Valida o token com base no usuário extraído
                if (jwtUtil.validateToken(token, userDetails.getUsername())) {
                    // Cria a autenticação do usuário
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    // Adiciona os detalhes da requisição (IP, headers, etc)
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Define o usuário autenticado no contexto do Spring Security
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        // Continua a cadeia de filtros normalmente
        filterChain.doFilter(request, response);
    }
}