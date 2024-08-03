package sys.config;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter;


/* whenever we use @Configuration
 we set spring bean configuration
 in the class
 */
@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig {

    /*
    in here we use static to make it use for
    once and also you should know
    bcryptPasswordEncoder uses algorithm
    to hash the password
     */
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private UserDetailsService userDetailsService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
        http.csrf().and()
                .cors().disable()
                .authorizeHttpRequests((authorizeRequests) -> authorizeRequests.requestMatchers(HttpMethod.GET, "api/").hasRole("ADMIN", "USER"))
                .cors((cors) -> {

                });

                http.authorizeHttpRequests((authorizeRequests) -> {
//                    authorizeRequests.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
//                    authorizeRequests.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
//                    authorizeRequests.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
//                    authorizeRequests.requestMatchers(HttpMethod.PATCH, "/api/**"). hasAnyRole("ADMIN", "USER");
//                    authorizeRequests.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "USER");
//                    authorizeRequests.requestMatchers(HttpMethod.GET, "/api/**").permitAll();
                            authorizeRequests.requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll();
                            authorizeRequests.anyRequest().authenticated();
                        }
                ).httpBasic(Customizer.withDefaults())
                .headers((headers) -> headers.xssProtection(
                        xXssConfig -> xXssConfig.headerValue(XXssProtectionHeaderWriter
                                .HeaderValue
                                .ENABLED_MODE_BLOCK)
                ).contentSecurityPolicy(cps ->
                        cps.policyDirectives("script-src 'self'")));
        return http.build();
    }






    /* when we use database(DAO)
    authentication
    we comment out this code
     */
//    @Bean
//    public UserDetailsService userDetailsService(){
//
//        UserDetails user = User.builder()
//                .username("javad")
//                .password(passwordEncoder().encode("password"))
//                .roles("USER")
//                .build();
//
//
//        UserDetails admin = User.builder()
//                .username("milad")
//                .password(passwordEncoder().encode("password"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authentication) throws Exception {
        return authentication.getAuthenticationManager();
    }


    @Bean
    public AnonymousFilter anonymousFilter() {
        return new AnonymousFilter();
    }


}
