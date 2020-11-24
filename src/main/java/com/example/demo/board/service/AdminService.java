package com.example.demo.board.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.demo.board.domain.UserVO;
import com.example.demo.board.mapper.AdminMapper;

@Service("com.example.demo.board.service.AdminService")
public class AdminService {

	@Resource(name="com.example.demo.board.mapper.AdminMapper")
	AdminMapper mAdminMapper;
	
	
	
	public List<UserVO> getUserListByAdminService()throws Exception{
		
		return mAdminMapper.getUserListByAdmin();
	}
	
	
	public UserVO getUserInfoByAdminService(int uno)throws Exception{
		
		return mAdminMapper.getUserInfoByAdmin(uno);
	}

	
	public void setUserInfoByAdminService(UserVO userVO)throws Exception{
		
		mAdminMapper.setUserInfoByAdmin(userVO);
	}
	
	public int countUserListByAdminService()throws Exception{
		
		return mAdminMapper.countUserListByAdmin();
	}
	
	public void deleteUserInfoByAdminService(int uno)throws Exception{
		mAdminMapper.deleteUserInfoByAdmin(uno);
		
	}
	
	
	
}
