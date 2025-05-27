package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import DBConnection.DBConnection;
import model.Admin;
import model.Shop;

public class ShopsDao {
public boolean registerShop(Shop shop) {
	String sql = "INSERT INTO shops(shop_name, email, password) VALUES (?, ?, ?)";
	Connection conn =null;
	PreparedStatement ps = null;
	try {
		 conn = DBConnection.getConnection();
		 ps = conn.prepareStatement(sql);
		 ps.setString(1, shop.getShopName());
         ps.setString(2, shop.getEmail());
         ps.setString(3, shop.getPassword());
         ps.executeUpdate();
         return true;
	}
	catch(Exception e) {
		e.printStackTrace();
		return false;
	}
	finally {
        try {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
	
}
public Admin getAdmin(String email) {
	Admin admin=new Admin();
	Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
	 try {
         String sql = "SELECT email, password FROM shops WHERE email = ?";
         conn = DBConnection.getConnection();
 	     ps = conn.prepareStatement(sql);
 		ps.setString(1, email);
 		rs = ps.executeQuery();

         if (rs.next()) {
             String adminEmail = rs.getString("email");
             String adminPassword = rs.getString("password");
              admin.setEmail(adminEmail);
              admin.setPassword(adminPassword); 
         } 
         
     } catch (Exception e) {
         e.printStackTrace();
     }
	 finally {
		    try {
		    	if (rs != null) rs.close();
		        if (ps != null) ps.close();
		        if (conn != null) conn.close();
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		}
	
     return admin; 
}
public HashMap<String,String> getShopNames(ArrayList<String>emails){
	HashMap<String,String>shopnames=new HashMap<>();
	String query = "SELECT shop_name FROM shops WHERE email = ?";
	Connection conn =null;
	PreparedStatement ps = null;
	 ResultSet rs = null;
	 try {
		 conn = DBConnection.getConnection();
		 ps = conn.prepareStatement(query);
		 for (String email : emails) {
			 ps.setString(1, email);
			 rs = ps.executeQuery();
			  while (rs.next()) {
                   String shopname= rs.getString("shop_name");
                    shopnames.put(email,shopname);
                }
			 	}
}
	 catch (Exception e) {
            e.printStackTrace();
        }
	 finally {
	        try {
	        	if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
	 return shopnames;
}

}
