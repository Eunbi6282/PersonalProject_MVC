package board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fileupload.FileUtil;

public class DownloadController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ofile = req.getParameter("ofile");
		String sfile = req.getParameter("sfile");
		String num = req.getParameter("num");
		
		//파일 다운로드 처리 (FileUtil의 download메서드 호출)
		FileUtil.download(req, resp, "/uploads", sfile, ofile);
		
		// 게시물 다운로드 수 1증가
		BoardDAO dao = new BoardDAO();
		dao.downCountPlus(num);
		dao.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}
