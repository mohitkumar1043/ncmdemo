package ncmControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class GetCreateAccountController {
	
	@GetMapping("/createaccount")
    
    public String getCreateAccount() {
       return "CreateAccount";
    }

}
