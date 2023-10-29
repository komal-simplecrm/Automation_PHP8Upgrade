package SalesFlow;


import java.time.Duration;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Library_Files.Base_Class;
import Library_Files.UtilityClass;
import Login_Page.Dashboard;
import POM_Calls_Module.Add_Calls;
import POM_Cases_Module.Create_Case;
import POM_Cases_Module.Duplicate_Case;
import POM_Lead_Module.Add_Lead;
import POM_Lead_Module.Lead_ListView;
import POM_Meeting_Module.Add_Meeting;
import POM_Opportunities_Module.Add_Opportunities;
import POM_Quote_Module.Add_Quotes;
import POM_Task_Module.Add_Task;

public class SupportFlowTestClass extends Base_Class 
{

	Create_Case cases;
	Dashboard dashboard;
	Lead_ListView list_View;
	Add_Opportunities add_Opportunities;
	Add_Lead create_Lead;
	Duplicate_Case duplicate_case;
	Add_Calls calls;
	Add_Task task;
	Add_Meeting meeting;
	Add_Quotes quotes;
	
	@BeforeMethod(alwaysRun = true)
	public void OpenBrowser() throws InterruptedException
	{
	
		//Create object of Create_Case Pom class
		cases = new Create_Case(driver);
		dashboard=new Dashboard(driver);
		list_View =new Lead_ListView(driver);
		add_Opportunities= new Add_Opportunities(driver);
		create_Lead=new Add_Lead(driver);
		duplicate_case = new Duplicate_Case(driver);
		calls = new Add_Calls(driver);
		task = new Add_Task(driver);
		meeting = new Add_Meeting(driver);
		quotes = new Add_Quotes(driver);
		
		//directed to the dash board page
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Thread.sleep(2000);
		dashboard.clickOnMenuOption();
		dashboard.clickOnSearch("Cases");
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Thread.sleep(2000);
		dashboard.clickOnMenuOption(driver,"Cases");
		Thread.sleep(2000);
		dashboard.closeMenuOption();
		Thread.sleep(4000);
	}
	
	
	//Create test case for create multiple Cases
	@Test( groups={"SupportFlow"})
	public void SupportFlow() throws Exception 
	{
		//Create this test case in Extent Report
		test= extent.createTest("Support Flow").assignAuthor("Komal")
					.assignCategory("Functional Test Case").assignDevice("Chrome");
		
		SoftAssert soft=new SoftAssert();
		Thread.sleep(5000);
		list_View.clickOnAddButton();
		cases.selectPriority(driver, UtilityClass.fetchDataFromExcelSheet("Cases",1, 0));
		cases.selectState(driver, UtilityClass.fetchDataFromExcelSheet("Cases",1, 1), UtilityClass.fetchDataFromExcelSheet("Cases",1, 2),UtilityClass.fetchDataFromExcelSheet("Cases",1, 7));
		cases.scrollUptoState(driver);
		cases.selectType(driver, UtilityClass.fetchDataFromExcelSheet("Cases",1, 3));
		add_Opportunities.enterAccountName(driver,UtilityClass.fetchDataFromExcelSheet("Cases",1, 4));
		cases.clickOnGeneralFeedbackforSubject();
		String subject=duplicate_case.getSubject();
		soft.assertNotNull(subject);
		test.info(subject+ " Case is created.");
		create_Lead.scrollpage(driver);
		Thread.sleep(2000);
		create_Lead.AssignedTo(driver, UtilityClass.fetchDataFromExcelSheet("Cases",1, 8));
		create_Lead.clickOnSavebtn();
		//String input1 = duplicate_case.getDetailViewCaseNumber();
		String input1 = duplicate_case.getDetailViewText(driver, "Number");
		test.info(subject+ " Case is created manually.");
		Thread.sleep(5000);
		
		//Diverted towards Calls module to schedule a call
		test.info("Driver diverted towards Calls module");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		dashboard.clickOnMenuOption();
		dashboard.clickOnSearch("Calls");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		dashboard.clickOnMenuOption(driver,"Calls");
		dashboard.closeMenuOption();
		list_View.scrolluptoAddBtn(driver);
		//Enter all details in Calls module
		test.info("Call is Schedule in progress.");
		list_View.clickOnAddButton();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		String subject1 = calls.enterSubject(UtilityClass.fetchDataFromExcelSheet("calls", 1, 0));
		soft.assertNotNull(subject1);
		test.info(subject+ " call is created");
		String status = calls.selectStatus(driver, UtilityClass.fetchDataFromExcelSheet("calls", 1, 1));
		soft.assertNotNull(status);
		String date = task.selectStartDate(driver, UtilityClass.fetchDataFromExcelSheet("calls", 1, 2), UtilityClass.fetchDataFromExcelSheet("calls", 1, 3));
		soft.assertNotNull(date);
		//create_Lead.scrollpage(driver);
		task.clickRelatedTo(driver, UtilityClass.fetchDataFromExcelSheet("calls", 1, 4), UtilityClass.fetchDataFromExcelSheet("calls", 1, 5));
		String duration = calls.enterDuration_Hours(UtilityClass.fetchDataFromExcelSheet("calls", 1, 6));
		soft.assertNotNull(duration);
		//calls.selectPopUpRadioBtn(driver, UtilityClass.fetchDataFromExcelSheet("calls", 1, 7));
		//Thread.sleep(3000);
		//calls.selectEmailRadioBtn(driver, UtilityClass.fetchDataFromExcelSheet("calls", 1, 7));
		//Thread.sleep(3000);
		create_Lead.enterDescription(UtilityClass.fetchDataFromExcelSheet("calls", 1, 8));
		create_Lead.scrollpage(driver);
		create_Lead.AssignedTo(driver, UtilityClass.fetchDataFromExcelSheet("calls", 1, 9));
		Thread.sleep(3000);
		//calls.scrollpage(driver);
		calls.clickOnAdd_Reminder();
		String a[]= {"Users","Contacts","Leads"};
		int k = 14;
		for(int j=0;j<=2;j++)
		{
		calls.addInviteesInRemainder(driver, 0, a[j], UtilityClass.fetchDataFromExcelSheet("calls", 1, k));
		k=k+2;	
		}
		calls.selectPopUpRadioBtnReminders(driver, UtilityClass.fetchDataFromExcelSheet("calls", 1, 10),0);
		
		calls.selectEmailRadioBtnReminders(driver, UtilityClass.fetchDataFromExcelSheet("calls", 1, 10),0);
		create_Lead.scrollpage(driver);
		calls.enterCaller_id(UtilityClass.fetchDataFromExcelSheet("calls", 1, 11));
		//calls.scrollpageUpToSaveBottomBtn(driver);
		create_Lead.clickOnSavebtn();
		Thread.sleep(2000);
		test.info("Call scheduled successfully");
		test.info("Driver diverted towards Meeting module");	
		//Diverted towards Calls module t schedule a call
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Thread.sleep(4000);
		dashboard.clickOnMenuOption();
		dashboard.clickOnSearch("Meetings");
		//Thread.sleep(4000);
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		dashboard.clickOnMenuOption(driver,"Meetings");
		Thread.sleep(2000);
		dashboard.closeMenuOption();
		Thread.sleep(2000);
		list_View.scrolluptoAddBtn(driver);
		//Enter all details in Calls module
		
		list_View.clickOnAddButton();
		test.info("Meeting is Schedule in progress");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		
		String subject2 = calls.enterSubject(UtilityClass.fetchDataFromExcelSheet("Meetings", 1, 0));
		System.out.println("subject: "+UtilityClass.fetchDataFromExcelSheet("Meetings", 1, 0));
		soft.assertNotNull(subject2);
		
		calls.selectStatus(driver, UtilityClass.fetchDataFromExcelSheet("Meetings", 1, 1));
		
		task.selectStartDate(driver, UtilityClass.fetchDataFromExcelSheet("Meetings", 1, 2), UtilityClass.fetchDataFromExcelSheet("Meetings",1, 3));
		
		task.clickRelatedTo(driver, UtilityClass.fetchDataFromExcelSheet("Meetings", 1, 4), UtilityClass.fetchDataFromExcelSheet("Meetings", 1, 5));
		
		meeting.selectDateEnd(driver, UtilityClass.fetchDataFromExcelSheet("Meetings", 1, 6), UtilityClass.fetchDataFromExcelSheet("Meetings", 1, 7));
		meeting.enterLocation(UtilityClass.fetchDataFromExcelSheet("Meetings", 1, 20));
		//calls.enterDuration_Hours(UtilityClass.fetchDataFromExcelSheet("Meetings", 1, 8));
		meeting.selectDuration(driver, UtilityClass.fetchDataFromExcelSheet("Meetings", 1, 8));
		calls.scrollpageuptoDuration(driver);
		calls.clickOnAdd_Reminder();
		String a1[]= {"Users","Contacts","Leads"};
		int L = 14;
		for(int j=0;j<=2;j++)
		{
		calls.addInviteesInRemainder(driver, 0, a1[j], UtilityClass.fetchDataFromExcelSheet("calls", 1, L));
		L=L+2;	
		}
		Thread.sleep(5000);
		
		calls.entertimebefore(driver, UtilityClass.fetchDataFromExcelSheet("Meetings",1, 11), 0);
		calls.scrollUpToAdd_Reminder(driver);
		create_Lead.enterDescription(UtilityClass.fetchDataFromExcelSheet("Meetings", 1, 9));
		create_Lead.scrollpage(driver);
		create_Lead.AssignedTo(driver, UtilityClass.fetchDataFromExcelSheet("Meetings", 1, 10));
		
		create_Lead.clickOnSavebtn();
		Thread.sleep(4000);
		test.info("Meeting scheduled successfully");
		test.info("Driver diverted towards Cases module again");	
		//Diverted towards Cases module again to assigned the case
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		dashboard.clickOnMenuOption();
		dashboard.clickOnSearch("Cases");
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Thread.sleep(4000);
		dashboard.clickOnMenuOption(driver,"Cases");
		Thread.sleep(4000);
		dashboard.closeMenuOption();
		Thread.sleep(4000);
		list_View.scrolluptoAddBtn(driver);
		test.info("Open the case to assignd to the specific user.");	
		
		//Edit the Case to Assigned To user Rahul Thakre
		//String input1 = duplicate_case.getDetailViewCaseNumber();
		 //Search case number with subject in filter and click on subject
		Thread.sleep(2000);
		 list_View.clickOnFilter(driver,input1,UtilityClass.fetchDataFromExcelSheet("Cases",1, 9),"Case");
		  cases.clickOnSubject(driver, input1, UtilityClass.fetchDataFromExcelSheet("Cases",1, 9));
		 Thread.sleep(5000);
		list_View.clickOnEditBtn();
		//Hard code the assigned user
		create_Lead.scrollpage(driver);
		create_Lead.AssignedTo(driver,"rahul thakre");
		create_Lead.clickOnSavebtn();
		
		test.info("Case is assigned to Rahul Thakre successfully");	
		test.info("Admin login with his user name and password on Edge browser.");	
		//Another User login - admin login
		Thread.sleep(5000);
		initializeBrowserForOpprotunitiesToFeedbackFlow("Edge","admin","simpleCRM@267");
		
		cases = new Create_Case(Tempdriver);
		list_View =new Lead_ListView(Tempdriver);
		create_Lead=new Add_Lead(Tempdriver);
		dashboard=new Dashboard(Tempdriver);
		
		//Diverted towards Cases module on Edge browser using Admin user
		Thread.sleep(5000);
		test.info("Driver diverted towards Cases module.");	
		/*dashboard.clickOnMenuOptionDashboard(Tempdriver);
		dashboard.clickOnSearch("Cases");
		Tempdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(70));
		dashboard.clickOnMenuOption(Tempdriver,"Cases");*/
			
		dashboard.clickOnMenuOption();
		dashboard.clickOnSearch("Cases");
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Thread.sleep(2000);
		dashboard.clickOnMenuOption(Tempdriver,"Cases");
		Thread.sleep(2000);
		dashboard.closeMenuOption();
		Thread.sleep(5000);
	 	
		
		test.info("Edit the State of the Case.");
		//dashboard.clickOnMenuOption();
		//dashboard.clickOnMenuOptionDashboard(Tempdriver);
		Thread.sleep(5000);
		list_View.clickOnFilter(Tempdriver,input1,UtilityClass.fetchDataFromExcelSheet("Cases",1, 9),"Case");
	  	cases.clickOnSubject(Tempdriver, input1, UtilityClass.fetchDataFromExcelSheet("Cases",1, 9));
		Thread.sleep(2000);	
		
							
				
				quotes.clickOnEditBtn(Tempdriver);
				cases.selectState(Tempdriver, UtilityClass.fetchDataFromExcelSheet("Cases",2, 1), UtilityClass.fetchDataFromExcelSheet("Cases",2, 2),UtilityClass.fetchDataFromExcelSheet("Cases",3, 7));
				create_Lead.scrollpage(Tempdriver);
				create_Lead.clickOnSavebtn();
				String msg = list_View.EveryPageAlert();
				test.info(msg+ " Message showing on CRM");
				Thread.sleep(10000);
				Tempdriver.close();
				test.info("Driver back to the Chrome browser.");	
				//driver switch to back to Chrome Browser
				driver.navigate().refresh();
				
				Thread.sleep(5000);
					
				String detailviewstate = duplicate_case.getDetailViewText(driver, "State");
				soft.assertEquals(detailviewstate, "Closed","Failed: State is not changed.");
				test.info(detailviewstate+ " State of the case updated successfully.");	
				String detailviewstatus = duplicate_case.getDetailViewStatus();
				soft.assertEquals(detailviewstatus, "CLOSED","Failed: Status is not changed.");
				test.info(detailviewstatus+ " Status of the case updated successfully.");	
				soft.assertAll();
	}
	
	
	
}
