package POM_Account_Module;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Add_Account 
{
	
			//Data members should be declare globally with access level private by using @findBy annotation
			@FindBy(xpath="//input[@id='phone_office']")private WebElement Office_Phone;
			@FindBy(xpath="//input[@id='ticker_symbol']")private WebElement Ticker_Symbol;
			@FindBy(xpath="//input[@id='billing_address_street']")private WebElement BillingAddress;
			@FindBy(xpath="//input[@id='billing_address_state']")private WebElement BillingState;
			@FindBy(xpath="//input[@id='billing_address_postalcode']")private WebElement BillingPostalCode;
			@FindBy(xpath="//input[@id='billing_address_country']")private WebElement BillingCountry;
			@FindBy(xpath="//input[@id='billing_address_city']")private WebElement BillingCity;
			@FindBy(xpath="(//input[@id='shipping_address_street'])[2]")private WebElement CopyFromleftCheckBox;
			@FindBy(xpath="//input[@id='account_type']")private WebElement AccountType;
			@FindBy(xpath="//input[@id='industry']")private WebElement Industry;
			@FindBy(xpath="//input[@id='annual_revenue']")private WebElement AnnualRevenue;
			@FindBy(xpath="//input[@id='employees']")private WebElement Employees;
			@FindBy(xpath="//*[local-name()='svg' and @id='search-btn-parent_name']")private WebElement MemberOfSearchIcon;
			@FindBy(xpath="//div[@role='dialog']//input[@id='name']")private WebElement windowName;
			@FindBy(xpath="//button[@form='relate-search-form']")private WebElement SearchBtnOnWindow;
			
			//xpath of detail view Opportunity sub panel and Lead sub panel
			@FindBy(xpath="//div[@id='opportunities']")private WebElement OpportunitySubPanelInDetailView;
			//Xpath of opportunityName
			@FindBy(xpath="//p[contains(@class,'subpanel-name')]//a")private WebElement OpportunitySubPanelName;
			@FindBy(xpath="//div[@id='leads']")private WebElement LeadsSubPanelInDetailView;
			@FindBy(xpath="//button[@aria-label='settings']")private WebElement SettingInListView;
			//Initialize the constructor with access level public using PageFactory class
			public Add_Account(WebDriver driver)
			{
				PageFactory.initElements(driver, this);
			}
			
			//Utilize within the methods with access level public
			public void enterOfficePhone(String officePhone)
			{
				Office_Phone.sendKeys(officePhone);
			
			}
			
			public void enterTickerSymbol(String tickerSymbol)
			{
				Ticker_Symbol.sendKeys(tickerSymbol);
				
			}
			
			public void enterBillingAddress(String billingAddress)
			{
				BillingAddress.sendKeys(billingAddress);
				
			}
			
			public void enterBillingState(String billingState)
			{
				BillingState.sendKeys(billingState);
				
			}
			
			public void enterBillingPostalCode(String billingPostalCode)
			{
				BillingPostalCode.sendKeys(billingPostalCode);
				
			}
			
			public void enterBillingCountry(String billingCountry)
			{
				BillingCountry.sendKeys(billingCountry);
				
			}
			
			public void enterBillingCity(String billingCity)
			{
				BillingCity.sendKeys(billingCity);
				
			}
			
			public void clickCopyFromleftCheckBox()
			{
				CopyFromleftCheckBox.click();
				
			}
			
			public void enterAccountType(WebDriver driver, String accountType) throws InterruptedException
			{
				AccountType.click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+accountType+"')]")).click();
			}
			
			public void enterIndustry(WebDriver driver,String industry) throws InterruptedException
			{
				Industry.click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+industry+"')]")).click();
			}
			
			public void enterAnnualRevenue(String annualRevenue)
			{
				AnnualRevenue.sendKeys(annualRevenue);
				
			}
			
			public void enterEmployees(String employees)
			{
				Employees.sendKeys(employees);
				
			}
			
			public void enterMemberOf(WebDriver driver,String member) throws InterruptedException
			{
				MemberOfSearchIcon.click();
				windowName.sendKeys(member);
				SearchBtnOnWindow.click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("//table[@role='grid']//tbody//td//a[contains(text(),'"+member+"')]")).click();
			}
			public void clickOnName(WebDriver driver,String  Name)
			{
				driver.findElement(By.xpath("//a[contains(text(),'"+Name+"')]")).click();
			}
			
			public String VerifyOpportunitySubPanelName(WebDriver driver, String subPanelName)//sub Panel names: opportunities, leads, activities
			{
				//OpportunitySubPanelInDetailView.click();
				driver.findElement(By.xpath("//div[@id='"+subPanelName+"']")).click();
				String oppName = OpportunitySubPanelName.getText();
				return oppName;
			}
			
			public void settingFilter(WebDriver driver, String setting) throws InterruptedException
			{
				SettingInListView.click();
				Thread.sleep(2000);
				Actions act=new Actions(driver);
				WebElement filter = driver.findElement(By.xpath("//ul[@role='menu']//li//span[text()='"+setting+"']"));
				act.moveToElement(filter).perform();
		    	act.click().perform();
			
				
			}
			 
			public void scrollpage(WebDriver driver)
			{
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",MemberOfSearchIcon);
			}
			
			public void scrollpageBillingCountry(WebDriver driver)
			{
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",BillingCountry);
			}
			public void scrollpageCopyFromleftCheckBox(WebDriver driver)
			{
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",CopyFromleftCheckBox);
			}
			}
			

