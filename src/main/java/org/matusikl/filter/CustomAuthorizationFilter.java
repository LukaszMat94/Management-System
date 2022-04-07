package org.matusikl.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.matusikl.errorresponse.ErrorResponse;
import org.matusikl.token.JWTTokenBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
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
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Component
@PropertySource("classpath:/application.properties")
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;
    private final Logger logger = LogManager.getLogger(CustomAuthorizationFilter.class);
    @Value("${spring.mvc.servlet.path}")
    private String servletPath;
    private List<String> excludeUrlPatterns = new ArrayList<>(Arrays.asList("/swagger-ui.html",
            "/swagger-ui/index.html",
            "/configuration/ui",
            "/configuration/security",
            "/v3/api-docs",
            "/v3/api-docs/swagger-config",
            "/swagger-ui/swagger-ui-standalone-preset.js",
            "/swagger-ui/swagger-ui.css",
            "/swagger-ui/swagger-ui-bundle.js",
            "/swagger-ui/favicon-32x32.png",
            "/swagger-ui/favicon-16x16.png",
            "/tokens/refresh"));

    @Autowired
    public CustomAuthorizationFilter(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.debug("In CustomAuthenticationFilter attemptAuthentication() method username: {}", request.getParameter("username"));
        if(request.getRequestURI().substring(request.getContextPath().length()).equals("/management-system/tokens")){
            filterChain.doFilter(request, response);
        }
        else{
            String authorization = request.getHeader(AUTHORIZATION);
            try{
                if(authorization.startsWith("Bearer ")){
                    logger.debug("User: {} authorization starts with Bearer syntax", request.getParameter("username"));
                    String token = authorization.substring("Bearer ".length());
                    Map<String, List<String>> decodedToken = JWTTokenBuilder.getDecodedAccessToken(token);

                    String username = "";
                    List<String> roles = new ArrayList<>();

                    for(Map.Entry<String, List<String>> list : decodedToken.entrySet()){
                        username = list.getKey();
                        roles = list.getValue();
                    }

                    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    logger.info("Successful authorized user: {} in CustomAuthorizationFilter doInternalFilter()", request.getParameter("username"));
                    filterChain.doFilter(request, response);
                }
                else{
                    logger.error("Error occured in in CustomAuthorizationFilter doInternalFilter() - Invalid schema token");
                    ErrorResponse errorResponse = new ErrorResponse(
                            "Incorrect schema for token!",
                            UNAUTHORIZED.value(),
                            ZonedDateTime.now());
                    objectMapper.writeValue(response.getOutputStream(), errorResponse);
                }

            }
            catch(NullPointerException exception){
                logger.error("NullPointerException in CustomAuthorizationFilter doInternalFilter() - Authorization is null");
                ErrorResponse errorResponse = new ErrorResponse(
                        "Authorization is null",
                        FORBIDDEN.value(),
                        ZonedDateTime.now());
                objectMapper.writeValue(response.getOutputStream(), errorResponse);
            }
            catch(Exception exception){
                logger.error("Exception occured in CustomAuthorizationFilter doInternalFilter() - exception:", exception);
                ErrorResponse errorResponse = new ErrorResponse(
                        exception.getMessage(),
                        FORBIDDEN.value(),
                        ZonedDateTime.now());
                objectMapper.writeValue(response.getOutputStream(), errorResponse);
            }
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI().substring(request.getContextPath().length());
        if(path.startsWith(servletPath + "/")){
            path = path.substring(servletPath.length());
            if (excludeUrlPatterns.contains(path)) {
                return true;
            } else {
                return false;
            }
        }
        else{
            return false;
        }
    }
}

