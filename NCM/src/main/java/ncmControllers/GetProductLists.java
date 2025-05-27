package ncmControllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dao.ProductDao;
import jakarta.servlet.http.HttpSession;
import model.Admin;
import model.OfferProduct;

@Controller
public class GetProductLists {

	@GetMapping("/getproductlists")
	 public String getProductLists(HttpSession session,Model model) {
		 Admin admin=(Admin)session.getAttribute("admin");
		 if(admin==null) {
			 return "Login";
		 }
		 ProductDao pdao=new ProductDao();
		 ArrayList<OfferProduct> products=pdao.getProducts(admin.getEmail());
		 model.addAttribute("products",products);
		 return "Products";
		
	      
	    }

}
