package org.kocofarm.controller.module;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.kocofarm.domain.comm.LoginVO;
import org.kocofarm.domain.emp.EmpVO;
import org.kocofarm.domain.message.MessageEmpListVO;
import org.kocofarm.domain.message.MessageRoomListVO;
import org.kocofarm.domain.message.MessageVO;
import org.kocofarm.domain.schedule.ScheduleCalenderMoveVO;
import org.kocofarm.service.module.MessageService;
import org.kocofarm.service.module.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import net.sf.json.JSONObject;

@Log4j
@Controller
@RequestMapping("/message/*")
@AllArgsConstructor
public class MessageController {

	private MessageService service;
	
	public static final class RESULT{
		public static final int UNKNOWN_ERROR = -1;
		public static final int SUCCESS = 1;
	};
	
	@GetMapping("/")
	private String getMessageInfo(HttpSession session, Model model){
		log.info("[message] /");
		
		if(null == session){
			return "/main";
		}
		
		LoginVO loginVO = (LoginVO) session.getAttribute("loginVO");
		if(null == loginVO){
			return "redirect:/main";
		}
		
		model.addAttribute("moduleNm", "schedule");
		model.addAttribute("empId", loginVO.getEmpId());
		return "/module/message/list";
	}

	@ResponseBody
	@GetMapping("/listMessageRoom")
	private List<MessageRoomListVO> getMessageRoomList(HttpSession session){
		log.info("[message] /list");
		
		if(null == session){
			return null;
		}
		
		LoginVO loginVO = (LoginVO)session.getAttribute("loginVO");
		if(null == loginVO){
			return null;
		}
		
		String empId = loginVO.getEmpId();
		if(null == empId){
			return null;
		}
		
		List<MessageRoomListVO> list = service.getMessageRoomList(loginVO.getEmpId());
		log.info(list);
		
		return list;
	}
	
	@ResponseBody
	@GetMapping("/listMessage")
	private List<MessageVO> listMessage(HttpSession session, int roomId){
		if(null == session){
			return null;
		}
		log.info("roomId:"+roomId);
		
		List<MessageVO> list = service.getMessageList(roomId);

		return list;
	}
	
	@ResponseBody
	@GetMapping("/empList")
	private List<MessageEmpListVO> getMessageEmpList(HttpSession session){
		if(null == session){
			return null;
		}
		
		LoginVO loginVO = (LoginVO)session.getAttribute("loginVO");
		if(null == loginVO){
			return null;
		}
		
		List<MessageEmpListVO> list = service.getMessageEmpList();
		return list;
	}
	
	@ResponseBody
	@PostMapping("/addMessageRoom")
	private int setMessageRoom(HttpSession session, @RequestBody String list){
		log.info("/addMessageRoom");
		log.info(list);
		
		JSONObject jsonObject =  JSONObject.fromObject(list);
		if(null == session){
			return RESULT.UNKNOWN_ERROR;
		}
		
		if(null == list){
			return RESULT.UNKNOWN_ERROR;
		}
		
		LoginVO loginVO = (LoginVO)session.getAttribute("loginVO");
		if(null == loginVO){
			return RESULT.UNKNOWN_ERROR;
		}
		
		String title = (String) jsonObject.get("0");		// title
		int lenght = (int)jsonObject.get("1");		// length
		
		List<String> empList = new ArrayList<String>();
		for(int i = 2; i < lenght+2; ++i){
			String empId = (String)jsonObject.get(i+"");
			empList.add(empId);
		}
		
		
		service.setMessageRoom(empList, title);
		return RESULT.SUCCESS;
	}
}
