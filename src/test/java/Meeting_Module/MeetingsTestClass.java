package Meeting_Module;

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
import POM_Calls_Module.Add_Calls;
import POM_Calls_Module.Duplicate_Call_Page;
import POM_Lead_Module.Add_Lead;
import POM_Lead_Module.Duplicate_Page;
import POM_Lead_Module.Lead_ListView;
import POM_Meeting_Module.Add_Meeting;
import POM_Opportunities_Module.Add_Opportunities;
import POM_Task_Module.Add_Task;
import POM_Task_Module.Duplicate_Task_Page;

public class MeetingsTestClass extends Base_Class
{
	Dashboard dashboard;
	Add_Lead create_Lead;
	Lead_ListView list_View;
	Add_Calls calls;
	Add_Task task;
	Add_Meeting meeting;
	Add_Opportunities add_Opportunities;
	Duplicate_Call_Page duplicate_call;
	Duplicate_Task_Page duplicate_task;
	Duplicate_Page duplicate;
	
	@BeforeMethod(alwaysRun = true)
	public void OpenBrowser() throws InterruptedException
	{
		dashboard = new Dashboard(driver);
		create_Lead = new Add_Lead(driver);
		list_View = new Lead_ListView(driver);
		calls = new Add_Calls(driver);
		task = new Add_Task(driver);
		meeting = new Add_Meeting(driver);
		add_Opportunities = new Add_Opportunities(driver);
		duplicate_task = new Duplicate_Task_Page(driver);
		duplicate_call = new Duplicate_Call_Page(driver);
		duplicate=new Duplicate_Page(driver);
		
		//directed to the dash board page
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Thread.sleep(2000);
		dashboard.clickOnMenuOption();
		dashboard.clickOnSearch("Meetings");
		Thread.sleep(5000);
		dashboard.clickOnMenuOption(driver,"Meetings");
		Thread.sleep(2000);
		dashboard.closeMenuOption();
		Thread.sleep(4000);
	}
	
	 		//Create test case for create multiple Account
			@Test( groups={"Create", "Sanity"})
			public void CreateMeetings() throws Exception 
			{
				//Create this test case in Extent Report
				test= extent.createTest("Create Meetings").assignAuthor("Komal")
								.assignCategory("Functional Test Case").assignDevice("Chrome");
						
				test.info("Create multiple Meetings");
						
				for(int i=1;i<=3;i++)
				{
					Thread.sleep(5000);
					list_View.clickOnAddButton();
					
					SoftAssert soft=new SoftAssert();
					try {
						
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
						
							String subject = calls.enterSubject(UtilityClass.fetchDataFromExcelSheet("Meetings", i, 0));
							System.out.println("subject: "+UtilityClass.fetchDataFromExcelSheet("Meetings", i, 0));
							soft.assertNotNull(subject);
							create_Lead.enterDescription(UtilityClass.fetchDataFromExcelSheet("Meetings", i, 9));
							task.selectStartDate(driver, UtilityClass.fetchDataFromExcelSheet("Meetings", i, 2), UtilityClass.fetchDataFromExcelSheet("Meetings", i, 3));
							meeting.selectDateEnd(driver, UtilityClass.fetchDataFromExcelSheet("Meetings", i, 6), UtilityClass.fetchDataFromExcelSheet("Meetings", i, 7));
							calls.selectStatus(driver, UtilityClass.fetchDataFromExcelSheet("Meetings", i, 1));
							calls.enterDuration(driver, UtilityClass.fetchDataFromExcelSheet("Meetings", i, 8));
							task.clickRelatedTo(driver, UtilityClass.fetchDataFromExcelSheet("Meetings", i, 4), UtilityClass.fetchDataFromExcelSheet("Meetings", i, 5));
							create_Lead.scrollpage(driver);
							//meeting.enterLocation(UtilityClass.fetchDataFromExcelSheet("Meetings", i, 20));
							//calls.enterDuration_Hours(UtilityClass.fetchDataFromExcelSheet("Meetings", i, 8));
						
							//calls.scrollpageuptoDuration(driver);
							//calls.clickOnAdd_Reminder();
							String a[]= {"Users","Contacts","Leads"};
							int k = 14;
							for(int j=0;j<=2;j++)
							{
									calls.addInviteesInRemainder(driver, 0, a[j], UtilityClass.fetchDataFromExcelSheet("calls", i, k));
									k=k+2;	
							}
							Thread.sleep(5000);
						
							calls.entertimebefore(driver, UtilityClass.fetchDataFromExcelSheet("Meetings",i, 11), 0);
							//calls.scrollUpToAdd_Reminder(driver);
							create_Lead.AssignedTo(driver, UtilityClass.fetchDataFromExcelSheet("Meetings", i, 10));
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
			public void EditMeeting() throws Exception 
			{
					//Create this test case in Extent Report
					test= extent.createTest("Edit Meeting").assignAuthor("Komal")
											.assignCategory("Functional Test Case").assignDevice("Chrome");
									
					test.info("User should Edit Meeting Status");
					String input1="Test Meeting1";
					list_View.enterTextInSearchBtn(driver,input1);
					Thread.sleep(5000);
									 	
					String OppName=add_Opportunities.verifyOpportunityName(driver, input1);
					if(OppName.equals(input1))
					{
						task.clickOnSubjectInListView(driver, input1);
						Thread.sleep(5000);
						list_View.clickOnEditBtn();
						//calls.enterSubject(UtilityClass.fetchDataFromExcelSheet("Meetings", 3, 0));
						calls.selectStatus(driver, UtilityClass.fetchDataFromExcelSheet("Meetings", 3, 1));
						meeting.selectDateEnd(driver, UtilityClass.fetchDataFromExcelSheet("Meetings", 3, 6), UtilityClass.fetchDataFromExcelSheet("Meetings", 3, 7));
						create_Lead.scrollpage(driver);
						create_Lead.clickOnSavebtn();
						String msg=list_View.EveryPageAlert();
						test.info(msg+ " Message showing on CRM");
						test.info(input1+ " record is updated");
					}
					else
					{
						System.out.println("Subject not matched");
						test.info(input1+ " meeting subject is not matched");
					}
			 
			}
			
			//Create test case for Delete Call From Edit Option
			@Test( groups = {"DeleteFromEditView", "Sanity"}, dependsOnMethods={"CreateMeetings"})
			public void DeleteMeetingsFromEditOption() throws Exception 
			{
				CommonFunctions.DeleteRecordFromEditOption(dashboard, list_View, add_Opportunities,"Test Meeting2", "Meetings");
				
			}
			
			
			//Create test case for Delete Task From List View
			@Test( groups = {"DeleteFromListView", "Sanity"}, dependsOnMethods={"CreateMeetings"})
			public void DeleteMeetingsFromListView() throws InterruptedException, EncryptedDocumentException, IOException 
			{
				CommonFunctions.DeleteRecordFromListView(list_View, add_Opportunities, "Test Meeting1", "Meetings");
					
			}
			
			//Create the test case to add multiple Remainders with one User, Contact and Lead
			@Test(groups = {"AddMultipleRemainders", "Sanity"})
				
			public void AddMultipleRemainders() throws Exception
				
			{	//Create this test case in Extent Report
				test= extent.createTest("Add Multiple Remainders In Meeting").assignAuthor("Komal")
							.assignCategory("Functional Test Case").assignDevice("Chrome");
					
					
				test.info("Add multiple Remainders with one User, Contact and Lead");
				Thread.sleep(5000);
				list_View.clickOnAddButton();
					
					
								
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
				calls.enterSubject(UtilityClass.fetchDataFromExcelSheet("Meetings", 1, 0));
				calls.selectStatus(driver, UtilityClass.fetchDataFromExcelSheet("Meetings", 1, 1));
				task.selectStartDate(driver, UtilityClass.fetchDataFromExcelSheet("Meetings", 1, 2), UtilityClass.fetchDataFromExcelSheet("Meetings", 1, 3));
				meeting.selectDateEnd(driver, UtilityClass.fetchDataFromExcelSheet("Meetings", 1, 6), UtilityClass.fetchDataFromExcelSheet("Meetings", 1, 7));
				task.clickRelatedTo(driver, UtilityClass.fetchDataFromExcelSheet("Meetings", 1, 4), UtilityClass.fetchDataFromExcelSheet("Meetings", 1, 5));
				//meeting.enterLocation(UtilityClass.fetchDataFromExcelSheet("Meetings", 1, 20));
				calls.enterDuration(driver, UtilityClass.fetchDataFromExcelSheet("Meetings", 1, 8));
				create_Lead.scrollpage(driver);
				//calls.scrollpageuptoDuration(driver);
				//Add Remainders function call from Common Functions class
				CommonFunctions.Add_Remainder(calls, UtilityClass.fetchDataFromExcelSheet("Meetings", 1, 11), 14);
				create_Lead.scrollpage(driver);
				create_Lead.clickOnSavebtn();
			}
			
			
			//Create the test case to add one Remainders with Multiple Users, Contacts and Leads
			@Test ( groups= {"AddMulitpleInviteesInRemainder", "Sanity"})
			public void AddMulitpleInviteesInRemainder() throws Exception
			{
				//Create this test case in Extent Report
				test= extent.createTest("Add Mulitple Invitees In Remainder In Meeting").assignAuthor("Komal")
						.assignCategory("Functional Test Case").assignDevice("Chrome");
				
				test.info("Add Mulitple Invitees In Remainder");
				Thread.sleep(5000);
				list_View.clickOnAddButton();
				
				
							
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
					calls.enterSubject(UtilityClass.fetchDataFromExcelSheet("Meetings", 3, 0));
					calls.selectStatus(driver, UtilityClass.fetchDataFromExcelSheet("Meetings", 2, 1));
					task.selectStartDate(driver, UtilityClass.fetchDataFromExcelSheet("Meetings", 2, 2), UtilityClass.fetchDataFromExcelSheet("Meetings", 2, 3));
					meeting.selectDateEnd(driver, UtilityClass.fetchDataFromExcelSheet("Meetings", 2, 6), UtilityClass.fetchDataFromExcelSheet("Meetings", 2, 7));
					task.clickRelatedTo(driver, UtilityClass.fetchDataFromExcelSheet("Meetings", 2, 4), UtilityClass.fetchDataFromExcelSheet("Meetings", 2, 5));	
					//meeting.enterLocation(UtilityClass.fetchDataFromExcelSheet("Meetings", 2, 20));
					calls.enterDuration(driver, UtilityClass.fetchDataFromExcelSheet("Meetings", 2, 8));
					//calls.scrollpageuptoDuration(driver);
					create_Lead.scrollpage(driver);
					//Add Multiple Invites In Remainder function call from Common Functions class
					CommonFunctions.AddMulitpleInviteesInRemainder(calls, UtilityClass.fetchDataFromExcelSheet("Meetings", 1, 11), 15);
					
					create_Lead.scrollpage(driver);
					create_Lead.clickOnSavebtn();
			}
			
			//Create the test case to Verify Call Is Duplicate?
			@Test( groups={"Duplicate"})
			public void DuplicateMeetings() throws InterruptedException, EncryptedDocumentException, IOException, AWTException
			{	
				//Create this test case in Extent Report
				test= extent.createTest("Verify Meeting Is Duplicate?").assignAuthor("Komal")
						.assignCategory("Functional Test Case").assignDevice("Chrome");
				
				test.info("Verify meeting is duplicate or not");
				SoftAssert soft = new SoftAssert();
				/*System.out.println("Enter LeadName: ");
				Scanner scan1 = new Scanner(System.in);
				String input1 = scan1.nextLine();*/
				String input1 = "Test meeting generate1";
				list_View.enterTextInSearchBtn(driver,input1);
				Thread.sleep(2000);
				list_View.clickOnName(driver,input1);
				
				
				Thread.sleep(5000);
				list_View.menu(driver,"DUPLICATE");
				
				//get Start Date 
				String[] date = duplicate_task.getDateStart(); 
				String StartDate = date[0];
				//Split date by -
				String[] dateex = StartDate.split("-");
				String month = dateex[1];
				//convert date string to int
				int m = Integer.parseInt(month);  
				//get month name format MMM
				String MonthName = UtilityClass.convertMonth(m);
				//combine date dd-MMM-yyyy
				String ExactstartDate = dateex[0]+"-"+MonthName+"-"+dateex[2];
				
				//get Start Time
				String StartTime = date[1];
				//time split from index 5 
				String time = StartTime.substring(0,5);
				String AMPM1 = StartTime.substring(5);
				String ampm1 = AMPM1.toUpperCase();
				//Add space in between time and AM PM
				String starttime = time+ " " +ampm1;
				
				//get End Date 
				String[] enddate = meeting.getDateEnd();
				String endDate = enddate[0];
				//Split date by -
				String[] Enddate = endDate.split("-");
				String Endmonth = dateex[1];
				//convert date string to int
				int em = Integer.parseInt(Endmonth);  
				//get month name format MMM
				String EndMonthName = UtilityClass.convertMonth(em);
				//combine date dd-MMM-yyyy
				String ExactEndDate = Enddate[0]+"-"+EndMonthName+"-"+Enddate[2];
				
				//get End Time
				String EndTime = enddate[1];
				//time split from index 5 
				String endtime = EndTime.substring(0,5);
				String AMPM2 = EndTime.substring(5);
				String ampm2 = AMPM2.toUpperCase();
				//Add space in between time and AM PM
				String Endtime = endtime+ " " +ampm2;
				int j=0, k=0, Inv=12;
				//compare Invites
				for(int i=1;i<=4;i++)
				{
					String invitee = meeting.getInvitee(driver, i);
					System.out.println(invitee);
					soft.assertEquals(invitee, UtilityClass.fetchDataFromExcelSheet("Meetings",2, Inv),"Failed: Both result are different");
					Inv = Inv+2;
					
				}
				String related = duplicate_task.getRelatedTo();
			
				String arr1[]= {duplicate_call.getSubject(),duplicate_call.getStatus(),ExactstartDate,starttime,
						related,duplicate_task.getRelatedToDynamic(related),ExactEndDate, Endtime, meeting.getDuration(), 
						/*meeting.getLocation(),*/duplicate_call.getPopUpRemainder(),duplicate_call.getEmailRemainder(),duplicate.getDescription()};
				String arr2[]= {UtilityClass.fetchDataFromExcelSheet("Meetings",2, 0),UtilityClass.fetchDataFromExcelSheet("Meetings",2, 1),UtilityClass.fetchDataFromExcelSheet("Meetings",2, 2),
						UtilityClass.fetchDataFromExcelSheet("Meetings",2, 3),UtilityClass.fetchDataFromExcelSheet("Meetings",2, 4),UtilityClass.fetchDataFromExcelSheet("Meetings",2, 5),
						UtilityClass.fetchDataFromExcelSheet("Meetings",2, 6),UtilityClass.fetchDataFromExcelSheet("Meetings",2, 7),UtilityClass.fetchDataFromExcelSheet("Meetings",2, 8),
						/*UtilityClass.fetchDataFromExcelSheet("Meetings",2, 20),*/UtilityClass.fetchDataFromExcelSheet("Meetings",2, 11),UtilityClass.fetchDataFromExcelSheet("Meetings",2, 11),
						UtilityClass.fetchDataFromExcelSheet("Meetings",2, 9)};
				
				for(int i=0;i<12;i++)
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
			
			//Test case for Export functionality
			@Test( groups = {"Export", "Sanity"})
			public void ExportRecordsForMeetings() throws Exception
			{
					
					//Call function from Common Function class
					CommonFunctions.ExportRecords(list_View, "Test meeting generate1", "Meetings");
			}
			
			//Test case for Mass update functionality
			@Test( groups={"MassUpdate", "Sanity"})
			public void MassUpdateMeetings() throws InterruptedException, EncryptedDocumentException, IOException, AWTException, ParseException
			{
				
				CommonFunctions.MassUpdateCaseModule(list_View, "Accounts", "Test Meeting2");
				
			}
}
