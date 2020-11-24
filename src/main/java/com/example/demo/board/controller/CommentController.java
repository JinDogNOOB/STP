package com.example.demo.board.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.board.domain.CommentVO;
import com.example.demo.board.domain.UserSessionVO;
import com.example.demo.board.domain.UserVO;
import com.example.demo.board.service.BoardService;
import com.example.demo.board.service.CommentService;
import com.example.demo.util.SecurityUtil;





@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Resource(name="com.example.demo.board.service.CommentService")
	CommentService mCommentService;
	@Resource(name="com.example.demo.board.service.BoardService")
	BoardService mBoardService = new BoardService();
	
	SecurityUtil secu = new SecurityUtil();
	
	@RequestMapping("/list") // 댓글 리스트 
	@ResponseBody
	private List<CommentVO> mCommentServiceList(@RequestParam int bno, @RequestParam String boardName, Model model)throws Exception{
		CommentVO comment = new CommentVO();
		comment.setBno(bno);
		comment.setBoardName(boardName);
		
		return mCommentService.commentListService(comment);
	}
	
	
	@RequestMapping("/insert") // 댓글 작성 
	@ResponseBody
	private int mCommentServiceInsert(@RequestParam int bno, @RequestParam String content, @RequestParam String boardName, HttpServletRequest request) throws Exception{
		HttpSession httpSession = request.getSession(false);
		UserSessionVO userSessionVO = (UserSessionVO)httpSession.getAttribute("USER");
		
		CommentVO comment = new CommentVO();
		comment.setBno(bno);
		comment.setBoardName(boardName);
		comment.setContent(secu.replaceXssString(content));
		comment.setWriter(userSessionVO.getUname());
		comment.setUid(userSessionVO.getUid());
		
		
		mBoardService.addMoneyService(userSessionVO.getUid(), 1);
		return mCommentService.commentInsertService(comment);
	}
	
	@RequestMapping("/update") // 업데이트 
	@ResponseBody
	private int mCommentServiceUpdateProc(@RequestParam int cno, @RequestParam String content, HttpServletRequest request)throws Exception{
		
		CommentVO dbCommentVO = mCommentService.getCommentDetailByCnoService(cno);
		
		HttpSession httpsession = request.getSession(false); // 세션 체크.. 실전엔 false로 
		if((httpsession == null)  ) {
		
			return 11;
		}
		UserSessionVO usersession = (UserSessionVO)httpsession.getAttribute("USER");
		if((usersession == null)) {
		
			return 11;
		}
		UserVO dbUserVO = mBoardService.userDetailService(dbCommentVO.getUid());
		if(dbUserVO.getUid().equals(usersession.getUid()) == false) {
			
			return 11;
		} //사용자 체크 끝 
		
		
		CommentVO comment = new CommentVO();
		comment.setCno(cno);
		comment.setContent(secu.replaceXssString(content));
		
		return mCommentService.commentUpdateService(comment);
	}
	
	
	@RequestMapping("/delete/{cno}") // 댓글 삭제 
	@ResponseBody
	private int mCommentServiceDelete(@PathVariable int cno, HttpServletRequest request) throws Exception{
		
		CommentVO dbCommentVO = mCommentService.getCommentDetailByCnoService(cno);
		
		HttpSession httpsession = request.getSession(false); // 세션 체크.. 실전엔 false로 
		if((httpsession == null)  ) {
		
			return 11;
		}
		UserSessionVO usersession = (UserSessionVO)httpsession.getAttribute("USER");
		if((usersession == null)) {
		
			return 11;
		}
		UserVO dbUserVO = mBoardService.userDetailService(dbCommentVO.getUid());
		if(dbUserVO.getUid().equals(usersession.getUid()) == false) {
			
			return 11;
		} //사용자 체크 끝 
		
		return mCommentService.commentDeleteService(cno);
	}
	
	
	
}
