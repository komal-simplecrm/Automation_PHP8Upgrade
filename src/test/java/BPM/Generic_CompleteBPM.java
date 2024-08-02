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
import POM_BPM.BPM_Elements;
import POM_Cases_Module.Create_Case;
import POM_Cases_Module.Duplicate_Case;
import POM_Lead_Module.Add_Lead;
import POM_Lead_Module.Lead_ListView;
import POM_Opportunities_Module.Add_Opportunities;

public class Generic_CompleteBPM extends Base_Class
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
		dashboard=new Dashboard(driver);
		list_View =new Lead_ListView(driver);
		add_Opportunities= new Add_Opportunities(driver);
		create_Lead=new Add_Lead(driver);
		duplicate_case = new Duplicate_Case(driver);
		BPM = new BPM_Elements(driver);
		soft = new SoftAssert();
		
		//directed to the dash board page
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Thread.sleep(2000);
		dashboard.clickOnMenuOption();
		dashboard.clickOnSearch("Cases");
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		//Thread.sleep(4000);
		dashboard.clickOnMenuOption(driver,"Cases");
		Thread.sleep(2000);
		dashboard.closeMenuOption();
		Thread.sleep(2000);
		
	}
	
	@Test
	public void BPM_All_Functionality() throws EncryptedDocumentException, IOException, InterruptedException
	{
		//Create this test case in Extent Report
		test= extent.createTest("BPM All Functionality").assignAuthor("Komal")
					.assignCategory("Functional Test Case").assignDevice("Chrome with Edge");
		
		
		int sub = 1;
		for(int k=1;k<6;k++)//k=10;k<12- Complaint BPM//k=1;k<6- Service request
		{
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
					cases.selectType(driver, UtilityClass.fetchDataFromExcelSheet("Cases",1, 3));
					add_Opportunities.enterAccountName(driver,UtilityClass.fetchDataFromExcelSheet("Cases",sub, 4));
					cases.clickOnGeneralFeedbackforSubject(driver,UtilityClass.fetchDataFromExcelSheet("Cases",1, 9));
					String subject = duplicate_case.getSubject();
					soft.assertNotNull(subject);
					Thread.sleep(2000);
					create_Lead.scrollpage(driver);
					//cases.enterLinkedin_account(UtilityClass.fetchDataFromExcelSheet("Cases",1, 5));
				
					Thread.sleep(2000);
					create_Lead.AssignedTo(driver, UtilityClass.fetchDataFromExcelSheet("Cases",1, 8));
	
					create_Lead.clickOnSavebtn();
					Thread.sleep(5000);
					String casenumber = duplicate_case.getDetailViewText(driver,"Number");
					String status = duplicate_case.getDetailViewStatus();
					
					String detailviewsubject = duplicate_case.getDetailViewText(driver,"Subject");
					test.info(casenumber+ " , " +detailviewsubject+ " Case is created manually.");
					//status = New
					//soft.assertEquals(status, "new");
					soft.assertEquals("new",status.toLowerCase());

					list_View.rightSideMenu(driver, "BPM Process");
					test.info("Clicked on BPM icon on right side bar.");
					
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
		//String ribbon = BPM.getRibbonName(driver, i);
		String Color = BPM.getChangeColorPanel(driver, i);
		System.out.println("Color:"+Color);
		if(Color.equals("Yellow"))
		{
		
			String assignedTo = BPM.getAssignedToOnBPM(driver, i);
			System.out.println("assignedTo:"+assignedTo);
			//Assigned To user convert into LowerCase
			String assignLowerCase = assignedTo.toLowerCase();
			String currentUser = BPM.currentProfile(driver);
			//Current logged in user convert into LowerCase
			String currentLowerCase = currentUser.toLowerCase();
			
			String[] Username = BPM.User(assignedTo);
			String UN = Username[0];
			System.out.println(UN);
			String Pass = Username[1];
			System.out.println(Pass);
			Thread.sleep(5000);
			
			
			//Assigned TO user convert into Lower case
			if(assignLowerCase.equalsIgnoreCase(currentLowerCase))
			{
				//Check the Task is present
				Tasks = BPM.checkTask(driver, i);
				if(Tasks == true)
				{
					BPM = new BPM_Elements(driver);
					
					//Call function to complete the Task assigned
					BPM.CompleteTask(driver, i,j, list_View);
				
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
					Thread.sleep(2000);
				}
				else if(NextButton == false)
				{
					System.out.println("Next Step button is not present.");
				}
			
			}
			//Assigned To user and Currently Logged in User is different
			else
			{
				LoginAnotherUser(UN, Pass, casenumber, detailviewsubject);
				
				
				
				String assignedTo1 = BPM.getAssignedToOnBPM(Tempdriver, i);
				//Assigned To user convert into LowerCase
				String assignLowerCase1 = assignedTo1.toLowerCase();
				test.info("Get Assigned to: "+assignLowerCase1);
				String currentUser1 = BPM.currentProfile(Tempdriver);
				//Current logged in user convert into LowerCase
				String currentLowerCase1 = currentUser1.toLowerCase();
				test.info("Get current to: "+currentLowerCase1);
				if(assignLowerCase1.equalsIgnoreCase(currentLowerCase1))
				{
					//Check the Task is present
					Tasks = BPM.checkTask(Tempdriver, i);
					test.info("Check task is there.");
					if(Tasks == true)
					{
						BPM = new BPM_Elements(Tempdriver);
						
						//Call function to complete the Task assigned
						test.info("Start to complete task.");
						BPM.CompleteTask(Tempdriver, i,j, list_View);
						test.info("End tha task.");
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
						Thread.sleep(2000);
					}
					else if(NextButton == false)
					{
						System.out.println("Next Step button is not present.");
					}
				
				}
				
				String assignedTo2 = BPM.getAssignedToOnBPM(Tempdriver, i+1);
				//Assigned To user convert into LowerCase
				String assignLowerCase2 = assignedTo2.toLowerCase();
				String currentUser2 = BPM.currentProfile(Tempdriver);
				//Current logged in user convert into LowerCase
				String currentLowerCase2 = currentUser2.toLowerCase();
				
				if(!assignLowerCase2.equals(currentLowerCase2))
				{
					test.info("Assigned to and Current user different so closed the driver.");
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
		else if(Color.equals("Green"))
		{
			
		}
		
	}
	
	
	
	//--------------------------Create Function make code readable and race complexity--------------------//
	
	
	//Task assigned to another user
	public void LoginAnotherUser( String UN, String Pass, String casenumber, String detailviewsubject) throws InterruptedException, IOException
	{
		try{
			String title = Tempdriver.getTitle();
			System.out.println(title);
		}catch(Exception e)
		{
			initializeBrowserForOpprotunitiesToFeedbackFlow("Edge", UN, Pass);
			System.out.print("Edge browser is closed");
		}
		
		
			BPM = new BPM_Elements(Tempdriver);
			dashboard = new Dashboard(Tempdriver) ;
			list_View =new Lead_ListView(Tempdriver);
			cases = new Create_Case(Tempdriver);
		
		
				//Diverted towards Cases module on Edge browser using assignedTO user profile
				Thread.sleep(5000);
				test.info("Diverted towards Cases module on Edge browser.");
				//dashboard.clickOnMenuOptionDashboard(Tempdriver);
				dashboard.clickOnMenuOption();
				dashboard.clickOnSearch("Cases");
				Tempdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(70));
				dashboard.clickOnMenuOption(Tempdriver,"Cases");
				Thread.sleep(2000);
				dashboard.closeMenuOption();


				Thread.sleep(5000);
				String currentuser2 = BPM.currentProfile(Tempdriver);
				String currentuser3 = currentuser2.toLowerCase();
				test.info(currentuser3+ " current user logged in.");
				System.out.println(currentuser3);
				
				//dashboard.clickOnMenuOption();
				test.info("Filter the case using " +casenumber+ " and" +detailviewsubject+ ".");
				Thread.sleep(5000);
				
				list_View.clickOnFilter(Tempdriver, casenumber,detailviewsubject, "Case");
			  	cases.clickOnSubject(Tempdriver, casenumber,detailviewsubject);
				//list_View.clickSettingInListView(Tempdriver,"Filter",casenumber,detailviewsubject,"Case");
				//cases.clickOnSubject(Tempdriver, casenumber, detailviewsubject);
				Thread.sleep(2000);	
			
				list_View.rightSideMenu(Tempdriver, "BPM Process");
	}
	
	
	//Check task assigned to Users and 
	public void TaskAsUserAssignedTo(WebDriver driver,int i, int j, String casenumber, String detailviewsubject) throws InterruptedException, IOException	
	{
		String assignedTo = BPM.assignedUserInTask(driver, i, j);//j: Number of Tasks on panel number i, Starts from i=0 ad j=0;
		//Assigned To user convert into LowerCase
		String assignLowerCase = assignedTo.toLowerCase();
		String currentUser = BPM.currentProfile(driver);
		//Current logged in user convert into LowerCase
		String currentLowerCase = currentUser.toLowerCase();
		
		String[] Username = BPM.User(assignedTo);
		String UN = Username[0];
		System.out.println(UN);
		String Pass = Username[1];
		System.out.println(Pass);
		Thread.sleep(5000);
		String status = BPM.CheckTaskStatus(driver, i, j);
		//Check the Task is present
		
		if(status.equalsIgnoreCase("Not Started"))
		{
			//Assigned TO user convert into Lower case
			if(assignLowerCase.equalsIgnoreCase(currentLowerCase))
			{
			
				//Call function to complete the Task assigned
				BPM.CompleteTask(driver, i,j, list_View);
			
			}
			else
			{
				LoginAnotherUser(UN, Pass, casenumber, detailviewsubject);
			
			
			
				String assignedTo1 = BPM.assignedUserInTask(Tempdriver, i, j);
				//Assigned To user convert into LowerCase
				String assignLowerCase1 = assignedTo1.toLowerCase();
				test.info("Get Assigned to: "+assignLowerCase1);
				String currentUser1 = BPM.currentProfile(Tempdriver);
					//Current logged in user convert into LowerCase
				String currentLowerCase1 = currentUser1.toLowerCase();
				test.info("Get current to: "+currentLowerCase1);
			
			
			
				if(assignLowerCase1.equalsIgnoreCase(currentLowerCase1))
				{
									
					//Call function to complete the Task assigned
					test.info("Start to complete task.");
					BPM.CompleteTask(Tempdriver, i,j, list_View);
					test.info("End tha task.");
				
				
				}
			}
	
			j++;
			TaskAsUserAssignedTo(driver, i,  j,  casenumber, detailviewsubject);
		}
		else if(status.equalsIgnoreCase("Completed"))
		{
		
			System.out.println("Task Ststus is completed.");
		}
	}	
}
