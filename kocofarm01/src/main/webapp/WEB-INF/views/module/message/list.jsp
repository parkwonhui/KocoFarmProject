<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<jsp:include page="/WEB-INF/views/comm/top.jsp" flush="false"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="/resources/css/module/message.css" />

<div class="cont_wrap">
	<!-- SubTitle Area -->
	<div class="sub_title">
		<div class="sub_title_top">
			<div class="sub_title_inner">
				<h2>
					Message <span>사원들과 메신저로 대화를 나눌 수 있습니다</span>
				</h2>
				<ul class="sub_nav">
					<li>홈</li>
					<li class="on">메시지 확인</li>
				</ul>
			</div>
		</div>
	</div>

	<!-- Contents Area -->
	<div class="contents_wrap">
		<!-- Modal -->
	<div class="modal fade" id="add-message-room" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">메시지 방 생성</h4>
				</div>
				<div class="modal-body">
					<p>방 제목</p>
					<input type = "text" id="add-message-room-title" />
					<div id="emp-list" ></div>
				</div>
				<div class="modal-footer">
					<button type="button" id="add-message-room-request" class="btn btn-primary" data-dismiss="modal">생성</button>
				</div>
			</div>

		</div>
	</div>
	
		<!-- list -->
		<div class="contents">
			<div class="messaging">
				<div class="inbox_msg">
					<div class="inbox_people">
						<div class="headind_srch">
							<div class="recent_heading">
								<h4>Recent</h4>
							</div>
							<div class="srch_bar">
								<div class="stylish-input-group">
									<input type="text" class="search-bar" placeholder="Search">
									<span class="input-group-addon">
										<button type="button">
											<i class="fa fa-search" aria-hidden="true"></i>
										</button>
									</span>
								</div>
							</div>
						</div>
						<div>
						<button type="button" id="add-message-room-button" class="btn btn-info btn-lg btn-block" data-toggle="modal" data-target="#add-message-room">방 만들기</button>
						</div>
						<div class="inbox_chat">
						
						</div>
					</div>
					<div class="mesgs">
						<div class="msg_history">
							<div class="incoming_msg">
								<div class="incoming_msg_img">
									<img src="https://ptetutorials.com/images/user-profile.png"
										alt="sunil">
								</div>
								<div class="received_msg">
									<div class="received_withd_msg">
										<p>Test which is a new approach to have all solutions</p>
										<span class="time_date"> 11:01 AM | June 9</span>
									</div>
								</div>
							</div>
							<div class="outgoing_msg">
								<div class="sent_msg">
									<p>Test which is a new approach to have all solutions</p>
									<span class="time_date"> 11:01 AM | June 9</span>
								</div>
							</div>
							<div class="incoming_msg">
								<div class="incoming_msg_img">
									<img src="https://ptetutorials.com/images/user-profile.png"
										alt="sunil">
								</div>
								<div class="received_msg">
									<div class="received_withd_msg">
										<p>Test, which is a new approach to have</p>
										<span class="time_date"> 11:01 AM | Yesterday</span>
									</div>
								</div>
							</div>
							<div class="outgoing_msg">
								<div class="sent_msg">
									<p>Apollo University, Delhi, India Test</p>
									<span class="time_date"> 11:01 AM | Today</span>
								</div>
							</div>
							<div class="incoming_msg">
								<div class="incoming_msg_img">
									<img src="https://ptetutorials.com/images/user-profile.png"
										alt="sunil">
								</div>
								<div class="received_msg">
									<div class="received_withd_msg">
										<p>We work directly with our designers and suppliers, and
											sell direct to you, which means quality, exclusive products,
											at a price anyone can afford.</p>
										<span class="time_date"> 11:01 AM | Today</span>
									</div>
								</div>
							</div>
						</div>
						<div class="type_msg">
							<div class="input_msg_write">
								<input type="text" class="write_msg"
									placeholder="Type a message" />
								<button class="msg_send_btn" type="button">
									<i class="fa fa-paper-plane-o" aria-hidden="true"></i>
								</button>
							</div>
						</div>
					</div>
				</div>

			</div>

		</div>
	</div>
	<script type="text/javascript" src="/resources/js/module/message.js"></script>
	<jsp:include page="/WEB-INF/views/comm/bottom.jsp" flush="false"></jsp:include>