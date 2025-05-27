package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import DBConnection.DBConnection;
import model.ShopLocation;

public class ShopLocationsDao {
	public boolean storeShopLocation(ShopLocation shoplocation) {
		
		Connection conn =null;
		PreparedStatement ps = null;
		 ResultSet rs = null;
		try {
			 conn = DBConnection.getConnection();
			 String chekSql = "SELECT email FROM shopLocations WHERE email = ?";
	            ps = conn.prepareStatement(chekSql);
	            ps.setString(1, shoplocation.getEmail());
	             rs = ps.executeQuery();
	            if (rs.next()) {
	            	
	                // Email exists -> Update
	                String updateSql = "UPDATE shopLocations SET latitude = ?, longitude = ? WHERE email = ?";
	                PreparedStatement psUpdate = conn.prepareStatement(updateSql);
	                psUpdate.setString(1,shoplocation.getLatitude());
	                psUpdate.setString(2, shoplocation.getLongitude());
	                psUpdate.setString(3,shoplocation.getEmail());
	                psUpdate.executeUpdate();
	                psUpdate.close(); 
		}
	            else {
	            
	            	 // Email not exists -> Insert
	                String insertSql = "INSERT INTO shopLocations (email, latitude, longitude) VALUES (?, ?, ?)";
	                PreparedStatement psInsert = conn.prepareStatement(insertSql);
	                psInsert.setString(1,shoplocation.getEmail());
	                psInsert.setString(2, shoplocation.getLatitude());
	                psInsert.setString(3,shoplocation.getLongitude());
	                psInsert.executeUpdate();
	                psInsert.close();
	            }
	            return true;
	            }
		catch(Exception e) {
			e.printStackTrace();
			return false;
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
	}
	
	public HashMap<String ,String[]> getShopLocations(ArrayList<String>emails){
		HashMap<String,String[]> shoplocations=new HashMap<>();
		String query = "SELECT latitude, longitude FROM shoplocations WHERE email = ?";
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
	                    String lat = rs.getString("latitude");
	                    String lng = rs.getString("longitude");
	                    shoplocations.put(email, new String[]{lat, lng});
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
		 return shoplocations;
}
	public String[]getShopLocation(String email){
		String query = "SELECT latitude, longitude FROM shoplocations WHERE email = ?";
		String shoplocation[]=new String[2];
		Connection conn =null;
		PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
			 conn = DBConnection.getConnection();
			 ps = conn.prepareStatement(query);
			 ps.setString(1, email);
			 rs = ps.executeQuery();
			 while (rs.next()) {
                 String lat = rs.getString("latitude");
                 String lng = rs.getString("longitude");
                 shoplocation[0]=lat;
                 shoplocation[1]=lng;
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
		 return shoplocation;
		 
}
	}