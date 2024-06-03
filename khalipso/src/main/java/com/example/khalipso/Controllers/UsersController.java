package com.example.khalipso.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.khalipso.Services.UserService;
import com.example.khalipso.WebModels.Response;
import com.example.khalipso.WebModels.UsersWebModel;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/")
public class UsersController {
	
	
	@Autowired
	UserService userService;
	
	
	@PostMapping("saveUser")
	public ResponseEntity<Response> saveUser(@RequestBody UsersWebModel user,HttpServletRequest request){
		
		
		try {
			
			
			System.out.println("UsersWebModel in controller :"+user.toString());
			
			
			System.out.println("request in controller :"+request.toString());
			

			return new ResponseEntity<Response>(userService.saveUser(user,request),HttpStatus.OK);
			
		}catch(Exception e) {
			System.out.println("Error occurred in saveUser api");
			e.printStackTrace();
		}
		
		
		return ResponseEntity.internalServerError().body(new Response(-1,"API Faild", null));
		
	}


}
