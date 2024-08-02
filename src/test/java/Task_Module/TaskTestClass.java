package Task_Module;



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
import POM_Lead_Module.Add_Lead;
import POM_Lead_Module.Duplicate_Page;
import POM_Lead_Module.Lead_ListView;
import POM_Opportunities_Module.Add_Opportunities;
import POM_Task_Module.Add_Task;
import POM_Task_Module.Duplicate_Task_Page;

public class TaskTestClass extends Base_Class
{		
	Dashboard dashboard;
	Add_Lead create_Lead;
	Lead_ListView list_View;
	Add_Task task;
	Add_Opportunities add_Opportunities;
	Duplicate_Task_Page duplicate_task;
	Duplicate_Page duplicate;
	
	@BeforeMethod(alwaysRun = true)
	public void OpenBrowser() throws InterruptedException
	{
		dashboard=new Dashboard(driver);
		create_Lead=new Add_Lead(driver);
		list_View =new Lead_ListView(driver);
		task = new Add_Task(driver);
		add_Opportunities = new Add_Opportunities(driver);
		duplicate_task = new Duplicate_Task_Page(driver);
		duplicate = new Duplicate_Page(driver);
		
		//directed to the dash board page
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Thread.sleep(2000);
		dashboard.clickOnMenuOption();
		dashboard.clickOnSearch("Tasks");
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Thread.sleep(5000);
		dashboard.clickOnMenuOption(driver,"Tasks");
		Thread.sleep(2000);
		dashboard.closeMenuOption();
		Thread.sleep(4000);
	}
	
	//Create test case for create multiple Account
	@Test( groups={"Create", "Sanity"})
	public void CreateTasks() throws Exception 
	{
		//Create this test case in Extent Report
		test= extent.createTest("Create Tasks").assignAuthor("Komal")
						.assignCategory("Functional Test Case").assignDevice("Chrome");
				
		test.info("Create multiple Tasks");
				
		for(int i=1;i<=5;i++)
		{
			Thread.sleep(5000);
			list_View.clickOnAddButton();
			
			SoftAssert soft = new SoftAssert();
			try {
						
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
				String subject = task.enterSubject(UtilityClass.fetchDataFromExcelSheet("Tasks", i, 0));
				soft.assertNotNull(subject);
				create_Lead.enterDescription(UtilityClass.fetchDataFromExcelSheet("Tasks", i, 10));
				task.clickOnStatus(driver, UtilityClass.fetchDataFromExcelSheet("Tasks", i, 1));
				task.selectStartDate(driver, UtilityClass.fetchDataFromExcelSheet("Tasks", i, 2),UtilityClass.fetchDataFromExcelSheet("Tasks", i, 3));
				task.selectDueDate(driver, UtilityClass.fetchDataFromExcelSheet("Tasks", i, 6),UtilityClass.fetchDataFromExcelSheet("Tasks", i, 7));
				create_Lead.scrollpage(driver);;
				task.Estimate_Effort(UtilityClass.fetchDataFromExcelSheet("Tasks", i, 12));
				task.Effort(UtilityClass.fetchDataFromExcelSheet("Tasks", i, 13));
				task.selectPriority(driver, UtilityClass.fetchDataFromExcelSheet("Tasks", i, 9));
				String related=task.clickRelatedTo(driver, UtilityClass.fetchDataFromExcelSheet("Tasks", i, 4),UtilityClass.fetchDataFromExcelSheet("Tasks", i, 5));
				soft.assertNotNull(related);
				soft.assertAll();
				create_Lead.AssignedTo(driver, UtilityClass.fetchDataFromExcelSheet("Tasks", i, 11));
				task.enterContactName(driver, UtilityClass.fetchDataFromExcelSheet("Tasks", i, 8));
				create_Lead.clickOnSavebtn();
				list_View.getAlertMessage(subject);
				Thread.sleep(7000);
				add_Opportunities.backToListView();
				Thread.sleep(4000);
				list_View.scrolluptoAddBtn(driver);
				Thread.sleep(4000);
			}
	 		catch(NullPointerException e) 
			{
	 			System.out.println("NullPointerException thrown!");
			
			}
		}
	}
	//Create test case for Edit Account
	@Test( groups={"Edit", "Sanity"})
	public void EditTask() throws InterruptedException, EncryptedDocumentException, IOException 
	{
			//Create this test case in Extent Report
			test= extent.createTest("Edit Task").assignAuthor("Komal")
									.assignCategory("Functional Test Case").assignDevice("Chrome");
							
			test.info("User should Edit Task Status");
			String input1="Automation Test Record2";
			list_View.enterTextInSearchBtn(driver,input1);
			Thread.sleep(5000);
							 	
			String OppName=add_Opportunities.verifyOpportunityName(driver, input1);
			if(OppName.equals(input1))
			{
				task.clickOnSubjectInListView(driver, input1);
				Thread.sleep(5000);
				list_View.clickOnEditBtn();
			
				task.clickOnStatus(driver, UtilityClass.fetchDataFromExcelSheet("Tasks", 3, 1));
				create_Lead.enterDescription(UtilityClass.fetchDataFromExcelSheet("Tasks", 3, 10));
				create_Lead.scrollpage(driver);
				create_Lead.clickOnSavebtn();
				String msg=list_View.EveryPageAlert();
				 test.info(msg+ "Message showing on CRM");
				 test.info(input1+ " record is updated");
			}
			else
			{
				System.out.println("Subject not matched");
				test.info(input1+ " task is not matched");
			}
	 
	}
	
		//Create test case for Delete Task From Edit Option
		@Test( groups = {"DeleteFromEditView", "Sanity"}, dependsOnMethods={"CreateTasks"})
		public void DeleteTaskFromEditOption() throws Exception 
		{
			CommonFunctions.DeleteRecordFromEditOption(dashboard, list_View, add_Opportunities,"Automation Test Record3","Tasks");
		
		}
		
	
		//Create test case for Delete Task From List View
		@Test(groups = {"DeleteFromListView", "Sanity"}, dependsOnMethods={"CreateTasks"})
		public void DeleteTaskFromListView() throws InterruptedException, EncryptedDocumentException, IOException 
		{
			CommonFunctions.DeleteRecordFromListView(list_View, add_Opportunities, "Automation Test Record4","Tasks" );
			
		}
		
		
		@Test( groups= {"Duplicate"})
		public void DuplicateTask() throws InterruptedException, EncryptedDocumentException, IOException, ParseException
		{
			//Create this test case in Extent Report
			 test= extent.createTest("Verify Account Is Duplicate ?").assignAuthor("Komal")
					.assignCategory("Functional Test Case").assignDevice("Chrome");
			
			 test.info("Verify Account is duplicate or not");
			 		
			SoftAssert soft=new SoftAssert();
			String input1="Automation Test Record1";
			list_View.enterTextInSearchBtn(driver,input1);
			Thread.sleep(5000);
		
			 	
				String OppName=add_Opportunities.verifyOpportunityName(driver, input1);
				if(OppName.equals(input1))
				{
					
					task.clickOnSubjectInListView(driver, input1);	
					Thread.sleep(5000);
					list_View.menu(driver,"DUPLICATE");
					//get Start Date 
					String[] date = duplicate_task.getDateStart(); 
					String StartDate = date[0];
					//Split date by -
					String[] dateex=StartDate.split("-");
					String month=dateex[1];
					//convert date string to int
					int m = Integer.parseInt(month);  
					//get month name format MMM
					String MonthName=UtilityClass.convertMonth(m);
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
					//get Due Date
					String[] date1 = duplicate_task.getDateDue();
					String DueDate = date1[0];
					//Split date by -
					String[] duedate=DueDate.split("-");
					String Duemonth=duedate[1];
					//convert date string to int
					int Duem=Integer.parseInt(Duemonth);  
					//get month name format MMM
					String DueMonthName=UtilityClass.convertMonth(Duem);
					//combine date dd-MMM-yyy
					String ExactdueDate=duedate[0]+"-"+DueMonthName+"-"+duedate[2];
					//get Due Time
					String DueTime = date1[1];
					//time split from index 5 
					String duetime=DueTime.substring(0,5);
					String AMPM2=DueTime.substring(5);
					String ampm2 = AMPM2.toUpperCase();
				
					//Add space in between time and AM PM
					String Duetime = duetime+ " " +ampm2;
										
					int j=0,k=0;
					String related = duplicate_task.getRelatedTo();
					String arr1[]= {duplicate_task.getSubject(), duplicate_task.getStatus(),ExactstartDate,starttime,
							related,duplicate_task.getRelatedToDynamic(related),ExactdueDate,Duetime,
							duplicate_task.getContact_Name(),duplicate_task.getPriority(),duplicate.getDescription(),duplicate_task.getEstimatedEffort(), duplicate_task.getEffort() };
					
					String arr2[]= {UtilityClass.fetchDataFromExcelSheet("Tasks",1, 0),UtilityClass.fetchDataFromExcelSheet("Tasks",1, 1),UtilityClass.fetchDataFromExcelSheet("Tasks",1, 2),
							UtilityClass.fetchDataFromExcelSheet("Tasks",1, 3),UtilityClass.fetchDataFromExcelSheet("Tasks",1, 4),UtilityClass.fetchDataFromExcelSheet("Tasks",1, 5),
							UtilityClass.fetchDataFromExcelSheet("Tasks",1, 6),UtilityClass.fetchDataFromExcelSheet("Tasks",1, 7),UtilityClass.fetchDataFromExcelSheet("Tasks",1, 8),
							UtilityClass.fetchDataFromExcelSheet("Tasks",1, 9),UtilityClass.fetchDataFromExcelSheet("Tasks",1, 10), UtilityClass.fetchDataFromExcelSheet("Tasks",1, 12), UtilityClass.fetchDataFromExcelSheet("Tasks",1, 13)};
					
					for(int i=0;i<13;i++)
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
		}
		
		//Test case for Export functionality
		@Test( groups = {"Export", "Sanity"})
		public void ExportRecordsForTasks() throws Exception
		{
				//Call function from Common Function class
				CommonFunctions.ExportRecords(list_View, "Automation Test Record2", "Tasks");
		}
		
		//Test case for Mass update functionality
		@Test( groups={"MassUpdate", "Sanity"})
		public void MassUpdateTasks() throws InterruptedException, EncryptedDocumentException, IOException, AWTException, ParseException
		{
			
			CommonFunctions.MassUpdateCaseModule(list_View, "Accounts", "Automation Test Record2");
			
		}
}

