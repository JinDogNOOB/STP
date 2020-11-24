package com.example.demo.board.controller;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//file upload download
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.board.domain.AuthUrlVO;
import com.example.demo.board.domain.BoardVO;
import com.example.demo.board.domain.FileVO;
import com.example.demo.board.domain.UserSessionVO;
import com.example.demo.board.domain.UserVO;
import com.example.demo.board.service.BoardService;
import com.example.demo.board.service.ShopService;
import com.example.demo.util.EmailServiceImpl;
import com.example.demo.util.Klog;
import com.example.demo.util.LocalIpUtil;

import com.example.demo.util.Pagination;
import com.example.demo.util.SecurityUtil;

import java.net.InetAddress;



@Controller
public class UserController {
	@Resource(name="com.example.demo.board.service.BoardService")
	BoardService mBoardService;
	@Resource(name="com.example.demo.board.service.ShopService")
	ShopService mShopService;
	
	
	SecurityUtil secu = new SecurityUtil();

	String uploadFilesX="/var/lib/tomcat8/webapps/ROOT/WEB-INF/uploadFiles/";
	String staticX = "/var/lib/tomcat8/webapps/ROOT/WEB-INF/classes/static/";
	
	
	/*###################################################/user/info/inventory###################################*/
	@RequestMapping("/user/info/inventory")
	private String getMyInventory(HttpServletRequest request, Model model)throws Exception{
		Klog.getInstance().log(request, request.getRequestURI() + request.getQueryString() + " request");
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
		
		model.addAttribute("itemLists", mShopService.getIventoryItemByUnoService(dbUserVO.getUno()));
		
		return "/userx/inventory";
	}
	
	/*########################################/user/mypage 마이페이지################################################*/
	@RequestMapping("/user/info/mypage")
	private String getMyPage(HttpServletRequest request, Model model)throws Exception{
		Klog.getInstance().log(request, request.getRequestURI() + request.getQueryString() + " request");
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
		
		
		model.addAttribute("list", mBoardService.boardGrepAllByUidService(dbUserVO.getUid()));		
		model.addAttribute("user", dbUserVO); // 아이디 이름 포인트 가입일 이메일 
		
		return "/userx/mypage";
	}
	
	
	/*#####################################새 비밀번호 이메일 인증으로 요청##############################################################3*/
	@RequestMapping("/user/info/getpwurl")
	private String getPwUrl(HttpServletRequest request, Model model)throws Exception{
		Klog.getInstance().log(request, request.getRequestURI() + request.getQueryString() + " request");
		LocalIpUtil localIpUtil = new LocalIpUtil();
		String ip = localIpUtil.getLocalServerIp();
		
		UserVO userVO = new UserVO();
		userVO.setUid(request.getParameter("uid").toString());
		userVO.setEmail(request.getParameter("email").toString());
		
		if(userVO.getEmail().contentEquals(mBoardService.userDetailService(userVO.getUid()).getEmail())) {
			if(mBoardService.authMulCheckService(userVO.getUid(), "PASSWORD")==true) { // 만약에 중복 있으면 
				mBoardService.authDeleteService(userVO.getUid(), "PASSWORD");
			}
			//여기부터 URL 재발급 or 발급 
			
			String url;
			do {
				url = RandomStringUtils.randomAlphanumeric(32);
			}while(mBoardService.authUrlCheckService(url)); // 만약에 중복되면 다시 
			mBoardService.authUrlSetService(userVO.getUid(), "PASSWORD", url);
			
			EmailServiceImpl email = new EmailServiceImpl();
			email.sendSimpleMessage(userVO.getEmail(), "이메일 인증입니다.", "이 메세지는 제삼웹서버끄래낑조로부터 시작됬으며 인증주소는 다음과 같습니다. https://"+ip+"/user/info/chkauthurl?url="+url);
			
			model.addAttribute("msg", "인증메일이 발송되었습니다");
			model.addAttribute("url", "/welcome");
		}else {
			model.addAttribute("msg", "정보가 잘못됨");
			model.addAttribute("url", "/welcome");
		}
		return "redirect";
	}
	
	/*####################################AUTH 이메일 인증 보내기######################################################################*/
	@RequestMapping("/user/info/getauthurl")
	private String getAuthUrl(HttpServletRequest request, Model model)throws Exception {
		Klog.getInstance().log(request, request.getRequestURI() + request.getQueryString() + " request");
		LocalIpUtil localIpUtil = new LocalIpUtil();
		String ip = localIpUtil.getLocalServerIp();
		
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
		
		
		
		if(mBoardService.authMulCheckService(dbUserVO.getUid(), "AUTH")==true) { // 만약에 중복 있으면 
			mBoardService.authDeleteService(dbUserVO.getUid(), "AUTH");
		}
		//여기부터 URL 재발급 or 발급 
		
		String url;
		do {
			url = RandomStringUtils.randomAlphanumeric(32);
		}while(mBoardService.authUrlCheckService(url)); // 만약에 중복되면 다시 
		mBoardService.authUrlSetService(dbUserVO.getUid(), "AUTH", url);
		
		EmailServiceImpl email = new EmailServiceImpl();
		email.sendSimpleMessage(dbUserVO.getEmail(), "이메일 인증입니다.", "이 메세지는 제삼웹서버끄래낑조로부터 시작됬으며 인증주소는 다음과 같습니다. https://"+ip+"/user/info/chkauthurl?url="+url);
		
		model.addAttribute("msg", "인증메일이 발송되었습니다");
		model.addAttribute("url", "/user/info/mypage");
		return "redirect"; 
	}
	
	/*##################################### 모든 이메일 인증 확인 ###########################################################################*/
	@RequestMapping("/user/info/chkauthurl")
	private String chkAuthUrl(HttpServletRequest request, Model model)throws Exception{
		Klog.getInstance().log(request, request.getRequestURI() + request.getQueryString() + " request");
		
		
		EmailServiceImpl email = new EmailServiceImpl();
		String url ="X";
		AuthUrlVO authUrlVO = new AuthUrlVO();
		if(request.getParameter("url")!=null) {
			url = request.getParameter("url");
		}
		if(mBoardService.authUrlCheckService(url) == true) { // 실제로 url 이 있다 
			authUrlVO.setUid(mBoardService.authUrlGetService(url).getUid());
			authUrlVO.setAuthType(mBoardService.authUrlGetService(url).getAuthType());
			
			if(authUrlVO.getAuthType().equals("AUTH")) { // 권한 상승 관련 url이었다.
				
				mBoardService.userAuthUpdateService(authUrlVO.getUid(), "1");
				mBoardService.authUrlDeleteService(url);
				model.addAttribute("msg", "계정이 인증되었습니다. 웹사이트를 자유롭게 이용하세요~");
				model.addAttribute("url", "/welcome");
			}else if(authUrlVO.getAuthType().equals("PASSWORD")) { // 비밀번호 찾기 관련 url이었다.
				
				String tempPassword = RandomStringUtils.randomAlphanumeric(12);
				mBoardService.userPasswordUpdateService(authUrlVO.getUid(), secu.encryptSHA256(tempPassword));
				mBoardService.authUrlDeleteService(url);
				email.sendSimpleMessage(mBoardService.userDetailService(authUrlVO.getUid()).getEmail(), "이메일 인증입니다.", "이 메세지는 제삼웹서버끄래낑조로부터 시작됬으며 임시 패스워드는 다음과 같습니다. "+tempPassword);
				model.addAttribute("msg", "계정의 임시 비밀번호가 해당 메일로 발송되었습니다.");
				model.addAttribute("url", "/main");
			}
		}else {  // url 이 없었다. 
			model.addAttribute("msg", "잘못된 경로입니다.");
			model.addAttribute("url", "/error");
		}
		
		
		return "redirect";
	}
	
	
	
	/*										20190409	최종													*/
	/*XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX*/
	/*#####################################################회원정보 수정던지기 #########################################################*/
	@RequestMapping("/user/modify")
	private String userModifyPage(HttpServletRequest request, Model model)throws Exception{
		Klog.getInstance().log(request, request.getRequestURI() + request.getQueryString() + " request");
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
		
		return "/userx/modifyuser";
	}
	
	
	/*XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX*/
	/*#####################################################회원정보 수정진행#########################################################*/
	@RequestMapping("/user/modifyProc")
	private String userModifyProc(HttpServletRequest request,@RequestPart MultipartFile files, Model model)throws Exception{
		Klog.getInstance().log(request, request.getRequestURI() + request.getQueryString() + " request");
		UserVO userVO = new UserVO();
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
				if(secu.isGoodToUse(request.getParameter("upassword"), SecurityUtil.CHECK_CODE_PASSWORD) == false) {
					model.addAttribute("msg", "비밀번호 칸이 비어있음. 뭐라도 넣어야함.");
					model.addAttribute("url", "/user/modify");
					return "redirect";
				}
				
				if(files.isEmpty()) {
					userVO.setThumbnailname(mBoardService.userDetailService(usersession.getUid()).getThumbnailname());
					userVO.setUid(usersession.getUid());
					userVO.setUname(secu.replaceXssString(request.getParameter("uname")));
					userVO.setUpassword(secu.encryptSHA256(request.getParameter("upassword")));
					
					mBoardService.userUpdateService(userVO);
				}else {
					
					
					
					String sourceFileName = files.getOriginalFilename();//원본 파일 네임 **.jpg
					String fileExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase(); // 확장명  저장 
					if(secu.isImageExtension(fileExtension) == false) {
						model.addAttribute("msg", "썸네일에 JPG나 PNG만 설정해주세요");
						model.addAttribute("url", "/user/modify");
						return "redirect";
					}
					File destinationFile; // 목적지 최종 파일이 있을 객체 
					String destinationFileName;
					String fileUrl=staticX+"userthumnail/";
					
					do {
						destinationFileName = RandomStringUtils.randomAlphanumeric(32)+"."+fileExtension;
						destinationFile = new File(fileUrl+destinationFileName);
					}while(destinationFile.exists()); // 만약에 중복되면 다시 
					
					destinationFile.getParentFile().mkdir();
					files.transferTo(destinationFile); // 임시파일 files를 detinationFile 로 이동 
					
					BufferedImage img = ImageIO.read(destinationFile); // 이미지 리사이징을 위해 이미지 로드 
					BufferedImage thumbimg = Scalr.resize(img, Method.QUALITY, Mode.AUTOMATIC, 2000, 2000, Scalr.OP_ANTIALIAS); // 리사이즈 
					File f2 = new File(fileUrl+destinationFileName);
					if(fileExtension.equals("jpg"))
						ImageIO.write(thumbimg, "jpg", f2); // 덮어쓰기 
					else if(fileExtension.equals("jpg"))
						ImageIO.write(thumbimg, "png", f2); // 덮어쓰기 
						
					
					userVO.setThumbnailname(destinationFileName);
					userVO.setUid(usersession.getUid());
					userVO.setUname(secu.replaceXssString(request.getParameter("uname")));
					userVO.setUpassword(secu.encryptSHA256(request.getParameter("upassword")));
					
					mBoardService.userUpdateService(userVO);
				}
			
				
			httpsession.invalidate();
			model.addAttribute("msg", "다시 로그인 하세요");
			model.addAttribute("url", "/user/login");
			return "redirect";
				
	}
	
	
	/*										20190409	최종													*/
	/*XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX*/
	/*########################################################로그아웃######################################################*/
	@RequestMapping("/user/logout")//로그아웃 컨트롤
	private String userLogout(HttpServletRequest request, Model model)throws Exception{
		Klog.getInstance().log(request, request.getRequestURI() + request.getQueryString() + " request");
		HttpSession httpsession = request.getSession(false);
		
		httpsession.invalidate();
		model.addAttribute("msg", "로그아웃됨");
		model.addAttribute("url", "/main");
		return "redirect";
	}
	
	
	
	/*										20190409	최종															*/
	/*XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX*/
	/*#######################################################로그인 페이지 던지기################################################*/
	@RequestMapping("/user/login")
	private String userLogin(HttpServletRequest request, Model model)throws Exception{
		Klog.getInstance().log(request, request.getRequestURI() + request.getQueryString() + " request");
		HttpSession httpsession = request.getSession(false); // 세션 체크.. 실전엔 false로 
		try {
			if(!(httpsession.getAttribute("USER")==null))  {
				model.addAttribute("msg", "세션이 이미 있음");
				model.addAttribute("url", "/welcome");
				return "redirect";
			}
		}catch(NullPointerException e) {
			
			return "/boardx/main";
		}
		
		
		return "/userx/login";
	}
	
	
	
	
	/*										20190409	최종													*/
	/*XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX*/
	/*##################################################로그인 진행###########################################################*/
	@RequestMapping("/user/loginProc")
	private String userLoginCheck(HttpServletRequest request, Model model)throws Exception{
		Klog.getInstance().log(request, request.getRequestURI() + request.getQueryString() + " request");
		HttpSession httpsessionc = request.getSession(false); // 세션 체크.. 실전엔 false로 
		try {
			if(!(httpsessionc.getAttribute("USER")==null))  {
				model.addAttribute("msg", "세션이 이미 있음");
				model.addAttribute("url", "/welcome");
				return "redirect";
			}
		}catch(NullPointerException e) {
			
			return "/boardx/main";
		}
		
		UserVO user = new UserVO();
		UserSessionVO usersession = new UserSessionVO();

		user.setUid(request.getParameter("uid"));
		user.setUpassword(secu.encryptSHA256(request.getParameter("upassword")));
		
		
		
		
		
		if(mBoardService.userLoginCheckService(user) == true) {
			
			/*create sessions*/
			usersession.setUid(user.getUid());
			usersession.setUpassword(user.getUpassword());
			usersession.setAuth(mBoardService.userDetailService(user.getUid()).getAuth());
			usersession.setUname(mBoardService.userDetailService(user.getUid()).getUname());
			HttpSession httpsession = request.getSession(true); // bring request.session
			httpsession.setAttribute("USER", usersession); // bind usersession into httpsession
			mBoardService.addJsessionByUidService(httpsession.getId(), user.getUid());
		
			model.addAttribute("msg", "로그인에 성공했어요~");
			model.addAttribute("url", "/welcome");
			return "redirect";
		}else {
			model.addAttribute("msg", "로그인에 실패했어요~");
			model.addAttribute("url", "/user/login");
			return "redirect";
		}
	}
	
	
	/*										20190409	최종													*/
	/*XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX*/
	/*####################################################회원가입 페이지 던지기#################################################*/
	@RequestMapping("/join_new") // calling user sign form
	private String userSign() throws Exception{
		
		return "/userx/signup";
	}
	
	
	/*										20190409	최종													*/
	/*XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX*/
	/*#################################################회원가입 진행############################################################*/
	@RequestMapping("/join_check")
	private String userSignProc(HttpServletRequest request, Model model)throws Exception{
		Klog.getInstance().log(request, request.getRequestURI() + request.getQueryString() + " request");
		if("".equals(request.getParameter("uid")) || "".equals(request.getParameter("upassword")) || "".equals(request.getParameter("uname")) || "".equals(request.getParameter("email"))) {
			model.addAttribute("msg", "Null 오류");
			model.addAttribute("url", "/join_new");
			return "redirect";
		}
		
		
		UserVO user = new UserVO();
		user.setUid(request.getParameter("uid").trim()); 
		user.setUpassword(secu.encryptSHA256(request.getParameter("upassword")));
		user.setUname(request.getParameter("uname"));
		user.setEmail(request.getParameter("email"));
		user.setThumbnailname("default.jpg");
		
		if(mBoardService.userIdCheckService(user.getUid())) {
			//sign proc success
			mBoardService.userInsertService(user);
			model.addAttribute("msg", "회원가입이 성공적이에요~");
			model.addAttribute("url", "/user/login");
			return "redirect";
		}else {
			//sign proc failure
			model.addAttribute("msg", "회원가입에 실패했어요~	 다시 작성하세요~");
			model.addAttribute("url", "/join_new");
			return "redirect";
		}
	}
	
	@RequestMapping("/user/choiceidpw")
	private String choiceIdPw()throws Exception{
		return "/userx/choiceidpw";
	}
	@RequestMapping("/user/findid")
	private String findId()throws Exception{
		return "/userx/findid";
	}
	@RequestMapping("/user/findpw")
	private String findPw()throws Exception{
		return "/userx/findpw";
	}
	
	@RequestMapping("/user/findidProc")
	private String findIdProc(HttpServletRequest request, Model model)throws Exception{
		// 메일로 아이디 목록 주기 
		String email = request.getParameter("email");
		if(email.isEmpty()) {
			model.addAttribute("msg", "이메일을 정확히 입력하세요,, 손모가지 날아가붕게..");
			model.addAttribute("url", "/user/findid");
			return "redirect";
		}
		List<String> list;
		list = mBoardService.findUidByEmailService(email);
		// 메일 일치하는 아이디 목록 메일로 보내기 
		if(list.isEmpty()) {
			model.addAttribute("msg", "해당 아이디가 없습니다.");
			model.addAttribute("url", "/user/findid");
			return "redirect";
		}
		String uidMsg = "이 메세지는 제삼웹서버끄래낑조로부터 시작됬으며 아이디는 다음과 같습니다. ";
		String[] uidList = list.toArray(new String[list.size()]);
		for(int i = 0 ; i < list.size(); i++) {
			uidMsg = uidMsg.concat(uidList[i]);
			uidMsg = uidMsg.concat(" :: ");
		}
		EmailServiceImpl emailSender = new EmailServiceImpl();
		emailSender.sendSimpleMessage(email, "이메일 인증입니다.", uidMsg);
		// 이메일 발신후 메인으로 리다이렉트 있으면 메일 보내고 없으면 안보내고  
		model.addAttribute("msg", "메일이 해당 계정으로 발송되었습니다.");
		model.addAttribute("url", "/user/login");
		return "redirect";
	}
	
	
}
