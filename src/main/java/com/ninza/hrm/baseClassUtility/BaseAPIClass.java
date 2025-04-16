package com.ninza.hrm.baseClassUtility;

import java.sql.SQLException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.ninja.hrm.JavaUtility.JavaUtility;
import com.ninza.hrm.DatabaseUtility.DataBaseUtility;
import com.ninza.hrm.FileUtility.PropertyFileUtility;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseAPIClass {
	public static JavaUtility jlib = new JavaUtility();
	public static PropertyFileUtility plib = new PropertyFileUtility();
	public static DataBaseUtility dlib = new DataBaseUtility();
	public static RequestSpecification specReqObj;
	public  static ResponseSpecification specRespObj;

	@BeforeSuite
	public void configBS() throws Exception {
		dlib.getdbConnection();
		System.out.println("=============================> Connect to DataBase<==============================");
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setContentType(ContentType.JSON);
		// builder.setAuth("username","password");
		// builder.addHeader("", "");
		builder.setBaseUri(plib.getDataFromPropertiesFile("BASEUri"));
		specReqObj = builder.build();
		ResponseSpecBuilder resbuilder = new ResponseSpecBuilder();
		resbuilder.expectContentType(ContentType.JSON);
		specRespObj = resbuilder.build();

	}
	
	@AfterSuite
	public void configAS() throws SQLException {
		dlib.closedbConnection();
		System.out.println("=============================> Disconnect to DataBase<==============================");
	}
}
