package com.ninja.hrm.projectTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;
import com.ninja.hrm.PojoClassUtility.ProjectPojoClass;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class ProjectTest2 {

	@Test
	public void addSingleProjectWithCreatedTest() throws SQLException {

		Random random = new Random();
		int randNum = random.nextInt(5000);
		
		String expSuccessMsg = "Successfully Added";
		String projectName = "Ducati"+randNum;
		
		//Create an Object to Pojo class 
	ProjectPojoClass pObj = new ProjectPojoClass("Obama", projectName, "Created", 0);

		//Verify the projectName in API Layer 
		Response resp = given().contentType(ContentType.JSON).body(pObj)
		.when().post("http://49.249.28.218:8091/addProject");
		resp.then()
			.assertThat().statusCode(201)
			.assertThat().time(Matchers.lessThan(3000L))
			.assertThat().contentType(ContentType.JSON)
			.log().all();
		
		String actSuccessMsg = resp.jsonPath().get("msg");
		Assert.assertEquals(expSuccessMsg, actSuccessMsg);
		
		//Verify the projectName in DB Layer 
		
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		Connection conn = DriverManager.getConnection("jdbc:mysql://49.249.28.218:3333/ninza_hrm", "root", "root");
		System.out.println("----Connection Successful----");
		ResultSet resultset = conn.createStatement().executeQuery("select * from project");
		
		while(resultset.next()) {
			System.out.println(resultset.getString(4));
		}
		conn.close();
		
	}

}


