package board;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import fileupload.FileUtil;
import utils.JSFunction;

public class EditController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 비밀번호 검증 후 검증이 완료되면 num에 해당하는 레코드를 dtp에 담아서 뷰페이지로 넘김
		String num = req.getParameter("num");
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = dao.selectView(num);
		
		req.setAttribute("dto", dto); //변수에 값을 담고 뷰페이지로 넘겨줌
		req.getRequestDispatcher("/MyHomePage/board_edit.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// board_edit.jsp 에서 수정될 내용을 받아서 DB에 적용
		
		// 1. 파일 업로드 처리
			String saveDirectory = req.getServletContext().getRealPath("/uploads");
			System.out.println(saveDirectory);
			
			// (1) 압러드할 파일의 최대용량(web.xml)
			ServletContext application = this.getServletContext();
			int maxPostSize = Integer.parseInt(application.getInitParameter("maxPostSize"));
			
			// (2) 파일 업로드 
			MultipartRequest mr = FileUtil.uploadFile(req, saveDirectory, maxPostSize);
			
			if(mr == null) {
				JSFunction.alertBack(resp, "첨부파일 용량이 초과되었습니다");
				return;
			}
			
		// 2. 파일 업로드 외 처리
			// 파일이 포함되어있으므로 request 객체가 아닌 MultipartRequest객체에서 Form의 변수 값을 받는다.
			String num = mr.getParameter("num");
			String prevOfile = mr.getParameter("prevOfile");
			String prevSfile = mr.getParameter("prevSfile");
			
			String name = mr.getParameter("name");
			String title = mr.getParameter("title");
			String content = mr.getParameter("content");
			
			// 비밀번호 : Session 변수에서 값 가져오기
			HttpSession session = req.getSession();
			String pass = (String)session.getAttribute("pass");
			
			// DTO에 넘겨받은 변수값을 저장 (Client Form => DTO => DAO에 전달)
			BoardDTO dto = new BoardDTO();
			dto.setNum(num);
			dto.setName(name);
			dto.setTitle(title);
			dto.setContent(content);
			dto.setPass(pass);
			
				// 변수값 넘어오는지 확인
//				System.out.println("DTO 객체에 저장된 값 불러오기 ============");
//				System.out.println(dto.getNum());
//				System.out.println(dto.getName());
//				System.out.println(dto.getTitle());
//				System.out.println(dto.getContent());
//				System.out.println(dto.getPass());
			
			/// dto객체의 Ofile, Sfile은 업로드 경로에 해당 파일명이 존재하는 경우 처리
			String fileName = mr.getFilesystemName("ofile");
			if(fileName != null) {  // 첨부파일이 uploads 폴더에 존재하는 경우 파일 이름을 수정해서 저장
				
			// 새로운 파일명 생성
			String now = new SimpleDateFormat("yyyyMMdd").format(new Date());  // 날짜처리
			String ext = fileName.substring(fileName.lastIndexOf("."));  // .뒤에 있는 확장자까지만 가지고 옴
			String newFileName = now + ext;
				
//				System.out.println("now : " + now);
//				System.out.println("ext : " + ext);
//				System.out.println("newFileName : " + newFileName);
			
			// 파일명 변경 
			File oldFile = new File(saveDirectory + File.separator + fileName);
			File newFile = new File(saveDirectory + File.separator + newFileName);
			
			oldFile.renameTo(newFile);
			
			// 변경한 내용 DTO에 저장
			dto.setOfile(fileName);	// 원본 파일 이름
			dto.setSfile(newFileName); // 새로운 파일 이름 (서버에 저장될 파일 이름)
			
			// 기존 파일 삭제
			FileUtil.deleteFile(req, "/uploads", prevSfile);
			
			} else {
				dto.setOfile(prevOfile);
				dto.setSfile(prevSfile);
			}
			
			// DB에 수정 내용을 반영 
			BoardDAO dao = new BoardDAO();
			int result = dao.updatePost(dto);  // :1 => 성공, :0 => 실패
			dao.close();
			
			// 수정 성공 vs 실패
			if(result == 1 ) { // 성공
				session.removeAttribute("pass");
				resp.sendRedirect("../MyHomePage/board_view.do?num=" + num);
			} else {
				JSFunction.alertLocation(resp, "비밀번호 검증을 다시 해주세요", "../MyHomePage/board_view.do?num=" + num);
			}
	}
	
}
