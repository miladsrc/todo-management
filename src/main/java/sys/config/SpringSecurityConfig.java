package sys.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.authorization.AuthorityReactiveAuthorizationManager.hasAnyRole;

/* whenever we use @Configuration
 we set spring bean configuration
 in the class
 */
@Configuration
@EnableMethodSecurity
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

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeRequests) -> {
//                    authorizeRequests.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
//                    authorizeRequests.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
//                    authorizeRequests.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
//                    authorizeRequests.requestMatchers(HttpMethod.PATCH, "/api/**"). hasAnyRole("ADMIN", "USER");
//                    authorizeRequests.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "USER");
//                    authorizeRequests.requestMatchers(HttpMethod.GET, "/api/**").permitAll();
                    authorizeRequests.anyRequest().authenticated();
                        }
                ).httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails user = User.builder()
                .username("javad")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();


        UserDetails admin = User.builder()
                .username("milad")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

}
