package com.example.demo.board.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.board.domain.UserVO;


@Repository("com.example.demo.board.mapper.AdminMapper")
public interface AdminMapper {

	// 유저 리스트 가져오기 
	public List<UserVO> getUserListByAdmin()throws Exception;
	// 유저 정보 가져오기 
	public UserVO getUserInfoByAdmin(int uno)throws Exception;
	// 유저 정보 수정 
	public void setUserInfoByAdmin(UserVO user)throws Exception;
	// 유저 인원수 세기
	public int countUserListByAdmin()throws Exception;
	
	public void deleteUserInfoByAdmin(int uno)throws Exception;
	
}
