package org.kocofarm.controller.module;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.kocofarm.domain.comm.LoginVO;
import org.kocofarm.domain.emp.EmpVO;
import org.kocofarm.domain.message.MessageEmpListVO;
import org.kocofarm.domain.message.MessagePushVO;
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
		
		LoginVO loginVO = (LoginVO)session.getAttribute("loginVO");
		if(null == loginVO){
			return null;
		}
		
		String empId = loginVO.getEmpId();
		if(null == empId){
			return null;
		}
		
		List<MessageRoomListVO> list = service.getMessageRoomList(loginVO.getEmpId());
		
		return list;
	}
	
	@ResponseBody
	@GetMapping("/listMessage")
	private List<MessageVO> listMessage(HttpSession session, int roomId){

		List<MessageVO> list = service.getMessageList(roomId);

		return list;
	}
	
	@ResponseBody
	@GetMapping("/empList")
	private List<MessageEmpListVO> getMessageEmpList(HttpSession session){
		
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
		
		JSONObject jsonObject =  JSONObject.fromObject(list);
		if(null == list){
			return RESULT.UNKNOWN_ERROR;
		}
		
		LoginVO loginVO = (LoginVO)session.getAttribute("loginVO");
		if(null == loginVO){
			return RESULT.UNKNOWN_ERROR;
		}
		
		String title = (String) jsonObject.get("0");		// title
		int lenght = (int)jsonObject.get("1");				// length
		
		List<String> empList = new ArrayList<String>();
		for(int i = 2; i < lenght+2; ++i){
			String empId = (String)jsonObject.get(i+"");
			empList.add(empId);
		}
		
		empList.add(loginVO.getEmpId());
		
		service.setMessageRoom(empList, title);
		
		return RESULT.SUCCESS;
	}
	
	@ResponseBody
	@PostMapping("/messageRoomInvite")
	private List<MessageEmpListVO> getMessageRoomInvite(int messageRoomId){
		List<MessageEmpListVO> list = service.getMessageRoomInvite(messageRoomId);
		if(null == list){
			return null;
		}
		
		return list;
	}
	
	@ResponseBody
	@PostMapping("/messageRoomEmpInfoList")
	private List<MessageEmpListVO> getMessageRoomEmpInfoList(int messageRoomId){
		List<MessageEmpListVO> list = service.getMessageRoomEmpInfoList(messageRoomId);
		if(null == list){
			return null;
		}
		
		return list;
	}
		
	public String getCurrentDateToString(){
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");			
		String dateToString = transFormat.format(new Date());
		return dateToString;
	}
}