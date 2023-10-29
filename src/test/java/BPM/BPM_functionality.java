package BPM;



import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Library_Files.Base_Class;
import Library_Files.UtilityClass;
import Login_Page.Dashboard;

import POM_BPM.BPM_Elements;

import POM_Cases_Module.Create_Case;
import POM_Cases_Module.Duplicate_Case;

import POM_Lead_Module.Add_Lead;

import POM_Lead_Module.Lead_ListView;

import POM_Opportunities_Module.Add_Opportunities;



public class BPM_functionality extends Base_Class
{
	
	boolean tasks;
	Create_Case cases;
	Dashboard dashboard;
	Lead_ListView list_View;
	Add_Opportunities add_Opportunities;
	Add_Lead create_Lead;
	Duplicate_Case duplicate_case;
	BPM_Elements BPM;
	
	@BeforeMethod
	public void OpenBrowser() throws InterruptedException
	{
	
		//Create object of Create_Case Pom class
		cases = new Create_Case(driver);
		dashboard=new Dashboard(driver);
		list_View =new Lead_ListView(driver);
		add_Opportunities= new Add_Opportunities(driver);
		create_Lead=new Add_Lead(driver);
		duplicate_case = new Duplicate_Case(driver);
		BPM = new BPM_Elements(driver);
		
		//directed to the dash board page
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Thread.sleep(2000);
		dashboard.clickOnMenuOption();
		dashboard.clickOnSearch("Cases");
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Thread.sleep(4000);
		dashboard.clickOnMenuOption(driver,"Cases");
	}
	
	//Create test case for create multiple Cases
		@Test(enabled = false, groups={"BPM"})
		public void BPM_Flow() throws Exception 
		{
			//Create this test case in Extent Report
			test= extent.createTest("BPM_Flow").assignAuthor("Komal")
						.assignCategory("Functional Test Case").assignDevice("Chrome");
			
			SoftAssert soft=new SoftAssert();
			Thread.sleep(5000);
			try{
			list_View.clickOnAddButton();
			cases.selectPriority(driver, UtilityClass.fetchDataFromExcelSheet("Cases",1, 0));
			cases.selectState(driver, UtilityClass.fetchDataFromExcelSheet("Cases",1, 1), UtilityClass.fetchDataFromExcelSheet("Cases",1, 2),UtilityClass.fetchDataFromExcelSheet("Cases",1, 7));
			cases.selectType(driver, UtilityClass.fetchDataFromExcelSheet("Cases",1, 3));
			add_Opportunities.enterAccountName(driver,UtilityClass.fetchDataFromExcelSheet("Cases",1, 4));
			cases.clickOnGeneralFeedbackforSubject();
			String subject=duplicate_case.getSubject();
			soft.assertNotNull(subject);
			
			cases.enterLinkedin_account(UtilityClass.fetchDataFromExcelSheet("Cases",1, 5));
			//cases.enterNote(driver,UtilityClass.fetchDataFromExcelSheet("Cases",1, 6)); Field is removed from this instance
			
			Thread.sleep(2000);
			create_Lead.AssignedTo(driver, UtilityClass.fetchDataFromExcelSheet("Cases",1, 8));
			
			create_Lead.clickOnSavebtn();
			Thread.sleep(5000);
			String casenumber = duplicate_case.getDetailViewText(driver,"Number");
			String status = duplicate_case.getDetailViewStatus();
			test.info(casenumber+ " , " +subject+ " Case is created manually.");
			//status = New
			soft.assertEquals(status, "New");
			
			//cases.clickActivitiesSubPanel();
			//String task = add_Account.VerifyOpportunitySubPanelName(driver, "activities");
			//System.out.println("task is in Activity sub panel" +task);
			
			list_View.rightSideMenu(driver, "BPM Process");
			test.info("Clicked on BPM icon on right side bar.");
			//BPM.checkElementPresence(driver);
			String BPM_Name = BPM.getBPM_Name();
			test.info("BPM is triggered: " +BPM.getBPM_Name());
			//System.out.println(BPM_Name);
			String assignedTo = BPM.getAssignedToOnBPM(driver,0);
			System.out.println(assignedTo);
			test.info("task is assigned to " +assignedTo);
			String ribbon = BPM.getRibbonName(driver, 0);
			test.info(ribbon+ " status on Ribbon.");
			String[] Username = BPM.User(assignedTo);
			String UN = Username[0];
			String Pass = Username[1];
			
			initializeBrowserForOpprotunitiesToFeedbackFlow("Edge", UN, Pass);
			
			BPM = new BPM_Elements(Tempdriver);
			dashboard = new Dashboard(Tempdriver) ;
			list_View =new Lead_ListView(Tempdriver);
			cases = new Create_Case(Tempdriver);
			
			//Diverted towards Cases module on Edge browser using Nidhi user
			Thread.sleep(5000);
			test.info("Driver diverted towards Cases module.");	
			dashboard.clickOnMenuOptionDashboard(Tempdriver);
			dashboard.clickOnSearch("Cases");
			Tempdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(70));
			dashboard.clickOnMenuOption(Tempdriver,"Cases");
			
			
			Thread.sleep(5000);
			
			dashboard.clickOnMenuOption();
			
			Thread.sleep(5000);
			list_View.clickSettingInListView(Tempdriver,"Filter",casenumber,UtilityClass.fetchDataFromExcelSheet("Cases",1, 9),"Case");
		  	cases.clickOnSubject(Tempdriver, casenumber, UtilityClass.fetchDataFromExcelSheet("Cases",1, 9));
			Thread.sleep(2000);	
			list_View.rightSideMenu(Tempdriver, "BPM Process");
			soft.assertEquals(BPM_Name, "CASES-SERVICE REQUEST-NEW");
			
			String Title = BPM.getTitleOfTaskOnBPM(Tempdriver,0);
			System.out.println(Title);
			test.info(Title+ " of sub panel on BPM.");
			
			String cond = BPM.selectConditionalTask(Tempdriver,"Meeting Successful",0);//pass the conditional task of BPM flow
			System.out.println(cond);
			test.info(cond+ " Conditional task selected.");
			
			if("Meeting Successful".equalsIgnoreCase(cond)) 
			{
				Thread.sleep(4000);
				BPM.clickOnNextStep(Tempdriver,0);
				Thread.sleep(4000);
				BPM.hideBPMPanel(Tempdriver, 0, ribbon);
				String msg=list_View.EveryPageAlert();
				test.info(msg+ " Message showing on CRM");
				Thread.sleep(7000);
				Tempdriver.close();
				Thread.sleep(5000);
				driver.navigate().refresh();
				
				Thread.sleep(2000);
				list_View.rightSideMenu(driver, "BPM Process");
				String Title1 = BPM.getTitleOfTaskOnBPM(driver,1);
				System.out.println(Title1);
				
				test.info(Title1+ " of sub panel on BPM.");
				//Thread.sleep(2000);
				String assignedTo1 = BPM.getAssignedToOnBPM(driver,1);
				System.out.println(assignedTo1);
				test.info("task is assigned to " +assignedTo1);
				//Thread.sleep(2000);
				String cond1 = BPM.selectConditionalTask(driver,"Documents required",1);//pass the conditional task of BPM flow
				System.out.println(cond1);
				test.info(cond1+ " Conditional task selected.");
				//Thread.sleep(2000);
				BPM.clickOnNextStep(driver,1);
				//Thread.sleep(4000);
				String ribbon1 = BPM.getRibbonName(driver, 1);
				test.info(ribbon1+ " status on Ribbon.");
				Thread.sleep(2000);
				BPM.hideBPMPanel(driver, 1, ribbon1);
				//String msg1=list_View.EveryPageAlert();
				//test.info(msg1+ " Message showing on CRM");
				String Title2 = BPM.getTitleOfTaskOnBPM(driver,2);
				System.out.println(Title2);
				test.info(Title2+ " of sub panel on BPM.");
				String assignedTo2 = BPM.getAssignedToOnBPM(driver,2);
				System.out.println(assignedTo2);
				test.info("task is assigned to " +assignedTo2);
				BPM.clickOnNextStep(driver,2);
				//Thread.sleep(4000);
				String ribbon2 = BPM.getRibbonName(driver, 2);
				test.info(ribbon2+ " status on Ribbon.");
				BPM.hideBPMPanel(driver, 2, ribbon2);
				driver.navigate().refresh();
				list_View.rightSideMenu(driver, "BPM Process");
				//String msg2=list_View.EveryPageAlert();
				//test.info(msg2+ " Message showing on CRM");
				String Title3 = BPM.getTitleOfTaskOnBPM(driver,3);
				System.out.println(Title3);
				test.info(Title3+ " of sub panel on BPM.");
				String assignedTo3 = BPM.getAssignedToOnBPM(driver,3);
				System.out.println(assignedTo3);
				test.info("task is assigned to " +assignedTo3);
				String cond2 = BPM.selectConditionalTask(driver,"Documents are ok and send the documents for approval",3);//pass the conditional task of BPM flow
				System.out.println(cond2);
				test.info(cond2+ " Conditional task selected.");
				BPM.clickOnNextStep(driver,3);
				Thread.sleep(4000);
				String ribbon3 = BPM.getRibbonName(driver, 3);
				test.info(ribbon3+ " status on Ribbon.");
				BPM.hideBPMPanel(driver, 3, ribbon3);
				//String msg3=list_View.EveryPageAlert();
				//test.info(msg3+ " Message showing on CRM");
				String Title4 = BPM.getTitleOfTaskOnBPM(driver,4);
				System.out.println(Title4);
				test.info(Title4+ " of sub panel on BPM.");
				String assignedTo4 = BPM.getAssignedToOnBPM(driver,4);
				System.out.println(assignedTo4);
				test.info("task is assigned to " +assignedTo4);
				String[] Username1 = BPM.User(assignedTo4);
				String UN1 = Username1[0];
				String Pass1 = Username1[1];
				initializeBrowserForOpprotunitiesToFeedbackFlow("Edge", UN1, Pass1);
				BPM = new BPM_Elements(Tempdriver);
				dashboard = new Dashboard(Tempdriver) ;
				list_View =new Lead_ListView(Tempdriver);
				cases = new Create_Case(Tempdriver);
				
				//Diverted towards Cases module on Edge browser using Nidhi user
				Thread.sleep(5000);
				test.info("Driver diverted towards Cases module.");	
				dashboard.clickOnMenuOptionDashboard(Tempdriver);
				dashboard.clickOnSearch("Cases");
				Tempdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(70));
				dashboard.clickOnMenuOption(Tempdriver,"Cases");
				
				
				Thread.sleep(5000);
				
				dashboard.clickOnMenuOption();
				
				Thread.sleep(5000);
				list_View.clickSettingInListView(Tempdriver,"Filter",casenumber,UtilityClass.fetchDataFromExcelSheet("Cases",1, 9),"Case");
			  	cases.clickOnSubject(Tempdriver, casenumber, UtilityClass.fetchDataFromExcelSheet("Cases",1, 9));
				Thread.sleep(2000);	
				list_View.rightSideMenu(Tempdriver, "BPM Process");
				String Title5 = BPM.getTitleOfTaskOnBPM(Tempdriver,4);
				System.out.println(Title5);
				test.info(Title5+ " of sub panel on BPM.");
				BPM.clickOnNextStep(Tempdriver,4);
				Thread.sleep(4000);
				String ribbon4 = BPM.getRibbonName(Tempdriver, 4);
				test.info(ribbon4+ " status on Ribbon.");
				BPM.hideBPMPanel(Tempdriver, 4, ribbon4);
				//String msg4=list_View.EveryPageAlert();
				//test.info(msg4+ " Message showing on CRM");
				String Title6 = BPM.getTitleOfTaskOnBPM(Tempdriver,5);
				System.out.println(Title6);
				test.info(Title6+ " of sub panel on BPM.");
				BPM.clickOnNextStep(Tempdriver,5);
				Thread.sleep(4000);
				String ribbon5 = BPM.getRibbonName(Tempdriver, 5);
				test.info(ribbon5+ " status on Ribbon.");
				BPM.hideBPMPanel(Tempdriver, 5, ribbon5);
				//String msg5=list_View.EveryPageAlert();
				//test.info(msg5+ " Message showing on CRM");
			}
			
			}catch(NullPointerException e) 
			{
				System.out.println("NullPointerException thrown!");
				
			}
			
		}
		
		
		@Test()
		public void test() throws EncryptedDocumentException, InterruptedException, IOException
		{
			driver.get("https://hackatonteam1.simplecrmdev.com/app/detailview/Cases/c639f8c2-5b56-e029-b4a1-63fc806a98d9");
			list_View.rightSideMenu(driver, "BPM Process");
			
			int i = 0;
			//for(int i=0;i<i+1;i++)
			//{
				BPM.checkElementPresence(driver,i);
				tasks = BPM.CheckCondTask(driver, i);
				System.out.println(tasks);
			try {	
				String assignedTo = BPM.getAssignedToOnBPM(driver,i);
				String[] Username1 = BPM.User(assignedTo);
				String UN = Username1[0];
				String Pass = Username1[1];
				String currentuser = BPM.currentProfile(driver);
				if(assignedTo==currentuser)
				{
					if(tasks==true)
					{
						String cond1 = BPM.selectConditionalTask(driver,UtilityClass.fetchDataFromExcelSheet("Cases",9, 0),i);//pass the conditional task of BPM flow
						System.out.println(cond1);
					
					
					}
					BPM.clickOnNextStep(driver,i);
				}
			
			else{
					initializeBrowserForOpprotunitiesToFeedbackFlow("Edge", UN, Pass);
					BPM = new BPM_Elements(Tempdriver);
					dashboard = new Dashboard(Tempdriver) ;
					list_View =new Lead_ListView(Tempdriver);
					cases = new Create_Case(Tempdriver);
				
					Thread.sleep(5000);
				    Tempdriver.navigate().refresh();
					//Diverted towards Cases module on Edge browser using assignedTO user profile
					Thread.sleep(7000);
					
					dashboard.clickOnMenuOptionDashboard(Tempdriver);
					dashboard.clickOnSearch("Cases");
					Tempdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(70));
					dashboard.clickOnMenuOption(Tempdriver,"Cases");
				
				
					Thread.sleep(5000);
				
					dashboard.clickOnMenuOption();
				
					Thread.sleep(5000);
					list_View.clickSettingInListView(Tempdriver,"Filter","236","test","Case");
					cases.clickOnSubject(Tempdriver, "236","test");
					Thread.sleep(2000);	
					list_View.rightSideMenu(Tempdriver, "BPM Process");
				
					if(tasks==true)
					{
						String cond1 = BPM.selectConditionalTask(Tempdriver,UtilityClass.fetchDataFromExcelSheet("Cases",9, 0),i);//pass the conditional task of BPM flow
						System.out.println(cond1);
					
					
					}
					BPM.clickOnNextStep(Tempdriver,i);
				
				
				
				}
		
			
			}catch(NullPointerException e) 
			{
				System.out.println("NullPointerException thrown!");
				
			}
		}
	//}
		
		@Test(enabled= false)
		public void test1() throws InterruptedException, EncryptedDocumentException, IOException
		{
			driver.get("https://hackatonteam1.simplecrmdev.com/app/detailview/Cases/e92b44b7-c827-95d8-cdff-63fc521b1f89");
			list_View.rightSideMenu(driver, "BPM Process");
			try {
			 tasks = BPM.CheckCondTask(driver, 0);
			String assignedTo = BPM.getAssignedToOnBPM(driver,0);
			String[] Username1 = BPM.User(assignedTo);
			String UN = Username1[0];
			String Pass = Username1[1];
			initializeBrowserForOpprotunitiesToFeedbackFlow("Edge", UN, Pass);
			BPM = new BPM_Elements(Tempdriver);
			dashboard = new Dashboard(Tempdriver) ;
			list_View =new Lead_ListView(Tempdriver);
			cases = new Create_Case(Tempdriver);
			Thread.sleep(5000);
		    Tempdriver.navigate().refresh();
			//Tempdriver.navigate().refresh();
			//Diverted towards Cases module on Edge browser using assignedTO user profile
			Thread.sleep(5000);
			test.info("Driver diverted towards Cases module.");	
			dashboard.clickOnMenuOption();
			dashboard.clickOnSearch("Cases");
			Tempdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(70));
			dashboard.clickOnMenuOption(Tempdriver,"Cases");
		
		
			Thread.sleep(5000);
		
			dashboard.clickOnMenuOption();
		
			Thread.sleep(5000);
			list_View.clickSettingInListView(Tempdriver,"Filter","233","test","Case");
			cases.clickOnSubject(Tempdriver, "233","test");
			Thread.sleep(2000);	
			list_View.rightSideMenu(Tempdriver, "BPM Process");
		
			if(tasks==true)
			{
				String cond1 = BPM.selectConditionalTask(Tempdriver,UtilityClass.fetchDataFromExcelSheet("Cases",9, 0),0);//pass the conditional task of BPM flow
				System.out.println(cond1);
			
			
			}
			BPM.clickOnNextStep(Tempdriver,0);
		
			
		}catch(NullPointerException e) 
		{
			System.out.println("NullPointerException thrown!");
			
		}
		}
}
