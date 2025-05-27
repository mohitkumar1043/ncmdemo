package ncmControllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dao.ProductDao;
import dao.ShopLocationsDao;
import dao.ShopsDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class SearchController {
	@GetMapping("search")
	 public String doLogin(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String search =request.getParameter("search");
		double ulatitude = Double.parseDouble(request.getParameter("latitude"));
	    double  ulongitude =Double.parseDouble( request.getParameter("longitude"));
	    session.setAttribute("latitude",ulatitude);
        session.setAttribute("longitude",ulongitude);
		ProductDao pdao=new ProductDao();
		ArrayList<String> emails= pdao.getEmail(search);
		ShopLocationsDao shoplocationsdao=new ShopLocationsDao();
		HashMap<String,String[]>shoplocations= shoplocationsdao.getShopLocations(emails);
		ShopsDao shopdao=new ShopsDao();
		HashMap<String ,String>shopnames=shopdao.getShopNames(emails);
		HashMap<String,Double>distances=new HashMap<>();
		for(int i=0;i<emails.size();i++) {
			String email=emails.get(i);
			double  slatitude =Double.parseDouble(shoplocations.get(email)[0]);
			double  slongitude =Double.parseDouble(shoplocations.get(email)[1]);
			double distance =haversine(ulatitude,ulongitude,slatitude,slongitude);
			distances.put(email,distance);
			
		}
		List<Map.Entry<String, Double>> entries = new ArrayList<>(distances.entrySet());
		entries.sort(Map.Entry.comparingByValue());
		  Map<String, Double> sortedMap = new LinkedHashMap<>();
	        for (Map.Entry<String, Double> entry : entries) {
	            sortedMap.put(entry.getKey(), entry.getValue());
	        }
	        	
	        model.addAttribute("sortedByDistances", sortedMap);
	        model.addAttribute("shopnames", shopnames);
	return"Shopes";
	 }
	
	public static double haversine(double lat1, double lon1, double lat2, double lon2) {
	    final int R = 6371; // Radius of the Earth in km
	    double dLat = Math.toRadians(lat2 - lat1);
	    double dLon = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
	               Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
	               Math.sin(dLon / 2) * Math.sin(dLon / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    return R * c; // Distance in km
	}
}
