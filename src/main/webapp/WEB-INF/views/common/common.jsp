<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page trimDirectiveWhitespaces="true" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" scope="page"/>

<c:set var="company_name" value="" scope="page"/>
<c:set var="system_name" value="" scope="page"/>
<c:set var="keywords" value="" scope="page"/>
<c:set var="copyright" value="Copyright © 2017.  " scope="page"/>


<% response.setHeader("Cache-Control","no-cache");%>
<% response.setHeader("Pragma","No-cache");%>
<% response.setDateHeader ("Expires",-1); %>