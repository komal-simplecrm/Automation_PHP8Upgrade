package Contacts_Module;

import java.awt.AWTException;
import java.io.IOException;
import java.text.ParseException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Lead_Module.LeadTestClass;
import Library_Files.Base_Class;
import Library_Files.CommonFunctions;
import Library_Files.UtilityClass;
import Login_Page.Dashboard;
import Login_Page.Login_Page;
import POM_Account_Module.Add_Account;
import POM_Contacts_Module.Add_Contacts;
import POM_Filter.FilterData;
import POM_Lead_Module.Add_Lead;
import POM_Lead_Module.Duplicate_Page;
import POM_Lead_Module.Lead_ListView;
import POM_Opportunities_Module.Add_Opportunities;

public class ContactsTestClass extends Base_Class
{
	Login_Page login;
	Dashboard dashboard;
	Add_Lead create_Lead;
	Lead_ListView list_View;
	Add_Contacts contacts;
	Add_Opportunities add_Opportunities;
	Duplicate_Page duplicate;
	LeadTestClass LeadClass;
	Add_Account add_Account;
	FilterData filter_data;
	
	@BeforeMethod(alwaysRun = true)
	public void OpenBrowser() throws IOException, InterruptedException 
	{
		
		
		//Create object of POM classes
		dashboard=new Dashboard(driver);
		create_Lead=new Add_Lead(driver);
		list_View =new Lead_ListView(driver);
		contacts = new Add_Contacts(driver);
		add_Opportunities= new Add_Opportunities(driver);
		duplicate=new Duplicate_Page(driver);
		LeadClass = new LeadTestClass();
		add_Account = new Add_Account(driver);
		filter_data = new FilterData(driver);
		
		//directed to the dash board page
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Thread.sleep(2000);
		dashboard.clickOnMenuOption();
		dashboard.clickOnSearch("Contacts");
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Thread.sleep(4000);
		dashboard.clickOnMenuOption(driver,"Contacts");
		//dashboard.clickOnMenuOption();
		Thread.sleep(2000);
		dashboard.closeMenuOption();
		Thread.sleep(7000);
	}
	
	
	@Test( groups={"Create", "Sanity"})
	public void CreateContacts() throws EncryptedDocumentException, IOException, InterruptedException 
	{
		//Create this test case in Extent Report
		 test= extent.createTest("Create Contacts").assignAuthor("Komal")
				.assignCategory("Functional Test Case").assignDevice();
		
		 test.info("Create multiple Contacts");
		
		for(int i=1;i<=6;i++)
		{
			
			SoftAssert soft=new SoftAssert();
		try {
			Thread.sleep(5000);
			list_View.clickOnAddButton();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		
		create_Lead.enterFirstName(UtilityClass.fetchDataFromExcelSheet("Contacts",i, 0));
		String S=create_Lead.enterLastName(driver,UtilityClass.fetchDataFromExcelSheet("Contacts",i, 1));
		soft.assertNotNull(S);
		test.info("New Contact " +S+ " is Created");
		create_Lead.enterOfficePhone(UtilityClass.fetchDataFromExcelSheet("Contacts",i, 2));
		create_Lead.enterMobile(UtilityClass.fetchDataFromExcelSheet("Contacts",i, 3));
		create_Lead.enterTitle(UtilityClass.fetchDataFromExcelSheet("Contacts",i, 4));
		create_Lead.enterDepartment(UtilityClass.fetchDataFromExcelSheet("Contacts",i, 5));
		add_Opportunities.enterAccountName(driver, UtilityClass.fetchDataFromExcelSheet("Contacts",i, 6));
		create_Lead.enterFax(UtilityClass.fetchDataFromExcelSheet("Contacts",i, 7));
		String S1=create_Lead.enterEmailAddress(driver,UtilityClass.fetchDataFromExcelSheet("Contacts",i, 8));
		soft.assertNotNull(S1);
		soft.assertAll();
		contacts.enterTwitterHandle(UtilityClass.fetchDataFromExcelSheet("Contacts",i, 9));
		create_Lead.scrolluptoDescription(driver);
		create_Lead.enterAddress(UtilityClass.fetchDataFromExcelSheet("Contacts",i, 10));
		create_Lead.enterState(UtilityClass.fetchDataFromExcelSheet("Contacts",i, 11));
		create_Lead.enterPostalCode(UtilityClass.fetchDataFromExcelSheet("Contacts",i, 12));
		create_Lead.enterCountry(UtilityClass.fetchDataFromExcelSheet("Contacts",i, 13));
		create_Lead.enterCity(UtilityClass.fetchDataFromExcelSheet("Contacts",i, 14));
		create_Lead.AddressCopyFromLeft();
		create_Lead.scrollpage(driver);
		create_Lead.enterDescription(UtilityClass.fetchDataFromExcelSheet("Contacts",i, 15));
		create_Lead.AssignedTo(driver, UtilityClass.fetchDataFromExcelSheet("Contacts",i, 16));
		create_Lead.scrollpage(driver);
		create_Lead.selectLeadSource(driver, UtilityClass.fetchDataFromExcelSheet("Contacts",i, 17));
		contacts.enterUserName(UtilityClass.fetchDataFromExcelSheet("Contacts",i, 18));
		contacts.enterPassword(UtilityClass.fetchDataFromExcelSheet("Contacts",i, 19));
		
		contacts.enterReportsTo(driver, UtilityClass.fetchDataFromExcelSheet("Contacts",i, 20));
		//create_Lead.enterCampaign(driver, UtilityClass.fetchDataFromExcelSheet("Contacts",i, 21));
		//contacts.Portal_active_userCheckBox();
		create_Lead.clickOnSavebtn();
		Thread.sleep(7000);
		add_Opportunities.backToListView();
		Thread.sleep(4000);
		list_View.scrolluptoAddBtn(driver);
		
		}
		catch(NullPointerException e) {
			System.out.println("NullPointerException thrown!");
	
		}
	
		}
	
	}	
	
	//Create test case for Edit Account
	@Test( groups={"Edit", "Sanity"})
	public void EditContacts() throws InterruptedException, EncryptedDocumentException, IOException 
	{
			//Create this test case in Extent Report
			test= extent.createTest("Edit Contacts").assignAuthor("Komal")
								.assignCategory("Functional Test Case").assignDevice("Chrome");
						
			test.info("User should Edit Contacts");
			String input1="John Kaif";
			list_View.enterTextInSearchBtn(driver,input1);
			Thread.sleep(5000);
						 	
			String OppName=add_Opportunities.verifyOpportunityName(driver, input1);
			if(OppName.equals(input1))
			{
								
				list_View.clickOnName(driver,input1);
				list_View.clickOnEditBtn();
															
				create_Lead.enterFirstName(UtilityClass.fetchDataFromExcelSheet("Contacts",2, 0));
				create_Lead.enterEmailAddress(driver, UtilityClass.fetchDataFromExcelSheet("Contacts",2,8));
				create_Lead.scrollpage(driver);
				create_Lead.clickOnSavebtn();
				String msg=list_View.EveryPageAlert();
				 test.info(msg+ "Message showing on CRM");
				test.info(input1+ " record is updated");
				
			}else
			{
					System.out.println("Account Name not matched");
					test.info(input1+ " record is not matched");
			}
				 
	}
	
	//Create test case for Delete Contact From Edit Option
	@Test( groups = {"DeleteFromEditView", "Sanity"}, dependsOnMethods={"CreateContacts"})
	public void DeleteContactFromEditOption() throws Exception 
	{
		
		CommonFunctions.DeleteRecordFromEditOption(dashboard, list_View, add_Opportunities, "Sona Patel","Contacts");
					
	}
	
	//Create test case for Delete Contact From Edit Option
	@Test(groups = {"DeleteFromListView", "Sanity"}, dependsOnMethods={"CreateContacts"})
	public void DeleteContactFromListView() throws InterruptedException, EncryptedDocumentException, IOException 
	{
		CommonFunctions.DeleteRecordFromListView(list_View, add_Opportunities, "Komal Kolhe", "Contacts");
						
	}
	
	//Test case for Verify record us duplicate functionality
		@Test( groups={"Duplicate"})
		public void DuplicateContact() throws InterruptedException, EncryptedDocumentException, IOException, AWTException
		{	
			//Create this test case in Extent Report
			test= extent.createTest("Verify Contact Is Duplicate?").assignAuthor("Komal")
					.assignCategory("Functional Test Case").assignDevice("Chrome");
			
			test.info("Verify contact is duplicate or not");
			SoftAssert soft=new SoftAssert();
			/*System.out.println("Enter LeadName: ");
			Scanner scan1 = new Scanner(System.in);
			String input1 = scan1.nextLine();*/
			String input1="Shreya Daut";
			list_View.enterTextInSearchBtn(driver,input1);
			list_View.clickOnName(driver,input1);
			Thread.sleep(5000);
			//UtilityClass.ZoomIn();
			dashboard.clickOnMenuOption();
			Thread.sleep(2000);
			list_View.menu(driver,"DUPLICATE");
			//UtilityClass.ZoomOut();
			int j=0,k=0;
			String arr1[]= {duplicate.getFirstName(),duplicate.getLastName(),duplicate.getOfficeNumber(),duplicate.getMobile(),
					duplicate.getTitle(), duplicate.getDepartment(),duplicate.getAccountName(),duplicate.getFax(),duplicate.getEmailAddress(),
					contacts.getTwitterHandle(),duplicate.getAddress(),duplicate.getState(),duplicate.getPostalCode(),duplicate.getCountry(),
					duplicate.getCity(),duplicate.getDescription(),duplicate.getLeadSource(),contacts.getUserName(),contacts.getPassword(),contacts.getReportsTo(),/*duplicate.getCampaign()*/};
			String arr2[]= {UtilityClass.fetchDataFromExcelSheet("Contacts",4, 0),UtilityClass.fetchDataFromExcelSheet("Contacts",4, 1),UtilityClass.fetchDataFromExcelSheet("Contacts",4, 2),
					UtilityClass.fetchDataFromExcelSheet("Contacts",4, 3),UtilityClass.fetchDataFromExcelSheet("Contacts",4, 4),UtilityClass.fetchDataFromExcelSheet("Contacts",4, 5),
					UtilityClass.fetchDataFromExcelSheet("Contacts",4, 6),UtilityClass.fetchDataFromExcelSheet("Contacts",4, 7),UtilityClass.fetchDataFromExcelSheet("Contacts",4, 8),
					UtilityClass.fetchDataFromExcelSheet("Contacts",4, 9),UtilityClass.fetchDataFromExcelSheet("Contacts",4, 10),UtilityClass.fetchDataFromExcelSheet("Contacts",4, 11),
					UtilityClass.fetchDataFromExcelSheet("Contacts",4, 12),UtilityClass.fetchDataFromExcelSheet("Contacts",4, 13),UtilityClass.fetchDataFromExcelSheet("Contacts",4, 14),
					UtilityClass.fetchDataFromExcelSheet("Contacts",4, 15),UtilityClass.fetchDataFromExcelSheet("Contacts",4, 17),UtilityClass.fetchDataFromExcelSheet("Contacts",4, 18),
					UtilityClass.fetchDataFromExcelSheet("Contacts",4, 19),UtilityClass.fetchDataFromExcelSheet("Contacts",4, 20),/*UtilityClass.fetchDataFromExcelSheet("Contacts",4, 21)*/};
			
			for(int i=0;i<=19;i++)
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
			CommonFunctions.alternteAddress(dashboard,list_View, duplicate, "John Kaif");
			
		}
		
		
		//Test case for In this if copy from left check is selected then verify both the address is same else print the Alternate address.
		@Test( groups={"MultipleEmailAddress", "Sanity"})
		public void MultipleEmailAddress() throws InterruptedException, EncryptedDocumentException, IOException
		{
			CommonFunctions.MultipleEmailAddress(list_View, create_Lead, add_Opportunities, "Contacts", 1, 0, 8, 22);
			
		}
		
		
		//Test case for Email setting 1st email id by default primary, second Opted out should be visible in detail view and 3rd email id invalid should not be displayed in detail view
		@Test( groups={"EmailSetting", "Sanity"})
		public void EmailSetting() throws EncryptedDocumentException, InterruptedException, IOException
		{
			CommonFunctions.EmailSetting(list_View, create_Lead, add_Opportunities, "Contacts", 1, 8, 22);
				
		}
		
		//Test case for Export functionality
		@Test( groups = {"Export", "Sanity"})
		public void ExportRecordsForContacts() throws Exception
		{
			//Call function from Common Function class
			CommonFunctions.ExportRecords(list_View, "Jimy Fernández", "Contacts");
		}
		
		//Test case for Mass update functionality
		@Test(groups={"MassUpdate", "Sanity"})
		public void MassUpdateContacts() throws InterruptedException, EncryptedDocumentException, IOException, AWTException, ParseException
		{
			
			CommonFunctions.MassUpdateCaseModule(list_View, "Contacts", "Jimy Fernández");
			
		}
		
		
		
		
		@Test(enabled= false, groups = {"Filter"})
		public void FilterOnContactsModule() throws Exception
		{
			//Create this test case in Extent Report
			 test= extent.createTest("Filter On Contacts Module").assignAuthor("Komal")
					.assignCategory("Functional Test Case").assignDevice("Chrome");
			
			 test.info("Filter On Contacts Module");
			//list_View.clickOnAddButton();
			 SoftAssert soft=new SoftAssert();
			try {
					
				/*create_Lead.enterFirstName(UtilityClass.fetchDataFromExcelSheet("Filter",5, 0));
				String S=create_Lead.enterLastName(driver,UtilityClass.fetchDataFromExcelSheet("Filter",5, 3));
				soft.assertNotNull(S);
			
				create_Lead.enterOfficePhone(UtilityClass.fetchDataFromExcelSheet("Filter",5, 2));
				
			
				
				add_Opportunities.enterAccountName(driver, UtilityClass.fetchDataFromExcelSheet("Filter",5, 6));
				
				String S1=create_Lead.enterEmailAddress(driver,UtilityClass.fetchDataFromExcelSheet("Filter",5, 1));
				soft.assertNotNull(S1);
				soft.assertAll();
				
				create_Lead.scrolluptoDescription(driver);
				create_Lead.enterAddress(UtilityClass.fetchDataFromExcelSheet("Filter",5, 4));
				create_Lead.enterState(UtilityClass.fetchDataFromExcelSheet("Filter",5, 8));
				create_Lead.enterPostalCode(UtilityClass.fetchDataFromExcelSheet("Filter",5, 9));
				create_Lead.enterCountry(UtilityClass.fetchDataFromExcelSheet("Filter",5, 11));
				create_Lead.enterCity(UtilityClass.fetchDataFromExcelSheet("Filter",5, 5));
				create_Lead.AddressCopyFromLeft();
				create_Lead.scrolluptoDescription(driver);
				
				create_Lead.AssignedTo(driver, UtilityClass.fetchDataFromExcelSheet("Filter",5, 10));
				create_Lead.selectLeadSource(driver, UtilityClass.fetchDataFromExcelSheet("Filter",5, 12));
				
				create_Lead.scrollpage(driver);
				
				create_Lead.clickOnSavebtn();
				Thread.sleep(7000);
				add_Opportunities.backToListView();
				Thread.sleep(4000);
				list_View.scrolluptoAddBtn(driver);*/
					//Click on Filter
					//Pass Test data from directly excel sheet , row index of the test data, and row index of column index 
					//Pass row index of column index to compare the Column label and the label on the filter and then perform functionality on that
					list_View.ClickOnApplyFilter();
					//filter_data.getTextFieldFromFilter(driver,UtilityClass.fetchDataFromExcelSheet("Filter", 1, 0),1, 0);
					
					filter_data.typeOfField(driver, 5, 4);
					//filter_data.getListViewData("Shipping Street","null");
					//filter_data.EnumOnFilter(driver, "null", 1, 0, 0);
					soft.assertAll();
										
				}
			 		catch(NullPointerException e) {
					System.out.println("NullPointerException thrown!");
					
				}
					
			 
		}
		
		
		
}	
	
	
	
	
	
	


