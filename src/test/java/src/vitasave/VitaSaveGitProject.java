package src.vitasave;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class VitaSaveGitProject {
	
	public static WebDriver Browser()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Maha v\\eclipse-workspace\\Selenium\\driver\\chromedriver.exe");
		return new ChromeDriver();
	}
	public static void navigateUrl(WebDriver driver,String URL) throws IOException
	{
		driver.manage().deleteAllCookies();
		driver.get(URL);
		screenShotStore(driver,"vitasaveurl.jpeg");
		driver.manage().window().maximize();
	}
	public static WebElement findByXPathLocator(WebDriver driver,String xpath)
	{
		return driver.findElement(By.xpath(xpath));
	}
	public static void screenShotStore(WebDriver driver,String data) throws IOException
	{
		TakesScreenshot tk=(TakesScreenshot)driver;
		File screenshotAs = tk.getScreenshotAs(OutputType.FILE);
		File desc=new File("C:\\Users\\Maha v\\Desktop\\screenshot\\"+data);
		FileUtils.copyFile(screenshotAs, desc);
	}
	public static JavascriptExecutor returnJsObject(WebDriver driver)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		return js;
	}
	public static void signIn(WebDriver driver) throws IOException, InterruptedException {
		WebElement signInLink = findByXPathLocator(driver, "//li[@class='site-header-account-link']");
		signInLink.click();
		WebElement customer = findByXPathLocator(driver, "//h2[text()='CUSTOMER SERVICE']");
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", customer);
		WebElement userName = findByXPathLocator(driver, "//input[@id='customer_email']");
		userName.sendKeys(ExcelInputRead.getLoginCredentials(0, 0));
		
		WebElement password = findByXPathLocator(driver, "//input[@id='customer_password']");
		password.sendKeys(ExcelInputRead.getLoginCredentials(1, 0));
		screenShotStore(driver,"signIn.jpeg");
		WebElement signIn = findByXPathLocator(driver, "//button[contains(text(),\"Sign in\")]");
		signIn.click();
	}
	public static void ItemSelection(WebDriver driver) throws InterruptedException, IOException, AWTException
	{
		WebElement about = findByXPathLocator(driver, "(//a[contains(text(),'About')])[2]");
		Actions acc=new Actions(driver);
		WebElement shopByCategory = findByXPathLocator(driver, "//a[@id='shopbycat']");
		acc.moveToElement(shopByCategory).perform();
		Thread.sleep(1000);
		WebElement diet = findByXPathLocator(driver, "//a[text()='Diet & Weight Loss']");
		diet.click();
		WebElement searchText = findByXPathLocator(driver, "(//input[@placeholder=\"What are you looking for?\"])[1]");
		searchText.sendKeys("Vega");
		Robot r=new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(1000);
		
		
		WebElement vegaProduct = findByXPathLocator(driver, "//a[contains(text(),'Vega Protein & Greens Chocolate')]");
		Thread.sleep(1000);
		
		vegaProduct.click();
//		WebElement magnesiumProduct = findByXPathLocator(driver, "(//a[@href=\"/collections/canprev/products/canprev-magnesium-bis-glycinate-200\"])[2]");
//		magnesiumProduct.click();
		screenShotStore(driver,"addtocart.jpeg");
		WebElement addToCart = findByXPathLocator(driver, "(//span[contains(text(),'Add to cart')])[1]");
		addToCart.click();
		Thread.sleep(2000);
		WebElement checkout = findByXPathLocator(driver, "(//a[contains(text(),'Checkout')])[2]");
		checkout.click();
		screenShotStore(driver,"summary.jpeg");
		WebElement itemName = findByXPathLocator(driver, "//span[contains(text(),'Vega Protein & Greens Chocolate')]");
		String productName = itemName.getText();
		WebElement itemPrice = findByXPathLocator(driver, "(//span[text()='$29.99'])[2]");
		String productPrice = itemPrice.getText();
		ExcelOutputWrite.printItemDetailsFromExcel(productName, productPrice);
	}
	public static void main(String[] args) throws IOException, InterruptedException, AWTException {
		// TODO Auto-generated method stub
		WebDriver driver = Browser();
		navigateUrl(driver, "https://www.vitasave.ca/");
		signIn(driver);
		ItemSelection(driver);
		driver.quit();
	}

}
