 package com.ninja.hrm.WebdriverUtility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Set;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility 
{
	
	//implicitly Wait
public void waitForPageToLoad(WebDriver driver)
{
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
}

//Explicitly Wait
public void waitForElementPresent(WebDriver driver,WebElement element )
{
	WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(20));
	wait.until(ExpectedConditions.visibilityOf(element));
	
}

public void explicitWaitUsingUrl(WebDriver driver , String url)
{
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
	wait.until(ExpectedConditions.urlContains(url));
}
public void explicitWaitUsingTitle(WebDriver driver , String title)
{
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
	wait.until(ExpectedConditions.titleContains(title));
}
public void explicitWaitUsingElementClickable(WebDriver driver , WebElement ele)
{
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
	wait.until(ExpectedConditions.elementToBeClickable(ele));
}
public void explicitWaitUsingLocator(WebDriver driver , By locator)
{
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
	wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
}
public void explicitWaitUsingTextInAElement(WebDriver driver ,WebElement ele, String text)
{
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
	wait.until(ExpectedConditions.textToBePresentInElement(ele, text));
}

//Switch the Control Between the windows
public void switchToWindowByURL(WebDriver driver , String partialUrl)
{
	Set<String> allwh = driver.getWindowHandles();
	for (String wh : allwh) 
	{
		driver.switchTo().window(wh);
		String url = driver.getCurrentUrl();
		if(url.contains(partialUrl)) 
		{
			break;
		}
	}
}
public void switchToWindowByTitle(WebDriver driver , String partialTitle)
{
	Set<String> allwh = driver.getWindowHandles();
	for (String wh : allwh) 
	{
		driver.switchTo().window(wh);
		String title = driver.getTitle();
		if(title.contains(partialTitle)) 
		{
			break;
		}
	}
}

/*public void switchToNewBrowserTabOnURL(WebDriver driver,String partialUrl)
{
	Set<String> set = driver.getWindowHandles();
	Iterator<String> it = set.iterator();
	while(it.hasNext())
	{
		String windowID=it.next();
		driver.switchTo().window(windowID);
		String Acturl=driver.getCurrentUrl();
		if(Acturl.contains(partialUrl))
		{
			break;
		}
	}
}
public void switchToNewBrowserTabOnTital(WebDriver driver,String partialTitle)
{
	Set<String> set = driver.getWindowHandles();
	Iterator<String> it = set.iterator();
	while(it.hasNext())
	{
		String windowID=it.next();
		driver.switchTo().window(windowID);
		String ActTitle=driver.getTitle();
		if(ActTitle.contains(partialTitle))
		{
			break;
		}
	}
}*/

//DropDowns
public void select(WebElement element, String text)
{
	Select sel= new Select(element);
	sel.selectByVisibleText(text);
}

public void select(WebElement element, int index)
{
	Select sel= new Select(element);
	sel.deselectByIndex(index);
}

public void handleDropdownPartialText(WebElement ele,String parialText)
{
	Select s=new Select(ele);
	s.selectByContainsVisibleText(parialText);
}
public void handleDropdownDeselectAll(WebElement ele)
{
	Select s=new Select(ele);
	s.deselectAll();
}
public void handleDropdownDeselectByVisibleText(WebElement ele,String text)
{
	Select s=new Select(ele);
	s.deselectByVisibleText(text);
}
public void handleDropdownDeselectByIndex(WebElement ele,int index)
{
	Select s=new Select(ele);
	s.deselectByIndex(index);
}
public void handleDropdownGetOptions(WebElement ele)
{
	Select s=new Select(ele);
	s.getOptions();
}
public void handleDropdownAllSelectedOptions(WebElement ele)
{
	Select s=new Select(ele);
	s.getAllSelectedOptions();
}
public void handleDropdownFirstSelectedOption(WebElement ele)
{
	Select s=new Select(ele);
	s.getFirstSelectedOption();
}
public boolean isMultiple(WebElement ele)
{
	Select s=new Select(ele);
	boolean result = s.isMultiple();
	return result;
}

//ActionClass
public void mouseMoveOnElement(WebDriver driver,WebElement element)
{
	Actions act = new Actions(driver);
	act.moveToElement(element).perform();
}
public void doubleClick(WebDriver driver,WebElement element)
{
	Actions act = new Actions(driver);
	act.doubleClick(element).perform();
}
public void rightClick(WebDriver driver , WebElement ele)
{
	Actions act=new Actions(driver);
	act.contextClick(ele).perform();
}
public void rightClickAndOpenInNewTab(WebDriver driver,WebElement ele) throws AWTException
{
	Actions act=new Actions(driver);
	act.contextClick(ele).perform();
	Robot r=new Robot();
	r.keyPress(KeyEvent.VK_T);
	r.keyRelease(KeyEvent.VK_T);
}
public void dragAndDrop(WebDriver driver , WebElement src,WebElement dst)
{
	Actions act=new Actions(driver);
	act.dragAndDrop(src, dst).perform();
}
public void dragAndDropBy(WebDriver driver , WebElement src,int x,int y)
{
	Actions act=new Actions(driver);
	act.dragAndDropBy(src, x, y).perform();
}
public void scrollToElement(WebDriver driver , WebElement ele)
{
	Actions act=new Actions(driver);
	act.scrollToElement(ele).perform();
}
public void scrollByAmount(WebDriver driver , int x,int y)
{
	Actions act=new Actions(driver);
	act.scrollByAmount(x, y).perform();
}
public void scrollToElementAndClick(WebDriver driver , WebElement ele)
{
	Actions act=new Actions(driver);
	act.scrollToElement(ele).click().perform();
}
public void sendKeys(WebDriver driver , WebElement ele,String text)
{
	Actions act=new Actions(driver);
	act.moveToElement(ele).click().sendKeys(text).perform();
}
public void clickAndHold(WebDriver driver , WebElement ele)
{
	Actions act=new Actions(driver);
	act.clickAndHold(ele).perform();
}
//JavaScriptExecutor
public void scrollUsingJSE(WebDriver driver,int x,int y)
{
	JavascriptExecutor js=(JavascriptExecutor) driver;
	js.executeScript("window.scrollBy("+x+","+y+")");
}
public void handleDisableElement(WebDriver driver , String id , String data)
{
	JavascriptExecutor js=(JavascriptExecutor) driver;
	js.executeScript("document.getElementById("+id+").value="+data+"");
}
public void scrollToElementUsingJSE(WebDriver driver,WebElement ele)
{
	JavascriptExecutor js=(JavascriptExecutor) driver;
	js.executeScript("arguments[0].scrollIntoView();",ele);
}
//Alert popup
public void AlertPopupAccept(WebDriver driver)
{
	Alert alert = driver.switchTo().alert();
	alert.accept();
}
public void AlertPopupDismiss(WebDriver driver)
{
	Alert alert = driver.switchTo().alert();
	alert.dismiss();
}
public void AlertPopupText(WebDriver driver)
{
	Alert alert = driver.switchTo().alert();
	alert.getText();
}
public void AlertPopupSendKeys(WebDriver driver,String keysToSend)
{
	Alert alert = driver.switchTo().alert();
	alert.sendKeys(keysToSend);
}
//Frames
public void switchToFrameByIndex(WebDriver driver, int index)
{
	driver.switchTo().frame(index);
}
public void switchToFrameById(WebDriver driver, String id)
{
	driver.switchTo().frame(id);
}
public void switchToFrameByElement(WebDriver driver, WebElement ele)
{
	driver.switchTo().frame(ele);
}
public void switchToParentFrame(WebDriver driver)
{
	driver.switchTo().parentFrame();
}
public void switchToDefaultFrame(WebDriver driver)
{
	driver.switchTo().defaultContent();
}
//Minimize and Maximize
public void maximize(WebDriver driver)
{
	driver.manage().window().maximize();
}
public void manimize(WebDriver driver)
{
	driver.manage().window().minimize();
}

//open empty window
public void openNewEmptyTab(WebDriver driver)
{
	driver.switchTo().newWindow(WindowType.TAB);
}

}
