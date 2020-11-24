package com.example.demo.board.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.board.domain.AuthUrlVO;
import com.example.demo.board.domain.BoardVO;
import com.example.demo.board.domain.CommentVO;
import com.example.demo.board.domain.FileVO;

import com.example.demo.board.domain.UserVO;



@Repository("com.example.demo.board.mapper.BoardMapper") // define this class will access on database
public interface BoardMapper { // writing the interface on here and this is used by BoardMapper.XML
	
	
	public void addJsessionByUid(Map<String, Object> map)throws Exception;
	
	
	//######################################################
	/*USER MAPPER*/
	
	// find uid by email
	public List<String> findUidByEmail(String email)throws Exception;
	
	//signup ID overlap check
	public UserVO userDetail(String uid)throws Exception;
	
	//signup IDPW
	public void userInsert(UserVO user) throws Exception;
	
	// delete user 
	public int userDelete(int uno) throws Exception;
	
	// add users point
	public void addMoney(Map<String, Object> map) throws Exception;
	
	public int getMoney(String uid)throws Exception;
	// update user info
	public void userUpdate(UserVO user)throws Exception;
	
	//update user auth
	public void userAuthUpdate(UserVO user)throws Exception;
	
	//update user password
	public void userPasswordUpdate(UserVO user)throws Exception;
	
	//######################################################
	/*BOARD MAPPER*/
	
	// uid 가지고 해당 uid 가지고 있는 게시물 모든 db 뒤져서 리턴한다. 
	public List<BoardVO> boardGrepAllByUid(String uid)throws Exception;
	
	// 게시물 bno 최고 높은거 찾는다.
	public Integer boardHighestBno(Map<String, Object> map)throws Exception;
	
	// 검색 게시물 bno 높은거 
	public Integer boardQueryHighestBno(Map<String, Object> map)throws Exception;
	
	// 전체 게시물 숫자를 센다 
	public int boardCount(Map<String, Object> map) throws Exception;
	
	// 검색된 전체 게시물 숫자를 센다.
	public int boardQueryCount(Map<String, Object> map)throws Exception;
	
	// 보드 리스트 제너릭배열클래스로 리턴 
	public List<BoardVO> boardList(Map<String, Object> map) throws Exception;
	
	// 검색된 보드 리스트 제너릭배열클래스로 리턴 
	public List<BoardVO> boardQueryList(Map<String, Object> map) throws Exception;
		
	// 게시글 내용 불러오기 
	public BoardVO boardDetail(Map<String, Object> map)throws Exception;
	
	// 글쓰기 
	public int boardInsert(com.example.demo.board.domain.BoardVO board)throws Exception;
	
	// 게시글 수정하기 
	public int boardUpdate(Map<String, Object> map)throws Exception;
	
	// 게시글 삭제하기 
	public int boardDelete(Map<String, Object> map) throws Exception;
	
	// 조회수 올리기 
	public void boardAddViewCount(Map<String, Object> map)throws Exception;
	
	
	
	
	//######################################################
	/*FILE MAPPER*/
	
	// file insert
	public int fileInsert(FileVO file)throws Exception;
	
	// file detail
	public FileVO fileDetail(Map<String, Object> map)throws Exception;

	
	
	
	
	//#######################################################
	/*AUTH URL MAPPER*/
	public void setAuthUrl(AuthUrlVO auth)throws Exception; // uid authType url 입력 
	
	public AuthUrlVO getAuthUrlT(AuthUrlVO auth)throws Exception; // uid와 authType을 가지고 url 리턴 

	public void deleteAuthUrl(AuthUrlVO auth)throws Exception; // uid와 authType을 가지고 url 삭제 
	
	public int countAuthByUrl(String url)throws Exception; // url로 컬럼 count 후 int 리턴 
	
	public AuthUrlVO getAuthByUrl(String url)throws Exception; // url 로   authUrlVO 리턴 
	
	public void deleteUrlByUrl(String url)throws Exception; // url로 url 삭제 
}
