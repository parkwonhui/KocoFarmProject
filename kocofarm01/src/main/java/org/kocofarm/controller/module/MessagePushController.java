package org.kocofarm.controller.module;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.AsyncContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kocofarm.domain.comm.LoginVO;
import org.kocofarm.domain.message.MessagePushVO;
import org.kocofarm.domain.message.MessageVO;
import org.kocofarm.service.module.MessageService;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/message/push/*")
@WebServlet(asyncSupported = true)
@AllArgsConstructor
public class MessagePushController {
	
	private MessageService service;
	
	private static List<AsyncContext> contexts = new CopyOnWriteArrayList<AsyncContext>();
	
	@PostMapping("/getMessage")
	public void getMessage(HttpServletRequest request, HttpServletResponse response) {
		log.info("[getRegistry]");
		
		final AsyncContext asyncContext = request.startAsync(request, response);
		asyncContext.setTimeout(10 * 60 * 1000);
		contexts.add(asyncContext);
		
	}

	@PostMapping("/sendMessage")
	public void sendMessage(HttpServletRequest request, HttpServletResponse response, MessageVO messageVo){
		log.info("[getRegistry]");

		HttpSession session = request.getSession();
		LoginVO loginVO = (LoginVO)session.getAttribute("loginVO");
		if(null == loginVO){
			return;
		}
		
		String empId = loginVO.getEmpId();
		String name = loginVO.getKorNm();
		
		String message = request.getParameter("message");
		int roomId = messageVo.getMessageRoomId();
		
		log.info(messageVo);
		
		if(null == empId || null == name ){
			return;
		}
				
		if(false == isWrongRequest(empId, roomId)){
			return;
		}
		

	
		messageVo.setEmpId(empId);
		messageVo.setKorNm(name);
		String strTime = getCurrentTime();
		messageVo.setDateString(strTime);
		
		service.setMessage(messageVo);

		List<String> roomEmpList = service.getMessageRoomEmpList(roomId);
		if(null == roomEmpList){
			return;
		}
		
		List<AsyncContext> asyncContexts = new CopyOnWriteArrayList<AsyncContext>(this.contexts);
		this.contexts.clear();
		
		int i = 0;
		for (AsyncContext asyncContext : asyncContexts) {
			
			HttpServletRequest req = (HttpServletRequest)asyncContext.getRequest();
			if(null == req){
				continue;
			}
			
			HttpSession s = req.getSession();
			if(null == s){
				continue;
			}
			
			LoginVO login = (LoginVO)s.getAttribute("loginVO");
			if(null == login){
				continue;
			}
			
			if(false == isMessageRoomEmp(roomEmpList, login.getEmpId())){
				continue;
			}
			
			PrintWriter writer;
			try {
				JSONObject obj = new JSONObject();
				asyncContext.getResponse().setContentType("application/json;  charset=UTF-8");
				obj.put("korNm", name);
                obj.put("empId", empId);
                obj.put("roomId", roomId);
                obj.put("contents", messageVo.getContents());
                obj.put("dateString", messageVo.getDateString());
                obj.put("empImgSrc", messageVo.getEmpImgSrc());
				writer = asyncContext.getResponse().getWriter();
				writer.println(obj);
				writer.flush();
				asyncContext.complete();
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
		}
	}
	
	public boolean isWrongRequest(final String empId, final int roomId){
		MessagePushVO messagePushVo = new MessagePushVO();
		messagePushVo.setEmpId(empId);
		messagePushVo.setMessageRoomId(roomId);

		// 메시지방에 유저가 존재하는지 확인
		if(null == service.getEmpMessageRoom(messagePushVo)){
			return false;
		}
		
		return true;
		
	}
	
	public boolean isMessageRoomEmp(List<String> list, String empId){
		for(String id : list){
			if(empId.equals(id)){
				return true;
			}
		}
		
		return false;
	}

	
	public String getCurrentTime(){
		Calendar calendar = Calendar.getInstance();
        java.util.Date date = calendar.getTime();
        String strTime = (new SimpleDateFormat("yyyyMMddHHmmss").format(date));
        return strTime;
	}
	
}
