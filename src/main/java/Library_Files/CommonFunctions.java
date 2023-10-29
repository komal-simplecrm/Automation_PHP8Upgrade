package Library_Files;

import java.awt.AWTException;
import java.io.IOException;
import java.text.ParseException;
import java.time.Duration;


import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;



import Login_Page.Dashboard;
import POM_Account_Module.Add_Account;
import POM_Account_Module.Duplicate_Account;
import POM_Calls_Module.Add_Calls;

import POM_Lead_Module.Add_Lead;
import POM_Lead_Module.Duplicate_Page;
import POM_Lead_Module.Lead_ListView;
import POM_Opportunities_Module.Add_Opportunities;
import POM_Quote_Module.Add_Quotes;

public class CommonFunctions extends Base_Class
{	
	
	//If click on copy from left check box verify the Permanent address and Alternate address both are same 
	//input1 - pass the Name of Record we need to search 
	public static void alternteAddress(Dashboard dashboard,Lead_ListView list_View, Duplicate_Page duplicate, String input1) throws InterruptedException, EncryptedDocumentException, IOException, AWTException
	{
		//Create this test case in Extent Report
		test= extent.createTest("Alternte Address").assignAuthor("Komal")
				.assignCategory("Functional Test Case").assignDevice("Chrome");
		test.info("If copy from left check box is checked verify the Permanent address and Alternate address both are same ");
		
		
		SoftAssert soft = new SoftAssert();
		//String input1="John";
		Thread.sleep(10000);
		list_View.enterTextInSearchBtn(driver,input1);
		Thread.sleep(5000);
		list_View.clickOnName(driver,input1);
		//dashboard.clickOnMenuOption();
		//UtilityClass.ZoomIn();
		list_View.menu(driver,"DUPLICATE");
		//UtilityClass.ZoomOut();
		if(duplicate.CopyFromleftCheckBox()==true)
		{
		int j=0,k=0;
		String arr1[]= {duplicate.getalterAddress(),duplicate.getalterState(),duplicate.getalterPostalCode(),duplicate.getalterCountry(),duplicate.getalterCity()};
		String arr2[]= {duplicate.getAddress(),duplicate.getState(),duplicate.getPostalCode(),duplicate.getCountry(),duplicate.getCity()};
		
		for(int i=0;i<=4;i++)
		{
			
			soft.assertEquals(arr1[j], arr2[k],"Failed: Both result are different");
			j++;
			k++;
			
		}
		soft.assertAll();
		}
		else {
			test.info("Primary Address and Other Address both are different");
			test.info("Printing Other Address in console");
			int j=0;
			
			String arr1[]= {duplicate.getalterAddress(),duplicate.getalterState(),duplicate.getalterPostalCode(),duplicate.getalterCountry(),duplicate.getalterCity()};
			for(int i=0;i<=4;i++)
			{
			
			System.out.println(arr1[j]);
			j++;
			}
		}
	}
	
	//input1 - pass the Name of Record we need to search 
	public static void DeleteRecordFromListView(Lead_ListView list_View,Add_Opportunities add_Opportunities, String input1, String ModuleName) throws InterruptedException
	{
		//Create this test case in Extent Report
		test= extent.createTest(ModuleName+ " Module Record Is Deleted From List View ").assignAuthor("Komal")
				.assignCategory("Functional Test Case").assignDevice("Chrome");
		
		test.info("User should delete the record from List View");
		
		//String input1="John";
		list_View.enterTextInSearchBtn(driver,input1);
		Thread.sleep(5000);
		String OppName=add_Opportunities.verifyOpportunityName(driver, input1);
		if(OppName.equals(input1))
		{
		
			list_View.selectCheckBox(driver, input1);
			Thread.sleep(5000);
			list_View.clickOnDelete();
			
			String msg=list_View.EveryPageAlert();
			 test.info(msg+ " Message showing on CRM");
			test.info(input1+ " Record is Deleted");
		}else
		{
			System.out.println("Record Name not matched");
			test.info(input1+ " record is not matched");
		}
		
	}
	
	
	public static void DeleteRecordFromEditOption(Dashboard dashboard, Lead_ListView list_View,Add_Opportunities add_Opportunities, String input1, String ModuleName) throws Exception
	{
		
		//Create this test case in Extent Report
		 test= extent.createTest(ModuleName+ " Module Record Is Deleted From Edit View").assignAuthor("Komal")
				.assignCategory("Functional Test Case").assignDevice("Chrome");
		
		 test.info("Delete Record from Edit Option");
		 
		
		// String input1="John";
		list_View.enterTextInSearchBtn(driver,input1);
		Thread.sleep(5000);
		String OppName=add_Opportunities.verifyOpportunityName(driver, input1);
		if(OppName.equals(input1))
		{
			add_Opportunities.clickOnOpp(driver, input1);
			Thread.sleep(5000);
			//dashboard.clickOnMenuOption();
			//UtilityClass.ZoomIn();
			list_View.menu(driver,"DELETE");
			//UtilityClass.ZoomOut();
			list_View.confirmDelete();
			String msg=list_View.EveryPageAlert();
			 test.info(msg+ " Message showing on CRM");			
			 test.info(input1+ " record is deleted");
		}
		else {
			System.out.println("Record Name not matched");
			test.info(input1+ " record is not matched");
		}
	}
	
	
	
	
	
	//Test case for Email setting 1st email id by default primary, second Opted out should be visible in detail view and 3rd email id invalid should not be displayed in detail view
	//sheetName - pass the Module name which is same as sheet name given to your test data sheet.
	//lastnameCellIndex - pass integer value of Cell Index for Last Name. 
	//emailAddressCellIndex - pass integer value of Cell Index for Email Address.
	//p = additionalEmailCellIndex;- pass integer value of Cell Index for Additional Email Address.
	public static void EmailSetting(Lead_ListView list_View, Add_Lead create_Lead, Add_Opportunities add_Opportunities, String sheetName,int lastnameCellIndex, int emailAddressCellIndex, int additionalEmailCellIndex ) throws EncryptedDocumentException, InterruptedException, IOException
	{
		//Create this test case in Extent Report
		test= extent.createTest(sheetName+ " Module Email Setting").assignAuthor("Komal")
				.assignCategory("Functional Test Case").assignDevice("Chrome");
		
		test.info("EmailID is Primary, Opted Out and Invalid");
		test.info(sheetName+ " Module Email Setting is verifyed");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		list_View.clickOnAddButton();
		if(sheetName.equals("Accounts"))
		
		{
				add_Opportunities.enterName(UtilityClass.fetchDataFromExcelSheet(sheetName,1,lastnameCellIndex));
		}
		else{
				create_Lead.enterLastName(driver,UtilityClass.fetchDataFromExcelSheet(sheetName,1, lastnameCellIndex));//1
			}
		
		create_Lead.enterEmailAddress(driver,UtilityClass.fetchDataFromExcelSheet(sheetName,1, emailAddressCellIndex));//8
		
		
		int k=0,l=11;
		int i=1;
		int p=additionalEmailCellIndex;//22
		for(int y=0;y<=1;y++)
		{
			
		create_Lead.SelectemailAddressSettingOption(driver,UtilityClass.fetchDataFromExcelSheet(sheetName,1, p),i,k,l);
		k++;
		l++;
		p++;
		i++;
		}
		
		Thread.sleep(2000);
		
		create_Lead.scrollpage(driver);
		create_Lead.clickOnSavebtn();
		test.info("1st emailId is by default Primary, 2nd emailId is Opted Out and 3rd Invalid emailID is not diplaying in the detail view");
	
			
	}
		//Test case to add multiple Email addresses
		//sheetName - pass the Module name which is same as sheet name given to your test data sheet.
		//firstnameCellIndex - pass integer value of Cell Index for Last Name. 
		//lastnameCellIndex - pass integer value of Cell Index for Last Name. 
		//emailAddressCellIndex - pass integer value of Cell Index for Email Address.
		//p = additionalEmailCellIndex;- pass integer value of Cell Index for Additional Email Address.
	public static void MultipleEmailAddress(Lead_ListView list_View, Add_Lead create_Lead,Add_Opportunities add_Opportunities, String sheetName, int lastnameCellIndex,int firstnameCellIndex, int emailAddressCellIndex, int additionalEmailCellIndex ) throws InterruptedException, EncryptedDocumentException, IOException
	{
		//Create this test case in Extent Report
		test= extent.createTest("In " +sheetName+ " Module Add Multiple Email Addresses").assignAuthor("Komal")
				.assignCategory("Functional Test Case").assignDevice("Chrome");
		
		test.info("Add Multiple Email Addresses");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		list_View.clickOnAddButton();
		if(sheetName.equals("Accounts"))
		{
				add_Opportunities.enterName(UtilityClass.fetchDataFromExcelSheet(sheetName,1,lastnameCellIndex));
				create_Lead.enterEmailAddress(driver,UtilityClass.fetchDataFromExcelSheet(sheetName,1, emailAddressCellIndex ));//18
		}
		else{
				create_Lead.enterFirstName(UtilityClass.fetchDataFromExcelSheet(sheetName,1, firstnameCellIndex));//1
				create_Lead.enterLastName(driver,UtilityClass.fetchDataFromExcelSheet(sheetName,1, lastnameCellIndex));//2
				create_Lead.enterEmailAddress(driver,UtilityClass.fetchDataFromExcelSheet(sheetName,1, emailAddressCellIndex ));//18
		}
		int k=0,l=11;
		int p=additionalEmailCellIndex;//30
		//add for loop to add maximum 5 email address
		for(int y=0;y<4;y++)
		{
			
		create_Lead.isPlusIconClicked(driver,UtilityClass.fetchDataFromExcelSheet(sheetName,1, p),k,l);
		k++;
		l++;
		p++;
		}
		create_Lead.scrollpage(driver);
		create_Lead.clickOnSavebtn();
		test.info("You can not add more than 5 email addresses");
	}
	
	
	
	//Test case for Add Remainder 3 time
	public static void Add_Remainder(Add_Calls calls,String priorTime, int InviteesCellIndex) throws EncryptedDocumentException, InterruptedException, IOException
	{
		//
		for(int i=0;i<=2;i++)
		{
			
			calls.clickOnAdd_Reminder();
			
			int k = InviteesCellIndex;//12
			String a[]= {"Users","Contacts","Leads"};
			for(int j=0;j<=2;j++)
			{
				calls.addInviteesInRemainder(driver, i, a[j], UtilityClass.fetchDataFromExcelSheet("calls", 1, k));//12
				
				k=k+2;																								//13
																													//14
			}
			
			calls.selectPopUpRadioBtnReminders(driver, priorTime,i);
			calls.selectEmailRadioBtnReminders(driver, priorTime,i);
			calls.scrollUpTotime(driver,i);
		}
	}
	
	
	public static void AddMulitpleInviteesInRemainder(Add_Calls calls, String priorTime, int InvteesCellIndex ) throws EncryptedDocumentException, InterruptedException, IOException
	{
		calls.clickOnAdd_Reminder();
		calls.scrolluptoRemainderWord(driver);
		String a[]= {"Users","Contacts","Leads"};
		//calls.addMoreUsers();
		int j=InvteesCellIndex;//13
		for(int i=0;i<=2;i++)
		{
			calls.addMultipleInviteesInRemainder(driver,a[i], UtilityClass.fetchDataFromExcelSheet("calls", 1, j));
			j=j+2;
		}
		Thread.sleep(5000);
		//calls.scrolluptoAssignedTo(driver);
		calls.selectPopUpRadioBtnReminders(driver, priorTime, 0);//0: 1st AddRmainder container
		calls.selectEmailRadioBtnReminders(driver, priorTime, 0);
		//calls.scrollUpToAdd_Reminder(driver);
	}
	
	public static void ShippingAddress(Lead_ListView list_View, Add_Account add_Account, Duplicate_Account duplicate_account,String input1,String sheetName ) throws InterruptedException, EncryptedDocumentException, IOException
	{
		//Create this test case in Extent Report
		test= extent.createTest("Shipping Address").assignAuthor("Komal")
						.assignCategory("Functional Test Case").assignDevice("Chrome");
		test.info("If copy from left check box is checked verify the Billing address and Shipping address both are same ");
				
		SoftAssert soft=new SoftAssert();
	
		//String input1="HDFC Finance";
		list_View.enterTextInSearchBtn(driver,input1);
		Thread.sleep(5000);
		add_Account.clickOnName(driver, input1);
		list_View.clickOnEditBtn();
		Thread.sleep(5000);
		duplicate_account.scrollpage(driver);
		if(duplicate_account.CopyFromleftCheckBox()==true)
		{
			int j=0,k=0;
			String arr1[]= {duplicate_account.getBillingAddress(),duplicate_account.getBillingState(),duplicate_account.getBillingPostalCode(),duplicate_account.getBillingCountry(),duplicate_account.getBillingCity()};
			String arr2[]= {duplicate_account.getShippingAddress(),duplicate_account.getShippingState(),duplicate_account.getShippingPostalCode(),duplicate_account.getShippingCountry(),duplicate_account.getShippingCity()};
	
			for(int i=0;i<=4;i++)
			{
		
				soft.assertEquals(arr1[j], arr2[k],"Failed: Both result are different");
		
				j++;
				k++;
		
			}
			soft.assertAll();
		}
		else {
				test.info("Billing Address and Shipping Address both are different");
				test.info("Printing Shipping Address in console");
				int j=0;
		
				String arr1[]= {duplicate_account.getShippingAddress(),duplicate_account.getShippingState(),duplicate_account.getShippingPostalCode(),duplicate_account.getShippingCountry(),duplicate_account.getShippingCity()};
				for(int i=0;i<=4;i++)
				{
		
					System.out.println(arr1[j]);
					j++;
				}
	}
	
	}
	
	//Mass update module assigned to Rahul Thakre
	public static void MassUpdateCaseModule(Lead_ListView list_View, String sheetName, String SearchText) throws InterruptedException, EncryptedDocumentException, IOException, AWTException, ParseException
	{
	//Create this test case in Extent Report
	 test= extent.createTest("Mass Update " +sheetName+ " Module").assignAuthor("Komal")
				.assignCategory("Functional Test Case").assignDevice("Chrome");
	 	//String Subject = "HDFC Finance - Service Request"; 
	 	String assignedTo = "rahul thakre";
	 	list_View.enterTextInSearchBtn(driver, SearchText);//1,9
	 	list_View.massUpdate(driver, SearchText, assignedTo /*UtilityClass.fetchDataFromExcelSheet("Cases",1, 8)*/);
	 	
	
	}
	
	//Export Functionality
	public static void ExportRecords(Lead_ListView list_View, String input1, String titleAttribute) throws InterruptedException
	{
		//Create this test case in Extent Report
		test= extent.createTest("Export functionality for "+titleAttribute).assignAuthor("Komal")
								.assignCategory("Functional Test Case").assignDevice("Chrome");
		
		//The title of icon of file in notification panel.				
		
		 test.info("Export Records On "+titleAttribute+" Module");
		
		list_View.enterTextInSearchBtn(driver, input1);
		Thread.sleep(5000);
		list_View.ExportAllRecords(driver, input1,titleAttribute );
		
	}
	
	//Get text function
	public static String GetText(WebElement element)
	{
		String text = element.getText();
		if(text.equals(""))
		{
			text = element.getAttribute("innerText");
			if(text.equals(""))
			{
				text = element.getAttribute("textContent");
			}
		}
		return text;
		
	}
	
	//Function created for Verifying values in Product Line items
		public static float ProductTotalPrice(Add_Quotes quotes) throws InterruptedException
		{
			SoftAssert soft = new SoftAssert();
					//get quantity
					String quantity = quotes.getQuantity(); 
					//get quantity convert it into float
					float qua = quotes.ConvertStringTofloat(quantity);
					//get List Price and convert it into float
					String ListPrice = quotes.getProductLineListPrice();
					float listprice = quotes.ConvertStringTofloat(ListPrice);
					//get discount and convert it into float
					String Discount = quotes.getDiscount();
					float discount = quotes.ConvertStringTofloat(Discount);
					System.out.println("Discount : "+discount);
					//get unit price and convert it into float
					String unitprice = quotes.getUnitPrice();
					float Unitprice = quotes.ConvertStringTofloat(unitprice);
					String Disctype = quotes.getDiscountType();
					System.out.println("Discount Type: "+Disctype);
					//calculate unit price
					float UnitPrice = 0;
					if(Disctype.equalsIgnoreCase("Pct"))
					{

					UnitPrice = listprice - (listprice * discount / 100);

					}
					else if(Disctype.equalsIgnoreCase("Amt"))
					{
					
						UnitPrice = listprice - discount;
						
					}
					System.out.println("Unit Price  : "+UnitPrice);
					soft.assertEquals(Unitprice, UnitPrice);
					//Calculate Total Amount Without Tax
					float Total_amt_without_Tax = UnitPrice*qua;
					System.out.println("Total amt without Tax for Product Line: "+Total_amt_without_Tax);
					//get Tax Amount and convert it into float
					String taxAmount = quotes.getTaxAmount();
					float TaxAmount = quotes.ConvertStringTofloat(taxAmount);
					//get Tax and convert it into float
					String tax = quotes.getProductLineTax();
					float Taxamount = quotes.ConvertStringTofloat(tax);
					//Calculate Tax Amount
					float Tax_Amt = (UnitPrice*qua*Taxamount)/100;
					System.out.println("Tax Amount for Product Line: "+Tax_Amt);
					soft.assertEquals(TaxAmount, Tax_Amt);
					//Calculate Total Amount
					String totalProductAmount = quotes.getTotalProductPrice();
					float TotalAmt = quotes.ConvertStringTofloat(totalProductAmount);
					float Total_Amt_with_Tax = Tax_Amt+Total_amt_without_Tax;
					System.out.println("Total Amout With Tax for Product Line: "+Total_Amt_with_Tax);
					soft.assertEquals(TotalAmt, Total_Amt_with_Tax);
					soft.assertAll();
					return Total_Amt_with_Tax;
				
		}
		
		//Function created for Verifying values in Service Line items
		public static float ServiceLineTotalPrice(Add_Quotes quotes) throws InterruptedException
		{
			SoftAssert soft = new SoftAssert();
				//get Service line List Price and covert into float
				String listPrice = quotes.getServiceLineListPrice();
				float ListPrice = quotes.ConvertStringTofloat(listPrice);
				//get Service Line Discount 
				String Discount = quotes.getServiceLineDiscount();
				float discount = quotes.ConvertStringTofloat(Discount);
				System.out.println("Discount : "+discount);
				//get unit price and convert it into float
				String unitprice = quotes.getServiceLineUnitPrice();
				float Unitprice = quotes.ConvertStringTofloat(unitprice);
				String Disctype = quotes.getServiceLineDiscountType();
				System.out.println("Discount Type: "+Disctype);
							
				float Unit_price = 0;
				if(Disctype.equalsIgnoreCase("Pct"))
				{

					Unit_price = ListPrice - (ListPrice * discount / 100);

				}
				else if(Disctype.equalsIgnoreCase("Amt"))
				{
							
					Unit_price = ListPrice - discount;
								
				}
							
				System.out.println("Unit Price  : "+Unit_price);
				soft.assertEquals(Unitprice, Unit_price);
							
				//get Tax Amount and convert it into float
				String taxAmount = quotes.getServiceLineTaxAmount();
				float TaxAmount = quotes.ConvertStringTofloat(taxAmount);
				//get Tax and convert it into float
				String tax = quotes.getServiceLineTax();
				float Taxamount = quotes.ConvertStringTofloat(tax);
				//Calculate Tax Amount
				float Tax_Amt = (Unit_price*Taxamount)/100;
				System.out.println("Tax Amount for Service Line: "+Tax_Amt);
				soft.assertEquals(TaxAmount, Tax_Amt);
				//Calculate Total Amount
				String totalProductAmount = quotes.getServiceLineTotalPrice();
				float TotalAmt = quotes.ConvertStringTofloat(totalProductAmount);
				float Total_Amt_with_Tax = Unit_price+Tax_Amt;
				System.out.println("Total Amout With Tax for Service Line: "+Total_Amt_with_Tax);
				soft.assertEquals(TotalAmt, Total_Amt_with_Tax);
				soft.assertAll();
				return Total_Amt_with_Tax;
					
		}
		
		//Function to combine all calculation part in one
		public static void AllCalculations(Add_Quotes quotes, Lead_ListView list_View, Add_Account add_Account, Add_Opportunities add_Opportunities, String ModuleName, String RecordName ) throws InterruptedException
		{
			
						
			SoftAssert soft = new SoftAssert();
			test.info(RecordName+" name entered in the search field.");
			String input1=RecordName;
			list_View.enterTextInSearchBtn(driver,input1);
			Thread.sleep(5000);
			
			String OppName=add_Opportunities.verifyOpportunityName(driver, input1);
			if(OppName.equals(input1))
			{
								
					add_Account.clickOnName(driver, input1);
					test.info("Click on edit button");
					list_View.clickOnEditBtn();
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
					test.info("Check the Product line item is present or not.");
					String displayed = quotes.getProductLineItems();
					System.out.println("displayed: " +displayed);
					test.info("Check the Service line item is present or not.");
					String displayed1 = quotes.getServiceLineItems();
					String displayed2 = displayed1.substring(0,4);
					System.out.println("displayed1: " +displayed2);
					float totalProductLine = 0;
					float totalServiceLine = 0 ;
					if(displayed.equalsIgnoreCase("Quantity"))
					{
						System.out.println("Enter in the Product Line");
						test.info("Call function of Product Line item calculations.");
						totalProductLine= ProductTotalPrice(quotes);
					}
					else 
					{
						System.out.println("Product Line Items are not there.");
					}
					if(displayed2.equalsIgnoreCase("Name"))
					{
						System.out.println("Enter in the Service Line");
						test.info("Call function of Service Line item calculations.");
						totalServiceLine = ServiceLineTotalPrice(quotes);
					}
					else
					{
						System.out.println("Service Line Items are not there.");
					}
					float Total = totalProductLine + totalServiceLine;
					System.out.println("Grand Total: "+Total);
					test.info("Grand Total: "+Total);
					String Grandtotal = quotes.getGrandTotal();
					float grandTotal = quotes.ConvertStringTofloat(Grandtotal);
					
					soft.assertEquals(Total, grandTotal);
					String ShippingAmt = quotes.getShippingAmount();
					float shippingAmt = quotes.ConvertStringTofloat(ShippingAmt);
				
					String ShippingTax = quotes.getShippingTax();
					float shippingtax = quotes.ConvertStringTofloat(ShippingTax);
					float shippingTaxAmt =( shippingAmt * shippingtax) / 100;
					float GrandTotal = Total +shippingAmt + shippingTaxAmt;
					System.out.println("Grand Total Calculate: "+GrandTotal);
					test.info("Final Grand Total: "+GrandTotal);
					String FinalGrandTotal = quotes.FinalGrandTotal();
					float Finalgrandtotal = quotes.ConvertStringTofloat(FinalGrandTotal);
					soft.assertEquals(Finalgrandtotal, GrandTotal);
					soft.assertAll();
			}
			else
			{
					System.out.println(ModuleName+" Name not matched");
					test.info(input1+ " record is not matched");
					
			}
		}
}
