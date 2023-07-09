package cat.itacademy.barcelonactiva.mohamedAli.Bilal.s05.t02.n01.S05T02N01mohamedAliBilal;

import cat.itacademy.barcelonactiva.mohamedAli.Bilal.s05.t02.n01.S05T02N01mohamedAliBilal.security.JWTAuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableWebSecurity
public class S05T02N01mohamedAliBilalApplication {

	public static void main(String[] args) {
		SpringApplication.run(S05T02N01mohamedAliBilalApplication.class, args);
	}

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {


			http
					.cors().and()
					.csrf().disable()
					.authorizeRequests()
					.antMatchers("/user").permitAll()
					.anyRequest().authenticated()
					.and()
					.addFilterAfter(new JWTAuthorizationFilter(),
							UsernamePasswordAuthenticationFilter.class);

		}
	}
}