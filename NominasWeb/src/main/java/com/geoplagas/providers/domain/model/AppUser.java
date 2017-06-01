package com.geoplagas.providers.domain.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class AppUser extends User
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2418084138861110416L;
	
	private String name;
	private String email;
	private String lang;
	private Long userId;
	private String image;
	private Long providerId;
	
	public AppUser(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked, Collection<? extends GrantedAuthority> set) {
		super(username, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, set);

	}
	
	public AppUser(User user) {
		super(user.getUsername(),"",true,true,true,true,user.getAuthorities());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}
}
