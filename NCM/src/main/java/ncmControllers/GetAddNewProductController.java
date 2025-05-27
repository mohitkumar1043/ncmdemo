package ncmControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import model.Admin;

@Controller
public class GetAddNewProductController {
	@GetMapping("/getaddnewproductpage")
	public String getAddNewProductPage(HttpSession session) {
		 if((Admin)session.getAttribute("admin")==null) {
			 return "Login";
		 }
		return "AddNewProduct";
	}

}
