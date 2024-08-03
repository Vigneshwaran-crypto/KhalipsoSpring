package com.example.khalipso.ServiceImplements;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.khalipso.Components.AppProperties;
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
	

	
	@Autowired
	AppProperties appProps;

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
				
				
				Map<String,Object> mapUser = new HashMap<>();
				
				mapUser.put("id", userExist.getId());
				mapUser.put("email", userExist.getEmail());
				mapUser.put("userName", userExist.getUserName());
				mapUser.put("isSeller", userExist.getSeller());		
				return new Response(1,"success",mapUser);
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

	@Override
	public Response imageUpload(UsersWebModel user) {
		
//		System.out.println("uploaded image in userServiceImplementation :"+user.getProfileImageFile());
//		System.out.println("bio in userServiceImplementation :"+user.getBio());
		
		
		try {
			
			
			MultipartFile imgFile = user.getProfileImageFile();
			
			
			long imgSize = imgFile.getSize()/1024;
			
			System.out.println("file size : "+imgSize);
			
//			if(imgSize > 20) {
//				
//				return new Response(0,"fail","Large size of image");
//				
//			}else {
				
				String fileName = imgFile.getOriginalFilename();
				String location =  appProps.getAssetPath();
//				String time = new SimpleDateFormat("DD-MM-YYYY").format(new Date());
//				String extension =  fileName.substring(fileName.lastIndexOf(".")+1);
//				String dbFileName = +"-"+time+"-"+extension;;
				String path = location+fileName;
				
				File file = new File(path);
				imgFile.transferTo(file);
				
				return new Response(1,"success",user.getProfileImageFile().getOriginalFilename());
				
//			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			return new Response(0,"fail",null);
		}
		
		
		
		
	}

	@Override
	public Response updateUser(UsersWebModel user) {
	
		
		System.out.println("api hit implementation :"+user);
		
		Optional<Users> dbUser = userRepo.findByEmail(user.getEmail());
		
		Users usr = dbUser.get();
	
		usr.setEmail(user.getEmail());
		usr.setPassword(user.getPassword());
		usr.setBio(user.getBio());
		usr.setProfileImage(user.getProfileImageFile().getOriginalFilename());
		usr.setId(user.getId());
		usr.setUserName(user.getUserName());
		
		
		return new Response(1,"testing",userRepo.save(usr));
	}

}
