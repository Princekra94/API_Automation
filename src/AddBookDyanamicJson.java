import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.files.CommonPath;
import com.files.Payload;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class AddBookDyanamicJson {

	//Add Book

	@Test(dataProvider = "BooksData")
	public void addBooks(String isbn, String aisle) {

		RestAssured.baseURI = "http://216.10.245.166";

		String responce = given().log().all().header("Content-Type", "application/json").
				body(Payload.addBook(isbn, aisle)).
				when().post("/Library/Addbook.php").
				then().log().all().assertThat().statusCode(200).
				extract().response().asString();

		JsonPath js = CommonPath.rawTojson(responce);

		String IdNumber =js.get("ID");
		System.out.println("Added book id: "+ IdNumber);


		given().log().all().header("Content-Type", "text/plain").
		body("+{IdNumber}+").
		when().delete("/Library/DeleteBook.php").
		then().log().all().assertThat().statusCode(200).
		extract().response().asString();
		

	}


	//Delete Book

	@Test (dependsOnMethods = { "addBooks" })
	public void deleteBooks(String isbn, String aisle) {
		
		RestAssured.baseURI = "http://216.10.245.166";

		given().log().all().header("Content-Type", "text/plain").
		body("+IdNumber+").
		when().delete("/Library/DeleteBook.php").
		then().log().all().assertThat().statusCode(200).
		extract().response().asString();

	}


	@DataProvider(name="BooksData")
	public Object[][] getData() {


		return	new Object[][] {{"okhf", "9008"}, {"hyty","9754"}, {"kloi", "2841"}};
	}

}
