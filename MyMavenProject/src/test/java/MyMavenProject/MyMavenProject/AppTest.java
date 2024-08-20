package MyMavenProject.MyMavenProject;

import java.io.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AppTest extends Main {
	static WebElement element;

	public static void timesheet(String nm) throws InterruptedException, IOException {
//		JavascriptExecutor js = (JavascriptExecutor)driver;

		driver.get("https://www.fieldglass.net/");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		try {
			String errTxt = driver.findElement(By.xpath("//*[@id='primary_content']/div")).getText();
			System.out.println("Current message displayed on screen: " + errTxt);
			if (errTxt.toLowerCase().contains("down") || errTxt.toLowerCase().contains("maintenance")) {
				System.out.println("FieldGlass site is down for maintenance, PLEASE TRY AFTER SOMETIME");
				driver.quit();
				System.exit(0);				
			} else {
				System.out.println("User ID and Password fields not displayed on screen");						
			}
		} catch (Exception e) {
			System.out.println("Exception displayed since the Error message not displayed on screen, Its fine to proceed");
		}
		
//		explicit_wait_text("xpath", "//*[@id='primary_content']/div");
		explicit_wait("id", "usernameId_new");
		explicit_wait("id", "passwordId_new");
		if (nm.toLowerCase().equals("siraz")) {
			element = driver.findElement(By.id("usernameId_new"));
//			js.executeScript("arguments[0].value='testing';",element);
			element.sendKeys("sshaik34");
			element = driver.findElement(By.id("passwordId_new"));
			element.sendKeys("55tyGHBN%^tyghbn");
		} else if (nm.toLowerCase().equals("tamil")) {
			element = driver.findElement(By.id("usernameId_new"));
			element.sendKeys("tamil.azhagi");
			element = driver.findElement(By.id("passwordId_new"));
			element.sendKeys("Dontforget02");
		}

		Thread.sleep(1000);

//		DateFormat df = new SimpleDateFormat("hh:mm:ss");
//		Date dt = new Date();		
//		String date1 = df.format(dt);	
//		date1 = date1.replace("/", "");
//		System.out.println("date1:  "+date1);
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		String path = "/Screenshots/" + dateName + ".png";
		File fdest = new File(path);

		TakesScreenshot ts = ((TakesScreenshot) driver);
		File fl = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir") + fdest);
		FileHandler.copy(fl, dest);

		explicit_wait("xpath", "/html/body/div[2]/form/div[2]/div[3]/div[5]/button");
		element = driver.findElement(By.xpath("/html/body/div[2]/form/div[2]/div[3]/div[5]/button"));
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
		Thread.sleep(20000);

		element.click();
		try {
			String txt = driver.findElement(By.xpath("//*[text()='Complete Time Sheet']")).getText();
			if (txt.trim().equalsIgnoreCase("Complete Time Sheet")) {
				System.out.println("'Complete Time Sheet' link is visible on the page");
				explicit_wait("xpath", "//*[@id='todoCardContents']/li[2]/div[1]/div[2]/a");
				element = driver.findElement(By.xpath("//*[@id='todoCardContents']/li[2]/div[1]/div[2]/a"));
				element.click();
				String val = dateofweek();
				int newVal = 0;
				String dayMon = "//*[@class='timeSheetEntry     " + val + "']";
				System.out.println("dateprop: " + dayMon);
				newVal = Integer.parseInt(val) + 1;
				System.out.println("newVal: " + newVal);
				String dayTue = "//*[@class='timeSheetEntry     " + newVal + "']";
				System.out.println("dateprop: " + dayTue);
				newVal = Integer.parseInt(val) + 2;
				System.out.println("newVal: " + newVal);
				String dayWed = "//*[@class='timeSheetEntry     " + newVal + "']";
				System.out.println("dateprop: " + dayWed);
				newVal = Integer.parseInt(val) + 3;
				System.out.println("newVal: " + newVal);
				String dayThu = "//*[@class='timeSheetEntry     " + newVal + "']";
				System.out.println("dateprop: " + dayThu);
				newVal = Integer.parseInt(val) + 4;
				System.out.println("newVal: " + newVal);
				String dayFri = "//*[@class='timeSheetEntry     " + newVal + "']";
				System.out.println("dateprop: " + dayFri);

				explicit_wait("xpath", dayMon);
				element = driver.findElement(By.xpath(dayMon));
				element.sendKeys("8");
				element = driver.findElement(By.xpath(dayTue));
				element.sendKeys("8");
				element = driver.findElement(By.xpath(dayWed));
				element.sendKeys("8");
				element = driver.findElement(By.xpath(dayThu));
				element.sendKeys("8");
				element = driver.findElement(By.xpath(dayFri));
				element.sendKeys("8");
			} else {
				System.out.println("'Complete Time Sheet' link is not visible on the page");
				explicit_wait("xpath", "//*[@id='accountOpener']/div[1]/div/div/span");
				element = driver.findElement(By.xpath("//*[@id='accountOpener']/div[1]/div/div/span"));
				element.click();
				explicit_wait("xpath", "//*[@id='signOut']/div");
				element = driver.findElement(By.xpath("//*[@id='signOut']/div"));
				element.click();
				Thread.sleep(5000);
				explicit_wait("xpath", "//*[@id='primary_content']/h2");
				element = driver.findElement(By.xpath("//*[@id='primary_content']/h2"));
				String signoutText = element.getText();
				if (signoutText.contains("signed out")) {
					System.out.println("User is successfully signed out");
					Thread.sleep(1000);
					driver.quit();
				} else {
					System.out.println("User sign out is not successful");
				}
			}
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("NoSuchElementException is displayed");
			System.out.println("It means, Timesheet is not available on the page to fill");
		}
	}

	public static void explicit_wait(String loc, String locValue) {
		WebDriverWait wdw = new WebDriverWait(driver, Duration.ofSeconds(20));
		if (loc.equalsIgnoreCase("id")) {
			element = driver.findElement(By.id(locValue));
		} else {
			element = driver.findElement(By.xpath(locValue));
		}
		WebElement eStatus = wdw.until(ExpectedConditions.elementToBeClickable(element));
		if (eStatus != null) {
			System.out.println(eStatus + " :Element found");
		} else {
			System.out.println(eStatus + " :Element not found");
		}

	}

	public static void explicit_wait_text(String loc, String locValue) {
		WebDriverWait wdw = new WebDriverWait(driver, Duration.ofSeconds(5));
		if (loc.equalsIgnoreCase("id")) {
			element = driver.findElement(By.id(locValue));
		} else {
			element = driver.findElement(By.xpath(locValue));
		}
		WebElement eStatus = wdw.until(ExpectedConditions.visibilityOf(element));
		if (eStatus != null) {
			System.out.println(eStatus + " :Element found");
		} else {
			System.out.println(eStatus + " :Element not found");
		}

	}

	protected void test() {
		FluentWait<WebDriver> fw = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofSeconds(10)).ignoring(NoSuchElementException.class);
		fw.until(ExpectedConditions.elementToBeClickable(element));

//		WebDriverWait wdd = new WebDriverWait(driver, Duration.ofSeconds(20));
//		Alert alt = wdd.until(ExpectedConditions.alertIsPresent());

	}

	private void txt() {

	}

	public static String dateofweek() {
		System.out.println("current date: " + LocalDate.now());
		LocalDate d = LocalDate.now();
		String day = d.getDayOfWeek().toString();
		System.out.println("current day: " + day);
		String val = "null";
		System.out.println("**********************");
		switch (day.toUpperCase()) {
		case "MONDAY":
			val = d.toString();
			val = val.replace("-", "");
			System.out.println("val:" + val);
			break;
		case "TUESDAY":
			val = d.minusDays(1).toString();
			val = val.replace("-", "");
			System.out.println("val:" + val);
			break;
		case "WEDNESDAY":
			val = d.minusDays(2).toString();
			val = val.replace("-", "");
			System.out.println("val:" + val);
			break;
		case "THURSDAY":
			val = d.minusDays(3).toString();
			val = val.replace("-", "");
			System.out.println("val:" + val);
			break;
		case "FRIDAY":
			val = d.minusDays(4).toString();
			val = val.replace("-", "");
			System.out.println("val:" + val);
			break;
		case "SATURDAY":
			val = d.minusDays(5).toString();
			val = val.replace("-", "");
			System.out.println("val:" + val);
			break;
		case "SUNDAY":
			val = d.minusDays(6).toString();
			val = val.replace("-", "");
			System.out.println("val:" + val);
			break;
		}

		return val;
	}

}