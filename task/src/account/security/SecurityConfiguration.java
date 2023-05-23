package account.security;

import account.exceptions.error.CustomAccessDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfiguration(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {



        http .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                .and()
                .httpBasic()
                .and()
                .csrf().disable().headers().frameOptions().disable() // for Postman, the H2 console
                .and()
                .authorizeRequests() // manage access
                .antMatchers("/h2/**").permitAll()
                .antMatchers("/actuator/shutdown").permitAll()
                .antMatchers(HttpMethod.POST, "/api/auth/signup/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/empl/payment/**", "/api/empl/payment").hasAnyRole("USER", "ACCOUNTANT")
                .antMatchers(HttpMethod.POST, "/api/auth/changepass/**").hasAnyRole("ADMINISTRATOR", "USER", "ACCOUNTANT")
                .antMatchers(HttpMethod.POST, "/api/acct/payments/**", "/api/acct/payments").hasRole("ACCOUNTANT")
                .antMatchers(HttpMethod.PUT, "/api/acct/payments/**").hasRole("ACCOUNTANT")
                .antMatchers(HttpMethod.GET, "/api/admin/user/**").hasRole("ADMINISTRATOR")
                .antMatchers(HttpMethod.DELETE, "/api/admin/user/**").hasRole("ADMINISTRATOR")
                .antMatchers(HttpMethod.PUT, "/api/admin/user/role/**").hasRole("ADMINISTRATOR")
                .anyRequest().authenticated()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ;

        return http.build();



    }
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }


    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(13);
    }
}
