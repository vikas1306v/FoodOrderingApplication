package com.food.Filter;

import com.food.Exception.TokenNotGiven;
import com.food.Utils.JwtService;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class RequestFilter extends OncePerRequestFilter {
    private final JwtService  jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request,
                                    @Nonnull HttpServletResponse response,
                                    @Nonnull FilterChain filterChain) throws ServletException, IOException
    {
        if(request.getRequestURI().split("/")[3].equals("auth"))
        {
            filterChain.doFilter(request,response);
            return;
        }
        String token=request.getHeader("Authorization");
        if(token==null)
        {
            filterChain.doFilter(request,response);
            throw new TokenNotGiven("Token not given");

        }
        if(token.startsWith("Bearer "))
        {
            token=token.substring(7);

        }else{
            filterChain.doFilter(request,response);
            return ;
        }
        String email=jwtService.extractUsername(token);
        if(SecurityContextHolder.getContext().getAuthentication()==null && email!=null)
        {

            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            if(jwtService.isTokenValid(token,email)) {

                UsernamePasswordAuthenticationToken upassToken = new UsernamePasswordAuthenticationToken(email, null, userDetails.getAuthorities());
                upassToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(upassToken);

            }
        }

        filterChain.doFilter(request, response);

    }
}
