package me.ftahmed.skel.config;

import me.ftahmed.skel.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Value("${securityDebug:false}")
    private Boolean securityDebug;

    @Autowired
    private CustomLoginSuccessHandler successHandler;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(t -> t
            .requestMatchers("/", "/login", "/register", "/permission").permitAll()
            .requestMatchers("/images/**", "/css/**", "/js/**", "/webjars/**", "/favicon.ico").permitAll()
            .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
            .requestMatchers("/account/**").hasAnyAuthority("USER")
            .anyRequest().authenticated());
        
        http.formLogin(t -> t
            .loginPage("/login")
            .failureUrl("/login?error=true")
            .successHandler(successHandler)
            .usernameParameter("email")
            .passwordParameter("password"));

        http.logout(t -> t
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/"));

        http.exceptionHandling(t -> t
            .accessDeniedPage("/access-denied"));

        http.authenticationProvider(authenticationProvider());
        http.headers(t -> t.frameOptions(u -> u.sameOrigin()));

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.debug(securityDebug);
    }

}
