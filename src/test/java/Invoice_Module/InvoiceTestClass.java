package Invoice_Module;

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
import POM_Invoice_Module.Add_Invoice;
import POM_Lead_Module.Add_Lead;
import POM_Lead_Module.Duplicate_Page;
import POM_Lead_Module.Lead_ListView;
import POM_Opportunities_Module.Add_Opportunities;
import POM_Opportunities_Module.Duplicate_Opp_Page;
import POM_Quote_Module.Add_Quotes;
import POM_Task_Module.Duplicate_Task_Page;

public class InvoiceTestClass extends Base_Class
{
	Dashboard dashboard;
	Add_Lead create_Lead;
	Lead_ListView list_View;
	Add_Opportunities add_Opportunities;
	Add_Quotes quotes;
	Add_Account add_Account;
	Duplicate_Account duplicate_account;
	Add_Invoice invoice;
	Duplicate_Task_Page duplicate_task;
	Duplicate_Page duplicate;
	Duplicate_Opp_Page duplicate_opp;
	
	@BeforeMethod(alwaysRun = true)
	public void OpenBrowser() throws InterruptedException
	{
		dashboard=new Dashboard(driver);
		create_Lead=new Add_Lead(driver);
		list_View =new Lead_ListView(driver);
		add_Opportunities = new Add_Opportunities(driver);
		quotes = new Add_Quotes(driver);
		add_Account = new Add_Account(driver);
		duplicate_account = new Duplicate_Account(driver);
		invoice = new Add_Invoice(driver);
		duplicate_task = new Duplicate_Task_Page(driver);
		duplicate = new Duplicate_Page(driver);
		
		duplicate_opp = new Duplicate_Opp_Page(driver);
		//directed to the dash board page
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//Thread.sleep(2000);
		dashboard.clickOnMenuOption();
		dashboard.clickOnSearch("Invoices");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		dashboard.clickOnMenuOption(driver,"Invoices");
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		Thread.sleep(5000);
		dashboard.closeMenuOption();
		Thread.sleep(4000);
	}
	
	//Create test case for create multiple Account
		@Test( groups={"Create", "Sanity"})
		public void CreateInvoices() throws Exception 
		{
			//Create this test case in Extent Report
			test= extent.createTest("Create Invoices").assignAuthor("Komal")
							.assignCategory("Functional Test Case").assignDevice("Chrome");
					
			test.info("Create multiple Invoices");
					
			for(int i=1;i<=5;i++)
			{
						 	
				Thread.sleep(5000);
				list_View.clickOnAddButton();
						 
				SoftAssert soft=new SoftAssert();
			try {
							
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
					String quote = add_Opportunities.enterName(UtilityClass.fetchDataFromExcelSheet("Invoices",i,0));
					soft.assertNotNull(quote);
					invoice.enterInvoiceNumber(UtilityClass.fetchDataFromExcelSheet("Invoices",i,1));
					invoice.enterQuoteNumber(UtilityClass.fetchDataFromExcelSheet("Invoices",i,2));
					invoice.selectQuoteDate(driver, UtilityClass.fetchDataFromExcelSheet("Invoices",i,3));
					invoice.selectDueDate(driver, UtilityClass.fetchDataFromExcelSheet("Invoices",i,4));
					invoice.selectInvoiceDate(driver, UtilityClass.fetchDataFromExcelSheet("Invoices",i,5));
					create_Lead.AssignedTo(driver, UtilityClass.fetchDataFromExcelSheet("Invoices",i,6));	
					invoice.selectStatus(driver, UtilityClass.fetchDataFromExcelSheet("Invoices",i,7));
					create_Lead.enterDescription(UtilityClass.fetchDataFromExcelSheet("Invoices",i,8));
					create_Lead.scrolluptoDescription(driver);
					quotes.selectAccount(driver, UtilityClass.fetchDataFromExcelSheet("Invoices",i,9));	
					//
					quotes.selectContact(driver, UtilityClass.fetchDataFromExcelSheet("Invoices",i,10));	
					add_Account.enterBillingAddress(UtilityClass.fetchDataFromExcelSheet("Invoices",i,11));
					add_Account.enterBillingState(UtilityClass.fetchDataFromExcelSheet("Invoices",i,12));
					
					add_Account.enterBillingPostalCode(UtilityClass.fetchDataFromExcelSheet("Invoices",i,13));
					add_Account.enterBillingCountry(UtilityClass.fetchDataFromExcelSheet("Invoices",i,14));
					add_Account.enterBillingCity(UtilityClass.fetchDataFromExcelSheet("Invoices",i,15));
					add_Account.scrollpageBillingCountry(driver);
					add_Account.clickCopyFromleftCheckBox();
					quotes.selectCurrency(driver, UtilityClass.fetchDataFromExcelSheet("Invoices",i,16));	
					quotes.clickOnAddGroup();
					
					quotes.clickOnAddProductLine();
					quotes.enterProductLineQuantity(UtilityClass.fetchDataFromExcelSheet("Invoices",i,17));
					quotes.selectProducts(driver, UtilityClass.fetchDataFromExcelSheet("Invoices",i,18));		
					//quotes.enterProductLineListPrice(UtilityClass.fetchDataFromExcelSheet("Invoices",i,19));	
					quotes.enterProductLineDiscount(UtilityClass.fetchDataFromExcelSheet("Invoices",i,20));	
					quotes.selectProductLineDiscountType(driver, UtilityClass.fetchDataFromExcelSheet("Invoices",i,21));
					quotes.selectProductLineTax(driver, UtilityClass.fetchDataFromExcelSheet("Invoices",i,22));
					quotes.clickOnShowMore();
					quotes.enterDescription(UtilityClass.fetchDataFromExcelSheet("Invoices",i,23));
					invoice.enterProductLineNote(UtilityClass.fetchDataFromExcelSheet("Invoices",i,24));
					quotes.scrollUpToDescription(driver);
					quotes.clickOnAddServiceLine();	
					quotes.enterServiceLineName(UtilityClass.fetchDataFromExcelSheet("Invoices",i,25));
					quotes.enterServiceLineListPrice(UtilityClass.fetchDataFromExcelSheet("Invoices",i,26));
					quotes.enterServiceLineDiscount(UtilityClass.fetchDataFromExcelSheet("Invoices",i,27));		
					quotes.selectServiceLineDiscountType(driver, UtilityClass.fetchDataFromExcelSheet("Invoices",i,28));
					quotes.selectServiceLineTax(driver, UtilityClass.fetchDataFromExcelSheet("Invoices",i,29));
					quotes.enterShippingAmount(UtilityClass.fetchDataFromExcelSheet("Invoices",i,30));
					quotes.selectShippingTax(driver, UtilityClass.fetchDataFromExcelSheet("Invoices",i,31));
					create_Lead.scrollpage(driver);
					create_Lead.clickOnSavebtn();
					list_View.getAlertMessage(quote);
					//back to list view of Account module
					
					Thread.sleep(7000);
					add_Opportunities.backToListView();
					Thread.sleep(4000);
					list_View.scrolluptoAddBtn(driver);
					soft.assertAll();	
				}
				catch(NullPointerException e)
				{
					System.out.println("NullPointerException thrown!");	
				}
							
			}
				
		}	
		
		//Create test case for Edit Quote
		@Test( groups={"Edit", "Sanity"})
		public void EditInvoice() throws Exception 
		{
				//Create this test case in Extent Report
				test= extent.createTest("Edit Invoice").assignAuthor("Komal")
								.assignCategory("Functional Test Case").assignDevice("Chrome");
							
				test.info("User changed status of Invoice.");
				String input1="Invoice2";
				list_View.enterTextInSearchBtn(driver,input1);
				Thread.sleep(5000);
							 	
				String OppName=add_Opportunities.verifyOpportunityName(driver, input1);
				if(OppName.equals(input1))
				{
									
						add_Account.clickOnName(driver, input1);
						list_View.clickOnEditBtn();
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
						invoice.selectStatus(driver, UtilityClass.fetchDataFromExcelSheet("Invoices",3,7));
						create_Lead.enterDescription(UtilityClass.fetchDataFromExcelSheet("Invoices",1,32));
						create_Lead.scrollpage(driver);
						create_Lead.clickOnSavebtn();
						test.info(input1+" 's status is changed succefully.");
		
				}
				else
				{
						System.out.println("Quote Name not matched");
						test.info(input1+ " record is not matched");
				}
		}
		
		//Create test case for Delete Account From Edit Option
		@Test( groups = {"DeleteFromEditView", "Sanity"}, dependsOnMethods={"CreateInvoices"})
		public void DeleteInvoiceFromEditOption() throws Exception 
		{
			CommonFunctions.DeleteRecordFromEditOption(dashboard,list_View, add_Opportunities, "Invoice5", "Invoice");
						
		}
		
		//Create test case for Delete Account From Edit Option
		@Test( groups = {"DeleteFromListView", "Sanity"}, dependsOnMethods={"CreateInvoices"})
		public void DeleteInvoiceFromListView() throws InterruptedException, EncryptedDocumentException, IOException 
		{
			CommonFunctions.DeleteRecordFromListView(list_View, add_Opportunities, "Invoice4", "Invoice");
							
		}

		//Test case for In this if copy from left check is selected then verify both the address is same else print the Alternate address.
		@Test( groups={"ShippingAddress"})
		public void ShippingAddressInvoice() throws InterruptedException, EncryptedDocumentException, IOException
		{
			CommonFunctions.ShippingAddress(list_View, add_Account, duplicate_account, "Invoice3", "Accounts");
		}
		
		//Test case for Export functionality
		@Test( groups = {"Export", "Sanity"})
		public void ExportRecordsForInvoice() throws Exception
		{
				
				//Call function from Common Function class
				CommonFunctions.ExportRecords(list_View, "Invoice1", "Invoices");
		}
		
		//Test case for Mass update functionality
		@Test( groups={"MassUpdate", "Sanity"})
		public void MassUpdateInvoices() throws InterruptedException, EncryptedDocumentException, IOException, AWTException, ParseException
		{
			
			CommonFunctions.MassUpdateCaseModule(list_View, "Invoices", "Invoice1");
			
		}
		
		@Test( groups= {"Duplicate"})
		public void DuplicateInvoice() throws InterruptedException, EncryptedDocumentException, IOException, ParseException, AWTException
		{
			//Create this test case in Extent Report
			 test= extent.createTest("Verify Invoice Is Duplicate?").assignAuthor("Komal")
					.assignCategory("Functional Test Case").assignDevice("Chrome");
			
			 test.info("Verify invoice is duplicate or not");
			 		
			SoftAssert soft=new SoftAssert();
			String input1="Invoice1";
			Thread.sleep(2000);
			list_View.enterTextInSearchBtn(driver,input1);
			Thread.sleep(5000);
		
			 	
				String OppName=add_Opportunities.verifyOpportunityName(driver, input1);
				if(OppName.equals(input1))
				{
					
					add_Account.clickOnName(driver, input1);
					
					Thread.sleep(5000);
					list_View.menu(driver,"DUPLICATE");
					
					String[] date = invoice.getQuoteDate();
					String ExactQuoteDate = duplicate_opp.dateCompare(date);
					System.out.println("Quote Date: " +ExactQuoteDate);
					
					String[] duedate = invoice.getDueDate();
					String ExactDueDate = duplicate_opp.dateCompare(duedate);
					System.out.println("Quote Date: " +ExactDueDate);
					
					String[] invoicedate = invoice.getInvoiceDate();
					String ExactInvoiceDate = duplicate_opp.dateCompare(invoicedate);
					System.out.println("Quote Date: " +ExactInvoiceDate);
					
					
					add_Account.scrollpageCopyFromleftCheckBox(driver);
					Thread.sleep(1000);
					quotes.clickOnShowMore();
					int j=0,k=0;
					String arr1[]= {invoice.getQuoteNumber(), ExactQuoteDate,ExactDueDate, ExactInvoiceDate,duplicate_task.getStatus(), duplicate.getDescription(), quotes.getAccount(), 
							quotes.getContact(), duplicate_account.getBillingAddress(),duplicate_account.getBillingState(),duplicate_account.getBillingPostalCode(),duplicate_account.getBillingCountry(),
							duplicate_account.getBillingCity(), quotes.getCurrency(),quotes.getQuantity(), quotes.getProducts(), quotes.getDiscount(), quotes.getDiscountType(), quotes.getProductLineTax(), quotes.getProductDescription(), invoice.getProductLineNote()
							,quotes.getServiceLineName(), quotes.getServiceLineListPrice(),quotes.getServiceLineDiscount(), quotes.getServiceLineDiscountType(), quotes.getServiceLineTax(), quotes.getShippingAmount(), quotes.getShippingTax()};
					
					String arr2[]= {UtilityClass.fetchDataFromExcelSheet("Invoices",1, 2),UtilityClass.fetchDataFromExcelSheet("Invoices",1, 3),UtilityClass.fetchDataFromExcelSheet("Invoices",1, 4),
							UtilityClass.fetchDataFromExcelSheet("Invoices",1, 5),UtilityClass.fetchDataFromExcelSheet("Invoices",1, 7),UtilityClass.fetchDataFromExcelSheet("Invoices",1, 8),
							UtilityClass.fetchDataFromExcelSheet("Invoices",1, 9),UtilityClass.fetchDataFromExcelSheet("Invoices",1, 10),UtilityClass.fetchDataFromExcelSheet("Invoices",1, 11),
							UtilityClass.fetchDataFromExcelSheet("Invoices",1, 12),UtilityClass.fetchDataFromExcelSheet("Invoices",1, 13),UtilityClass.fetchDataFromExcelSheet("Invoices",1, 14),
							UtilityClass.fetchDataFromExcelSheet("Invoices",1, 15),UtilityClass.fetchDataFromExcelSheet("Invoices",1, 16),UtilityClass.fetchDataFromExcelSheet("Invoices",1, 17),
							UtilityClass.fetchDataFromExcelSheet("Invoices",1, 18),UtilityClass.fetchDataFromExcelSheet("Invoices",1, 20),UtilityClass.fetchDataFromExcelSheet("Invoices",1, 21)
							,UtilityClass.fetchDataFromExcelSheet("Invoices",1, 22),UtilityClass.fetchDataFromExcelSheet("Invoices",1, 23),UtilityClass.fetchDataFromExcelSheet("Invoices",1, 24)
							,UtilityClass.fetchDataFromExcelSheet("Invoices",1, 25),UtilityClass.fetchDataFromExcelSheet("Invoices",1, 26),UtilityClass.fetchDataFromExcelSheet("Invoices",1, 27)
							,UtilityClass.fetchDataFromExcelSheet("Invoices",1, 28),UtilityClass.fetchDataFromExcelSheet("Invoices",1, 29),UtilityClass.fetchDataFromExcelSheet("Invoices",1, 30)
							,UtilityClass.fetchDataFromExcelSheet("Invoices",1, 31)};
					
					for(int i=0;i<28;i++)
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
		
		@Test ( groups= {"Calculations", "Sanity"})
		public void Invoice_Calculation() throws InterruptedException
		{
			//Create this test case in Extent Report
			test= extent.createTest("Invoice Calculation").assignAuthor("Komal")
							.assignCategory("Functional Test Case").assignDevice("Chrome");
				
			
			CommonFunctions.AllCalculations(quotes, list_View, add_Account, add_Opportunities, "Invoice", "Invoice1");
		}
}
