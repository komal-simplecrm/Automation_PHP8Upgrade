package Quotes_Module;


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
import POM_Lead_Module.Add_Lead;
import POM_Lead_Module.Duplicate_Page;
import POM_Lead_Module.Lead_ListView;
import POM_Opportunities_Module.Add_Opportunities;
import POM_Opportunities_Module.Duplicate_Opp_Page;
import POM_Quote_Module.Add_Quotes;

public class QuotesTestClass extends Base_Class
{
	SoftAssert soft;
	Dashboard dashboard;
	Add_Lead create_Lead;
	Lead_ListView list_View;
	Add_Opportunities add_Opportunities;
	Add_Quotes quotes;
	Add_Account add_Account;
	Duplicate_Account duplicate_account;
	Duplicate_Page duplicate;
	Duplicate_Opp_Page duplicate_opp;
	QuotesTestClass quotesTest;
	
	@BeforeMethod(alwaysRun = true)
	public void OpenBrowser() throws InterruptedException
	{
		dashboard = new Dashboard(driver);
		create_Lead = new Add_Lead(driver);
		list_View = new Lead_ListView(driver);
		add_Opportunities = new Add_Opportunities(driver);
		quotes = new Add_Quotes(driver);
		add_Account = new Add_Account(driver);
		duplicate_account = new Duplicate_Account(driver);
		duplicate = new Duplicate_Page(driver);
		duplicate_opp = new Duplicate_Opp_Page(driver);
		soft = new SoftAssert();
		quotesTest = new QuotesTestClass();
		
		//directed to the dash board page
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//Thread.sleep(2000);
		dashboard.clickOnMenuOption();
		dashboard.clickOnSearch("Quotes");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		dashboard.clickOnMenuOption(driver,"Quotes");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		dashboard.closeMenuOption();
		Thread.sleep(4000);
	}
	
	//Create test case for create multiple Account
	@Test( groups={"Create", "Sanity"})
	public void CreateQuotes() throws Exception 
	{
		//Create this test case in Extent Report
		test= extent.createTest("Create Quotes").assignAuthor("Komal")
						.assignCategory("Functional Test Case").assignDevice("Chrome");
				
		test.info("Create multiple Quotes");
				
		for(int i=1;i<=8;i++)
		{
					 	
			Thread.sleep(5000);
			list_View.clickOnAddButton();
					 
			
		try {
						
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
				String quote = add_Opportunities.enterName(UtilityClass.fetchDataFromExcelSheet("Quotes",i,0));
				soft.assertNotNull(quote);
				
				quotes.selectOpportunity(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",i,1));
				//quotes.enterQuoteNumber(UtilityClass.fetchDataFromExcelSheet("Quotes",i,2));
				String quotestage = quotes.selectQuoteStage(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",i,3));
				soft.assertNotNull(quotestage);
				String ValidUntil = quotes.selectValidUntil(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",i,4));
				soft.assertNotNull(ValidUntil);
				quotes.selectInvoiceStatus(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",i,5));
				create_Lead.AssignedTo(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",i,6));
				quotes.selectPaymentTerm(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",i,7));	
				quotes.selectApprovalStatus(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",i,8), UtilityClass.fetchDataFromExcelSheet("Quotes",i,9));
				quotes.scrollpageuptoSearchIconKnight(driver);
				//quotes.selectKnight(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",i,33));
				
				quotes.enterApprovalIssue(UtilityClass.fetchDataFromExcelSheet("Quotes",i,9));	
				quotes.enterDescription(UtilityClass.fetchDataFromExcelSheet("Quotes",i,34));
				quotes.selectAccount(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",i,10));		
				
				quotes.selectContact(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",i,11));	
				add_Account.enterBillingAddress(UtilityClass.fetchDataFromExcelSheet("Quotes",i,12));
				add_Account.enterBillingState(UtilityClass.fetchDataFromExcelSheet("Quotes",i,13));
				//create_Lead.scrollpage(driver);
				add_Account.enterBillingPostalCode(UtilityClass.fetchDataFromExcelSheet("Quotes",i,14));
				add_Account.enterBillingCountry(UtilityClass.fetchDataFromExcelSheet("Quotes",i,15));
				add_Account.scrollpageBillingCountry(driver);
				add_Account.enterBillingCity(UtilityClass.fetchDataFromExcelSheet("Quotes",i,16));
				add_Account.clickCopyFromleftCheckBox();
				add_Account.scrollpageCopyFromleftCheckBox(driver);
				//quotes.selectCurrency(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",i,17));	
				//quotes.scrollUpToShippingAmount(driver);
				quotes.clickOnAddGroup();
				quotes.clickOnAddProductLine();
				quotes.enterProductLineQuantity(UtilityClass.fetchDataFromExcelSheet("Quotes",i,18));
				quotes.selectProducts(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",i,19));		
				//quotes.enterProductLineListPrice(UtilityClass.fetchDataFromExcelSheet("Quotes",i,21));	
				quotes.enterProductLineDiscount(UtilityClass.fetchDataFromExcelSheet("Quotes",i,21));	
				quotes.selectProductLineDiscountType(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",i,22));
				quotes.selectProductLineTax(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",i,23));
				quotes.clickOnShowMore();
				quotes.enterDescription(UtilityClass.fetchDataFromExcelSheet("Quotes",i,24));
				create_Lead.enterDescription(UtilityClass.fetchDataFromExcelSheet("Quotes",i,25));
				quotes.clickOnAddServiceLine();	
				create_Lead.scrollpage(driver);
				quotes.enterServiceLineName(UtilityClass.fetchDataFromExcelSheet("Quotes",i,26));
				quotes.enterServiceLineListPrice(UtilityClass.fetchDataFromExcelSheet("Quotes",i,27));
				quotes.enterServiceLineDiscount(UtilityClass.fetchDataFromExcelSheet("Quotes",i,28));		
				quotes.selectServiceLineDiscountType(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",i,29));
				quotes.selectServiceLineTax(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",i,30));
				quotes.enterShippingAmount(UtilityClass.fetchDataFromExcelSheet("Quotes",i,31));
				quotes.selectShippingTax(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",i,32));
				create_Lead.scrollpage(driver);
				create_Lead.clickOnSavebtn();
				list_View.getAlertMessage(quote);
				//back to list view of Account module
				//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
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
	@Test(enabled= false, groups={"Edit", "Sanity"})
	public void EditQuote() throws Exception 
	{
			//Create this test case in Extent Report
			test= extent.createTest("Edit Quote").assignAuthor("Komal")
							.assignCategory("Functional Test Case").assignDevice("Chrome");
						
			test.info("User changed status of quote.");
			String input1="Quote1";
			list_View.enterTextInSearchBtn(driver,input1);
			Thread.sleep(5000);
						 	
			String OppName=add_Opportunities.verifyOpportunityName(driver, input1);
			if(OppName.equals(input1))
			{
								
					add_Account.clickOnName(driver, input1);
					list_View.clickOnEditBtn();
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
					quotes.selectQuoteStage(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",3,3));
					quotes.selectValidUntil(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",3,4));
					quotes.selectInvoiceStatus(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",3,5));
					quotes.selectApprovalStatus(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",3,8), UtilityClass.fetchDataFromExcelSheet("Quotes",3,9));
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
	@Test(enabled= false, groups = {"DeleteFromEditView", "Sanity"}, dependsOnMethods={"CreateQuotes"})
	public void DeleteQuoteFromEditOption() throws Exception 
	{
		CommonFunctions.DeleteRecordFromEditOption(dashboard, list_View, add_Opportunities,"Quote1", "Quotes");
					
	}
	
	//Create test case for Delete Account From Edit Option
	@Test(enabled= false, groups = {"DeleteFromListView", "Sanity"}, dependsOnMethods={"CreateQuotes"})
	public void DeleteQuoteFromListView() throws InterruptedException, EncryptedDocumentException, IOException 
	{
		CommonFunctions.DeleteRecordFromListView(list_View, add_Opportunities, "Quote2", "Quotes");
						
	}

	//Test case for In this if copy from left check is selected then verify both the address is same else print the Alternate address.
	@Test(enabled= false, groups={"ShippingAddress"})
	public void ShippingAddressQuotes() throws InterruptedException, EncryptedDocumentException, IOException
	{
		CommonFunctions.ShippingAddress(list_View, add_Account, duplicate_account, "Quote1", "Accounts");
	}
	
	//Test case for Export functionality
	@Test(enabled= false, groups = {"Export", "Sanity"})
	public void ExportRecordsForQuote() throws Exception
	{
			
			//Call function from Common Function class
			CommonFunctions.ExportRecords(list_View, "Quote1", "Quotes");
	}
	
	//Test case for Mass update functionality
	@Test(enabled= false, groups={"MassUpdate", "Sanity"})
	public void MassUpdateQuotes() throws InterruptedException, EncryptedDocumentException, IOException, AWTException, ParseException
	{
		
		CommonFunctions.MassUpdateCaseModule(list_View, "Accounts", "Quote1");
		
	}
	
	@Test(enabled= false, groups= {"Duplicate"})
	public void DuplicateQuotes() throws InterruptedException, EncryptedDocumentException, IOException, ParseException, AWTException
	{
		//Create this test case in Extent Report
		 test= extent.createTest("Verify Quotes Is Duplicate ?").assignAuthor("Komal")
				.assignCategory("Functional Test Case").assignDevice("Chrome");
		
		 test.info("Verify Quotes is duplicate or not");
		 		
		SoftAssert soft=new SoftAssert();
		String input1="Quote3";
		list_View.enterTextInSearchBtn(driver,input1);
		Thread.sleep(5000);
	
		 	
			String OppName=add_Opportunities.verifyOpportunityName(driver, input1);
			if(OppName.equals(input1))
			{
				
				add_Account.clickOnName(driver, input1);
				
				Thread.sleep(5000);
				list_View.menu(driver,"DUPLICATE");
				
				String[] date = quotes.getValidUntil();
				String ExactValidDate = duplicate_opp.dateCompare(date);
				System.out.println("Valid Date: " +ExactValidDate);
				
				add_Account.scrollpageCopyFromleftCheckBox(driver);
				Thread.sleep(1000);
				quotes.clickOnShowMore();
				int j=0,k=0;
				String arr1[]= {duplicate_opp.getOpportunityName(), quotes.getOpportunity(), quotes.getQuoteStage(), ExactValidDate,quotes.getInvoiceStatus(), quotes.getPaymentTerm(),
						quotes.getApprovalStatus(),quotes.getApprovalIssue(), quotes.getKnight(),quotes.getAccount(), quotes.getContact(), duplicate_account.getBillingAddress(),
						duplicate_account.getBillingState(),duplicate_account.getBillingPostalCode(),duplicate_account.getBillingCountry(),duplicate_account.getBillingCity(), quotes.getCurrency(), 
						quotes.getQuantity(), quotes.getProducts(), quotes.getDiscount(), quotes.getDiscountType(), quotes.getProductLineTax(), quotes.getProductDescription(), duplicate.getDescription(),
						quotes.getServiceLineName(), quotes.getServiceLineListPrice(),quotes.getServiceLineDiscount(), quotes.getServiceLineDiscountType(), quotes.getServiceLineTax(), quotes.getShippingAmount(), quotes.getShippingTax()};
				
				String arr2[]= {UtilityClass.fetchDataFromExcelSheet("Quotes",3, 0),UtilityClass.fetchDataFromExcelSheet("Quotes",3, 1),UtilityClass.fetchDataFromExcelSheet("Quotes",3, 3),
						UtilityClass.fetchDataFromExcelSheet("Quotes",3, 4),UtilityClass.fetchDataFromExcelSheet("Quotes",3, 5),UtilityClass.fetchDataFromExcelSheet("Quotes",3, 7),
						UtilityClass.fetchDataFromExcelSheet("Quotes",3, 8),UtilityClass.fetchDataFromExcelSheet("Quotes",3, 9),UtilityClass.fetchDataFromExcelSheet("Quotes",3, 33),
						UtilityClass.fetchDataFromExcelSheet("Quotes",3, 10),UtilityClass.fetchDataFromExcelSheet("Quotes",3, 11),UtilityClass.fetchDataFromExcelSheet("Quotes",3, 12),
						UtilityClass.fetchDataFromExcelSheet("Quotes",3, 13),UtilityClass.fetchDataFromExcelSheet("Quotes",3, 14),UtilityClass.fetchDataFromExcelSheet("Quotes",3, 15),
						UtilityClass.fetchDataFromExcelSheet("Quotes",3, 16),UtilityClass.fetchDataFromExcelSheet("Quotes",3, 17),UtilityClass.fetchDataFromExcelSheet("Quotes",3, 18)
						,UtilityClass.fetchDataFromExcelSheet("Quotes",3, 19),UtilityClass.fetchDataFromExcelSheet("Quotes",3, 21),UtilityClass.fetchDataFromExcelSheet("Quotes",3, 22)
						,UtilityClass.fetchDataFromExcelSheet("Quotes",3, 23),UtilityClass.fetchDataFromExcelSheet("Quotes",3, 24),UtilityClass.fetchDataFromExcelSheet("Quotes",3, 25)
						,UtilityClass.fetchDataFromExcelSheet("Quotes",3, 26),UtilityClass.fetchDataFromExcelSheet("Quotes",3, 27),UtilityClass.fetchDataFromExcelSheet("Quotes",3, 28)
						,UtilityClass.fetchDataFromExcelSheet("Quotes",3, 29),UtilityClass.fetchDataFromExcelSheet("Quotes",3, 30),UtilityClass.fetchDataFromExcelSheet("Quotes",3, 31),
						UtilityClass.fetchDataFromExcelSheet("Quotes",3, 32)};
				
				for(int i=0;i<31;i++)
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
	
	
	@Test(enabled= false, groups= {"Calculations", "Sanity"})
	public void Quotes_Calculation() throws InterruptedException
	{
		//Create this test case in Extent Report
		test= extent.createTest("Quotes Calculation").assignAuthor("Komal")
						.assignCategory("Functional Test Case").assignDevice("Chrome");
			
		
		CommonFunctions.AllCalculations(quotes, list_View, add_Account, add_Opportunities, "Quotes", "Quote5");
		
	}
}
