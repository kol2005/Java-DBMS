package com.biz.user.dao;

import java.util.List;

import com.biz.user.persistence.UserDTO;

public interface UserDao {
	
public String getMaxUCode();
public List<UserDTO> selectAll();
public UserDTO findById(String u_code);
public List<UserDTO> findByName(String u_name);
public List<UserDTO> findByTel(String u_tel);
public int insert(UserDTO userDTO);
public int update(UserDTO userDTO);
public int delete(String u_code);
	
}
