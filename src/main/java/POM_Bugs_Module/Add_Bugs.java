package POM_Bugs_Module;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Add_Bugs {
	

		//Data members should be declare globally with access level private by using @FindBy annotation
		@FindBy(xpath="//input[@id='source']") private WebElement Source;
		@FindBy(xpath="//input[@id='status']") private WebElement Status;
		@FindBy(xpath="//input[@id='product_category']") private WebElement Category;
		@FindBy(xpath="//input[@id='resolution']") private WebElement Resolution;
		@FindBy(xpath="//input[@id='found_in_release']") private WebElement Found_in_release;
		@FindBy(xpath="//input[@id='fixed_in_release']") private WebElement Fixed_in_release;
		@FindBy(xpath="//textarea[@id='work_log']") private WebElement Work_log;
		@FindBy(xpath="//input[@id='bug_number']") private WebElement Number;
		
		//Initialize the constructor with access level public using PageFactory class
		public Add_Bugs(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}
		
		//Utilize within the methods with access level public
		
		public void enterNumber(String number)
		{
			Number.sendKeys(number);
		}
		public void selectSource(WebDriver driver, String source)
		{	
				Source.click();
				driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+source+"')]")).click();
			
		}
		
		public void selectStatus(WebDriver driver, String status)
		{	
				Status.click();
				driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+status+"')]")).click();
			
		}
		
		public void selectCategory(WebDriver driver, String category)
		{	
				Category.click();
				driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+category+"')]")).click();
			
		}

		public void selectResolution(WebDriver driver, String resolution)
		{	
				Resolution.click();
				driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+resolution+"')]")).click();
			
		}
		
		public void selectFound_in_release(WebDriver driver, String found_in_release)
		{	
				Found_in_release.click();
				driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+found_in_release+"')]")).click();
			
		}
		
		public void selectFixed_in_release(WebDriver driver, String fixed_in_release)
		{	
				Fixed_in_release.click();
				driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+fixed_in_release+"')]")).click();
			
		}
		
		public void enterWork_log(String work_log)
		{	
				Work_log.sendKeys(work_log);

		}
		//Duplicate functionality functions
		public String getStatus()
		{
			String status = Status.getAttribute("value");
			return status;
		}
		public String getsStatus()
		{
			String status = Status.getAttribute("value");
			return status;
		}
}
