package Login_Page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Library_Files.UtilityClass;

public class Dashboard 
{
		//Data members should be declare globally with access level private by using @findBy annotation
		@FindBy(xpath="//button[@aria-label='open drawer']")private WebElement Menu;
		@FindBy(xpath="//div[contains(@class,'MuiDrawer-paperAnchorLeft')]//*[local-name()='svg']")private WebElement closeMenu;
		@FindBy(xpath="//input[@id='search_field']")private WebElement Search;
		//@FindBy(xpath="//input[@id='search_field']")private WebElement Search;//This is used for login in https://internaluat.simplecrmondemand.com/ instance
		@FindBy(xpath="//ul[@class='MuiList-root MuiList-padding']//a[@title='Leads']")private WebElement Lead;
		@FindBy(xpath="//button[@title='Search']")private WebElement AdvanceSearch;
		@FindBy(xpath="//input[@id='queryString']")private WebElement EnterTextInAdvanceSearch;
		@FindBy(xpath="//div[contains(@class,'MuiCardHeader')]//span[contains(@class,'MuiTypography-displayBlock')]//a//a")private WebElement CaseName;
		@FindBy(xpath="//button[@aria-controls='right-profile-menu']")private WebElement ProfileIcon;
		@FindBy(xpath="//span[@class='MuiIconButton-label']/..")private WebElement Menu1;
		//Initialize the constructor with access level public using PageFactory class
		public Dashboard(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}
		
		//Utilize within the methods with access level public
		public void clickOnMenuOption()
		{
			Menu.click();
			
			//Menu.sendKeys(Keys.ENTER);
		}
		public void closeMenuOption()
		{
			closeMenu.click();
			
			//Menu.sendKeys(Keys.ENTER);
		}
		public void clickOnSearch(String entermenu)
		{
			String s2 = Keys.chord(Keys.CONTROL, "a");
			Search.sendKeys(s2);
			Search.sendKeys(Keys.DELETE);
			Search.sendKeys(Keys.ENTER);
			Search.sendKeys(entermenu);
		}
		public void clickOnMenuOption(WebDriver driver, String menuOption)
		{
			
			//Lead.click();
			driver.findElement(By.xpath("//ul//li[contains(@class,'MuiListItem-container')]//a[@title='"+menuOption+"']")).click();

		}
		public void advanceSearch()
		{
			AdvanceSearch.click();
		}
		
		public void enterTextInAdvanceSearch(String CaseNumber) throws InterruptedException
		{
			EnterTextInAdvanceSearch.click();
			Thread.sleep(5000);
			EnterTextInAdvanceSearch.sendKeys(CaseNumber);
			
		}
		public void clickOnCaseName()
		{
			CaseName.click();
		}
		
		public void clickProfileIcon(WebDriver driver, String menuoption)
		{
			ProfileIcon.click();
			driver.findElement(By.xpath("//ul[@role='menu']//li[contains(text(),'"+menuoption+"')]")).click();
		}
		
		
		//For opportunity to Feedback flow
		public void clickOnMenuDashboard(WebDriver driver, String entermenu)
		{
			Actions act= new Actions(driver);
			//act.moveToElement(Menu1).perform();
	    	//act.click().perform();
			 /*((JavascriptExecutor) driver).executeScript("arguments[0].click();", Menu1);
	    	act.moveToElement(Search).perform();
	    	act.click().perform();*/
			WebElement Search = driver.findElement(By.xpath("//input[@placeholder='Search...']"));
	    	Search.sendKeys(Keys.ENTER);
	    	//Search.click();
	    	UtilityClass.wait_until_element_invisible(driver, Search);
	    	((JavascriptExecutor)driver).executeScript("arguments[0].setAttribute('value','Quotes');", Search);
			//Search.sendKeys(entermenu);
		}
		public void clickOnMenuOptionDashboard(WebDriver driver)
		{
			WebElement menu = driver.findElement(By.xpath("//span[@class='MuiIconButton-label']/.."));
			UtilityClass.wait_until_element_found(driver, menu);
			menu.sendKeys(Keys.RETURN);
			//Menu.sendKeys(Keys.ENTER);
		}
}
