package board;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fileupload.FileUtil;
import utils.JSFunction;

public class PassController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//view 페이지 (board_pass.jsp)로 값 넘김
		//mode:edit <= 글수정, mode:delte <= 글삭제
		req.setAttribute("mode",req.getParameter("mode"));  // get방식으로 들어온 mode의 값을 "mode"변수에 담는다. 
		req.getRequestDispatcher("../MyHomePage/board_pass.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// form에서 post 방식으로 넘어오는 변수 값 3개
		String num = req.getParameter("num");
		String mode = req.getParameter("mode");
		String pass = req.getParameter("pass");
		
		String sfile = req.getParameter("sfile");  // 삭제시 파일도 같이 삭제
		
		// 비밀번호 확인 (DAO에 작업)
		BoardDAO dao = new BoardDAO();
		boolean confirmed = dao.confirmPass(pass, num);
		dao.close();  // close()시킴, 41다시 새로 만듬
		
		if(confirmed) {
			// 비밀번호가 일치할 떄 (mode변수를 확인해서 edit면 수정페이지로, delete면 삭제 페이지로 가도록)
			if(mode.equals("edit")) {
				HttpSession session = req.getSession();
				session.setAttribute("pass", pass);  //pass를 session 변수 "pass"에 할당
				resp.sendRedirect("../MyHomePage/board_edit.do?num=" + num);
			}else if (mode.equals("delete")) {
				dao = new BoardDAO();  //dao객체 새로 만듬
				
				//dao delete객체 만들기
				BoardDTO dto = dao.selectView(num);
				int result = dao.deletePost(num);  //게시물 삭제
				dao.close();
				// 게시물 삭제시 첨부파일도 같이 삭제 
				FileUtil.deleteFile(req, "/uploads", sfile);
				
				
				// 삭제 이후 페이지 이동 (js)
				JSFunction.alertLocation(resp, "삭제되었습니다", "../MyHomePage/board_list.do");
			}
			
		} else {  // 비밀번호가 맞지 않을 때 js이요해 이전 페이지로 돌아가도록
			JSFunction.alertBack(resp, "비밀번호 검증에 실패했습니다.");
			
		}
	}
}
