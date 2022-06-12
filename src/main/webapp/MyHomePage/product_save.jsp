<%@page import="product.ProductDTO"%>
<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ page language="java" import="java.sql.*,java.util.*,java.text.*,java.io.*" %> 
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<% request.setCharacterEncoding("UTF-8"); %>

<%


	String fileurl = application.getRealPath("/products");
	String saveFolder = "./images/products";
	ServletContext context = getServletContext();
	String realFolder = context.getRealPath(saveFolder);
	
	//out.print("realFolder : -->" + realFolder);
	
	int maxsize = 5*1024*1024*1024; //사이즈
	String enctype = "UTF-8";
	
	MultipartRequest multi = new MultipartRequest (request,fileurl,maxsize,enctype, new DefaultFileRenamePolicy());
	
	String pc = multi.getParameter("p_id");
	String cat= multi.getParameter("category");
	String wn = multi.getParameter("wname");
	String pn = multi.getParameter("pname");
	String sn = multi.getParameter("sname");
	int price = Integer.parseInt(multi.getParameter("price"));
	int dprice = Integer.parseInt(multi.getParameter("dprice"));
	int stock = Integer.parseInt(multi.getParameter("stock"));
	String filename = multi.getFilesystemName("pImg");
	String des = multi.getParameter("description");
	String flag = multi.getParameter("flag");
	File file = multi.getFile("pImg");
	 
	//out.println(wn + " " + cat + " " + pn + " " +sn + "  " + price + " " + dprice + " " + stock + " " + des);
	//if (true) return; // 프로그램 멈춤
	
	multi.getFile("../images/products");
	ProductDTO p = new ProductDTO();
	p.setP_id(pc);
	p.setCategory(cat);
	p.setWname(wn);
	p.setSname(sn);
	p.setPrice(price);
	p.setDownprice(dprice);
	p.setStock(stock);
	p.setpImg(filename);
	
	 
%>
