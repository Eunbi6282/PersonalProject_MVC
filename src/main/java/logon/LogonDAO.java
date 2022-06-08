package logon;

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
				} else if(!orgPass.equals(decodedTxt)) {
					System.out.println(orgPass + "는 존재하지 않는 비밀번호 입니다.");
					x = 0;
				} else {
					System.out.println(id + " 는 존재하지 않는 아이디입니다.");
					x = -1;  // 패스워드가 일치하지 않을 때
				}
			}
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("인증 실패했습니다.");
			}finally {
				instance.close();
			}
		return x;
	}
	
	
	
	
	
	
	
}
