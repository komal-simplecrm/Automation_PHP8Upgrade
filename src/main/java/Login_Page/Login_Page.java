package Login_Page;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Library_Files.UtilityClass;

public class Login_Page 
{
	//Data members should be declare globally with access level private by using @FindBy annotation
			@FindBy(xpath="//div[@role='button']") private WebElement ClickOnBaseline;
			//@FindBy(xpath="//ul[@role='listbox']//li[text()='baselinev267.simplecrmdev.com']") private WebElement Baseline;
			@FindBy(xpath="//ul[@role='listbox']//li[text()='thepipalbank.simplecrmdemo.com']") private WebElement Baseline;
			@FindBy(xpath="//input[@id='username']") private WebElement UserId;
			@FindBy(xpath="//input[@id='password']") private WebElement PSW;
			@FindBy(xpath="//button[@type='submit']//span[text()='Login']") private WebElement loginbtn;
			
			//Initialize the constructor with access level public using PageFactory class
			public Login_Page(WebDriver driver)
			{
				PageFactory.initElements(driver, this);
			}
			
			//Utilize within the methods with access level public 
			public void Login(String UN, String psw) throws IOException, InterruptedException
			{
				//ClickOnBaseline.click();
				//Thread.sleep(4000);
				//Baseline.click();
				UserId.sendKeys(UtilityClass.getDataFromPF(UN));
				PSW.sendKeys(UtilityClass.getDataFromPF(psw));
				loginbtn.click();
			}
			
			public void LoginAdmin(String UN, String PSw) throws IOException, InterruptedException
			{
				//ClickOnBaseline.click();
				//Thread.sleep(4000);
				//Baseline.click();
				//UserId.sendKeys("Komal");
				//PSW.sendKeys("Komal@kolhe8");
				UserId.sendKeys(UN);
			
				PSW.sendKeys(PSw);
				//Thread.sleep(5000);
				loginbtn.click();
			}
			
}
