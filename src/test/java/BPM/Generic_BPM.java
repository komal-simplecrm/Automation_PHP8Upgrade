package BPM;

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
import POM_BPM.BPM_Elements;
import POM_Cases_Module.Create_Case;
import POM_Cases_Module.Duplicate_Case;
import POM_Lead_Module.Add_Lead;
import POM_Lead_Module.Lead_ListView;
import POM_Opportunities_Module.Add_Opportunities;

public class Generic_BPM extends Base_Class
{
	boolean tasks;
	
	//String casenumber;
	//String detailviewsubject;
	
	String title;
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
	
	
	public void PanelPresence(String casenumber, String detailviewsubject,int j,int k, int i) throws InterruptedException, EncryptedDocumentException, IOException
	{ 
		
		//Column index 0, 1, 2
		
		driver.navigate().refresh();
		list_View.rightSideMenu(driver, "BPM Process");
		
		String ribbon = BPM.getRibbonName(driver, i);
		
		Thread.sleep(3000);
		if(ribbon.equals("In Progress"))
		{
			
			
			String panelid = BPM.checkElementPresence(driver,i);
			test.info("Panel id is: " +panelid);
			String assignedTo1 = BPM.getAssignedToOnBPM(driver,i);
			String assignedTo = assignedTo1.toLowerCase();
			System.out.println(assignedTo);
			test.info("task is assigned to " +assignedTo);
			String ribbon1 = BPM.getRibbonName(driver, i);
			test.info(ribbon1+ " status on Ribbon.");
			String[] Username = BPM.User(assignedTo);
			String UN = Username[0];
			System.out.println(UN);
			String Pass = Username[1];
			System.out.println(Pass);
			Thread.sleep(5000);
			String currentuser1 = BPM.currentProfile(driver);
			String currentuser = currentuser1.toLowerCase();
			System.out.println(currentuser);
			tasks = BPM.CheckCondTask(driver, i);
			//test.info("tasks is " +tasks);
			System.out.println(tasks);
					
					if(assignedTo.equalsIgnoreCase(currentuser))
					{ 				
			
						if(tasks==true)
						{
							String cond1 = BPM.selectConditionalTask(driver,UtilityClass.fetchDataFromExcelSheet("BPM",k, j),i);//pass the conditional task of BPM flow
							System.out.println(cond1);
							BPM.clickOnNextStep(driver,i);
							Thread.sleep(4000);
						}
						else if(tasks==false)
						{
								BPM.clickOnNextStep(driver,i);
								Thread.sleep(4000);
						}
		
					}
			
	
				else{
					
					try{
						String title = Tempdriver.getTitle();
						System.out.println(title);
					}catch(Exception e)
					{
						initializeBrowserForOpprotunitiesToFeedbackFlow("Edge", UN, Pass);
						System.out.print("Edge browser is closed");
					}
					/*if(title=="SimpleCRM")
					{
						System.out.print("Edge browser is closed");
						test.info("Assigned to and Current user different so Edge browser is opening.");
						//initializeBrowserForOpprotunitiesToFeedbackFlow("Edge", UN, Pass);
					
					}*/
					
						//test.info("Assigned to and Current user different so Edge browser is opening.");
				
					
						//initializeBrowserForOpprotunitiesToFeedbackFlow("Edge", UN, Pass);
						BPM = new BPM_Elements(Tempdriver);
						dashboard = new Dashboard(Tempdriver) ;
						list_View =new Lead_ListView(Tempdriver);
						cases = new Create_Case(Tempdriver);
					
						/*if(title.equals("SimpleCRM"))
						{
							Tempdriver.navigate().refresh();
						}
						else
						{*/
							//Diverted towards Cases module on Edge browser using assignedTO user profile
							Thread.sleep(5000);
							test.info("Diverted towards Cases module on Edge browser.");
							dashboard.clickOnMenuOptionDashboard(Tempdriver);
							dashboard.clickOnSearch("Cases");
							Tempdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(70));
							dashboard.clickOnMenuOption(Tempdriver,"Cases");
		
		
							Thread.sleep(5000);
		
							dashboard.clickOnMenuOption();
							test.info("Filter the case using " +casenumber+ " and" +detailviewsubject+ ".");
							Thread.sleep(5000);
							list_View.clickSettingInListView(Tempdriver,"Filter",casenumber,detailviewsubject,"Case");
							cases.clickOnSubject(Tempdriver, casenumber, detailviewsubject);
							Thread.sleep(2000);	
							//test.info("Clicked on BPM icon.");
							list_View.rightSideMenu(Tempdriver, "BPM Process");
						//}
						String currentuser2 = BPM.currentProfile(Tempdriver);
						String currentuser3 = currentuser2.toLowerCase();
						test.info(currentuser3+ " current user logged in.");
						System.out.println(currentuser3);
						String assignedTo2 = BPM.getAssignedToOnBPM(Tempdriver,i);
						String assignedTo3 = assignedTo2.toLowerCase();
						System.out.println(assignedTo3);
					
					
					
					
						tasks = BPM.CheckCondTask(driver, i);
						System.out.println(tasks);
						test.info("Assigned to and Current user both are same.");
			
							if(assignedTo3.equalsIgnoreCase(currentuser3))
							{
								
								if(tasks==true)
								{
							
									String cond1 = BPM.selectConditionalTask(Tempdriver,UtilityClass.fetchDataFromExcelSheet("BPM",k, j),i);//pass the conditional task of BPM flow
									test.info(cond1+ " Conditional task is selected.");
									System.out.println(cond1);
									BPM.clickOnNextStep(Tempdriver,i);	
									Thread.sleep(4000);
								}
								else  if(tasks==false)
								{
										BPM.clickOnNextStep(Tempdriver,i);	
										Thread.sleep(4000);
								}
							}
							
								String assignedTo4 = BPM.getAssignedToOnBPM(Tempdriver,i+1);
								String assignedTo5 = assignedTo4.toLowerCase();
								test.info("task is assigned to " +assignedTo5);
								//String ribbon2 = BPM.getRibbonName(driver, i+1);
								System.out.println("AssignedTo " +assignedTo5+" != " +currentuser3);
								String currentuser4 = BPM.currentProfile(Tempdriver);
								String currentuser5 = currentuser4.toLowerCase();
								test.info(currentuser4+ " current user logged in.");
								if(!assignedTo5.equals(currentuser5))
								{
									test.info("Assigned to and Current user different so closed the driver.");
									try {
									Tempdriver.close();
									}
									catch(NoSuchSessionException e)
									{
										System.out.println("NoSuchSessionException thrown!");
									}
								}
			
							
					}
					if(tasks == true)	
					{
							j++;	
					}
					i++;
		
				PanelPresence(casenumber,detailviewsubject,j,k,i);
			
					
				
		}
		//String ribbon1 = BPM.getRibbonName(driver, i+1);
		//if(ribbon.equals("Completed"))
		else if(ribbon.equals("Completed"))
		{
			//Tempdriver.close();
			//driver.switchTo().defaultContent();
			//Tempdriver.manage().deleteAllCookies();
		}
		
	}	
	
	

	@Test(groups = "BPM")
	public void BPM_Flow() throws EncryptedDocumentException, InterruptedException, IOException
	
	{
		//Create this test case in Extent Report
				test= extent.createTest("BPM_Flow").assignAuthor("Komal")
							.assignCategory("Functional Test Case").assignDevice("Chrome");
				
				
				SoftAssert soft=new SoftAssert();
	for(int k=1;k<=6;k++)
	{
		try {	
				int i = 0;
				int j=0;
				Thread.sleep(7000);
				
				//Create object of Create_Case POM class
				cases = new Create_Case(driver);
				dashboard=new Dashboard(driver);
				list_View =new Lead_ListView(driver);
				add_Opportunities= new Add_Opportunities(driver);
				create_Lead=new Add_Lead(driver);
				duplicate_case = new Duplicate_Case(driver);
				BPM = new BPM_Elements(driver);
				
				list_View.clickOnAddButton();
				Thread.sleep(2000);
			cases.selectPriority(driver, UtilityClass.fetchDataFromExcelSheet("Cases",1, 0));
			cases.selectState(driver, UtilityClass.fetchDataFromExcelSheet("Cases",1, 1), UtilityClass.fetchDataFromExcelSheet("Cases",1, 2),UtilityClass.fetchDataFromExcelSheet("Cases",1, 7));
			cases.selectType(driver, UtilityClass.fetchDataFromExcelSheet("Cases",1, 3));
			add_Opportunities.enterAccountName(driver,UtilityClass.fetchDataFromExcelSheet("Cases",k, 4));
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
			
			String detailviewsubject = duplicate_case.getDetailViewText(driver,"Subject");
			test.info(casenumber+ " , " +detailviewsubject+ " Case is created manually.");
			//status = New
			soft.assertEquals(status, "New");
			
			list_View.rightSideMenu(driver, "BPM Process");
			test.info("Clicked on BPM icon on right side bar.");
			//BPM.checkElementPresence(driver);
			String BPM_Name = BPM.getBPM_Name();
			test.info("BPM is triggered: " +BPM_Name);
			//System.out.println(BPM_Name);
			
				PanelPresence(casenumber,detailviewsubject,j,k,i);
				Thread.sleep(2000);
				driver.navigate().refresh();
				Thread.sleep(2000);
				add_Opportunities.backToListView();
				//driver.switchTo().defaultContent();
		
			
			
			}catch(NullPointerException e) 
			{
				System.out.println("NullPointerException thrown!");
				//Tempdriver.switchTo().defaultContent();
				
			}
		
		}
	}
	
	
	public void PanelPresence1(String casenumber, String detailviewsubject,int j,int k, int i) throws InterruptedException, EncryptedDocumentException, IOException
	{ 
		
		//Column index 0, 1, 2
		
		driver.navigate().refresh();
		list_View.rightSideMenu(driver, "BPM Process");
		
		String ribbon = BPM.getRibbonName(driver, i);
		Thread.sleep(3000);
		if(ribbon.equals("In Progress"))
		{
			
			
			String panelid = BPM.checkElementPresence(driver,i);
			test.info("Panel id is: " +panelid);
			String assignedTo1 = BPM.getAssignedToOnBPM(driver,i);
			String assignedTo = assignedTo1.toLowerCase();
			System.out.println(assignedTo);
			test.info("task is assigned to " +assignedTo);
			String ribbon1 = BPM.getRibbonName(driver, i);
			test.info(ribbon1+ " status on Ribbon.");
			String[] Username = BPM.User(assignedTo);
			String UN = Username[0];
			System.out.println(UN);
			String Pass = Username[1];
			System.out.println(Pass);
			Thread.sleep(5000);
			String currentuser1 = BPM.currentProfile(driver);
			String currentuser = currentuser1.toLowerCase();
			System.out.println(currentuser);
			tasks = BPM.CheckCondTask(driver, i);
		
			System.out.println(tasks);
					
					if(assignedTo.equalsIgnoreCase(currentuser))
					{ 				
			
						if(tasks==true)
						{
							String cond1 = BPM.selectConditionalTask(driver,UtilityClass.fetchDataFromExcelSheet("BPM",k, j),i);//pass the conditional task of BPM flow
							System.out.println(cond1);
							BPM.clickOnNextStep(driver,i);
							Thread.sleep(4000);
						}
						else if(tasks==false)
						{
								BPM.clickOnNextStep(driver,i);
								Thread.sleep(4000);
						}
		
					}
			
	
				else{
					
					try{
						String title = Tempdriver.getTitle();
						System.out.println(title);
					}catch(Exception e)
					{
						initializeBrowserForOpprotunitiesToFeedbackFlow("Edge", UN, Pass);
						System.out.print("Edge browser is closed");
					}
					
					
						BPM = new BPM_Elements(Tempdriver);
						dashboard = new Dashboard(Tempdriver) ;
						list_View =new Lead_ListView(Tempdriver);
						cases = new Create_Case(Tempdriver);
					
					
							//Diverted towards Cases module on Edge browser using assignedTO user profile
							Thread.sleep(5000);
							test.info("Diverted towards Cases module on Edge browser.");
							dashboard.clickOnMenuOptionDashboard(Tempdriver);
							dashboard.clickOnSearch("Cases");
							Tempdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(70));
							dashboard.clickOnMenuOption(Tempdriver,"Cases");
		
		
							Thread.sleep(5000);
							String currentuser2 = BPM.currentProfile(Tempdriver);
							String currentuser3 = currentuser2.toLowerCase();
							test.info(currentuser3+ " current user logged in.");
							System.out.println(currentuser3);
							
							/*if(assignedTo.equalsIgnoreCase(currentuser3))
							{ 
								Tempdriver.navigate().refresh();
								
							}
							else if(!assignedTo.equals(currentuser3))
							{*/
								dashboard.clickOnMenuOption();
								test.info("Filter the case using " +casenumber+ " and" +detailviewsubject+ ".");
								Thread.sleep(5000);
								list_View.clickSettingInListView(Tempdriver,"Filter",casenumber,detailviewsubject,"Case");
								cases.clickOnSubject(Tempdriver, casenumber, detailviewsubject);
								Thread.sleep(2000);	
						
								list_View.rightSideMenu(Tempdriver, "BPM Process");
							//}
						
						String assignedTo2 = BPM.getAssignedToOnBPM(Tempdriver,i);
						String assignedTo3 = assignedTo2.toLowerCase();
						System.out.println(assignedTo3);
					
						tasks = BPM.CheckCondTask(driver, i);
						System.out.println(tasks);
						test.info("Assigned to and Current user both are same.");
			
							if(assignedTo3.equalsIgnoreCase(currentuser3))
							{
								
								if(tasks==true)
								{
							
									String cond1 = BPM.selectConditionalTask(Tempdriver,UtilityClass.fetchDataFromExcelSheet("BPM",k, j),i);//pass the conditional task of BPM flow
									test.info(cond1+ " Conditional task is selected.");
									System.out.println(cond1);
									BPM.clickOnNextStep(Tempdriver,i);	
									Thread.sleep(4000);
								}
								else  if(tasks==false)
								{
										BPM.clickOnNextStep(Tempdriver,i);	
										Thread.sleep(4000);
								}
							}
							
								String assignedTo4 = BPM.getAssignedToOnBPM(Tempdriver,i+1);
								String assignedTo5 = assignedTo4.toLowerCase();
								test.info("task is assigned to " +assignedTo5);
								
								System.out.println("AssignedTo " +assignedTo5+" != " +currentuser3);
								String currentuser4 = BPM.currentProfile(Tempdriver);
								String currentuser5 = currentuser4.toLowerCase();
								test.info(currentuser4+ " current user logged in.");
								if(!assignedTo5.equals(currentuser5))
								{
									test.info("Assigned to and Current user different so closed the driver.");
									try {
									Tempdriver.close();
									}
									catch(NoSuchSessionException e)
									{
										System.out.println("NoSuchSessionException thrown!");
									}
								}
			
							
					}
					if(tasks == true)	
					{
							j++;	
					}
					i++;
		
				PanelPresence1(casenumber,detailviewsubject,j,k,i);
			
					
				
		}
		
		else if(ribbon.equals("Completed"))
		{
			
		}
		
	}	
	
}
