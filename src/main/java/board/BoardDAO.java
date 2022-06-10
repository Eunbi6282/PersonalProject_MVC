package board;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool;

public class BoardDAO extends DBConnPool{
	
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
			stmt = con.createStatement();
			rs= stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);
					
			System.out.println(totalCount);
		} catch (Exception e) {
			System.out.println("게시물 카운트중 예외발생");
			e.printStackTrace();
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
			
			query += "		ORDER BY id DESC"
					+ " ) Tb "
					+ ") " 
					+" WHERE rNUM BETWEEN ? AND ?";
			
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
			}
			
			return board;	// board는 DTO객체를 담고 있다. 
		}
		
		// 목록 검색 (Select ) : 주어진 일련번호에 해당하는 값을 DTO에 담아 반환(한 페이지 read)
				//ViewController에서 요청 처리/ idx값으로 select하기
			public BoardDTO selectView(String id) {
				BoardDTO dto = new BoardDTO();	
				String query = "SELECT * FROM board WHERE id =?";
				
				try {
					psmt = con.prepareStatement(query);
					psmt.setString(1, id);
					rs = psmt.executeQuery();
					
					if(rs.next()) {
						//rs(select 결과물 들어있음) set이용해서 값 주입
						dto.setNum(rs.getString(1));
						dto.setId(rs.getString(2));	// 1번컬럼
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
				}
				return dto;
			}
			
			// 게시물 조회수 증가 메서드 
			public void updateVisitCount(String id) {
				String query = "UPDATE board SET "
						+ " visitcount = visitcount + 1 "
						+ " WHERE id = ?";
				
				try {
					psmt = con.prepareStatement(query);
					psmt.setString(1, id);
					rs= psmt.executeQuery();
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("게시물 조회수 증가시 예외 발생");
				}
			}
		
	
}
