package product;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import product.ProductDTO;

public class ProductDAO1 {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs1;
	
	public ProductDAO1() {//������ ����ɶ����� �ڵ����� db������ �̷������ �ֵ�����
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
	
	   public Date getDate() {
		      //String SQL = "Select GETDATE()";
		      //
		       String SQL="SELECT sysdate FROM product";
		      // Select GETDATE();
		      
		      try {
		         PreparedStatement pstmt = conn.prepareStatement(SQL);
		         rs1 = pstmt.executeQuery();
		         if (rs1.next()) {
		            return rs1.getDate(1);
		         }
		      } catch (Exception e) {
		         e.printStackTrace();
		      }
			return null;
		      
		      
		   }
	
	public ArrayList<ProductDTO> getList(){
		String sql = "SELECT p_id, category, wname, pname, sname, price, downprice, stock, description, pImg FROM product";
		
		ArrayList<ProductDTO> pList = new ArrayList<ProductDTO>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs1 = pstmt.executeQuery(sql);
			
			while(rs1.next()) {
				ProductDTO pp = new ProductDTO();
				
				pp.setP_id(rs1.getString(1));
				pp.setCategory(rs1.getString(2));
				pp.setWname(rs1.getString(3));
				pp.setPname(rs1.getString(4));
				pp.setSname(rs1.getString(5));
				pp.setPrice(rs1.getInt(6));
				pp.setDownprice(rs1.getInt(7));
				pp.setStock(rs1.getInt(8));
				pp.setDescription(rs1.getString(9));
				pp.setpImg(rs1.getString(10));
				pList.add(pp);
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("리스트 오류");
		}
		
		return pList;
	}
	
	public class
	
}
