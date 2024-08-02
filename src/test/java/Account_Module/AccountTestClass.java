package Account_Module;


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
import POM_Account_Module.Duplicate_Account;
import POM_Filter.FilterData;
import POM_Lead_Module.Add_Lead;
import POM_Lead_Module.Duplicate_Page;
import POM_Lead_Module.Lead_ListView;
import POM_Opportunities_Module.Add_Opportunities;
import POM_Opportunities_Module.Duplicate_Opp_Page;



public class AccountTestClass extends Base_Class
{
	Dashboard dashboard;
	Add_Lead create_Lead;
	Lead_ListView list_View;
	Add_Opportunities add_Opportunities;
	Add_Account add_Account;
	Duplicate_Account duplicate_account;
	Duplicate_Opp_Page duplicate_opp;
	Duplicate_Page duplicate;
	//Filter_Records FR;
	FilterData filter_data;
	SoftAssert soft;
	
	@BeforeMethod(alwaysRun = true)
	public void OpenBrowser() throws InterruptedException
	{
		dashboard = new Dashboard(driver);
		create_Lead = new Add_Lead(driver);
		list_View = new Lead_ListView(driver);
		add_Opportunities = new Add_Opportunities(driver);
		add_Account = new Add_Account(driver);
		duplicate_account = new Duplicate_Account(driver);
		duplicate_opp = new Duplicate_Opp_Page(driver);
		duplicate = new Duplicate_Page(driver);
		filter_data = new FilterData(driver);
		soft = new SoftAssert();
		
		//directed to the dash board page
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//Thread.sleep(2000);
		dashboard.clickOnMenuOption();
		dashboard.clickOnSearch("Accounts");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		//Thread.sleep(4000);
		dashboard.clickOnMenuOption(driver,"Accounts");
		Thread.sleep(2000);
		dashboard.closeMenuOption();
		Thread.sleep(4000);
	}
	
		//Create test case for create multiple Account
		@Test(groups={"Create", "Sanity"})
		public void CreateAccount() throws EncryptedDocumentException, IOException, InterruptedException 
		{
			//Create this test case in Extent Report
			 test= extent.createTest("Create Account").assignAuthor("Komal")
					.assignCategory("Functional Test Case").assignDevice("Chrome");
			
			 test.info("Create multiple Account");
			 for(int i=1;i<=10;i++)
				{
				 	//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
				 Thread.sleep(5000);
				 
				 	list_View.clickOnAddButton();

				try {
					
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
					String name = add_Opportunities.enterName( UtilityClass.fetchDataFromExcelSheet("Accounts",i,0));
					soft.assertNotNull(name);
					
					add_Account.enterOfficePhone(UtilityClass.fetchDataFromExcelSheet("Accounts",i,1));
					create_Lead.enterWebsite(UtilityClass.fetchDataFromExcelSheet("Accounts",i,2));
					create_Lead.enterFax(UtilityClass.fetchDataFromExcelSheet("Accounts",i,3));
					String email=create_Lead.enterEmailAddress(driver, UtilityClass.fetchDataFromExcelSheet("Accounts",i,4));
					soft.assertNotNull(email);
					add_Account.enterTickerSymbol(UtilityClass.fetchDataFromExcelSheet("Accounts",i,5));
					create_Lead.enterDescription(UtilityClass.fetchDataFromExcelSheet("Accounts",i,6));
					add_Account.enterBillingAddress(UtilityClass.fetchDataFromExcelSheet("Accounts",i,7));
					create_Lead.scrollpage(driver);
					add_Account.enterBillingState(UtilityClass.fetchDataFromExcelSheet("Accounts",i,8));
					add_Account.enterBillingPostalCode(UtilityClass.fetchDataFromExcelSheet("Accounts",i,9));
					add_Account.enterBillingCountry(UtilityClass.fetchDataFromExcelSheet("Accounts",i,10));
					add_Account.enterBillingCity(UtilityClass.fetchDataFromExcelSheet("Accounts",i,11));
					add_Account.clickCopyFromleftCheckBox();
					//add_Account.scrollpageCopyFromleftCheckBox(driver);
					add_Account.enterAccountType(driver,UtilityClass.fetchDataFromExcelSheet("Accounts",i,12));
					add_Account.enterIndustry(driver,UtilityClass.fetchDataFromExcelSheet("Accounts",i,13));
					add_Account.enterAnnualRevenue(UtilityClass.fetchDataFromExcelSheet("Accounts",i,14));
					add_Account.enterEmployees(UtilityClass.fetchDataFromExcelSheet("Accounts",i,15));
					//add_Account.enterMemberOf(driver, UtilityClass.fetchDataFromExcelSheet("Accounts",i,16));
					create_Lead.AssignedTo(driver, UtilityClass.fetchDataFromExcelSheet("Accounts",i,17));
					//create_Lead.enterCampaign(driver, UtilityClass.fetchDataFromExcelSheet("Accounts",i,18));
					create_Lead.clickOnSavebtn();
					list_View.getAlertMessage(name);
					//back to list view of Account module
					//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
					Thread.sleep(7000);
					add_Opportunities.backToListView();
					Thread.sleep(4000);
					list_View.scrolluptoAddBtn(driver);
					 
					soft.assertAll();
										
				}
			 		catch(NullPointerException e) {
					System.out.println("NullPointerException thrown!");
					
				}
					
			}
		
		}	
		//Create test case for Edit Account
		@Test(groups={"Edit", "Sanity"})
		public void EditAccount() throws InterruptedException, EncryptedDocumentException, IOException 
		{
					//Create this test case in Extent Report
					 test= extent.createTest("Edit Account").assignAuthor("Komal")
							.assignCategory("Functional Test Case").assignDevice("Chrome");
					
					     test.info("User should Edit Account");
					 	 String input1="HDFC Finance";
					 	list_View.enterTextInSearchBtn(driver,input1);
					 	 Thread.sleep(5000);
					 	
						String OppName=add_Opportunities.verifyOpportunityName(driver, input1);
						if(OppName.equals(input1))
						{
							
							add_Account.clickOnName(driver, input1);
							list_View.clickOnEditBtn();
														
						 add_Opportunities.enterName(UtilityClass.fetchDataFromExcelSheet("Accounts",3,0));
						 create_Lead.enterEmailAddress(driver, UtilityClass.fetchDataFromExcelSheet("Accounts",3,4));
						 create_Lead.scrollpage(driver);
						 create_Lead.clickOnSavebtn();
						 //get the msg 
						 String msg=list_View.EveryPageAlert();
						 test.info(msg+ "Message showing on CRM");
						 test.info(input1+ " record is updated");
						}else
							{
								System.out.println("Account Name not matched");
								test.info(input1+ " record is not matched");
							}
					 
			}

			//Create test case for Delete Account From Edit Option
			@Test( groups = {"DeleteFromEditView", "Sanity"}, dependsOnMethods={"CreateAccount"})
			public void DeleteAccountFromEditOption() throws Exception 
			{
				CommonFunctions.DeleteRecordFromEditOption(dashboard,list_View, add_Opportunities,"ICICI Finance", "Accounts");
							
			}
			
			//Create test case for Delete Account From Edit Option
			@Test( groups = {"DeleteFromListView", "Sanity"}, dependsOnMethods={"CreateAccount"})
			public void DeleteAccountFromListView() throws InterruptedException, EncryptedDocumentException, IOException 
			{
				CommonFunctions.DeleteRecordFromListView(list_View, add_Opportunities, "Axis Finance", "Accounts");
								
			}
			
			@Test( groups= {"Duplicate"})
			public void DuplicateAccount() throws InterruptedException, EncryptedDocumentException, IOException, ParseException, AWTException
			{
				//Create this test case in Extent Report
				 test= extent.createTest("Verify Account Is Duplicate ?").assignAuthor("Komal")
						.assignCategory("Functional Test Case").assignDevice("Chrome");
				
				 test.info("Verify Account is duplicate or not");
				 		
				SoftAssert soft=new SoftAssert();
				String input1="ICICI Finance";
				list_View.enterTextInSearchBtn(driver,input1);
				Thread.sleep(5000);
			
				 	
					String OppName=add_Opportunities.verifyOpportunityName(driver, input1);
					if(OppName.equals(input1))
					{
						
						add_Account.clickOnName(driver, input1);
						//UtilityClass.ZoomIn();
						//dashboard.clickOnMenuOption();
						Thread.sleep(5000);
						list_View.menu(driver,"DUPLICATE");
						//UtilityClass.ZoomOut();
						int j=0,k=0;
						String arr1[]= {duplicate_opp.getOpportunityName(),duplicate_account.getOffice_Phone(),duplicate.getWebsite(),duplicate.getFax(),
								duplicate.getEmailAddress(),duplicate_account.getTickerSymbol(),duplicate.getDescription(),duplicate_account.getBillingAddress(),
								duplicate_account.getBillingState(),duplicate_account.getBillingPostalCode(),duplicate_account.getBillingCountry(),duplicate_account.getBillingCity(),
								duplicate_account.getAccountType(),duplicate_account.getIndustry(),duplicate_account.getAnnualRevenue(),duplicate_account.getEmployees()/*,duplicate_account.getMemberOf()*/,
								/*duplicate.getCampaign()*/};
						
						String arr2[]= {UtilityClass.fetchDataFromExcelSheet("Accounts",2, 0),UtilityClass.fetchDataFromExcelSheet("Accounts",2, 1),UtilityClass.fetchDataFromExcelSheet("Accounts",2, 2),
								UtilityClass.fetchDataFromExcelSheet("Accounts",2, 3),UtilityClass.fetchDataFromExcelSheet("Accounts",2, 4),UtilityClass.fetchDataFromExcelSheet("Accounts",2, 5),
								UtilityClass.fetchDataFromExcelSheet("Accounts",2, 6),UtilityClass.fetchDataFromExcelSheet("Accounts",2, 7),UtilityClass.fetchDataFromExcelSheet("Accounts",2, 8),
								UtilityClass.fetchDataFromExcelSheet("Accounts",2, 9),UtilityClass.fetchDataFromExcelSheet("Accounts",2, 10),UtilityClass.fetchDataFromExcelSheet("Accounts",2, 11),
								UtilityClass.fetchDataFromExcelSheet("Accounts",2, 12),UtilityClass.fetchDataFromExcelSheet("Accounts",2, 13),UtilityClass.fetchDataFromExcelSheet("Accounts",2, 14),
								UtilityClass.fetchDataFromExcelSheet("Accounts",2, 15)/*,UtilityClass.fetchDataFromExcelSheet("Accounts",2, 16),/*UtilityClass.fetchDataFromExcelSheet("Accounts",2, 17)*/};
						
						for(int i=0;i<=14;i++)
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
			
			
			//Test case for In this if copy from left check is selected then verify both the address is same else print the Alternate address.
			@Test( groups={"ShippingAddress"})
			public void ShippingAddress() throws InterruptedException, EncryptedDocumentException, IOException
			{
				CommonFunctions.ShippingAddress(list_View, add_Account, duplicate_account, "Tata Power", "Accounts");
			}
			
			
			//Test case for In this if copy from left check is selected then verify both the address is same else print the Alternate address.
			@Test( groups={"EmailSetting", "Sanity"})
			public void EmailSetting() throws InterruptedException, EncryptedDocumentException, IOException
			{
				
				CommonFunctions.EmailSetting(list_View, create_Lead,add_Opportunities, "Accounts", 0, 4, 19);
				
			}
			
			//Test case for In this if copy from left check is selected then verify both the address is same else print the Alternate address.
			@Test( groups={"MultipleEmailAddress", "Sanity"})
			public void MultipleEmailAddress() throws InterruptedException, EncryptedDocumentException, IOException
			{
				
				CommonFunctions.MultipleEmailAddress(list_View, create_Lead, add_Opportunities, "Accounts", 0, 0, 4, 19);
				
			}
			
			//Test case for Mass update functionality
			@Test( groups={"MassUpdate", "Sanity"})
			public void MassUpdateAccount() throws InterruptedException, EncryptedDocumentException, IOException, AWTException, ParseException
			{
				
				CommonFunctions.MassUpdateCaseModule(list_View, "Accounts", "Axis Finance");
				
			}
			
			//Test case for Export functionality
			@Test( groups = {"Export", "Sanity"})
			public void ExportRecordsForAccounts() throws Exception
			{
							
				 //Call function from Common Function class
				 CommonFunctions.ExportRecords(list_View, "ICICI Finance", "Accounts");
			}
			
			
					
			@Test(enabled = false,  groups = {"Filter"})
			public void FilterOnAccountModule() throws Exception
			{
				//Create this test case in Extent Report
				 test= extent.createTest("Filter On Account Module").assignAuthor("Komal")
						.assignCategory("Functional Test Case").assignDevice("Chrome");
				
				 test.info("Filter On Account Module");
				list_View.clickOnAddButton();
				 SoftAssert soft=new SoftAssert();
				try {
						
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
						String name=add_Opportunities.enterName(UtilityClass.fetchDataFromExcelSheet("Filter",1,0));
						soft.assertNotNull(name);
						
						add_Account.enterOfficePhone(UtilityClass.fetchDataFromExcelSheet("Filter",1,1));
						create_Lead.enterWebsite(UtilityClass.fetchDataFromExcelSheet("Filter",1,2));
						create_Lead.enterFax(UtilityClass.fetchDataFromExcelSheet("Filter",1,3));
						String email=create_Lead.enterEmailAddress(driver, UtilityClass.fetchDataFromExcelSheet("Filter",1,4));
						soft.assertNotNull(email);
						
						add_Account.enterBillingAddress(UtilityClass.fetchDataFromExcelSheet("Filter",1,5));
						add_Account.enterBillingState(UtilityClass.fetchDataFromExcelSheet("Filter",1,6));
						create_Lead.scrollpage(driver);
						add_Account.enterBillingPostalCode(UtilityClass.fetchDataFromExcelSheet("Filter",1,7));
						add_Account.enterBillingCountry(UtilityClass.fetchDataFromExcelSheet("Filter",1,8));
						add_Account.enterBillingCity(UtilityClass.fetchDataFromExcelSheet("Filter",1,9));
						add_Account.clickCopyFromleftCheckBox();
						add_Account.enterAccountType(driver,UtilityClass.fetchDataFromExcelSheet("Accounts",1,12));
						add_Account.enterIndustry(driver,UtilityClass.fetchDataFromExcelSheet("Accounts",1,13));
						create_Lead.scrollpage(driver);
						add_Account.enterAnnualRevenue(UtilityClass.fetchDataFromExcelSheet("Filter",1,12));
						add_Account.enterEmployees(UtilityClass.fetchDataFromExcelSheet("Filter",1,13));
						add_Account.enterMemberOf(driver, UtilityClass.fetchDataFromExcelSheet("Filter",1,14));
						create_Lead.AssignedTo(driver, UtilityClass.fetchDataFromExcelSheet("Accounts",1,17));
						
						create_Lead.clickOnSavebtn();
						
						test.info(name+ " Account is created.");
						
						//back to list view of Account module
						Thread.sleep(7000);
						add_Opportunities.backToListView();
						Thread.sleep(4000);
						list_View.scrolluptoAddBtn(driver);
					
						//Click on Filter
						//Pass Test data from directly excel sheet , row index of the test data, and row index of column index 
						//Pass row index of column index to compare the Column label and the label on the filter and then perform functionality on that
						list_View.ClickOnApplyFilter();
						//filter_data.typeOfField(driver, /*UtilityClass.fetchDataFromExcelSheet("Filter", 1, 0),*/ 1, 0);
						filter_data.SortColumn();
						
						//filter_data.getListViewData("Shipping Street","null");
						//filter_data.EnumOnFilter(driver, "null", 1, 0, 0);
						soft.assertAll();
											
					}
				 		catch(NullPointerException e) {
						System.out.println("NullPointerException thrown!");
						
					}
						
				 
			}
			
			 
			
			
			   
}
			

