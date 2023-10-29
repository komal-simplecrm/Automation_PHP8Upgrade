package Partner_Contacts_Module;

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
import POM_Partner_Contacts_Module.Add_Partner_Contacts;

public class Partner_ContactsTestClass extends Base_Class
{
	Login_Page login;
	Dashboard dashboard;
	Add_Lead create_Lead;
	Lead_ListView list_View;
	Duplicate_Page duplicate;
	Add_Partner_Contacts partner_contacts;
	Add_Opportunities add_Opportunities;
	
	@BeforeMethod(alwaysRun = true)
	public void OpenBrowser() throws IOException, InterruptedException 
	{
		
	
		//Create object of Pom classes
		dashboard = new Dashboard(driver);
		create_Lead = new Add_Lead(driver);
		list_View = new Lead_ListView(driver);
		duplicate = new Duplicate_Page(driver);
		partner_contacts = new Add_Partner_Contacts(driver);
		add_Opportunities = new Add_Opportunities(driver);
		
		//directed to the dash board page
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Thread.sleep(2000);
		dashboard.clickOnMenuOption();
		dashboard.clickOnSearch("Partner Contacts");
		//Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		dashboard.clickOnMenuOption(driver,"Partner Contacts");
		Thread.sleep(2000);
		dashboard.closeMenuOption();
		Thread.sleep(4000);
	}
	
	//Create test case for add multiple records in Lead module
	@Test( groups={"Create", "Sanity"})
	public void CreatePartner_Contacts() throws EncryptedDocumentException, IOException, InterruptedException 
	{
		//Create this test case in Extent Report
			test= extent.createTest("Create Partner Contacts").assignAuthor("Komal")
					.assignCategory("Functional Test Case").assignDevice();
			
			test.info("Create multiple Partner Contacts");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
			
			for(int i=1;i<=6;i++)
			{
				
			SoftAssert soft=new SoftAssert();
		try {
				Thread.sleep(4000);
				list_View.clickOnAddButton();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
				
				create_Lead.enterFirstName(UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",i, 0));
				String lastname = create_Lead.enterLastName(driver,UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",i, 1));
				soft.assertNotNull(lastname);
				test.info("New Partner Contacts " +lastname+ " is Created");
				create_Lead.enterTitle(UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",i, 2));
				create_Lead.enterDepartment(UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",i, 3));
				create_Lead.enterOfficePhone(UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",i, 4));
				create_Lead.enterMobile(UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",i, 5));
				partner_contacts.enterHomePhone(UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",i, 6));
				partner_contacts.enterOtherPhone(UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",i, 7));
	
				partner_contacts.enterPortalUsername(UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",i, 8));
				partner_contacts.enterPortalPassword(UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",i, 9));
	
				partner_contacts.clickPortalActiveCheckBox();
				partner_contacts.clickDoNotCallCheckBox();
				create_Lead.enterFax(UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",i, 10));
				create_Lead.AssignedTo(driver, UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",i, 11));
				create_Lead.enterDescription(UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",i, 12));
				partner_contacts.scrollpageuptoPortalPassword(driver);
				partner_contacts.enterPartners(driver, UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",i, 13));
				String email = create_Lead.enterEmailAddress(driver,UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",i, 14));
				soft.assertNotNull(email);
				soft.assertAll();
				create_Lead.scrollpage(driver);
				create_Lead.enterAddress(UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",i, 15));
				create_Lead.scrollpage(driver);
				create_Lead.enterState(UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",i, 16));
				create_Lead.enterPostalCode(UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",i, 17));
				create_Lead.enterCountry(UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",i, 18));
				create_Lead.enterCity(UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",i, 19));
				create_Lead.AddressCopyFromLeft();
	
	
				create_Lead.clickOnSavebtn();
				Thread.sleep(7000);
				add_Opportunities.backToListView();
				Thread.sleep(4000);
				list_View.scrolluptoAddBtn(driver);
			}
				catch(NullPointerException e) 
				{
					System.out.println("NullPointerException thrown!");
					//create_Lead.clickOnSavebtn();
					//list_View.menu(driver,"CREATE LEADS");
				}
				
			}
	}
	
	
		//Test case for Edit functionality
		@Test(  groups={"Edit", "Sanity"})
		public void EditPartnerContacts() throws EncryptedDocumentException, IOException, InterruptedException
		{
			//Create this test case in Extent Report
			test= extent.createTest("Edit Partner Contacts").assignAuthor("Komal")
					.assignCategory("Functional Test Case").assignDevice("Chrome");
			
			test.info("User should Edit the Partner Contacts");
			
			String input1="Jimy Fernández";
			list_View.enterTextInSearchBtn(driver,input1);
			Thread.sleep(5000);
			String LeadName=list_View.verfyLeadName(driver, input1);
			if(LeadName.equals(input1))
			{
				list_View.clickOnName(driver,input1);
				Thread.sleep(2000);
				//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
				list_View.clickOnEditBtn();
				//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				test.info("Last Name is editted");
				create_Lead.enterLastName(driver,UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",2, 1));
				test.info("Email address is editted");
				create_Lead.enterEmailAddress(driver,UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",2, 14));
				create_Lead.scrollpage(driver);
				create_Lead.clickOnSavebtn();
				String msg=list_View.EveryPageAlert();
				 test.info(msg+ "Message showing on CRM");
				test.info(input1+ " record is editted");
			}else
				{
					System.out.println("Partner Contacts Name not matched");
				}
			
			}
		
	//Create test case for Delete Partner_Contacts From Edit Option
	@Test( groups = {"DeleteFromEditView", "Sanity"}, dependsOnMethods={"CreatePartner_Contacts"})
	public void DeletePartnerContactsFromEditOption() throws Exception 
	{
			CommonFunctions.DeleteRecordFromEditOption(dashboard, list_View, add_Opportunities,"Sona Patel", "Partner_Contacts");		
	}
			
			
	//Create test case for Delete Partner_Contacts From List View
	@Test( groups = {"DeleteFromListView", "Sanity"}, dependsOnMethods={"CreatePartner_Contacts"})
	public void DeletePartnerContactsFromListView() throws InterruptedException, EncryptedDocumentException, IOException 
	{
			CommonFunctions.DeleteRecordFromListView(list_View, add_Opportunities, "Shreysh Daut", "Partner_Contacts");
			
	}
	
	//Test case for Verify record us duplicate functionality
	@Test(  groups={"Duplicate"})
	public void DuplicatePartnerContacts() throws InterruptedException, EncryptedDocumentException, IOException
	{	
		//Create this test case in Extent Report
		test= extent.createTest("Verify PartnerContacts Is Duplicate?").assignAuthor("Komal")
				.assignCategory("Functional Test Case").assignDevice("Chrome");
		
		test.info("Verify Partner Contacts is duplicate or not");
		SoftAssert soft=new SoftAssert();
		
		String input1="John Kaif";
		list_View.enterTextInSearchBtn(driver,input1);
		Thread.sleep(5000);
		list_View.clickOnName(driver,input1);
		Thread.sleep(2000);
		list_View.menu(driver,"DUPLICATE");
		int j=0,k=0;
		String arr1[]= {duplicate.getFirstName(),duplicate.getLastName(),duplicate.getTitle(),duplicate.getDepartment(),duplicate.getOfficeNumber(),
				duplicate.getMobile(),partner_contacts.getHomePhone(),partner_contacts.getOtherPhone(),partner_contacts.getPortalUsername(),
				partner_contacts.getPortalPassword(),duplicate.getFax(),duplicate.getDescription(),partner_contacts.getPartner(),duplicate.getEmailAddress(), 
				duplicate.getAddress(),duplicate.getState(),duplicate.getPostalCode(),duplicate.getCountry(),duplicate.getCity()};
		String arr2[]= {UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",1, 0),UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",1, 1),UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",1, 2),
				UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",1, 3),UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",1, 4),UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",1, 5),
				UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",1, 6),UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",1, 7),UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",1, 8),
				UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",1, 9),UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",1, 10),UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",1, 12),
				UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",1, 13),UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",1, 14),UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",1, 15),
				UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",1, 16),UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",1, 17),UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",1, 18),
				UtilityClass.fetchDataFromExcelSheet("Partner_Contacts",1, 19)};
		
		for(int i=0;i<19;i++)
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
	@Test( groups={"alternteAddress"})
	public void alternteAddress() throws InterruptedException, EncryptedDocumentException, IOException, AWTException
	{
			CommonFunctions.alternteAddress(dashboard, list_View, duplicate, "John Kaif");
			
	}
		
	//Test case for Email setting 1st email id by default primary, second Opted out should be visible in detail view and 3rd email id invalid should not be displayed in detail view
	@Test(  groups={"EmailSetting", "Sanity"})
	public void EmailSetting() throws EncryptedDocumentException, InterruptedException, IOException
	{
		
			CommonFunctions.EmailSetting(list_View, create_Lead, add_Opportunities, "Partner_Contacts", 2, 14, 20);
				
	}
		
	//Test case for In this if copy from left check is selected then verify both the address is same else print the Alternate address.
	@Test( groups={"MultipleEmailAddress", "Sanity"})
	public void MultipleEmailAddress() throws InterruptedException, EncryptedDocumentException, IOException
	{
			CommonFunctions.MultipleEmailAddress(list_View, create_Lead, add_Opportunities, "Partner_Contacts", 2, 1, 14, 20);
			
	}
	
	//Test case for Mass update Partner Contacts Module
	@Test(groups= {"MassUpdate", "Sanity"})
	public void MassUpdatePartnerContactsModule() throws InterruptedException, EncryptedDocumentException, IOException, AWTException, ParseException
	{
	
		CommonFunctions.MassUpdateCaseModule(list_View, "Partner_Contacts", "Manali Kaif");	 	
	
	}
	
	//Test case for Export functionality
	@Test( groups = {"Export", "Sanity"})
	public void ExportRecordsForPartner_Contacts() throws Exception
	{
		 //Call function from Common Function class
		CommonFunctions.ExportRecords(list_View, "Manali Kaif", "Partner Contacts");
	}
}
