package product;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import common.DBConnPool;

public class ProductDAO extends DBConnPool{
	private static ProductDAO instance = new ProductDAO();
	
	// ProductDTO 리턴하는 메서드
	public static ProductDAO getInstance() {
		return instance;
	}
	
	// 기본생성자 : private => 외부에서 객체 생성 불가능
		// 부모 클래스의 기본 생성자 호출
	private ProductDAO() { super(); };
	
	
	// 상품 등록 처리 (
//	public void insertProduct(ProductDTO product) {
//		String fileurl= application.getRealPath("shop/upload");
//	}
	
	public ArrayList<ProductDTO> getList(){
		String sql = "SELECT * from product";
		
		ArrayList<ProductDTO> pList = new ArrayList<ProductDTO>();
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery(sql);
			
			while(rs.next()) {
				ProductDTO pp = new ProductDTO();
				pp.setP_id(rs.getString(1));
				pp.setCategory(rs.getString(2));
				pp.setWname(rs.getString(3));
				pp.setPname(rs.getString(4));
				pp.setSname(rs.getString(5));
				pp.setPrice(rs.getInt(6));
				pp.setDownprice(rs.getInt(7));
				pp.setInputdate(rs.getDate(8));
				pp.setStock(rs.getInt(9));
				pp.setDescription(rs.getString(10));
				pp.setpImg(rs.getString(11));
				pList.add(pp);
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("리스트 오류");
		}
		
		return pList;
	}
}
