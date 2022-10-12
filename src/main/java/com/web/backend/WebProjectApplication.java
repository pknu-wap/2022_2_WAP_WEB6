package com.web.backend;

import com.web.backend.entity.AuthorityEntity;
import com.web.backend.entity.UserEntity;
import com.web.backend.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class WebProjectApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	public static void main(String[] args) {
		SpringApplication.run(WebProjectApplication.class, args);
	}

	@PostConstruct // 초기화 후 수행되는 매서드
	protected void init() {
		List<AuthorityEntity> authorityEntityList = new ArrayList<>();

		authorityEntityList.add(createAuthority("USER", "User role"));
		authorityEntityList.add(createAuthority("ADMIN", "Admin role"));

		UserEntity user = new UserEntity();
		user.setUserName("testt");
		user.setFirstName("test");
		user.setLastName("H");

		user.setPassword(passwordEncoder.encode("testtest"));
		user.setEnabled(true);
		user.setAuthorities(authorityEntityList);

		userDetailsRepository.save(user);
	}

	private AuthorityEntity createAuthority(String roleCode, String roleDescription) {
		AuthorityEntity authority = new AuthorityEntity();
		authority.setRoleCode(roleCode);
		authority.setRoleDescription(roleDescription);
		return authority;

	}
}
