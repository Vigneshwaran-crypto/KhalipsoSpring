package com.example.khalipso.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.khalipso.Entities.Users;
import com.example.khalipso.WebModels.Response;
import com.example.khalipso.WebModels.UsersWebModel;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {

	Response save(UsersWebModel user);

}
