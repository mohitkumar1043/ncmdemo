package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DBConnection.DBConnection;
import model.OfferProduct;
import model.Product;

public class ProductDao {
public boolean storeProduct(Product p) {
	String sql = "INSERT INTO products(email,productName,productPrice,discountOffer,productImageUrl ) VALUES (?,?,?,?,?)";
	Connection conn =null;
	PreparedStatement ps = null;
	try {
		 conn = DBConnection.getConnection();
		 ps = conn.prepareStatement(sql);
		 ps.setString(1, p.getEmail());
         ps.setString(2,p.getName());
         ps.setBigDecimal(3,p.getPrice());
         ps.setBigDecimal(4,p.getDiscountOffer());
         ps.setString(5,p.getImgurl());
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

public ArrayList<String>getEmail(String search){
	String query = "SELECT DISTINCT email FROM products WHERE productName LIKE '%" + search + "%'";
	Connection conn =null;
	PreparedStatement ps = null;
	ResultSet rs=null;
	ArrayList<String>emails=new ArrayList<>();
	try {
		 conn = DBConnection.getConnection();
		 ps = conn.prepareStatement(query); 
		  rs = ps.executeQuery();
         while (rs.next()) {
             emails.add(rs.getString("email"));
         }
	} catch(Exception e) {
		e.printStackTrace();
	}
	finally {
        try {
        	if(rs!=null)rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
	return emails;
}
  public ArrayList<OfferProduct>getProducts(String email){
	  ArrayList<OfferProduct>products=new ArrayList<>();
	  String query = "SELECT * FROM products WHERE email = ?";
	  Connection conn =null;
		PreparedStatement ps = null;
		ResultSet rs=null;
	  try {
		  conn = DBConnection.getConnection();
			 ps = conn.prepareStatement(query); 
			 ps.setString(1, email);
			 rs = ps.executeQuery();
			 while (rs.next()) {
	             OfferProduct p=new OfferProduct();
	            p.setId(rs.getInt("id"));
	             p.setName(rs.getString("productName"));
	             p.setPrice(rs.getBigDecimal("productPrice"));
	             p.setDiscountOffer(rs.getBigDecimal("discountOffer"));
	             String fullPath=rs.getString("productImageUrl");
	             
	             p.setImgurl(fullPath);
	             BigDecimal discountOffer = rs.getBigDecimal("discountOffer"); 
	             BigDecimal productPrice = rs.getBigDecimal("productPrice");
	             BigDecimal discountAmount = productPrice.multiply(discountOffer).divide(BigDecimal.valueOf(100));
	             BigDecimal finalPrice = productPrice.subtract(discountAmount);
	             p.setDiscountprice(finalPrice);
	           products.add(p) ;
	         }	 
			 
	  }catch(Exception e) {
			e.printStackTrace();
		}
		finally {
	        try {
	        	if(rs!=null)rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
	  return products;
	  
  }
  public boolean removeProductById(int id) {
      boolean result = false;
      Connection con = null;
      PreparedStatement ps = null;
      String query = "DELETE FROM products WHERE id = ?";
      try {
    	  con = DBConnection.getConnection();
			 ps = con.prepareStatement(query); 
			 ps.setInt(1, id);
			 int rowsAffected = ps.executeUpdate();
	            result = (rowsAffected > 0);
      }catch(Exception e) {
			e.printStackTrace();
		}
		finally {
	        try {
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
      return result;
      
  }
  

}
