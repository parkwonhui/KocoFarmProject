<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="org.kocofarm.domain.schedule.ScheduleCalenderListVO" %>
<%@page import="java.util.List"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%!
	List<ScheduleCalenderListVO> category_list;
%>
<% 
	category_list = (List<ScheduleCalenderListVO>)request.getAttribute("calenderList");
	out.println(JSONArray.fromObject(category_list).toString());
%>

