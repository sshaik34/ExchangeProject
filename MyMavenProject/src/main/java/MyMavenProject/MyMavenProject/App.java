
package MyMavenProject.MyMavenProject;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class App {
	private static final String WebDriverManager = null;
	static WebDriver driver;

	public static void main(String[] args) throws Exception {
//		autoIT();
//		textboxValidation();
//		CrunchBistro();
//		autoIT_gmail();
		stockPrice();

	}

	public static void CrunchBistro() throws Exception {
		WebElement element;
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://crunchbistro.com/");
		Thread.sleep(5000);

		List<WebElement> hrefs = driver.findElements(By.tagName("a"));
		System.out.println("Num of links on page: " + hrefs.size());
//		for(WebElement gg: hrefs) {
//			String hrefText = gg.getText();
//			String href = gg.getAttribute("href");
//			System.out.println(href+" > "+hrefText);
//		}

//        Thread.sleep(500000);
		String old_handle = driver.getWindowHandle();
		List<WebElement> li = driver.findElements(By.xpath("//*[@id='masthead']/div/div/div/div/nav/div/ul[1]/li/a"));
		System.out.println("Num of items in Header Menu: " + li.size());
		for (int i = 1; i <= li.size(); i++) {
			String hd = driver
					.findElement(By.xpath("//*[@id='masthead']/div/div/div/div/nav/div/ul[1]/li[" + i + "]/a"))
					.getText();
			System.out.println("List of items in Header: " + hd);
			if (hd.equalsIgnoreCase("ORDER ONLINE")) {
				driver.findElement(By.xpath("//*[@id='masthead']/div/div/div/div/nav/div/ul[1]/li[" + i + "]/a"))
						.click();
			}
		}
		Thread.sleep(5000);

		ArrayList<String> remaining_tabs = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("No. of remaining tabs: " + remaining_tabs.size());
		for (int i = 0; i < remaining_tabs.size(); i++) {
			if (!((remaining_tabs.get(i)).equals(old_handle))) {
				driver.switchTo().window(remaining_tabs.get(i));
//			    	DriverFactory.getDriver().switchTo().window(remaining_tabs.get(i));
				System.out.println("Switched to application tab");
				System.out.println("Title: " + driver.getTitle());
//			    	report.addResult("Switched to application tab successfully", "Pass");
			}

		}

		System.out.println("Title: " + driver.getTitle());
		List<WebElement> lst = driver.findElements(By.xpath("//*[@id='menuItems']/ul"));
		System.out.println("Num of items in Popular Items: " + lst.size());
		for (int i = 1; i <= lst.size(); i++) {
			String hdh = driver.findElement(By.xpath("//*[@id='menuItems']/ul/div[" + i + "]/div/div/h4")).getText();
			System.out.println("List of items: " + hdh);
//			if(hd.equalsIgnoreCase("ORDER ONLINE")) {
//				driver.findElement(By.xpath("//*[@id='masthead']/div/div/div/div/nav/div/ul[1]/li["+i+"]/a")).click();								
//			}
			if (hdh.equalsIgnoreCase("Custom Crunch Salad")) {
				driver.findElement(By.xpath("//*[@id='menuItems']/ul/div[" + i + "]/div/div/h4")).click();
				Thread.sleep(5000);
			}
		}
		List<WebElement> radiobtns = driver.findElements(By.cssSelector("input[type='radio']"));
		System.out.println("List of radio buttons: " + radiobtns.size());
		List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
		System.out.println("List of checkboxes: " + checkboxes.size());
		List<WebElement> button = driver.findElements(By.cssSelector("input[type='button']"));
		System.out.println("List of buttons: " + button.size());
		Thread.sleep(1000);

	}

	public static void textboxValidation() throws InterruptedException {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.html.am/html-codes/textboxes/html-textbox.cfm");
		Thread.sleep(5000);
		driver.findElement(By.xpath("/html/body/div[1]/article/table[1]/tbody/tr[2]/td[2]/textarea")).clear();
		Thread.sleep(5000);
//		driver.findElement(By.xpath("/html/body/div[1]/article/table[1]/tbody/tr[2]/td[2]/textarea")).sendKeys("line1\nline2\nline3");
		driver.findElement(By.xpath("/html/body/div[1]/article/table[1]/tbody/tr[2]/td[2]/textarea"))
				.sendKeys("line1 line2 line3");
		Thread.sleep(5000);
	}

	public static void autoIT() throws InterruptedException, IOException, AWTException {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.draftable.com/compare");
		System.out.println("Title: " + driver.getTitle());
		Thread.sleep(1000);
		driver.switchTo().parentFrame();
		Thread.sleep(2000);
		String txt = driver.findElement(By.xpath("/html/body/div[4]/div/h1")).getText();
		System.out.println("Header Text:  " + txt);
		Thread.sleep(1000);
		driver.switchTo().frame(1);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='id_dropzone_1']/div[1]/div[2]/p[3]")).click();
		Thread.sleep(2000);
		Runtime.getRuntime().exec("C:/AutoIT/firstautoitfile.exe");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='id_dropzone_2']/div[1]/div[2]/p[3]")).click();
		Thread.sleep(2000);
		Runtime.getRuntime().exec("C:/AutoIT/firstautoitfile.exe");
		Thread.sleep(5000);
//		driver.switchTo().parentFrame();
		Thread.sleep(1000);
//		driver.switchTo().frame(1);
		Thread.sleep(1000);
		WebElement element = driver.findElement(By.xpath("//*[@id='compare-button']"));
//		driver.switchTo().frame("41440f91e32d658bbe23fdad839c2c82");		
		Thread.sleep(1000);
		Robot rbt = new Robot();
		rbt.keyPress(KeyEvent.VK_DOWN);
		rbt.keyPress(KeyEvent.VK_DOWN);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='compare-button']")).click();// not working , need to move to that element
		Thread.sleep(2000);

	}

	public static void autoIT_gmail() throws InterruptedException, IOException, AWTException {
		
//		mention below line in the end of target field after u right click on browser 
//		 --remote-debugging-port=9722
				
		System.setProperty("webdriver.chrome.driver","C:/chromeData/chromedriver.exe");
		
		ChromeOptions opt=new ChromeOptions();

		// pass the debuggerAddress and pass the port along with host. Since I am running test on local so using localhost
		opt.setExperimentalOption("debuggerAddress","localhost:9722");
		
//		opt.setExperimentalOption("debuggerUrl","ws://127.0.0.1:9722/devtools/browser/2c3d6cb2-f0e4-4a49-9bf6-e3ba1dfb1462");
		WebDriver dr=new ChromeDriver(opt);

		// now you can use now existing Browser
//		dr.get("http://facebook.com");
//		dr.findElement(By.id("email")).sendKeys("test");
		for(int i=0;i<500;i++) {
			Thread.sleep(1000);
//			dr.findElement(By.xpath("/html/body/div[7]/div[3]/div/div[2]/div[2]/div/div/div/div[1]/div[2]/div[1]/div[2]/div/span/div[3]")).click();
//			Thread.sleep(2000);
//		dr.findElement(By.xpath("//*[@id=':26a']/div[1]/span")).click();
		dr.findElement(By.xpath("/html/body/div[7]/div[3]/div/div[2]/div[2]/div/div/div/div[1]/div[3]/div[1]/div[1]/div/div/div[1]/div/div[1]/div")).click();
		Thread.sleep(2000);
		dr.findElement(By.xpath("/html/body/div[7]/div[3]/div/div[2]/div[2]/div/div/div/div[1]/div[3]/div[1]/div[1]/div/div/div[7]/div/div[4]/div")).click();
		Thread.sleep(2000);
//		dr.findElement(By.xpath("//*[@id=':4']/div/div[1]/div[1]/div/div/div[2]/div[3]")).click();
//		if(dr.findElement(By.xpath("/html/body/div[7]/div[3]/div/div[2]/div[2]/div/div/div/div[1]/div[3]/div[1]/div[1]/div/div/div[2]/div[3]/div")).isEnabled()) {
			dr.findElement(By.xpath("/html/body/div[7]/div[3]/div/div[2]/div[2]/div/div/div/div[1]/div[3]/div[1]/div[1]/div/div/div[2]/div[3]/div")).click();
//		}
		Thread.sleep(3000);
		
		
		
		}
		
		
//		driver = new ChromeDriver();
//		driver.navigate().to("https://gmail.com");
//		Thread.sleep(5000);
//		driver.findElement(By.xpath("//*[@id=':n2']/div/div[2]")).click();
//		Thread.sleep(5000);
		
//		Capabilities cap=((ChromiumDriver) driver).getCapabilities();
//
//		// asMap method will return all capability in MAP
//		Map<String, Object> myCap=cap.asMap();
//
//		// print the map data-
//		System.out.println(myCap);
		
//		driver.manage().window().maximize();
//		driver.get("https:gmail.com");
//		Thread.sleep(5000);
//		driver.findElement(B)
				
		
		
		
	}
	
	public static void stockPrice() throws Exception {
		WebElement element;
		double expValue = 4.2;
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://google.com/");
		// Lucid Stock Price
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='APjFqb']")).sendKeys("Lucid stock price");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='APjFqb']")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		String pr = driver.findElement(By.xpath("//*[@id='knowledge-finance-wholepage__entity-summary']/div[3]/g-card-section/div/g-card-section/div[2]/div[1]/span[1]/span/span[1]")).getText();
		double prr = Double.parseDouble(pr);
		System.out.println("Current Lucid price - "+ prr);
		Thread.sleep(1000);
		if (prr <= expValue) {
			System.out.println("FAIL - Lucid stock price is less than invested amount");
		}else {
			System.out.println("PASS - Lucid stock price is greater than invested amount");
		}
		// Nio Stock Price
		double expValue_Nio = 8.2;
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='APjFqb']")).clear();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='APjFqb']")).sendKeys("Nio stock price");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='APjFqb']")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		String nio = driver.findElement(By.xpath("//*[@id='knowledge-finance-wholepage__entity-summary']/div[3]/g-card-section/div/g-card-section/div[2]/div[1]/span[1]/span/span[1]")).getText();
		double dnio = Double.parseDouble(nio);
		System.out.println("Current Nio price - "+ dnio);
		Thread.sleep(1000);
		if (dnio <= expValue_Nio) {
			System.out.println("FAIL - Nio stock price is less than invested amount");
		}else {
			System.out.println("PASS - Nio stock price is greater than invested amount");
		}
		// AMC Stock Price
		double expValue_AMC = 10.2;
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='APjFqb']")).clear();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='APjFqb']")).sendKeys("AMC stock price");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='APjFqb']")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		String amc = driver.findElement(By.xpath("//*[@id='knowledge-finance-wholepage__entity-summary']/div[3]/g-card-section/div/g-card-section/div[2]/div[1]/span[1]/span/span[1]")).getText();
		double damc = Double.parseDouble(amc);
		System.out.println("Current AMC price - "+ damc);
		Thread.sleep(1000);
		if (damc <= expValue_AMC) {
			System.out.println("FAIL - AMC stock price is less than invested amount");
		}else {
			System.out.println("PASS - AMC stock price is greater than invested amount");
		}
		// Virgin Galactic Stock Price
		double expValue_Virgin = 10.2;
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='APjFqb']")).clear();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='APjFqb']")).sendKeys("Virgin Galactic stock price");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='APjFqb']")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		String virgin = driver.findElement(By.xpath("//*[@id='knowledge-finance-wholepage__entity-summary']/div[3]/g-card-section/div/g-card-section/div[2]/div[1]/span[1]/span/span[1]")).getText();
		double dvirgin = Double.parseDouble(virgin);
		System.out.println("Current Virgin Galactic price - "+ dvirgin);
		Thread.sleep(1000);
		if (dvirgin <= expValue_Virgin) {
			System.out.println("FAIL - Virgin Galactic stock price is less than invested amount");
		}else {
			System.out.println("PASS - Virgin Galactic stock price is greater than invested amount");
		}
		// FXAIX Stock Price
		double expValue_Fxaix = 10.2;
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='APjFqb']")).clear();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='APjFqb']")).sendKeys("FXAIX stock price");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='APjFqb']")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		String fxaix = driver.findElement(By.xpath("//*[@id='knowledge-finance-wholepage__entity-summary']/div[3]/g-card-section/div/g-card-section/div[2]/div[1]/span[1]/span/span[1]")).getText();
		double dfxaix = Double.parseDouble(fxaix);
		System.out.println("Current Virgin Galactic price - "+ dfxaix);
		Thread.sleep(1000);
		if (dfxaix <= expValue_Fxaix) {
			System.out.println("FAIL - FXAIX stock price is less than invested amount");
		}else {
			System.out.println("PASS - FXAIX stock price is greater than invested amount");
		}
		// ETC Stock Price
		double expValue_etc = 40.2;
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='APjFqb']")).clear();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='APjFqb']")).sendKeys("Ethereum Classic price");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='APjFqb']")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		String etc = driver.findElement(By.xpath("//*[@class='card-section PZPZlf']/div[2]/span")).getText();
		double detc = Double.parseDouble(etc);
		System.out.println("Current Ethereum Classic price - "+ detc);
		Thread.sleep(1000);
		if (detc <= expValue_etc) {
			System.out.println("FAIL - Ethereum Classic stock price is less than invested amount");
		}else {
			System.out.println("PASS - Ethereum Classic stock price is greater than invested amount");
		}
		// Dogecoin Stock Price
		double expValue_dc = 0.22;
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='APjFqb']")).clear();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='APjFqb']")).sendKeys("Dogecoin price");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='APjFqb']")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		String dc = driver.findElement(By.xpath("//*[@class='card-section PZPZlf']/div[2]/span")).getText();
		double ddc = Double.parseDouble(dc);
		System.out.println("Current Dogecoin price - "+ ddc);
		Thread.sleep(1000);
		if (ddc <= expValue_dc) {
			System.out.println("FAIL - Dogecoin stock price is less than invested amount");
		}else {
			System.out.println("PASS - Dogecoin stock price is greater than invested amount");
		}
		
		
		//testing
		driver.quit();
	}

}
