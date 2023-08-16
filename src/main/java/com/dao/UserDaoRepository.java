package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.entity.UserEntity;

public interface UserDaoRepository extends JpaRepository<UserEntity, Integer> {

}
