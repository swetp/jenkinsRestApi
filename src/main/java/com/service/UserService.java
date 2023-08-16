package com.service;

import java.util.List;

import com.dto.UserDto;

public interface UserService {

	UserDto findById(int userId);
	List<UserDto> findAllUser();
	void register(UserDto userDto);
	void delete(int userId);
	void update(UserDto userDto);

}
