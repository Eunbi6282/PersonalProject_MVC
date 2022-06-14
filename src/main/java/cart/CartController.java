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
		
		String pImg = req.getParameter("pImg");
		
		
//		String id = req.getParameter("id");
//		String p_id = req.getParameter("p_id");
//		int amount = req.getp
//		
//		dto.setId(id);
//		dto.setP_id(p_id);
//		dto.setAmount(amount);
		CartDTO dto = new CartDTO();
		CartDAO dao= new CartDAO();
		HttpSession session = req.getSession(true);
		session.setAttribute("pImg", pImg);
		
		//게시물 (dto)객체를 view페이지로 전달하기 위한 변수값 저장
		req.setAttribute("dto", dto);
		dao.insert(dto);
		
		// 뷰페이지로 이동
		req.getRequestDispatcher("/MyHomePage/cart_list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 장바구니 insert처리
	}
	
}
