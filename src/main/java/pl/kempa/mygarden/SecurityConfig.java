package pl.kempa.mygarden;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import pl.kempa.mygarden.services.MyUserDetailsService;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		@Qualifier("userDetailsService")
		MyUserDetailsService userDetailsService;


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		//.antMatchers("/home","/register").permitAll()
		.antMatchers("/main","/signup2").access("hasRole('default')")
		.and()
		.formLogin()
		.loginPage("/signin2")
		.failureUrl("/signin2?error=true");
		
	}
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
