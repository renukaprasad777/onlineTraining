package differentWaysToPassBody;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class UsingExtFile {

	@Test
	public void createUser() {
		File f=new File("./src/test/resources/body.json");
		
		RestAssured.given().contentType(ContentType.JSON)
		.body(f).log().all()
		.when().post("https://reqres.in/api/users")
		.then().assertThat().statusCode(201).log().all();
	}
}
