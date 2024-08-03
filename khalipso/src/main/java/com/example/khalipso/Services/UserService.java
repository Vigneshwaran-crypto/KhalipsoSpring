package com.example.khalipso.Services;

import com.example.khalipso.WebModels.Response;
import com.example.khalipso.WebModels.UsersWebModel;

import jakarta.servlet.http.HttpServletRequest;

public interface UserService {
	
	Response saveUser(UsersWebModel user,HttpServletRequest request);
	
	Response logIn(UsersWebModel user,HttpServletRequest request);
	
	Response editUser(UsersWebModel user);
	
	Response imageUpload(UsersWebModel user);
	
	Response updateUser(UsersWebModel user);

}
