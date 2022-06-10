package board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 게시물 정보 불러오기 (1. 조회수 증가, 2. 게시물 정보 가져오기)
		BoardDAO dao = new BoardDAO();
		
		String num = req.getParameter("num");
		
		// 조회수 증가
		dao.updateVisitCount(num);
		
		// 게시물들의 자세한 정보값 가져오기
		BoardDTO dto = dao.selectView(num);
		//dao.close(); // 객체 반납
		
		// DB의 content컬럼의 엔터를 "<br />" 태그로 변환
		dto.setContent(dto.getContent().replaceAll("\r\n", "<br>"));
		
		//게시물 (dto)객체를 view페이지로 전달하기 위한 변수값 저장
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/MyHomePage/board_view.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}
