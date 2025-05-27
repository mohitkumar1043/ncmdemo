package ncmControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dao.ShopsDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.Admin;

@Controller
public class LoginController {
	
	 @PostMapping("/doLogin") 
	    public String doLogin(HttpServletRequest request,HttpSession session, RedirectAttributes redirectAttributes) {
		
		 String email = request.getParameter("email");
		 String password = request.getParameter("password");
		
		 ShopsDao shopdao=new ShopsDao();
		 Admin admin=shopdao.getAdmin(email);
		 if(admin.getEmail()==null&&admin.getPassword()==null) {
			 redirectAttributes.addFlashAttribute("error", "User not found please create account");
	            return "redirect:/createaccount";
		 }
		 if (admin.getPassword().equals(password)) {
			 
             session.setAttribute("admin", admin);
             redirectAttributes.addFlashAttribute("success", "You are successfully login!");
             return "redirect:/home"; 
             
		 }
		 else {
			 redirectAttributes.addFlashAttribute("error", "Your password is wrong try again!");
	            return "redirect:/login";
		 }
		 
		 
	 }
	
}