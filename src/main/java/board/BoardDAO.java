package board;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool;

public class BoardDAO extends DBConnPool{
	
	private static BoardDAO instance = new BoardDAO();

	/// LogonDTO객체를 리턴하는 메서드 
	public static BoardDAO getInstance() {
		return instance;
	}
	
	public BoardDAO() {
		super();
	}
	
	//검색 조건에 맞는 게시물 개수를 반환
	public int selectCount(Map<String,Object> map) {
		int totalCount = 0;
		String query = "SELECT COUNT(*) FROM board";	// 레코드의 총 개수반환,
			if (map.get("searchWord") != null) { //T(String)에 searchWord 이 있을 때 where값을 추가로 춰리에 넣는다. 
				query += " Where " + map.get("searchField") + " " + "like '%" + map.get("searchWord") + "%'";
			}
			
		try {
			psmt = con.prepareStatement(query);
			rs= psmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);
					
			System.out.println(totalCount);
		} catch (Exception e) {
			System.out.println("게시물 카운트중 예외발생");
			e.printStackTrace();
		}finally {
			instance.close();
		}
		
		return totalCount;
	}
	
	// 검색 조건에 맞는 게시물 목록을 반환합니다.
			// DataBase에서 Select한 결과값을 DTO에 담아서 리턴 시켜줌
		public List <BoardDTO> selectListPage ( Map<String, Object> map){
			List<BoardDTO> board = new Vector <BoardDTO>();
			
			String query = " "
					+ "SELECT * FROM ( " 
					+ "		SELECT Tb.*, ROWNUM rNUM FROM ( "
					+ "			SELECT * FROM board ";
			
			if (map.get("searchWord") != null) {	// 검색 기능을 사용했다라면 
				query += " WHERE " + map.get("searchField")
					+ " LIKE '%" + map.get("searchWord") + "%' ";
			}
			
			query += "		ORDER BY num DESC"
					+ " ) Tb "
					+ ") " 
					+" WHERE rNUM BETWEEN ? AND ?"
					+ " ORDER BY postdate DESC";
			
			System.out.println(query);  //콘솔에 전체 쿼리 출력
			
			try{	//psmt객체 생성후 쿼리 실행
				psmt = con.prepareStatement(query);
				psmt.setString(1, map.get("start").toString());
				psmt.setString(2, map.get("end").toString());
				rs = psmt.executeQuery();	// DataBase에서 Select한 결과값을 rs에 저장
				
				// rs의 저장된 값을 DTO에 저장 ==> 객체를 List에 add
				while(rs.next()) {
					BoardDTO dto = new BoardDTO();
					dto.setNum(rs.getString(1));
					dto.setId(rs.getString(2)); //rs의 index1번의 값을 setter통해 주입
					dto.setName(rs.getString(3));
					dto.setTitle(rs.getString(4));
					dto.setContent(rs.getString(5));
					dto.setPostdate(rs.getDate(6));
					dto.setOfile(rs.getString(7));
					dto.setSfile(rs.getString(8));
					dto.setDowncount(rs.getInt(9));
					dto.setPass(rs.getString(10));
					dto.setVisitcount(rs.getInt(11));
					
					
					
					board.add(dto); // List의 DB의 rs의 하나의 레코드 값을 dto에 저장하고 
										// dto를 List에 추가
				}
				
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("리스트오류");
			}finally {
				instance.close();
			}
			
			return board;	// board는 DTO객체를 담고 있다. 
		}
		
		// 목록 검색 (Select ) : 주어진 일련번호에 해당하는 값을 DTO에 담아 반환(한 페이지 read)
				//ViewController에서 요청 처리/ idx값으로 select하기
			public BoardDTO selectView(String num) {
				BoardDTO dto = new BoardDTO();	
				String query = "SELECT * FROM board WHERE num =?";
				
				try {
					psmt = con.prepareStatement(query);
					psmt.setString(1, num);
					rs = psmt.executeQuery();
					
					if(rs.next()) {
						//rs(select 결과물 들어있음) set이용해서 값 주입
						dto.setNum(rs.getString(1));
						dto.setId(rs.getString(2)); //rs의 index1번의 값을 setter통해 주입
						dto.setName(rs.getString(3));
						dto.setTitle(rs.getString(4));
						dto.setContent(rs.getString(5));
						dto.setPostdate(rs.getDate(6));
						dto.setOfile(rs.getString(7));
						dto.setSfile(rs.getString(8));
						dto.setDowncount(rs.getInt(9));
						dto.setPass(rs.getString(10));
						dto.setVisitcount(rs.getInt(11));
					}
				}catch (Exception e) {
					System.out.println("게시물 상세정보 출력시 예외 발생");
					e.printStackTrace();
				}finally {
					instance.close();
				}
				return dto;
			}
			
		// 게시물 조회수 증가 메서드 
		public void updateVisitCount(String num) {
			String query = "UPDATE board SET "
					+ " visitcount = visitcount + 1 "
					+ " WHERE num = ?";
			
			try {
				psmt = con.prepareStatement(query);
				psmt.setString(1, num);
				rs= psmt.executeQuery();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("게시물 조회수 증가시 예외 발생");
			}finally {
				instance.close();
			}
		}
	
		
		
		// 데이터 삽입(Insert)
		public int insertWrite(BoardDTO dto) { // 폼에서 넘겨받은 값들을 dto에 저장
			int result = 0;
			
			try {
				String query = "INSERT INTO board("
						+ " num, id ,name, title, content, ofile, sfile, pass) " 
						+ " VALUES( " 
						+ " seq_board_peb.nextval, ?, ?, ?, ?, ?, ? ,?)";
				
				psmt = con.prepareStatement(query);
				psmt.setString(1, dto.getId());
				psmt.setString(2, dto.getName());
				psmt.setString(3, dto.getTitle());
				psmt.setString(4, dto.getContent());
				psmt.setString(5, dto.getOfile());
				psmt.setString(6, dto.getSfile());
				psmt.setString(7, dto.getPass());
				
				result = psmt.executeUpdate();  //Insert성공일 때 result > 0 // DB에 값을 저장
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("값 insert시 예외발생");
			}
			
			return result;  // 성공일 떄 result >0, 실패시 :0 
		}
		
		// 다운로드 횟수 증가 메서드
		public void downCountPlus (String num) {
			String query = "UPDATE board SET downcount = downcount + 1"
					+ " WHERE num = ?";
			try {
				psmt = con.prepareStatement(query);
				psmt.setString(1, num);
				psmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("downloadCount 처리중 예외 발생");
			}
		}
		
		// 비밀번호 확인 메서드 (입력한 비밀번호가 DB의 값과 일치하는지 확인)
		public boolean confirmPass (String pass, String num) {
			boolean isCorr = true;
			try {
				// count(*) = 1 레코드 개수, 레코드가 존재하면 아이디와 패스워드가 일치하는 경우이다.
				// count(*) = 0 레코드가 존재하지 않음.
				
				String query = "SELECT COUNT(*) FROM board WHERE pass = ? and num = ?";
				psmt = con.prepareStatement(query);
				psmt.setString(1, pass);
				psmt.setString(2, num);
				rs = psmt.executeQuery();
				
				rs.next();  // 레코드의 첫번때에 커서를 위치 시켜라
				if (rs.getInt(1) == 0) {  // index방번호의 1번방의 값이 0이면 false
					isCorr = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("게시판 비밀번호 검증 중 예외발생");
			}
			return isCorr;
		}
		
		
		// Update
		public int updatePost (BoardDTO dto) {
			int result = 0;
			
			try {
				String query = "UPDATE board SET title =?, name=?, content=?, ofile =?, sfile=? WHERE num=? and pass=?"; //num와 pass가 다 맞을 떄 수정
				psmt = con.prepareStatement(query);
				psmt.setString(1, dto.getTitle());
				psmt.setString(2, dto.getName());
				psmt.setString(3, dto.getContent());
				psmt.setString(4, dto.getOfile());
				psmt.setString(5, dto.getSfile());
				psmt.setString(6, dto.getNum());
				psmt.setString(7, dto.getPass());
				
				result = psmt.executeUpdate();  // update성공시 result변수의 값이  >0
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("게시물 Update시 예외발생");
			}
			return result;
		}
		
		// Delete
		public int deletePost (String num) {
			int result = 0;
			
			try {
				String query = "DELETE board WHERE num = ?";
				psmt = con.prepareStatement(query);
				psmt.setString(1, num);
				
				result = psmt.executeUpdate();	//result가 0보다 크면 삭제 성공, result가 0이면 삭제 실패
						
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("게시물 Delete시 예외발생");
			}
			return result;
		}
		
	
}
