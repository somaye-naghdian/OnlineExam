package ir.maktab.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityContext extends WebSecurityConfigurerAdapter {

    private static final String AUTHORITY_QUERY = "SELECT email, password FROM `user` WHERE `email` = ?";

    @Autowired
    DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/register").permitAll()
                .anyRequest().authenticated()
                .and().requiresChannel().anyRequest().requiresSecure()
                .and()
                .formLogin().and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("test").password("{noop}test").roles("USER");
        auth.jdbcAuthentication().dataSource(dataSource).authoritiesByUsernameQuery(AUTHORITY_QUERY);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}
