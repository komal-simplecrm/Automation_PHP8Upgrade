package Bugs_Module;

import java.awt.AWTException;
import java.io.IOException;
import java.text.ParseException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Library_Files.Base_Class;
import Library_Files.CommonFunctions;
import Library_Files.UtilityClass;
import Login_Page.Dashboard;
import POM_Bugs_Module.Add_Bugs;
import POM_Calls_Module.Add_Calls;

import POM_Cases_Module.Create_Case;
import POM_Lead_Module.Add_Lead;

import POM_Lead_Module.Lead_ListView;
import POM_Opportunities_Module.Add_Opportunities;


public class BugsTestClass extends Base_Class
{
	
	Dashboard dashboard;
	Add_Lead create_Lead;
	Add_Calls calls;
	Lead_ListView list_View;
	Create_Case cases;
	Add_Bugs bugs;
	Add_Opportunities add_Opportunities;
	SoftAssert soft;
	
	@BeforeMethod(alwaysRun = true)
	public void OpenBrowser() throws InterruptedException
	{
		dashboard = new Dashboard(driver);
		create_Lead = new Add_Lead(driver);
		list_View = new Lead_ListView(driver);
		calls = new Add_Calls(driver);
		cases = new Create_Case(driver);
		bugs = new Add_Bugs(driver);
		add_Opportunities = new Add_Opportunities(driver);
		soft = new SoftAssert();
		
		//directed to the dash board page
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Thread.sleep(2000);
		dashboard.clickOnMenuOption();
		dashboard.clickOnSearch("Bugs");
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Thread.sleep(5000);
		dashboard.clickOnMenuOption(driver,"Bugs");
		Thread.sleep(4000);
		dashboard.closeMenuOption();	
		Thread.sleep(4000);
	}
	
	//Create test case for create multiple Bugs
		@Test(groups={"Create"})
		public void CreateBugs() throws EncryptedDocumentException, IOException, InterruptedException 
		{
			//Create this test case in Extent Report
			 test= extent.createTest("Create Bugs").assignAuthor("Komal")
					.assignCategory("Functional Test Case").assignDevice("Chrome");
			
			 
			 test.info("New Bugs are created");
			for(int i=1;i<=6;i++)
			{
				try {
						
						Thread.sleep(5000);
						list_View.clickOnAddButton();
						//bugs.enterNumber(UtilityClass.fetchDataFromExcelSheet("Bugs", i, 10));
						String subject = calls.enterSubject(UtilityClass.fetchDataFromExcelSheet("Bugs", i, 0));
						Assert.assertNotNull(subject);
						cases.selectPriority(driver, UtilityClass.fetchDataFromExcelSheet("Bugs",i, 1));
						cases.selectType(driver, UtilityClass.fetchDataFromExcelSheet("Bugs",i, 2));
						bugs.selectSource(driver, UtilityClass.fetchDataFromExcelSheet("Bugs",i, 3));
						bugs.selectStatus(driver, UtilityClass.fetchDataFromExcelSheet("Bugs",i, 4));
						bugs.selectCategory(driver, UtilityClass.fetchDataFromExcelSheet("Bugs",i, 5));
						bugs.selectResolution(driver,  UtilityClass.fetchDataFromExcelSheet("Bugs",i, 6));
						//bugs.selectFound_in_release(driver,  UtilityClass.fetchDataFromExcelSheet("Bugs",i, 3));
						//bugs.selectFixed_in_release(driver,  UtilityClass.fetchDataFromExcelSheet("Bugs",i, 3));
						create_Lead.enterDescription( UtilityClass.fetchDataFromExcelSheet("Bugs",i, 7));
						create_Lead.scrollpage(driver);
						bugs.enterWork_log( UtilityClass.fetchDataFromExcelSheet("Bugs",i, 8));
						create_Lead.AssignedTo(driver, UtilityClass.fetchDataFromExcelSheet("Bugs",i, 9));
						create_Lead.clickOnSavebtn();
						list_View.getAlertMessage(subject);
						Thread.sleep(7000);
						add_Opportunities.backToListView();
						Thread.sleep(4000);
						list_View.scrolluptoAddBtn(driver);
						Thread.sleep(4000);
						
				}
				catch(NullPointerException e) {
					System.out.println("NullPointerException thrown!");
					create_Lead.clickOnSavebtn();
					
				}
			}
		}
		
		//Delete the bug
		@Test(groups= {"DeleteFromEditView", "Sanity"}, dependsOnMethods={"CreateBugs"})
		public void DeleteBugFromEditOption() throws Exception
		{
			//Create this test case in Extent Report
			 test= extent.createTest("Delete Bug From Edit Option").assignAuthor("Komal")
					.assignCategory("Functional Test Case").assignDevice("Chrome");
			
			 test.info("Delete Bugs from Edit Option");
			 
			 String input1="";
			//Search case number with subject in filter and click on subject, Subject: Test Bug2
			 
			 list_View.clickOnFilter(driver, input1,UtilityClass.fetchDataFromExcelSheet("Bugs",2, 0), "Bugs");
			  	cases.clickOnSubject(driver, input1, UtilityClass.fetchDataFromExcelSheet("Bugs",2, 0));
			 Thread.sleep(5000);
				list_View.menu(driver,"DELETE");
				list_View.confirmDelete();
				String msg = list_View.EveryPageAlert();
				 test.info(msg+ " Message showing on CRM");
				test.info(input1+ " record is deleted");
				if(msg.contains("Oops"))
				{
					soft.fail();
				}
				
		}
		
		//Delete the case
		@Test(groups= {"DeleteFromListView", "Sanity"}, dependsOnMethods={"CreateBugs"})
		public void DeleteBugFromListView() throws Exception
		{
				//Create this test case in Extent Report
				test= extent.createTest("Delete Bug From List View").assignAuthor("Komal")
							.assignCategory("Functional Test Case").assignDevice("Chrome");
					
				test.info("Delete Bug from List View");
					 
				String input1="";
				//Search bug number with subject i filter and click on subject, Subject: Test Bug4
					
				list_View.clickOnFilter(driver,input1,UtilityClass.fetchDataFromExcelSheet("Bugs",4, 0),"Bugs");
				Thread.sleep(5000);
				list_View.selectCheckBox(driver, UtilityClass.fetchDataFromExcelSheet("Bugs",4, 0));
				Thread.sleep(5000);
				list_View.clickOnDelete();
				String msg=list_View.EveryPageAlert();
				test.info(msg+ "Message showing on CRM");
				test.info(input1+ " record is deleted");
				if(msg.contains("Oops"))
				{
						soft.fail();
				}
					
						
		}
				
		//Create test case to edit the case
		@Test(groups= {"Edit", "Sanity"})
		public void EditBug() throws InterruptedException, EncryptedDocumentException, IOException
		{
			//Create this test case in Extent Report
			test= extent.createTest("Edit Bug").assignAuthor("Komal")
							.assignCategory("Functional Test Case").assignDevice("Chrome");
						
			test.info("Edit Bug");
			String input1=""; 
			//Search bug number with subject i filter and click on subject
						 	
			list_View.clickOnFilter(driver,input1,UtilityClass.fetchDataFromExcelSheet("Bugs",1, 0),"Bugs");
			cases.clickOnSubject(driver, input1, UtilityClass.fetchDataFromExcelSheet("Bugs",1, 0));
			Thread.sleep(5000);
			list_View.clickOnEditBtn();
			
			bugs.selectStatus(driver, UtilityClass.fetchDataFromExcelSheet("Bugs",3, 4));
			bugs.selectResolution(driver, UtilityClass.fetchDataFromExcelSheet("Bugs",3, 6));
			
			create_Lead.scrollpage(driver);
			create_Lead.clickOnSavebtn();
			String msg = list_View.EveryPageAlert();
			test.info(msg+ "Message showing on CRM");
			test.info(input1+ " record is updated");
	}
		
		//Mass update cases assigned to Rahul Thakre
		@Test( groups= {"MassUpdate", "Sanity"})
		public void MassUpdateBugModule() throws InterruptedException, EncryptedDocumentException, IOException, AWTException, ParseException
		{
		
		 	CommonFunctions.MassUpdateCaseModule(list_View, "Bugs", "Test Bug5");
		
		}
		
		
		//Test case for Export functionality
		@Test( groups = {"Export", "Sanity"})
		public void ExportRecordsForBugs() throws Exception
		{
			//Create this test case in Extent Report
			test= extent.createTest( "Export functionality for Bugs.").assignAuthor("Komal")
					.assignCategory("Functional Test Case").assignDevice("Chrome");
			
			String input1="";
			//Filter the Document
			Thread.sleep(5000);
			
			 list_View.clickOnFilter(driver,input1,UtilityClass.fetchDataFromExcelSheet("Bugs",6, 0),"Bugs");
			Thread.sleep(5000);
			list_View.ExportAllRecords(driver, UtilityClass.fetchDataFromExcelSheet("Bugs",6, 0), "Bugs");
		}
}
