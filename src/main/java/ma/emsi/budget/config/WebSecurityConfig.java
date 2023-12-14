package ma.emsi.budget.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.emsi.budget.auth.jwt.AuthTokenFilter;
import ma.emsi.budget.auth.oauth.CustomOAuth2User;
import ma.emsi.budget.auth.oauth.CustomOAuth2UserService;
import ma.emsi.budget.service.UtilisateurService;
import ma.emsi.budget.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

	@Autowired
	private CustomOAuth2UserService oauthUserService;

	@Autowired
	private UtilisateurService userService;

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(authorize -> authorize.requestMatchers("/", "/login", "/signup", "/oauth/**")
						.permitAll().anyRequest().authenticated())
				.formLogin(formLogin -> formLogin.loginPage("/login").permitAll())
				.oauth2Login(oauth2Login -> oauth2Login.loginPage("/login")
						.userInfoEndpoint(userInfo -> userInfo.userService(oauthUserService))
						.successHandler(authenticationSuccessHandler()));
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new AuthenticationSuccessHandler() {
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					org.springframework.security.core.Authentication authentication)
					throws IOException, ServletException {
				CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
				userService.processOAuthPostLogin(oauthUser.getEmail());
				response.sendRedirect("/list");
			}
		};
	}

}
