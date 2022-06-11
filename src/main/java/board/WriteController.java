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
		
		// id session값 넣어주기
		HttpSession session = req.getSession();
		String str = (String) session.getAttribute("id");
		
		
		// Form (board_write.jsp)에서 넘어오는 변수의 값을 처리
		// form에서 파일을 전송하므로 cos.jar 라이브러리의 객체 생성 후 변수의 값을 받아야 한다.
		
		// 1. 파일 업로드 처리
			//saveDirectory 변수에 업로드할 파일을 저장할 서버의 물리적인 경로를 저장
		String saveDirectory = req.getServletContext().getRealPath("/uploads");
			
			// maxPostSize : 업로드할 최대 용량 (web.xml에서 설정)
			ServletContext application = getServletContext(); // 이걸 먼저 만들어야 xml설정에 있는 변수값을 불러올 수 있다. 
			int maxPostSize = Integer.parseInt(application.getInitParameter("maxPostSize"));
			
			// 파일 업로드 객체 생성
			MultipartRequest mr = FileUtil.uploadFile(req, saveDirectory, maxPostSize);
			
			// 객체 생성 실패 시 처리할 냐용
			if(mr == null) {  // 용량 이상의 이미지를 넣을 떄
				JSFunction.alertLocation(resp, "첨부 용량이 초과되었습니다.", "../MyHomPage/board_write.do");
				return;
			}
			
		// 2. 파일 업로드 외 처리 (form의 변수값 처리)
			// 폼에서 넘겨받은 값을 받아서 DTO(VO)에 Setter주입을 하고 DAO에 Insert메서드에 던져줌
			BoardDTO dto = new BoardDTO();
			dto.setName(mr.getParameter("name"));
			dto.setId(str);  // id session값 넣어주기
			dto.setTitle(mr.getParameter("title"));
			dto.setContent(mr.getParameter("content"));
			dto.setPass(mr.getParameter("pass"));
			
			//원본 파일 이름과 저장 파일 이름 설정
			String fileName = mr.getFilesystemName("ofile"); // client 의 첨부파일의 물리적 주소
			if (fileName != null) { // 첨부파일이 비어있지 않으면
				// 새로운 파일 이름으로 변경해서 서버에 저장함 (서버의 해당 파일이 존재할 경우가 있으므로)
				String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
				
				// 확장자만 잘라서 저장
				String ext = fileName.substring(fileName.lastIndexOf("."));
					System.out.println("ext : " + ext);
					
				// 서버에 저장할 파일이름 생성
				String newFileName = now + ext;
					System.out.println(newFileName);
					
				// 파일 명 변경
				File oldFile = new File(saveDirectory + File.separator + fileName);
				File newFile = new File(saveDirectory + File.separator + newFileName);
					System.out.println("oldFile : " + oldFile);
					System.out.println("newFile : " + newFile);
				oldFile.renameTo(newFile);
				
				// DTO 에 Setter 주입( 조건 : 파일을 업로드한 경우에만)
				dto.setOfile(fileName); // 원래 파일 이름
				dto.setSfile(newFileName);	// 서버에 저장될 파일 이름
			}
			
			// DTO의 객체를 DAO의 insertWrite(dto) 메서드를 호출래서 DB에 저장
			BoardDAO dao = new BoardDAO();
			int result = dao.insertWrite(dto);
			
			// 객체 종료 메서드 호출
			dao.close();
			
			// 글쓰기 성공일 때 이동할 페이지
			if(result == 1) {
				resp.sendRedirect("../MyHomePage/board_list.do"); // 성공일 떄 list페이지로 이동
			}
			
			// 글쓰기 실패일 때 이동할 페이지
			if (result == 0) {
				resp.sendRedirect("../MyHomPage/board_write.do");
			}
	}
}
