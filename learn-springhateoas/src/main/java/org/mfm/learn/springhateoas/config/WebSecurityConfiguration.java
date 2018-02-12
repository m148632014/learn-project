package org.mfm.learn.springhateoas.config;

import org.mfm.learn.springhateoas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * GlobalAuthenticationConfigurerAdapter可以用来配置 一个SecurityConfigurer
 * 可以暴露为一个bean来配置全局AuthenticationManagerBuilder。
 * 这种类型的Bean由AuthenticationConfiguration自动使用以配置全局AuthenticationManagerBuilder。
 *
 * @author MFM
 *
 */
@Configuration
public class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {
	@Autowired
	private UserRepository userRepository;

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDetailsService());
	}

	/**
	 * 1.根据用户名获取一个用户user，注意此处的User为系统自己定义的model
	 * (username)->this.userRepository.findByUsername(username)
	 * <p>
	 * 2.创建一个用户角色，用于管理用户权限，此处的用户User为Spring Security的model
	 * <p>
	 * 3.如果用户找不到抛出异常，此处User为系统自己定义的model
	 *
	 * @return
	 */
	@Bean
	public UserDetailsService userDetailsService() {
		return (username) -> this.userRepository.findByUsername(username)
				.map(user -> new User(user.getUsername(), user.getPassword(), true, true, true, true,
						AuthorityUtils.createAuthorityList("ROLE_USER")))
				.orElseThrow(() -> new UsernameNotFoundException(username));
	}
}
