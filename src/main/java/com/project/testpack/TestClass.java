package com.project.testpack;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestClass {

	public static void main(String[] args) {

		// set chrome-driver path
		//System.setProperty("webdriver.chrome.driver", "F://chromedriver_win32//chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--headless");
		chromeOptions.addArguments("--no-sandbox");
		chromeOptions.addArguments("--disable-dev-shm-usage");
		chromeOptions.addArguments("--remote-allow-origins=*");
		
		WebDriver driver = new ChromeDriver(chromeOptions);
		System.out.println("Welcome to selenium test script");
		driver.get("http://15.206.185.23:30941/contact.html");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		WebElement inputField = driver.findElement(By.xpath("//input[@type='text' and @placeholder='Your Name']"));
		inputField.sendKeys("demo");
		
		WebElement inputField1 = driver.findElement(By.xpath("//input[@type='text' and @placeholder='Phone Number']"));
		inputField1.sendKeys("9876543210");
		
		WebElement inputField2 = driver.findElement(By.xpath("//input[@type='email']"));
		inputField2.sendKeys("demo@gmail.com");
		
		driver.findElement(By.className("message-box")).sendKeys("How Are You?");
		
		driver.findElement(By.className("btn_box")).click();	
	}
}
