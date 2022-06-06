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
	
	
	
	
	
	
	
}
