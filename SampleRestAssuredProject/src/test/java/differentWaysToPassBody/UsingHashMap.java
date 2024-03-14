package differentWaysToPassBody;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class UsingHashMap {

	@Test
	public void createUser() {
		
		HashMap cb=new HashMap();
		cb.put("name", "Virat");
		cb.put("job", "cricketer");
		
		RestAssured.given().contentType(ContentType.JSON)
		.body(cb).log().all()
		.when().post("https://reqres.in/api/users")
		.then().assertThat().statusCode(201).log().all();
	}
}
