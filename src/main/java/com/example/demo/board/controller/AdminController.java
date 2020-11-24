package com.example.demo.board.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.board.domain.UserSessionVO;
import com.example.demo.board.domain.UserVO;
import com.example.demo.board.service.AdminService;
import com.example.demo.board.service.BoardService;
import com.example.demo.util.Klog;
import com.example.demo.util.SecurityUtil;

@Controller
public class AdminController {
	@Resource(name="com.example.demo.board.service.AdminService")
	AdminService mAdminService;
	@Resource(name="com.example.demo.board.service.BoardService")
	BoardService mBoardService;
	
	SecurityUtil secu = new SecurityUtil();
	
	@RequestMapping("/admin/user/list")
	public String getUserListByAdmin(HttpServletRequest request, Model model)throws Exception{
		Klog.getInstance().log(request, request.getRequestURI() + request.getQueryString() + " ADMIN");
		// 세션체크 
		HttpSession httpsession = request.getSession(false); // 세션 체크.. 실전엔 false로 
		if((httpsession == null)  ) {
			model.addAttribute("msg", "세션이 없음");
			model.addAttribute("url", "/user/login");
			return "redirect";
		}
		UserSessionVO usersession = (UserSessionVO)httpsession.getAttribute("USER");
		if((usersession == null)) {
			model.addAttribute("msg", "세션이 없음_1");
			model.addAttribute("url", "/user/login");
			return "redirect";
		}
		UserVO dbUserVO = mBoardService.userDetailService(usersession.getUid());
		if(false==(dbUserVO.getUpassword().equals(usersession.getUpassword()) && dbUserVO.getJsession().equals(httpsession.getId()))) {
			httpsession.invalidate();
			model.addAttribute("msg", "세션 정보가 잘못됨 or 다른 사용자가 이 아이디로 로그인했음");
			model.addAttribute("url", "/user/login");
			return "redirect";
		} //세션체크끝 
		if(dbUserVO.getAuth() < 99){
			model.addAttribute("msg", "권한이 없음_ADMIN이 아님");
			model.addAttribute("url", "/user/info/mypage");
			return "redirect";
		} //권한체크끝
		
		
		// 권한 체크 후
		// 유저 리스트 던져주면 댐
		model.addAttribute("userlist", mAdminService.getUserListByAdminService());
		model.addAttribute("usercount", mAdminService.countUserListByAdminService());
		return "/adminx/adminuserlist";
	}
	
	
	// 권한 체크 후 
	// 유저 정보 수정창 리턴 해당 유저 정보와 함께
	@RequestMapping("/admin/user/modify")
	public String getUserInfoModifyPageByAdmin(HttpServletRequest request, Model model)throws Exception{
		Klog.getInstance().log(request, request.getRequestURI() + request.getQueryString() + " ADMIN");
		// 세션체크 
				HttpSession httpsession = request.getSession(false); // 세션 체크.. 실전엔 false로 
				if((httpsession == null)  ) {
					model.addAttribute("msg", "세션이 없음");
					model.addAttribute("url", "/user/login");
					return "redirect";
				}
				UserSessionVO usersession = (UserSessionVO)httpsession.getAttribute("USER");
				if((usersession == null)) {
					model.addAttribute("msg", "세션이 없음_1");
					model.addAttribute("url", "/user/login");
					return "redirect";
				}
				UserVO dbUserVO = mBoardService.userDetailService(usersession.getUid());
				if(false==(dbUserVO.getUpassword().equals(usersession.getUpassword()) && dbUserVO.getJsession().equals(httpsession.getId()))) {
					httpsession.invalidate();
					model.addAttribute("msg", "세션 정보가 잘못됨 or 다른 사용자가 이 아이디로 로그인했음");
					model.addAttribute("url", "/user/login");
					return "redirect";
				} //세션체크끝 
				if(dbUserVO.getAuth() < 99){
					model.addAttribute("msg", "권한이 없음_ADMIN이 아님");
					model.addAttribute("url", "/user/info/mypage");
					return "redirect";
				} //권한체크끝
		
		int uno;
		if(("".contentEquals(request.getParameter("uno"))==false)) {
			uno = Integer.parseInt(request.getParameter("uno"));
		}else {
			model.addAttribute("url", "/admin/user/list");
			model.addAttribute("msg", "Exception : uno is fxxking null");
			return "redirect";
		}
		
		model.addAttribute("user", mAdminService.getUserInfoByAdminService(uno));
		return "/adminx/adminusermodify";
	}
	
	// 패스워드는 암호화되서 가야하므로 패스워드가 바뀌었나 안바뀌었나 체크해야함 
	//만약에 안바뀌었으면 그냥 보내야함 암호화하면 안댐 
	@RequestMapping("/admin/user/modifyProc")
	public String setUserInfoByAdmin(HttpServletRequest request, Model model)throws Exception{
		Klog.getInstance().log(request, request.getRequestURI() + request.getQueryString() + " ADMIN");
		// 세션체크 
		HttpSession httpsession = request.getSession(false); // 세션 체크.. 실전엔 false로 
		if((httpsession == null)  ) {
			model.addAttribute("msg", "세션이 없음");
			model.addAttribute("url", "/user/login");
			return "redirect";
		}
		UserSessionVO usersession = (UserSessionVO)httpsession.getAttribute("USER");
		if((usersession == null)) {
			model.addAttribute("msg", "세션이 없음_1");
			model.addAttribute("url", "/user/login");
			return "redirect";
		}
		UserVO dbUserVO = mBoardService.userDetailService(usersession.getUid());
		if(false==(dbUserVO.getUpassword().equals(usersession.getUpassword()) && dbUserVO.getJsession().equals(httpsession.getId()))) {
			httpsession.invalidate();
			model.addAttribute("msg", "세션 정보가 잘못됨 or 다른 사용자가 이 아이디로 로그인했음");
			model.addAttribute("url", "/user/login");
			return "redirect";
		} //세션체크끝 
		if(dbUserVO.getAuth() < 99){
			model.addAttribute("msg", "권한이 없음_ADMIN이 아님");
			model.addAttribute("url", "/user/info/mypage");
			return "redirect";
		} //권한체크끝
		
		UserVO userVO = new UserVO();
		UserVO otherDbUserVO;
		
		try {
			userVO.setUno(Integer.parseInt(request.getParameter("uno")));
	
			userVO.setUname(request.getParameter("uname"));
			userVO.setAuth(Integer.parseInt(request.getParameter("auth")));
			userVO.setMoney(Integer.parseInt(request.getParameter("money")));
			
			otherDbUserVO = mAdminService.getUserInfoByAdminService(userVO.getUno());
			
			if("".contentEquals(request.getParameter("upassword"))) {
				// 입력값이 없다는것은 아무것도 입력을 안한상태 디비에서 받아서 넣어주면됨
				userVO.setUpassword(otherDbUserVO.getUpassword());
				mAdminService.setUserInfoByAdminService(userVO);
			}else {
				// 패스워드가 다르다는 거는 입력했다는 거임 암호화 시켜서 넘겨줘야함 
				String temp = request.getParameter("upassword");
				userVO.setUpassword(secu.encryptSHA256(temp));
				mAdminService.setUserInfoByAdminService(userVO);
			}
		}catch(Exception e) {
			model.addAttribute("url", "/admin/user/list");
			model.addAttribute("msg", "변경 실패 뭔가 잘못됨. null 같은걸 부었나? ");
			return "redirect";	
		}
		
		model.addAttribute("url", "/admin/user/list");
		model.addAttribute("msg", "정보가 잘  수정되었습니다.");
		return "redirect";
	}
	
	@RequestMapping("/admin/user/deleteProc")
	public String deleteUserInfoByAdmin(HttpServletRequest request, Model model)throws Exception{
		Klog.getInstance().log(request, request.getRequestURI() + request.getQueryString() + " ADMIN");
		
		// 세션체크 
		HttpSession httpsession = request.getSession(false); // 세션 체크.. 실전엔 false로 
		if((httpsession == null)  ) {
			model.addAttribute("msg", "세션이 없음");
			model.addAttribute("url", "/user/login");
			return "redirect";
		}
		UserSessionVO usersession = (UserSessionVO)httpsession.getAttribute("USER");
		if((usersession == null)) {
			model.addAttribute("msg", "세션이 없음_1");
			model.addAttribute("url", "/user/login");
			return "redirect";
		}
		UserVO dbUserVO = mBoardService.userDetailService(usersession.getUid());
		if(false==(dbUserVO.getUpassword().equals(usersession.getUpassword()) && dbUserVO.getJsession().equals(httpsession.getId()))) {
			httpsession.invalidate();
			model.addAttribute("msg", "세션 정보가 잘못됨 or 다른 사용자가 이 아이디로 로그인했음");
			model.addAttribute("url", "/user/login");
			return "redirect";
		} //세션체크끝 
		if(dbUserVO.getAuth() < 99){
			model.addAttribute("msg", "권한이 없음_ADMIN이 아님");
			model.addAttribute("url", "/user/info/mypage");
			return "redirect";
		} //권한체크끝
		
		
		int uno;
		if( ("".contentEquals(request.getParameter("uno") ) ) == true) {
			// uno 가 널이면
			model.addAttribute("url", "/admin/user/list");
			model.addAttribute("msg", "uno was null");
			return "redirect";
		}else {
			uno = Integer.parseInt(request.getParameter("uno"));
		}
		
		mAdminService.deleteUserInfoByAdminService(uno);
		model.addAttribute("url", "/admin/user/list");
		model.addAttribute("msg", "계정이 잘 삭제되었습니다.");
		return "redirect";
	}
	
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
}
