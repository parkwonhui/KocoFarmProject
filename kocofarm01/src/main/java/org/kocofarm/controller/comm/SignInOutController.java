package org.kocofarm.controller.comm;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.kocofarm.domain.comm.LoginVO;
import org.kocofarm.service.comm.SignInOutService;
import org.kocofarm.util.SHA256Util;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/signIn/*")
@AllArgsConstructor
public class SignInOutController {
	
	private SignInOutService signInOutService;
	
	@GetMapping("/page")
	public String signInPage(){

		return "comm/sign_in";
	}
	
	@PostMapping("/in")
	public String signIn(HttpSession session, @RequestParam HashMap<String, String> paramMap, Model model){
		String userId = paramMap.get("userId").toUpperCase();
		
		LoginVO loginVO = signInOutService.getLoginUserInfo(userId);
		int stateNum = -1;
		String myPage = "'/signIn/page'";
		
		if(null != loginVO){
			String inputPw = SHA256Util.getEncrypt(paramMap.get("userPw"), loginVO.getSalt());
			String userPw = loginVO.getPw();
			
			// 비밀 번호 일치 여부 re > -1: 불 일치 / 1: 일치
			if(!inputPw.equals(userPw)){
				stateNum = -2;
			}else{
				stateNum = 1;
				session.setAttribute("loginVO", loginVO);
				myPage = "'/main'";
			}
		}
		
		model.addAttribute("module", "signIn");
		model.addAttribute("re", stateNum);
		model.addAttribute("myPage", myPage);
		
		return "comm/proC";
	}
	
	@GetMapping("/out")
	public String signOut(HttpSession session, Model model){
		session.invalidate();
		
		model.addAttribute("module", "signIn");
		model.addAttribute("re", 1);
		model.addAttribute("myPage", "'/main'");
		
		return "comm/proC";
	}
	
}
