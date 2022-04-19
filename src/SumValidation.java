import org.testng.Assert;
import org.testng.annotations.Test;

import com.files.Payload;

import io.restassured.path.json.JsonPath;

public class SumValidation {



	@Test
	public void validatePrice() {

		int sum = 0;

		JsonPath js = new JsonPath(Payload.courseDetails());
		int courseCount =	js.getInt("courses.size()");


		for (int i = 0; i < courseCount; i++) {

			int courcesPrice = js.getInt("courses["+i+"].price");
			int soldCopies = js.getInt("courses["+i+"].copies");

			int amount = courcesPrice * soldCopies;

			System.out.println("Total amount are : " + amount);

			sum = sum + amount ;


		}

		System.out.println("Total sum price is : "+ sum);


		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		
		Assert.assertEquals(sum, purchaseAmount, "Purchase amount not matching with sum of the price amount");

	}

}
