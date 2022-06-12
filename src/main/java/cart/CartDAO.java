package cart;

import java.util.ArrayList;
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
		public int insert(CartDTO dto) {
			int result = 0;
			
			try {
				String query = "INSERT INTO cart (cart_id, id, p_id, amount)"
						+ "        VALUES( "
						+ " seq_cart.nextval, ?, ?, ?)";
				psmt = con.prepareStatement(query);
				psmt.setString(1, dto.getId());
				psmt.setString(2, dto.getP_id());
				psmt.setInt(3, dto.getAmount());
				result = psmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("장바구니 값 insert시 예외발생");
			}
			return result;
		}
	
	// 장바구니 목록
	public ArrayList<CartDTO> listCart (String id) {
		ArrayList<CartDTO> cartlist = new ArrayList<CartDTO>();
		try {
			String query = "SELECT cart_id, p.p_id, c.id, name, pname, amount, price, downprice, cart_price, (price*amount) money"
					+ " FROM member m, cart c, product p"
					+ " WHERE m.id=c.id and c.p_id=p.p_id and c.id=? order by cart_id";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			System.out.println(query);
			
			while(rs.next()) {
				CartDTO dto = new CartDTO();
				dto.setCart_id(rs.getInt("cart_id"));
				dto.setP_id(rs.getString("p_id"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setPname(rs.getString("pname"));
				dto.setAmount(rs.getInt("amount"));
				dto.setPrice(rs.getInt("price"));
				dto.setDownprice(rs.getInt("downprice"));
				if (!(dto.getDownprice()==0) && dto.getDownprice() <= dto.getPrice()) {
					dto.setCart_price(rs.getInt("downprice")); // 할인가가 존재하면 장바구니 안의 가격을 할인가로 
				} else {
					dto.setCart_price(rs.getInt("price"));
				}
				dto.setMoney(rs.getInt("money"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("장바구니list중 예외발생");
		}finally {
			instance.close();
		}
		return cartlist;
	}
	
	
	// delete
//		public void delete (int cart_id) {
//			
//		}

	// delteAll
//		public void deleteAll (String id) {
//			
//		}

	// update
//		public void update(int cart_id) {
//			
//		}
	
	// 장바구니 금액 합계
//		public int sumMoney (String id) {
//			
//		}

	// countCart 장바구니 상품 개수 
//		public int countCart(String id, String p_id) {
//			
//		}
	
	// updateCart  장바구니 수정
//		public void updateCart(CartDTO dto) {
//			
//		}

	// modifyCart
//		public void modifyCart(CartDTO dto) {
//			
//		}
}
