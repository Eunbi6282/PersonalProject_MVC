package cart;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.CaretListener;

import common.DBConnPool;

public class CartDAO extends DBConnPool{
	
	private static CartDAO instance = new CartDAO();
	
	public static CartDAO getInstance() {
		return instance;
	}
	
	public CartDAO() {
		super();
	}
	
	public int getAmount(int amount) {
		
		try {
			String query = "UPDATE cart SET amount = ?";
			psmt = con.prepareStatement(query);
			psmt.setInt(1, amount);
			psmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("amount값 가져오는 중 예외발생");
		}
		
		return amount;
		
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
				System.out.println(dto.getId());
				System.out.println(dto.getAmount());
				System.out.println(dto.getP_id());
				
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
			String query = "SELECT cart_id, p.p_id, c.id, name, pname, amount, price, downprice,  "
					+ " CASE WHEN p.downprice <> 0 and p.price >= p.downprice THEN p.downprice ELSE p.price END AS cart_price, (cart_price*amount) as money, pImg"
					+ " FROM member m, cart c, product p"
					+ " WHERE m.id=c.id and c.p_id=p.p_id and c.id=? order by cart_id";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			//System.out.println(query);
			
			
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
				dto.setCart_price(rs.getInt(9));
//				if (dto.getDownprice()!=0 && dto.getPrice() > dto.getDownprice()) {
//					dto.setCart_price(rs.getInt("downprice")); // 할인가가 존재하면 장바구니 안의 가격을 할인가로 
//				} else {
//					dto.setCart_price(rs.getInt("price"));
//				}
//				
				dto.setMoney(rs.getInt("money"));
				dto.setpImg(rs.getString("pImg"));
				
				cartlist.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("장바구니list중 예외발생");
		}finally {
			//instance.close();
		}
		return cartlist;
	}
	
	
	//getCart
	public ArrayList<CartDTO> getCartid (int cart_id) {
		ArrayList<CartDTO> cart = new ArrayList<>();
		
		try {
			String sql = "SELECT m.id, p.p_id, c.cart_id"
					+ " FROM member m, product p, cart "
					+ " WHERE m.id = c.id and p.p_id = c.p_id and c.cart_id = ?";
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, cart_id);
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				CartDTO dto = new CartDTO();
				dto.setId(rs.getString("id"));
				dto.setP_id(rs.getString("p_id"));
				dto.setCart_id(rs.getInt("cart_id"));
				
				cart.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("cart_id로드 중 예외발생");
		}
		
		return cart;
		
	}
	
	// delete
	public int deleteCart (int cartid) {
		int result = 0;
		
		try {
			String query = "DELETE cart WHERE cart_id = ?";
			psmt = con.prepareStatement(query);
			psmt.setInt(1, cartid);
			
			result = psmt.executeUpdate();	//result가 0보다 크면 삭제 성공, result가 0이면 삭제 실패
					
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("장바구니 Delete시 예외발생");
		}
		return result;
	}

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

	
}
