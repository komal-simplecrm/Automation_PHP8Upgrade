package POM_Lead_Module;




import java.awt.AWTException;
import java.text.ParseException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;
import Library_Files.Base_Class;
import Library_Files.UtilityClass;
import POM_Account_Module.Add_Account;
import POM_Filter.FilterData;
import java.time.Duration;




public class Lead_ListView extends Base_Class
{
	SoftAssert soft = new SoftAssert();
	FilterData filter = new FilterData(driver);
	Add_Account add_Account = new Add_Account(driver);
	Actions act = new Actions(driver);
	//Data members should be declare globally with access level private by using @findBy annotation
	@FindBy(xpath="//button[@type='button']//span[text()='Add']")private WebElement Add;
	//@FindBy(xpath="//table[@role='grid']//td[@data-testid='MuiDataTableBodyCell-1-0']")private WebElement leadName;
	//@FindBy(xpath="//button[@title='Edit']")private WebElement edit;
	//@FindBy(xpath="//tr[contains(@id,'MUIDataTableBodyRow')]//a[text()='Automation Test Record1']/../../../..//input[contains(@id,'MUIDataTableSelectCell')]")private WebElement CheckBox;
	//@FindBy(xpath="//input[@id='MUIDataTableSelectCell-0']")private WebElement CheckBox;
	@FindBy(xpath="//a[contains(text(),'Mr. John Patel')]/parent::p/../..")private WebElement leadName;
	@FindBy(xpath="//button[@title='Edit Record']")private WebElement Edit;
	@FindBy(xpath="//input[@aria-label='search']")private WebElement SearchBtn;
	@FindBy(xpath="//button[@type='submit']")private WebElement Searchicon;
	@FindBy(xpath="//span[text()='Delete']")private WebElement Delete;
	@FindBy(xpath="//span[text()='Yes']")private WebElement Yes;
	@FindBy(xpath="//button[@aria-haspopup='menu'or @title='More Action']")private WebElement Menu;
	//@FindBy(xpath="//ul[@role='menu']/li//span[text()='DELETE']")private WebElement MenuDelete;
	@FindBy(xpath="//div[@role='alert']")private WebElement Alert;
	
	
	//Mass Update xpaths
	@FindBy(xpath="//span[text()='Mass update']")private WebElement MassUpdate;
	@FindBy(xpath="//div[@role='dialog']//input[@id='case_number']")private WebElement CaseNumberOnMassUpdate;
	@FindBy(xpath="//div[@role='dialog']//div[@id='priority']")private WebElement PriorityOnMassUpdate;
	@FindBy(xpath="//div[@role='dialog']//*[local-name()='svg' and @id='seach-btn-account_name']")private WebElement AccountNameOnMassUpdate;
	@FindBy(xpath="//div[@role='dialog']//input[@id='first_name']")private WebElement windowFirstName;
	@FindBy(xpath="//div[@role='dialog']//input[@id='last_name']")private WebElement LastNameOnWindow;
	@FindBy(xpath="//div[@role='dialog']//span[text()='Save']")private WebElement SavebtnOnMassUpdate;
	@FindBy(xpath="//div[@role='dialog']//span[text()='Search']")private WebElement SearchWindowBtn1;
	@FindBy(xpath="//*[local-name()='svg' and @id='seach-btn-assigned_user_name']")private WebElement AssignedToIconOnMassUpdate;
	
	//Filter Search xpaths
	@FindBy(xpath="//span[text()='Apply Filter']")private WebElement ApplyFilterBtn;
	@FindBy(xpath="//span[text()='Clear Filter']")private WebElement ClearFilterBtn;
	@FindBy(xpath="//button[@title='Close Filter']")private WebElement CloseFilterIcon;
	@FindBy(xpath="//button[@aria-label='settings']")private WebElement SettingInListView;
	@FindBy(xpath="//form[@id='filter-form']//span[text()='Reset']") private WebElement ResetBtnOnFilter;
	@FindBy(xpath="//form[@id='filter-form']//input[@id='case_number']") private WebElement CaseNumberOnFilter;
	@FindBy(xpath="//form[@id='filter-form']//input[@id='bug_number']") private WebElement BugNumberOnFilter;
	@FindBy(xpath="//form[@id='filter-form']//input[@id='name']") private WebElement SubjectOnFilter;
	@FindBy(xpath="//span[text()='Search']") private WebElement SearchBtnOnFilter;
	@FindBy(xpath="//form[@id='filter-form']//input[@id='document_name']") private WebElement DocumentNameOnFilter;
	@FindBy(xpath="//button[@title='Filter Drawer keep to be unlock']") private WebElement LockiconOnFilter;
	//button[@title='Filter Drawer keep to be unlock']
	//Xpaths of Export
	@FindBy(xpath="//span[text()='Export']") private WebElement Export;
	@FindBy(xpath="//div[@aria-labelledby='alert-dialog-title']//span[text()='Yes']") private WebElement ExportYes;
	@FindBy(xpath="(//div[@id='scrollableDiv']//div[contains(@class,'MuiAvatar-circular')])[1]") private WebElement ModuleIcon;
	@FindBy(xpath="//span[text()='Download available!']") private WebElement Download_available;
	//@FindBy(xpath="//button[@id='download-btn-0']") private WebElement DownLoadFileBtn;
	@FindBy(xpath="//div[contains(@class,'MuiCardHeader-action')]//button[not(contains(@class,'tile-clear-icon') or @aria-label='settings')]") private WebElement DownLoadFileBtn;
	@FindBy(xpath="//span[@title='Clear all notifications']") private WebElement ClearAllBtn;
	
	//Xpaths for Column arrangement
	
	@FindBy(xpath="//span[text()='Save']") private WebElement SaveOnColumnPopup;
	
	
	//Initialize the constructor with access level public using PageFactory class
	public Lead_ListView(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		
	}

	//Utilize within the methods with access level public
	public void clickOnAddButton()
	{
		Add.click();
	}
	public void scrolluptoAddBtn(WebDriver driver)
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(Add).perform();
		
		//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",BackToListView);
	}
	public void clickOnAddButton1(WebDriver driver)
	{
		driver.findElement(By.xpath("//button[@type='button']//span[text()='Add']")).click();
	}
	public void enterTextInSearchBtn(WebDriver driver, String leadname) throws InterruptedException
	{
		Thread.sleep(4000);
		String s = Keys.chord(Keys.CONTROL, "a");
		SearchBtn.sendKeys(s);
		Thread.sleep(2000);
		SearchBtn.sendKeys(Keys.DELETE);
		Thread.sleep(2000);
		SearchBtn.sendKeys(leadname);
		Searchicon.click();
		String Name = SearchBtn.getText();
		System.out.println(Name);
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
	}
	public void clickOnName(WebDriver driver,String searchleadname) throws InterruptedException
	{
		
		WebElement element;
		//element=driver.findElement(By.xpath("//a[contains(text(),'"+searchleadname+"')]/parent::p/../.."));
		//element=driver.findElement(By.xpath("//a[contains(text(),'"+searchleadname+"')]/parent::p/../../..//button[@title='Edit']"));
		//UtilityClass.wait_until_element_found(driver, element);
		element=driver.findElement(By.xpath("//a[contains(text(),'"+searchleadname+"')]"));
		Thread.sleep(4000);
		element.click();
		//((JavascriptExecutor)driver).executeScript("arguments[0].click()",element);
		//edit.click();
		//driver.findElement(By.xpath("//p//a[text()='"+name+"']")).click();
		//driver.findElement(By.xpath("//table[@role='grid']//td[@data-testid='MuiDataTableBodyCell-1-0']")).click();
	}
	public void clickOnEditBtn()
	{   
		
		Edit.click();
	}
	
	public void selectCheckBox(WebDriver driver, String record )
	{   
		driver.findElement(By.xpath("//tr[contains(@id,'MUIDataTableBodyRow')]//a[contains(text(),'"+record+"')]/../../../..//input[contains(@id,'MUIDataTableSelectCell')]")).click();
		//CheckBox.click();
	}
	public void clickOnDelete()
	{   
		
		Delete.click();
		Yes.click();
	}
	public void menu(WebDriver driver,String option) throws InterruptedException
	{
		Menu.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//ul[@role='menu']//li//span[contains(text(),'"+option+"')]")).click();
		//MenuDelete.click();
	}
	public void confirmDelete()
	{
		Yes.click();
	}
	public String EveryPageAlert()
	{
		String msg=Alert.getText();
		if(msg.equals(""))
		{
			msg = Alert.getAttribute("innerText");
			if(msg.equals(""))
			{
				msg = Alert.getAttribute("textContent");
			}
		}
		return msg;
		
	}
	public String verfyLeadName(WebDriver driver, String LeadName)
	{
		WebElement Leadname=driver.findElement(By.xpath("//tbody//tr//td[@data-tableid='ListViewTable']//p//a[contains(text(),'"+LeadName+"')]"));
		String leadname=Leadname.getText();
		return leadname;
	
	
	}
	public void scrollUpToMenu(WebDriver driver)
	{
		((JavascriptExecutor)driver).executeScript("scrollRight = arguments[0]. offsetWidth", Menu);
	}

	
	//Function to get the Notification count
	public String getNotification()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement count = driver.findElement(By.xpath("(//header//span[@title='Notifications']//span)[3]"));
		//WebElement element = driver.findElement(By.xpath("element_xpath"));
		String elementText = (String) js.executeScript("return arguments[0].innerText;", count);
		return elementText;
	}
	
	//Get the records count of same as subject entered in search field in list view and click on check boxes
	public void clickOnCheckBoxes(String subject) throws InterruptedException
	{
		
		//Get count of check boxes which contains the subject
		List<WebElement> rows = driver.findElements(By.xpath("//tbody//td[@data-tableid='ListViewTable']//a[contains(text(),'"+subject+"')]"));
		int count = rows.size();
		System.out.println("ROW COUNT : "+count);
		Thread.sleep(2000);
		//Click on All select check box in the header
		driver.findElement(By.xpath("//span[@data-description='row-select-header']")).click();
		Thread.sleep(2000);
		//Get count of all check boxes 
		String totalCount = driver.findElement(By.xpath("//button[@aria-label='settings']/..//span[contains(@class,'anchorOriginTopRightCircular')]")).getText();
		int totalcount = Integer.parseInt(totalCount);
		
		//Apply assertion the count of check boxes containing subject equals to count of total check box selected.
		//soft.assertEquals(count, totalcount);
		//soft.assertAll();
	}
	
	
	//In Future this functionality is used for Column and Import functionality
	public void clickSettingInListView(WebDriver driver, String setting, String casenumber, String subject, String sheetname) throws InterruptedException
	{
		Thread.sleep(4000);
		
		UtilityClass.wait_until_element_found(driver, SettingInListView);
		SettingInListView.click();
		
		Thread.sleep(2000);
		//Actions act = new Actions(driver);
		WebElement options = driver.findElement(By.xpath("//ul[@role='menu']//li//span[text()='"+setting+"']"));
		act.moveToElement(options).perform();
    	act.click().perform();
		
		ResetBtnOnFilter.click();
		if(sheetname.equals("Case"))
		{
			CaseNumberOnFilter.sendKeys(casenumber);
			SubjectOnFilter.sendKeys(subject);
		}
		
		else if(sheetname.equals("Documents"))
		{
			DocumentNameOnFilter.sendKeys(subject);
		}
		else if(sheetname.equals(""))
		{
			
		}
		SearchBtnOnFilter.click();
		
	}
	//Mass update Functionality Cases Module, mass update using AssingedTo field
	public void massUpdate(WebDriver driver, String subject, String assignedTo) throws InterruptedException, AWTException, ParseException
	{
		
		//Call the function to get the count of records and click on the check boxes in list view
		clickOnCheckBoxes(subject);
		//driver.findElement(By.xpath("//span[@data-description='row-select-header']")).click();
		//Click on the Mass update button
		MassUpdate.click();
		Thread.sleep(1000);
		//Click on search icon of Assigned to field
		AssignedToIconOnMassUpdate.click();
	
		Thread.sleep(2000);
		String[] sapratename = assignedTo.split(" ");
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
		
		//windowFirstName.sendKeys(assigned);
		SearchWindowBtn1.click();
		Thread.sleep(4000);
		
		//Click on the name of assigned user in the first record in the table 
		WebElement element= driver.findElement(By.xpath("(//div[@role='dialog']//table//a[text()='"+assignedTo+"'])[1]"));
		//Scroll up to Assigned user name
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",element);
		element.click();
		Thread.sleep(2000);
		SavebtnOnMassUpdate.click();
		//Call the get List view data function to verify the record is assigned to the correct user or not.
		FilterData.getListViewData("Assigned to" , assignedTo );
		
	}
	
	//Special function for Cases and Documents module record search.
	public void clickOnFilter(WebDriver driver, String casenumber, String subject, String sheetname) throws InterruptedException
	{ 
		ClickOnApplyFilter();
		
		if(sheetname.equals("Case"))
		{
		CaseNumberOnFilter.sendKeys(casenumber);
		SubjectOnFilter.sendKeys(subject);
		}
		else if(sheetname.equals("Bugs"))
		{
			BugNumberOnFilter.sendKeys(casenumber);
			SubjectOnFilter.sendKeys(subject);
		}
		else if(sheetname.equals("Documents"))
		{
			DocumentNameOnFilter.sendKeys(subject);
		}
		SearchBtnOnFilter.click();
		Thread.sleep(8000);
		CloseFilterIcon.click();
		
	}
	public void rightSideMenu(WebDriver driver, String elementName)//elementName:- BPM Process, ADMIN, Search, Smart Search, Activities, Activity Stream, News Feeds, Notifications
	{
		System.out.println("Enter in the righ side menu function");
		WebElement element = driver.findElement(By.xpath("//button[@title='"+elementName+"']"));
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
    	act.click().perform();
	}
	
	//Function for Export functionality
	public void ExportAllRecords(WebDriver driver, String subject, String Title) throws InterruptedException
	{
		
		WebElement notify = driver.findElement(By.xpath("//span[@title='Notifications']"));
		ClickOnNotification();
		Thread.sleep(2000);
		
		driver.navigate().refresh();
		
		UtilityClass.CheckPageLoaded();
				
		String notificationcount = getNotification();
		System.out.println(notificationcount);
		int Ncount1 = Integer.parseInt(notificationcount)+1;
		System.out.println("Notification count: " +Ncount1);
		Thread.sleep(2000);
		
		//Call the function to get the count of records and click on the check boxes in list view
		clickOnCheckBoxes(subject);
		
		Export.click();
		Thread.sleep(2000);
		ExportYes.click();
		
		Thread.sleep(5000);
		
		UtilityClass.CheckPageLoaded();
		
		String notificationcount1 = getNotification();
		int Ncount2 = Integer.parseInt(notificationcount1);
		System.out.println("Notification count after export: " +Ncount2);
		test.info("Notification count after export: " +Ncount2);
		ClickOnNotification();
		Thread.sleep(2000);
		String moduleName = ModuleIcon.getAttribute("title");
		System.out.println(moduleName);
		test.info(moduleName+" module file exported.");
		if(Ncount1 == Ncount2)
		{
			if(Title.equalsIgnoreCase(moduleName))
			{
				System.out.println("Records are Exproted successfully.");
				//notify.click();
				Thread.sleep(2000);
				act.moveToElement(DownLoadFileBtn).click().perform();
				test.info("File downloaded successfully.");
				
				//As discussed with Ritesh sir the file exist functionality no need to automate its keep for manual testing.
						/*LocalDate date = LocalDate.now();  
						
						 // current time   
					      Format f = new SimpleDateFormat("HH_mm_ss");
					      String strResult = f.format(new Date());
					    
					      System.out.println("Time = "+strResult);
				Thread.sleep(2000);
				
				
					//Check the exported file is downloaded or not      
					  // Define the file path and name
					   String fileName = moduleName+date+" "+strResult;
					    String filePath = "C:\\Users\\LENOVO-PC\\Downloads\\"+fileName+".csv";
					     System.out.println(filePath);
					     test.info("File path: "+filePath);
					     
					     // Create a file object
					     File file = new File(filePath);
					     boolean result  = file.exists();
					     System.out.println(result);
					    
					     // Check if the file exists
					     if (result==true) {
					          // The file exists
					          System.out.println("File exists!");
					         test.info("File exists!");
					      } else {
					          // The file does not exist
					          System.out.println("File does not exist.");
					          test.info("File does not exist.");
					      }*/

			}
			else {
				System.out.println("Module name is not matched");
				test.info("Module name is not matched");
				soft.fail("Module name is not matched");
			}
			
		}
		else 
		{
			System.out.println("Count is not increased.");
			test.info("Count is not increased.");
			soft.fail("Count is not increased.");
		}
		soft.assertAll();
	}
	
	//Function for click on notification icon
	public void ClickOnNotification()
	{
		int MAX_ATTEMPTS = 5;
		int attempts = 0;
		while (attempts < MAX_ATTEMPTS) {
		    try {
		    	WebElement notify = driver.findElement(By.xpath("//span[@title='Notifications']"));
		    	notify.click();
		        
		        break;
		    } catch (StaleElementReferenceException e) {
		        // Element is stale, retry
		    	System.out.println("StaleElementReferenceException!");
		        attempts++;
		    }
		}
	}
	//Function of column chooser
	public void ColumnFunctionality(String FieldLabel, String dataFromExcelSheet) throws InterruptedException, AWTException
	{
		add_Account.settingFilter(driver,"Columns");
		
		System.out.println("Field Label: " +FieldLabel);
		/*List<WebElement> Displayedfields = driver.findElements(By.xpath("//div[@role='dialog']//h6[text()='Displayed']/following-sibling::h6"));
		int i = 1;
		for(WebElement fields : Displayedfields)
		{
			WebElement field = driver.findElement(By.xpath("(//div[@role='dialog']//h6[text()='Displayed']/following-sibling::h6)["+i+"]"));
		
			String fielddisplayed = field.getText();
		
			if(FieldLabel.equalsIgnoreCase(fielddisplayed)|| fielddisplayed.contains(FieldLabel))
			{
				SaveOnColumnPopup.click();
			}
			else
			{*/
			
				List<WebElement> Hiddenfields = driver.findElements(By.xpath("//div[@role='dialog']//h6[text()='Hidden']/following-sibling::h6"));
				int count = Hiddenfields.size();
				int j = 1;
				for(int i=1; i<=count;i++)
				//for(WebElement fieldsHide : Hiddenfields)
				{
					System.out.println("j:"+j);
					
					WebElement Hfield  = driver.findElement(By.xpath("(//div[@role='dialog']//h6[text()='Hidden']/following-sibling::h6)["+j+"]")); 
					Thread.sleep(1000);
					String fieldHidden = Hfield.getText();
					
					System.out.println(fieldHidden+" field is hidden.");
					
					if(FieldLabel.equalsIgnoreCase(fieldHidden) || fieldHidden.contains(FieldLabel))
					{
						
						//Thread.sleep(5000);
						((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",Hfield);
						
						//Building a drag and drop action
						System.out.println("Enter in FieldLabel.equalsIgnoreCase(fieldHidden)");
						
						
						Thread.sleep(2000);
						Actions act = new Actions(driver);
						act.clickAndHold(Hfield);
						Thread.sleep(2000);
						//act.moveToElement(driver.findElement(By.xpath("//div[@class='mobileLayout-Col-Cont']//h6[text()='Displayed']/following-sibling::h6")));
						Thread.sleep(2000);
						//this method work using pixels if layout change need to change pixels, 10 is horizontal pixel and 0 is vertical pixel.
						act.moveByOffset(10, 0);
						//Thread.sleep(2000);(
						act.release(driver.findElement(By.xpath("(//div[@class='mobileLayout-Col-Cont']//h6[text()='Displayed']/following-sibling::h6)[2]")));
						Thread.sleep(2000);
						act.build().perform();

						//Performing the drag and drop action
						
						
						Thread.sleep(5000);
						WebElement Save = driver.findElement(By.xpath("//span[text()='Save']"));
						//System.out.println("result "+Save);
						 Save.click();
						/*if(result != null)
						{
							result.click();
						}
						else
						{
							result.click();
						}*/
						Thread.sleep(4000);
						//driver.navigate().refresh();
						//Thread.sleep(2000);
						System.out.println("FieldLabel "+FieldLabel);
						System.out.println("dataFromExcelSheet "+dataFromExcelSheet);
						Thread.sleep(5000);
						try {
						FilterData.getListViewData( FieldLabel, dataFromExcelSheet);
						}
						catch (Exception e)
						{
							
						}
						break;
					}
					else if(i==count)
					{
						System.out.println(FieldLabel+" column does not exist in hidden list.");
						driver.findElement(By.xpath("//span[text()='Cancel']")).click(); 
						
						break;
						
					}
					else
					{
						
						System.out.println(FieldLabel+" column name does not matched.");
						
					}
					j++;
					
				}
			
				
			}
		//}
	//}
	
	//Function to scroll upto edit button
	public void scrollUptoEditBtn(WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.moveToElement(Edit).perform();
    	
	}
	
	//Function of click on Apply Filter button
	public void ClickOnApplyFilter()
	{
		boolean LockiconOnFilter = false;
		try {
			
			ClearFilterBtn.click();
		}
		catch (NoSuchElementException | StaleElementReferenceException e){
			System.out.println("Clear Filter Button is not present.");
		}
		
		try {
			
			LockiconOnFilter = driver.findElement(By.xpath("//button[@title='Filter Drawer keep to be unlock']")).isDisplayed();
		}
		catch (NoSuchElementException | StaleElementReferenceException e){
			System.out.println("Filter Lock icon is not present.");
		}
		if(LockiconOnFilter==true)
		{
			System.out.println("Filter is already open.");
		}
		else
		{
			ApplyFilterBtn.click();
		}
		
	}
	
	public void menu1(WebDriver driver,String option) throws InterruptedException
	{
		WebElement menu = driver.findElement(By.xpath("//button[@aria-haspopup='menu'or @title='More Action']"));
		Actions act = new Actions(driver);
		act.click(menu).perform();
		//Menu.click();
		Thread.sleep(2000);
		WebElement options = driver.findElement(By.xpath("//ul[@role='menu']//li//span[contains(text(),'"+option+"')]"));
		//MenuDelete.click();
		act.click(options).perform();
	}
	
}
