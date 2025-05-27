package ncmControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.ShopLocationsDao;
import jakarta.servlet.http.HttpSession;
import model.Admin;
import model.ShopLocation;


@Controller
public class StoreShopLocationController {
    @PostMapping("/storelocation")
    @ResponseBody
    public String storeLocation( @RequestParam("latitude") String latitude,
    	    @RequestParam("longitude") String longitude,
    	    HttpSession session) {
    	 
    	Admin admin=(Admin)session.getAttribute("admin");
    	 if(admin==null) {
			 return "fail";
		 }
   
    	if (latitude == null || latitude.isEmpty() || longitude == null || longitude.isEmpty()) {
            return "fail";  // Return fail if latitude or longitude is empty
       }
    	String email=admin.getEmail();
        ShopLocation shoplocation =new ShopLocation();
        shoplocation.setEmail(email);
        shoplocation.setLatitude(latitude);
        shoplocation.setLongitude(longitude);
        ShopLocationsDao shopLocationsDao =new ShopLocationsDao();
    	boolean isStore=shopLocationsDao.storeShopLocation(shoplocation);
    	if (isStore) {
    		return "success";
        } else {
           return "fail";
        }
    	
        }
    	
   
}
