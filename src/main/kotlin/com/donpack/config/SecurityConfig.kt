package com.donpack.config

import com.donpack.service.TokenService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


/**
 * This class sets all security related configuration.
 */
@Configuration
@EnableWebSecurity
class SecurityConfig (
    private val tokenService: TokenService,
) {

    //https://docs.spring.io/spring-security/reference/servlet/exploits/headers.html

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        // Define public and private routes
        http.authorizeHttpRequests{authorize -> authorize
                .requestMatchers(HttpMethod.POST, "/api/public/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/public/register").permitAll()
                .requestMatchers("/api/private/**").authenticated()
                .anyRequest().permitAll()
        }


        // Configure JWT
/*
        http.oauth2ResourceServer {
            it.jwt{}
        }
*/
        http.oauth2ResourceServer { oauth2ResourceServer -> oauth2ResourceServer.jwt(withDefaults()) }

        http.authenticationManager { auth ->
            val jwt = auth as BearerTokenAuthenticationToken
            val user = tokenService.parseToken(jwt.token) ?: throw InvalidBearerTokenException("Invalid token")
            UsernamePasswordAuthenticationToken(user, "", listOf(SimpleGrantedAuthority("USER")))
        }

        // Other configuration
        //in necesary
        http.cors {}

        http.sessionManagement {
            it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }
        //If our stateless API uses token-based authentication, such as JWT, we don't need CSRF protection, and we must disable it as we saw earlier.
        http.csrf{ it.disable() }

        http.headers{ head ->
            head.frameOptions{option ->
                option.disable()
            }
        }
        http.headers{ pro ->
            pro.xssProtection{
                it.equals("script-src 'self'")
            }
        }

        return http.build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        // allow localhost for dev purposes
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("http://localhost:3000", "http://localhost:8080", "null")
        configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE")
        configuration.allowedHeaders = listOf("authorization", "content-type")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}
