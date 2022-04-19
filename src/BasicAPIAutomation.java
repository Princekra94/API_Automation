import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import com.files.CommonPath;


public class BasicAPIAutomation {

	public static void main(String[] args) throws IOException {

		// Validate POST API

		// Given - All input details.
		// When - Submit the API, resorce and API method
		// Then - Validate the details.
		// Convert the json to string -> content of file can convert into Byte -> convert byte to string

		RestAssured.baseURI = "https://rahulshettyacademy.com/";

		String responce = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(new String (Files.readAllBytes(Paths.get("/Users/prince/eclipse-workspace/API_DEMO_PROJECT/src/addPlace.json")))).
				when().post("maps/api/place/add/json").
				then().statusCode(200).body("scope", equalTo("APP"))
				.header("server", "Apache/2.4.18 (Ubuntu)").extract().asString();

		System.out.println(responce);

		JsonPath js = new JsonPath(responce);
		String PlaceID = js.getString("place_id");

		System.out.println("Added Place Id is = " + PlaceID);


		// Update Place

		String newAddress= "NewYork city USA";

		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").
		body("{\n"
				+ "\"place_id\":\""+PlaceID+"\",\n"
				+ "\"address\":\""+newAddress+"\",\n"
				+ "\"key\":\"qaclick123\"\n"
				+ "}").when().put("maps/api/place/update/json").then().assertThat().log().all().
		statusCode(200).body("msg", equalTo("Address successfully updated"));


		// Get Place

		String getResponce = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", PlaceID).when().get("maps/api/place/get/json").then().assertThat().log().all().
				statusCode(200).extract().response().asString();

		JsonPath js1 = CommonPath.rawTojson(getResponce);

		String asctualAdress = js1.getString("address");

		System.out.println("New updated Address is : "+asctualAdress);
		
		Assert.assertEquals(asctualAdress, newAddress);

	}

}
