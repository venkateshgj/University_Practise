package com.springboot.University.Config;

import com.springboot.University.Util.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Value("${university.admin.username}")
    private String adminUsername;

    @Value("${university.admin.password}")
    private String adminPassword;

    @Value("${university.admin.role}")
    private String adminRole;

    @Value("${university.student.username}")
    private String studentUsername;

    @Value("${university.student.password}")
    private String studentPassword;

    @Value("${university.student.role}")
    private String studentRole;

    @Value("${university.user.username}")
    private String userUsername;

    @Value("${university.user.password}")
    private String userPassword;

    @Value("${university.user.role}")
    private String userRole;

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
            .requestMatchers("/auth/login").permitAll()
            .requestMatchers(HttpMethod.GET, "/university/api/v1/course/all").hasAnyRole("ADMIN", "STUDENT", "USER")
            .requestMatchers(HttpMethod.GET, "/university/api/v1/course/{id}").hasAnyRole( "ADMIN","STUDENT")
            .requestMatchers( "/university/api/v1/course/**").hasRole("ADMIN")

            .requestMatchers(HttpMethod.GET, "/university/api/v1/professors/**").hasAnyRole("STUDENT")
            .requestMatchers( "/university/api/v1/professors/**").hasRole("ADMIN")

//            .requestMatchers( "/university/api/v1/students/**").hasRole("ADMIN")
            .anyRequest().authenticated());

        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }


    //            .requestMatchers("/university/api/v1/course/all").permitAll()
    // 1. In memory User Details Manager
    // 2. Db table is going to store user creds

    // OAuth 2 - This is used to protect application from app 2 app calls.

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(PasswordEncoder passwordEncoder){

        UserDetails admin = User.withUsername(adminUsername)
                .password(passwordEncoder.encode(adminPassword))
                .roles(adminRole)
                .build();

        UserDetails student = User.withUsername(studentUsername)
                .password(passwordEncoder.encode(studentPassword))
                .roles(studentRole)
                .build();

        UserDetails user =  User.withUsername(userUsername)
                .password(passwordEncoder.encode(userPassword))
                .roles(userRole)
                .build();

        return new InMemoryUserDetailsManager(admin,student,user);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



//    Roles - Admin, User, Student
}
