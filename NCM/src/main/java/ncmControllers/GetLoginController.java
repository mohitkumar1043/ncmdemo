package ncmControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import model.Admin;





@Controller
public class GetLoginController {
	@GetMapping("/login")
	public String getLogin(HttpSession session) {
		Admin admin=(Admin)session.getAttribute("admin");
		if(admin!=null) {
			return "Home";
		}
		
       return "Login";
    }

}
