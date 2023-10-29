package POM_Account_Module;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Duplicate_Account 
{
	//Data members should be declare globally with access level private by using @findBy annotation
	@FindBy(xpath="//input[@id='phone_office']")private WebElement Office_Phone;
	@FindBy(xpath="//input[@id='ticker_symbol']")private WebElement TickerSymbol;
	@FindBy(xpath="//input[@id='billing_address_street']")private WebElement BillingAddress;
	@FindBy(xpath="//input[@id='billing_address_state']")private WebElement BillingState;
	@FindBy(xpath="//input[@id='billing_address_postalcode']")private WebElement BillingPostalCode;
	@FindBy(xpath="//input[@id='billing_address_country']")private WebElement BillingCountry;
	@FindBy(xpath="//input[@id='billing_address_city']")private WebElement BillingCity;
	//Shipping Address
	@FindBy(xpath="//input[@id='shipping_address_street']")private WebElement ShippingAddress;
	@FindBy(xpath="//input[@id='shipping_address_state']")private WebElement ShippingState;
	@FindBy(xpath="//input[@id='shipping_address_postalcode']")private WebElement ShippingPostalCode;
	@FindBy(xpath="//input[@id='shipping_address_country']")private WebElement ShippingCountry;
	@FindBy(xpath="//input[@id='shipping_address_city']")private WebElement ShippingCity;
	@FindBy(xpath="//input[@id='account_type']")private WebElement AccountType;
	@FindBy(xpath="//input[@id='industry']")private WebElement Industry;
	@FindBy(xpath="//input[@id='annual_revenue']")private WebElement AnnualRevenue;
	@FindBy(xpath="//input[@id='employees']")private WebElement Employees;
	@FindBy(xpath="//input[@id='parent_name']")private WebElement MemberOf;
	@FindBy(xpath="//span[contains(@class,'MuiIconButton')]//input[@id='shipping_address_street']")private WebElement CopyFromleftCheckBox;
		//Initialize the constructor with access level public using PageFactory class
		public Duplicate_Account(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
			
		}

		//Utilize within the methods with access level public

		public String getOffice_Phone()
		{
			String officephone=Office_Phone.getAttribute("value");
			return officephone;
		}
		
		public String getTickerSymbol()
		{
			String tickersymbol = TickerSymbol.getAttribute("value");
			return tickersymbol;
		}
		
		public String getBillingAddress()
		{
			String billingaddress=BillingAddress.getAttribute("value");
			return billingaddress;
		}
		
		public String getBillingState()
		{
			String billingState=BillingState.getAttribute("value");
			return billingState;
		}
		public String getBillingPostalCode()
		{
			String billingPostalCode=BillingPostalCode.getAttribute("value");
			return billingPostalCode;
		}
		
		public String getBillingCountry()
		{
			String billingCountry=BillingCountry.getAttribute("value");
			return billingCountry;
		}
		
		public String getBillingCity()
		{
			String billingCity=BillingCity.getAttribute("value");
			return billingCity;
		}
		
		public String getAccountType()
		{
			String accounttype=AccountType.getAttribute("value");
			return accounttype;
		}
		
		public String getIndustry()
		{
			String industry=Industry.getAttribute("value");
			return industry;
		}
		public String getAnnualRevenue()
		{
			String annualRevenue=AnnualRevenue.getAttribute("value");
			return annualRevenue;
		}
		
		
		public String getEmployees()
		{
			String employees=Employees.getAttribute("value");
			return employees;
		}
		
		public String getMemberOf()
		{
			String memberOf=MemberOf.getAttribute("value");
			return memberOf;
		}
		
		public String getShippingAddress()
		{
			String Shippingaddress=ShippingAddress.getAttribute("value");
			return Shippingaddress;
		}
		
		public String getShippingState()
		{
			String shippingState = ShippingState.getAttribute("value");
			return shippingState;
		}
		public String getShippingPostalCode()
		{
			String shippingPostalCode = ShippingPostalCode.getAttribute("value");
			return shippingPostalCode;
		}
		
		public String getShippingCountry()
		{
			String shippingCountry = ShippingCountry.getAttribute("value");
			return shippingCountry;
		}
		
		public String getShippingCity()
		{
			String shippingCity=ShippingCity.getAttribute("value");
			return shippingCity;
		}
		
		public void scrollpage(WebDriver driver)
		{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",CopyFromleftCheckBox);
		}
		
		public boolean CopyFromleftCheckBox()
		{
			boolean copyAddress=CopyFromleftCheckBox.isSelected();
			return copyAddress;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
