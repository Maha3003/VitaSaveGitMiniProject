package src.vitasave;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class VitaSaveGitProject {
	
	public static WebDriver Browser()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Maha v\\eclipse-workspace\\Selenium\\driver\\chromedriver.exe");
		return new ChromeDriver();
	}
	public static void navigateUrl(WebDriver driver,String URL)
	{
		driver.manage().deleteAllCookies();
		driver.get(URL);
		driver.manage().window().maximize();
	}
	public static WebElement findByXPathLocator(WebDriver driver,String xpath)
	{
		return driver.findElement(By.xpath(xpath));
	}
	public static void signIn(WebDriver driver) throws IOException {
		WebElement signInLink = findByXPathLocator(driver, "//li[@class='site-header-account-link']");
		signInLink.click();
		WebElement userName = findByXPathLocator(driver, "//input[@id='customer_email']");
		userName.sendKeys(ExcelInputRead.getLoginCredentials(0, 0));
		WebElement password = findByXPathLocator(driver, "//input[@id='customer_password']");
		password.sendKeys(ExcelInputRead.getLoginCredentials(1, 0));
		WebElement signIn = findByXPathLocator(driver, "//button[contains(text(),\"Sign in\")]");
		signIn.click();
	}
	public static void ItemSelection(WebDriver driver) throws InterruptedException, IOException
	{
		WebElement magnesiumProduct = findByXPathLocator(driver, "(//a[@href=\"/collections/canprev/products/canprev-magnesium-bis-glycinate-200\"])[2]");
		magnesiumProduct.click();
		WebElement addToCart = findByXPathLocator(driver, "(//span[contains(text(),'Add to cart')])[1]");
		addToCart.click();
		Thread.sleep(2000);
		WebElement checkout = findByXPathLocator(driver, "(//a[contains(text(),'Checkout')])[2]");
		checkout.click();
		WebElement itemName = findByXPathLocator(driver, "//span[contains(text(),'CanPrev')]");
		String productName = itemName.getText();
		WebElement itemPrice = findByXPathLocator(driver, "(//span[text()='$35.99'])[2]");
		String productPrice = itemPrice.getText();
		ExcelOutputWrite.printItemDetailsFromExcel(productName, productPrice);
	}
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = Browser();
		navigateUrl(driver, "https://www.vitasave.ca/");
		signIn(driver);
		ItemSelection(driver);
		driver.quit();
	}

}
