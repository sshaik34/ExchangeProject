package MyMavenProject.MyMavenProject;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter NAME to fill timesheet - ");
		String name = sc.next();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		Thread.sleep(2000);
		AppTest.timesheet(name);
		sc.close();

		
	}

}


