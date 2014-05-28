package xdrozd07.dt.junit;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionTest {
	
	static Connection con = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String url = "jdbc:mysql://localhost:3306/dp_itil?characterEncoding=UTF-8";
        String user = "root";
        String password = "";
        
        try {
            con= DriverManager.getConnection(url, user, password);

        } catch (SQLException ex) {
        	System.out.println(ex.getMessage());
        }

        
        
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	  if (con != null) {
          con.close();
      }

	}

	@Test
	public void test() {
		System.out.println("Work work...");
		
		
		String name = "jméno";
		String surname = "pøíjmení";
		
		String insert = "INSERT INTO test (name, surname) VALUES ( ?, ?)";
		PreparedStatement psInsert;
		try {
			con.setAutoCommit(false);
			psInsert = con.prepareStatement(insert);
			
			psInsert.setString(1, name);
			psInsert.setString(2, surname);
			
			psInsert.execute();
			
			con.commit();
			
		}catch(SQLException e){
			System.err.println(e.getMessage());
		}

	}

}
