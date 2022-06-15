package logon;

import java.sql.PreparedStatement;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import common.DBConnPool;

public class LogonDAO extends DBConnPool{
	
	private static LogonDAO instance = new LogonDAO();

	/// LogonDTO객체를 리턴하는 메서드 
	public static LogonDAO getInstance() {
		return instance;
	}
	
	// 기본 생성자 : private =>외부에서 객체 생성 불가능	
		// 부모 클래스의 기본 생성자 호출
	private LogonDAO() { super(); };
	
	// 회원가입 처리 (registerForm -> regiserPro.jsp)에서 넘어오는 값을 DB에 저장 (Insert)
	public void insertMember (LogonDTO member) {
		try {
			String orgPass = member.getPass();
			byte[] targetBytes = orgPass.getBytes();
			
			// 암호화
			Encoder encoder = Base64.getEncoder();
			//Base64인코딩
			byte[] encodedBytes = encoder.encode(targetBytes);
			String encodedTxt = new String(encodedBytes);
			
			String sql = "INSERT INTO member VALUES(?,?,?,?,?,?,?)";
			
			psmt = con.prepareStatement(sql);
			psmt.setString(1, member.getId());
			psmt.setString(2, member.getEmail());
			psmt.setString(3, member.getName());
			psmt.setString(4, encodedTxt );
			psmt.setString(5, member.getAddress());
			psmt.setString(6, member.getTel());
			psmt.setDate(7, member.getRegidate());
			psmt.executeUpdate();
			
			System.out.println("회원정보 DB입력 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("회원정보 DB입력시 예외 발생");
		}finally {
			instance.close();
		}
	}
	
	// 아이디 중복처리 체크
	public int confirmId (String id) {
		int x = -1;
		
		try {
			String sql = "SELECT id FROM member WHERE id = ?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if( rs.next()) {  // 아이디가 DB에 존재하는 경우
				
				System.out.println("존재하는 ID입니다. 아이디: " + id);
				
				x = 1;
			} else { // 아이디가 DB에 존재하지 않는 경우
				x = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("아이디 중복 체크 중 예외발생");
		}
		return x;
	}
	
	// 로그인 기능(loginPro.jsp) : 폼에서 넘겨받은 아이디와 패스워드를 DB와 확인
		// 사용자 인증, Db의 정보를 수정할 때, SB의 정보를 삭제할 때
		// 사용자 인증(MemberCheck.jsp)에서 사용하는 메서드
	public int userCheck(String id, String pass) {
		int x = -1;
		System.out.println("로그인 메소드 호출 성공");
		System.out.println(id); 
		System.out.println(pass); 
		// 복호화 : 암호화된 Password를 해독된 Password로 변환
		try {
			String orgPass = pass;
			byte[] targetBytes = orgPass.getBytes();
			
			// Base64디코딩
			Decoder decoder = Base64.getDecoder();
			
			String sql = "SELECT pass FROM member WHERE id=?";
		
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if (rs.next()) { // 아이디가 존재하면
				String dbpasswd = rs.getString("pass");
					// 디코딩 필요
				byte[] decodedBytes = decoder.decode(dbpasswd);
				String decodedTxt = new String (decodedBytes); // decode처리됨
				
				if(orgPass.equals(decodedTxt)) {
					x = 1;  // 폼에서 넘겨온 패스워드와 DB에서 가져온 패스워드가 일치할 때 x에 1을 할당
					
					System.out.println("인증 성공");
				} else if(!orgPass.equals(decodedTxt)) {
					System.out.println(orgPass + "는 존재하지 않는 비밀번호 입니다.");
					x = 0;
				} else {
					System.out.println(id + " 는 존재하지 않는 아이디입니다.");
					x = -1;  // 패스워드가 일치하지 않을 때
				}
				
				//System.out.println(x);
			}
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("인증 실패했습니다.");
			}finally {
				//instance.close();
			}
		return x;
	}
	
	//현재의 시간을 가져오는 함수
	   public String getDate() {
	       String SQL="SELECT to_char(sysdate,'YYYY-MM-DD') FROM member";
	      
	      try {
	         PreparedStatement pstmt = con.prepareStatement(SQL);
	         rs = pstmt.executeQuery();
	         if (rs.next()) {
	            return rs.getString(1);
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      
	      return "";// 데이터베이스 오류
	   }
	   
	   
	
	// 회원정보 DB에서 가져와서 수정(modifyForm.jsp): DB에서 회원정보의 값을 가져오는 메서드
	public LogonDTO getMember (String id, String pass) {
		// DTO리턴
		LogonDTO member = new LogonDTO();
		
		try {
			String orgPass = pass;
			byte[] targetBytes = orgPass.getBytes();
			
			// 디코딩 (암호 디코딩해서 변수에 담아야 함)
			Decoder decoder = Base64.getDecoder();
			
			String sql = "SELECT id, email, name, address, tel FROM member where id = ?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if(rs.next()) {  // 만약 아이디가 존재한다면 
				// 암호 디코딩해서 폼에서 넘어온 Pass 와 같은지 처리
				String dbpass = rs.getString("pass");
				byte[] decodedBytes = decoder.decode(dbpass);
				String decodedTxt = new String (decodedBytes); // decode처리됨
				System.out.println(decodedTxt);
				if(orgPass.equals(decodedTxt)) {
					// DB의 passwd와 폼에서 넘겨온 Pass가 같을 때 처리할 부분
						// DB에서 select한 레코드를 DTO (LogonDataBean)에 Setter주입해서 값을 반환
					
					// 객체 생성후 DB의 rs에 저장된 값을 Setter주입
					
					member.setId(rs.getString("id"));  // 컬럼명 , rs.getString(1)도 가능
					member.setEmail(rs.getString("email"));
					member.setName(rs.getString("name"));
					member.setAddress(rs.getString("address"));
					member.setTel(rs.getString("tel"));
				}else {
					// DB의 passwd와 폼에서 넘겨온 Pass가 다를 때 처리할 부분
					System.out.println("비밀번호 다름");
				}
			}	
		} catch (Exception e) {
				e.printStackTrace();
				System.out.println("회원 정보 읽어오는 중 예외 발생");
		}finally {
			//instance.close(); // 객체 사용 종료. rs, psmt, con
		}
			
		return member;
		}
		
		
		// 수정 페이지에서 수정한 내용을 DB에 저장하는 메서드
			// 회원정보 수정처리 (modifyPro.jsp)에서 회원정보를 수정처리하는 메서드
	public int updateMember (LogonDTO member) {
		int x = -1;  //업데이트 실패
		
		// 아이디와 패스워드 확인 절차를 거친 후 업데이트 진행
			// 넘어온 비밀번호를 암호화 시켜서 db에 있는 암호화된 비밀번호의 값과 대조해야 함
		try {
			String orgPass = member.getPass();   // 비밀전호 안들어올 경우 유효성검사
			byte[] targetBytes = orgPass.getBytes();
			
			// Base64 인코딩
			Encoder encoder = Base64.getEncoder();
			byte[] encodedBytes = encoder.encode(targetBytes);
			String encodedTxt = new String(encodedBytes);
			
			// Base64디코딩
			Decoder decoder = Base64.getDecoder();
			
			String sql = "SELECT pass FROM member WHERE id = ?";
			
			psmt = con.prepareStatement(sql);
			psmt.setString(1, member.getId()); 
			rs= psmt.executeQuery(); 
			
			if(rs.next()) {  //해당 아이디가 db에 존재한다.
				// 폼에서 넘긴 패스워드와 db에서 가져온 패스워드가 일치하는ㄴ지 확인 후 처리
				String dbpass = rs.getString("pass");
				byte[] decodedBytes = decoder.decode(dbpass);
				String decodedTxt = new String (decodedBytes); // decode처리됨
				
				if(orgPass.equals(decodedTxt)) {
					//DTO(member)에서 들어온 값을 db에 update
					sql = "UPDATE member SET email = ?, name = ?, address = ?, tel = ? where id = ?";
					psmt = con.prepareStatement(sql);
					psmt.setString(1, member.getEmail());
					psmt.setString(2, member.getName());
					psmt.setString(3, member.getAddress());
					psmt.setString(4, member.getTel());
					psmt.setString(5, member.getId());
					psmt.executeUpdate();
					x = 1;  //update성공시 x변수에 1을 할당
							
				}else { //해당 아이디가 존재하지 않는다면
					System.out.println("회원수정 비밀번호 확인바람");
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("회원정보 수정 시 예외발생");
		}finally {
			instance.close();
		}
		return x;
	}
	
	
	
	
	
}
