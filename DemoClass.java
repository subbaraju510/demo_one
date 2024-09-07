package com.samplepach;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

public class DemoClass {
	WebDriver driver = new ChromeDriver();

	@Test (priority = 1, description="close the browser description")
	public void CloseBrowser() {
		driver.close();
		System.out.println("Closing Google Chrome browser");
	}

	@Test (priority = 0, description="valid login and password description")
	
	public void OpenBrowser() throws InterruptedException {
		System.out.println("Launching Google Chrome browser"); 
		driver.get("https://nlp.nexterp.in/nlp/nlp/login");
		driver.manage().window().maximize();
		String getActualTitle = driver.getTitle();
		Boolean verifyTitle = driver.getTitle().equalsIgnoreCase("Most Reliable App | BrowserStack");
		System.out.println("Boolean value test"+ verifyTitle); //false
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(getActualTitle, "Most Reliable App & Cross Browser Testing Platform | BrowserStack");
		softAssert.assertNotEquals(getActualTitle, "Most Reliable App & Cross Browser Testing Platform | BrowserStack");
		driver.findElement(By.xpath("//*[@id=\"input_0\"]")).sendKeys("9951053510");
		driver.findElement(By.xpath("//*[@id=\"input_2\"]")).sendKeys("Next@123");
		driver.findElement(By.xpath("//*[@id=\"input_4\"]")).sendKeys("976");
		driver.findElement(By.xpath("//*[@id=\"loginForm\"]/button/span/span")).click();
		Thread.sleep(20000);
		WebElement str=driver.findElement(By.xpath("//h2[contains(text(),'Hey, Sattibabu Jai!')]"));
		
		String welcome=str.getText();
		Thread.sleep(10000);
		System.out.println(welcome);
	}
}