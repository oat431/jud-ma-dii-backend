package panomete.judsue.config;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import panomete.judsue.security.entity.Roles;
import panomete.judsue.security.repository.AuthRepository;
import panomete.judsue.utility.JwtEntryPoint;
import panomete.judsue.utility.JwtFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    final JwtEntryPoint jwtEntryPoint;
    final JwtFilter jwtFilter;
    final AuthRepository authRepository;

    private static final String[] FREE_AREA = {
            "/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/webjars/**",
            "/favicon.ico",
            "/webjars/**",
            "/api/v1/auth/",
            "/api/v1/auth/signup",
    };

    private static final String[] ACCOUNT_WHITELIST = {
            "/api/v1/auth/details",
            "/api/v1/auth/credentials",
            "/api/v1/auth/password",
            "/api/v1/auth/email",
            "/api/v1/auth/username",
            "/api/v1/auth/forgetpassword",
            "/api/v1/auth/refresh",
            "/api/v1/item/**",
            "/api/v1/bill/**"
    };

    private static final String[] ADMIN_WHITELIST = {
            "/api/v1/admin/**"
    };

    private static final String[] USER_WHITELIST = {
            "/api/v1/user/**"
    };

    private static final String[] REQUESTER_WHITELIST = {
            "/api/v1/requester/**"
    };

    private static final String[] PURCHASER_WHITELIST = {
            "/api/v1/purchaser/**"
    };

    private static final String[] APPROVER_WHITELIST = {
            "/api/v1/approver/**"
    };

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable).cors().and()
                .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtEntryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request -> request
                        .requestMatchers(FREE_AREA).permitAll()
                        .requestMatchers(ACCOUNT_WHITELIST).hasAnyAuthority(
                                Roles.ROLE_ADMIN.name(),
                                Roles.ROLE_USER.name(),
                                Roles.ROLE_REQUESTER.name(),
                                Roles.ROLE_PURCHASER.name(),
                                Roles.ROLE_APPROVER.name()
                        )
                        .requestMatchers(ADMIN_WHITELIST).hasAuthority(Roles.ROLE_ADMIN.name())
                        .requestMatchers(USER_WHITELIST).hasAnyAuthority(Roles.ROLE_USER.name())
                        .requestMatchers(REQUESTER_WHITELIST).hasAuthority(Roles.ROLE_REQUESTER.name())
                        .requestMatchers(PURCHASER_WHITELIST).hasAuthority(Roles.ROLE_PURCHASER.name())
                        .requestMatchers(APPROVER_WHITELIST).hasAuthority(Roles.ROLE_APPROVER.name())
                        .anyRequest().authenticated()
                );
        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @SneakyThrows
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return authRepository::findByUsername;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer configure() {
        return web -> web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
    }

    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        DefaultHttpFirewall firewall = new DefaultHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        return firewall;
    }
}
