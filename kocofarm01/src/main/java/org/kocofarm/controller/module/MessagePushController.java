package org.kocofarm.controller.module;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import org.kocofarm.controller.module.MessageController.RESULT;
import org.kocofarm.domain.comm.LoginVO;
import org.kocofarm.domain.emp.EmpVO;
import org.kocofarm.domain.message.MessageEmpListVO;
import org.kocofarm.domain.message.MessagePushVO;
import org.kocofarm.domain.message.MessageRoomListVO;
import org.kocofarm.domain.message.MessageVO;
import org.kocofarm.service.module.EmpService;
import org.kocofarm.service.module.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.org.apache.bcel.internal.generic.PUSH;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import net.sf.json.JSONObject;

@Log4j
@Controller
@RequestMapping("/message/push/*")
@WebServlet(asyncSupported = true)
@AllArgsConstructor
public class MessagePushController {
	
	public static final class MESSAGE_TYPE{
		public static final int TEXT = 0;
		public static final int FILE = 1;
		public static final int EXIT = 2;	// emp 나감
		public static final int INVITE = 3; // emp 초대
	}
	
	public static final class PUSH_TYPE{
		public static final int MESSAGE = 0;	// 메시지 정보 추가
		public static final int MESSAGE_ROOM = 1;	// 메시지 방 정보 추가 
	}
	
	private MessageService service;
	private EmpService empService;
	
	private static List<AsyncContext> contexts = new CopyOnWriteArrayList<AsyncContext>();
	
	@PostMapping("/getMessage")
	public void getMessage(HttpServletRequest request, HttpServletResponse response) {
		log.info("[getRegistry]");
				
		LoginVO loginVo = getLoginVO(request);
		if(null == loginVo){
			return;
		}

		int index = getListIndex(loginVo.getEmpId());
		
		if(-1 != index){
			contexts.remove(index);
		}		
	
		final AsyncContext asyncContext = request.startAsync(request, response);
		asyncContext.setTimeout(10 * 60 * 1000);
		contexts.add(asyncContext);
	}

	@PostMapping("/sendMessage")
	public void sendMessage(HttpServletRequest request, HttpServletResponse response, MessageVO messageVo){
		log.info("[sendMessage]");

		HttpSession session = request.getSession();
		LoginVO loginVO = (LoginVO)session.getAttribute("loginVO");
		if(null == loginVO){
			System.out.println("에러11111");
			return;
		}
		
		String empId = loginVO.getEmpId();
		String name = loginVO.getKorNm();
		
		String message = request.getParameter("message");
		int roomId = messageVo.getMessageRoomId();
		
		log.info(messageVo);

		if(null == empId || null == name ){
			System.out.println("444444");
			return;
		}
				
		if(false == isWrongRequest(empId, roomId)){
			System.out.println("6666666");

			return;
		}

		messageVo.setEmpId(empId);
		messageVo.setKorNm(name);
		String strTime = getCurrentTime();
		messageVo.setDateString(strTime);
		
		service.setMessage(messageVo);
		
		List<String> roomEmpList = service.getMessageRoomEmpList(roomId);

		pushMessage(roomId, loginVO, roomEmpList, messageVo, PUSH_TYPE.MESSAGE,true);
	}
	
	@ResponseBody
	@PostMapping("/delMessagePush")
	private int setMessageRoom(HttpSession session, MessagePushVO messagePushVO){
		
		if(0 == messagePushVO.getMessageRoomId()){
			return RESULT.UNKNOWN_ERROR;
		}
		
		LoginVO loginVO = (LoginVO)session.getAttribute("loginVO");
		if(null == loginVO.getEmpId()){
			return RESULT.UNKNOWN_ERROR;
		}
		
		messagePushVO.setEmpId(loginVO.getEmpId());
		
		// 나갔다는 메시지 생성
		MessageVO messageVo = new MessageVO();
		messageVo.setMessageRoomId(messagePushVO.getMessageRoomId());
		messageVo.setContents(loginVO.getKorNm()+"님이 대화방을 나갔습니다");
		messageVo.setEmpId(loginVO.getEmpId());
		messageVo.setType(MESSAGE_TYPE.EXIT);
		messageVo.setDateString(getCurrentTime());	
		
		if(1 != service.delMessagePush(messagePushVO, messageVo)){
			return RESULT.UNKNOWN_ERROR;
		}
		List<String> roomEmpList = service.getMessageRoomEmpList(messagePushVO.getMessageRoomId()); 
		pushMessage(messagePushVO.getMessageRoomId(), loginVO, roomEmpList, messageVo, PUSH_TYPE.MESSAGE, false);

		
		return RESULT.SUCCESS;
	}
	

	@ResponseBody
	@PostMapping("/inviteMessageRoom")
	public int pushMessage(HttpSession session, @RequestBody String list){
		log.info("[addMessageRoom]");
		log.info(list);
		
		JSONObject jsonObject =  JSONObject.fromObject(list);
		if(null == list){
			return RESULT.UNKNOWN_ERROR;
		}
		
		LoginVO loginVO = (LoginVO)session.getAttribute("loginVO");
		if(null == loginVO){
			return RESULT.UNKNOWN_ERROR;
		}
				
		int roomId = (int)jsonObject.get("0");			// title
		int lenght = (int)jsonObject.get("1");			// length
		
		if(false == isWrongRequest(loginVO.getEmpId(), roomId)){
			return RESULT.UNKNOWN_ERROR;
		}
		
		// 유저 추가 전 기존 방에 있던 유저 정보
		List<String> befoeEmpList = service.getMessageRoomEmpList(roomId);
		List<String> afterEmpList = new ArrayList<String>();
		
		StringBuilder addEmpName = new StringBuilder();
		// 정보 파서
		for(int i = 2; i < lenght+2; ++i){
			String empId = (String)jsonObject.get(i+"");
			afterEmpList.add(empId);
			MessagePushVO messageVo = new MessagePushVO();
			messageVo.setEmpId(empId);
			messageVo.setMessageRoomId(roomId);		
			service.setMessagePush(messageVo);
			EmpVO empVO = empService.getEmp(empId);
		
			if(null == empVO){
				continue;
			}
			
			addEmpName.append(empVO.getKorNm());
			addEmpName.append(", ");
		}
		
		addEmpName.append(" 님을 초대했습니다");
		
		// 방 추가된 사람은 메시지 룸 추가가 필요하다 . 갱신된 메시지 정보를 전달해야한다
		
		// 누군가 들어왔다는 메시지를 저장한다
		// 초대한 사람 empId 저장하자 임의로
	 	MessageVO messageVo = new MessageVO();
	 	messageVo.setEmpId(loginVO.getEmpId());
		messageVo.setKorNm(loginVO.getKorNm());
		messageVo.setContents(addEmpName.toString());
		messageVo.setType(MESSAGE_TYPE.INVITE);
		String strTime = getCurrentTime();
		messageVo.setDateString(strTime);
		service.setMessage(messageVo);
		
		// 기존에 있던 유저들은 누구누구를 초대했다는 메시지가 떠야한다
		pushMessage(roomId, loginVO, befoeEmpList, messageVo, PUSH_TYPE.MESSAGE, true);
		
		return RESULT.SUCCESS;
	}
	
	public void pushMessage(int roomId, LoginVO loginVO, List<String> roomEmpList, MessageVO messageVo,final int pushType,final boolean flag){
		System.out.println("2222222");
		List<AsyncContext> asyncContexts = new CopyOnWriteArrayList<AsyncContext>(this.contexts);
		this.contexts.clear();

		if(null == roomEmpList){
			System.out.println("333333333");

			return;
		}
		
		int i = 0;
		for (AsyncContext asyncContext : asyncContexts) {
			System.out.println("444444444");
			
			LoginVO sendLoginVO = getLoginVO((HttpServletRequest)asyncContext.getRequest());
			if(null == sendLoginVO){
				System.out.println("6666");

				continue;
			}
			
			if(true == flag){
				if(false == isMessageRoomEmp(roomEmpList, sendLoginVO.getEmpId())){
					System.out.println("77777");
	
					continue;
				}
			}
			
			PrintWriter writer;
			try {
				
				asyncContext.getResponse().setContentType("application/json;  charset=UTF-8");
				
				JSONObject obj = null;
				if(PUSH_TYPE.MESSAGE == pushType){
					obj = getMessageTypeObject(roomId, loginVO, messageVo);
				}else{
					
				}
				
				writer = asyncContext.getResponse().getWriter();
				System.out.println("지금 보내느중인뎅.. obj:"+obj);
				writer.println(obj);
				writer.flush();
				asyncContext.complete();
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
		}
	}
	
	public JSONObject getMessageTypeObject(int roomId, LoginVO loginVO, MessageVO messageVo){
		JSONObject obj = new JSONObject();
		obj.put("pushType", PUSH_TYPE.MESSAGE);
		obj.put("korNm", loginVO.getKorNm());
        obj.put("empId", loginVO.getEmpId());
        obj.put("roomId", roomId);
        obj.put("contents", messageVo.getContents());
        obj.put("dateString", messageVo.getDateString());
        obj.put("empImgSrc", messageVo.getEmpImgSrc());
        obj.put("type", messageVo.getType());
		
        return obj;
	}
	
	public JSONObject getMessageRoomTypeObject(MessageRoomListVO roomListVo){
		JSONObject obj = new JSONObject();
		obj.put("pushType", PUSH_TYPE.MESSAGE_ROOM);
		obj.put("messageRoomId", roomListVo.getMessageRoomId());
		obj.put("roomTitle", roomListVo.getRoomTitle());
		obj.put("lastMessageEmpId", roomListVo.getLastMessageEmpId());
		obj.put("lastMessageEmpName", roomListVo.getLastMessageEmpName());
		obj.put("lastMessage", roomListVo.getLastMessage());
		obj.put("lastMessageDate", roomListVo.getLastMessageDate());
		obj.put("lastMessageDateToString", roomListVo.getLastMessageDateToString());		
		
		return obj;
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

        //String strTime = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
        return strTime;
	}
	
	public int getListIndex(String empId){
		int size = contexts.size();
		System.out.println("size:"+size);
		for(int i = 0; i < size; ++i ){
		LoginVO loginVO = getLoginVO((HttpServletRequest)contexts.get(i).getRequest());
			if(true == empId.equals(loginVO.getEmpId())){
				return i;
			}
		}
		return -1;		
	}

	public LoginVO getLoginVO(HttpServletRequest req){
		if(null == req){
			return null;
		}
		
		HttpSession s = req.getSession();
		if(null == s){
			return null;
		}
		
		LoginVO login = (LoginVO)s.getAttribute("loginVO");
		if(null == login){
			return null;
		}
		
		return login;
	}
	
}
