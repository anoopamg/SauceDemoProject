package demo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SauceDemoLogin {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		try {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

		// Launch saucedemo.com
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);		
		
		for (int i = 1; i < 5; i++) {
			WebElement uName = driver.findElement(By.xpath("//input[@name='user-name']"));
			WebElement pswd = driver.findElement(By.xpath("//input[@name='password']"));
			WebElement login = driver.findElement(By.xpath("//input[@data-test='login-button']"));
			WebElement error ;
			// driver.findElement(By.xpath("//div[@class='error-message-container
			// error']/h3[@data-test='error']"));

			// UserName and Password Empty

			/*
			 * login.click(); String errorMsg; if(error.isEnabled()) { errorMsg =
			 * error.getText(); System.out.println(errorMsg); }
			 */

			// Get the User names
			WebElement loginUser = driver.findElement(By.xpath("//div[@class='login_credentials']"));
			String text = loginUser.getAttribute("innerText");
			String[] uNames = text.split("\n");

			// Get the password
			WebElement loginPswd = driver.findElement(By.xpath("//div[@class='login_password']"));
			String loginPass = loginPswd.getText();
			String[] loginPassword = loginPass.split(":");
			uName.clear();
			pswd.click();
			Thread.sleep(5000);
			pswd.clear();
			
			WebElement menu ;
			WebElement logout;
			switch(uNames[i]) {
			case "standard_user":
				uName.sendKeys(uNames[i]);
				pswd.sendKeys(loginPassword[1]);
				login.click();
				System.out.println(uNames[i]+ " logged in successfully");
				Thread.sleep(5000);
				menu = driver.findElement(By.xpath("//div[@class='bm-burger-button']"));
				menu.click();
				Thread.sleep(5000);
				logout  = driver.findElement(By.xpath("//a[@id='logout_sidebar_link']"));
				logout.click();
				System.out.println(uNames[i]+ " logged out successfully");
				break;
			case "locked_out_user":				
				Thread.sleep(5000);
				uName.sendKeys(uNames[i]);
				pswd.sendKeys(loginPassword[1]);
				login.click();
				System.out.println(uNames[i]+ " NOT logged in successfully");
				Thread.sleep(10000);
				error = driver.findElement(By.xpath("//div[@class='error-message-container error']/h3"));
				if(error.isDisplayed()) {
					System.out.println(error.getText());
				}
				break;
			case "problem_user":
				uName.sendKeys(uNames[i]);
				login.click();
				Thread.sleep(5000);
				menu = driver.findElement(By.xpath("//div[@class='bm-burger-button']"));
				menu.click();
				System.out.println(uNames[i]+ " logged in successfully");
				Thread.sleep(5000);
				logout  = driver.findElement(By.xpath("//a[@id='logout_sidebar_link']"));
				logout.click();
				System.out.println(uNames[i]+ " logged out successfully");
				break;
			case "performance_glitch_user":
				uName.sendKeys(uNames[i]);
				pswd.sendKeys(loginPassword[1]);
				login.click();
				System.out.println(uNames[i]+ " logged in successfully");
				Thread.sleep(5000);
				menu = driver.findElement(By.xpath("//div[@class='bm-burger-button']"));
				menu.click();
				Thread.sleep(5000);
				logout  = driver.findElement(By.xpath("//a[@id='logout_sidebar_link']"));
				logout.click();
				System.out.println(uNames[i]+ " logged out successfully");
				break;
			}
			
		}
		driver.close();
		}
		catch(Exception err)
		{
			System.out.println(err);
		}
	}

}
