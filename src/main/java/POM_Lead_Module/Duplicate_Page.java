package POM_Lead_Module;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Library_Files.CommonFunctions;


public class Duplicate_Page {
	
		//Data members should be declare globally with access level private by using @findBy annotation
				
				@FindBy(xpath="//input[@id='salutation']")private WebElement SalutationOption;
				@FindBy(xpath="//input[@id='first_name']")private WebElement FirstName;
				@FindBy(xpath="//input[@id='last_name']")private WebElement LastName;
				@FindBy(xpath="//input[@id='type_c']")private WebElement Type;
				@FindBy(xpath="//input[@id='phone_work']")private WebElement OfficePhone;
				@FindBy(xpath="//input[@id='title']")private WebElement Title;
				@FindBy(xpath="//input[@id='phone_mobile']")private WebElement Mobile;
				@FindBy(xpath="//input[@id='department']")private WebElement Department;
				@FindBy(xpath="//input[@id='loan_type_c']")private WebElement LoanType;
				@FindBy(xpath="//input[@id='account_name']")private WebElement AccountName;
				@FindBy(xpath="//input[@id='website']")private WebElement Website;
				@FindBy(xpath="//input[@id='status']")private WebElement Status;
				@FindBy(xpath="//input[@id='simplecrm_status_c']")private WebElement ApprovalStatus;
				@FindBy(xpath="//input[@id='primary_address_street']")private WebElement Address;
				@FindBy(xpath="//input[@id='primary_address_state']")private WebElement State;
				@FindBy(xpath="//input[@id='primary_address_postalcode']")private WebElement PostalCode;
				@FindBy(xpath="//input[@id='primary_address_country']")private WebElement Country;
				@FindBy(xpath="//input[@id='primary_address_city']")private WebElement City;
				@FindBy(xpath="//input[@id='email10']")private WebElement EmailAddress;
				@FindBy(xpath="//textarea[@id='description']")private WebElement Description;
				@FindBy(xpath="//input[@id='phone_fax']")private WebElement Fax;
				@FindBy(xpath="//input[@id='scrm_partner_contacts_leads_name']")private WebElement Partner_Contacts;
				@FindBy(xpath="//input[@id='utm_url_c']")private WebElement UTM_URL;
				@FindBy(xpath="//input[@id='utm_campaign_c']")private WebElement UTM_Campaign;
				@FindBy(xpath="//input[@id='lead_source']")private WebElement LeadSource;
				@FindBy(xpath="//div[@class='MuiInputAdornment-root-49585 jss46238']//*[name()='svg']")private WebElement Partner_Contact;
				@FindBy(xpath="(//input[@id='alt_address_street'])[2]")private WebElement CopyFromleftCheckBox;
				@FindBy(xpath="//textarea[@id='status_description']")private WebElement Status_Description;
				@FindBy(xpath="//textarea[@id='lead_source_description']")private WebElement Lead_Source_Description;
				@FindBy(xpath="//input[@id='opportunity_amount']")private WebElement Opportunity_Amount;
				@FindBy(xpath="//input[@id='refered_by']")private WebElement Refered_by;
				@FindBy(xpath="//input[@id='campaign_name']")private WebElement Campaign;
				//@FindBy(xpath="//input[@id='first_name']")private WebElement windowFirstName;
				//Alternate Address xpath
				@FindBy(xpath="//input[@id='alt_address_street']")private WebElement alterAddress;
				@FindBy(xpath="//input[@id='alt_address_state']")private WebElement alterState;
				@FindBy(xpath="//input[@id='alt_address_postalcode']")private WebElement alterPostalCode;
				@FindBy(xpath="//input[@id='alt_address_country']")private WebElement alterCountry;
				@FindBy(xpath="//input[@id='alt_address_city']")private WebElement alterCity;
				
				
				
				//Initialize the constructor with access level public using PageFactory class
				public Duplicate_Page(WebDriver driver)
				{
					PageFactory.initElements(driver, this);
				}
				
				//Utilize within the methods with access level public
				public String getSalutation()
				{
					String salu=SalutationOption.getAttribute("value");
					
					return salu;
				
				}
				public String getFirstName()
				{
					String FN=FirstName.getAttribute("value");
					return FN;
				}
				public String getLastName() 
				{	String LN=LastName.getAttribute("value");
					return LN;
					
				}
				public String getType()
				{
					String type=Type.getAttribute("value");
					return type;
					
					
				} 
				public String getOfficeNumber()
				{
					String OP=OfficePhone.getAttribute("value");
					return OP;
					
				}
				public String getTitle()
				{
					String title=Title.getAttribute("value");
					return title;
				}
				public String getMobile()
				{	
					String mobile=Mobile.getAttribute("value");
					return mobile;
					
				}
				public String getDepartment()
				{
					String dep=Department.getAttribute("value");
					return dep;
				}
				public String getLoanType()
				{
					String LT=LoanType.getAttribute("value");
					return LT;
					
				}
				public String getAccountName()
				{
					String AccN=AccountName.getAttribute("value");
					return AccN;
					
				}
				public String getWebsite()
				{
					String Web=Website.getAttribute("value");
					return Web;
				}
				public String getStatus()
				{
					String status=Status.getAttribute("value");
					return status;
					
										}
				public String getApprovalStatus()
				{
					String Appstatus=ApprovalStatus.getAttribute("value");
					
					return Appstatus;
				}
				public String getAddress()
				{
					String Add=Address.getAttribute("value");
					return Add;
				}
				public String getState()
				{
					String state=State.getAttribute("value");
					return state;
				}
				public String getPostalCode()
				{
					String Postalcode=PostalCode.getAttribute("value");
					return Postalcode;
				}
				public String getCountry()
				{
					String country=Country.getAttribute("value");
					return country;
				}
				public String getCity()
				{
					String city=City.getAttribute("value");
					return city;
				}
				/*public String getAddressCopyFromLeft(WebDriver driver)
				{
					String add[]= {"alt_address_street","alt_address_state","alt_address_postalcode","alt_address_country","alt_address_city"};
					for(int i=0;i<=add.length;i++)
					{
							String Altaddress=driver.findElement(By.xpath("//input[@id='"+add+"']")).getText();
							return Altaddress;
					}
				}*/
				public String getEmailAddress() 
				{	
					
					String email=EmailAddress.getAttribute("value");
					return email;
					
				}
				
				
				public String getLeadSource()
				{
					
					String LS=LeadSource.getAttribute("value");
					return LS;
					
				} 
				public String getDescription()
				{
					String desc=Description.getAttribute("value");
					return desc;
				}
				
				public String getFax()
				{
					String fax = Fax.getAttribute("value");
					return fax;
				}
				
				public String getPartner_Contacts()
				{
					String partner_contacts = Partner_Contacts.getAttribute("value");
					return partner_contacts;
				}
				public String getUTM_URL()
				{
					String utm = UTM_URL.getAttribute("value");
					return utm;
				}
				public String getUTM_Campaign()
				{
					String Utm = UTM_Campaign.getAttribute("value");
					return Utm;
				}
				/*public void clickPartner_Contact(WebDriver driver, String partner_contact)
				{
					Partner_Contact.click();
					//Handle Window popup
					Set<String> ids=driver.getWindowHandles();
					ArrayList<String> al= new ArrayList<String>(ids);
					String childWindowId=al.get(1);
					driver.switchTo().window(childWindowId);
					windowFirstName.sendKeys(partner_contact);
				}*/
				public boolean CopyFromleftCheckBox()
				{
					boolean copyAddress = CopyFromleftCheckBox.isSelected();
					return copyAddress;
				}
				public String getStatus_Description()
				{
					String status_desc=Status_Description.getAttribute("value");
					return status_desc;
				}
				public String getLead_Source_Description()
				{
					String LSD=Lead_Source_Description.getAttribute("value");
					return LSD;
				}
				
				public String getOpportunity_Amount()
				{
					String OA=Opportunity_Amount.getAttribute("value");
					return OA;
				}
				public String getRefered_by()
				{
					String RB=Refered_by.getAttribute("value");
					return RB;
				}
				/*public String getCampaign()
				{
					String C=Campaign.getAttribute("value");
					return C;
				}*/
				
				public String getalterAddress()
				{
					String add=alterAddress.getAttribute("value");
					return add;
				}
				public String getalterState()
				{
					
					String state =alterState.getAttribute("value");
					return state;
				}
				public String getalterPostalCode()
				{
					
					String PC=alterPostalCode.getAttribute("value");
					return PC;
				}
				public String getalterCountry()
				{
					
					String country=alterCountry.getAttribute("value");
					return country;
				}
				public String getalterCity()
				{
					
					String city=alterCity.getAttribute("value");
					return city;
				}
				
				
				
	

}
