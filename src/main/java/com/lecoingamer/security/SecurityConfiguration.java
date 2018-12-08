package com.lecoingamer.security;



import com.lecoingamer.services.Implementation.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private DataSource dataSource;

    @Bean
    public MySecurityEventListener MySecurityEventListener(){
        return new MySecurityEventListener();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {



        http
                //.anonymous().and()
                .authorizeRequests()
                .antMatchers("/","/index","/login", "/logout").permitAll()
                .antMatchers("/webjars/**").permitAll()
                //.antMatchers("/ressources/**").permitAll().anyRequest().permitAll()
                .antMatchers("/user/").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/roles").permitAll()
                .antMatchers("/role").permitAll()
                .antMatchers("/role/**").permitAll()
                .antMatchers("/usersPage").hasAuthority("ROLE_USER")
                .antMatchers("/adminPage").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated().and().csrf().disable().formLogin()
                .and().exceptionHandling().accessDeniedPage("/403")
                .and().formLogin()//
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/login")//
                .defaultSuccessUrl("/index",true)//
                .failureUrl("/login?error=true")
                .usernameParameter("name")//
                .passwordParameter("password")
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful")
                .and().exceptionHandling()
                ;
        http.authorizeRequests().antMatchers("/resources/**").permitAll().anyRequest().permitAll();
    }
    /*@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/ressources/**","/webjars/**");//,"/","/index");
    }
*/

    /*@Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }*/
}
