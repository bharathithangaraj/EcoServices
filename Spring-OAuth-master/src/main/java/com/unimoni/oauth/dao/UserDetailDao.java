package com.unimoni.oauth.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unimoni.oauth.model.User;
import com.unimoni.oauth.model.UserDetail;

@Repository
public interface UserDetailDao extends JpaRepository<UserDetail, Long> {
	
	public List<UserDetail> findByUserId(Long userId);
	public UserDetail findByUserDetailId(Long userDetailId);

    
}
