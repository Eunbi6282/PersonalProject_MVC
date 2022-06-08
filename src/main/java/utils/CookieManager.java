package utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieManager {

	// 쿠키 생성 메서드
				// 명시한 이름, 값, 유지기간을 조건으로 세로운 쿠키를 생성
		public static void makeCookie (HttpServletResponse response, String cName, String cValue, int cTime) {
			Cookie cookie = new Cookie (cName, cValue);
			cookie.setPath("/"); //경로설정
			cookie.setMaxAge(cTime); // 쿠키 유지 기간
			response.addCookie(cookie);	// 쿠키 저장
		}
		
		/// 쿠키 정보를 읽는 메서드
		// 명시한 쿠키 이름을 찾아 그 값을 반환하는 메서드
		public static String readCookie (HttpServletRequest request, String cName) {
			String cookieValue = "";	// ��ȯ ��
			
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				for (Cookie c : cookies) {
					String cookieName = c.getName();
					
					// 매개변수로 입풋되는 cName변수에 해당하는 이름의 Value를 리턴시켜준다.  
					if(cookieName.equals(cName)) {
						cookieValue = c.getValue();
					}
				}
			}
			
			return cookieValue;
		}
		// 쿠키 삭제 메서드
					// 명시한 이름의 쿠키를 삭제하는 메서드
		public static void deleteCookie (HttpServletResponse response, String cName) {
			makeCookie(response, cName, "", 0);
		}
}

