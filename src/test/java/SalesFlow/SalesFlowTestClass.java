package SalesFlow;

import java.io.IOException;
import java.time.Duration;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Library_Files.Base_Class;
import Library_Files.CommonFunctions;
import Library_Files.UtilityClass;
import Login_Page.Dashboard;
import Login_Page.Login_Page;
import POM_Account_Module.Add_Account;
import POM_Contacts_Module.Add_Contacts;
import POM_Lead_Module.Add_Lead;
import POM_Lead_Module.Lead_ListView;
import POM_Notes_Module.Add_Notes;
import POM_Opportunities_Module.Add_Opportunities;

public class SalesFlowTestClass extends Base_Class
{
	Login_Page login;
	Dashboard dashboard;
	Add_Lead create_Lead;
	Lead_ListView list_View;
	Add_Opportunities add_Opportunities;
	Add_Contacts contacts; 
	Add_Account add_Account;
	SoftAssert soft;
	Add_Notes notes;
	@BeforeMethod(alwaysRun = true)
	public void OpenBrowser() throws IOException, InterruptedException 
	{
		
		
		//Create object of POM classes
		dashboard=new Dashboard(driver);
		create_Lead=new Add_Lead(driver);
		list_View =new Lead_ListView(driver);
		add_Opportunities= new Add_Opportunities(driver);
		contacts = new Add_Contacts(driver);
		add_Account = new Add_Account(driver);
		soft = new SoftAssert();
		notes = new Add_Notes(driver);
		
		//directed to the dash board page
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Thread.sleep(2000);
		dashboard.clickOnMenuOption();
		dashboard.clickOnSearch("Leads");
		Thread.sleep(4000);
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		dashboard.clickOnMenuOption(driver,"Leads");
		Thread.sleep(2000);
		dashboard.closeMenuOption();
		Thread.sleep(4000);
	}
	
	
	@Test
	public void SalesFlow() throws Exception
	{
		//Create this test case in Extent Report
		 test= extent.createTest("Sales Flow").assignAuthor("Komal")
				.assignCategory("Functional Test Case").assignDevice();
		
		 test.info("Sales Flow started");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		//Create Lead
		list_View.clickOnAddButton();
		String DateTime = CommonFunctions.getCurrentTime();
		String[] sapratename = DateTime.split(" ");
		String CurrentDate = sapratename[0];
		String CurrentTime = sapratename[1];
		create_Lead.enterFirstName(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 1),CurrentTime);
		String lastName = create_Lead.enterLastName(driver,UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 2));
		test.info(lastName+ " lead is created");
		test.info("New Lead " +lastName+ " is Created");
		create_Lead.selectType(driver,UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 3));
		create_Lead.enterLeadScore(UtilityClass.fetchDataFromExcelSheet("Leads",1, 35));
		create_Lead.enterOfficePhone(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 4));
		create_Lead.enterTitle(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 5));
		create_Lead.enterMobile(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 6));
		create_Lead.enterDepartment(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 7));
		create_Lead.enterAccountName(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 9));
		create_Lead.enterWebsite(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 10));
		create_Lead.selectStatus(driver,UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 11));
		create_Lead.selectApprovalStatus(driver,UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 12));
		//Scroll down page 
		create_Lead.ScrollPage(driver);
		create_Lead.enterAddress(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 13));
		
		create_Lead.enterState(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 14));
		create_Lead.enterPostalCode(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 15));
		create_Lead.enterCountry(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 16));
		create_Lead.enterCity(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 17));
		create_Lead.AddressCopyFromLeft();
		String S1=create_Lead.enterEmailAddress(driver,UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 18));
		soft.assertNotNull(S1);
		soft.assertAll();
		create_Lead.ScrollPageUptocity(driver);
		create_Lead.clickOnConvertedRedioBtn();
		create_Lead.enterDescription(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 19));
		//create_Lead.enterFax(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 20));
		//create_Lead.enterUTM_URL(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 21));
		//create_Lead.enterPartner_Contact(driver,UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 22));
		//create_Lead.enterUTM_Campaign(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 23));
		Thread.sleep(2000);
		//create_Lead.scrolluptoUTMURL(driver);
		create_Lead.selectLeadSource(driver,UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 24));
		Thread.sleep(2000);
		create_Lead.enterStatus_Description(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 25));
		create_Lead.enterLead_Source_Description(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 26));
		create_Lead.enterOpportunity_Amount(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 27));
		create_Lead.enterRefered_by(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 28));
		
		create_Lead.scrollpage(driver);
		Thread.sleep(2000);
		create_Lead.AssignedTo(driver, UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 34));
		create_Lead.clickOnSavebtn();
		test.info("Detail view of " +lastName+  " is displayed.");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		test.info("Click on menu options.");
		
		Thread.sleep(4000);
		//scroll up to edit button
		list_View.scrollUptoEditBtn(driver);
		test.info("Click on CONVERT LEAD option.");
		//Click on Convert Lead
		list_View.menu(driver,"CONVERT LEAD");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		//Scroll up to Create Opportunity
		add_Opportunities.scrollUpToCreateOpportuity(driver);
		//get Opportunity Name
		String OpportunitiesName = add_Opportunities.getOpportunitiesName();
		//Create Opportunity
		add_Opportunities.selectSalesFlowCurrency(driver, UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 35));
		add_Opportunities.selectSalesFlowSalesStage(driver, UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 36));
		add_Opportunities.selectSalesFlowOpportunityAmount(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 37));
		add_Opportunities.selectSalesFlowDate_Closed(driver,UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 38));
		add_Opportunities.selectSalesFlowDescription(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 39));
		create_Lead.scrollpage(driver);
		/*test.info("Create Notes.");
		notes.clickonCheckBoxNote();
		notes.scrollUptoNote(driver);
		Thread.sleep(2000);
		notes.enterNoteSubject(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 45));
		Thread.sleep(2000);
		notes.enterNoteDescription(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 46));
		Thread.sleep(2000);
		create_Lead.scrollpage(driver);*/
		test.info("Click on Save Button.");
		create_Lead.clickOnSavebtn();
		Thread.sleep(4000);
		//Get status of Opportunity from detail view
		String status = add_Opportunities.getStatusConverted();
		test.info(lastName+ " status is " +status+ " in Detail View.");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		test.info("Click on menu options.");
		//Diverted towards Opportunities module
		dashboard.clickOnMenuOption();
		Thread.sleep(2000);
		dashboard.clickOnSearch("Opportunities");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		dashboard.clickOnMenuOption(driver,"Opportunities");
		Thread.sleep(2000);
		dashboard.closeMenuOption();
		Thread.sleep(5000);
		list_View.scrolluptoAddBtn(driver);
		//Searched opportunity name by entering in the search field
		list_View.enterTextInSearchBtn(driver,OpportunitiesName);
		Thread.sleep(5000);
		String OppName=add_Opportunities.verifyOpportunityName(driver, OpportunitiesName);
		if(OppName.equals(OpportunitiesName))
		{
			add_Opportunities.clickOnOpp(driver, OpportunitiesName);
			test.info("Open Detail View of "+OpportunitiesName+ " .");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
			test.info("Click on Edit button.");
			list_View.clickOnEditBtn();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
			test.info("Edit the Sales Stage, if Opportunity lost Date Lost and Reason for lost, if Opportunity won Actual Date Closed and Description.");
			//Edit the Sales stages
			add_Opportunities.selectSalesStage(driver, UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 40), 
					UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 41), UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 42), 
					CurrentDate, UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 44));
		
			create_Lead.scrollpage(driver);
			test.info("Click on Save Buuton in Opptiunities Module");
			create_Lead.clickOnSavebtn();
			test.info(OpportunitiesName+ " in Detail View.");
			Thread.sleep(2000);
			//get detail view Sales stage of opportunity
			String SalesStage = add_Opportunities.getDetailViewSalesStageOfOpportunities();
			test.info(OpportunitiesName+ " 's sales stage is " +SalesStage);
			//Diverted towards Contacts module.
			
			Thread.sleep(2000);
			dashboard.clickOnMenuOption();
			Thread.sleep(2000);
			dashboard.clickOnSearch("Contacts");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
			dashboard.clickOnMenuOption(driver,"Contacts");
			Thread.sleep(2000);
			dashboard.closeMenuOption();
			Thread.sleep(5000);
		
			test.info("Contact is searched in contact module.");
			String[] contactname = OpportunitiesName.split(" ");
			String contactFirstName = contactname[0];
			String contactLastName = contactname[1];
			String contactName = contactFirstName + " " + contactLastName;
			list_View.scrolluptoAddBtn(driver);
			//Searched Contacts name by entering in the search field
			list_View.enterTextInSearchBtn(driver,contactName);
			Thread.sleep(5000);
			list_View.clickOnName(driver,contactName);
			//Verify the Contacts Name and Opportunity Name
			String contact = contacts.VerifyContactName();
			soft.assertEquals(contact, OpportunitiesName,"Failed: Contact name and Opportunity name is not matched.");
			test.info(contact+ " contact is created in Contact module.");
			
			//Diverted towards Accounts module.
			
			Thread.sleep(2000);
			dashboard.clickOnMenuOption();
			Thread.sleep(2000);
			dashboard.clickOnSearch("Accounts");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
			dashboard.clickOnMenuOption(driver,"Accounts");
			Thread.sleep(2000);
			dashboard.closeMenuOption();
			Thread.sleep(5000);
			list_View.scrolluptoAddBtn(driver);
			Thread.sleep(5000);
			//Searched Account name by entering in the search field
			list_View.enterTextInSearchBtn(driver,OpportunitiesName);
			Thread.sleep(5000);
			list_View.clickOnName(driver,contactName);
			//Verify the Account Name and Opportunity Name
			String AccountName = contacts.VerifyContactName();
			soft.assertEquals(AccountName, OpportunitiesName,"Failed: Account name and Opportunity name is not matched.");
			//Verify the Contacts Name and Opportunity Name in Opportunity sub-panel
			String oppName = add_Account.VerifyOpportunitySubPanelName(driver,"opportunities");
			System.out.println("oppName is" +oppName);
			System.out.println("OpportunitiesName is" +OpportunitiesName);
			soft.assertEquals(oppName, OpportunitiesName,"Failed: Created Opportunity name is not matched with Opportunity name in opportuniy sub-panel.");
			//Verify the Contacts Name and Opportunity Name in Opportunity sub-panel
			String LeadName = add_Account.VerifyOpportunitySubPanelName(driver,"leads");
			soft.assertEquals(LeadName, contactName,"Failed: Created Lead name is not matched with Lead name in Lead sub-panel.");
			
			System.out.println("LeadName is" +LeadName);
			
			
			soft.assertAll();
			test.info(AccountName+ " account is created in Account module.");
		}
	}
	
}
