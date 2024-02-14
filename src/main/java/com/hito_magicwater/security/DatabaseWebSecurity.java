package com.hito_magicwater.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


import javax.sql.DataSource;

@Configuration
public class DatabaseWebSecurity  {



    @Bean
    public UserDetailsManager users(DataSource dataSource) {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.setUsersByUsernameQuery("select nif, password, activo from usuario where nif=?");
        users.setAuthoritiesByUsernameQuery("SELECT nif, permiso FROM usuario WHERE nif=?");
        return users;
    }

    // Filtros por URL.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/css/**").permitAll()
                .requestMatchers("/imagenes/**").permitAll()
                .requestMatchers("/", "/productos", "/login", "/signup").permitAll()
                .requestMatchers("/carrito").hasAuthority("trabajador")
                .requestMatchers("/categorias").hasAuthority("supervisor")
                .requestMatchers("/pedido").hasAuthority("supervisor")
                .requestMatchers("/nuevo").hasAuthority("supervisor")
                .anyRequest().authenticated());
        http.formLogin(formLogin -> formLogin.loginPage("/login").permitAll());
        http.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/").permitAll());
        http.exceptionHandling((exception)-> exception.accessDeniedPage("/denegado"));

        return http.build();
    }
}

