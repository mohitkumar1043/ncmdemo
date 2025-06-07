package ncmControllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import dao.ProductDao;

@Controller
public class RemoveProductController {
	@PostMapping("/removeproduct")
    public String removeProduct(@RequestParam("id")int  productid,@RequestParam("imgurl") String imageName,RedirectAttributes redirectAttributes) {
		try {
		String publicId = imageName.substring(imageName.indexOf("/ncm_products/") + 1, imageName.lastIndexOf(".png"));
		 Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
	                "cloud_name", "dncdbq7f5",
	                "api_key", "289565295337619",
	                "api_secret", "GOfIfqG3a8Uo9s9RdtIPEaW-eNE"
	            ));
		 
		Map result = cloudinary.uploader().destroy(publicId,
			    ObjectUtils.asMap("resource_type", "image",
			    		 "invalidate", true));
		}
		catch(Exception e) {
			redirectAttributes.addFlashAttribute("error", "❌ Failed to remove product");
			 return "redirect:/getproductlists";

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
