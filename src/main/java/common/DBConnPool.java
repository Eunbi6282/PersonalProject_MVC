package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnPool {
	
	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs; 
	
	//�⺻ ������ 
	public DBConnPool() {
		
		
       try {
            // JDBC ����̹� �ε�
            Class.forName("oracle.jdbc.OracleDriver");

            // DB�� ����
            String url = "jdbc:oracle:thin:@localhost:1521:xe";  
            String id = "SHOPPING";
            String pwd = "1234"; 
            con = DriverManager.getConnection(url, id, pwd); 

            System.out.println("DB 연결 성공(기본 생성자)");
        }
        catch (Exception e) {            
            e.printStackTrace();
        }
		
		
		/*
		System.out.println("DBCP ��ü ��� ");
		
		try {
			//���Ͽ� ������ Ŀ�ؼ� Ǯ (DBCP) ���� ������
			Context initCtx = new InitialContext(); 
			Context ctx = (Context)initCtx.lookup("java:comp/env"); 
			DataSource source = (DataSource)ctx.lookup("dbcp_myoracle"); 
			
			//Ŀ�ؼ� Ǯ�� ���ؼ� ���� ��� 
			con = source.getConnection();
			
			System.out.println("Ŀ�ؼ�Ǯ(DBCP) ���� ����");
			
		}catch (Exception e) {
			System.out.println("Ŀ�ؼ�Ǯ(DBCP) ���� ����");
			e.printStackTrace(); 	//���� �޼��� ��� : �ڼ��� ���� ��� 
			System.out.println(e.getMessage()); //���� �޼��� ��� (���� ����)
		}
		
		*/	
	}
	
	//�ڿ� ���� ����  (�ڿ� �ݳ�) : close() �޼ҵ� ȣ��� �ڿ��� �ݳ��ϵ��� ���� 
	public void close() {
		try {
			if (rs != null)  rs.close(); 
			if (stmt != null) stmt.close(); 
			if (psmt != null) psmt.close(); 
			if (con != null) con.close();     //�ڿ��� Ŀ�ؼ� Ǯ�� �ݳ���
			
			System.out.println("DB 커넥션 풀 자원 반납 (성공)");
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB 커넥션 풀 자원 반납 (실패) ");
		}
		
	}
	
	

}
