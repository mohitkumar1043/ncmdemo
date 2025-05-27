package ncmControllers;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import dao.ProductDao;

@Controller
public class RemoveProductController {
	@PostMapping("/removeproduct")
    public String removeProduct(@RequestParam("id")int  productid,@RequestParam("imgurl") String imageName,RedirectAttributes redirectAttributes) {
		String folderPath = "C:/Users/mohit/Desktop/NcmProudctImeges/";
	    String imagePath = folderPath + imageName;

	    File file = new File(imagePath);
	    if (file.exists()) {
	        file.delete();
	    }
		ProductDao pdao=new ProductDao();
		boolean  isRemoved=pdao.removeProductById(productid);
		 if (isRemoved) {
	            redirectAttributes.addFlashAttribute("success", "✅ Product removed successfully");
	        } else {
	            redirectAttributes.addFlashAttribute("error", "❌ Failed to remove product");
	        }

	        
	        return "redirect:/getproductlists";
		
	}
}
