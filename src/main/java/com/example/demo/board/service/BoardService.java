package com.example.demo.board.service;

import com.example.demo.board.mapper.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.demo.board.domain.AuthUrlVO;
import com.example.demo.board.domain.BoardVO;
import com.example.demo.board.domain.CommentVO;
import com.example.demo.board.domain.FileVO;

import com.example.demo.board.domain.UserVO;
import com.example.demo.board.mapper.BoardMapper;
import com.example.demo.util.SecurityUtil;

@Service("com.example.demo.board.service.BoardService")
public class BoardService {

	@Resource(name="com.example.demo.board.mapper.BoardMapper")
	BoardMapper mBoardMapper;
	
	
	//######################세션아이디 조작 테스트 
	public void addJsessionByUidService(String jsession, String uid)throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("jsession", jsession);
		map.put("uid", uid);
		mBoardMapper.addJsessionByUid(map);
	}
	
	
	/*######################################################################*/
	/*######################################################################*/
	/*######################################################################*/
	/*USERS SERVICE*/
	public List<String> findUidByEmailService(String email)throws Exception{
		
		return mBoardMapper.findUidByEmail(email);
	}
	
	public boolean userLoginCheckService(UserVO user)throws Exception{
		
		UserVO db_user = new UserVO();
		try {
			db_user.setUid(mBoardMapper.userDetail(user.getUid()).getUid());
			db_user.setUpassword(mBoardMapper.userDetail(user.getUid()).getUpassword());
		}catch(Exception e) {
			return false;
		}
		if(db_user.getUid().contentEquals(user.getUid()) && db_user.getUpassword().contentEquals(user.getUpassword())) {
			return true;
		}else {
			return false;
		}
	}
	
	
	public UserVO userDetailService(String uid)throws Exception{
		return mBoardMapper.userDetail(uid);
	}
	
	
	
	public boolean userIdCheckService(String uid)throws Exception{ 

		if(mBoardMapper.userDetail(uid)==null) {
			return true; //중복이 없으면 리턴 트루 
		}else {
			return false; // 널이 아니면, 중복이 있으면 false
		}
	}
	
	
	
	public void userInsertService(UserVO user)throws Exception{
	
		mBoardMapper.userInsert(user);
	}
	
	
	public boolean userDeleteService(UserVO user)throws Exception{
		
		UserVO dbuser = new UserVO();
		dbuser.setUid(mBoardMapper.userDetail(user.getUid()).getUid());
		dbuser.setUpassword(mBoardMapper.userDetail(user.getUid()).getUpassword());
		if(dbuser.getUid()==user.getUid() && dbuser.getUpassword()==user.getUpassword()) {
			mBoardMapper.userDelete(dbuser.getUno());
			return true;
		}else {
			return false;
		}
	}
	
	public void userUpdateService(UserVO user)throws Exception{
		mBoardMapper.userUpdate(user);
		return;
	}
	
	public void userAuthUpdateService(String uid, String auth)throws Exception {
		UserVO userVO = new UserVO();
		userVO.setUid(uid);
		userVO.setAuth(Integer.parseInt(auth));
		mBoardMapper.userAuthUpdate(userVO);
	}
	
	public void userPasswordUpdateService(String uid, String password)throws Exception {
		UserVO userVO = new UserVO();
		userVO.setUid(uid);
		userVO.setUpassword(password);
		mBoardMapper.userPasswordUpdate(userVO);
	}
	
	/*######################################################################*/
	/*######################################################################*/
	/*######################################################################*/
	/*BOARD SERVICE*/
	
	public List<BoardVO> boardGrepAllByUidService(String uid)throws Exception{
		
		return mBoardMapper.boardGrepAllByUid(uid);
	}
	
	public int boardIndexCountService(String boardName, int row)throws Exception{
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardname", boardName.toUpperCase());
		return (mBoardMapper.boardCount(map)/row)+1; // 게시판 인덱스 최대 숫자를 리턴. 
	}
	
	public int boardQueryIndexCountService(String boardName, int row, String query)throws Exception{
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardname", boardName.toUpperCase());
		map.put("query", query);
		return (mBoardMapper.boardQueryCount(map)/row)+1; // 게시판 검색결과 인덱스 최대 숫자를 리턴. 
	}
	
	public List<BoardVO> boardListService(String boardName, int boardPage, int row)throws Exception{
		// boardName : 테이블 식별자   boardPage : 게시판 페이지     row : 한번에 보여줄 게시글 수 
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardname", boardName.toUpperCase());
		int boardhighestBno=0;
		if (mBoardMapper.boardHighestBno(map)!=null) {
			boardhighestBno = mBoardMapper.boardHighestBno(map); 
		}
		int boardcount = mBoardMapper.boardCount(map);
		int indexStartBno = 1 +(boardhighestBno - boardcount) + boardcount -((boardPage-1)*row);
		
		map.put("indexstart", indexStartBno);
		map.put("row", row);
		
		return mBoardMapper.boardList(map);
	}
	
	
	public List<BoardVO> boardQueryListService(String boardName, int boardPage, int row, String query)throws Exception{
		// boardName : 테이블 식별자   boardPage : 게시판 페이지     row : 한번에 보여줄 게시글 수 
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardname", boardName.toUpperCase());
		map.put("query", query);
		
		map.put("queryindexstart", 10*(boardPage-1));
		int boardhighestBno=0;
		if (mBoardMapper.boardQueryHighestBno(map)!=null) {
			boardhighestBno = mBoardMapper.boardQueryHighestBno(map); 
		}
		
		
		
		map.put("indexstart", boardhighestBno+1);
		map.put("row", row);
		
		
		return mBoardMapper.boardQueryList(map);
	}
	
	public BoardVO boardDetailService(String boardName, int bno)throws Exception{
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardname", boardName.toUpperCase());
		map.put("bno", bno);
		mBoardMapper.boardAddViewCount(map); // 조회수 뷰카운트 올리기 
		return mBoardMapper.boardDetail(map);
	}
	
	public int boardInsertService(String boardName, BoardVO board) throws Exception{
		
		//안전하게 무식한 방법으로 한다.
		
		board.setBoardname(boardName.toUpperCase());
		//board.setBoardname("BOARDXFREE");
		//map.put("boardname", boardName.toUpperCase());
	
		return mBoardMapper.boardInsert(board);
	}
	
	public int boardUpdateService(String boardName, BoardVO board)throws Exception{
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardname", boardName.toUpperCase());
		map.put("subject", board.getSubject());
		map.put("content", board.getContent());
		map.put("bno", board.getBno());
		return mBoardMapper.boardUpdate(map);
	}
	
	public int boardDeleteService(String boardName, int bno)throws Exception{
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardname", boardName.toUpperCase());
		map.put("bno", bno);
		return mBoardMapper.boardDelete(map);
	}
	
	
	
	
	
	/*######################################################################*/
	/*######################################################################*/
	/*##################################################################*/
	/*FILE SERVICE*/
	
	public int fileInsertService(FileVO file)throws Exception{
		return mBoardMapper.fileInsert(file);
	}
	
	public FileVO fileDetailService(String boardName, int bno)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardname", boardName);
		map.put("bno", bno);
		return mBoardMapper.fileDetail(map); // throw bno values then you will get the FileVO instance and return thisr
	}
	
	
	
	/*######################################################################*/
	/*######################################################################*/
	/*##################################################################*/
	/*ADD POINT SERVICE*/
	
	public void addMoneyService(String uid, int money)throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		int newmoney = mBoardMapper.getMoney(uid) + money;
		map.put("uid", uid);
		map.put("money", newmoney);
		mBoardMapper.addMoney(map);
		return;
	}
	
	
	/*######################################################################*/
	/*######################################################################*/
	/*##################################################################*/
	/*AUTH PASSWORD URL SERVICE*/
	public void authUrlSetService(String uid, String authType, String url)throws Exception{
		
		AuthUrlVO authUrlVO = new AuthUrlVO();
		authUrlVO.setUid(uid);
		authUrlVO.setAuthType(authType);
		authUrlVO.setUrl(url);
		mBoardMapper.setAuthUrl(authUrlVO);
	}
	
	public boolean authMulCheckService(String uid, String authType)throws Exception{
		AuthUrlVO authUrlVO = new AuthUrlVO();
		authUrlVO.setUid(uid);
		authUrlVO.setAuthType(authType);
		
		if(mBoardMapper.getAuthUrlT(authUrlVO)==null) {
			return false;
		}else {
			return true;
		}	
	}
	
	public void authDeleteService(String uid, String authType)throws Exception{
		
		AuthUrlVO authUrlVO = new AuthUrlVO();
		authUrlVO.setUid(uid);
		authUrlVO.setAuthType(authType);
		mBoardMapper.deleteAuthUrl(authUrlVO);
	}
	
	public boolean authUrlCheckService(String url)throws Exception {
		if(mBoardMapper.countAuthByUrl(url) > 0) {
			return true;
		}
		return false;
	}
	
	public AuthUrlVO authUrlGetService(String url)throws Exception{
		
		return mBoardMapper.getAuthByUrl(url);
	}
	
	public void authUrlDeleteService(String url)throws Exception{
		
		mBoardMapper.deleteUrlByUrl(url);
	}
	
	
}
