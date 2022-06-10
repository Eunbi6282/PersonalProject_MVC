package board;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.BoardPage;


public class ListController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. DAO 객체 생성
			BoardDAO dao = new BoardDAO();  //객체 만드는 순간 연결성공
		
		// 2. 뷰에 전달할 매개변수 저장용 맵 생성 (Key, Value)
			Map<String, Object> map = new HashMap<String, Object>();
			String searchFiled = req.getParameter("searchField");
			String searchWord = req.getParameter("searchWord");
			
			if(searchWord != null) {	// 검색어가 존재한다면 map에 값을 저장
				map.put("searchField", searchFiled);
				map.put("searchWord", searchWord);
			}
			
			// 게시물 개수 알아오기(DAO에 selectCount
			int totalCount = dao.selectCount(map);
			// System.out.println("전체 레코드 수 : " + totalCount);
			// System.out.println("List.do 요청시 Controller 페이지 잘 응답");
				
		
		/* 페이징 처리 부분 start*/
			// web.xml에서 세팅한 변수 값게더링해오기
			ServletContext application = getServletContext();
			int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
			int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));
		
			System.out.println(pageSize);
			System.out.println(blockPage);
		
			//현재 페이지 확인
			int pageNum = 1;
			String pageTemp = req.getParameter("pageNum"); // Parameter로 넘어오는 값은 모두 String, 계산하려면 int로 변환필요 
			if (pageTemp != null && !pageTemp.equals("")) {
				pageNum = Integer.parseInt(pageTemp); // 값이 비어있지 않을 때 넘어온 페이지 변수를 정수로 변환해서 변수에 넣는다. 
			}
			
			// 목록에 출력할 게시물 범위 계산
			int start = (pageNum - 1) * pageSize + 1;	// 첫 게시물 번호
			int end = pageNum * pageSize; //마지막 게시물 번호
			
			// 뷰 페이지에 값을 던져줌
			map.put("start", start);	// ("변수명", 들어갈 값)
			map.put("end", end);
		
		/* 페이징 처리 부분 end*/
		
			// 게시물 목록 받아오기(DAO 객체에 작업을 전달)
				// DAO의 selectListPage()호출 => return board 이므로 DTO까지 불러옴 => boardLists에 결과값 담기
					//DTO에는 DB안의 값들이 들어있다. board는 DTO객체를 담고 있다. 
			List <BoardDTO> boardLists = dao.selectListPage(map);  //map에start, end의 값이 들어있음
			dao.close(); //DB연결 닫기(DBConnPool을 상속하고 있음
			
			// 뷰페이지에 전달할 매개변수들을 추가
			String pagingImg = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "../MyHomePage/board_list.do"); // 바로가기 HTML문자열
			
			// 뷰페이지로 변수의 값을 전달
			map.put("pagingImg", pagingImg);
			map.put("totalCount", totalCount);
			map.put("pageSize", pageSize);
			map.put("pageNum", pageNum);
			
			// 뷰페이지로 데이터 전달, request 영역에 전달할 데이터를 저장 후 board_list.jsp(뷰페이지)로 포워드
			req.setAttribute("boardLists", boardLists); // ("변수값", 값) //DB에서 Select한 결과값
			req.setAttribute("map", map);
			req.getRequestDispatcher("/MyHomePage/board_list.jsp").forward(req, resp);	
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
}
