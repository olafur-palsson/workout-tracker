package server.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserAuthenticationProvider authProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("BLABLABALBALBAABLA");
        auth.authenticationProvider(authProvider);
        auth.inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf().disable()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "**").permitAll()
                .antMatchers(HttpMethod.GET, "/database/newUser**").permitAll()
                .antMatchers(HttpMethod.GET, "/database/userEnabled/newUser/**").permitAll()
                .antMatchers(HttpMethod.GET, "/database/userEnabled/**").authenticated()
                .anyRequest().hasRole("ADMIN");

    }

}
