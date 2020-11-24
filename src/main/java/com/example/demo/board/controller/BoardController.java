package com.example.demo.board.controller;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//file upload download
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.board.domain.BoardVO;
import com.example.demo.board.domain.FileVO;
import com.example.demo.board.domain.UserSessionVO;
import com.example.demo.board.domain.UserVO;
import com.example.demo.board.service.BoardService;

import com.example.demo.util.EmailServiceImpl;
import com.example.demo.util.Klog;
import com.example.demo.util.Pagination;
import com.example.demo.util.SecurityUtil;





@Controller
public class BoardController {
	@Resource(name="com.example.demo.board.service.BoardService")
	BoardService mBoardService;
	
	SecurityUtil secu = new SecurityUtil();
	
	String uploadFilesX="/var/lib/tomcat8/webapps/ROOT/WEB-INF/uploadFiles/";
	String staticX = "/var/lib/tomcat8/webapps/ROOT/WEB-INF/classes/static/";
	
	// 세션은 컨트롤러 단에서 서비스는 최대한 서비스단에서 해결하
	
	/*###################################################테스트 공간################################################################*/
	@RequestMapping("/") // 정적리소스 테스트 
	private String imageTest(Model model)throws Exception{
		return "/userx/login";
	}
	
	

	
	
	
	
	/*XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX*/
	/*#######################################################메인페이지리턴##############################################*/
	@RequestMapping("/main")
	private String mainPage(HttpServletRequest request, Model model)throws Exception{
		
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
		
		return "/boardx/main";
	}
	
	/*XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX*/
	/*#######################################################로그인성공 welcome##################################*/
	@RequestMapping("/welcome")
	private String welcomePage(HttpServletRequest request, Model model)throws Exception{
		Klog.getInstance().log(request, request.getRequestURI() + request.getQueryString() + " request");
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
		
		
		model.addAttribute("thumbnailname", dbUserVO.getThumbnailname());
		model.addAttribute("session", usersession); // 아이디 암호 이름 auth
		model.addAttribute("money", mBoardService.userDetailService(usersession.getUid()).getMoney()); // 돈정보 
		
		
		return "/boardx/welcome";
	}
		
	
	/*###########################################################list/1 리다이렉트#######################################*/
	/*
	@RequestMapping("/board/view/?id={boardName}")
	private String boardListD(Model model)throws Exception {
		Pagination pagination = new Pagination(1, mBoardService.boardIndexCountService(10));
		model.addAttribute("pagination", pagination);
		return "redirect:/list/1";
	}
	*/ // 잠정 폐기 사유 : 안씀 
	
	/*                                                     20190409 최종                                                           */
	/*###########################################################게시글목록 던지기###################################################*/
	@RequestMapping("/board/lists") // ?id={boardId}&page={boardPage}요청이 들어옵니다...
	private String boardList(HttpServletRequest request, Model model) throws Exception{
		Klog.getInstance().log(request, request.getRequestURI() + request.getQueryString() + " request");
		int boardPage = Integer.parseInt(request.getParameter("page")); // int boardPage에 따라서 페이지 넘긴다.
		String boardName = request.getParameter("id"); // 이 boardId에 따라서 다른 보드 jsp를 리턴하고 다른 정보를 가져옴 조건줘서 하나의 jsp만 만들까.. _허용
		String query = request.getParameter("query")==null?"":request.getParameter("query");
		int row = 10;     /*request.getParameter("row")나중에 체크박스 구현하면서 해보자 */
		
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
		
			if("".equals(query)==false) { // 쿼리문이 있을경우 
				Pagination pagination = new Pagination(boardPage, mBoardService.boardQueryIndexCountService(boardName, row, query));
				model.addAttribute("thumbnailname", dbUserVO.getThumbnailname());
				model.addAttribute("money", dbUserVO.getMoney());
				model.addAttribute("uname", usersession.getUname());
				model.addAttribute("sessionuid", usersession.getUid());
				model.addAttribute("boardName", boardName);
				model.addAttribute("pagination", pagination); //페이징정보 넘긴다.
				model.addAttribute("query", query); // 쿼리정보 넘기기 
				model.addAttribute("list", mBoardService.boardQueryListService(boardName, boardPage, row, query));
				return "/boardx/boardlist";
			}else { // 쿼리문이 없을경우 
			//게시글 전달 모델꾸리기.
				Pagination pagination = new Pagination(boardPage, mBoardService.boardIndexCountService(boardName, row));
				model.addAttribute("thumbnailname", dbUserVO.getThumbnailname());
				model.addAttribute("money", dbUserVO.getMoney());
				model.addAttribute("uname", usersession.getUname());
				model.addAttribute("sessionuid", usersession.getUid());
				model.addAttribute("boardName", boardName);
				model.addAttribute("pagination", pagination); //페이징정보 넘긴다.
				model.addAttribute("query", query); // null 쿼리정보 넘기기 
				model.addAttribute("list",mBoardService.boardListService(boardName, boardPage, row)); //테이블인지도줘야함..
				return "/boardx/boardlist"; //리스트.jsp 떤지기. 이 list
			}
	}
	
	
	/*                                                     20190409 최종                                                       */
	/*###################################################################게시글 상세###########################################*/
	@RequestMapping("/board/view")
	private String boardDetail(HttpServletRequest request, Model model)throws Exception{
		Klog.getInstance().log(request, request.getRequestURI() + request.getQueryString() + " request");
		int bno = Integer.parseInt(request.getParameter("bno"));
		String boardName = request.getParameter("id");
		
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
				if(dbUserVO.getAuth() < 1){
					model.addAttribute("msg", "권한이 없음_이메일 인증을 받으세요");
					model.addAttribute("url", "/user/info/mypage");
					return "redirect";
				} //권한체크끝
				
		model.addAttribute("boardName", boardName);
		model.addAttribute("detail",mBoardService.boardDetailService(boardName, bno));
		model.addAttribute("files", mBoardService.fileDetailService(boardName, bno));
		return "/boardx/boarddetail";
		
	}
	
	/*                                                     20190409 최종                                                       */
	/*##########################################################게시글 쓰기 던지##################################################*/
	@RequestMapping("/board/write") // calling post inserting form
	private String boardInsertForm(HttpServletRequest request, Model model)throws Exception {
		Klog.getInstance().log(request, request.getRequestURI() + request.getQueryString() + " request");
		String boardName = request.getParameter("id");
		
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
		
		if(dbUserVO.getAuth() < 1){
			model.addAttribute("msg", "권한이 없음_이메일 인증을 받으세요");
			model.addAttribute("url", "/user/info/mypage");
			return "redirect";
		} //권한체크끝
		if(boardName.equals("notice") && dbUserVO.getAuth()!=99){
			model.addAttribute("msg", "공지사항은 운영자만 작성할수있습니다.");
			model.addAttribute("url", "/user/info/mypage");
			return "redirect";
		} //권한체크끝
		
		model.addAttribute("boardName", boardName);
		return "/boardx/boardwrite";
	}
	
	/*                                             20190409                                                       */
	/*####################################################게시글 쓰기 진행###########################################################*/
	@RequestMapping("/board/writeProc")
	private String boardInsertProc(HttpServletRequest request, @RequestPart MultipartFile files, Model model)throws Exception{
		String boardName = request.getParameter("id");
		BoardVO board = new BoardVO();
		FileVO file = new FileVO();
		HttpSession session = request.getSession(false);
		UserSessionVO usersessionVO = (UserSessionVO)session.getAttribute("USER");
		
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
				if(dbUserVO.getAuth() < 1){
					model.addAttribute("msg", "권한이 없음_이메일 인증을 받으세요");
					model.addAttribute("url", "/user/info/mypage");
					return "redirect";
				} //권한체크끝
				if(boardName.equals("notice") && dbUserVO.getAuth()!=99){
					model.addAttribute("msg", "공지사항은 운영자만 작성할수있습니다.");
					model.addAttribute("url", "/user/info/mypage");
					return "redirect";
				} //권한체크끝
		
		board.setSubject(secu.replaceXssString(request.getParameter("subject")));
		board.setContent(secu.replaceXssString(request.getParameter("content")));
		board.setUid(usersessionVO.getUid());
		board.setWriter(usersessionVO.getUname());
		
		
		if(files.isEmpty()) {// if file is not exist
			mBoardService.boardInsertService(boardName, board);
			Klog.getInstance().log(request, request.getRequestURI() + request.getQueryString() + " insert");
			
		}else {
			String sourceFileName = files.getOriginalFilename();
			String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();//확장자 소문자로 받자.
			File destinationFile; //목적지 최종 파일이 있을 객체.
			String destinationFileName; // 곧 랜덤 글자로 채워질 스트링 객체;
			String fileUrl=uploadFilesX;
		
		
				do {
					destinationFileName = RandomStringUtils.randomAlphanumeric(32)+"."+sourceFileNameExtension;
					destinationFile = new File(fileUrl + destinationFileName);
				}while(destinationFile.exists());// 만약에 파일이 존재하면 그니까 만약에. 중복되면 다시 랜덤 돌린다.
		
					destinationFile.getParentFile().mkdirs();
					files.transferTo(destinationFile); // 임시파일을 아까 목적지 경로로 이동시킨다.
		
					mBoardService.boardInsertService(boardName, board);
					
					file.setBno(board.getBno());
					file.setFileName(destinationFileName);
					file.setFileOriName(sourceFileName); // 에러가 생길수있는 코드.
					file.setFileUrl(fileUrl);
					file.setBoardName(boardName);
					mBoardService.fileInsertService(file); // db에 fileVO 파일정보 저장 
					Klog.getInstance().log(request, request.getRequestURI() + request.getQueryString() + " insert filename : " + destinationFileName);
		}
		
		mBoardService.addMoneyService(usersessionVO.getUid(), 5);
		return "redirect:/board/lists?id="+boardName+"&page=1";
	}
	
	
	/*                                             20190409                                                       */
	/*#############################################################글 수정폼 던지기#####################################################*/
	@RequestMapping("/board/modify")// are you restful human? no im not a restful man hehe
	private String boardUpdateForm(HttpServletRequest request, Model model)throws Exception{
		Klog.getInstance().log(request, request.getRequestURI() + request.getQueryString() + " request");
		String boardName = request.getParameter("id");
		int bno = Integer.parseInt(request.getParameter("bno"));
		model.addAttribute("detail", mBoardService.boardDetailService(boardName, bno));
		model.addAttribute("boardName", boardName);
		
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
		
		if(dbUserVO.getAuth() < 1){
			model.addAttribute("msg", "권한이 없음_이메일 인증을 받으세요");
			model.addAttribute("url", "/user/info/mypage");
			return "redirect";
		} //권한체크끝
		if(dbUserVO.getUid().equals(mBoardService.boardDetailService(boardName, bno).getUid())==false) {
			model.addAttribute("msg", "소유자가 아님");
			model.addAttribute("url", "/user/info/mypage");
			return "redirect";
		} // 주인체크
		
		
		return "/boardx/boardmodify";
	}
	
	
	/*                                             20190409                                                       */
	/*####################################################글 수정 진행################################################################*/
	@RequestMapping("/board/modifyProc") // calling post modifying Form
	private String boardUpdateProc(HttpServletRequest request, Model model)throws Exception{
		String boardName = request.getParameter("id");
		BoardVO board = new BoardVO();
		board.setSubject(secu.replaceXssString(request.getParameter("subject")));
		board.setContent(secu.replaceXssString(request.getParameter("content")));
		board.setBno(Integer.parseInt(request.getParameter("bno")));
		
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
		if(dbUserVO.getAuth() < 1){
			model.addAttribute("msg", "권한이 없음_이메일 인증을 받으세요");
			model.addAttribute("url", "/user/info/mypage");
			return "redirect";
		} //권한체크끝
		if(dbUserVO.getUid().equals(mBoardService.boardDetailService(boardName, board.getBno()).getUid())==false) {
			model.addAttribute("msg", "소유자가 아님");
			model.addAttribute("url", "/user/info/mypage");
			return "redirect";
		} // 주인체크
		
		mBoardService.boardUpdateService(boardName, board);
		Klog.getInstance().log(request, request.getRequestURI() + request.getQueryString() + " insert");
		return "redirect:/board/view?id="+boardName+"&bno="+request.getParameter("bno");
	}
	
	
	
	/*                                             20190409                                                       */
	/*###############################################게시글 삭제 진행##################################################################*/
	@RequestMapping("/board/delete")
	private String boardDelete(HttpServletRequest request, Model model)throws Exception{
		String boardName = request.getParameter("id");
		int bno = Integer.parseInt(request.getParameter("bno"));
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
		if(dbUserVO.getAuth() < 1){
			model.addAttribute("msg", "권한이 없음_이메일 인증을 받으세요");
			model.addAttribute("url", "/user/info/mypage");
			return "redirect";
		} //권한체크끝
		if(dbUserVO.getUid().equals(mBoardService.boardDetailService(boardName, bno).getUid())==false) {
			model.addAttribute("msg", "소유자가 아님");
			model.addAttribute("url", "/user/info/mypage");
			return "redirect";
		} // 주인체크
		
		
		mBoardService.boardDeleteService(boardName, bno);
		Klog.getInstance().log(request, request.getRequestURI() + request.getQueryString() + " delete");
		
		return "redirect:/board/lists?id="+boardName+"&page=1";
	}
	
	
	/*                                             20190409                                                       */
	/*#################################################파일 다운로드##################################################################*/
	@RequestMapping("/board/fileDown/{boardName}/{bno}")
		private void fileDown(@PathVariable("boardName") String boardName, @PathVariable("bno") int bno, HttpServletRequest request, HttpServletResponse response)throws Exception{

		request.setCharacterEncoding("UTF-8");
		FileVO fileVO = mBoardService.fileDetailService(boardName, bno);
		
		// files path
		try {
			String fileUrl = fileVO.getFileUrl();
			fileUrl += "/";
			String savePath = fileUrl;
			String fileName = fileVO.getFileName();
			
			
			// real file name
			String oriFileName = fileVO.getFileOriName();
			InputStream in = null;
			OutputStream os = null;
			File file = null;
			boolean skip = false;
			String client="";
		
		
				// read the file and put into Stream
			try {
				file = new File(savePath, fileName);
				in = new FileInputStream(file);
			}catch(FileNotFoundException fe) {
				skip = true;
			}
			
			client = request.getHeader("User-Agent");
			
			// write the file download header
			response.reset();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Description", "JSP Generated Data");
			
			if(!skip) {
				// IE
				if(client.indexOf("MSIE")!= -1) {
					response.setHeader("Content-Disposition", "attachment; filename=\""
							+java.net.URLEncoder.encode(oriFileName,"UTF-8").replaceAll("\\+","\\ ")+"\"");
				// higher than IE 11
				}else if(client.indexOf("Trident")!=-1) {
					response.setHeader("Content-Disposition","attachment; filename=\""
							+java.net.URLEncoder.encode(oriFileName,"UTF-8").replaceAll("\\+","\\ ")+"\"");
				// use korean file name 
				}else {
					response.setHeader("Content-Disposition","attachment; filename=\""+ new String(oriFileName.getBytes("UTF-8"),"ISO8859_1")+"\"");
					response.setHeader("Content-Type","application/octet-stream; charset=utf-8");
				}
				response.setHeader("Content-Length",""+file.length());
				os = response.getOutputStream();
				byte b[] = new byte[(int)file.length()];
				int leng = 0;
				while((leng = in.read(b))>0) {
					os.write(b,0,leng);
				}
			}else {
				response.setContentType("text/html;charset=UTF-8");
				System.out.println("<script language='javascript'>alert('can not find files');history.back();</script>");
			}
			in.close();
			os.close();		
		}catch(Exception e) {
			System.out.println("ERROR : " +e.getMessage() );
		}
		
		
	}
	
	
	
	
	
	
	/*
	 
	 
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
			model.addAttribute("url", "/login");
			return "redirect";
		}
		UserVO dbUserVO = mBoardService.userDetailService(usersession.getUid());
		if(false==dbUserVO.getUpassword().equals(usersession.getUpassword())) {
			model.addAttribute("msg", "세션 정보가 잘못됨");
			model.addAttribute("url", "/login");
			return "redirect";
		} //세션체크끝           // 이후 auth 체크는 필요한데만..
		if(dbUser.getAuth() < 1 or 99){
			model.addAttribute("msg", "권한이 없음_이메일 인증을 받으세요");
			model.addAttribute("url", "/user/modify");
			return "redirect";
		} //권한체크끝
		if(false==(dbUserVO.getUpassword().equals(usersession.getUpassword()) && dbUserVO.getJsession().equals(httpsession.getId()))) {
			httpsession.invalidate();
			model.addAttribute("msg", "세션 정보가 잘못됨 or 다른 사용자가 이 아이디로 로그인했음");
			model.addAttribute("url", "/user/login");
			return "redirect";
		} //세션체크끝 
		
	
	
	*/
	

	
}
