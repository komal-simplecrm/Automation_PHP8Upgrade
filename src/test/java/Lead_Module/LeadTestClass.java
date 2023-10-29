package Lead_Module;


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
import Login_Page.Login_Page;
import POM_Lead_Module.Add_Lead;
import POM_Lead_Module.Duplicate_Page;
import POM_Lead_Module.Lead_ListView;
import POM_Opportunities_Module.Add_Opportunities;





public class LeadTestClass extends Base_Class
{
	
	Login_Page login;
	Dashboard dashboard;
	Add_Lead create_Lead;
	Lead_ListView list_View;
	Duplicate_Page duplicate;
	Add_Opportunities add_Opportunities;
	
	@BeforeMethod(alwaysRun = true)
	public void OpenBrowser() throws IOException, InterruptedException 
	{
		
	
		//Create object of Pom classes
		dashboard=new Dashboard(driver);
		create_Lead=new Add_Lead(driver);
		list_View =new Lead_ListView(driver);
		duplicate=new Duplicate_Page(driver);
		add_Opportunities= new Add_Opportunities(driver);
		
		//directed to the dash board page
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Thread.sleep(2000);
		dashboard.clickOnMenuOption();
		dashboard.clickOnSearch("Leads");
		Thread.sleep(4000);
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		dashboard.clickOnMenuOption(driver,"Leads");
		dashboard.closeMenuOption();
		Thread.sleep(4000);
	}
	
	//Create test case for add multiple records in Lead module
	@Test(groups={"Create", "Sanity"})
	public void CreateLead() throws EncryptedDocumentException, IOException, InterruptedException 
	{
		//Create this test case in Extent Report
		 test= extent.createTest("Create Leads").assignAuthor("Komal")
				.assignCategory("Functional Test Case").assignDevice();
		
		 test.info("Create multiple Leads");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		
		
		for(int i=1;i<=6;i++)
		{
			list_View.clickOnAddButton();
			SoftAssert soft=new SoftAssert();
		try {
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		create_Lead.selectSalutation(driver,UtilityClass.fetchDataFromExcelSheet("Leads",i, 0));
		create_Lead.enterFirstName(UtilityClass.fetchDataFromExcelSheet("Leads",i, 1));
		String S=create_Lead.enterLastName(driver,UtilityClass.fetchDataFromExcelSheet("Leads",i, 2));
		soft.assertNotNull(S);
		
		create_Lead.selectType(driver,UtilityClass.fetchDataFromExcelSheet("Leads",i, 3));
		create_Lead.enterOfficePhone(UtilityClass.fetchDataFromExcelSheet("Leads",i, 4));
		create_Lead.enterTitle(UtilityClass.fetchDataFromExcelSheet("Leads",i, 5));
		create_Lead.enterMobile(UtilityClass.fetchDataFromExcelSheet("Leads",i, 6));
		create_Lead.enterDepartment(UtilityClass.fetchDataFromExcelSheet("Leads",i, 7));
		create_Lead.selectLoanType(driver,UtilityClass.fetchDataFromExcelSheet("Leads",i, 8));
		create_Lead.enterAccountName(UtilityClass.fetchDataFromExcelSheet("Leads",i, 9));
		create_Lead.enterWebsite(UtilityClass.fetchDataFromExcelSheet("Leads",i, 10));
		create_Lead.selectStatus(driver,UtilityClass.fetchDataFromExcelSheet("Leads",i, 11));
		create_Lead.selectApprovalStatus(driver,UtilityClass.fetchDataFromExcelSheet("Leads",i, 12));
		//Scroll down page 
		create_Lead.ScrollPage(driver);
		create_Lead.enterAddress(UtilityClass.fetchDataFromExcelSheet("Leads",i, 13));
		
		create_Lead.enterState(UtilityClass.fetchDataFromExcelSheet("Leads",i, 14));
		create_Lead.enterPostalCode(UtilityClass.fetchDataFromExcelSheet("Leads",i, 15));
		create_Lead.enterCountry(UtilityClass.fetchDataFromExcelSheet("Leads",i, 16));
		create_Lead.enterCity(UtilityClass.fetchDataFromExcelSheet("Leads",i, 17));
		create_Lead.AddressCopyFromLeft();
		String S1=create_Lead.enterEmailAddress(driver,UtilityClass.fetchDataFromExcelSheet("Leads",i, 18));
		soft.assertNotNull(S1);
		soft.assertAll();
		create_Lead.ScrollPageUptocity(driver);
		create_Lead.clickOnConvertedRedioBtn();
		create_Lead.enterDescription(UtilityClass.fetchDataFromExcelSheet("Leads",i, 19));
		create_Lead.enterFax(UtilityClass.fetchDataFromExcelSheet("Leads",i, 20));
		create_Lead.enterUTM_URL(UtilityClass.fetchDataFromExcelSheet("Leads",i, 21));
		create_Lead.enterPartner_Contact(driver,UtilityClass.fetchDataFromExcelSheet("Leads",i, 22));
		create_Lead.enterUTM_Campaign(UtilityClass.fetchDataFromExcelSheet("Leads",i, 23));
		Thread.sleep(2000);
		create_Lead.scrolluptoUTMURL(driver);
		
		create_Lead.selectLeadSource(driver,UtilityClass.fetchDataFromExcelSheet("Leads",i, 24));
		Thread.sleep(2000);
		create_Lead.enterStatus_Description(UtilityClass.fetchDataFromExcelSheet("Leads",i, 25));
		create_Lead.enterLead_Source_Description(UtilityClass.fetchDataFromExcelSheet("Leads",i, 26));
		create_Lead.enterOpportunity_Amount(UtilityClass.fetchDataFromExcelSheet("Leads",i, 27));
		create_Lead.enterRefered_by(UtilityClass.fetchDataFromExcelSheet("Leads",i, 28));
		//create_Lead.enterCampaign(driver,UtilityClass.fetchDataFromExcelSheet("Leads",i, 29));
		create_Lead.scrollpage(driver);
		Thread.sleep(2000);
		create_Lead.AssignedTo(driver, UtilityClass.fetchDataFromExcelSheet("Leads",i, 34));
		create_Lead.clickOnSavebtn();
		test.info("New Lead " +S+ " is Created");
		
		Thread.sleep(7000);
		add_Opportunities.backToListView();
		Thread.sleep(4000);
		list_View.scrolluptoAddBtn(driver);
		}
		catch(NullPointerException e) {
			System.out.println("NullPointerException thrown!");
			
		}
		
		
	}
		
		
		//Assert.fail();
	}
	
	//Test case for Edit functionality
	@Test(groups={"Edit", "Sanity"})
	public void EditLead() throws EncryptedDocumentException, IOException, InterruptedException
	{
		//Create this test case in Extent Report
		test= extent.createTest("Edit Lead").assignAuthor("Komal")
				.assignCategory("Functional Test Case").assignDevice("Chrome");
		
		test.info("User should Edit the Lead");
		
		String input1="John";
		list_View.enterTextInSearchBtn(driver,input1);
		Thread.sleep(5000);
		//String LeadName=list_View.verfyLeadName(driver, input1);
		//if(LeadName.equals(input1))
		//{
			list_View.clickOnName(driver,input1);
			Thread.sleep(2000);
			//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
			list_View.clickOnEditBtn();
			//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			test.info("Last Name is editted");
			create_Lead.enterLastName(driver,UtilityClass.fetchDataFromExcelSheet("Leads",2, 2));
			test.info("Email address is editted");
			create_Lead.enterEmailAddress(driver,UtilityClass.fetchDataFromExcelSheet("Leads",2, 18));
			create_Lead.scrollpage(driver);
			create_Lead.clickOnSavebtn();
			String msg=list_View.EveryPageAlert();
			 test.info(msg+ "Message showing on CRM");
			test.info(input1+ " record is editted");
		/*}else
			{
				System.out.println("Lead Name not matched");
			}*/
		
		}
	
	//Test case for Delete Lead From List View functionality
	@Test(groups={"DeleteFromListView", "Sanity"}, dependsOnMethods={"CreateLead"})
	public void DeleteLeadFromListView() throws InterruptedException
	{
		
		//CommonFunctions.DeleteRecordFromListView(list_View, add_Opportunities, "John");
		//This above function is not working for Lead module because,John is not matched with Mr. John Kaif.
		
		
		//Create this test case in Extent Report
		test= extent.createTest("Delete Lead From List View In Leads Module").assignAuthor("Komal")
				.assignCategory("Functional Test Case").assignDevice("Chrome");
		
		test.info("User should delete the Lead from List View");
		
		String input1="Komal";
		list_View.enterTextInSearchBtn(driver,input1);
		Thread.sleep(5000);
		//String LeadName=list_View.verfyLeadName(driver, input1);
		//if(LeadName.equals(input1))
		//{
			list_View.selectCheckBox(driver,input1);
			list_View.clickOnDelete();
			
			String msg=list_View.EveryPageAlert();
			 test.info(msg+ " Message showing on CRM");
			test.info(input1+ "Record is Deleted");
		/*}else
			{
				System.out.println("Lead Name not matched");
			}*/
		
	}
	
	//Test case for Delete From Edit Option functionality
	@Test( groups={"DeleteFromEditView", "Sanity"}, dependsOnMethods={"CreateLead"})
	public void DeleteFromEditOption() throws InterruptedException
	{
		//Create this test case in Extent Report
		test= extent.createTest("Delete From Edit Option In Leads Module").assignAuthor("Komal")
				.assignCategory("Functional Test Case").assignDevice("Chrome");
		
		test.info("User should delete the Lead from Edit Option");
		
		String input1="Sona";
		list_View.enterTextInSearchBtn(driver,input1);
		Thread.sleep(5000);
		//String LeadName=list_View.verfyLeadName(driver, input1);
		//if(LeadName.equals(input1))
		//{
			list_View.clickOnName(driver,input1);
			Thread.sleep(2000);
			list_View.menu(driver,"DELETE");
			list_View.confirmDelete();
			String msg=list_View.EveryPageAlert();
			 test.info(msg+ " Message showing on CRM");
			test.info(input1+ "Record is Deleted");
		/*}
		else
		{
			System.out.println("Lead Name not matched");
		}*/
	}
	
	//Test case for Verify record us duplicate functionality
	@Test(groups={"Duplicate"})
	public void DuplicateLead() throws InterruptedException, EncryptedDocumentException, IOException
	{	
		//Create this test case in Extent Report
		test= extent.createTest("Verify Lead Is Duplicate?").assignAuthor("Komal")
				.assignCategory("Functional Test Case").assignDevice("Chrome");
		
		test.info("Verify lead is duplicate or not");
		SoftAssert soft=new SoftAssert();
		
		String input1="John Kaif";
		list_View.enterTextInSearchBtn(driver,input1);
		Thread.sleep(5000);
		list_View.clickOnName(driver,input1);
		Thread.sleep(2000);
		list_View.menu(driver,"DUPLICATE");
		int j=0,k=0;
		String arr1[]= {duplicate.getSalutation(),duplicate.getFirstName(),duplicate.getLastName(),duplicate.getType(),duplicate.getOfficeNumber(),
				duplicate.getTitle(),duplicate.getMobile(), duplicate.getDepartment(),duplicate.getLoanType(),duplicate.getAccountName(),duplicate.getWebsite(),
				duplicate.getStatus(),duplicate.getApprovalStatus(),duplicate.getAddress(),duplicate.getState(),duplicate.getPostalCode(),
				duplicate.getCountry(),duplicate.getCity(),duplicate.getEmailAddress(),duplicate.getDescription(),duplicate.getFax(),
				duplicate.getUTM_URL(),duplicate.getPartner_Contacts(),duplicate.getUTM_Campaign(),duplicate.getLeadSource(),duplicate.getStatus_Description(),duplicate.getLead_Source_Description(),
				duplicate.getOpportunity_Amount(),duplicate.getRefered_by(),/*duplicate.getCampaign()*/};
		String arr2[]= {UtilityClass.fetchDataFromExcelSheet("Leads",1, 0),UtilityClass.fetchDataFromExcelSheet("Leads",1, 1),UtilityClass.fetchDataFromExcelSheet("Leads",1, 2),
				UtilityClass.fetchDataFromExcelSheet("Leads",1, 3),UtilityClass.fetchDataFromExcelSheet("Leads",1, 4),UtilityClass.fetchDataFromExcelSheet("Leads",1, 5),
				UtilityClass.fetchDataFromExcelSheet("Leads",1, 6),UtilityClass.fetchDataFromExcelSheet("Leads",1, 7),UtilityClass.fetchDataFromExcelSheet("Leads",1, 8),
				UtilityClass.fetchDataFromExcelSheet("Leads",1, 9),UtilityClass.fetchDataFromExcelSheet("Leads",1, 10),UtilityClass.fetchDataFromExcelSheet("Leads",1, 11),
				UtilityClass.fetchDataFromExcelSheet("Leads",1, 12),UtilityClass.fetchDataFromExcelSheet("Leads",1, 13),UtilityClass.fetchDataFromExcelSheet("Leads",1, 14),
				UtilityClass.fetchDataFromExcelSheet("Leads",1, 15),UtilityClass.fetchDataFromExcelSheet("Leads",1, 16),UtilityClass.fetchDataFromExcelSheet("Leads",1, 17),
				UtilityClass.fetchDataFromExcelSheet("Leads",1, 18),UtilityClass.fetchDataFromExcelSheet("Leads",1, 19),UtilityClass.fetchDataFromExcelSheet("Leads",1, 20),
				UtilityClass.fetchDataFromExcelSheet("Leads",1, 21),UtilityClass.fetchDataFromExcelSheet("Leads",1, 22),UtilityClass.fetchDataFromExcelSheet("Leads",1, 23),UtilityClass.fetchDataFromExcelSheet("Leads",1, 24),
				UtilityClass.fetchDataFromExcelSheet("Leads",1, 25),UtilityClass.fetchDataFromExcelSheet("Leads",1, 26),UtilityClass.fetchDataFromExcelSheet("Leads",1, 27),
				UtilityClass.fetchDataFromExcelSheet("Leads",1, 28),/*UtilityClass.fetchDataFromExcelSheet("Leads",1, 29)*/};
		
		for(int i=0;i<29;i++)
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
	
	//Test case for In this if copy from left check is selected then verify both the address is same else print the Alternate address.
	@Test(groups={"alternteAddress"})
	public void alternteAddress() throws InterruptedException, EncryptedDocumentException, IOException, AWTException
	{
		CommonFunctions.alternteAddress(dashboard,list_View, duplicate, "John Kaif");
		
	}
	
	//Test case for Email setting 1st email id by default primary, second Opted out should be visible in detail view and 3rd email id invalid should not be displayed in detail view
	@Test(groups={"EmailSetting", "Sanity"})
	public void EmailSetting() throws EncryptedDocumentException, InterruptedException, IOException
	{
		
		CommonFunctions.EmailSetting(list_View, create_Lead,add_Opportunities, "Leads", 2, 18, 30);
			
	}
	
	//Test case for In this if copy from left check is selected then verify both the address is same else print the Alternate address.
	@Test(enabled = false,groups={"MultipleEmailAddress", "Sanity"})
	public void MultipleEmailAddress() throws InterruptedException, EncryptedDocumentException, IOException
	{
		CommonFunctions.MultipleEmailAddress(list_View, create_Lead, add_Opportunities, "Leads", 2, 1, 18, 30);
		
	}
	
	//Test case for Export functionality
	@Test( groups = {"Export", "Sanity"})
	public void ExportRecordsForLeads() throws Exception
	{
		 CommonFunctions.ExportRecords(list_View, "John Kaif", "Leads");
	}
	
	//Test case for Mass update functionality
	@Test( groups={"MassUpdate", "Sanity"})
	public void MassUpdateLeads() throws InterruptedException, EncryptedDocumentException, IOException, AWTException, ParseException
	{
		
		CommonFunctions.MassUpdateCaseModule(list_View, "Leads", "Shreysh Daut");
		
	}

}
