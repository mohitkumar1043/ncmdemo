package ncmControllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dao.ProductDao;
import dao.ShopLocationsDao;
import jakarta.servlet.http.HttpSession;
import model.OfferProduct;

@Controller
public class ShopDetailsController {
	@GetMapping("/shopDetails")
    public String getShopDetails(@RequestParam("shopKey") String email,@RequestParam("shopName") String shopname, Model model,HttpSession session) {
		
		ProductDao pdao=new ProductDao();
		ArrayList<OfferProduct> products= pdao.getProducts(email);
		ShopLocationsDao sldao=new ShopLocationsDao();
		
		String shoplocation []=sldao.getShopLocation(email);
		Double userLatObj = (Double) session.getAttribute("latitude");
		Double userLngObj = (Double) session.getAttribute("longitude");

		String userLat = userLatObj != null ? userLatObj.toString() : null;
		String userLng = userLngObj != null ? userLngObj.toString() : null;
		 session.removeAttribute("latitude");
		  session.removeAttribute("longitude");
		 model.addAttribute("slatitude",shoplocation[0]);
		 model.addAttribute("slongitude",shoplocation[1]);
		 model.addAttribute("ulatitude",userLat);
		 model.addAttribute("ulongitude", userLng);
		 model.addAttribute("products", products);
		 model.addAttribute("shopname",shopname);
		return "Offers";
	}
     
}
