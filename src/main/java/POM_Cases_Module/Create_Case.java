package POM_Cases_Module;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Library_Files.CommonFunctions;

public class Create_Case {
			//Data members should be declare globally with access level private by using @FindBy annotation
			@FindBy(xpath="//input[@id='priority']") private WebElement Priority;
			@FindBy(xpath="//input[@id='state']") private WebElement State;
			@FindBy(xpath="//input[@id='status']") private WebElement Status;
			@FindBy(xpath="//input[@id='type']") private WebElement Type;
			@FindBy(xpath="//*[local-name()='svg' and @data-icon='copy']") private WebElement Subject;
			@FindBy(xpath="//input[@id='linkedin_account_c']") private WebElement Linkedin_account;
			@FindBy(xpath="//body[@id='tinymce']") private WebElement Note;
			@FindBy(xpath="//textarea[@id='resolution']") private WebElement Resolution;
			@FindBy(xpath="//textarea[@id='update_text']") private WebElement Update_text;
			@FindBy(xpath="//input[@id='internal']") private WebElement InternalUpdate;
			@FindBy(xpath="//input[@id='case_update_form']") private WebElement Update_form;
			@FindBy(xpath="//input[@id='case_attachments_display']") private WebElement Attachments_display;
			//Back to ListView 
			@FindBy(xpath="//span[@id='back-btn-link']") private WebElement Cases;
			//Suggestion
			//@FindBy(xpath="//*[local-name()='svg' and @title='Suggestions']") private WebElement BulbIcon;
			@FindBy(xpath="//button[@title='Subject']") private WebElement BulbIcon;
			@FindBy(xpath="//div[@role='dialog']//input[@id='name']")private WebElement windowName;
			@FindBy(xpath="//div[@role='dialog']//span[text()='Reset']") private WebElement ResetBtn;
			@FindBy(xpath="//span[text()='Search']")private WebElement SearchOnWindow;
			@FindBy(xpath="//button[@aria-label='settings']")private WebElement SettingInListView;
			@FindBy(xpath="//form[@id='filter-form']//span[text()='Reset']") private WebElement ResetBtnOnFilter;
			@FindBy(xpath="//form[@id='filter-form']//input[@id='case_number']") private WebElement CaseNumberOnFilter;
			@FindBy(xpath="//form[@id='filter-form']//input[@id='name']") private WebElement SubjectOnFilter;
			@FindBy(xpath="//form[@id='filter-form']//span[text()='Search']") private WebElement SearchBtnOnFilter;
			@FindBy(xpath="//span[text()='Mass update']")private WebElement MassUpdate;
			@FindBy(xpath="//*[local-name()='svg' and @id='seach-btn-assigned_user_name']")private WebElement AssignedToIconOnMassUpdate;
			@FindBy(xpath="//div[@role='dialog']//input[@id='case_number']")private WebElement CaseNumberOnMassUpdate;
			@FindBy(xpath="//div[@role='dialog']//div[@id='priority']")private WebElement PriorityOnMassUpdate;
			@FindBy(xpath="//div[@role='dialog']//*[local-name()='svg' and @id='seach-btn-account_name']")private WebElement AccountNameOnMassUpdate;
			@FindBy(xpath="//form[@id='relate-search-form']//input[@id='first_name']")private WebElement windowFirstName;
			@FindBy(xpath="//form[@id='relate-search-form']//input[@id='last_name']")private WebElement LastNameOnWindow;
			@FindBy(xpath="//div[@role='dialog']//span[text()='Save']")private WebElement SavebtnOnMassUpdate;
			@FindBy(xpath="//form[@id='relate-search-form']//span[text()='Search']")private WebElement SearchWindowBtn1;
			
			
			
			//Initialize the constructor with access level public using PageFactory class
			public Create_Case(WebDriver driver)
			{
				PageFactory.initElements(driver, this);
			}
				
			//Utilize within the methods with access level public 
			public void selectPriority(WebDriver driver, String priority) 
			{
				Priority.click();
				
				driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+priority+"')]")).click();
			}
			
			public void selectState(WebDriver driver, String state,String status,String resolution) throws InterruptedException 
			{
				/*String s = Keys.chord(Keys.CONTROL, "a");
				State.sendKeys(s);
				State.sendKeys(Keys.DELETE);*/
				//Select state
				State.click();
				driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+state+"')]")).click();
				//Select Status
				/*String s1 = Keys.chord(Keys.CONTROL, "a");
				Status.sendKeys(s1);
				Status.sendKeys(Keys.DELETE);*/
				Status.click();
				driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+status+"')]")).click();
				//Resolution.sendKeys(resolution);
				//if State Closed then only resolution add
				
				
				if(state.equals("Closed"))
				{
					//scrollUptoState(driver);
					Thread.sleep(2000);
					BulbIcon.click();
					ResetBtn.click();
					/*String s1 = Keys.chord(Keys.CONTROL, "a");
					windowName.sendKeys(s1);
					windowName.sendKeys(Keys.DELETE);*/
					windowName.sendKeys(resolution);
					SearchOnWindow.click();
					Thread.sleep(4000);
					driver.findElement(By.xpath("//table[@role='grid']//tbody//td//a[text()='"+resolution+"']")).click();
					Resolution.sendKeys(resolution);
					Thread.sleep(2000);
					scrollUptoState(driver);
					/*String s = Keys.chord(Keys.CONTROL, "a");
					Resolution.sendKeys(s);
					Resolution.sendKeys(Keys.END);
					Resolution.sendKeys(Keys.ENTER);
					Resolution.sendKeys(resolution);*/
				}
			}
				
				public void selectType(WebDriver driver, String type)
				{
					Type.click();
					driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+type+"')]")).click();
				}
				
				public void clickOnGeneralFeedbackforSubject(WebDriver driver, String subject) throws InterruptedException
				{
					Subject.click();
					WebElement selectedsubjectclear = driver.findElement(By.xpath("//input[@id='name']"));
					String SuggestedSubject = CommonFunctions.GetText(selectedsubjectclear);
					System.out.println("Suggested Subject "+SuggestedSubject);
					Thread.sleep(2000);
					String s = Keys.chord(Keys.CONTROL, "a");
					selectedsubjectclear.sendKeys(s);
					selectedsubjectclear.sendKeys(Keys.DELETE);
					selectedsubjectclear.sendKeys(subject);
				}
				
				public void enterLinkedin_account(String linkedin_account)
				{
					Linkedin_account.sendKeys(linkedin_account);;
				}
				
				public void enterNote(WebDriver driver,String note)
				{
					WebElement frame=driver.findElement(By.xpath("//iframe[@class='tox-edit-area__iframe']"));
					driver.switchTo().frame(frame);
					Note.sendKeys(note);
					driver.switchTo().defaultContent();
				}
				public void enterResolution(String resolution)
				{
					Resolution.sendKeys(resolution);
				}
				
				
				//Back to List View of Cases Module
				public void backToListView()
				{
					Cases.click();
				}
				
				
			
				public void clickOnSubject(WebDriver driver, String casenumber, String subject) throws InterruptedException
				{
					Thread.sleep(4000);
					//driver.findElement(By.xpath("//tbody//td[@data-tableid='ListViewTable']//div[text()='"+casenumber+"']/../..//a[text()='"+subject+"']")).click();
					driver.findElement(By.xpath("//tbody//td[@data-tableid='ListViewTable']/../..//a[text()='"+subject+"']")).click();
				}
				
				
				
				public void clickOnSubject1(WebDriver Tempdriver, String casenumber, String subject) throws InterruptedException
				{
					Thread.sleep(4000);
					Tempdriver.findElement(By.xpath("//tbody//td[@data-tableid='ListViewTable']//div[text()='"+casenumber+"']/../..//a[text()='"+subject+"']")).click();
				}
				
				public void scrollUptoState(WebDriver driver)
				{
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", State);
				}
				
	}

