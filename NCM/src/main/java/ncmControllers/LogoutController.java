package ncmControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LogoutController {
	@GetMapping("/logout")
	public String doLogout(HttpServletRequest request) {
		 HttpSession session = request.getSession(false); // false = do not create if not exists

	        if (session != null) {
	            session.invalidate(); // Invalidate entire session
	        }
		 return "Login";
	}

}
