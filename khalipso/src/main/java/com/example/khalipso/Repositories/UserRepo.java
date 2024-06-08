package com.example.khalipso.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.khalipso.Entities.Users;
import com.example.khalipso.WebModels.Response;
import com.example.khalipso.WebModels.UsersWebModel;



@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {

	Response save(UsersWebModel user);
	
	@Query(value = "SELECT * FROM users WHERE email=:email AND password=:password",nativeQuery = true)
	Users login(@Param("email") String email , @Param("password") String password);

	Optional<Users> findByEmail(String email);
	
	
	//Users updateUser(@Param("email") String email,@Param("password") String password,@Param("userName"))
	
	

}
