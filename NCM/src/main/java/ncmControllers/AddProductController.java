package ncmControllers;

import java.io.InputStream;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import dao.ProductDao;

import jakarta.servlet.http.HttpSession;
import model.Admin;
import model.Product;


@Controller
public class AddProductController {
	 @PostMapping("/AddProduct")
	    public String addProduct( @RequestParam("name") String productName,
                @RequestParam("price") String priceStr,
                @RequestParam("offer") String offerStr,
                @RequestParam("image") MultipartFile imageFile,
	                 HttpSession session, RedirectAttributes redirectAttributes
	                             ) {
		 if((Admin)session.getAttribute("admin")==null) {
			 return "redirect:/login";
		 }
		 
		// Validate input
	        if (productName == null || priceStr == null || offerStr == null || productName.isEmpty() || priceStr.isEmpty() || offerStr.isEmpty()) {
	        	 redirectAttributes.addFlashAttribute("error", "please try again!");
		             return "redirect:/getaddnewproductpage";
	          
	        }

	        BigDecimal productPrice = new BigDecimal(priceStr);
	        BigDecimal discountOffer = new BigDecimal(offerStr);

	       
	        if (imageFile == null || imageFile.isEmpty()) {
	        	 redirectAttributes.addFlashAttribute("error", "please try again!");
	             return "redirect:/getaddnewproductpage";
          
	        }
		 try {
	         // Original image
	            BufferedImage originalImage = ImageIO.read(imageFile.getInputStream());
	         // Resize with padding (not crop)
	            BufferedImage resizedImage = resizeWithPadding(originalImage, 300, 300);
	            
	         // Convert to InputStream
	            ByteArrayOutputStream os = new ByteArrayOutputStream();
	            ImageIO.write(resizedImage, "png", os);
	            

	            
	            Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
	                "cloud_name", "",
	                "api_key", "",
	                "api_secret", ""
	            ));

	            Map uploadResult = cloudinary.uploader().upload(os.toByteArray(),
	            	    ObjectUtils.asMap(
	            	        "resource_type", "image",
	            	        "folder", "ncm_products",
	            	        "public_id", UUID.randomUUID().toString()
	            	    )
	            	);

	            String imageUrl = uploadResult.get("secure_url").toString();

	            Admin admin=(Admin)session.getAttribute("admin");
	           Product p=new Product() ;
	          p.setEmail(admin.getEmail());
	          p.setName(productName);
	          p.setPrice(productPrice);
	          p.setDiscountOffer(discountOffer);
	          p.setImgurl(imageUrl);
	          ProductDao pDao=new ProductDao();
	          boolean isSave =pDao.storeProduct(p);
	          if (isSave) {
	        	  redirectAttributes.addFlashAttribute("success", "Product saved successful");
	        	  return "redirect:/getaddnewproductpage";
	          } else {
	        	  redirectAttributes.addFlashAttribute("error", "please try again!");
		             return "redirect:/getaddnewproductpage";
	          }
	               
		 }
	catch (Exception e){
         
         e.printStackTrace();
         redirectAttributes.addFlashAttribute("error", "please try again!");
         return "redirect:/getaddnewproductpage";
  
     }
		 
	 }
		 
	// Resizing with white padding
	    private BufferedImage resizeWithPadding(BufferedImage original, int targetWidth, int targetHeight) {
	        int originalWidth = original.getWidth();
	        int originalHeight = original.getHeight();

	        double scale = Math.min((double) targetWidth / originalWidth, (double) targetHeight / originalHeight);
	        int scaledWidth = (int) (scale * originalWidth);
	        int scaledHeight = (int) (scale * originalHeight);

	        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
	        Graphics2D g = resizedImage.createGraphics();

	        // White background
	        g.setColor(Color.WHITE);
	        g.fillRect(0, 0, targetWidth, targetHeight);

	        // Center the image
	        int x = (targetWidth - scaledWidth) / 2;
	        int y = (targetHeight - scaledHeight) / 2;

	        g.drawImage(original, x, y, scaledWidth, scaledHeight, null);
	        g.dispose();

	        return resizedImage;
	    } 
	 }


