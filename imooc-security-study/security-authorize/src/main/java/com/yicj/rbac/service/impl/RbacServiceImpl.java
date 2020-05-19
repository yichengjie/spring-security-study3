package com.yicj.rbac.service.impl;

import com.yicj.rbac.service.RbacService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Component("rbacService")
public class RbacServiceImpl implements RbacService {

	private AntPathMatcher antPathMatcher = new AntPathMatcher();

	@Override
	public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
		//读取用户所拥有权限的所有url
		Set<String> urls = new HashSet<>() ;
		urls.add("/user/api/*") ;
		Object principal = authentication.getPrincipal();
		boolean hasPermission = false;
		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			for (String url: urls){
				if (antPathMatcher.match(url, request.getRequestURI())){
					hasPermission = true ;
					break;
				}
			}
		}
		return hasPermission;
	}

}
