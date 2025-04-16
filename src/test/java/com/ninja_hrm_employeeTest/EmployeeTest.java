package com.ninja_hrm_employeeTest;
import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ninja.hrm.JavaUtility.JavaUtility;
import com.ninja.hrm.PojoClassUtility.EmployeePojo;
import com.ninja.hrm.PojoClassUtility.ProjectPojoClass;
import com.ninza.hrm.DatabaseUtility.DataBaseUtility;
import com.ninza.hrm.FileUtility.PropertyFileUtility;
import com.ninza.hrm.baseClassUtility.BaseAPIClass;
import com.ninza.hrm.endpoints.IEndpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class EmployeeTest extends BaseAPIClass {
	EmployeePojo epoj;
	ProjectPojoClass Pobj;
	@Test
	public void addEmpolyee() throws Exception {
		int ran = jlib.getRandomNumber();
		String projectName = "Rafeal" + ran;
		String userName = "Mother Thressa" + ran;
		String name="Rock"+ran;
		
		// Add the Project
		Pobj = new ProjectPojoClass(projectName, "Dashrath", "created", 0);
		given()
		.spec(specReqObj)
		.body(Pobj)
		.when()
		.post(IEndpoints.ADDproj)
		.then()
		.spec(specRespObj)
				.log().all();
		// add a employee
		epoj = new EmployeePojo("Architect", "07/12/1989", "dashrath.rathod71289@gmail.com", userName, 5, "9876767675",
				projectName, "ROLE_ADMIN",name);
		Response employeeResponseBody = given()
				.spec(specReqObj)
				.body(epoj)
				.when()
				.post(IEndpoints.ADDEmp);
		employeeResponseBody.then().assertThat().statusCode(201).assertThat()
				// .time(Matchers.lessThan(3000L))
				.body("msg", Matchers.equalTo("Employee Added Successfully"))
				.spec(specRespObj)
				.log().all();
		// Verify the Project is addted to the DataBase(Database Layer Testing....
		boolean flag=dlib.executeQueryVerifyAndGetData("select * from employee", 11, name);
		Assert.assertTrue(flag,"Projectis not added to the DataBase");	
		}
	@Test
	public void addEmployeeWithoutMail() throws Exception {
		int ran = jlib.getRandomNumber();
		String projectName = "Zapuka" + ran;
		String userName = "Zupaka" + ran;
		// Add the Project
		Pobj = new ProjectPojoClass(projectName, userName, "created", 0);
		given()
		.spec(specReqObj)
		.body(Pobj)
		.when()
		.post(IEndpoints.ADDproj)
		.then()
		.spec(specRespObj)
		.log().all();
		epoj = new EmployeePojo("Architect", "32/12/1500", "", userName, 5, "9876767675",
				projectName, "ROLE_ADMIN",userName);
		Response employeeResponseBody = given()
				.spec(specReqObj)
				.body(epoj).when()
				.post(IEndpoints.ADDEmp);
		employeeResponseBody.then().statusCode(500)
		.spec(specRespObj)
		.log().all();
	}
}
