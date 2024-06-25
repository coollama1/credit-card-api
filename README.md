# Credit Card Validator Api

<br>

The credit card validator API accepts requests with a request parameter "cardNumber" for the credit card number being validated. A JSON response is returned with the values isCardValid showing whether the card is valid, an array of validation error messages showing why the card is not valid, and "cardType", which displays the type of credit card provided.
<br><br>

### Swagger Examples
  
<img src="images/valid-credit-card-validation-swagger.PNG" width="1000" height="700">
The above picture shows a <strong>valid</strong> response for credit card #: <strong>5455 0049 4554 7488</strong>
<br><br><br>

<img src="images/invalid-credit-card-validation-swagger.PNG" width="1000" height="700"> 
The above picture shows an <strong>invalid</strong> response for credit card #: <strong>182 5227 7110 1601 7970</strong> 

<br><br>
### Postman Examples

![](images/invalid-credit-card-request.png)
<br>

The above picture displays an invalid response

<br>

![](images/valid-credit-card-request.png)
<br>

The above picture shows a valid response

<br>


## Testing

<br>

![](images/unit-tests.png)
<br>

A total of 35 unit tests were run to test the classes within the project, and all have passed successfully.
<br>
Below some of the specific tests that were run

![](images/validator-tests.png)

<br>

![](images/payment-system-tests.png)
