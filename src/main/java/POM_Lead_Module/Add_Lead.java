package POM_Lead_Module;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Library_Files.UtilityClass;

public class Add_Lead 
{
	//Data members should be declare globally with access level private by using @findBy annotation
			
			@FindBy(xpath="//input[@id='salutation']")private WebElement Salutation;
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
			@FindBy(xpath="(//input[@id='alt_address_street'])[2]")private WebElement CopyFromleftCheckBox;
			@FindBy(xpath="//input[@id='email1-0']")private WebElement EmailAddress;
			@FindBy(xpath="(//button[@type='submit']//span[text()='Save'])[2]")private WebElement SaveBtn;
			@FindBy(xpath="//span[@id='back-btn-link']")private WebElement lead;
			@FindBy(xpath="//textarea[@id='description']")private WebElement Description;
			@FindBy(xpath="//input[@id='phone_fax']")private WebElement Fax;
			@FindBy(xpath="//input[@id='utm_url_c']")private WebElement UTM_URL;
			@FindBy(xpath="//input[@id='utm_campaign_c']")private WebElement UTM_Campaign;
			@FindBy(xpath="//input[@id='lead_source']")private WebElement LeadSource;
			@FindBy(xpath="//*[local-name()='svg' and @id='seach-btn-scrm_partner_contacts_leads_name']")private WebElement Partner_ContactSearchIcon;
			@FindBy(xpath="//input[@id='converted']")private WebElement Converted;
			@FindBy(xpath="//textarea[@id='status_description']")private WebElement Status_Description;
			@FindBy(xpath="//textarea[@id='lead_source_description']")private WebElement Lead_Source_Description;
			@FindBy(xpath="//input[@id='opportunity_amount']")private WebElement Opportunity_Amount;
			@FindBy(xpath="//input[@id='refered_by']")private WebElement Refered_by;
			@FindBy(xpath="//button[@form='relate-search-form']")private WebElement SearchOnWindow;
			@FindBy(xpath="//div[@role='dialog']//input[@id='first_name']")private WebElement windowFirstName;
			@FindBy(xpath="//div[@role='dialog']//input[@id='last_name']")private WebElement LastNameOnWindow;
			@FindBy(xpath="//input[@id='lead_scoring_c']")private WebElement LeadScore;
			//Xpath of Campaign field
			
			@FindBy(xpath="//*[local-name()='svg' and @id='seach-btn-campaign_name']")private WebElement CampaignSearchIcon;
			@FindBy(xpath="//div[@role='dialog']//input[@id='name']")private WebElement windowName;
			
			//Extra Email Address Setting add
			@FindBy(xpath="//input[@id='primary0']")private WebElement Primary;
			@FindBy(xpath="//input[@id='optOut0']")private WebElement OptedOut;
			@FindBy(xpath="//input[@id='invalid0']")private WebElement Invalid;
			
			//Assigned To
			@FindBy(xpath="//*[local-name()='svg' and @id='search-btn-assigned_user_name']")private WebElement AssignedToSearchIcon;
			
			//Initialize the constructor with access level public using PageFactory class
			public Add_Lead(WebDriver driver)
			{
				PageFactory.initElements(driver, this);
			}
			
			//Utilize within the methods with access level public
			/*public void clickOnAddButton()
			{
				Add.click();
			}*/
			public void selectSalutation(WebDriver driver,String salutation)
			{
				Salutation.click();
				
				driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+salutation+"')]")).click();
				
			}
			public void enterFirstName(String firstname)
			{	String s = Keys.chord(Keys.CONTROL, "a");
				FirstName.sendKeys(s);
				FirstName.sendKeys(Keys.DELETE);
				FirstName.sendKeys(firstname);
			}
			public void enterFirstName(String firstname,String Time )
			{	String s = Keys.chord(Keys.CONTROL, "a");
				FirstName.sendKeys(s);
				FirstName.sendKeys(Keys.DELETE);
				FirstName.sendKeys(firstname+Time);
			}
			public String enterLastName(WebDriver driver,String lastname) throws InterruptedException
			{	String s = Keys.chord(Keys.CONTROL, "a");
				LastName.sendKeys(s);
				LastName.sendKeys(Keys.DELETE);
				//LastName.clear();
				//((JavascriptExecutor) driver).executeScript("arguments[0].value ='';", LastName);
				LastName.sendKeys(lastname);
				
				String Lastname=LastName.getAttribute("value");
				return Lastname;
				
			}
			public void selectType(WebDriver driver, String type) throws InterruptedException
			{
				Type.click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+type+"')]")).click();
			} 
			public void enterOfficePhone(String officePhoneNo)
			{
				OfficePhone.sendKeys(officePhoneNo);
				
			}
			public void enterTitle(String title)
			{
				Title.sendKeys(title);
			}
			public void enterMobile(String mobile)
			{
				Mobile.sendKeys(mobile);
			}
			public void enterLeadScore(String leadScore)
			{
				LeadScore.sendKeys(leadScore);
			}
			public void enterDepartment(String department)
			{
				Department.sendKeys(department);
			}
			public void selectLoanType(WebDriver driver, String loantype) throws InterruptedException
			{
				LoanType.click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+loantype+"')]")).click();
			}
			public void enterAccountName(String accountName)
			{
				AccountName.sendKeys(accountName);
			}
			public void enterWebsite(String website)
			{
				Website.sendKeys(website);
			}
			public void selectStatus(WebDriver driver, String status) throws InterruptedException
			{
				Status.click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+status+"')]")).click();
			}
			public void selectApprovalStatus(WebDriver driver,String approvalstatus) throws InterruptedException
			{
				ApprovalStatus.click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+approvalstatus+"')]")).click();
			}
			public void enterAddress(String address)
			{
				Address.sendKeys(address);
			}
			public void enterState(String state)
			{
				State.sendKeys(state);
			}
			public void enterPostalCode(String postalCode)
			{
				PostalCode.sendKeys(postalCode);
			}
			public void enterCountry(String country)
			{
				Country.sendKeys(country);
			}
			public void enterCity(String city)
			{
				City.sendKeys(city);
			}
			public void AddressCopyFromLeft()
			{
				CopyFromleftCheckBox.click();
			}
			public String enterEmailAddress(WebDriver driver, String emailAddress) throws InterruptedException
			{	
				//Clear Field
				String s = Keys.chord(Keys.CONTROL, "a");
				EmailAddress.sendKeys(s);
				EmailAddress.sendKeys(Keys.DELETE);
				//EmailAddress.clear();
				//((JavascriptExecutor) driver).executeScript("arguments[0].value ='';", EmailAddress);
				
				EmailAddress.sendKeys(emailAddress);
				String email = EmailAddress.getAttribute("value");
				return email;
				
								
			}
			public void clickOnSavebtn()
			{
				SaveBtn.click();
			}
			public void ScrollPage(WebDriver driver)
			{
				//To scroll down upto EmailAddress field is showing
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",ApprovalStatus);
				
			}
			public void clickLead()
			{
				lead.click();
			}
			public void selectLeadSource(WebDriver driver, String leadSource) throws InterruptedException
			{
				
				LeadSource.click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+leadSource+"')]")).click();
			} 
			public void enterDescription(String description)
			{
				String s = Keys.chord(Keys.CONTROL, "a");
				Description.sendKeys(s);
				Description.sendKeys(Keys.DELETE);
				Description.sendKeys(description);
			}
			public void enterFax(String fax)
			{
				Fax.sendKeys(fax);
			}
			public void enterUTM_URL(String utm_url)
			{
				UTM_URL.sendKeys(utm_url);
			}
			public void enterUTM_Campaign(String utm_campaign)
			{
				UTM_Campaign.sendKeys(utm_campaign);
			}
			public void enterPartner_Contact(WebDriver driver, String partner_contact) throws InterruptedException
			{
				Partner_ContactSearchIcon.click();
				//windowFirstName.sendKeys(partner_contact);
				
				
				String[] sapratename = partner_contact.split(" ");
				String firstName = sapratename[0];
				String lastName = sapratename[1];
				
				//Clear the field
				String s = Keys.chord(Keys.CONTROL, "a");
				windowFirstName.sendKeys(s);
				windowFirstName.sendKeys(Keys.DELETE);
				//enter text in first name field
				windowFirstName.sendKeys(firstName);
				//Clear the field
				String s1 = Keys.chord(Keys.CONTROL, "a");
				LastNameOnWindow.sendKeys(s1);
				LastNameOnWindow.sendKeys(Keys.DELETE);
				//enter text in last name field
				LastNameOnWindow.sendKeys(lastName);
				SearchOnWindow.click();
				Thread.sleep(4000);
				//((JavascriptExecutor)driver).executeScript("window.scrollBy(0,100)");
				WebElement partner = driver.findElement(By.xpath("//table[@role='grid']//tbody//td//a[contains(text(),'"+partner_contact+"')]"));
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",partner);
				Thread.sleep(2000);
				partner.click();
			
			}
				/*Partner_ContactSearchIcon.click();
				//Handle Window popup
				Set<String> ids=driver.getWindowHandles();
				String mainWindow=driver.getWindowHandle();
				System.out.println("Parent Window" +mainWindow);
				ArrayList<String> al= new ArrayList<String>(ids);
				for(String handle:ids)
				{
				//String childWindowId=al.get(0);
				System.out.println("ChildWindow"+handle);
				}
				///driver.switchTo().window(childWindowId);
				windowFirstName.sendKeys(partner_contact);
				SearchOnWindow.click();
				driver.findElement(By.xpath("//table[@role='grid']//tbody//td//a[text()='"+partner_contact+"']")).click();*/
				
				
				/*String parent_window = driver.getWindowHandle();
				System.out.println("Before switching Title is : "+driver.getTitle());
				Partner_ContactSearchIcon.click();
				Set<String> s1 = driver.getWindowHandles();
				Iterator<String> i1 = s1.iterator();
				while(i1.hasNext())
				{
				    String child_window = i1.next();
				    if (!parent_window.equalsIgnoreCase(child_window))
				    {
				        driver.switchTo().window(child_window);
				        //induce some wait here for the elements to render
				        Thread.sleep(5000);
				        System.out.println("After switching title is : "+driver.getTitle());
				        //perform your other tasks
				        windowFirstName.sendKeys(partner_contact);
						SearchOnWindow.click();
						driver.findElement(By.xpath("//table[@role='grid']//tbody//td//a[text()='"+partner_contact+"']")).click();
				    }
				}
				driver.switchTo().window(parent_window);
				System.out.println("Back to parent Window : "+driver.getTitle());
			}*/
			

			public void clickOnConvertedRedioBtn()
			{
				Converted.click();
			}
			public void enterStatus_Description(String status_description)
			{
				Status_Description.sendKeys(status_description);
			}
			public void enterLead_Source_Description(String lead_source_description)
			{
				Lead_Source_Description.sendKeys(lead_source_description);
			}
			public void enterOpportunity_Amount(String opportunity_amount)
			{
				Opportunity_Amount.sendKeys(opportunity_amount);
			}
			public void enterRefered_by(String refered_by)
			{
				Refered_by.sendKeys(refered_by);
			}
			
			public void enterCampaign(WebDriver driver,String campaign) throws InterruptedException
			{
				CampaignSearchIcon.click();
				windowName.sendKeys(campaign);
				SearchOnWindow.click();
				Thread.sleep(4000);
				((JavascriptExecutor)driver).executeScript("window.scrollBy(0,100)");
				driver.findElement(By.xpath("//table[@role='grid']//tbody//td//a[contains(text(),'"+campaign+"')]")).click();
				
				
				
			}
			
			public boolean isPlusIconClicked(WebDriver driver, String secondaryemailaddress, int k, int l)
			{	 
			    try {
			    	
			    	WebElement PlusIcon=driver.findElement(By.xpath("//*[local-name()='svg' and @id='add-email-btn-"+k+"']"));
			    	UtilityClass.wait_until_element_found(driver, PlusIcon);
			    	PlusIcon.click();
			    	driver.findElement(By.xpath("//input[contains(@id,'email1-"+l+"')]")).sendKeys(secondaryemailaddress);
			    	
			    	return true;
			    		
			    } catch(Exception e){
			    	System.out.println("Unable to click on PlusIcon");
			        return false;
			    }
			}
			
			public void SelectemailAddressSettingOption(WebDriver driver, String secondaryemailaddress, int i, int k, int l) throws InterruptedException
			{
				
				
				if(i==1) {
					WebElement primary=driver.findElement(By.xpath("//input[contains(@id,'email1-0')]"));
				String primaryemail=primary.getAttribute("value");
				System.out.println(primaryemail+"is by default Primary email address");
				
					driver.findElement(By.xpath("//*[local-name()='svg' and @id='add-email-btn-"+k+"']")).click();
			    	Actions act=new Actions(driver);
			    	
			    	
			    	driver.findElement(By.xpath("//*[local-name()='svg' and @id='add-email-setting-btn1']")).click();
			    	WebElement OptedOut=driver.findElement(By.xpath("//ul[contains(@class,'MuiList-padding')]//span[text()='Opted Out']"));
			    	String Optedemail=OptedOut.getText();
			    	act.moveToElement(OptedOut).perform();
			    	act.click().perform();
			    	//driver.switchTo().parentFrame();
			    	Thread.sleep(2000);
			    	WebElement email = driver.findElement(By.xpath("//input[contains(@id,'email1-"+i+"')]"));
			    	act.moveToElement(email).perform();
			    	act.click().perform();
			    	email.sendKeys(secondaryemailaddress);
			    	Thread.sleep(2000);
			    	String emailAddress = email.getAttribute("value");
			    	System.out.println(emailAddress+ " is " +Optedemail);
			    	
			    	
			    	//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",AssignedToSearchIcon);
				}
				else if (i==2)
				{
					driver.findElement(By.xpath("//*[local-name()='svg' and @id='add-email-btn-"+k+"']")).click();
			    	Actions act=new Actions(driver);
			    	
			    	
			    	driver.findElement(By.xpath("//*[local-name()='svg' and @id='add-email-setting-btn2']")).click();
			    	
			    	
			    	WebElement Invalid = driver.findElement(By.xpath("//ul[contains(@class,'MuiList-padding')]//span[text()='Invalid']"));
			    	String invalidemail = Invalid.getText();
			    	//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",AssignedToSearchIcon);
			    	act.moveToElement(Invalid).perform();
			    	act.click().perform();
			    	//driver.switchTo().parentFrame();
			    	Thread.sleep(2000);
			    	WebElement email = driver.findElement(By.xpath("//input[contains(@id,'email1-"+l+"')]"));
			    	act.moveToElement(email).perform();
			    	act.click().perform();
			    	email.sendKeys(secondaryemailaddress);
			    	Thread.sleep(2000);
			    	String emailAddress = email.getAttribute("value");
			    	System.out.println(emailAddress+ " is " +invalidemail);
			    	
			    	//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",AssignedToSearchIcon);
				}
				
				
				
				
				
				
				/*//WebElement settingBtn=driver.findElement(By.xpath("//*[local-name()='svg' and @id='add-email-setting-btn"+i+"']"));
				//settingBtn.click();
				/*if(i==0)
					{
					Primary.click();
					}
				else*/ 
			/*try {
					
					    	//k=0;
					    	WebElement PlusIcon=driver.findElement(By.xpath("//*[local-name()='svg' and @id='add-email-btn-"+k+"']"));
					    	UtilityClass.wait_until_element_found(driver, PlusIcon);
					    	PlusIcon.click();
					    	//i=1;
					    	Thread.sleep(2000);
					    	driver.findElement(By.xpath("//*[local-name()='svg' and @id='add-email-setting-btn"+i+"']")).click();
					    	Thread.sleep(2000);
					    	driver.findElement(By.xpath("//ul[contains(@class,'MuiList-padding')]//input[@id='optOut1']")).click();
					    	//l=11;
					    	Thread.sleep(2000);
					    	WebElement enterEmail=driver.findElement(By.xpath("//input[contains(@id,'email"+l+"')]"));
					    	enterEmail.click();
					    	enterEmail.sendKeys(secondaryemailaddress);
					    	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",SaveBtn);
					    	return true;
					    		
					    
													
					    	}catch(Exception e){
						    	System.out.println("Unable to click on PlusIcon");
						        return false;
						
			}
				/*else if(i==2)
					
				{
				WebElement PlusIcon=driver.findElement(By.xpath("//*[local-name()='svg' and @id='add-email-btn-"+k+"']"));
					    	UtilityClass.wait_until_element_found(driver, PlusIcon);
					    	PlusIcon.click();
					    	//i=1;
					    	driver.findElement(By.xpath("//*[local-name()='svg' and @id='add-email-setting-btn"+i+"']"));
					    	driver.findElement(By.xpath("//ul[contains(@class,'MuiList-padding')]//input[@id='invalid0']")).click();
					    	//l=11;
					    	WebElement enterEmail=driver.findElement(By.xpath("//input[contains(@id,'email"+l+"')]"));
					    	enterEmail.click();
					    	enterEmail.sendKeys(secondaryemailaddress);
					    	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",SaveBtn);
					    	return true;
					    		
					    } catch(Exception e){
					    	System.out.println("Unable to click on PlusIcon");
					        return false;
					
					
				
					}
					driver.findElement(By.xpath("//ul[contains(@class,'MuiList-padding')]//input[@id='invalid0']")).click();
					//driver.findElement(By.xpath("//*[local-name()='svg' and @id='add-email-btn-2']")).click();
				
				}*/
			}	
			
			public void AssignedTo(WebDriver driver, String assigned) throws InterruptedException
			{
				AssignedToSearchIcon.click();
				String[] sapratename = assigned.split(" ");
				String firstName = sapratename[0];
				String lastName = sapratename[1];
				UtilityClass.CheckPageLoaded();
				
				//Clear the field
				String s = Keys.chord(Keys.CONTROL, "a");
				windowFirstName.sendKeys(s);
				windowFirstName.sendKeys(Keys.DELETE);
				//enter text in first name field
				windowFirstName.sendKeys(firstName);
				//Clear the field
				String s1 = Keys.chord(Keys.CONTROL, "a");
				LastNameOnWindow.sendKeys(s1);
				LastNameOnWindow.sendKeys(Keys.DELETE);
				//enter text in last name field
				LastNameOnWindow.sendKeys(lastName);
				
				//windowFirstName.sendKeys(assigned);
				SearchOnWindow.click();
				Thread.sleep(4000);
				//((JavascriptExecutor)driver).executeScript("window.scrollBy(0,500)");
				WebElement name = driver.findElement(By.xpath("//table[@role='grid']//tbody//td//a[contains(text(),'"+assigned+"')]"));
				
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",name);
				name.click();
			
			
			}
			public void scrollpage(WebDriver driver)
			{
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",SaveBtn);
			}
			public void ScrollPageUptocity(WebDriver driver)
			{
				//To scroll down upto EmailAddress field is showing
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",City);
				
			}
			
			public void clickOnSavebtn1(WebDriver driver) throws InterruptedException
			{
				WebElement element = driver.findElement(By.xpath("(//button[@type='submit']//span[text()='Save'])[2]"));
				
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",element);
				Thread.sleep(2000);
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
				//element.sendKeys(Keys.ENTER);
			}
			public void scrolluptoDescription(WebDriver driver)
			{
				
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",Description);
			}
			public void scrolluptoUTMURL(WebDriver driver)
			{
				
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",UTM_URL);
			}
}
