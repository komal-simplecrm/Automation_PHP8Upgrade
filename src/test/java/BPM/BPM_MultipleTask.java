package BPM;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Library_Files.Base_Class;
import Library_Files.UtilityClass;
import Login_Page.Dashboard;
import Login_Page.Login_Page;
import POM_BPM.BPM_Elements;
import POM_Cases_Module.Create_Case;
import POM_Cases_Module.Duplicate_Case;
import POM_Lead_Module.Add_Lead;
import POM_Lead_Module.Lead_ListView;
import POM_Opportunities_Module.Add_Opportunities;

public class BPM_MultipleTask extends Base_Class
{

	boolean Conditional_tasks;
	boolean Tasks;
	boolean NextButton;
	
	SoftAssert soft;
	Create_Case cases;
	Dashboard dashboard;
	Lead_ListView list_View;
	Add_Opportunities add_Opportunities;
	Add_Lead create_Lead;
	Duplicate_Case duplicate_case;
	BPM_Elements BPM;
	
	@BeforeMethod
	public void OpenBrowser() throws InterruptedException
	{
	
		//Create object of Create_Case Pom class
		cases = new Create_Case(driver);
		dashboard = new Dashboard(driver);
		list_View = new Lead_ListView(driver);
		add_Opportunities = new Add_Opportunities(driver);
		create_Lead = new Add_Lead(driver);
		duplicate_case = new Duplicate_Case(driver);
		BPM = new BPM_Elements(driver);
		soft = new SoftAssert();
		
		//directed to the dash board page
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Thread.sleep(2000);
		dashboard.clickOnMenuOption();
		dashboard.clickOnSearch("Cases");
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Thread.sleep(4000);
		dashboard.clickOnMenuOption(driver,"Cases");
		
	}
	
	@Test
	public void BPM_All_Functionality() throws EncryptedDocumentException, IOException, InterruptedException
	{
		//Create this test case in Extent Report
		test= extent.createTest("BPM All Functionality").assignAuthor("Komal")
					.assignCategory("Functional Test Case").assignDevice("Chrome with Edge");
		
		
		int sub = 1;
		for(int k=10;k<12;k++)//For only conditional tasks start k =1 end k<=6
		{					// For Tasks and Conditional tasks start k = 10 and end k<12
			try {	
					int i = 0;
					int j = 0;
					
					Thread.sleep(5000);
		
					//Create object of Create_Case POM class
					cases = new Create_Case(driver);
					dashboard=new Dashboard(driver);
					list_View =new Lead_ListView(driver);
					add_Opportunities= new Add_Opportunities(driver);
					create_Lead=new Add_Lead(driver);
					duplicate_case = new Duplicate_Case(driver);
					BPM = new BPM_Elements(driver);
		
					list_View.clickOnAddButton();
					Thread.sleep(2000);
					cases.selectPriority(driver, UtilityClass.fetchDataFromExcelSheet("Cases",1, 0));
					cases.selectState(driver, UtilityClass.fetchDataFromExcelSheet("Cases",1, 1), UtilityClass.fetchDataFromExcelSheet("Cases",1, 2),UtilityClass.fetchDataFromExcelSheet("Cases",1, 7));
					cases.selectType(driver, UtilityClass.fetchDataFromExcelSheet("Cases",3, 3));//for service request BPM no (1,3 ) and for complaint (3,3) 
					add_Opportunities.enterAccountName(driver,UtilityClass.fetchDataFromExcelSheet("Cases",sub, 4));
					cases.clickOnGeneralFeedbackforSubject();
					String subject=duplicate_case.getSubject();
					soft.assertNotNull(subject);
	
					cases.enterLinkedin_account(UtilityClass.fetchDataFromExcelSheet("Cases",1, 5));
				
					Thread.sleep(2000);
					create_Lead.AssignedTo(driver, UtilityClass.fetchDataFromExcelSheet("Cases",1, 8));
	
					create_Lead.clickOnSavebtn();
					Thread.sleep(5000);
					String casenumber = duplicate_case.getDetailViewText(driver,"Number");
					String status = duplicate_case.getDetailViewStatus();
	
					String detailviewsubject = duplicate_case.getDetailViewText(driver,"Subject");
					test.info(casenumber+ " , " +detailviewsubject+ " Case is created manually.");
					//status = New
					soft.assertEquals(status, "New");
	
					list_View.rightSideMenu(driver, "BPM Process");
		
					String BPM_Name = BPM.getBPM_Name();
					test.info("BPM is triggered: " +BPM_Name);
					
					BPM(i, k, j, casenumber, detailviewsubject);
					Thread.sleep(2000);
					driver.navigate().refresh();
					Thread.sleep(2000);
					add_Opportunities.backToListView();
					sub++;
			}catch(NullPointerException e) 
			{
				System.out.println("NullPointerException thrown!");
				
			}
		}
	}

	public void BPM(int i, int k, int j, String casenumber, String detailviewsubject) throws InterruptedException, EncryptedDocumentException, IOException
	{
		
		driver.navigate().refresh();
		Thread.sleep(2000);
		list_View.rightSideMenu(driver, "BPM Process");
		Thread.sleep(2000);
		String ribbon = BPM.getRibbonName(driver, i);
		
		String currentUrl = driver.getCurrentUrl();
		if(ribbon.equals("In Progress"))
		{
		
			String assignedTo = BPM.getAssignedToOnBPM(driver, i);
			//Assigned To user convert into LowerCase
			String assignLowerCase = assignedTo.toLowerCase();
			String currentUser = BPM.currentProfile(driver);
			//Current logged in user convert into LowerCase
			String currentLowerCase = currentUser.toLowerCase();
			test.info("Assigned To user: " +assignLowerCase+ "Current Logged in User:" +currentLowerCase);
			
			String[] Username = BPM.User(assignedTo);
			String UN = Username[0];
			System.out.println(UN);
			String Pass = Username[1];
			System.out.println(Pass);
			Thread.sleep(5000);
			int t = 0;
			
			//Assigned TO user convert into Lower case
			if(assignLowerCase.equalsIgnoreCase(currentLowerCase))
			{
				Thread.sleep(2000);
				//Check the Task is present
				Tasks = BPM.checkTask1(driver, i, t);
				
				if(Tasks == true)
				{
					//BPM = new BPM_Elements(driver);
					
					//Call function to complete the Task assigned
					//BPM.CompleteTask(driver, i,j, list_View);
					TaskAsUserAssignedTo(driver, i, t, j, casenumber, detailviewsubject);
					test.info("Task completed successfully.");
				}
				else if(Tasks == false)
				{
					System.out.println("Task is not present.");
					test.info("Task is not present.");
				}
			
				//Check the Conditional Task is present
				Conditional_tasks = BPM.CheckCondTask(driver, i);
				if(Conditional_tasks == true)
					
				{
					driver.navigate().refresh();
					list_View.rightSideMenu(driver, "BPM Process");
					//Call function to complete the Conditional Task assigned
					String cond1 = BPM.selectConditionalTask(driver,UtilityClass.fetchDataFromExcelSheet("BPM",k, j),i);//pass the conditional task of BPM flow
					test.info(cond1+ " Conditional task is selected.");
					System.out.println(cond1);
					
				}
				else if(Conditional_tasks == false)
				{
					System.out.println("Conditional Task is not present.");
					test.info("Conditional Task is not present.");
				}
				//Check Next Step Button
				NextButton = BPM.CheckNextStepBtn(driver, i);
				Thread.sleep(2000);
				if(NextButton == true)
				{
					BPM.clickOnNextStep(driver,i);
					test.info("Clicked on Next Step.");
					Thread.sleep(2000);
				}
				else if(NextButton == false)
				{
					test.info("Next Step button is not present.");
				}
			
			}
			//Assigned To user and Currently Logged in User is different
			else
			{
				
				LoginAnotherUser(UN, Pass, casenumber, detailviewsubject, currentUrl);
				test.info("Both the users are different so loggedIn in Edge browser with Task assigned To user");
				
				String assignedTo1 = BPM.getAssignedToOnBPM(Tempdriver, i);
				//Assigned To user convert into LowerCase
				String assignLowerCase1 = assignedTo1.toLowerCase();
				
				String currentUser1 = BPM.currentProfile(Tempdriver);
				//Current logged in user convert into LowerCase
				String currentLowerCase1 = currentUser1.toLowerCase();
				test.info("Assigned To user: " +assignLowerCase1+ "Current Logged in User:" +currentLowerCase1);
				if(assignLowerCase1.equalsIgnoreCase(currentLowerCase1))
				{
					//Check the Task is present
					Tasks = BPM.checkTask1(Tempdriver, i,t);
					
					if(Tasks == true)
					{
						BPM = new BPM_Elements(Tempdriver);
						
						//Call function to complete the Task assigned
						
						//BPM.CompleteTask(Tempdriver, i,j, list_View);
						TaskAsUserAssignedTo(Tempdriver, i, t, j, casenumber, detailviewsubject);
						test.info("Task completed successfully.");
					}
					else if(Tasks == false)
					{
						System.out.println("Task is not present.");
						test.info("Task is not present.");
					}
			
					//Check the Conditional Task is present
					Conditional_tasks = BPM.CheckCondTask(Tempdriver, i);
					if(Conditional_tasks == true)
					{
						Tempdriver.navigate().refresh();
						list_View.rightSideMenu(Tempdriver, "BPM Process");
						//Call function to complete the Conditional Task assigned
						String cond1 = BPM.selectConditionalTask(Tempdriver,UtilityClass.fetchDataFromExcelSheet("BPM",k, j),i);//pass the conditional task of BPM flow
						test.info(cond1+ " Conditional task is selected.");
						System.out.println(cond1);
						
					}
					else if(Conditional_tasks == false)
					{
						System.out.println("Conditional Task is not present.");
						test.info("Conditional Task is not present.");
					}
					//Check Next Step Button
					NextButton = BPM.CheckNextStepBtn(Tempdriver, i);
					Thread.sleep(2000);
					if(NextButton == true)
					{
						BPM.clickOnNextStep(Tempdriver,i);
						test.info("Clicked on Next Step.");
						Thread.sleep(2000);
					}
					else if(NextButton == false)
					{
						test.info("Next Step button is not present.");
					}
				
				}
				
				String assignedTo2 = BPM.getAssignedToOnBPM(Tempdriver, i+1);
				//Assigned To user convert into LowerCase
				String assignLowerCase2 = assignedTo2.toLowerCase();
				String currentUser2 = BPM.currentProfile(Tempdriver);
				//Current logged in user convert into LowerCase
				String currentLowerCase2 = currentUser2.toLowerCase();
				test.info("Assigned To user: " +assignedTo2+ "Current Logged in User:" +currentLowerCase2+ "On next panel");
				if(!assignLowerCase2.equals(currentLowerCase2))
				{
					test.info("For next panel assigned to and Current user different so closed the driver.");
					BPM.CloseBrowser(Tempdriver);
				}
				
			}
			soft.assertAll();
			if(Conditional_tasks == true)	
			{
					j++;	
			}
			
			i++;
			BPM(i, k, j, casenumber, detailviewsubject);
		}
		else if(ribbon.equals("Completed"))
		{
			
		}
		
	}
	
	
	
	//--------------------------Create Function make code readable and race complexity--------------------//
	
	
	//Task assigned to another user
	public void LoginAnotherUser( String UN, String Pass, String casenumber, String detailviewsubject, String currentUrl) throws InterruptedException, IOException
	{
		try{
			String title = Tempdriver.getTitle();
			System.out.println(title);
		}catch(Exception e)
		{
			
			initializeBrowserForOpprotunitiesToFeedbackFlow("Edge", UN, Pass);
			test.info("Login in Edge browser to complete the tasks using panel responsibility assigned To users credentials");
			System.out.print("Edge browser is closed");
		}
		
		
			BPM = new BPM_Elements(Tempdriver);
			dashboard = new Dashboard(Tempdriver) ;
			list_View =new Lead_ListView(Tempdriver);
			cases = new Create_Case(Tempdriver);
		
		
				//Diverted towards Cases module on Edge browser using assignedTO user profile
				Thread.sleep(5000);
				dashboard.clickOnMenuOptionDashboard(Tempdriver);
				dashboard.clickOnSearch("Cases");
				Tempdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(70));
				dashboard.clickOnMenuOption(Tempdriver,"Cases");


				Thread.sleep(5000);
				String currentuser2 = BPM.currentProfile(Tempdriver);
				String currentuser3 = currentuser2.toLowerCase();
				test.info(currentuser3+ " current user logged in.");
				System.out.println(currentuser3);
				
				dashboard.clickOnMenuOption();
				test.info("Filter the case using " +casenumber+ " and" +detailviewsubject+ ".");
				Thread.sleep(5000);
				list_View.clickSettingInListView(Tempdriver,"Filter",casenumber,detailviewsubject,"Case");
				cases.clickOnSubject(Tempdriver, casenumber, detailviewsubject);
				Thread.sleep(2000);	
			
				list_View.rightSideMenu(Tempdriver, "BPM Process");
	}
	
	
	//Check task assigned to Users and 
	public void TaskAsUserAssignedTo(WebDriver driver,int i, int t, int j, String casenumber, String detailviewsubject) throws InterruptedException, IOException	
	{
		String assignedTo = BPM.assignedUserInTask(driver, i, t);//j: Number of Tasks on panel number i, Starts from i=0 ad j=0;
		//Assigned To user convert into LowerCase
		String assignLowerCase = assignedTo.toLowerCase();
		String currentUser = BPM.currentProfile(driver);
		//Current logged in user convert into LowerCase
		String currentLowerCase = currentUser.toLowerCase();
		System.out.println("i,t,j: "+i+","+t+","+j);
		test.info("i,t,j: "+i+","+t+","+j);
		String[] Username = BPM.User(assignedTo);
		String UN = Username[0];
		System.out.println(UN);
		String Pass = Username[1];
		System.out.println(Pass);
		Thread.sleep(5000);
		String status = BPM.CheckTaskStatus(driver, i, t);
		//Check the Task is present
		System.out.println(status);
		if(status.equalsIgnoreCase("Not Started"))
		{
			String currentUrl = driver.getCurrentUrl();
			System.out.println(currentUrl);
			//Assigned TO user convert into Lower case
			if(assignLowerCase.equalsIgnoreCase(currentLowerCase))
			{
				BPM = new BPM_Elements(driver);
				//Call function to complete the Task assigned
				System.out.println("Start to complete task.");
				BPM.CompleteTask1(driver, i, t, j, list_View);
				System.out.println("End the task.");
			
			}
			else
			{
				//Open Firefox browser to complete the Task
				initializeBrowserForOpprotunitiesToFeedbackFlow("Firefox", UN, Pass);
				test.info("Login in firefox browser to complete the task using Task assigned To users credentials");
				Thread.sleep(5000);
				//Get UR of current case no.
				Firfoxdriver.get(currentUrl);
				BPM = new BPM_Elements(Firfoxdriver);
				
				list_View =new Lead_ListView(Firfoxdriver);
							
				//Call function to complete the Task assigned
				
				BPM.CompleteTask1(Firfoxdriver, i, t, j, list_View);
				
				
				
				
				
				String assignedTo2 = BPM.assignedUserInTask(Firfoxdriver, i, t+1);
				//Assigned To user convert into LowerCase
				String assignLowerCase2 = assignedTo2.toLowerCase();
				String currentUser2 = BPM.currentProfile(Firfoxdriver);
				//Current logged in user convert into LowerCase
				String currentLowerCase2 = currentUser2.toLowerCase();
				
				if(!assignLowerCase2.equals(currentLowerCase2))
				{
					BPM.CloseBrowser(Firfoxdriver);
					test.info("Assigned To user: " +assignedTo2+ "Current Logged in User:" +currentLowerCase2+ "On next panel so close the firefox browser");
				}
			}
	
			//j++;
			t++;
			TaskAsUserAssignedTo(driver, i, t, j,  casenumber, detailviewsubject);
		}
		else if(status.equalsIgnoreCase("Completed"))
		{
		
			System.out.println("Task Status is completed.");
			test.info("Task Status is completed.");
		}
	}	
	
	
	
	
	
	
	
	
	
	
}
