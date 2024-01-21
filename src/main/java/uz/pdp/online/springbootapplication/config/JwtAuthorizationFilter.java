package uz.pdp.online.springbootapplication.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.Nullable;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JwtConfig jwtConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @Nullable HttpServletResponse response, @Nullable FilterChain chain)
            throws ServletException, IOException {
        // Extract the JWT token from the request header
        String header = request.getHeader(jwtConfig.getHeader());

        if (header != null && header.startsWith(jwtConfig.getPrefix())) {
            String token = header.replace(jwtConfig.getPrefix(), "");

            try {
                // Validate the token and extract user information
                Claims claims = Jwts.parser()
                        .setSigningKey(jwtConfig.getSecret())
                        .parseClaimsJws(token)
                        .getBody();

                String username = claims.getSubject();

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    // Create an Authentication object and set it in the SecurityContext
                    if (isValidToken(claims)) {
                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
            } catch (Exception e) {
                // Token validation failed
                log.error("Token validation failed: {}", e.getMessage());
            }
        }

        Objects.requireNonNull(chain).doFilter(request, response);
    }

    private boolean isValidToken(Claims claims) {
        // Check token expiration
        Date expirationDate = claims.getExpiration();
        if (expirationDate != null && expirationDate.before(new Date())) {
            return false; // Token has expired
        }

        // Check if the token was issued in the future (clock skew)
        Date issuedAt = claims.getIssuedAt();
        if (issuedAt != null && issuedAt.after(new Date())) {
            return false; // Token was issued in the future
        }

        // Check if the token is not before the not-before time
        Date notBefore = claims.getNotBefore();
        if (notBefore != null && notBefore.after(new Date())) {
            return false; // Token is not yet valid
        }

        // Check if the token issuer is as expected
        String issuer = claims.getIssuer();
        return issuer == null || issuer.equals("spring-security");
    }

}
