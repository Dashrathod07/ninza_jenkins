package com.ninja.hrm.projectTest;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ninja.hrm.PojoClassUtility.ProjectPojoClass;
import com.ninza.hrm.baseClassUtility.BaseAPIClass;
import com.ninza.hrm.endpoints.IEndpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProjectTest extends BaseAPIClass {
	
	ProjectPojoClass Pobj;
	@Test
	public void addSingleProjectWithCreated() throws Exception {
		int ran = jlib.getRandomNumber();
		String projectName = "Mirage" + ran;
		 Pobj = new ProjectPojoClass(projectName,"obama", "created", 0);
		Response createdprojectresponse = given()
				.spec(specReqObj)
				.body(Pobj).when()
				.post(IEndpoints.ADDproj);
		createdprojectresponse.then().assertThat().statusCode(201)
				// .assertThat().time(Matchers.lessThan(3000))
				.spec(specRespObj)
				.log().all();
		Object actMsg = createdprojectresponse.jsonPath().get("msg");
		String expMsg = "Successfully Added";
		Assert.assertEquals(expMsg, actMsg);
		// Verify the Project is addted to the DataBase(Database Layer Testing....
		//select * from project
		boolean flag=dlib.executeQueryVerifyAndGetData("select * from project", 4, projectName);
		Assert.assertTrue(flag,"Projectis not added to the DataBase");
		}


	@Test(dependsOnMethods = "addSingleProjectWithCreated")
	public void craeteDuplicateProjectTest() throws Exception {
		given()
		.spec(specReqObj)
		.body(Pobj).when()
		.post(IEndpoints.ADDproj)
.then().assertThat().statusCode(409)
		// .assertThat().time(Matchers.lessThan(3000))
		.assertThat().contentType(ContentType.JSON)
		.spec(specRespObj)
		.log().all();
	}
}