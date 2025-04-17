package NinzaTask;

import java.time.Duration;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.ninja.hrm.JavaUtility.JavaUtility;
import com.ninja.hrm.PojoClassUtility.ProjectPojoClass;
import com.ninja.hrm.WebdriverUtility.WebDriverUtility;
import com.ninza.hrm.DatabaseUtility.DataBaseUtility;
import com.ninza.hrm.baseClassUtility.BaseAPIClass;
import com.ninza.hrm.endpoints.IEndpoints;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class taskTest extends BaseAPIClass {
	WebDriverUtility wlib=new WebDriverUtility();
	DataBaseUtility dlib=new DataBaseUtility();
	JavaUtility jlib=new JavaUtility();
	int ran=jlib.getRandomNumber();
	@Test
	public void firstCaseTest() throws Exception {
		EdgeDriver driver=new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://49.249.28.218:8091/");
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("rmgyantra");
		driver.findElement(By.xpath("//input[@id='inputPassword']")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath(" //button[@type='submit']")).click();
		WebElement pro = driver.findElement(By.xpath("//a[text()='Projects']"));
		pro.click();
		driver.findElement(By.xpath("//span[text()='Create Project']")).click();
		
		String projectName = "Barac Obama"+ran;
		driver.findElement(By.xpath("//input[@name='projectName']")).sendKeys(projectName);
		driver.findElement(By.xpath("//input[@name='createdBy']")).sendKeys("Trump");
		WebElement dd = driver.findElement(By.xpath("(//select[@name='status'])[2]"));
		wlib.select(dd, "Created");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		WebElement dd1 = driver.findElement(By.xpath("(//select[@class='form-control'])[1]"));
		wlib.select(dd1, "Search by Project Name");
		driver.findElement(By.xpath("(//input[@class='form-control'])")).sendKeys(projectName);
		String pName = driver.findElement(By.xpath("//td[text()='"+projectName+"']/./..//td[1]")).getText();
		System.out.println(pName);
		driver.close();
		//get the Project from Middle Layer
		Response spec=given()
		.pathParam("projectId",""+pName+"")
	    .spec(specReqObj)
		.when()
		.get("/project/{projectId}");
		spec.then().log().all();
		Object actualData = spec.jsonPath().get("projectId");
		Assert.assertEquals(actualData, pName);
		
		
		// serach the projectName in data base
	dlib.executeQueryVerifyAndGetData("select * from project", 1, pName);
	}
	
	// 
	@Test
	public void secondCaseTest() throws Exception {
		//Create the Data in middle layer
		System.out.println("======================Create Project Using API========================");
		int ran=new Random().nextInt(1000);
		String projectName="Kilvish"+ran;
		ProjectPojoClass pObj=new ProjectPojoClass(projectName,"Shaktimaan", "Created",0);
		Response resp=given()
		.spec(specReqObj)
		.body(pObj)
		.when()
		.post(IEndpoints.ADDproj);
		resp.then()
		.log().all();
		String proId=resp.jsonPath().get("projectId");
		String pName=resp.jsonPath().getString("projectName");
		//DataBase Verification
		System.out.println("====================Verify the Data in DataBase======================");
		dlib.executeQueryVerifyAndGetData("select * from project", 4, pName);
		System.out.println("====================Update Projec IN User Interface=====================");
		
		EdgeDriver driver=new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://49.249.28.218:8091/");
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("rmgyantra");
		driver.findElement(By.xpath("//input[@id='inputPassword']")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath(" //button[@type='submit']")).click();
		WebElement pro = driver.findElement(By.xpath("//a[text()='Projects']"));
		pro.click();
		WebElement dd1 = driver.findElement(By.xpath("(//select[@class='form-control'])[1]"));
		wlib.select(dd1, "Search by Project Name");
		driver.findElement(By.xpath("(//input[@class='form-control'])")).sendKeys(projectName);
		//String pName = driver.findElement(By.xpath("//td[text()='"+projectName+"']/./..//td[1]")).getText();
		driver.findElement(By.xpath("//i[@data-toggle=\"tooltip\"]/../..//a[1]")).click();
		WebElement NewPname = driver.findElement(By.xpath("//div[@class='modal-body']//input[@value='"+projectName+"']"));
		NewPname.clear();
		String PROJECTNAME="Jumanji"+ran;
		NewPname.sendKeys(PROJECTNAME);
		driver.findElement(By.xpath("//input[@class='btn btn-info']")).click();
		WebElement DD1 = driver.findElement(By.xpath("(//select[@class='form-control'])[1]"));
		wlib.select(DD1, "Search by Project Id");
		WebElement ele1 = driver.findElement(By.xpath("(//input[@class='form-control'])[1]"));
		ele1.clear();
		ele1.sendKeys(proId);
		String newPname = driver.findElement(By.xpath("//td[text()='"+proId+"']/../td[2]")).getText();
		Assert.assertEquals(newPname,PROJECTNAME );
		driver.close();
	}
		@Test
		public void thirdCaseTest() throws Exception {
			//create the Project in DataBase Using SQL Query
			System.out.println("==========================Create The Resource Into The DataBase==============================");
			String proId="Black_PROJ_"+ran+System.currentTimeMillis();
			//String projectName="Zordust"+ran;
			String query="insert into project values('Red_PROJ_123','Marcos','17/04/2025','Mickey Mouse','Created','0');";
			dlib.executeNonSelectQuery(query);
			System.out.println("=========================Get Resource in User Interface===================================");
			EdgeDriver driver=new EdgeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.get("http://49.249.28.218:8091/");
			driver.findElement(By.xpath("//input[@id='username']")).sendKeys("rmgyantra");
			driver.findElement(By.xpath("//input[@id='inputPassword']")).sendKeys("rmgy@9999");
			driver.findElement(By.xpath(" //button[@type='submit']")).click();
			WebElement pro = driver.findElement(By.xpath("//a[text()='Projects']"));
			pro.click();
			WebElement dd1 = driver.findElement(By.xpath("(//select[@class='form-control'])[1]"));
			wlib.select(dd1, "Search by Project Id");
			driver.findElement(By.xpath("(//input[@class='form-control'])")).sendKeys("Red_PROJ_123");
			
			
		
		
		
	
		
	
		
		
		
		
	}

}
