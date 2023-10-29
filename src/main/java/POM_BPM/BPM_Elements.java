package POM_BPM;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.testng.asserts.SoftAssert;

import Library_Files.UtilityClass;
import POM_Lead_Module.Lead_ListView;



public class BPM_Elements 
{
	
	SoftAssert soft;
	//Data members should be declare globally with access level private by using @FindBy annotation
	//@FindBy(xpath="//div[@id='sla_step_name']") private WebElement StepName;	
	//@FindBy(xpath="//span[normalize-space(text())='Next Step']") private WebElement NextStep;
	@FindBy(xpath="//ul[contains(@class,'bpmRecordTitle')]/..//div[contains(@class,'MuiListItemText')]//span") private WebElement BPM_Name;
	//@FindBy(xpath="//h6[text()='BPM Process']/..//a[@id='assigned_user_id']")private WebElement AssignedToOnBPM;
	@FindBy(xpath="//h6[text()='BPM Process']/..//div[contains(@class,'MuiAccordion-rounded')]") private WebElement SubPanelCount;
	@FindBy(xpath="//button[@aria-controls='right-profile-menu']") private WebElement CurrentProfile;	
	//Detail view xpaths
	@FindBy(xpath="//div[@id='activities']") private WebElement DetailsViewActivities;	
	@FindBy(xpath="//div[@id='history']") private WebElement DetailsViewHistory;	
	@FindBy(xpath="//div[text()='Status']/..//p") private WebElement StatusOnHistory;	
	@FindBy(xpath="//div[text()='Subject']/..//a") private WebElement SubjectOnHistory;	
	@FindBy(xpath="//button[@aria-label='close']")private WebElement CloseBtnOnActivities;
	@FindBy(xpath="//span[text()='Yes']")private WebElement YesOnPopup;
	
	
	//Initialize the constructor with access level public using PageFactory class
	public BPM_Elements(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
		
	
	//Utilize within the methods with access level public 
	public String selectConditionalTask(WebDriver driver, String condition,int i) throws InterruptedException
	{
		WebElement StepName = driver.findElement(By.xpath("//div[@id='panel"+i+"bh-content']//input[@id='sla_step_name']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", StepName);
		Thread.sleep(2000);
		StepName.click();
		WebElement Condition = driver.findElement(By.xpath("//ul[@role='listbox']//li[contains(text(),'"+condition+"')]"));
		Condition.click();
		Thread.sleep(2000);
		String cond = StepName.getText();
		return cond;
	}
	
	public void hideBPMPanel(WebDriver driver, int i, String ribbon) throws InterruptedException
	{
		if(ribbon.equalsIgnoreCase("Completed"))
		{		
			driver.findElement(By.xpath("//div[@id='panel"+i+"bh-content']//span")).click();
		}
	
	}
	
	public boolean CheckNextStepBtn(WebDriver driver, int i) throws InterruptedException
	{
		try {
			driver.findElement(By.xpath("//div[@id='panel"+i+"bh-content']//span[text()='Next Step']")).isDisplayed();

			return true;
		}
		catch(Exception e)
		{
			
			System.out.println("Next button is not there.");
			return false;
		
		}
		
		//UtilityClass.wait_until_element_found(driver, NextStep);
		//((JavascriptExecutor)driver).executeScript("arguments[0].click()",NextStep);
	}
	
	public void clickOnNextStep(WebDriver driver, int i) throws InterruptedException
	{
		Actions act=new Actions(driver);
		//WebElement NextStep = driver.findElement(By.xpath("//div[@id='panel"+i+"bh-content']//span[normalize-space(text())='Next Step']"));
		WebElement NextStep = driver.findElement(By.xpath("//div[@id='panel"+i+"bh-content']//span[text()='Next Step']"));
		act.moveToElement(NextStep).perform();
    	act.click().perform();
		
		//UtilityClass.wait_until_element_found(driver, NextStep);
		//((JavascriptExecutor)driver).executeScript("arguments[0].click()",NextStep);
	}
	
	public String currentProfile(WebDriver driver)
	{
		
		//WebElement user = driver.findElement(By.xpath("//button[@aria-controls='right-profile-menu']"));
		WebElement user = driver.findElement(By.xpath("//div[contains(@class,'profileMenuIcon')]//parent::span//parent::button"));
		//String user = CurrentProfile.getAttribute("title");
		String user1 = user.getAttribute("title");
		System.out.println(user1);
		return user1;
	}
	
	public String getBPM_Name()
	{
		String BPM_name = BPM_Name.getText();
		return BPM_name;
	}
	
	/*public String getRibbonName(WebDriver driver, int i) throws InterruptedException//i=0,i=1
	{
		
		try {
		WebElement Ribbon = driver.findElement(By.xpath("//div[@id='panel"+i+"bh-content']//span[contains(@class,'ReactRibbons_leftCornerLargeRibbonText')]"));
		Thread.sleep(2000);
		String ribbon = Ribbon.getText();
		return ribbon;
		}
		catch(Exception e)
		{
			String ribbon = "Completed";
			System.out.println("Ribbon status is completed.");
			return ribbon;
		
		}
	}*/
	
	public String getChangeColorPanel(WebDriver driver, int i)
	{
		
		
		String color = null;
		System.out.println("i:" +i);
		try {
			
				//Find the xpath of element representing the panel
				WebElement panel = driver.findElement(By.id("panel"+i+"bh-content"));
				System.out.println("panel:" +panel);
					
				// Get the current background color
				String backgroundColor = panel.getCssValue("background-color");
				System.out.println("backgroundColor:" +backgroundColor);
        
				// Assert that the background color is green
				if (backgroundColor .equalsIgnoreCase("rgba(40, 167, 69, 1)")) 
				{
					System.out.println("Color changed to green");
					color = "Green";
					//return color;
			
				} else  if (backgroundColor.equalsIgnoreCase("rgba(255, 193, 7, 1)"))
				{
					System.out.println("Color did not change to green");
					color = "Yellow";
					//return color;
				}
			}
			catch(NoSuchElementException e) 
			{
				System.out.println("All steps are completed.");
				color = "Green";
				
			}
		return color;
		
		
		
	}
	public String getAssignedToOnBPM(WebDriver driver, int i) throws InterruptedException
	{
		try {
		//WebElement assignedto = driver.findElement(By.xpath("//h6[text()='BPM Process']/..//div[@id='panel"+i+"bh-content']//a[@id='assigned_user_id']"));
		WebElement assignedto = driver.findElement(By.xpath("//div[@id='panel"+i+"bh-content']//a[@id='assigned_user_id']"));

		Thread.sleep(2000);
		String assigned = assignedto.getText();
		return assigned;
		}
		catch(Exception e)
		{
			String assigned = "Komal Kolhe";
			System.out.println("User name is not there.");
			return assigned;
		
		}
	}
	public String getTitleOfTaskOnBPM(WebDriver driver, int i) throws InterruptedException
	{
		WebElement Title = driver.findElement(By.xpath("//h6[text()='BPM Process']/..//div[@id='panel"+i+"bh-content']"));
		Thread.sleep(2000);
		String title = Title.getText();
		return title;
	}
	
	
	public String[] User(String un)
	{ 
		String[] User1 = new String[2];
		if(un.equalsIgnoreCase("Akash Patil"))
		{ 
			
			 User1[0] = "Akash Patil";
			 User1[1] = "Akash@123";
			//return User1;
		}
		else if(un.equalsIgnoreCase("Komal Kolhe"))
		{ 
			
			 User1[0] = "Komal Kolhe";
			 User1[1] = "Komal@123";//Komal@kolhe8
			//return User1;
		}
		else if(un.equalsIgnoreCase("Rahul Thakre"))
		{ 
			
			 User1[0] = "Rahul Thakre";
			 User1[1] = "admin123";
			//return User1;
		}
		return User1;
	}
	
	public boolean CheckCondTask(WebDriver driver, int i)
	{
		try {
				//boolean task = driver.findElement(By.xpath("//div[@id='panel"+i+"bh-content']//span[contains(@class,'labelContainer')]")).isDisplayed();
				driver.findElement(By.xpath("//div[@id='panel"+i+"bh-content']//span[text()='CONDITIONAL TASK']")).isDisplayed();
				//System.out.println(task);
				return true;
			}catch(Exception e)
			{
				System.out.println("Element not present.");
				return false;
			}
	} 
	
	public String checkElementPresence(WebDriver driver, int i)
	{
		/*WebElement Panel_Count = driver.findElement(By.xpath("//div[@id='panel0bh-content']"));
		int count = Panel_Count.size();
		System.out.println(count);*/
		//int i=0;
		
			WebElement Panel = driver.findElement(By.xpath("//div[@id='panel"+i+"bh-content']"));
			String id1 = Panel.getAttribute("id");
			System.out.println(id1);
			return id1;
			//WebElement task = driver.findElement(By.xpath("//div[@id='panel"+i+"bh-content']//span[contains(@class,'labelContainer')]"));
			//String tasks = task.getText();
			
		
	}
	
	/*public void getSessionId(WebDriver driver)
	{
		SessionId s = ((RemoteWebDriver) driver).getSessionId();
		System.out.println(driver+ "Session in: " +s);
		
	}*/
	
	public void clickOnActivities(WebDriver driver)
	{
		//DetailsViewActivities.click();
		driver.findElement(By.xpath("//div[@id='activities']")).click();
	}
	
	public void clickOnHistory()
	{
		DetailsViewHistory.click();
	}
	
	public String getStatusInHistorySubPanel()
	{
		String status = StatusOnHistory.getText();
		System.out.println("Status In History Sub Panel:"+status);
		return status;
	}
	
	public String getSubjectOnHistorySubPanel()
	{
		String status = SubjectOnHistory.getText();
		return status;
	}
	/*public String getAssignedToTasks(WebDriver driver, int i, int j)
	{
		WebElement Task = driver.findElement(By.xpath("//div[@id='panel"+i+"bh-content']//div[@id='task-"+i+"-"+j+"']//h6[@id='responsibility']"));
		String TaskAssignUser = Task.getText();
		return TaskAssignUser;
	}*/
	
	public void clickOnCloseBtnOnActivities(WebDriver driver)
	{
		//driver.findElement(By.xpath("//tr[@id='MUIDataTableBodyRow-SubpanelListViewTable-"+j+"']//button[@aria-label='close']")).click();
		CloseBtnOnActivities.click();
		YesOnPopup.click();
	}
	
	public boolean checkTask(WebDriver driver, int i)
	{
		
		try {
			driver.findElement(By.xpath("//div[@id='panel"+i+"bh-content']//div[@id='task-"+i+"-0']//a")).isDisplayed();
			//driver.findElement(By.xpath("//span[contains(@class,'MuiStepLabel-vertical')]/../..//h6[@id='responsibility']")).isDisplayed();
			return true;
		}catch(Exception e)
		{
			System.out.println("Task not present.");
			return false;
		}
	}
	
	public String assignedUserInTask(WebDriver driver, int i, int t)
	{
		String assigned = null;
		try {
			WebElement assignedto = driver.findElement(By.xpath("//div[@id='panel"+i+"bh-content']//div[@id='task-"+i+"-"+t+"']//h6[@id='responsibility']"));
			Thread.sleep(2000);
			 assigned = assignedto.getText();
			 if(assigned.equals(""))
			{
				 assigned = assignedto.getAttribute("innerText");
				 if(assigned.equals(""))
					{
					 assigned = assignedto.getAttribute("textContent");
					}
			}
			return assigned;
			}
			catch(Exception e)
			{
				assigned = "Komal Kolhe";
				System.out.println("User is not matched.");
				return assigned;
			
			}
	}
	
	public String CheckTaskStatus(WebDriver driver, int i, int t)
	{	String TaskStatus = null;
		
		try {
			
			WebElement Status = driver.findElement(By.xpath("//div[@id='panel"+i+"bh-content']//div[@id='task-"+i+"-"+t+"']//h6[@id='panel_completed']"));
			
			Thread.sleep(2000);
			TaskStatus = Status.getText();
			if(TaskStatus.equals(""))
			{
				TaskStatus =  Status.getAttribute("innerText");
				if(TaskStatus.equals(""))
				{
					TaskStatus =  Status.getAttribute("textContent");
				}
			}
			
			
			System.out.println("TaskStatus: " + TaskStatus);
			return TaskStatus;
			}
			catch(Exception e)
			{
				TaskStatus = "Completed";
				System.out.println("Task status is completed.");
				return TaskStatus;
			
			}
	
	}
	
	//Check the Task in the BPM and Complete the Task
	public void CompleteTask(WebDriver driver, int i,int j, Lead_ListView list_View) throws InterruptedException
	{
			try {
				System.out.println("driver name:" +driver);
			Thread.sleep(2000);
			//list_View = new Lead_ListView(driver);
			//driver.switchTo().defaultContent();
			//driver.navigate().refresh();
			//list_View.rightSideMenu(driver, "BPM Process");
			Thread.sleep(4000);
			clickOnActivities(driver);
			
			//list_View.rightSideMenu(driver, "BPM Process");
			clickOnCloseBtnOnActivities(driver);
			
			//UtilityClass.CheckPageLoaded();
			Thread.sleep(5000);
			clickOnHistory();
			
			Thread.sleep(3000);
			String status = getStatusInHistorySubPanel();
			
			//System.out.println(status);
			soft.assertEquals(status, "Completed");
			soft.assertAll();
			System.out.println("Before Refresh");
			driver.navigate().refresh();
			System.out.println("After Refresh");
			Thread.sleep(2000);
			//driver.findElement(By.xpath("//button[@title='"+elementName+"']")).click();
			list_View.rightSideMenu(driver, "BPM Process");
			}
			catch(NullPointerException e)
			{
				System.out.println("NullPointerException thrown!");
				driver.navigate().refresh();
				Thread.sleep(2000);
				list_View.rightSideMenu(driver, "BPM Process");
				//driver.findElement(By.xpath("//button[@title='"+elementName+"']")).click();
			}
		
		
	}
	
	//User are different Close the Edge Browser
		public void CloseBrowser(WebDriver Tempdriver)
		{
			
			try {
					Tempdriver.close();
				}
				catch(NoSuchSessionException e)
				{
					System.out.println("NoSuchSessionException thrown!");
				}
			
		}
		
		public void clickOnCloseBtnOnActivities1(WebDriver driver, int t)
		{
			driver.findElement(By.xpath("//tr[@id='MUIDataTableBodyRow-SubpanelListViewTable-0']//button[@aria-label='close']")).click();
			//CloseBtnOnActivities.click();
			driver.findElement(By.xpath("//span[text()='Yes']")).click();
		
			//YesOnPopup.click();
		}
		
		public boolean checkTask1(WebDriver driver, int i, int t)
		{
			
			try {
				driver.findElement(By.xpath("//div[@id='panel"+i+"bh-content']//div[@id='task-"+i+"-"+t+"']//a")).isDisplayed();
				//driver.findElement(By.xpath("//span[contains(@class,'MuiStepLabel-vertical')]/../..//h6[@id='responsibility']")).isDisplayed();
				return true;
			}catch(Exception e)
			{
				System.out.println("Element not present.");
				return false;
			}
		}
		//Check the Task in the BPM and Complete the Task
		public void CompleteTask1(WebDriver driver, int i, int t,int j, Lead_ListView list_View) throws InterruptedException
		{
				try {
					System.out.println("driver name:" +driver);
				Thread.sleep(2000);
				//list_View = new Lead_ListView(driver);
				//driver.switchTo().defaultContent();
				driver.navigate().refresh();
				//list_View.rightSideMenu(driver, "BPM Process");
				Thread.sleep(2000);
				clickOnActivities(driver);
				//list_View.rightSideMenu(driver, "BPM Process");
				
				clickOnCloseBtnOnActivities1(driver, t);
				System.out.println("clickOnCloseBtnOnActivities1");
				Thread.sleep(5000);
				clickOnHistory();
				System.out.println("clickOnHistory");
				Thread.sleep(2000);
				String status = getStatusInHistorySubPanel();
				System.out.println("getStatusInHistorySubPanel");
				//System.out.println(status);
				soft.assertEquals(status, "Completed");
				soft.assertAll();
				System.out.println("Before refresh");
				driver.navigate().refresh();
				System.out.println("After refresh");
				Thread.sleep(2000);
				//driver.findElement(By.xpath("//button[@title='"+elementName+"']")).click();
				list_View.rightSideMenu(driver, "BPM Process");
				}
				catch(NullPointerException e)
				{
					System.out.println("NullPointerException thrown!");
					driver.navigate().refresh();
					Thread.sleep(2000);
					list_View.rightSideMenu(driver, "BPM Process");
					//driver.findElement(By.xpath("//button[@title='"+elementName+"']")).click();
				}
			
			
		}
		
}
