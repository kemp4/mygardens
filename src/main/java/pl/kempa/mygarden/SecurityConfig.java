package pl.kempa.mygarden;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import pl.kempa.mygarden.services.MyUserDetailsService;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
      http
        .httpBasic()
      .and()
        .authorizeRequests()
          .antMatchers("/index.html", "/home.html", "/login.html", "/","/register.html","/ws/register").permitAll()
          .anyRequest().authenticated()
          .and()
          .csrf()
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
      ;
    }
  
	
	
		@Autowired
		@Qualifier("userDetailsService")
		MyUserDetailsService userDetailsService;
//
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//		.csrf().disable()
//		.authorizeRequests()
//		//.antMatchers("/home","/register").permitAll()
//		.antMatchers("/main","/ranking2").access("hasRole('default')")
//		.and()
//		.formLogin()
//		.loginPage("/signin2")
//		.failureUrl("/signin2?error=true");
		
//	}
//	@Override
//	protected void configure(
//			AuthenticationManagerBuilder auth) throws Exception {
//		auth
//		.userDetailsService(new UserDetailsService() {
//			@Override
//			public UserDetails loadUserByUsername(String username)
//					throws UsernameNotFoundException {
//				return playerRepository.findOne(username);
//			}
//		});
//	}
	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    	auth.userDetailsService(userDetailsService);
	    }
	    
//		@Bean
//		public PasswordEncoder passwordEncoder(){
//			PasswordEncoder encoder = new BCryptPasswordEncoder();
//			return encoder;
//		}
	    
}
