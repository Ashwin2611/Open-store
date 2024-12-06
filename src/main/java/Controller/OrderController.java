package Controller;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.example.onlineStore.MyEntity;
 
import Model.OrderMode;
import Model.UsersModel;
import Services.OrderServices;
import Services.UserService;
 
@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderServices service;
	
	@GetMapping("/getOrders")
	public ResponseEntity<?> getOrder(){
		List<OrderMode> products=service.getAllOrder();
		if(products.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		System.out.println("return");
		return ResponseEntity.ok(products);
	}
	
	@PostMapping("/addproduct")
	public ResponseEntity<?> postUser(@RequestBody OrderMode product){
		System.out.println("users");
		String userorder=service.checkUser(product);
		String products=service.checkProduct(product);
		System.out.println(products);
		if(products.equals("valid") && userorder.equals("valid")) {
			long getordId=service.getOrdId();
			product.setOrderId(getordId+1);
			long getTotalPrice=service.setPrice(product);
			product.setTotal_price(getTotalPrice);
			OrderMode users=service.addProd(product);
			System.out.println(users.getProductName());
			return ResponseEntity.ok(users);	
		}else {
			return ResponseEntity.badRequest().body("Message:"+"Product Not Present");
		}
		
	}
}