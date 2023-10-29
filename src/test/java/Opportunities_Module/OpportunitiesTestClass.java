package Opportunities_Module;

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

import POM_Lead_Module.Add_Lead;
import POM_Lead_Module.Duplicate_Page;
import POM_Lead_Module.Lead_ListView;
import POM_Opportunities_Module.Add_Opportunities;
import POM_Opportunities_Module.Duplicate_Opp_Page;


public class OpportunitiesTestClass extends Base_Class
{
	
	Dashboard dashboard;
	
	Lead_ListView list_View;
	Add_Lead create_Lead;
	Add_Opportunities add_Opportunities;
	Duplicate_Opp_Page duplicate_opp;
	Duplicate_Page duplicate;
	
	@BeforeMethod(alwaysRun = true)
	public void OpenBrowser() throws IOException, InterruptedException 
	{
		//Create object of Pom classes
		dashboard =new Dashboard(driver);
		
		list_View= new Lead_ListView(driver);
		add_Opportunities= new Add_Opportunities(driver);
		create_Lead= new Add_Lead(driver);
		duplicate_opp = new Duplicate_Opp_Page(driver);
		duplicate= new Duplicate_Page(driver);
		
		
				
		//directed to the Dashboard page
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//Thread.sleep(2000);
		dashboard.clickOnMenuOption();
		dashboard.clickOnSearch("Opportunities");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		//Thread.sleep(5000);
		dashboard.clickOnMenuOption(driver,"Opportunities");
		dashboard.closeMenuOption();
		Thread.sleep(4000);
	}
	
	//Create test case for add multiple records in Opportunity module
	@Test( groups={"Create", "Sanity"} )
	public void CreateOpportunities() throws Exception 
	{
		//Create this test case in Extent Report
		 test= extent.createTest("Create Opportunities").assignAuthor("Komal")
				.assignCategory("Functional Test Case").assignDevice("Chrome");
		
		 test.info("Create multiple Opportunities");
		
		for(int i=1;i<=10;i++)
		{
			
			SoftAssert soft=new SoftAssert();
		try {
				Thread.sleep(5000);
				list_View.clickOnAddButton();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				String OppName = add_Opportunities.enterName(UtilityClass.fetchDataFromExcelSheet("Opportunity",i, 0));
				soft.assertNotNull(OppName);
				 
				add_Opportunities.enterAccountName(driver, UtilityClass.fetchDataFromExcelSheet("Opportunity",i, 1));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				String date = add_Opportunities.enterDate_closed(driver,UtilityClass.fetchDataFromExcelSheet("Opportunity",i, 3) );
				soft.assertNotNull(date);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				String OppAmount = add_Opportunities.enterOpportunityAmount(UtilityClass.fetchDataFromExcelSheet("Opportunity",i, 4));
				soft.assertNotNull(OppAmount);
				
				add_Opportunities.selectType(driver, UtilityClass.fetchDataFromExcelSheet("Opportunity",i, 5));
				String Stages = add_Opportunities.selectSalesStage(driver, UtilityClass.fetchDataFromExcelSheet("Opportunity",i, 6),
						UtilityClass.fetchDataFromExcelSheet("Opportunity",i, 12),UtilityClass.fetchDataFromExcelSheet("Opportunity",i, 14),
						UtilityClass.fetchDataFromExcelSheet("Opportunity",i, 11),UtilityClass.fetchDataFromExcelSheet("Opportunity",i, 13));
				soft.assertNotNull(Stages);
				soft.assertAll();
				create_Lead.selectLeadSource(driver, UtilityClass.fetchDataFromExcelSheet("Opportunity",i, 7));
				add_Opportunities.enterProbability(UtilityClass.fetchDataFromExcelSheet("Opportunity",i, 8));
				//create_Lead.enterCampaign(driver, UtilityClass.fetchDataFromExcelSheet("Opportunity",i, 9));
				add_Opportunities.enterNext_Step(UtilityClass.fetchDataFromExcelSheet("Opportunity",i, 10));
				create_Lead.scrollpage(driver);
				create_Lead.AssignedTo(driver, UtilityClass.fetchDataFromExcelSheet("Opportunity",i, 15));
				
				create_Lead.clickOnSavebtn();
				test.info(OppName+ " Opportunities is created.");
				//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				Thread.sleep(7000);
				add_Opportunities.backToListView();
				Thread.sleep(4000);
				list_View.scrolluptoAddBtn(driver);
			
			}catch(NullPointerException e) 
				{
					System.out.println("NullPointerException thrown!");
					create_Lead.clickOnSavebtn();
				}
		
		}
	}
	
	//Test case for Edit functionality
		@Test( groups= {"Edit", "Sanity"})
		public void editOpportunity() throws Exception
		{
			//Create this test case in Extent Report
			 test= extent.createTest("Edit Opportunity").assignAuthor("Komal")
					.assignCategory("Functional Test Case").assignDevice("Chrome");
			
			 test.info("User should Edit the Lead");
			 
			/*System.out.println("Enter Opportunity Name: ");
			Scanner scan1 = new Scanner(System.in);
			String input1 = scan1.nextLine();*/
			 String input1="Nidhi";
				list_View.enterTextInSearchBtn(driver,input1);
				Thread.sleep(5000);
			String OppName=add_Opportunities.verifyOpportunityName(driver, input1);
			if(OppName.equals(input1))
			{
				add_Opportunities.clickOnOpp(driver, input1);
				list_View.clickOnEditBtn();
				add_Opportunities.enterName(UtilityClass.fetchDataFromExcelSheet("Opportunity",2, 0));
				add_Opportunities.enterDate_closed(driver,UtilityClass.fetchDataFromExcelSheet("Opportunity",2, 3) );
				add_Opportunities.enterOpportunityAmount(UtilityClass.fetchDataFromExcelSheet("Opportunity",2, 4));
				add_Opportunities.selectSalesStage(driver, UtilityClass.fetchDataFromExcelSheet("Opportunity",2, 6),
						UtilityClass.fetchDataFromExcelSheet("Opportunity",2, 12),UtilityClass.fetchDataFromExcelSheet("Opportunity",2, 14),
						UtilityClass.fetchDataFromExcelSheet("Opportunity",2, 11),UtilityClass.fetchDataFromExcelSheet("Opportunity",2, 13));
				create_Lead.scrollpage(driver);
				create_Lead.clickOnSavebtn();
				test.info(input1+ " record is editted");
			}
			else
			{
				System.out.println("Opportunity Name not matched");
				test.info(input1+ " record is not matched");
			}
		
		}
		
		
		
	//Test case for Delete Lead From List View functionality
	@Test(groups= {"DeleteFromListView", "Sanity"}, dependsOnMethods={"CreateOpportunities"})
	public void DeleteOpportunityFromListView() throws Exception
	{
		CommonFunctions.DeleteRecordFromListView(list_View, add_Opportunities, "Rahul", "Opportunity");
		
	}
	
	//Test case for Delete From Edit Option functionality
	@Test(groups= {"DeleteFromEditView", "Sanity"}, dependsOnMethods={"CreateOpportunities"})
	public void DeleteOpportunityFromEditOption() throws Exception
	{
		CommonFunctions.DeleteRecordFromEditOption(dashboard,list_View, add_Opportunities, "Komal", "Opportunity");
		
	}
	@Test(groups= {"Duplicate"})
	public void DuplicateOpportunity() throws InterruptedException, EncryptedDocumentException, IOException, ParseException
	{
		//Create this test case in Extent Report
		 test= extent.createTest("Verify Opportunity Is Duplicate?").assignAuthor("Komal")
				.assignCategory("Functional Test Case").assignDevice("Chrome");
		
		 test.info("Verify Opportunity is duplicate or not");
		
		
		SoftAssert soft=new SoftAssert();
		
		String input1="John";
		list_View.enterTextInSearchBtn(driver,input1);
		Thread.sleep(5000);
		add_Opportunities.clickOnOpp(driver, input1);
		list_View.menu(driver,"DUPLICATE");
		
		
		String ExactlostDate = null;
		String ExactActual_date_closed = null;
		
		String[] date = duplicate_opp.getDate_closed(); 
		String ExactclosedDate = duplicate_opp.dateCompare(date);
		
		String salesStage = duplicate_opp.getSalesStage();
		String SlaesStages="Closed Lost";
		if(salesStage.equals(SlaesStages))
		{
			ExactActual_date_closed = "";
			//String[] date2 = duplicate_opp.getActual_date_closed(); 
			//ExactActual_date_closed = duplicate_opp.dateCompare(date2);
			String[] date1 = duplicate_opp.getDate_lost(); 
			ExactlostDate = duplicate_opp.dateCompare(date1);
		
		}else
		{
			String[] date2 = duplicate_opp.getActual_date_closed(); 
			ExactActual_date_closed = duplicate_opp.dateCompare(date2);
		}
		
		int j=0,k=0;
		String arr1[]= {duplicate_opp.getOpportunityName(),duplicate_opp.getAccountName(),duplicate_opp.getCurrency(),
				ExactclosedDate,duplicate_opp.getOpportunityAmount(), duplicate_opp.getType(),duplicate_opp.getSalesStage(), duplicate.getLeadSource()
				,duplicate_opp.getProbability(),/*duplicate.getCampaign()*/ duplicate_opp.getNext_Step(),ExactActual_date_closed, ExactlostDate,
				duplicate_opp.getDescription(),duplicate_opp.getReason_for_lost()};
		String arr2[]= {UtilityClass.fetchDataFromExcelSheet("Opportunity",1, 0),UtilityClass.fetchDataFromExcelSheet("Opportunity",1, 1),UtilityClass.fetchDataFromExcelSheet("Opportunity",1, 2),
				UtilityClass.fetchDataFromExcelSheet("Opportunity",1, 3),UtilityClass.fetchDataFromExcelSheet("Opportunity",1, 4),UtilityClass.fetchDataFromExcelSheet("Opportunity",1, 5),
				UtilityClass.fetchDataFromExcelSheet("Opportunity",1, 6),UtilityClass.fetchDataFromExcelSheet("Opportunity",1, 7),UtilityClass.fetchDataFromExcelSheet("Opportunity",1, 8),
				/*UtilityClass.fetchDataFromExcelSheet("Opportunity",1, 9)*/UtilityClass.fetchDataFromExcelSheet("Opportunity",1, 10),UtilityClass.fetchDataFromExcelSheet("Opportunity",1, 11),
				UtilityClass.fetchDataFromExcelSheet("Opportunity",1, 12),UtilityClass.fetchDataFromExcelSheet("Opportunity",1, 13),UtilityClass.fetchDataFromExcelSheet("Opportunity",1, 14),
				};
		
		for(int i=0;i<14;i++)
		{
			
			soft.assertEquals(arr1[j], arr2[k],"Failed: Both result are different");
			
			j++;
			k++;
			
		}
		soft.assertAll();
		
		
	}
	
	//Test case for Export functionality
	@Test( groups = {"Export", "Sanity"})
	public void ExportRecordsForOpportunities() throws Exception
	{
		
		 //Call function from Common Function class
		 CommonFunctions.ExportRecords(list_View, "John", "Opportunities");
	}
	
	//Test case for Mass update functionality
	@Test( groups={"MassUpdate", "Sanity"})
	public void MassUpdateOpportunities() throws InterruptedException, EncryptedDocumentException, IOException, AWTException, ParseException
	{
		
		CommonFunctions.MassUpdateCaseModule(list_View, "Opportunities", "John");
		
	}
}
