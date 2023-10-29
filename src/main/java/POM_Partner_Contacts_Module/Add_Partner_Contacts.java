package POM_Partner_Contacts_Module;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Add_Partner_Contacts 
{
	//Data members should be declare globally with access level private by using @FindBy annotation
	@FindBy(xpath="//input[@id='phone_home']") private WebElement HomePhone;
	@FindBy(xpath="//input[@id='phone_other']") private WebElement OtherPhone;
	@FindBy(xpath="//input[@id='portal_username_c']") private WebElement PortalUsername;
	@FindBy(xpath="//input[@id='portal_password_c']") private WebElement PortalPassword;
	@FindBy(xpath="//input[@id='portal_active_c']") private WebElement PortalActiveCheckBox;
	@FindBy(xpath="//input[@id='do_not_call']") private WebElement DoNotCallCheckBox;
	@FindBy(xpath="//*[local-name()='svg' and @id='seach-btn-scrm_partners_scrm_partner_contacts_1_name']")private WebElement PartnerSearchIcon;
	@FindBy(xpath="//input[@id='scrm_partners_scrm_partner_contacts_1_name']")private WebElement Partner;
	@FindBy(xpath="//button[@form='relate-search-form']")private WebElement SearchOnWindow;
	@FindBy(xpath="//div[@role='dialog']//input[@id='name']")private WebElement windowName;
	@FindBy(xpath="(//button[@type='submit']//span[text()='Save'])[2]")private WebElement SaveBtn;
	
	//Initialize the constructor with access level public using PageFactory class
	public Add_Partner_Contacts(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
			
	//Utilize within the methods with access level public 
	public void enterHomePhone(String number) 
	{
		HomePhone.sendKeys(number);
	}
		
	public void enterOtherPhone(String number) 
	{
		OtherPhone.sendKeys(number);	
	}	
		
	public void enterPortalUsername(String username) 
	{
		PortalUsername.sendKeys(username);	
	}	
	
	public void enterPortalPassword(String password) 
	{
		PortalPassword.sendKeys(password);
	}	
		
	public void clickPortalActiveCheckBox() 
	{
		PortalActiveCheckBox.click();
	}	
		
	public void clickDoNotCallCheckBox() 
	{
		DoNotCallCheckBox.click();
	}	
		
	public void enterPartners(WebDriver driver, String partner) throws InterruptedException 
	{
		PartnerSearchIcon.click();
		Thread.sleep(2000);
		windowName.sendKeys(partner);
		SearchOnWindow.click();
		WebElement partners = driver.findElement(By.xpath("//table[@role='grid']//tbody//td//a[contains(text(),'"+partner+"')]"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",partners);
		Thread.sleep(2000);
		partners.click();
	}	
		
	public void scrollpage(WebDriver driver)
	{
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",SaveBtn);
	}
		
	public void scrollpageuptoPortalPassword(WebDriver driver)
	{
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",PortalPassword);
	}	
		
		
		
	//Functions for Duplicate functionality
	public String getHomePhone() 
	{
		String homephone = HomePhone.getAttribute("value");
		return homephone;
	}
		
	public String getOtherPhone() 
	{
		String otherphone = OtherPhone.getAttribute("value");
		return otherphone;
	}	
		
	public String getPortalUsername() 
	{
		String portalusername = PortalUsername.getAttribute("value");
		return portalusername;
	}	
		
	public String getPortalPassword() 
	{
		String portalpassword = PortalPassword.getAttribute("value");
		return portalpassword;
	}		
		
	public String getPartner() 
	{
		String partner = Partner.getAttribute("value");
		return partner;
	}	
}
