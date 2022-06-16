package cart;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CartController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String p_img = req.getParameter("pImg");

		HttpSession session = req.getSession(true);
		session.setAttribute("p_img", p_img);
		System.out.println(p_img);
		
		CartDTO dto = new CartDTO();
		CartDAO dao= new CartDAO();
		
		String id = null;
		String p_id = null;
		if(req.getParameter("p_id") != null) {
			p_id = req.getParameter("p_id");
		}
		if(req.getParameter("id") != null) {
			id = req.getParameter("id");
		}
		
		int amount = Integer.parseInt(req.getParameter("amount"));
		//System.out.println(amount);
	    //System.out.println(p_id);
		//System.out.println(id);
		
		dto.setId(id);
		dto.setP_id(p_id);
		dto.setAmount(amount);
		
		int result = dao.insert(dto);
		
		// 객체 종료 메서드 호출
		//dao.close();
		
		// 인서트 성공일 때 이동할 페이지
		if(result == 1) {
			
			resp.sendRedirect("../MyHomePage/cart_list.jsp"); // 성공일 떄 list페이지로 이동
		}
		
		// 인서트 실패일 때 이동할 페이지
		if (result == 0) {
			
			resp.sendRedirect("../MyHomePage/main.jsp");
		}
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
}
