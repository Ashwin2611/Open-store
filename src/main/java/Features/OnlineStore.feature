Feature: Online Store API

Scenario: Get All Product Details
Given Enter the url      
When get the JWT Token
Then get the product details      
And validate the status code      
And validate the Product name       
And Validate the price 

Scenario: Without Using Token
Given Enter the url
When get the product details
Then Error Handling

