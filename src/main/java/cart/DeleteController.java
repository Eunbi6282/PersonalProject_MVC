package cart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.JSFunction;

public class DeleteController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int cart_id = Integer.parseInt(req.getParameter("cart_id"));
		System.out.println(cart_id);
		CartDAO dao = new CartDAO();
		
		int result = dao.deleteCart(cart_id);
		dao.close();
		
		// 삭제 성공일 때 이동할 페이지
		if(result == 1) {
			
			// 삭제 이후 페이지 이동 (js)
			JSFunction.alertLocation(resp, "삭제되었습니다", "../MyHomePage/cart_list.jsp");
		}
		
		// 삭제 실패일 때 이동할 페이지
		if (result == 0) {
			JSFunction.alertLocation(resp, "실패했습니다.", "../MyHomePage/cart_list.jsp");
		}
	}
}
