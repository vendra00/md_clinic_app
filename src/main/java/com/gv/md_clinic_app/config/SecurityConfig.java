package com.gv.md_clinic_app.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security config.
 */
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Security filter chain.
     * @param http http security.
     * @return security filter chain.
     * @throws Exception exception.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(@NotNull HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/doctor/**").hasRole("DOCTOR")
                        .requestMatchers("/patient/**").hasRole("PATIENT")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login").permitAll()
                        .defaultSuccessUrl("/home", true)
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                );

        return http.build();
    }

    /**
     * Configure global.
     * @param auth authentication manager builder.
     * @param passwordEncoder password encoder.
     * @throws Exception exception.
     */
    @Autowired
    public void configureGlobal(@NotNull AuthenticationManagerBuilder auth, @NotNull PasswordEncoder passwordEncoder) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder.encode("adminPass"))
                .roles("ADMIN")
                .and()
                .withUser("doctor")
                .password(passwordEncoder.encode("doctorPass"))
                .roles("DOCTOR")
                .and()
                .withUser("patient")
                .password(passwordEncoder.encode("patientPass"))
                .roles("PATIENT");
    }

    /**
     * Password encoder.
     * @return password encoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}

}





