package admin.config;
import admin.security.JwtAuthenticationFilter;
import admin.security.JwtAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

/**
 * spring security 配置类主要进行一些安全相关的配置，如权限url匹配策略
 * 、认证器过滤配置、制定身份验证组件，开启权限认证注解等
 */
@Configuration
@EnableWebSecurity //开启spring security
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启权限注解，如@preAuthorize注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService; //通过userDetailsServic验证用户明密码和授权


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(new JwtAuthenticationProvider(userDetailsService));

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		禁用csrf,由于使用的是jwt，这里不需要csrf
		http.cors().and().csrf().disable().
				authorizeRequests()
//		跨域预检验请求
		.antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
//		web jars
		.antMatchers("/webjars/**").permitAll()
//	   查看sql监控（druid）
       .antMatchers("/druid/**").permitAll()
//			首页和登录页面
		.antMatchers("/").permitAll()
		.antMatchers("/login").permitAll()
//				swagger
		.antMatchers("/swagger-ui.html").permitAll()
		.antMatchers("/swagger-resources/**").permitAll()
		.antMatchers("/v2/api-docs").permitAll()
		.antMatchers("/webjars/springfox-swagger-ui/**").permitAll()
//		验证码
		.antMatchers("/captcha.jpg/**").permitAll()
//		服务监控
		.antMatchers("/actuator/**").permitAll()
//		其他所有请求需要身份验证
		.anyRequest().authenticated();
//		退出登录处理
		http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
//		token验证过滤器
		http.addFilterBefore(new JwtAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);

	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean(); //无法验证成功
		/*return  super.authenticationManagerBean();*/
	}

}
