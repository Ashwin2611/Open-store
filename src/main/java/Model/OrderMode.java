package Model;
 
import org.springframework.data.mongodb.core.mapping.Document;
 
@Document(collection="Orders")
public class OrderMode {
	private long OrderId;
	private String userName;
	private String productName;
	private long total_price;
	
	public long getOrderId() {
		return OrderId;
	}
	public void setOrderId(long orderId) {
		OrderId = orderId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public long getTotal_price() {
		return total_price;
	}
	public void setTotal_price(long total_price) {
		this.total_price = total_price;
	}
 
}
 
