package differentWaysToPassBody;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.CreateUser;

public class UsingPojo {

	@Test
	public void createUser() {
		
		CreateUser cu=new CreateUser();
		cu.setName("Modi");
		cu.setJob("Prime minister");
		
		RestAssured.given().contentType(ContentType.JSON)
		.body(cu).log().all()
		.when().post("https://reqres.in/api/users")
		.then().assertThat().statusCode(201).log().all();
	}
}
