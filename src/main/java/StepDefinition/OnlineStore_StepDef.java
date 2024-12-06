package StepDefinition;

import static org.junit.Assert.*;

import java.util.List;

import org.json.simple.JSONObject;

import com.aventstack.extentreports.Status;

import Utils.ExtentReportManager;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;

public class OnlineStore_StepDef {
	Response res;
	RestAssured resp;
	String API;
	String token;
	@Given("Enter the url")
	public void enter_the_url() {
//		API="http://192.168.5.80:8000/products";
		resp.baseURI="http://192.168.5.80:8000";
	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
		ExtentReportManager.getTest().log(Status.INFO,"BaseUrl"+resp.baseURI);
	}

	@When("get the JWT Token")
	public void get_the_jwt_token() {
		JSONObject json=new JSONObject();
		json.put("email","ashwinmurugan1@gmail.com");
		json.put("password", "Ashwin");
		
		res=resp.given().contentType(ContentType.JSON).body(json.toJSONString()).post("/auth/authenticate");
		token=res.jsonPath().getString("token"); // TO extract the token value from object
		System.out.println("TOKEN:"+token);
		ExtentReportManager.getTest().log(Status.INFO, "JWT Token:"+token);
	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
	}

	@Then("get the product details")
	public void get_the_product_details() {
		res=resp.given().headers("Authorization","Bearer "+token).get("/products");
		System.out.println("response:"+res.asPrettyString());
		if(res.statusCode()==200) {
			ExtentReportManager.getTest().log(Status.PASS, "Valid User");
			ExtentReportManager.getTest().log(Status.INFO, "Product Details"+res.asPrettyString());	
		}
	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
	}

	@Then("validate the status code")
	public void validate_the_status_code() {
		System.out.println("Status Code:"+res.statusCode());
	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
		if(res.statusCode()==200) {
			ExtentReportManager.getTest().log(Status.PASS, "Status Code"+res.statusCode());	
		}else {
			ExtentReportManager.getTest().log(Status.FAIL, "Status Code"+res.statusCode());	
		}
	}

	@Then("validate the Product name")
	public void validate_the_Product_name() {
		List<String> productName=res.jsonPath().getList("productName",String.class);
		if(productName.contains("Tank")) {
			System.out.println("Match Found Bike");
			ExtentReportManager.getTest().log(Status.PASS, "Product Name (Match Found)"+productName);
		}else {
			System.out.println("No Match Found");
			ExtentReportManager.getTest().log(Status.FAIL, "Product Name (No Match Found)"+productName);
		}
//		for(String name:productName) {
////			try {
////				assertEquals("No Data",name,"Car");
////				System.out.println("Matched:"+name);
////			}catch(Exception e) {
////				
////				System.out.println(e.getMessage());
////			}
//			if(name.equals("Bike")) {
//				System.out.println("Match Found");
//				prodName++;
//			}
//		}
		
	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
	}

	@Then("Validate the price")
	public void validate_the_price() {
		List<Integer> productPrice=res.jsonPath().getList("productPrice",Integer.class);
		if(productPrice.contains(6900000)) {
			System.out.println("Match Found 2500000");
			ExtentReportManager.getTest().log(Status.PASS, "Product Price (Match Found)"+productPrice);
		}else {
			System.out.println("No Match Found");
			ExtentReportManager.getTest().log(Status.FAIL, "Product Price (No Match Found)"+productPrice);
		}
	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
	}

	@Then("Error Handling")
	public void error_handling() {
		ExtentReportManager.getTest().log(Status.FAIL, "InValid User");
	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
	}
}
