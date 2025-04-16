package com.ninza.hrm.JsonUtility;

import java.util.List;

import com.jayway.jsonpath.JsonPath;
import com.ninza.hrm.FileUtility.PropertyFileUtility;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class JsonUtility {
	PropertyFileUtility plib=new PropertyFileUtility();
	/**
	 * get the JsonData from based on complex xpath
	 * @param resp
	 * @param jsonxpath
	 * @return
	 */
public String getDataOnJsonPath(Response resp,String jsonXpath) {
	List<Object>list=JsonPath.read(resp.asString(),jsonXpath);
	return list.get(0).toString();
	
}
/*get Xml Data from based on xml Complex Xpath
 * @param resp
 * @param xmlPath
 * Return
 */
public String getDataOnXmlXpath(Response resp,String xmlXpath) {
	return resp.xmlPath().get(xmlXpath);
}


public boolean verifyDataOnJsonPath(Response resp,String jsonXpath,String expectedData) {
	List<String>list=JsonPath.read(resp.asString(),jsonXpath);
	boolean flag=false;
	for(String str:list) {
		if(str.equals(expectedData)) {
			System.out.println(expectedData   + "is available====>PASS ");
			flag=true;
		}
		if(flag==false) {
			System.out.println(expectedData   + "is not available====>Failed ");
		}
	}
	return flag;
}

public String getAccessToken() throws Exception {
	
	Response resp= given()
	
			.formParam("client_id", plib.getDataFromPropertiesFile("clientId"))
			.formParam("client_secret", plib.getDataFromPropertiesFile("ClientSceret"))
			.formParam("grant_type", "client_credentials")
			.when()
			.post("http://49.249.28.218.8180/auth/realms/ninza/protocol/openid-connect/token");
	resp.then().log().all();
	//capture data from response
	String token=resp.jsonPath().get("access_token");
return token;
}
}
