package ncmControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dao.ShopsDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.Shop;

@Controller
public class ShopRegisterController {
	@PostMapping("/register")
	 
	public String registerShop(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String shopName = request.getParameter("shopName");
        String email = request.getParameter("email");
        String otpInput = request.getParameter("otp");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        
        HttpSession session = request.getSession();
        String sessionOtp = (String) session.getAttribute("otp");
        String sessionEmail = (String) session.getAttribute("otpEmail");
        
        
        if (!password.equals(confirmPassword)) {
        	redirectAttributes.addFlashAttribute("error", "Passwords do not match.");
        	 return "redirect:/createaccount";
        }
        if (sessionOtp == null || !sessionOtp.equals(otpInput)) {
        	redirectAttributes.addFlashAttribute("error", "Invalid OTP.");
        	 return "redirect:/createaccount";
        }
        if (sessionEmail == null || !sessionEmail.equals(email)) {
        	redirectAttributes.addFlashAttribute("error", "Email does not match the one used for OTP.");
        	 return "redirect:/createaccount";
        }
        Shop shop = new Shop();
        shop.setEmail(email);
        shop.setShopName(shopName);
        shop.setPassword(confirmPassword);
        ShopsDao shopdao=new ShopsDao();
        boolean isregister =shopdao.registerShop(shop);
        if (isregister) {
            session.removeAttribute("otp");
            session.removeAttribute("otpEmail");
            redirectAttributes.addFlashAttribute("success", "Registration successful! Please log in.");
            return "redirect:/login"; 
        } else {
        	 redirectAttributes.addFlashAttribute("error", "Registration failed. Try again.");
        	 return "redirect:/createaccount";
        }
	}

}
