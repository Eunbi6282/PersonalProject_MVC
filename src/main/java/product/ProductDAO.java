package product;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import product.ProductDTO;

public class ProductDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public ProductDAO() {//������ ����ɶ����� �ڵ����� db������ �̷������ �ֵ�����
		try {
			String driverName = "oracle.jdbc.driver.OracleDriver";
			String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
			String dbID = "SHOPPING";
			String dbPassword = "1234";
			
			Class.forName(driverName);
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			
			System.out.println("DB연결\n");
			
		
		}catch(Exception e) {
			System.out.println("DB오류");
			e.printStackTrace();
		}
	}
	
	//현재의 시간을 가져오는 함수
	   public String getDate() {
	       String SQL="SELECT to_char(sysdate,'YYYY-MM-DD') FROM product";
	      
	      try {
	         PreparedStatement pstmt = conn.prepareStatement(SQL);
	         rs = pstmt.executeQuery();
	         if (rs.next()) {
	            return rs.getString(1);
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      
	      return "";// 데이터베이스 오류
	   }
	
	public ArrayList<ProductDTO> getList(){
		String sql = "SELECT * FROM product";
		
		ArrayList<ProductDTO> pList = new ArrayList<ProductDTO>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			
			while(rs.next()) {
				ProductDTO pp = new ProductDTO();
				
				pp.setP_id(rs.getString(1));
				pp.setCategory(rs.getString(2));
				pp.setWname(rs.getString(3));
				pp.setPname(rs.getString(4));
				pp.setSname(rs.getString(5));
				pp.setPrice(rs.getInt(6));
				pp.setDownprice(rs.getInt(7));
				pp.setInputdate(rs.getString(8));
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
	
	// insert
	public void productInsert(ProductDTO p) {
		try {
			String sql = "insert into product "
					+ " values(?,?,?,?,?,?,?,?,?,?,?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,p.getP_id());
			pstmt.setString(2, p.getCategory());
			pstmt.setString(3, p.getWname());
			pstmt.setString(4, p.getPname());
			pstmt.setString(5, p.getSname());
			pstmt.setInt(6, p.getPrice());
			pstmt.setInt(7, p.getDownprice());
			pstmt.setInt(8, p.getStock());
			pstmt.setString(9, p.getDescription());
			pstmt.setString(10, p.getInputdate());
			pstmt.setString(11, p.getpImg());
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
}
