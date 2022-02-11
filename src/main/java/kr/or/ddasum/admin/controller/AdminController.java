package kr.or.ddasum.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddasum.admin.model.service.AdminService;
import kr.or.ddasum.admin.model.vo.AdminMember;

@Controller
public class AdminController {
		
	@Autowired
	private AdminService admService;
	
	@RequestMapping(value="/admin/adminMain.do", method = RequestMethod.GET)
	public String adminMain() {
				
		
		return "admin/adminMain";
		
	}
	
	//회원정보관리 페이징처리
	@RequestMapping(value="/admin/adminMemberManageList.do", method = RequestMethod.GET)
	public ModelAndView adminSelectAllMember(@RequestParam(defaultValue ="1") int currentPage, HttpServletRequest request, ModelAndView mav){
		
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		int pageTotalCount = (int)Math.ceil(admService.memberTotalCount()/(double)recordCountPerPage);
		int startNavi = currentPage - (currentPage - 1) % naviCountPerPage;
		int endNavi = startNavi + naviCountPerPage - 1;
		endNavi = endNavi > pageTotalCount ? pageTotalCount : endNavi;
		
		ArrayList<AdminMember> list = admService.adminSelectAllMember(currentPage, recordCountPerPage);
		ArrayList<Integer> navi = new ArrayList<>();
		for (int i = startNavi; i <= endNavi; i++) {
			navi.add(i);
		}
		
		mav.addObject("list", list);
		mav.addObject("currentPage", currentPage);
		mav.addObject("navi", navi);
		mav.addObject("preNavi", startNavi > 1 ? startNavi - 1 : 0 );
		mav.addObject("nextNavi", pageTotalCount > endNavi ? endNavi + 1 : 0 );
		mav.setViewName("admin/adminMemberManageList");
		
		return mav;		
		
	}
	
//	@RequestMapping(value="/admin/adminMemberManageList.do", method = RequestMethod.GET)
//	public String adminMemberManageList() {
//		
//		return "/admin/adminMemberManageList";
//	}

	//이용내역 확인
	@RequestMapping(value="/admin/adminUserDetail.do", method = RequestMethod.GET)
	public ModelAndView adminUserDetail(HttpServletRequest request, ModelAndView mav, @RequestParam int userNo) {
		
		int currentPage;
		if(request.getParameter("currentPage") == null) {
			currentPage = 1;
		}else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		int recordCountPerPage = 10;
		
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
				
		HashMap<String,Object> map = admService.userDetail(currentPage, userNo);
		
		
		map.put("currentPage", currentPage);
		map.put("userNo", userNo);
		
		mav.addObject("map", map);
		mav.setViewName("admin/adminUserDetail");

		return mav;
				
	}
	
	@RequestMapping(value="/admin/adminBizManageList.do", method = RequestMethod.GET)
	public String adminBizManageList() {
		return "/admin/adminBizManageList";
	}
	
	@RequestMapping(value="/admin/adminNoticeManageList.do", method = RequestMethod.GET)
	public String adminNoticeManageList() {
		return "/admin/adminNoticeManageList";
	}
	
	
	@RequestMapping(value="/admin/adminSupport.do", method = RequestMethod.GET)
	public String adminSupport() {
		return "/admin/adminSupport";
	}
	
	@RequestMapping(value="/admin/adminCardManageList.do", method = RequestMethod.GET)
	public String adminCardManageList() {
		return "/admin/adminCardManageList";
	}
	
	@RequestMapping(value="/admin/adminFAQManageList.do", method = RequestMethod.GET)
	public String adminFAQManageList() {
		return "/admin/adminFAQManageList";
	}
	
	@RequestMapping(value="/admin/adminNoticeWrite.do", method = RequestMethod.GET)
	public String adminNoticeWrite() {
		return "/admin/adminNoticeWrite";
	}	
	
	@RequestMapping(value="/admin/adminCardConfirm.do", method = RequestMethod.GET)
	public String adminCardConfirm() {
		return "/admin/adminCardConfirm";
	}
	
	@RequestMapping(value="/member/storyBoard.do", method = RequestMethod.GET)
	public String stroyBoard() {
		return "/member/storyBoard";
	}
	
}
