package com.itmcs.roo.mirisk.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.itmcs.roo.mirisk.dta.ManageUser;
import com.itmcs.roo.mirisk.service.api.CategoryService;
import com.itmcs.roo.mirisk.service.api.ManageUserService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    
	private ManageUserService manageUserService;

    @Autowired
    public CustomUserDetailsService(ManageUserService manageUserService) {
        this.manageUserService = manageUserService;
    }
    
    public ManageUserService getManageUserService() {
		return manageUserService;
	}

    public void setManageUserService(ManageUserService manageUserService) {
		this.manageUserService = manageUserService;
	}

	@Override
	public ManageUser loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Inside CustomUserDetailsService.loadUserByUsername, username: " + username);
        try {
        ManageUser manageUser = getManageUserService().findByUserName(username);
        if (null == manageUser) {
            throw new UsernameNotFoundException("can't find " + username + "!");
        }
        System.out.println("manageUser:"+manageUser);
        return manageUser;
        }catch(Exception e) {
        	System.out.println("in catch");
        	e.printStackTrace();
        }
        return null;
    }
}
