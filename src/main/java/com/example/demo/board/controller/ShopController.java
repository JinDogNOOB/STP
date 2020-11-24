package com.example.demo.board.controller;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.board.domain.InventoryVO;
import com.example.demo.board.domain.ItemVO;
import com.example.demo.board.domain.UserSessionVO;
import com.example.demo.board.domain.UserVO;
import com.example.demo.board.service.BoardService;
import com.example.demo.board.service.ShopService;
import com.example.demo.util.Klog;
import com.example.demo.util.SecurityUtil;


/*
 * 상점페이지 콘트롤러 (세션체크, 권한체크, )
 * 	- 상점 메인 
 *  - 
 * 상점 서비스 (구매 관련 ) 
 * 
 * 
 * 
 * */


@Controller
public class ShopController {

		@Resource(name="com.example.demo.board.service.BoardService")
		BoardService mBoardService;
		@Resource(name="com.example.demo.board.service.ShopService")
		ShopService mShopService;
		SecurityUtil secu = new SecurityUtil();

		String uploadFilesX="/var/lib/tomcat8/webapps/ROOT/WEB-INF/uploadFiles/";
		String staticX = "/var/lib/tomcat8/webapps/ROOT/WEB-INF/classes/static/";
		
		//#################################상품 구매 진행##################################################3
		@RequestMapping("/board/shop/buy")
		public String shopBuy(HttpServletRequest request, Model model)throws Exception{
			Klog.getInstance().log(request, request.getRequestURI() + request.getQueryString() + " insert");
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
			
			ItemVO itemVO = new ItemVO(); 
			InventoryVO inventoryVO = new InventoryVO(); // 유저인벤토리용 공간 
			
			String shopId = request.getParameter("shopId")==null?"":request.getParameter("shopId");
			int sno = Integer.parseInt(request.getParameter("sno")==null?"0":request.getParameter("sno"));
			itemVO.setShopId(shopId.toUpperCase());
			itemVO.setSno(sno);
			ItemVO dbItemVO = mShopService.getOneShopItemService(itemVO); // 분명히 얕은 복사일텐데...
			
			// 돈 비교 
			if(dbUserVO.getMoney() < dbItemVO.getPrice()) { // 돈이 부족하면
				model.addAttribute("msg", "포인트를 더 버세요. "+(-(dbUserVO.getMoney()-dbItemVO.getPrice()))+"원이 더 필요합니다.");
				model.addAttribute("url", "/board/shop/lists?shopId="+shopId);
				return "redirect";
			}
			// 돈이 충분하면 
			
			inventoryVO.setUno(dbUserVO.getUno());
			inventoryVO.setPrice(dbItemVO.getPrice());
			inventoryVO.setItemName(dbItemVO.getItemName());
			inventoryVO.setImgName(dbItemVO.getImgName());
			inventoryVO.setShopId(shopId);
			
			mShopService.addIventoryItemService(inventoryVO); // 인벤토리에 저장하고 
			mBoardService.addMoneyService(dbUserVO.getUid(), -(dbItemVO.getPrice())); // 돈 빼고 
			
			model.addAttribute("msg", "아이템을 구매하셨습니다. 인벤토리를 확인해주세요");
			model.addAttribute("url", "/board/shop/lists?shopId="+shopId);
			return "redirect";
		}
		
		
		//#############################상점 메인############################################################3
		@RequestMapping("/board/shop")
		public String shopMain(HttpServletRequest request, Model model)throws Exception {
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
				
			
			
			
			return "/shopx/shopmain";
		}
		
		
	
		
		//################################상점 물건 리스트######################################################
		@RequestMapping("/board/shop/lists")
		public String shopList(HttpServletRequest request, Model model)throws Exception {
			Klog.getInstance().log(request, request.getRequestURI() + request.getQueryString() + " request");
			String shopId = request.getParameter("shopId");
			ItemVO itemVO = new ItemVO();
			
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
			
			itemVO.setShopId(shopId.toUpperCase());
			
			model.addAttribute("shopId", shopId);
			model.addAttribute("itemLists", mShopService.getShopItemService(itemVO));
			// 샵 아이디를 매개변수로 하는 mShopService 
			
			return "/shopx/items";
		}
		
		//#################################상점 물건 삽입########################################################
		@RequestMapping("/board/shop/insert")
		public String shopInsert(HttpServletRequest request, Model model)throws Exception{
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
			if(dbUserVO.getAuth() < 99){
				model.addAttribute("msg", "관리자만 상품을 추가할수있습니다.");
				model.addAttribute("url", "/user/info/mypage");
				return "redirect";
			} //권한체크끝
			
			String shopId = request.getParameter("shopId");
			model.addAttribute("shopId", shopId);
			return "/shopx/iteminsert";
		}
		
		//#################################상점 물건 삽입진행########################################################
		@RequestMapping("/board/shop/insertProc")
		public String shopInsertProc(HttpServletRequest request, @RequestPart MultipartFile files, Model model)throws Exception{
			Klog.getInstance().log(request, request.getRequestURI() + request.getQueryString() + " insert");
			ItemVO itemVO = new ItemVO();
			int price = Integer.parseInt(request.getParameter("price"));
			String itemName = request.getParameter("itemname");
			String shopId = request.getParameter("shopId");
			String imgName;
			
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
				model.addAttribute("msg", "관리자만 상품을 추가할수있습니다.");
				model.addAttribute("url", "/user/info/mypage");
				return "redirect";
			} //권한체크끝
			
			if(files.isEmpty()) {// if file is not exist
				// if files is empty
				imgName = "default.jpg";
			}else {
				String sourceFileName = files.getOriginalFilename();
				String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();//확장자 소문자로 받자.
				File destinationFile; //목적지 최종 파일이 있을 객체.
				String destinationFileName; // 곧 랜덤 글자로 채워질 스트링 객체;
				String fileUrl=staticX+"shopitem/";
			
			
				do {
					destinationFileName = RandomStringUtils.randomAlphanumeric(32)+"."+sourceFileNameExtension;
					destinationFile = new File(fileUrl + destinationFileName);
				}while(destinationFile.exists());// 만약에 파일이 존재하면 그니까 만약에. 중복되면 다시 랜덤 돌린다.
			
				destinationFile.getParentFile().mkdirs();
				files.transferTo(destinationFile); // 임시파일을 아까 목적지 경로로 이동시킨다.
				imgName = destinationFileName;
			}
			itemVO.setPrice(price);
			itemVO.setItemName(itemName);
			itemVO.setShopId(shopId.toUpperCase());
			itemVO.setImgName(imgName);			
			
			mShopService.addShopItemService(itemVO);
			
			return "/shopx/shopmain";
		}
				
		
		//##################################상점 물건 삭제#########################################################
		@RequestMapping("/board/shop/delete")
		public String shopDelete(HttpServletRequest request, Model model)throws Exception{
			
			
			
			return "/error";
		}
		
		
}
