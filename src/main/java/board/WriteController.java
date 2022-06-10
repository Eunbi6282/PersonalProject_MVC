package board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// board_list.jsp 에서 글쓰기를 클릭했을 떄 글쓰기 뷰페이지 (board_write.jsp)
		// 뷰페이지로 이동
		req.getRequestDispatcher("/MyHomePage/board_write.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Form (
	}
	
	
	
	
}
