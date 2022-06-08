<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ page language="java" import="java.sql.*,java.util.*,java.text.*" %> 
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<% request.setCharacterEncoding("UTF-8"); %>

<%


	String fileurl = application.getRealPath("images/products");
	String saveFolder = "images/products";
	ServletContext context = getServletContext();
	String realFolder = context.getRealPath(saveFolder);
	
	out.print("realFolder : -->" + realFolder);
	
	int maxsize = 5*1024*1024*1024; //사이즈
	String enctype = "UTF-8";
	
	MultipartRequest multi = new MultipartRequest (request,fileurl,maxsize,enctype, new DefaultFileRenamePolicy());
	
	String wn = multi.getParameter("wname");
	String cat= multi.getParameter("category");
	String pn = multi.getParameter("pname");
	String sn = multi.getParameter("sname");
	int price = Integer.parseInt(multi.getParameter("price"));
	int dprice = Integer.parseInt(multi.getParameter("dprice"));
	int stock = Integer.parseInt(multi.getParameter("stock"));
	String des = multi.getParameter("description");
	 
	out.println(wn + " " + cat + " " + pn + " " +sn + "  " + price + " " + dprice + " " + stock + " " + des);
	 if (true) return; // 프로그램 멈춤

%>
