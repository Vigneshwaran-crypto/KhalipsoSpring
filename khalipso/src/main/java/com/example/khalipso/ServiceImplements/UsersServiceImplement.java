package com.example.khalipso.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.khalipso.Entities.Users;
import com.example.khalipso.Repositories.UserRepo;
import com.example.khalipso.Services.UserService;
import com.example.khalipso.WebModels.Response;
import com.example.khalipso.WebModels.UsersWebModel;

import jakarta.servlet.http.HttpServletRequest;

@Service
@Primary
public class UsersServiceImplement implements UserService {
	
	
	
	@Autowired
	UserRepo userRepo;

	@Override
	public Response saveUser(UsersWebModel user, HttpServletRequest request) {
		
		Users tableUser = new Users();
		
		System.out.println("user data in seveImple :"+user);
		
		System.out.println("user request in seveImple :"+request);
		
		Users mailCheck = userRepo.findByEmail(user.getEmail());
		
		if(mailCheck != null) {
			return new Response(0,"fail","Email already exists");
		}else {
			
		tableUser.setEmail(user.getEmail());
		tableUser.setPassword(user.getPassword());
		tableUser.setUserName(user.getUserName());
		tableUser.setBio(user.getBio());
		tableUser.setSeller(user.getSeller());
		
		return new Response(1,"success",userRepo.save(tableUser));
		}
	}

	@Override
	public Response logIn(UsersWebModel user, HttpServletRequest request) {
		
		Users tableUser = userRepo.login(user.getEmail(), user.getPassword());
		
		Users mailCheck = userRepo.findByEmail(user.getEmail());
		
		System.out.println("mail checking from table"+mailCheck);
		
		System.out.println("users in login :"+tableUser);
		
		
		if(mailCheck == null) {
			return new Response(0,"fail","Enter valid email");
		}else if( mailCheck != null && tableUser == null) {
			return new Response(0,"fail","Enter valid password");
		}else if(tableUser == null) {
			return new Response(0,"fail","User doesn't exist");
		}else {
			return new Response(1,"succes",tableUser);
		}
		
	
	}

}
