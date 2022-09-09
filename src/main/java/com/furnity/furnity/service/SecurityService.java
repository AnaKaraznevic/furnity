package com.furnity.furnity.service;



public interface SecurityService {
	
    boolean isAuthenticated();
    
    void autoLogin(String username, String password);
    
}
