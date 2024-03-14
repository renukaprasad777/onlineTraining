package petstore;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

public class Pet {
	String baseURL="https://petstore.swagger.io/v2";
	long petId;

	//create pet using HashMap
	@Test(priority = 1)
	public void createPet() {

		HashMap cat=new HashMap();
		cat.put("name", "dog");

		HashMap cb=new HashMap();
		cb.put("category", cat);
		cb.put("name", "Scooby");
		cb.put("status", "available");

		String createBodyResp = RestAssured.given().contentType(ContentType.JSON)
				.body(cb).log().all()
				.when().post(baseURL+"/pet")
				.then().assertThat().statusCode(200).log().all()
				.extract().response().asString();

		System.out.println(createBodyResp);

		JsonPath js=new JsonPath(createBodyResp);
		petId=js.get("id");
		System.out.println(petId);
		
	}

	//getPet
	@Test(priority = 2)
	public void getPet() {
		
		RestAssured.given().log().all()
		.when().get(baseURL+"/pet/"+petId)
		.then().assertThat().statusCode(200).log().all();
		}
	//updatePet
	@Test(priority = 3)
	public void updatePet() {
		HashMap cat=new HashMap();
		cat.put("name", "dog");

		HashMap ub=new HashMap();
		ub.put("category", cat);
		ub.put("name", "Bruno");
		ub.put("status", "unavailable");
		ub.put("id", petId);
		
		RestAssured.given().contentType(ContentType.JSON)
		.body(ub).log().all()
		.when().put(baseURL+"/pet")
		.then().assertThat().statusCode(200).log().all();
		}
	//deletPet
	@Test(priority = 4)
	public void deletePet() {
		RestAssured.given().log().all()
		.when().delete(baseURL+"/pet/"+petId)
		.then().assertThat().statusCode(200).log().all();
	}

}
