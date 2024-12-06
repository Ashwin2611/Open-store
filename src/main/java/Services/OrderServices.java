package Services;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.example.onlineStore.MyEntity;
import com.example.onlineStore.MyEntityRepository;
 
import Model.OrderMode;
import Model.UsersModel;
import Repo.OrderRepo;
import Repo.UserRepo;
@Service
public class OrderServices {
	
	@Autowired
	private OrderRepo repo;
	
	@Autowired
	private MyEntityRepository repo2;
	
	@Autowired
	private UserRepo repo3;
 
	public String checkProduct(OrderMode product) {
		List<MyEntity> products=repo2.findAll();
		for(MyEntity prod:products) {
			System.out.println(prod.getProductName()+" "+product.getProductName());
			if(prod.getProductName().equals(product.getProductName())) {
				return "valid";
			}
		}
		return "invalid";
	}
	
    public long getOrdId() {
        long inc = 0;
        List<OrderMode> list = getAllOrder();
        for (OrderMode user : list) {
            inc = user.getOrderId();  // Get the latest userId
        }
        return inc;
    }
 
	public List<OrderMode> getAllOrder() {
		// TODO Auto-generated method stub
		List<OrderMode> Order=repo.findAll();
		return Order;
	}
 
	public OrderMode addProd(OrderMode product) {
		return repo.save(product);
		// TODO Auto-generated method stub
	}

	public String checkUser(OrderMode product) {
		// TODO Auto-generated method stub
		List<UsersModel> users=repo3.findAll();
		for(UsersModel user : users) {
			if(user.getEmail().equals(product.getUserName())) {
				return "valid";
			}
		}
		
		return "invalid";
	}

	public long setPrice(OrderMode product) {
		List<MyEntity> products=repo2.findAll();
		for(MyEntity prod:products) {
			System.out.println(prod.getProductName()+" "+product.getProductName());
			if(prod.getProductName().equals(product.getProductName())) {
				return prod.getProductPrice();
			}
		}
		return 0;
	}
 
}