package cart;

import java.util.List;

import common.DBConnPool;

public class CartDAO extends DBConnPool{
	
	private static CartDAO instance = new CartDAO();
	
	public static CartDAO getInstance() {
		return instance;
	}
	
	public CartDAO() {
		super();
	}
	
	
	
	
	
	
	
	public List<CartDTO> cartMoney(){
		return null;
	}
	
	// insert  장바구니 추가
	public void insert(CartDTO dto) {
		int result = 0;
		
		try {
			String query = "INSERT INTO cart (cart_id, id, p_id, amount)"
					+ "        VALUES( "
					+ " seq_cart.nextval, ?, ?, ?)";
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// 장바구니 목록
	public List<CartDTO> listCart (String id) {
	}
	
	
	// delete
	public void delete (int cart_id) {
		
	}

	// delteAll
	public void deleteAll (String id) {
		
	}

	// update
	public void update(int cart_id) {
		
	}
	
	// 장바구니 금액 합계
	public int sumMoney (String id) {
		
	}

	// countCart 장바구니 상품 개수 
	public int countCart(String id, String p_id) {
		
	}
	
	// updateCart  장바구니 수정
	public void updateCart(CartDTO dto) {
		
	}

	// modifyCart
	public void modifyCart(CartDTO dto) {
		
	}
}
