package com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dto.UserDto;
import com.entity.UserEntity;
import com.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<?> getUser(@PathVariable int userId){
		UserDto userDto = userService.findById(userId);
		return new ResponseEntity<>(userDto, HttpStatus.OK);
		
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserDto>> getAllUser(){
		List<UserDto> userList = userService.findAllUser();
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}
	
	@PostMapping("/users")
	public  ResponseEntity<String> addUser(@RequestBody UserDto userDto){
		userService.register(userDto);
		return new ResponseEntity<>("User Registered Successfully!", HttpStatus.OK);
		
	}
	
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable int userId){
		userService.delete(userId);
		return new ResponseEntity<>("User Deleted Successfully", HttpStatus.OK);
	}
	
	@PutMapping("/users")
	public ResponseEntity<String> updateUser(@RequestBody UserDto userDto){
		userService.update(userDto);
		return new ResponseEntity<>("User Updated Successfully", HttpStatus.OK);
	}
	

}
