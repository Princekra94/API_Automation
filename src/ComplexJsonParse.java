import com.files.Payload;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {


	public static void main(String[] args) {


		JsonPath js = new JsonPath(Payload.courseDetails());

		System.out.println("----------------------------------------------------");
		System.out.println("Print no. of cources returened by API.");

		int courseCount =	js.getInt("courses.size()");
		System.out.println("No. of cources availble : "+ courseCount);


		//Print purchase amount.
		System.out.println("----------------------------------------------------");
		System.out.println("Print purchase amount.");

		int totalPurchaseAmount =	js.get("dashboard.purchaseAmount");
		System.out.println("Total Purchase amount : "+totalPurchaseAmount);

		//Print title of the first course
		System.out.println("----------------------------------------------------");
		System.out.println("Print title of the first course");

		String firstCourseTitle =  js.getString("courses[0].title");
		System.out.println("First course title is : "+firstCourseTitle);


		//Print total cources and there respective size.
		System.out.println("----------------------------------------------------");
		System.out.println("Print total cources and there respective size.");


		for (int i = 0; i < courseCount; i++) {

			String courseTitle = js.getString("courses["+i+"].title");

			int courcesPrice = js.getInt("courses["+i+"].price");

			System.out.println("Cource titles are : "+ courseTitle);

			System.out.println("Cources price are : " + courcesPrice);

		}


		//Print number of copies sold in RPA
		
		System.out.println("----------------------------------------------------");
		System.out.println("Print number of copies sold in RPA");

		for (int i = 0; i < courseCount; i++) {

			String courseTitles = js.getString("courses["+i+"].title");

			if(courseTitles.equalsIgnoreCase("RPA")) {

				int soldCopies = js.getInt("courses["+i+"].copies");
				System.out.println("Number of copies sold for RPA : "+soldCopies);

				break;

			}




		}




	}



}
