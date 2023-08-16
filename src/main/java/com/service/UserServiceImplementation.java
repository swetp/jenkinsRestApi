package com.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDaoRepository;
import com.dto.UserDto;
import com.entity.UserEntity;
import com.exception.DuplicateEmailException;
import com.exception.UserNotFoundException;

@Service
@Transactional
public class UserServiceImplementation implements UserService{

	@Autowired
	UserDaoRepository userDaoRepository;

	
	@Override
	public UserDto findById(int userId) {
		try {
			Optional<UserEntity> optional = userDaoRepository.findById(userId);
			UserEntity userEntity = optional.get();
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(userEntity, userDto);
			return userDto;
		}catch(Exception e) {
			throw new UserNotFoundException("User does not exist in database");
		}	
	}


	@Override
	public List<UserDto> findAllUser() {
		List<UserEntity> userEntityList = userDaoRepository.findAll();
		List<UserDto> userDtoList = new ArrayList<>();
		if(userEntityList.size() > 0) {
			for(UserEntity temp: userEntityList) {
				UserDto userDto = new UserDto();
				BeanUtils.copyProperties(temp, userDto);
				userDtoList.add(userDto);
			}
		}
		return userDtoList;
	}


	@Override
	public void register(UserDto userDto) {
		
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userDto, userEntity);
		try {
			userDaoRepository.saveAndFlush(userEntity);
		}catch(Exception e) {
			throw new DuplicateEmailException("Email already in use. Try another.");
		}
		
	}


	@Override
	public void delete(int userId) {
		try {
			userDaoRepository.deleteById(userId);
		}catch(Exception e) {
			throw new UserNotFoundException("User does not exist in database");
		}
		
	}


	@Override
	public void update(UserDto userDto) {
		UserEntity  userEntity = new UserEntity();
		BeanUtils.copyProperties(userDto, userEntity);
		try {
			
			Optional<UserEntity> optional = userDaoRepository.findById(userEntity.getUserId());
			
		}catch(Exception e) {
			throw new UserNotFoundException("User does not exist in database");
		}
		try {
			userDaoRepository.saveAndFlush(userEntity);
		}catch(Exception e) {
			throw new DuplicateEmailException("Email is already in use. Try another");
		}
	}
	
	
	
}
