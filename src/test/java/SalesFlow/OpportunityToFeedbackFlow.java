package SalesFlow;


import java.io.IOException;
import java.time.Duration;


import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.NoSuchSessionException;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Library_Files.Base_Class;
import Library_Files.UtilityClass;
import Login_Page.Dashboard;
import Login_Page.Login_Page;
import POM_Account_Module.Add_Account;
import POM_Account_Module.Duplicate_Account;
import POM_BPM.BPM_Elements;
import POM_Cases_Module.Create_Case;
import POM_Contacts_Module.Add_Contacts;
import POM_Feedback_Module.Add_Feedback;
import POM_Invoice_Module.Add_Invoice;
import POM_Lead_Module.Add_Lead;
import POM_Lead_Module.Duplicate_Page;
import POM_Lead_Module.Lead_ListView;
import POM_Opportunities_Module.Add_Opportunities;
import POM_Opportunities_Module.Duplicate_Opp_Page;
import POM_Quote_Module.Add_Quotes;
import POM_Quote_Module.Duplicate_Quote_Page;
import POM_Task_Module.Duplicate_Task_Page;

public class OpportunityToFeedbackFlow extends Base_Class
{
	Login_Page login;
	Dashboard dashboard;
	Add_Lead create_Lead;
	Lead_ListView list_View;
	Add_Opportunities add_Opportunities;
	Add_Contacts contacts; 
	Add_Account add_Account;
	Add_Quotes quotes;
	Duplicate_Quote_Page duplicate_Quotes;
	Duplicate_Opp_Page duplicate_opp;
	Duplicate_Account duplicate_account;
	Add_Feedback feedback;
	Add_Invoice invoice;
	Duplicate_Task_Page duplicate_task;
	Duplicate_Page duplicate;
	Create_Case cases;
	SoftAssert soft;
	BPM_Elements BPM; 
	
	@BeforeMethod(alwaysRun = true)
	public void OpenBrowser() throws IOException, InterruptedException 
	{
		
		
		//Create object of Pom classes
		dashboard=new Dashboard(driver);
		create_Lead=new Add_Lead(driver);
		list_View =new Lead_ListView(driver);
		add_Opportunities= new Add_Opportunities(driver);
		contacts = new Add_Contacts(driver);
		add_Account = new Add_Account(driver);
		quotes = new Add_Quotes(driver);
		duplicate_Quotes = new Duplicate_Quote_Page(driver);
		duplicate_opp = new Duplicate_Opp_Page(driver);
		duplicate_account = new Duplicate_Account(driver);
		feedback = new Add_Feedback(driver);
		invoice = new Add_Invoice(driver);
		duplicate_task = new Duplicate_Task_Page(driver);
		duplicate=new Duplicate_Page(driver);
		cases = new Create_Case(driver);
		BPM = new BPM_Elements(driver);
		soft = new SoftAssert();
		
		
		//directed to the dash board page
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Thread.sleep(2000);
		dashboard.clickOnMenuOption();
		dashboard.clickOnSearch("Leads");
		Thread.sleep(4000);
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		dashboard.clickOnMenuOption(driver,"Leads");
		Thread.sleep(5000);
		dashboard.closeMenuOption();
		Thread.sleep(4000);
		
	}
	
	
	@Test()
	public void FullSalesFlow() throws Exception
	{
		//Create this test case in Extent Report
		 test= extent.createTest("Full Sales Flow up to Feedback module").assignAuthor("Komal")
				.assignCategory("Functional Test Case").assignDevice();
		
		 test.info("Sales Flow started");
		 String lastName = null;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		list_View.clickOnAddButton();
		try {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		create_Lead.selectSalutation(driver,UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 0));
		create_Lead.enterFirstName(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 1));
		lastName = create_Lead.enterLastName(driver,UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 2));
		soft.assertNotNull(lastName);
		
		create_Lead.selectType(driver,UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 3));
		create_Lead.enterOfficePhone(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 4));
		create_Lead.enterTitle(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 5));
		create_Lead.enterMobile(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 6));
		create_Lead.enterDepartment(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 7));
		create_Lead.selectLoanType(driver,UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 8));
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
		create_Lead.enterFax(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 20));
		create_Lead.enterUTM_URL(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 21));
		create_Lead.enterPartner_Contact(driver,UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 22));
		create_Lead.enterUTM_Campaign(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 23));
		Thread.sleep(2000);
		create_Lead.scrolluptoUTMURL(driver);
		create_Lead.selectLeadSource(driver,UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 24));
		Thread.sleep(2000);
		create_Lead.enterStatus_Description(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 25));
		create_Lead.enterLead_Source_Description(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 26));
		create_Lead.enterOpportunity_Amount(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 27));
		create_Lead.enterRefered_by(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 28));
		//create_Lead.enterCampaign(driver,UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 29));
		create_Lead.scrollpage(driver);
		Thread.sleep(2000);
		create_Lead.AssignedTo(driver, UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 34));
		create_Lead.clickOnSavebtn();
		
		test.info("New Lead " +lastName+ " is Created");
		Thread.sleep(7000);
		list_View.scrollUptoEditBtn(driver);
	
		}
		catch(NullPointerException e) {
			System.out.println("NullPointerException thrown!");
			
		}
			
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		test.info("Click on menu options.");
		//dashboard.clickOnMenuOption();
		Thread.sleep(4000);
		test.info("Click on CONVERT LEAD option.");
		list_View.menu(driver,"CONVERT LEAD");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		add_Opportunities.scrollUpToCreateOpportuity(driver);
		//get Opportunity Name
		String OpportunitiesName = add_Opportunities.getOpportunitiesName();
		add_Opportunities.selectSalesFlowCurrency(driver, UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 35));
		add_Opportunities.selectSalesFlowSalesStage(driver, UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 36));
		add_Opportunities.selectSalesFlowOpportunityAmount(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 37));
		add_Opportunities.selectSalesFlowDate_Closed(driver,UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 38));
		add_Opportunities.selectSalesFlowDescription(UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 39));
		test.info("Click on Save Button.");
		create_Lead.scrollpage(driver);
		create_Lead.clickOnSavebtn();
		//String msg = list_View.EveryPageAlert();(Defects in the alert massage)
		//test.info(msg+ "Message showing on CRM");
		//System.out.println(msg);
		Thread.sleep(2000);
		String status = add_Opportunities.getStatusConverted();
		test.info(lastName+ " status is " +status+ " in Detail View.");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		test.info("Click on menu options.");
		dashboard.clickOnMenuOption();
		Thread.sleep(2000);
		dashboard.clickOnSearch("Opportunities");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		dashboard.clickOnMenuOption(driver,"Opportunities");
		Thread.sleep(4000);
		list_View.scrolluptoAddBtn(driver);
		Thread.sleep(5000);
		
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
			add_Opportunities.selectSalesStage(driver, UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 40), 
					UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 41), UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 42), 
					UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 43), UtilityClass.fetchDataFromExcelSheet("SalesFlow",1, 44));
		
			create_Lead.scrollpage(driver);
			test.info("Click on Save Buuton in Opptiunities Module");
			create_Lead.clickOnSavebtn();
			test.info(OpportunitiesName+ " in Detail View.");
			Thread.sleep(2000);
			list_View.scrollUptoEditBtn(driver);
			String SalesStage = add_Opportunities.getDetailViewSalesStageOfOpportunities();
			test.info(OpportunitiesName+ " 's sales stage is " +SalesStage);
			//Diverted towards Contacts module.
			
			//Thread.sleep(4000);
			//list_View.scrolluptoAddBtn(driver);
			Thread.sleep(2000);
			dashboard.clickOnSearch("Contacts");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
			dashboard.clickOnMenuOption(driver,"Contacts");
			Thread.sleep(2000);
			dashboard.closeMenuOption();
			Thread.sleep(5000);
		
			test.info("Contact is searched in contact module.");
			String[] contactname = OpportunitiesName.split(" ");
			String contactFirstName = contactname[1];
			String contactLastName = contactname[2];
			String contactName = contactFirstName + " " + contactLastName;
			System.out.println(contactName+" contact Name");
			list_View.scrolluptoAddBtn(driver);
			//Searched Contacts name by entering in the search field
			list_View.enterTextInSearchBtn(driver,contactName);
			Thread.sleep(5000);
			list_View.clickOnName(driver,contactName);
			/*String input1="";
			list_View.clickOnFilter(driver, input1,contactFirstName, "Case");
		  	cases.clickOnSubject(driver, input1, contactFirstName);*/
		 	String contact = contacts.VerifyContactName();
			soft.assertEquals(contact, OpportunitiesName,"Failed: Contact name and Opportunity name is not matched.");
			test.info(contact+ " contact is created in Contact module.");
			
			//Diverted towards Accounts module.
			//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			//test.info("Click on menu options.");
			Thread.sleep(2000);
			dashboard.clickOnMenuOption();
			Thread.sleep(2000);
			dashboard.clickOnSearch("Accounts");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
			dashboard.clickOnMenuOption(driver,"Accounts");
			//Thread.sleep(2000);
			//dashboard.clickOnMenuOption();
			Thread.sleep(5000);
			list_View.enterTextInSearchBtn(driver,OpportunitiesName);
			Thread.sleep(5000);
			list_View.clickOnName(driver,contactName);
			String AccountName = contacts.VerifyContactName();
			soft.assertEquals(AccountName, OpportunitiesName,"Failed: Contact name and Opportunity name is not matched.");
			String oppName = add_Account.VerifyOpportunitySubPanelName(driver,"opportunities");
			System.out.println("oppName is" +oppName);
			System.out.println("OpportunitiesName is" +OpportunitiesName);
			soft.assertEquals(oppName, OpportunitiesName,"Failed: Contact name and Opportunity name is not matched.");
			String LeadName = add_Account.VerifyOpportunitySubPanelName(driver, "leads");
			soft.assertEquals(LeadName, contactName,"Failed: Contact name and Opportunity name is not matched.");
			
			System.out.println("LeadName is" +LeadName);
			
			
			soft.assertAll();
			test.info(AccountName+ " account is created in Account module.");
			//dashboard.clickOnMenuOption();
			Thread.sleep(5000);
			//Create Quote
			//dashboard.clickOnMenuOption();
			dashboard.clickOnSearch("Quotes");
			Thread.sleep(4000);
			dashboard.clickOnMenuOption(driver,"Quotes");
			Thread.sleep(5000);
			list_View.clickOnAddButton();
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
			String quote = add_Opportunities.enterName(UtilityClass.fetchDataFromExcelSheet("Quotes",1,0));
			soft.assertNotNull(quote);
			
			quotes.selectOpportunity(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",1,1));
			//quotes.enterQuoteNumber(UtilityClass.fetchDataFromExcelSheet("Quotes",i,2));
			String quotestage = quotes.selectQuoteStage(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",1,3));
			soft.assertNotNull(quotestage);
			String ValidUntil = quotes.selectValidUntil(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",1,4));
			soft.assertNotNull(ValidUntil);
			quotes.selectInvoiceStatus(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",1,5));
			create_Lead.AssignedTo(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",1,6));	
			quotes.selectPaymentTerm(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",1,7));	
			quotes.selectApprovalStatus(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",1,8), UtilityClass.fetchDataFromExcelSheet("Quotes",1,9));
			quotes.scrollpageuptoSearchIconKnight(driver);
			quotes.selectKnight(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",1,33));
			
			//quotes.enterApprovalIssue(UtilityClass.fetchDataFromExcelSheet("Quotes",1,9));	
			quotes.selectAccount(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",1,10));	//defect in this field	
			
			quotes.selectContact(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",1,11));	//defect in this field	
			add_Account.enterBillingAddress(UtilityClass.fetchDataFromExcelSheet("Quotes",1,12));
			add_Account.enterBillingState(UtilityClass.fetchDataFromExcelSheet("Quotes",1,13));
			//create_Lead.scrollpage(driver);
			add_Account.enterBillingPostalCode(UtilityClass.fetchDataFromExcelSheet("Quotes",1,14));
			add_Account.enterBillingCountry(UtilityClass.fetchDataFromExcelSheet("Quotes",1,15));
			add_Account.scrollpageBillingCountry(driver);
			add_Account.enterBillingCity(UtilityClass.fetchDataFromExcelSheet("Quotes",1,16));
			add_Account.clickCopyFromleftCheckBox();
			add_Account.scrollpageCopyFromleftCheckBox(driver);
			quotes.selectCurrency(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",1,17));	
			//quotes.scrollUpToShippingAmount(driver);
			quotes.clickOnAddGroup();
			quotes.clickOnAddProductLine();
			quotes.enterProductLineQuantity(UtilityClass.fetchDataFromExcelSheet("Quotes",1,18));
			quotes.selectProducts(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",1,19));		
			//quotes.enterProductLineListPrice(UtilityClass.fetchDataFromExcelSheet("Quotes",1,21));	
			quotes.enterProductLineDiscount(UtilityClass.fetchDataFromExcelSheet("Quotes",1,21));	
			quotes.selectProductLineDiscountType(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",1,22));
			quotes.selectProductLineTax(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",1,23));
			quotes.clickOnShowMore();
			quotes.enterDescription(UtilityClass.fetchDataFromExcelSheet("Quotes",1,24));
			create_Lead.enterDescription(UtilityClass.fetchDataFromExcelSheet("Quotes",1,25));
			quotes.clickOnAddServiceLine();	
			create_Lead.scrollpage(driver);
			quotes.enterServiceLineName(UtilityClass.fetchDataFromExcelSheet("Quotes",1,26));
			quotes.enterServiceLineListPrice(UtilityClass.fetchDataFromExcelSheet("Quotes",1,27));
			quotes.enterServiceLineDiscount(UtilityClass.fetchDataFromExcelSheet("Quotes",1,28));		
			quotes.selectServiceLineDiscountType(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",1,29));
			quotes.selectServiceLineTax(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",1,30));
			quotes.enterShippingAmount(UtilityClass.fetchDataFromExcelSheet("Quotes",1,31));
			quotes.selectShippingTax(driver, UtilityClass.fetchDataFromExcelSheet("Quotes",1,32));
			
			String arr2[]= {duplicate_opp.getOpportunityName(), invoice.getQuoteNumberinQuoteModule(), quotes.getAccount(), quotes.getContact(), duplicate_account.getBillingAddress(),duplicate_account.getBillingState(),duplicate_account.getBillingPostalCode(),
					duplicate_account.getBillingCountry(),duplicate_account.getBillingCity(), quotes.getCurrency(), quotes.getQuantity(), quotes.getProducts(), quotes.getDiscount(), quotes.getDiscountType(), quotes.getProductLineTax(), quotes.getProductDescription(), 
					duplicate.getDescription(),quotes.getServiceLineName(), quotes.getServiceLineListPrice(),quotes.getServiceLineDiscount(), quotes.getServiceLineDiscountType(), quotes.getServiceLineTax(), quotes.getShippingAmount(), quotes.getShippingTax()};
			Thread.sleep(5000);
			create_Lead.scrollpage(driver);
			create_Lead.clickOnSavebtn();
			test.info(quote+ " quote is created.");
			//back to list view of Account module
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			//add_Opportunities.backToListView();
			soft.assertAll();	
			//logout from admin user credentials
			//dashboard.clickProfileIcon(driver,"Logout");
			//login with regular user credentials
			//login.Login();
			Thread.sleep(5000);
			/*initializeBrowserForOpprotunitiesToFeedbackFlow("Edge","admin","simpleCRM@267");
			dashboard=new Dashboard(Tempdriver);
			list_View =new Lead_ListView(Tempdriver);
			create_Lead=new Add_Lead(Tempdriver); 
			add_Opportunities = new Add_Opportunities(Tempdriver);
			add_Account = new Add_Account(Tempdriver);
			quotes = new Add_Quotes(Tempdriver);
			
			Thread.sleep(5000);
			dashboard.clickOnMenuOption();
			dashboard.clickOnSearch("Quotes");
			//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			Thread.sleep(2000);
			dashboard.clickOnMenuOption(Tempdriver,"Quotes");
			Thread.sleep(2000);
			dashboard.closeMenuOption();
			Thread.sleep(5000);
			
		
		Thread.sleep(5000);
	 	
		String input1="Quote1";
		
		//quotes.enterTextInSearchBtn(Tempdriver,input1);
		list_View.enterTextInSearchBtn(Tempdriver,input1);
		Thread.sleep(5000);
					 	
		String QuoteName=add_Opportunities.verifyOpportunityName(Tempdriver, input1);
		if(QuoteName.equals(input1))
		{
							
				add_Account.clickOnName(Tempdriver, input1);
				Tempdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
				quotes.clickOnEditBtn(Tempdriver);
				Tempdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				
				quotes.selectApprovalStatus1(Tempdriver);
				Thread.sleep(2000);
				//create_Lead.clickOnSavebtn1(Tempdriver);
				create_Lead.scrollpage(Tempdriver);
				create_Lead.clickOnSavebtn();
				Thread.sleep(2000);
		}
				Tempdriver.close();*/
				//driver.switchTo().window(s);
		 
		 		/*list_View.enterTextInSearchBtn(driver,"Quote1");
		 		list_View.clickOnName(driver, "Quote1");
		 		
		 		list_View.clickOnEditBtn();
		 		add_Account.scrollpageCopyFromleftCheckBox(driver);
		 		quotes.clickOnShowMore();
				String arr2[]= {duplicate_opp.getOpportunityName(), invoice.getQuoteNumberinQuoteModule(), quotes.getAccount(), quotes.getContact(), duplicate_account.getBillingAddress(),duplicate_account.getBillingState(),duplicate_account.getBillingPostalCode(),
						duplicate_account.getBillingCountry(),duplicate_account.getBillingCity(), quotes.getCurrency(), quotes.getQuantity(), quotes.getProducts(), quotes.getDiscount(), quotes.getDiscountType(), quotes.getProductLineTax(), quotes.getProductDescription(), 
						duplicate.getDescription(),quotes.getServiceLineName(), quotes.getServiceLineListPrice(),quotes.getServiceLineDiscount(), quotes.getServiceLineDiscountType(), quotes.getServiceLineTax(), quotes.getShippingAmount(), quotes.getShippingTax()};
				Thread.sleep(5000);
				create_Lead.scrollpage(driver);
				//Click on save button
				create_Lead.clickOnSavebtn();*/
			try {	
				testRegularUserLogin();
				Thread.sleep(5000);
				driver.navigate().refresh();
				
				
				
				//get Approval status on the Regular user login browser
				String status1 = duplicate_Quotes.getDetailViewApprovalStatus();
				
				/*Thread.sleep(5000);
				dashboard.clickOnMenuOptionDashboard(driver);
				dashboard.clickOnSearch("Feedback");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(70));
				dashboard.clickOnMenuOption(driver,"Feedback");*/
				test.info(status1+" Quotes status is changed by admin.");
			//logout from regular user credentials
			//dashboard.clickProfileIcon(driver,"Logout");
			//login with admin user credentials
			//login.Login();
			//diverted towards Quote module
			
			
				soft.assertEquals(status1, "Approved","Failed: Status of the Quotes is different.");			
				//add_Account.clickOnName(driver, quote);
				
				/*Thread.sleep(5000);
				//Click on edit button
				list_View.clickOnEditBtn();
				
				String arr2[]= {duplicate_opp.getOpportunityName(), invoice.getQuoteNumberinQuoteModule(), quotes.getAccount(), quotes.getContact(), duplicate_account.getBillingAddress(),duplicate_account.getBillingState(),duplicate_account.getBillingPostalCode(),
						duplicate_account.getBillingCountry(),duplicate_account.getBillingCity(), quotes.getCurrency(), quotes.getQuantity(), quotes.getProducts(), quotes.getDiscount(), quotes.getDiscountType(), quotes.getProductLineTax(), quotes.getProductDescription(), 
						duplicate.getDescription(),quotes.getServiceLineName(), quotes.getServiceLineListPrice(),quotes.getServiceLineDiscount(), quotes.getServiceLineDiscountType(), quotes.getServiceLineTax(), quotes.getShippingAmount(), quotes.getShippingTax()};
				Thread.sleep(5000);
				create_Lead.scrollpage(driver);
				//Click on save button
				create_Lead.clickOnSavebtn();*/
				
				//Email quotation
				Thread.sleep(5000);
			}
			catch(NoSuchSessionException e)
			{
				System.out.println("No Such Session Exception thrown!");
				
				
			}
			
			create_Lead=new Add_Lead(driver);
			list_View =new Lead_ListView(driver);
			quotes = new Add_Quotes(driver);
			duplicate_opp = new Duplicate_Opp_Page(driver);
			duplicate_account = new Duplicate_Account(driver);
			invoice = new Add_Invoice(driver);
			soft = new SoftAssert();
			
				list_View.menu(driver,"EMAIL QUOTATION");
				Thread.sleep(2000);
				quotes.selectTemplate(driver, "Quotes PDF Template");
				Thread.sleep(2000);
				String emailFrom = duplicate_Quotes.getEmailFrom();
				test.info(emailFrom+ " email send from email address");
				//quotes.searchIconFuctionality(driver, );
				Thread.sleep(5000);
				//String contactName = "Alexa Shara";
				System.out.println(emailFrom);
				quotes.searchIconFuctionality(driver, "To", "Accounts", "Mr. Alexa Shara" );
				quotes.searchIconFuctionality(driver, "CC", "Contacts", contactName );
				quotes.searchIconFuctionality(driver, "BCC", "Leads", contactName );
				quotes.enterSubject("Please review the quotation");
				quotes.clickOnattachFile();
				Thread.sleep(2000);
				quotes.clickOnAttachDocuments(driver, "Test5.txt");
				Thread.sleep(2000);
				quotes.clickOnSend();
				String msg2 = list_View.EveryPageAlert();
				test.info(msg2+ "Message showing on CRM");
				System.out.println(msg2);
				Thread.sleep(8000);
				list_View.menu(driver,"CONVERT TO INVOICE");
				Thread.sleep(5000);
				quotes.scrollUpToCurrency(driver);
		 		quotes.clickOnShowMore();
				
				//Validate the data in invoice is autofill and compare with Quote1
				int j=0,k=0;
				String arr1[]= { duplicate_opp.getOpportunityName(), invoice.getQuoteNumber(), quotes.getAccount(), quotes.getContact(), duplicate_account.getBillingAddress(),duplicate_account.getBillingState(),duplicate_account.getBillingPostalCode(), 
						duplicate_account.getBillingCountry(),duplicate_account.getBillingCity(), quotes.getCurrency(),quotes.getQuantity(), quotes.getProducts(), quotes.getDiscount(), quotes.getDiscountType(), quotes.getProductLineTax(), quotes.getProductDescription(), 
						invoice.getProductLineNote(),quotes.getServiceLineName(), quotes.getServiceLineListPrice(),quotes.getServiceLineDiscount(), quotes.getServiceLineDiscountType(), quotes.getServiceLineTax(), quotes.getShippingAmount(), quotes.getShippingTax()};
				
				
				for(int i=0;i<24;i++)
				{
					
					soft.assertEquals(arr1[j], arr2[k],"Failed: Both result are different");
					
					j++;
					k++;
					
				}
				
				create_Lead.scrollpage(driver);
				//Click on save button
				create_Lead.clickOnSavebtn();
				soft.assertAll();
		}
	}
	
	public void testRegularUserLogin() throws EncryptedDocumentException, Exception
	{
		try{
			String title = Tempdriver.getTitle();
			System.out.println(title);
		}catch(Exception e)
		{
			initializeBrowserForOpprotunitiesToFeedbackFlow("Edge", "admin", "simpleCRM@267");
			System.out.print("Edge browser is closed");
		}
		
		//initializeBrowserForOpprotunitiesToFeedbackFlow("Edge","admin","simpleCRM@267");
		dashboard=new Dashboard(Tempdriver);
		list_View =new Lead_ListView(Tempdriver);
		create_Lead=new Add_Lead(Tempdriver); 
		add_Opportunities = new Add_Opportunities(Tempdriver);
		add_Account = new Add_Account(Tempdriver);
		quotes = new Add_Quotes(Tempdriver);
		
		Thread.sleep(5000);
		dashboard.clickOnMenuOption();
		dashboard.clickOnSearch("Quotes");
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Thread.sleep(2000);
		dashboard.clickOnMenuOption(Tempdriver,"Quotes");
		Thread.sleep(2000);
		dashboard.closeMenuOption();
		Thread.sleep(5000);
		
	
	Thread.sleep(5000);
 	
	String input1="Quote1";
	
	//quotes.enterTextInSearchBtn(Tempdriver,input1);
	list_View.enterTextInSearchBtn(Tempdriver,input1);
	Thread.sleep(5000);
				 	
	String QuoteName=add_Opportunities.verifyOpportunityName(Tempdriver, input1);
	if(QuoteName.equals(input1))
	{
						
			add_Account.clickOnName(Tempdriver, input1);
			Tempdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
			quotes.clickOnEditBtn(Tempdriver);
			Tempdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			
			quotes.selectApprovalStatus1(Tempdriver);
			Thread.sleep(2000);
			//create_Lead.clickOnSavebtn1(Tempdriver);
			create_Lead.scrollpage(Tempdriver);
			create_Lead.clickOnSavebtn();
			Thread.sleep(2000);
	}
		BPM.CloseBrowser(Tempdriver);
		
	}
	
	
}
