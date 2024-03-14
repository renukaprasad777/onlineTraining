package reqres;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Users {

	String baseUrl="https://reqres.in";
	
	@Test
	public void createUser() {
		//given()- Inputs
		//when() - HTTP method along with URL
		//then() - Validation
		RestAssured.given().body("{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}").contentType(ContentType.JSON)
		.when().post(baseUrl+"/api/users")
		.then().assertThat().statusCode(201).log().all();
	}

	@Test
	public void getSingleUser() {
		RestAssured.given().log().all()
		.when().get(baseUrl+"/api/users/2")
		.then().assertThat().statusCode(200).log().all();
	}
	@Test
	public void getListUsers() {
		
		RestAssured.given()
		.when().get(baseUrl+"/users?page=2")
		.then().assertThat().statusCode(200).log().all();
	}
	
}
