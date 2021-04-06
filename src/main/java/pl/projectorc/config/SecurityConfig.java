package pl.projectorc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    No logging required.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> authorize
                        .antMatchers("/h2-console/**").permitAll() //do not use in  production
                        .antMatchers("/", "/home", "/scripts/**", "/styles/**", "/css/**").permitAll()
                )
                .authorizeRequests(requests -> ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl) requests.anyRequest()).authenticated());
        http.formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/login")
                .permitAll()
                .successForwardUrl("/")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error");

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                .logoutSuccessUrl("/")
                .permitAll();
        http.httpBasic();

        //h2 console -- config do not use in  production
        http.headers().frameOptions().sameOrigin();
        http.csrf().disable();
    }
}
