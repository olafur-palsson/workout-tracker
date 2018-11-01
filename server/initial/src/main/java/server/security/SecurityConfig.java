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

        httpSecurity.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "**").permitAll()
                .antMatchers("/database/newUser/**").permitAll()
                .antMatchers("/database/userEnabled/newUser/**").permitAll()
                .antMatchers("/database/userEnabled/**") .authenticated()
                .anyRequest().hasRole("ADMIN")
                .and()
                .httpBasic()
                .and()
                .csrf().disable();


        //.addFilterBefore(customFilter(), BasicAuthenticationFilter.class)     dno hvad thetta gerir, don't ask
        //.fullyAuthenticated()     dno hvad thetta gerir, don't ask
        //.antMatchers("**/rest/*")     dno hvad thetta gerir, don't ask





    }

}
