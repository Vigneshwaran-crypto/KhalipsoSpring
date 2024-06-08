package com.example.khalipso.ServiceImplements;

import java.util.Optional;

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
		
		//Users mailCheck = userRepo.findByEmail(user.getEmail());
		Optional<Users> mailCheck = userRepo.findByEmail(user.getEmail());
		
		if(mailCheck.isPresent()) {
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
		
//		Users tableUser = userRepo.login(user.getEmail(), user.getPassword());
				
		Optional<Users> mailCheck = userRepo.findByEmail(user.getEmail());
		
		System.out.println("mail checking from table"+mailCheck);
		
		if(mailCheck.isPresent()) {
			Users userExist = mailCheck.get();
			
			System.out.println("exist user : "+userExist);
			
			System.out.println("exist user pass: "+userExist.getPassword());
			System.out.println("request user pass:"+user.getPassword());
			
			String existPass = userExist.getPassword();
			String clientPass = user.getPassword();
			
			if(existPass.equalsIgnoreCase(clientPass)) {
				return new Response(1,"success","Hello User!!!");
			}
			else {
				return new Response(0,"fail","Enter valid password");

			}
				

		}else {
			return new Response(0, "fail" ,"No User Found");
		}
		
	}

	@Override
	public Response editUser(UsersWebModel user) {
		System.out.println("user in serviceImplementation : "+user);
		
		Optional<Users> existUser = userRepo.findByEmail(user.getEmail());
		System.out.println("existUser user in serviceImplementation : "+existUser.get());
		if(existUser.isPresent()) {
			Users dbUser = existUser.get();
			dbUser.setEmail(user.getEmail());
			dbUser.setPassword(user.getPassword());
			dbUser.setBio(user.getBio());
			dbUser.setProfileImage(user.getProfileImage());
			dbUser.setUserName(user.getUserName());
			userRepo.saveAndFlush(dbUser);
			return new Response(1,"success",dbUser);
		}else {
			return new Response(0,"fail",null);
		}
		
		
		
		
		
		
	}

}
