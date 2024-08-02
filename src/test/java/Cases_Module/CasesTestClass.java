package Cases_Module;



import java.awt.AWTException;
import java.io.IOException;
import java.text.ParseException;



import org.apache.poi.EncryptedDocumentException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.Duration;
import Library_Files.Base_Class;
import Library_Files.CommonFunctions;
import Library_Files.UtilityClass;
import Login_Page.Dashboard;
import POM_Cases_Module.Create_Case;
import POM_Cases_Module.Duplicate_Case;
import POM_Lead_Module.Add_Lead;

import POM_Lead_Module.Lead_ListView;
import POM_Opportunities_Module.Add_Opportunities;
import POM_Opportunities_Module.Duplicate_Opp_Page;


public class CasesTestClass  extends Base_Class
{	Create_Case cases;
	Dashboard dashboard;
	Lead_ListView list_View;
	Add_Opportunities add_Opportunities;
	Add_Lead create_Lead;
	Duplicate_Case duplicate_case;
	Duplicate_Opp_Page duplicate_opp;
	SoftAssert soft;
	
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
		duplicate_opp = new Duplicate_Opp_Page(driver);
		soft = new SoftAssert();
		
		//directed to the dash board page
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//Thread.sleep(2000);
		dashboard.clickOnMenuOption();
		dashboard.clickOnSearch("Cases");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		//Thread.sleep(4000);
		dashboard.clickOnMenuOption(driver,"Cases");
		//dashboard.clickOnMenuOption();
		dashboard.closeMenuOption();	
		Thread.sleep(4000);
	}
	
	//Create test case for create multiple Cases
	@Test( groups={"Create", "Sanity"})
	public void CreateCase() throws EncryptedDocumentException, IOException, InterruptedException 
	{
		//Create this test case in Extent Report
		 test= extent.createTest("Create Cases").assignAuthor("Komal")
				.assignCategory("Functional Test Case").assignDevice("Chrome");
		
		 
		 test.info("New cases are created");
		for(int i=1;i<=6;i++)
		{
			try {
					
					Thread.sleep(5000);
					list_View.clickOnAddButton();
					cases.selectPriority(driver, UtilityClass.fetchDataFromExcelSheet("Cases",i, 0));
					cases.selectState(driver, UtilityClass.fetchDataFromExcelSheet("Cases",i, 1), UtilityClass.fetchDataFromExcelSheet("Cases",i, 2),UtilityClass.fetchDataFromExcelSheet("Cases",i, 7));
					Thread.sleep(2000);
					cases.scrollUptoState(driver);
					Thread.sleep(2000);
					cases.selectType(driver, UtilityClass.fetchDataFromExcelSheet("Cases",i, 3));
					add_Opportunities.enterAccountName(driver,UtilityClass.fetchDataFromExcelSheet("Cases",i, 4));
					cases.clickOnGeneralFeedbackforSubject(driver,UtilityClass.fetchDataFromExcelSheet("Cases",i, 9) );
					String subject = duplicate_case.getSubject();
					soft.assertNotNull(subject);
					test.info(subject+ " Case is created.");
					Thread.sleep(2000);
					create_Lead.scrollpage(driver);
					
					Thread.sleep(2000);
					create_Lead.AssignedTo(driver, UtilityClass.fetchDataFromExcelSheet("Cases",i, 8));
					create_Lead.clickOnSavebtn();
					list_View.getAlertMessage(subject);
					Thread.sleep(7000);
					soft.assertAll();
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
	
	
	//Delete the case
	@Test( groups= {"DeleteFromEditView", "Sanity"}, dependsOnMethods={"CreateCase"})
	public void DeleteCaseFromEditOption() throws Exception
	{
		//Create this test case in Extent Report
		 test= extent.createTest("Delete Case From Edit Option").assignAuthor("Komal")
				.assignCategory("Functional Test Case").assignDevice("Chrome");
		
		 test.info("Delete Case from Edit Option");
		 
		 String input1="";
		//Search case number with subject in filter and click on subject, Subject: ICICI Finance - Claim
		 
		 list_View.clickOnFilter(driver, input1,UtilityClass.fetchDataFromExcelSheet("Cases",2, 9), "Case");
		  	cases.clickOnSubject(driver, input1, UtilityClass.fetchDataFromExcelSheet("Cases",2, 9));
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
		@Test( groups= {"DeleteFromListView", "Sanity"}, dependsOnMethods={"CreateCase"})
		public void DeleteCaseFromListView() throws Exception
		{
			//Create this test case in Extent Report
			 test= extent.createTest("Delete Case From List View").assignAuthor("Komal")
					.assignCategory("Functional Test Case").assignDevice("Chrome");
			
			 test.info("Delete Case from LIst View");
			 
			 String input1="";
			//Search case number with subject i filter and click on subject, Subject: Axis Finance - Complaint
			
			 list_View.clickOnFilter(driver,input1,UtilityClass.fetchDataFromExcelSheet("Cases",4, 9),"Case");
			 Thread.sleep(5000);
			 list_View.selectCheckBox(driver, UtilityClass.fetchDataFromExcelSheet("Cases",4, 9));
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
	@Test( groups= {"Edit", "Sanity"})
	public void EditCase() throws InterruptedException, EncryptedDocumentException, IOException
	{
		//Create this test case in Extent Report
		 test= extent.createTest("Edit Case").assignAuthor("Komal")
					.assignCategory("Functional Test Case").assignDevice("Chrome");
			
		 		test.info("Edit Case");
			 	String input1=""; 
			 	//Search case number with subject i filter and click on subject
			 	
			 	 list_View.clickOnFilter(driver,input1,UtilityClass.fetchDataFromExcelSheet("Cases",1, 9),"Case");
			 	cases.clickOnSubject(driver, input1, UtilityClass.fetchDataFromExcelSheet("Cases",1, 9));
			 	Thread.sleep(5000);
				list_View.clickOnEditBtn();
				cases.selectState(driver, UtilityClass.fetchDataFromExcelSheet("Cases",2, 1), UtilityClass.fetchDataFromExcelSheet("Cases",2, 2),UtilityClass.fetchDataFromExcelSheet("Cases",3, 7));
				create_Lead.scrollpage(driver);
				create_Lead.clickOnSavebtn();
				String msg=list_View.EveryPageAlert();
				 test.info(msg+ "Message showing on CRM");
				test.info(input1+ " record is updated");
	}
	
	//Create duplicate Case always duplicate case have status New
	@Test( groups= {"Duplicate"})
	public void DuplicateCase() throws InterruptedException, EncryptedDocumentException, IOException 
	{
		//Create this test case in Extent Report
		 test= extent.createTest("Duplicate Case").assignAuthor("Komal")
					.assignCategory("Functional Test Case").assignDevice("Chrome");
		 
		 test.info("Create Duplicate Case");
		 String input1=""; 
		 	//Search case number with subject i filter and click on subject
		
		 list_View.clickOnFilter(driver,input1,UtilityClass.fetchDataFromExcelSheet("Cases",1, 9),"Case");
		  	cases.clickOnSubject(driver, input1, UtilityClass.fetchDataFromExcelSheet("Cases",1, 9));
		 Thread.sleep(5000);
		 list_View.menu(driver,"DUPLICATE");
		 create_Lead.scrollpage(driver);
		 create_Lead.clickOnSavebtn();
		 String msg=list_View.EveryPageAlert();
		 test.info(msg+ "Message showing on CRM");
			test.info(input1+ " Duplicate record is created");
		 
	}
	
	
	@Test( groups= {"Duplicate"})
	public void VerifyDuplicateCase() throws InterruptedException, EncryptedDocumentException, IOException, ParseException
	{
		//Create this test case in Extent Report
		 test= extent.createTest("Verify Case Is Duplicate?").assignAuthor("Komal")
				.assignCategory("Functional Test Case").assignDevice("Chrome");
		
		 test.info("Verify Case is duplicate or not");
		 		
		SoftAssert soft=new SoftAssert();
		String input1="";
		//Search case number with subject in filter and click on subject, Subject:HDFC Finance - Service Request
		
		 list_View.clickOnFilter(driver,input1,UtilityClass.fetchDataFromExcelSheet("Cases",2, 9), "Case");
	  	cases.clickOnSubject(driver, input1, UtilityClass.fetchDataFromExcelSheet("Cases",2, 9));
		 Thread.sleep(5000);
		list_View.menu(driver,"DUPLICATE");
		
		int j=0,k=0;
		String arr1[]= {duplicate_case.getPriority(),duplicate_case.getState(),duplicate_case.getStatus(),
				duplicate_case.getType(),duplicate_opp.getAccountName(),duplicate_case.getSubject(),duplicate_case.getResolution()};
		String arr2[]= {UtilityClass.fetchDataFromExcelSheet("Cases",2, 0),UtilityClass.fetchDataFromExcelSheet("Cases",2, 1),UtilityClass.fetchDataFromExcelSheet("Cases",2, 2),
				UtilityClass.fetchDataFromExcelSheet("Cases",2, 3),UtilityClass.fetchDataFromExcelSheet("Cases",2, 4),UtilityClass.fetchDataFromExcelSheet("Cases",2, 9),UtilityClass.fetchDataFromExcelSheet("Cases",2, 7)};
		
		for(int i=0;i<=6;i++)
		{
			
			soft.assertEquals(arr1[j], arr2[k],"Failed: Both result are different");
			
			j++;
			k++;
			
		}
		soft.assertAll();
		create_Lead.scrollpage(driver);
		create_Lead.clickOnSavebtn();
		
	}
	
	//Mass update cases assigned to Rahul Thakre
	@Test( groups= {"MassUpdate", "Sanity"})
	public void MassUpdateCaseModule() throws InterruptedException, EncryptedDocumentException, IOException, AWTException, ParseException
	{
		 	//String Subject = "HDFC Finance - Service Request"; 
	 	
		 	CommonFunctions.MassUpdateCaseModule(list_View, "Cases", "HDFC Finance - Service Request");
	
	}
	
	
	//Test case for Export functionality
	@Test( groups = {"Export", "Sanity"})
	public void ExportRecordsForCases() throws Exception
	{
		//Create this test case in Extent Report
		test= extent.createTest( "Export functionality for Cases.").assignAuthor("Komal")
				.assignCategory("Functional Test Case").assignDevice("Chrome");
		
		String input1="";
		//Filter the Document
		Thread.sleep(5000);
		
		 list_View.clickOnFilter(driver,input1,UtilityClass.fetchDataFromExcelSheet("Cases",1, 9),"Case");
		Thread.sleep(5000);
		list_View.ExportAllRecords(driver, UtilityClass.fetchDataFromExcelSheet("Cases",1, 9), "Cases");
	}
	
}
