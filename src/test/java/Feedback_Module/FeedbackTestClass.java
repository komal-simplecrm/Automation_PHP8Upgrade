package Feedback_Module;

import java.time.Duration;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Library_Files.Base_Class;
import Library_Files.UtilityClass;
import Login_Page.Dashboard;
import POM_Feedback_Module.Add_Feedback;
import POM_Lead_Module.Add_Lead;
import POM_Lead_Module.Lead_ListView;

public class FeedbackTestClass extends Base_Class
{
	Dashboard dashboard;
	Add_Lead create_Lead;
	Lead_ListView list_View;
	Add_Feedback feedback;
	
	@BeforeMethod(alwaysRun = true)
	public void OpenBrowser() throws InterruptedException
	{
		dashboard = new Dashboard(driver);
		create_Lead = new Add_Lead(driver);
		list_View = new Lead_ListView(driver);
		feedback = new Add_Feedback(driver);
		
		//directed to the dash board page
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Thread.sleep(2000);
		dashboard.clickOnMenuOption();
		dashboard.clickOnSearch("Feedback");
		Thread.sleep(4000);
		dashboard.clickOnMenuOption(driver,"Feedback");
		Thread.sleep(2000);
		dashboard.closeMenuOption();
		Thread.sleep(4000);
	}
	
	//Create test case for create multiple Account
	@Test( groups={"Create", "Sanity"})
	public void GiveFeedback() throws Exception 
	{
		//Create this test case in Extent Report
		test= extent.createTest("Give Feedback").assignAuthor("Komal")
							.assignCategory("Functional Test Case").assignDevice("Chrome");
						
			Thread.sleep(3000);
			list_View.clickOnAddButton();
						 
		try {
							
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
				feedback.enterAntonymsOfSatisfaction(UtilityClass.fetchDataFromExcelSheet("Feedback",1,0));
				feedback.enterIssueResolved(UtilityClass.fetchDataFromExcelSheet("Feedback",1,1));
				feedback.enterDateEntered(UtilityClass.fetchDataFromExcelSheet("Feedback",1,2));
				feedback.enterArticulateTroubleshootingSteps(UtilityClass.fetchDataFromExcelSheet("Feedback",1,3));
				feedback.enterRating(UtilityClass.fetchDataFromExcelSheet("Feedback",1,4));
				feedback.enterReferance(UtilityClass.fetchDataFromExcelSheet("Feedback",1,5));
				feedback.selectCases(driver, UtilityClass.fetchDataFromExcelSheet("Feedback",1,6));
				feedback.enterUnderstandEngineerClearly(UtilityClass.fetchDataFromExcelSheet("Feedback",1,7));
				//create_Lead.AssignedTo(driver, UtilityClass.fetchDataFromExcelSheet("Feedback",1,8));
				feedback.enterPrintFeedbackOnWebsite(UtilityClass.fetchDataFromExcelSheet("Feedback",1,9));
				feedback.enterRemarks(UtilityClass.fetchDataFromExcelSheet("Feedback",1,10));
				create_Lead.scrollpage(driver);
				create_Lead.clickOnSavebtn();
			}
			catch(NullPointerException e)
			{
				System.out.println("NullPointerException thrown!");	
			}
					
		}
		
}	

