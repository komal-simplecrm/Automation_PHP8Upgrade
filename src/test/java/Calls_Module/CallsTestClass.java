package Calls_Module;

import java.awt.AWTException;
import java.io.IOException;
import java.text.ParseException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Library_Files.Base_Class;
import Library_Files.CommonFunctions;
import Library_Files.UtilityClass;
import Login_Page.Dashboard;
import POM_Account_Module.Add_Account;
import POM_Calls_Module.Add_Calls;
import POM_Calls_Module.Duplicate_Call_Page;

import POM_Lead_Module.Add_Lead;
import POM_Lead_Module.Duplicate_Page;
import POM_Lead_Module.Lead_ListView;
import POM_Meeting_Module.Add_Meeting;
import POM_Opportunities_Module.Add_Opportunities;
import POM_Task_Module.Add_Task;
import POM_Task_Module.Duplicate_Task_Page;

public class CallsTestClass extends Base_Class
{
	Dashboard dashboard;
	Add_Lead create_Lead;
	Lead_ListView list_View;
	Add_Calls calls;
	Add_Task task;
	Add_Opportunities add_Opportunities;
	Duplicate_Call_Page duplicate_call;
	Duplicate_Task_Page duplicate_task;
	Duplicate_Page duplicate;
	Add_Account add_Account;
	Add_Meeting meeting;
	
	@BeforeMethod(alwaysRun = true)
	public void OpenBrowser() throws InterruptedException
	{
		dashboard = new Dashboard(driver);
		create_Lead = new Add_Lead(driver);
		list_View = new Lead_ListView(driver);
		calls = new Add_Calls(driver);
		task = new Add_Task(driver);
		add_Opportunities = new Add_Opportunities(driver);
		duplicate_call = new Duplicate_Call_Page(driver);
		duplicate_task = new Duplicate_Task_Page(driver);
		duplicate=new Duplicate_Page(driver);
		add_Account = new Add_Account(driver);
		meeting = new Add_Meeting(driver);
		
		//directed to the dash board page
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Thread.sleep(2000);
		dashboard.clickOnMenuOption();
		dashboard.clickOnSearch("Calls");
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Thread.sleep(2000);
		dashboard.clickOnMenuOption(driver,"Calls");
		Thread.sleep(2000);
		dashboard.closeMenuOption();
		Thread.sleep(4000);
	}
	
		//Create test case for create multiple Account
		@Test( groups={"Create", "Sanity"})
		public void CreateCalls() throws Exception 
		{
			//Create this test case in Extent Report
			test= extent.createTest("Create Calls").assignAuthor("Komal")
							.assignCategory("Functional Test Case").assignDevice("Chrome");
					
			test.info("Create multiple Calls");
					
			for(int i=1;i<=7;i++)//10// 7- loop run u to Lead option in Relate field
			{
				Thread.sleep(5000);
				list_View.clickOnAddButton();
				
				SoftAssert soft=new SoftAssert();
				try {
							
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
					String subject = calls.enterSubject(UtilityClass.fetchDataFromExcelSheet("calls", i, 0));
					soft.assertNotNull(subject);
					test.info(subject+ " call is created");
					create_Lead.enterDescription(UtilityClass.fetchDataFromExcelSheet("calls", i, 8));
					String status = calls.selectStatus(driver, UtilityClass.fetchDataFromExcelSheet("calls", i, 1));
					soft.assertNotNull(status);
					String date = task.selectStartDate(driver, UtilityClass.fetchDataFromExcelSheet("calls", i, 2), UtilityClass.fetchDataFromExcelSheet("calls", i, 3));
					soft.assertNotNull(date);
					meeting.selectDateEnd(driver, UtilityClass.fetchDataFromExcelSheet("calls", i, 20), UtilityClass.fetchDataFromExcelSheet("calls", i, 21));
					
					task.clickRelatedTo(driver, UtilityClass.fetchDataFromExcelSheet("calls", i, 4), UtilityClass.fetchDataFromExcelSheet("calls", i, 5));
					calls.enterDuration(driver, UtilityClass.fetchDataFromExcelSheet("calls", i, 6));
					
					//calls.selectPopUpRadioBtn(driver, UtilityClass.fetchDataFromExcelSheet("calls", i, 7));
					//Thread.sleep(3000);
					//calls.selectEmailRadioBtn(driver, UtilityClass.fetchDataFromExcelSheet("calls", i, 7));
					//Thread.sleep(3000);
					create_Lead.scrollpage(driver);				
					create_Lead.AssignedTo(driver, UtilityClass.fetchDataFromExcelSheet("calls", i, 9));
					Thread.sleep(3000);
					//calls.scrollpage(driver);
					//calls.clickOnAdd_Reminder();
					String a[]= {"Users","Contacts","Leads"};
					int k = 14;
					for(int j=0;j<=2;j++)
					{
					calls.addInviteesInRemainder(driver, 0, a[j], UtilityClass.fetchDataFromExcelSheet("calls", i, k));
					k=k+2;	
					}
					calls.entertimebefore(driver, UtilityClass.fetchDataFromExcelSheet("calls",i, 10), 0);
					create_Lead.scrollpage(driver);
					//calls.enterCaller_id(UtilityClass.fetchDataFromExcelSheet("calls", i, 11));
					//calls.scrollpageUpToSaveBottomBtn(driver);
					create_Lead.clickOnSavebtn();
					list_View.getAlertMessage(subject);
					Thread.sleep(7000);
					add_Opportunities.backToListView();
					Thread.sleep(4000);
					list_View.scrolluptoAddBtn(driver);
					
					}
				catch(NullPointerException e) 
				{
			 			System.out.println("NullPointerException thrown!");
					
				}
			}
		}
		
		//Create test case for Edit Account
		@Test( groups={"Edit", "Sanity"})
		public void EditCall() throws Exception 
		{
				//Create this test case in Extent Report
				test= extent.createTest("Edit Call").assignAuthor("Komal")
										.assignCategory("Functional Test Case").assignDevice("Chrome");
								
				test.info("User should Edit Call Status");
				String input1="Test call generate2";
				list_View.enterTextInSearchBtn(driver,input1);
				Thread.sleep(5000);
								 	
				String OppName=add_Opportunities.verifyOpportunityName(driver, input1);
				if(OppName.equals(input1))
				{
					task.clickOnSubjectInListView(driver, input1);
					Thread.sleep(5000);
					list_View.clickOnEditBtn();
					calls.enterSubject(UtilityClass.fetchDataFromExcelSheet("calls", 3, 0));
					calls.selectStatus(driver, UtilityClass.fetchDataFromExcelSheet("calls", 3, 1));
					task.selectStartDate(driver, UtilityClass.fetchDataFromExcelSheet("calls", 3, 2), UtilityClass.fetchDataFromExcelSheet("calls", 3, 3));
					calls.enterDuration(driver, UtilityClass.fetchDataFromExcelSheet("calls", 3, 6));
					create_Lead.scrollpage(driver);
					create_Lead.clickOnSavebtn();
					String msg=list_View.EveryPageAlert();
					 test.info(msg+ " Message showing on CRM");
					 test.info(input1+ " record is updated");
				}
				else
				{
					System.out.println("Subject not matched");
					test.info(input1+ " call subject is not matched");
				}
		 
		}
		
		//Create test case for Delete Call From Edit Option
		@Test( groups = {"DeleteFromEditView", "Sanity"}, dependsOnMethods={"CreateCalls"})
		public void DeleteCallFromEditOption() throws Exception 
		{
			CommonFunctions.DeleteRecordFromEditOption(dashboard, list_View, add_Opportunities,"Test call generate3", "Calls");
			
		}
		
		
		//Create test case for Delete Task From List View
		@Test( groups = {"DeleteFromListView", "Sanity"}, dependsOnMethods={"CreateCalls"})
		public void DeleteCallFromListView() throws InterruptedException, EncryptedDocumentException, IOException 
		{
			CommonFunctions.DeleteRecordFromListView(list_View, add_Opportunities, "Test Call1", "Calls");
				
		}
			
		//Create the test case to add multiple Remainders with one User, Contact and Lead
		@Test( groups = {"AddMultipleRemainders", "Sanity"})
		public void AddMultipleRemainders() throws Exception
		{//Create this test case in Extent Report
			test= extent.createTest("Add Multiple Remainders In Call").assignAuthor("Komal")
						.assignCategory("Functional Test Case").assignDevice("Chrome");
				
			test.info("Add multiple Remainders with one User, Contact and Lead");
			Thread.sleep(5000);
			list_View.clickOnAddButton();
				
				
							
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
					calls.enterSubject(UtilityClass.fetchDataFromExcelSheet("calls", 1, 0));
					calls.selectStatus(driver, UtilityClass.fetchDataFromExcelSheet("calls", 1, 1));
					task.selectStartDate(driver, UtilityClass.fetchDataFromExcelSheet("calls", 1, 2), UtilityClass.fetchDataFromExcelSheet("calls", 1, 3));
					calls.enterDuration(driver, UtilityClass.fetchDataFromExcelSheet("calls", 1, 6));
					calls.scrolluptoAssignedTo(driver);
					//Add Remainders function call from Common Functions class
					CommonFunctions.Add_Remainder(calls, UtilityClass.fetchDataFromExcelSheet("calls", 1, 10), 14);
					create_Lead.scrollpage(driver);
					create_Lead.clickOnSavebtn();
		}
			
		//Create the test case to Verify Call Is Duplicate?
		@Test( groups={"Duplicate"})
		public void DuplicateCalls() throws InterruptedException, EncryptedDocumentException, IOException, AWTException
		{	
			//Create this test case in Extent Report
			test= extent.createTest("Verify Call Is Duplicate?").assignAuthor("Komal")
						.assignCategory("Functional Test Case").assignDevice("Chrome");
				
			test.info("Verify call is duplicate or not");
			SoftAssert soft=new SoftAssert();
				/*System.out.println("Enter LeadName: ");
				Scanner scan1 = new Scanner(System.in);
				String input1 = scan1.nextLine();*/
				String input1="Test call generate1";
				list_View.enterTextInSearchBtn(driver,input1);
				Thread.sleep(2000);
				list_View.clickOnName(driver,input1);
				
				
				Thread.sleep(5000);
				list_View.menu(driver,"DUPLICATE");
				
				//get Start Date 
				String[] date = duplicate_task.getDateStart(); 
				String StartDate = date[0];
				//Split date by -
				String[] dateex=StartDate.split("-");
				String month=dateex[1];
				//convert date string to int
				int m=Integer.parseInt(month);  
				//get month name format MMM
				String MonthName=UtilityClass.convertMonth(m);
				//combine date dd-MMM-yyyy
				String ExactstartDate=dateex[0]+"-"+MonthName+"-"+dateex[2];
				
				//get Start Time
				String StartTime = date[1];
				//time split from index 5 
				String time=StartTime.substring(0,5);
				String AMPM=StartTime.substring(5);
				String ampm = AMPM.toUpperCase();
				//Add space in between time and AM PM
				String starttime = time+ " " +ampm;
				int j=0,k=0;
				String related = duplicate_task.getRelatedTo();
				String arr1[]= {duplicate_call.getSubject(),duplicate_call.getStatus(),ExactstartDate,starttime,
						related,duplicate_task.getRelatedToDynamic(related),duplicate_call.getDuration1(), duplicate.getDescription(),
						duplicate_call.getPopUpRemainder(),duplicate_call.getEmailRemainder()/*,duplicate_call.getCaller_id()*/};
				String arr2[]= {UtilityClass.fetchDataFromExcelSheet("Calls",2, 0),UtilityClass.fetchDataFromExcelSheet("Calls",2, 1),UtilityClass.fetchDataFromExcelSheet("Calls",2, 2),
						UtilityClass.fetchDataFromExcelSheet("Calls",2, 3),UtilityClass.fetchDataFromExcelSheet("Calls",2, 4),UtilityClass.fetchDataFromExcelSheet("Calls",2, 5),
						UtilityClass.fetchDataFromExcelSheet("Calls",2, 6),UtilityClass.fetchDataFromExcelSheet("Calls",2, 8),UtilityClass.fetchDataFromExcelSheet("Calls",2, 7),
						UtilityClass.fetchDataFromExcelSheet("Calls",2, 7)/*,UtilityClass.fetchDataFromExcelSheet("Calls",2, 11)*/};
				
				for(int i=0;i<10;i++)
				{
					
					soft.assertEquals(arr1[j], arr2[k],"Failed: Both result are different");
					
					j++;
					k++;
					
				}
				soft.assertAll();
				create_Lead.scrollpage(driver);
				create_Lead.clickOnSavebtn();
				test.info(input1+ " duplicate record is Created");
			}
			
		//Create the test case to add one Remainders with Multiple Users, Contacts and Leads
		@Test ( groups= {"AddMulitpleInviteesInRemainder", "Sanity"})
		public void AddMulitpleInviteesInRemainder() throws Exception
		{
			//Create this test case in Extent Report
			test= extent.createTest("AddMulitple Invitees In Remainder In Call").assignAuthor("Komal")
					.assignCategory("Functional Test Case").assignDevice("Chrome");
			
			test.info("Add Mulitple Invitees In Remainder");
			Thread.sleep(5000);
			list_View.clickOnAddButton();
			
			
						
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
				calls.enterSubject(UtilityClass.fetchDataFromExcelSheet("calls", 1, 0));
				calls.selectStatus(driver, UtilityClass.fetchDataFromExcelSheet("calls", 1, 1));
				task.selectStartDate(driver, UtilityClass.fetchDataFromExcelSheet("calls", 1, 2), UtilityClass.fetchDataFromExcelSheet("calls", 1, 3));
				calls.enterDuration(driver, UtilityClass.fetchDataFromExcelSheet("calls", 1, 6));
				calls.scrollpageuptoDuration(driver);
				create_Lead.scrollpage(driver);
				//Add Multiple Invites In Remainder function call from Common Functions class
				CommonFunctions.AddMulitpleInviteesInRemainder(calls, UtilityClass.fetchDataFromExcelSheet("calls", 1, 10), 15);
				create_Lead.clickOnSavebtn();
		}
		
		/*//Test case for Filter functionality
		@Test( groups = {"Filter"})
		public void FilterOnCallsModule() throws Exception
		{
			//Create this test case in Extent Report
			 test= extent.createTest("Filter On Calls Module").assignAuthor("Komal")
					.assignCategory("Functional Test Case").assignDevice("Chrome");
			
			 test.info("Filter On Calls Module");
			
				SoftAssert soft=new SoftAssert();
			try{
				
				add_Account.settingFilter(driver,"Filter");
				add_Account.enterNameOnFilter(UtilityClass.fetchDataFromExcelSheet("calls",1, 0));
				task.clickOnFilterRelatedTo(driver, UtilityClass.fetchDataFromExcelSheet("calls", 1, 4), UtilityClass.fetchDataFromExcelSheet("calls", 1, 5));
				calls.selectDirection(driver, UtilityClass.fetchDataFromExcelSheet("calls", 0, 14));
				calls.selectFilterStatus(driver, UtilityClass.fetchDataFromExcelSheet("calls", 1, 1));
				calls.selectStartDate(driver, "Equals", "27-Mar-2023" );
				calls.selectEndDate(driver, "Equals", "27-Mar-2023" );
				//FR.selectSortColumn(driver, "Name");
				//FR.selectSortOrder(driver, "Ascending");
				add_Account.enterSaveFilterOnFilter("TestFilterByAutomation");
				add_Account.clickSearchBtnOnFilter();
				Thread.sleep(2000);
				String Name = add_Account.nameOnListView(driver,UtilityClass.fetchDataFromExcelSheet("calls",1, 0));
				Thread.sleep(2000);
				soft.assertNotNull(Name);
				soft.assertAll();
				}catch(NullPointerException e) {
				
				System.out.println("NullPointerException thrown!");
				
			}
		}*/
		
		//Test case for Export functionality
		@Test( groups = {"Export", "Sanity"})
		public void ExportRecordsForCalls() throws Exception
		{
			 //Call function from Common Function class
			 CommonFunctions.ExportRecords(list_View, "Test Call2", "Calls");
		}
		
		//Test case for Mass update functionality
		@Test( groups={"MassUpdate", "Sanity"})
		public void MassUpdateCalls() throws InterruptedException, EncryptedDocumentException, IOException, AWTException, ParseException
		{
			
			CommonFunctions.MassUpdateCaseModule(list_View, "Calls", "Test Call1");
			
		}
}
